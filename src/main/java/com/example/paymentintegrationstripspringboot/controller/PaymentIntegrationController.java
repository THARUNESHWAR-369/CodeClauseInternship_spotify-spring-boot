package com.example.paymentintegrationstripspringboot.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PaymentIntegrationController implements ErrorController {

        @RequestMapping("/error")
        public String handleError() {
            // Customize this method to return your custom error page.
            return "error"; // Assuming you have an "error.html" template in your templates directory.
        }

        public String getErrorPath() {
            return "/error";
        }

    @RequestMapping("/success")
    public String successRedirect() {
        return "success";
    }

}
