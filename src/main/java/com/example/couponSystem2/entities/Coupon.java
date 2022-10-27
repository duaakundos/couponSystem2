package com.example.couponSystem2.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
@Builder
@ToString
@Table(name = "coupon")
public class Coupon {
    @Id
    @GeneratedValue
    @Column(name = "coupon_id")
    private int id;
    @ManyToOne(targetEntity = Company.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    @JsonIgnore
    private Company company;
    @Column(name = "coupon_category_id")
    private Category category;
    @Column(name = "coupon_title")
    private String title;
    @Column(name = "coupon_description")
    private String description;
    @Column(name = "start_date")
    private java.sql.Date startDate;
    @Column(name = "end_date")
    private java.sql.Date endDate;
    @Column(name = "coupon_amount")
    private int amount;
    @Column(name = "coupon_price")
    private double price;
    @Column(name = "coupon_image")
    private String image;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "customer_list_of_coupons",
            joinColumns = @JoinColumn(name = "coupon_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id"))
    @ToString.Exclude
    @JsonIgnore
    private List<Customer> customersList;


}
