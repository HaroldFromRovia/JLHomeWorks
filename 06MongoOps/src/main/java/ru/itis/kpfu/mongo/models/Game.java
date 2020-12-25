package ru.itis.kpfu.mongo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "games")
public class Game {

    private String id;
    private String name;
    private List<String> tags;
    private Double averageRating;
    private Double price;

}
