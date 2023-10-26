package com.example.paymentintegrationstripspringboot.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.net.StripeResponse;
import com.stripe.param.ChargeCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StripeService {
    @Value("${stripe.api.secretKey}")
    private String stripeApiKey;

    public StripeResponse getStripeResponse() {
        // Implement logic to obtain and return StripeResponse object
        // Example: Stripe.apiKey = stripeSecretKey; return Stripe.<StripeAPIOperation>.retrieve("<RESOURCE_ID>");
        return null; // Implement your logic here
    }

    public Charge cardCharge(String token, long amount) throws StripeException {
        Stripe.apiKey = stripeApiKey;

        Charge charge = Charge.create(
                new ChargeCreateParams.Builder()
                        .setSource(token)
                        .setAmount(amount)
                        .setCurrency("usd")
                        .build()
        );

        return charge;
    }

}
