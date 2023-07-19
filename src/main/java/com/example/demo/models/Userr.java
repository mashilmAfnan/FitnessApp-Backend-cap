package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
//import javax.persistence.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Userr")
public class Userr {
    @Id
    private Integer user_info_id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private UserInfo userInfo;

    @OneToMany(mappedBy = "user")
    private List<Subscription> subscriptions= new ArrayList<>();

    @ManyToMany(mappedBy = "users")
    private List<Subscriber> subscribers= new ArrayList<>();
}
