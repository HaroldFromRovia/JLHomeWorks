package ru.itis.kpfu.querydsl.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;

    @ManyToMany
    @JoinTable(name = "game_shop")
    private List<Game> games;

    @ManyToMany
    @JoinTable(name = "user_shop")
    private List<User> users;

}
