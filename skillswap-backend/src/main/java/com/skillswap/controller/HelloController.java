package com.skillswap.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/*
This controller is used to test
 * whether the back end server is running
 * HelloController handles basic test APIs.
 * This endpoint is used to verify that
 * the SkillSwap backend is running.
*/
@RestController //handles web requests
@RequestMapping("/api")//base url
public class HelloController {
	// This method returns a simple message
    @GetMapping("/hello")//handles GET requests
    public String hello() {
        return "SkillSwap backend is running!";
    }
}
