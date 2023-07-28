package com.example.demo.models;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "subscription")
public class Subscription {
    @Id
    @GeneratedValue
    private Integer id;
    private Double total_price;
    private LocalDate start_date;
    private LocalDate end_date;

    @ManyToOne
    @JoinColumn(name = "package_id")
    private Package packageEntity;

    @ManyToOne
    @JoinColumn(name = "discount")
    private Discount discount;


    @ManyToMany(mappedBy = "subscriptions")
    private List<UserRole> users;


    @OneToOne(mappedBy = "subscription")
    private Subscriber subscriber;
}
