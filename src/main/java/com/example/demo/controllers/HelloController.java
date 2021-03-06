package com.example.demo.controllers;


import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.RequestWrapper;
import javax.servlet.http.Cookie;

@Controller
public class HelloController {



    @RequestMapping(value = "")
    @ResponseBody
    public String index(HttpServletRequest request, HttpServletResponse response) {
        String userName = request.getParameter("name");
        if ((request.getCookies() == null && userName == null) || (request.getCookies() == null && userName == "")) {
            userName = "World";
            Cookie enCookie = new Cookie("en","0");
            response.addCookie(enCookie);
            Cookie frCookie = new Cookie("fr","0");
            response.addCookie(frCookie);
            Cookie esCookie = new Cookie("es","0");
            response.addCookie(esCookie);
            Cookie pgCookie = new Cookie("pg","0");
            response.addCookie(pgCookie);
            Cookie grCookie = new Cookie("gr","0");
            response.addCookie(grCookie);
            Cookie kgCookie = new Cookie("kg","0");
            response.addCookie(kgCookie);
            Cookie totalCookie = new Cookie("total", "0");
            response.addCookie(totalCookie);
            Cookie lastLangCookie = new Cookie("lastLang", "en");
            response.addCookie(lastLangCookie);
            Cookie nameCookie = new Cookie("name", null);
            response.addCookie(nameCookie);
        } else {
            Cookie[] valueCookie = request.getCookies();
            for (Cookie iteration : valueCookie) {
                if (iteration.getName().contains("name")) {
                    userName = iteration.getValue();
                }
            }
        }
        if (userName == "") {
            userName = "World";
        }

        String html = "<p>Hello, " + userName + "</p>" +
                "<p><a href='/hello'>Say hello to someone.</a></p>" +
                "<p><a href='/greeting'>Greet me in multiple languages.</a></p>" +
                "<p><a href='/goodbye'>Say 'goodbye' for now.</a></p>";
        return html;
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
    public String helloPost(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        Cookie nameCookie = new Cookie("name", name);
        response.addCookie(nameCookie);
        String html = "<p>Hello " + name + "</p><p><a href='/'>Go to index.</a></p>";
        return html;
    }

    @RequestMapping(value = "greeting", method=RequestMethod.GET)
    @ResponseBody
    public static String createMessage(HttpServletResponse response,  HttpServletRequest request) {
        String nameValue = "";
        String lastLang = "en";
        if (request.getCookies() == null) {
            Cookie enCookie = new Cookie("en","0");
            response.addCookie(enCookie);
            Cookie frCookie = new Cookie("fr","0");
            response.addCookie(frCookie);
            Cookie esCookie = new Cookie("es","0");
            response.addCookie(esCookie);
            Cookie pgCookie = new Cookie("pg","0");
            response.addCookie(pgCookie);
            Cookie grCookie = new Cookie("gr","0");
            response.addCookie(grCookie);
            Cookie kgCookie = new Cookie("kg","0");
            response.addCookie(kgCookie);
            Cookie totalCookie = new Cookie("total", "0");
            response.addCookie(totalCookie);
            Cookie lastLangCookie = new Cookie("lastLang", "en");
            response.addCookie(lastLangCookie);
            Cookie nameCookie = new Cookie("name", null);
            response.addCookie(nameCookie);
        } else {
            Cookie[] valueCookie = request.getCookies();
            for (Cookie iteration : valueCookie) {
                if (iteration.getName().contains("name")){
                    nameValue = iteration.getValue();
                }
                if (iteration.getName().contains("lastLang")) {
                    lastLang = iteration.getValue();
                }
            }
        }
        String html = "<form method='post'>" +
                "<input type='text' name='user' value='" + nameValue + "'/>" +
                "<select name='lang'>";
        switch(lastLang) {
            case "en":
                html = html + "<option value='en' selected>English</option>" +
                        "<option value='fr'>French</option>" +
                        "<option value='es'>Spanish</option>" +
                        "<option value='pg'>Portuguese</option>" +
                        "<option value='gr'>German</option>" +
                        "<option value='kg'>Klingon</option>";
                break;
            case "fr":
                html = html + "<option value='en'>English</option>" +
                        "<option value='fr' selected>French</option>" +
                        "<option value='es'>Spanish</option>" +
                        "<option value='pg'>Portuguese</option>" +
                        "<option value='gr'>German</option>" +
                        "<option value='kg'>Klingon</option>";
                break;
            case "es":
                html = html + "<option value='en'>English</option>" +
                        "<option value='fr'>French</option>" +
                        "<option value='es' selected>Spanish</option>" +
                        "<option value='pg'>Portuguese</option>" +
                        "<option value='gr'>German</option>" +
                        "<option value='kg'>Klingon</option>";
                break;
            case "pg":
                html = html + "<option value='en'>English</option>" +
                        "<option value='fr'>French</option>" +
                        "<option value='es'>Spanish</option>" +
                        "<option value='pg' selected>Portuguese</option>" +
                        "<option value='gr'>German</option>" +
                        "<option value='kg'>Klingon</option>";
                break;
            case "gr":
                html = html + "<option value='en'>English</option>" +
                        "<option value='fr'>French</option>" +
                        "<option value='es'>Spanish</option>" +
                        "<option value='pg'>Portuguese</option>" +
                        "<option value='gr' selected>German</option>" +
                        "<option value='kg'>Klingon</option>";
                break;
            case "kg":
                html = html + "<option value='en'>English</option>" +
                        "<option value='fr'>French</option>" +
                        "<option value='es'>Spanish</option>" +
                        "<option value='pg'>Portuguese</option>" +
                        "<option value='gr'>German</option>" +
                        "<option value='kg' selected>Klingon</option>";
                break;
        }

        html = html + "<input type='submit' value='Greet me!' /></form>";

        return html;

    }
    @RequestMapping(value = "greeting", method=RequestMethod.POST)
    @ResponseBody
    public static String giveMessage(HttpServletResponse response, HttpServletRequest request) {
        String name = request.getParameter("user");
        String lang = request.getParameter("lang");
        Cookie[] myCookies = request.getCookies();
        String select = "";
        String currentLang = "";
        String currentLangView = "";
        String totalView = "";
        switch(lang) {
            case "en":
                select = "Hello, ";
                currentLang = "English";
                break;
            case "fr":
                select = "Bonjour, ";
                currentLang = "French";
                break;
            case "es":
                select = "Hola, ";
                currentLang = "Spanish";
                break;
            case "pg":
                select = "Olá, ";
                currentLang = "Portuguese";
                break;
            case "gr":
                select = "Guten tag, ";
                currentLang = "German";
                break;
            case "kg":
                select = "nuqneH, ";
                currentLang = "Klingon";
                break;
        }
        for (Cookie iteration : myCookies) {
            if (iteration.getName().contains(lang) || iteration.getName().contains("total")) {
                String countStr = iteration.getValue();
                int countInt = Integer.parseInt(countStr);
                countInt++;
                countStr = Integer.toString(countInt);
                iteration.setValue(countStr);
                if (iteration.getName().contains("total")) {
                    totalView = iteration.getValue();
                } else {
                    currentLangView = iteration.getValue();
                }
                response.addCookie(iteration);
            }
        }

        Cookie nameCookie = new Cookie("name", name);
        response.addCookie(nameCookie);
        Cookie langCookie = new Cookie("lastLang", lang);
        response.addCookie(langCookie);


        String html = "<h2>" + select + name + "!</h2>";
        if (lang.contains("kg")) {
            html = html + "<img src='http://2.bp.blogspot.com/-3Lpva--N9pU/U1JJVvT7DFI/AAAAAAAAIYA/xn88y-DyKgI/s1600/Rockin+Worf.jpg'" +
                    "style='width: 249; height: 342;'>";
        }
        html = html + "<p>You viewed this page in " + currentLang + " " + currentLangView + " time(s) " +
                "and you have seen this greeting in any language " + totalView + " time(s)." +
                "<p><a href='/greeting'>Greet me again!</a></p><p><a href='/'>Go to index.</a></p>";

        return html;
    }

    @RequestMapping(value="hello/{name}")
    @ResponseBody
    public String helloUrlSegment(@PathVariable String name, HttpServletResponse response, HttpServletRequest request) {

        if (request.getCookies() != null) {
            Cookie[] valueCookie = request.getCookies();
            for (Cookie iteration : valueCookie) {
                if (iteration.getName().contains("name")) {
                    Cookie nameCookie = new Cookie("name", name);
                    nameCookie.setPath("/");
                    response.addCookie(nameCookie);
                }
            }
        } else {
            Cookie enCookie = new Cookie("en","0");
            enCookie.setPath("/");
            response.addCookie(enCookie);
            Cookie frCookie = new Cookie("fr","0");
            frCookie.setPath("/");
            response.addCookie(frCookie);
            Cookie esCookie = new Cookie("es","0");
            esCookie.setPath("/");
            response.addCookie(esCookie);
            Cookie pgCookie = new Cookie("pg","0");
            pgCookie.setPath("/");
            response.addCookie(pgCookie);
            Cookie grCookie = new Cookie("gr","0");
            grCookie.setPath("/");
            response.addCookie(grCookie);
            Cookie kgCookie = new Cookie("kg","0");
            kgCookie.setPath("/");
            response.addCookie(kgCookie);
            Cookie totalCookie = new Cookie("total", "0");
            totalCookie.setPath("/");
            response.addCookie(totalCookie);
            Cookie lastLangCookie = new Cookie("lastLang", "en");
            lastLangCookie.setPath("/");
            response.addCookie(lastLangCookie);
            Cookie nameCookie = new Cookie("name", name);
            nameCookie.setPath("/");
            response.addCookie(nameCookie);
        }

        return "Hello " + name + "<p><a href='/'>Go to index.</a></p>";
        }

    @RequestMapping(value = "goodbye")
    public String goodbye(HttpServletResponse response, HttpServletRequest request) {
        if (request.getCookies() != null) {
            Cookie[] myCookies = request.getCookies();
            for (Cookie iteration : myCookies) {
                if (iteration.getName().contains("name")) {
                    iteration.setValue("");
                    iteration.setPath("/");
                    response.addCookie(iteration);
                }
            }
        }
        return "redirect:/";
    }

}
