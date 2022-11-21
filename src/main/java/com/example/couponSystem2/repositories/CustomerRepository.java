package com.example.couponSystem2.repositories;

import com.example.couponSystem2.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    //isCustomerExists
    boolean existsCustomerByEmailAndPassword(String email, String password);
    boolean existsCustomerByEmail(String email);
    boolean existsCustomerByEmailAndIdIsNot(String email,int id);
    //update customer
    @Transactional
    @Modifying
    @Query(value = "update customers c set c.customer_first_name = :firstName,c.customer_last_name = :lastName,c.customer_email = :email, c.customer_password = :password where c.customer_id = :id", nativeQuery = true)
    void updateCustomer(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("email") String email, @Param("password") String password, @Param("id") int id);

    //getCustomerID
    @Query(value = "select customer_id from customers c where c.customer_email = :email and c.customer_password = :password", nativeQuery = true)
    int getCustomerID(@Param("email") String email, @Param("password") String password);
}
