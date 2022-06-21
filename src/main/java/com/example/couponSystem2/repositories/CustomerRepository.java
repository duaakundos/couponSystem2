package com.example.couponSystem2.repositories;

import com.example.couponSystem2.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
