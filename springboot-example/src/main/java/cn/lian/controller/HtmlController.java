package cn.lian.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


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

    @RequestMapping(value = "/hello")
    public String hello(Model model) {
        model.addAttribute("name", "Dear");
        return "hello";
    }
}
