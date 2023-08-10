package com.example.demo.models;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "feedback")
public class Feedback {
    @Id
    @GeneratedValue
    private  Integer id;

    private String content;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "subscriber_id")
    private Subscriber subscriber;

}
