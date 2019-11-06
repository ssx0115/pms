package com.offcn.project.controller;

import com.offcn.project.bean.Attachment;
import com.offcn.project.service.AttachmentService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping(value = "/attachment")
public class AttachmentController {
    @Autowired
    private AttachmentService attachmentService;
    //添加附件信息
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public String insertAttachment(Attachment attachment, MultipartFile attachFile, HttpSession session){
        //获取目录
        ServletContext context = session.getServletContext();
        String realPath = context.getRealPath("/upload");
        File file = new File(realPath);
        //判断文件是否存在
        if(!file.exists()){
            file.mkdirs();
        }
        String originalFilename = attachFile.getOriginalFilename();
        String realName = UUID.randomUUID().toString().replaceAll("-","")+originalFilename;
        try {
            attachFile.transferTo(new File(realPath+"/"+realName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        attachment.setPath(realPath+"/"+realName);
        attachmentService.insertAttachment(attachment);
        return "redirect:/attachment/list";
    }
    //查询所有附件信息
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView selectAttachment(){
        ModelAndView view = new ModelAndView("project-file");
        List<Attachment> attachments = attachmentService.selectAttachment();
        view.addObject("attachments",attachments);
        return view;
    }
    //下载附件
    @RequestMapping(value = "/download/{id}",method=RequestMethod.GET)
    public ResponseEntity<byte[]> download(@PathVariable("id") Integer id, HttpSession session) throws Exception{
        Attachment attachment = attachmentService.selectAttachmentById(id);
        String path = attachment.getPath();
        ServletContext context = session.getServletContext();
        String fileName = path;
        String realPath = path;
        FileInputStream fis = new FileInputStream(new File(realPath));
        //
        byte[] body = new byte[fis.available()];
        fis.read(body);
        MultiValueMap<String, String> headers = new HttpHeaders();
        //解决文件名乱码问题
        fileName = new String(fileName.getBytes("gbk"),"iso8859-1");
        //不再被浏览器解析，会被当做附件下载
        headers.add("Content-Disposition", "attachment;filename="+fileName);
        HttpStatus statusCode = HttpStatus.OK;
        ResponseEntity<byte[]> result = new ResponseEntity<byte[]>(body, headers, statusCode);
        return result;
    }
    //查看附件详情
    @RequestMapping(value = "/selectAttachment/{id}",method = RequestMethod.GET)
    public ModelAndView selectAttachmentById(@PathVariable("id") Integer id){
        ModelAndView view = new ModelAndView("project-file-look");
        Attachment attachment = attachmentService.selectAttachmentById(id);
        view.addObject("attachment",attachment);
        return view;
    }
    //修改附件详情要先查到附件
    @RequestMapping(value = "/seachAttachment/{id}",method = RequestMethod.GET)
    public ModelAndView  seachAttachment(@PathVariable("id") Integer id){
        ModelAndView view = new ModelAndView("project-file-edit");
        Attachment attachment = attachmentService.selectAttachmentById(id);
        view.addObject("attachment",attachment);
        return view;
    }
    //修改附件
    @RequestMapping(value="/update",method = RequestMethod.PUT)
    public String update(Attachment attachment,MultipartFile attachFile,HttpSession session){
        attachmentService.update(attachment);
        return "redirect:/attachment/list";
    }
    //导出Excle文件
    @RequestMapping(value = "/exportExcel",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> exportExcel(){
        Workbook wb = new HSSFWorkbook();
        Sheet sheet1 = wb.createSheet("attachment");
        Row row = sheet1.createRow(0);
        Cell [] cell = new HSSFCell[5];
        for (int i = 0; i < 5; i++) {
            cell[i] = row.createCell(i);
        }
        cell[0].setCellValue("ID");
        cell[1].setCellValue("附件名称");
        cell[2].setCellValue("所属项目");
        cell[3].setCellValue("附件信息描述");
        cell[4].setCellValue("备注");
        List<Attachment> attachments = attachmentService.selectAttachment();

        for (int i = 0; i < attachments.size(); i++) {
            Attachment attachment = attachments.get(i);
            Row row1 = sheet1.createRow(i + 1);
            Cell [] cell2 = new HSSFCell[5];
            for (int j = 0; j < 5; j++) {
                cell2[j] = row1.createCell(j);
            }
            cell2[0].setCellValue(attachment.getId());
            cell2[1].setCellValue(attachment.getAttname());
            cell2[2].setCellValue(attachment.getProject().getPname());
            cell2[3].setCellValue(attachment.getAttdis());
            cell2[4].setCellValue(attachment.getRemark());
        }
        FileOutputStream fos= null;

        try {
            fos= new FileOutputStream(new File("d:\\Desktop\\attachment.xls"));
            wb.write(fos);
            fos.close();
        }catch (Exception ex){

        }finally {
            if(fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("code",200);
        map.put("message","下载成功");
        return map;
    }

    //批量删除附件信息
    @RequestMapping(value = "/batchDelete",method =RequestMethod.DELETE)
    public Map<String,Object> batchDelete(@RequestParam("ids[]") Integer [] ids){
        boolean isSuccess = attachmentService.batchDelete(ids);
        Map<String,Object> map = new HashMap<String,Object>();
        if(isSuccess) {
            map.put("statusCode",200);
            map.put("message","删除成功");
        }else{
            map.put("statusCode",500);
            map.put("message","删除失败");
        }
        return map;
    }

}
