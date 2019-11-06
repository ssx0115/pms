package com.offcn.usual.controller;

import com.offcn.usual.bean.Archives;
import com.offcn.usual.service.ArchivesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/archives")
public class ArchivesController {
    @Autowired
    private ArchivesService archivesService;

    //查询所有档案信息
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView selectArchives(){
        ModelAndView view = new ModelAndView("archives");
        List<Map<String,Object>> archivesList = archivesService.selectArchives();
        view.addObject("archivesList",archivesList);
        return view;
    }
}
