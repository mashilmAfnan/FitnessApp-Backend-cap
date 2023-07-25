package com.example.demo.models;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    private Integer id;
    @JsonProperty("type")
    private String type;
    @JsonProperty("price")
    private Double price;

    @OneToMany(mappedBy = "packageEntity")
    private List<Subscription> subscriptions = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "place_of_service_id")
    private PlaceOfService placeOfService;
}
