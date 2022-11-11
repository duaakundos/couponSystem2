package com.example.couponSystem2.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "companies")
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
    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY,
    cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnore
    private List<Coupon> coupons;

}
