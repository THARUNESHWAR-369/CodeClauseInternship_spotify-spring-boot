package com.example.paymentintegrationstripspringboot.controller;

import com.example.paymentintegrationstripspringboot.dto.RequestDto;
import com.stripe.Stripe;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentController {

    @Value("${stripe.api.secretKey}")
    private String stripeSecretKey;


    @PostMapping("/create-checkout-session")
    public String createCheckoutSession(@RequestBody RequestDto body) {
        Stripe.apiKey = stripeSecretKey;

        String YOUR_DOMAIN = "http://localhost:8080";
        SessionCreateParams params =
                SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl(YOUR_DOMAIN + "/success")
                .setCancelUrl(YOUR_DOMAIN + "/")
                .addLineItem(
                        SessionCreateParams.LineItem.builder()
                                .setQuantity(1L)
                                .setPriceData(
                                        SessionCreateParams.LineItem.PriceData.builder()
                                                .setCurrency("inr")
                                                .setProductData(
                                                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                .setName(body.getProductName())
                                                                .build()
                                                )
                                                .setUnitAmount(body.getAmount() * 100)
                                                .build()
                                )
                                .build())
                .build();

        Session session = null;
        try {
            session = Session.create(params);
        } catch (Exception e) {
            // Handle exception
            e.printStackTrace();
        }

        if (session != null) {
            return session.getUrl();
        } else {
            return "redirect:/";
        }
    }

}
