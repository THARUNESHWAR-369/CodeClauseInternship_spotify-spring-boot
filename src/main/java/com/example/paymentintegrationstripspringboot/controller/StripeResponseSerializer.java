package com.example.paymentintegrationstripspringboot.controller;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.stripe.net.StripeResponse;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class StripeResponseSerializer extends JsonSerializer<StripeResponse> {

    @Override
    public void serialize(StripeResponse stripeResponse, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("body", stripeResponse.body());
        jsonGenerator.writeNumberField("code", stripeResponse.code());
        jsonGenerator.writeObjectField("headers", stripeResponse.headers());
        jsonGenerator.writeEndObject();
    }
}
