package com.manager.repairshop.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/login")
    public String viewLoginPage() {
        return "login";
    }

    @GetMapping("")
    public String redirect() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String viewHomePage(HttpServletRequest httpServletRequest) {

        Locale currLocale = httpServletRequest.getLocale();
        String countryCode = currLocale.getCountry();
        String countryName = currLocale.getDisplayCountry();

        String langCode = currLocale.getLanguage();
        String langName = currLocale.getDisplayLanguage();

        System.out.println("countryCode " + countryCode);
        System.out.println("countryName " + countryName);
        System.out.println("langCode " + langCode);
        System.out.println("langName " + langName);

        return "index";
    }

}