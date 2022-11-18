package com.runchen.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Controller
public class HealthCheckController {

    private String opsInfo;

    public HealthCheckController() {

        try {

            BufferedInputStream inputStream = new BufferedInputStream(
                    HealthCheckController.class.getResourceAsStream("/static/healthcheck.html"));
            StringBuilder sb = new StringBuilder();
            byte[] line = new byte[1024];
            while (inputStream.read(line) != -1) {
                sb.append(new String(line));
            }

            opsInfo = sb.toString();
        } catch (FileNotFoundException e){
            opsInfo = "ops info not exist";
        } catch (IOException e) {
            opsInfo = "ops info read error";
        }
    }

    @RequestMapping(value = "/healthcheck.html")
    @ResponseBody
    public String checkHealth() {
        return opsInfo;
    }
}
