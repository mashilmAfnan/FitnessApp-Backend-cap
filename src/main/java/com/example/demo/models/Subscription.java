package com.example.demo.models;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "subscription")
public class Subscription {
    @Id
    @GeneratedValue
    private Integer Id;
    private LocalDate start_date;
    private LocalDate end_date;

    @ManyToOne
    @JoinColumn(name = "package_id")
    private Package packageEntity;

    @ManyToOne
    @JoinColumn(name = "discount_id")
    private Discount discount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Userr user;

    @OneToOne
    @JoinColumn(name = "subscriber_id")
    private Subscriber subscriber;
}
