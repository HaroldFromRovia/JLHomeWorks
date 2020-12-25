package ru.itis.kpfu.querydsl.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Game {

    private Long id;
    private String name;
    private String developer;
    private Double averageRating;

}
