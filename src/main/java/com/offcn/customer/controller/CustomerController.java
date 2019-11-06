package com.offcn.customer.controller;

import com.offcn.customer.bean.Customer;
import com.offcn.customer.service.CustomerService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value = "/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    //添加客户信息
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public String insertInfo(Customer customer){
        customerService.insertInfo(customer);
        return "redirect:/customer/list";
    }
    //查询显示所有客户信息
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView selectCustomer(){
        List<Customer> customers = customerService.setectCustomer();
        ModelAndView view = new ModelAndView("customer");
        view.addObject("customers",customers);
        return view;
    }
    //查询客户详情
    @RequestMapping(value = "/selectCustomerById/{id}",method = RequestMethod.GET)
    public ModelAndView selectCustomerById(@PathVariable("id") Integer id){
        ModelAndView view = new ModelAndView("customer-look");
        Customer customer = customerService.selectCustomerById(id);
        view.addObject("customer",customer);
        return view;
    }
    //编辑要先查客户详情
    @RequestMapping(value = "/edit/{id}",method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable("id") Integer id){
        ModelAndView view = new ModelAndView("customer-edit");
        Customer customer = customerService.edit(id);
        view.addObject("customer",customer);
        return view;
    }
    //修改客户信息
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public String update(Customer customer){
        customerService.update(customer);
        return "redirect:/customer/list";
    }
    //批量删除客户信息
    @RequestMapping(value = "/batchDelete",method = RequestMethod.DELETE)
    public Map<String,Object> batchDelete(@RequestParam("ids[]") Integer [] ids){
        boolean isSuccess = customerService.batchDelete(ids);
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
    //按条件查询
    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public ModelAndView search(Integer cid,String keyword,Integer orderby){
        List<Customer> customers = customerService.search(cid,keyword,orderby);
        ModelAndView view = new ModelAndView("customer");
        view.addObject("customers",customers);
        return view;
    }

    //查询新建项目时的公司名称
    @RequestMapping(value = "/jsonList",method = RequestMethod.GET)
    @ResponseBody
    public List<Customer> infoCustomer(){
        List<Customer> customers = customerService.setectCustomer();
        return customers;
    }
    //查询客户负责人
    @RequestMapping(value = "/info/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Customer infoLeader(@PathVariable("id") Integer id){
        Customer customer = customerService.selectCustomerById(id);
        return customer;
    }
    @RequestMapping(value = "/importExcel",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> importExcel(MultipartFile excel){
        Map<String,Object> map = new HashMap<String, Object>();
        List<Customer> customers = new ArrayList<Customer>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try{
            Workbook wb = WorkbookFactory.create(excel.getInputStream());
            for(int k=0;k<wb.getNumberOfSheets();k++){
                Sheet sheet = wb.getSheetAt(k);
                for (int i=sheet.getFirstRowNum()+1; i<=sheet.getLastRowNum();i++){
                    Row row = sheet.getRow(i);
                    Customer customer = new Customer();
                    if(row != null){

                        String linkMan = row.getCell(1).getStringCellValue();
                        customer.setCompanyperson(linkMan);
                        String companyName = row.getCell(2).getStringCellValue();
                        customer.setComname(companyName);
                        Date dateCellValue = row.getCell(3).getDateCellValue();
                        String format = sdf.format(dateCellValue);
                        Date addTime = sdf.parse(format);
                        customer.setAddtime(addTime);
                        double numericCellValue = row.getCell(4).getNumericCellValue();
                        BigDecimal decimal = new BigDecimal(String.valueOf(numericCellValue));
                        customer.setComphone(decimal.toPlainString());
						   /* for( int j = row.getFirstCellNum(); j<row.getLastCellNum();j++){
								Cell cell = row.getCell(j);
								String value= ExcelUtils.parseExcelValueToString(cell);
								if(i>0 && j==0){
									value = value.substring(0, value.indexOf("."));
									customer.setId(Integer.parseInt(value));
								}
								System.out.print(value+"    ");
							}*/
                        System.out.println();
                    }
                    customers.add(customer);
                }
            }
            System.out.println(customers);
            /*customerService.batchInsert(customers);*/
            map.put("statusCode",200);
            map.put("message","上传成功");
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            System.out.println("出异常了");
            map.put("statusCode",500);
            map.put("message","上传失败");
        }


        return map;
    }
}
