package com.example.orderservice.dto;



public record PaymentResponse(String paymentId, String status, long processedAt) {}
