package com.example.couponSystem2.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
@Builder
public class Coupon {
    @Id
    @GeneratedValue
    @Column(name = "coupon_id")
    private int id;
    @Column(name = "company_id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    private int companyID;
    @Column(name = "coupon_category")
    private Category category;
    @Column(name = "coupon_title")
    private String title;
    @Column(name = "coupon_description")
    private String description;
    @Column(name = "coupon_start-date")
    private Date startDate;
    @Column(name = "coupon_end-date")
    private Date endDate;
    @Column(name = "coupon_amount")
    private int amount;
    @Column(name = "coupon_price")
    private double price;
    @Column(name = "coupon_image")
    private String image;

    @Override
    public String toString() {
        return "Coupon{" +
                "id=" + id +
                ", companyID=" + companyID +
                ", category=" + category +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", amount=" + amount +
                ", price=" + price +
                ", image='" + image + '\'' +
                '}'+ "\n";
    }
}
