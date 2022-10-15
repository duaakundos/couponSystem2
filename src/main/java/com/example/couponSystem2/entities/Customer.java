package com.example.couponSystem2.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
@Builder
@ToString
@Table(name = "Customers")
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
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "customer_list_of_coupons",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "coupon_id"))
    @JsonIgnoreProperties({"company","customersList", "hibernateLazyInitializer"})
    @ToString.Exclude
    private List<Coupon> coupons;

}
