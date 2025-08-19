package com.example.paymentservice.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.paymentservice.dto.PaymentResponse;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @PostMapping
    public PaymentResponse processPayment(@RequestBody Map<String, Object> payload) throws InterruptedException {
        // simulate payment delay
        Thread.sleep(120);
        return new PaymentResponse(
                "pay-" + System.currentTimeMillis(),
                "SUCCESS",
                System.currentTimeMillis()
        );
    }
}
