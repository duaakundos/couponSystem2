package com.example.couponSystem2.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
@Builder
public class Customer {
    @Id
    @GeneratedValue
    @Column(name = "customer_id")
    private int id;
    @Column(name = "customer_first_name")
    private String firstName;
    @Column(name = "customer_last_name")
    private String lastName;
    @Column(name = "customer_email")
    private String email;
    @Column(name = "customer_password")
    private String password;
    @Column(name = "customer_list_of_coupons")
    // todo: join column in order to do the connection one to many?
    //  maybe we need to do it from the repository
    private ArrayList<Coupon> coupons;

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", coupons=" + coupons +
                '}'+ "\n";
    }
}
