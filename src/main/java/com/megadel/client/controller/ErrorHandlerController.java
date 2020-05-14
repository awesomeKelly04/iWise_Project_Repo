package com.megadel.client.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorHandlerController implements ErrorController{

    @Override
    @RequestMapping("/error")
    @ResponseBody
    public String getErrorPath() {
        return "<center>" +
                "<h1>Something went wrong</h1>" +
                "<hr><br>" +
                "<div><h5>Go to <a href='/'>iWise API</a> home page<h5>" +
                "</div>" +
                "</center>";
    }
}
