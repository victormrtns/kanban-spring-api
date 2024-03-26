package br.com.kanban.kanban.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @GetMapping("/user")
    public String hello (){
        return "helloooo";
    }
}
