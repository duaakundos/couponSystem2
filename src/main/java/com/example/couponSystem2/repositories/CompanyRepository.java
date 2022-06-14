package com.example.couponSystem2.repositories;

import com.example.couponSystem2.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer>  {
}
