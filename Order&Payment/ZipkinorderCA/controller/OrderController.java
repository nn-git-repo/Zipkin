package com.example.orderservice.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.orderservice.dto.OrderRequest;
import com.example.orderservice.dto.PaymentResponse;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final RestTemplate restTemplate;
    private final String paymentServiceUrl;

    public OrderController(RestTemplate restTemplate,
                           @Value("${payment.service.url:http://localhost:8082}") String paymentServiceUrl) {
        this.restTemplate = restTemplate;
        this.paymentServiceUrl = paymentServiceUrl;
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest order) {
        var req = Map.of("customer", order.customer(), "amount", order.amount());

        PaymentResponse paymentResp = restTemplate.postForObject(
                paymentServiceUrl + "/payments",
                req,
                PaymentResponse.class
        );

        var response = Map.of(
                "orderId", "order-" + System.currentTimeMillis(),
                "customer", order.customer(),
                "amount", order.amount(),
                "payment", paymentResp
        );

        return ResponseEntity.ok(response);
    }
}
