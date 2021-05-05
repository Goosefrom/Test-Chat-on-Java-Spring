package com.goosefrom.chat.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    @RequestMapping("/")
    public String index(HttpServletRequest request, Model model){
        String nickname = (String) request.getSession().getAttribute("nickname");

        if(nickname == null || nickname.isEmpty()) return "redirect:/login";
        model.addAttribute("nickname", nickname);

        return "chat";
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String showLogin(){
        return "login";
    }

    @RequestMapping(path = "login", method = RequestMethod.POST)
    public String Login(HttpServletRequest request, @RequestParam(defaultValue = "") String nickname){
        nickname = nickname.trim();

        if(nickname.isEmpty()) return "login";
        request.getSession().setAttribute("nickname", nickname);

        return "redirect:/";
    }

    @RequestMapping(path = "logout")
    public String Logout(HttpServletRequest request){
        request.getSession(true).invalidate();

        return "redirect:/login";
    }
}
