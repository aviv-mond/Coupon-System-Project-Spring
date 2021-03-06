package com.jb.CouponSystemProjectP2.Beans;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customers")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private int id;
    @Column(length = 45, nullable = false)
    private String firstName;
    @Column(length = 45, nullable = false)
    private String lastName;
    @Column(length = 45, nullable = false, unique = true)
    private String email;
    @Column(length = 45, nullable = false)
    private String password;
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private List<Coupon> coupons;
}
