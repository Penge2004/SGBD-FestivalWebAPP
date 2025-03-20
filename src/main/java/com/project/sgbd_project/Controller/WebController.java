package com.project.sgbd_project.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String index() {
        return "index";  // This will automatically map to src/main/resources/templates/index.html
    }
}