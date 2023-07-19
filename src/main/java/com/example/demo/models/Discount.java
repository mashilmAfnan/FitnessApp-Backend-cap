package com.example.demo.models;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "discount")
public class Discount {
    @Id
    @GeneratedValue
    private Integer Id;
    private String couponCode;
    private Integer Percentage;

    @OneToMany(mappedBy = "discount")
    private List<Subscription> subscriptions= new ArrayList<>();
}
