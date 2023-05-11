package com.callsign.campsite.main.control;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MainController {
    @PostMapping("/main")
    public String Main(){
        return "hello world";
    }

    @PostMapping("/test")
    public String Test(){
        return "update Test";
    }

    @PostMapping("/test2")
    public String Test2(){
        return "update Test";
    }
}
