package cn.lian.controller;

import cn.lian.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by lianrongfa on 2018/1/19.
 */
@Controller
@RequestMapping("/index")
public class HtmlController {
    @RequestMapping("/html")
    public String test(){
        return "index";
    }
}
