package com.miniordersystem.paymentservice.repository;

import com.miniordersystem.paymentservice.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
