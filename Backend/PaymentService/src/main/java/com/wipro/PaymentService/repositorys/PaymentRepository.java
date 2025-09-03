package com.wipro.PaymentService.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.PaymentService.entities.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
