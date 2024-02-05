package com.example.giftgrower;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class TestController {

    @RequestMapping("/test")
    public String test() {
        System.out.println("TestController test");
        return "test.jsp";
    }

    @RequestMapping("/test2")
    public String test2(Model model) {
        System.out.println("TestController test2");
        model.addAttribute("name", "hello springBoot1234");
        return "test";
    }
}