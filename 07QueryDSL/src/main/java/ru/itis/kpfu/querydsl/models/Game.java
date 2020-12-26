package ru.itis.kpfu.querydsl.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String developer;
    private Double averageRating;

    @ManyToMany(mappedBy = "games",fetch = FetchType.EAGER)
    private List<Shop> shops;

    @ManyToMany(mappedBy = "games")
    private List<User> players;

    @Column
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column
    @Enumerated(EnumType.STRING)
    private GameState gameState;

}
