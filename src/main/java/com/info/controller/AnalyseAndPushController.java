package com.info.controller;

import com.info.model.ArticleResultList;
import com.info.model.User;
import com.info.service.AnalyseRecordService;
import com.info.service.ArticleService;
import com.info.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Created by wayne on 2017/5/10.
 */
@Controller()
@RequestMapping("/api")
public class AnalyseAndPushController {

    @Autowired
    private AnalyseRecordService analyseRecordService;


    @RequestMapping("/analyse/submit.do")
    @ResponseBody
    public Object analyseAndPush(@RequestParam String categoryCode,@RequestParam String articleCode, HttpSession session){

        Object userObj = session.getAttribute("loginUser");
        if(userObj ==null){
            return "对不起,用户未登录！";
        }
        User user = (User) userObj;
        return analyseRecordService.analyseAndPush(user,categoryCode,articleCode);
    }

}
