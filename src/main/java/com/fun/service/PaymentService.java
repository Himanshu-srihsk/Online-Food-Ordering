package com.fun.service;

import com.fun.model.Order;
import com.fun.response.PaymentResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentService
{
    public PaymentResponse createPaymentLink(Order order);
}
