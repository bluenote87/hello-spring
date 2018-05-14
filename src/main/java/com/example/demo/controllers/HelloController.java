package com.example.demo.controllers;


import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.ws.RequestWrapper;

@Controller
public class HelloController {

    @RequestMapping(value = "")
    @ResponseBody
    public String index(HttpServletRequest request) {

        String userName = request.getParameter("name");

        if (userName == null) {
            userName = "World";
        }
        return "Hello, " + userName;
    }

    @RequestMapping(value = "hello", method=RequestMethod.GET)
    @ResponseBody
    public String helloForm() {
        String html = "<form method='post'>" +
                "<input type='text' name='name' />" +
                "<input type='submit' value='Greet me!' />" +
                "</form>";

        return html;
    }

    @RequestMapping(value = "hello", method=RequestMethod.POST)
    @ResponseBody
    public String helloPost(HttpServletRequest request) {
        String name = request.getParameter("name");

        return "Hello " + name;
    }

    @RequestMapping(value = "greeting", method=RequestMethod.GET)
    @ResponseBody
    public static String createMessage() {
        String html = "<form method='post'>" +
                "<input type='text' name='user' />" +
                "<select name='lang'>" +
                "<option value='en' selected>English</option>" +
                "<option value='fr'>French</option>" +
                "<option value='es'>Spanish</option>" +
                "<option value='pg'>Portuguese</option>" +
                "<option value='gr'>German</option>" +
                "<option value='kg'>Klingon</option>" +
                "<input type='submit' value='Greet me!' />" +
                "</form>";
        return html;

    }
    @RequestMapping(value = "greeting", method=RequestMethod.POST)
    @ResponseBody
    public static String createMessage(HttpServletRequest request) {
        String name = request.getParameter("user");
        String lang = request.getParameter("lang");
        String select = "";
        switch(lang) {
            case "en":
                select = "Hello, ";
            case "fr":
                select = "Bonjour, ";
            case "es":
                select = "Hola, ";
            case "pg":
                select = "Olá, ";
            case "gr":
                select = "Guten tag, ";
            case "kg":
                select = "nuqneH, ";
        }
        String html = "<h2>" + select + name + "!</h2>" +
                "<img src='http://2.bp.blogspot.com/-3Lpva--N9pU/U1JJVvT7DFI/AAAAAAAAIYA/xn88y-DyKgI/s1600/Rockin+Worf.jpg' style='width: 249; height: 342;'>";
        return html;
    }

    @RequestMapping(value="hello/{name}")
    @ResponseBody
    public String helloUrlSegment(@PathVariable String name) {
        return "Hello " + name;
    }

    @RequestMapping(value = "goodbye")
    public String goodbye() {

        return "redirect:/";
    }

}
