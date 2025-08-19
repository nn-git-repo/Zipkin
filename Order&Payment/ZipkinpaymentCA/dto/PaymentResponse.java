
package com.example.paymentservice.dto;

public record PaymentResponse(String paymentId, String status, long processedAt) {}
