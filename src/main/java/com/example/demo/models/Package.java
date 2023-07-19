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
@Table (name = "package")
public class Package {
    @Id
    @GeneratedValue
    private Integer Id;
    private String Type;
    private Double price;

    @OneToMany(mappedBy = "package", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Subscription> subscriptions = new ArrayList<>();
@OneToMany(mappedBy = "PlaceOfService", cascade = CascadeType.ALL, orphanRemoval = true)
private List<PlaceOfService> Place = new ArrayList<>();
}
