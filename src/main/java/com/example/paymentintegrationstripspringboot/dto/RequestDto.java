package com.example.paymentintegrationstripspringboot.dto;

import lombok.Getter;

@Getter
//@Setter
public class RequestDto {
    private long amount;
    private String productName;
}
