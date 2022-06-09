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
public class Company {
    @Id
    @GeneratedValue
    @Column(name = "company_id")
    private int id;
    @Column(name = "company_name")
    private String  name;
    @Column(name = "company_email")
    private String  email;
    @Column(name = "company_password")
    private String  password;
    @OneToMany(mappedBy = "company_id", fetch = FetchType.LAZY,
    cascade = CascadeType.ALL)
    private ArrayList<Coupon> coupons;

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", coupons=" + coupons +
                '}' + "\n";
    }
}
