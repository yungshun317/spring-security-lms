package yungshun.chang.springsecuritylms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LMSController {

    @GetMapping("/")
    public String home() {
        return "home";
    }
}
