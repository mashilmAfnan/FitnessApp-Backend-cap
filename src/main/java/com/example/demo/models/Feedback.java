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
@Table (name = "feedback")
public class Feedback {
    @Id
    @GeneratedValue
    private  Integer Id;
    private String content;
    private LocalDate date;

    @ManyToMany(mappedBy = "feedbacks")
    private List<Subscriber> subscribers;

}
