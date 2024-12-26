package ru.ssau.tk.shnurok.lab2.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import ru.ssau.tk.shnurok.lab2.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.shnurok.lab2.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.shnurok.lab2.functions.factory.TabulatedFunctionFactory;

@Controller
public class SettingsController {
    @PostMapping("/settings")
    public String updateSettings(@RequestParam("factoryType") String factoryType, HttpSession session) {

        TabulatedFunctionFactory factory = null;

        if ("array".equals(factoryType)) {
            factory = new ArrayTabulatedFunctionFactory();
        } else if ("linkedlist".equals(factoryType)) {
            factory = new LinkedListTabulatedFunctionFactory();
        }

        session.setAttribute("FACTORY_KEY", factory);

        return "home";
    }
}
