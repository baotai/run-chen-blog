package com.runchen.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author lepdou 2017-08-30
 */
@Controller
public class SignInController {

  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public String login() {
    return "login.html";
  }

}
