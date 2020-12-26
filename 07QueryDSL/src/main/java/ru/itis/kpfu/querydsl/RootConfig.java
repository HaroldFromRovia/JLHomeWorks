package ru.itis.kpfu.querydsl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import ru.itis.kpfu.querydsl.models.*;
import ru.itis.kpfu.querydsl.repositories.interfaces.GameRepository;
import ru.itis.kpfu.querydsl.repositories.interfaces.ShopRepository;
import ru.itis.kpfu.querydsl.repositories.interfaces.UserRepository;

import javax.annotation.PostConstruct;
import javax.persistence.PostLoad;
import java.util.Arrays;
import java.util.Collections;

@Configuration
@RequiredArgsConstructor
public class RootConfig {

    private final GameRepository gameRepository;
    private final UserRepository userRepository;
    private final ShopRepository shopRepository;

    @PostConstruct
    public void loadEntities(){

        var game1 = Game.builder()
                .averageRating(9.5)
                .gameState(GameState.STORED)
                .name("Sekiro: Shadows Die Twice")
                .category(Category.RPG)
                .developer("FromSoftware")
                .build();

        var game2 = Game.builder()
                .averageRating(7.6)
                .gameState(GameState.STORED)
                .name("Destiny 2")
                .category(Category.ADVENTURES)
                .developer("Bungie")
                .build();

        var game3 = Game.builder()
                .averageRating(9.5)
                .gameState(GameState.PREORDER)
                .name("Sea of Thieves")
                .category(Category.ACTION)
                .developer("Microsoft")
                .build();

        var game4 = Game.builder()
                .averageRating(9.6)
                .gameState(GameState.STORED)
                .name("Forza Horizon 4")
                .category(Category.RACING)
                .developer("MICROSOFT")
                .build();

        var game5 = Game.builder()
                .averageRating(9.7)
                .gameState(GameState.CANCELED)
                .name("Red dead redemption 2")
                .category(Category.RPG)
                .developer("RockstarGames")
                .build();

        var game6 = Game.builder()
                .averageRating(9.3)
                .gameState(GameState.STORED)
                .name("GTAV")
                .category(Category.ACTION)
                .developer("RockstarGames")
                .build();

        var game7 = Game.builder()
                .averageRating(9.8)
                .gameState(GameState.STORED)
                .name("DOOM Eternal")
                .category(Category.ACTION)
                .developer("id Software")
                .build();

        var game8 = Game.builder()
                .averageRating(9.5)
                .gameState(GameState.STORED)
                .name("Sekiro: Shadows Die Twice")
                .category(Category.RPG)
                .developer("FromSoftware")
                .build();

        gameRepository.save(game1);
        gameRepository.save(game2);
        gameRepository.save(game3);
        gameRepository.save(game4);
        gameRepository.save(game5);
        gameRepository.save(game6);
        gameRepository.save(game7);
        gameRepository.save(game8);

        var user1 = User.builder()
                .nickname("UristMcEmhead")
                .login("Bentos")
                .games(Arrays.asList(game1,game2,game3,game4,game5, game6))
                .build();

        var user2 = User.builder()
                .nickname("A_Platypus_F")
                .login("linar240001")
                .games(Arrays.asList(game8,game7,game6,game4,game5))
                .build();

        var user3 = User.builder()
                .nickname("MomLoverBoy")
                .login("UristMcUser")
                .games(Arrays.asList(game3,game8))
                .build();

        var user4 = User.builder()
                .nickname("DummyOne")
                .login("UristMcPlayer")
                .games(Collections.singletonList(game6))
                .build();

        var user5 = User.builder()
                .nickname("MinecraftGOAT")
                .login("UristMcMiner")
                .games(Arrays.asList(game3,game4,game5))
                .build();

        var user6 = User.builder()
                .nickname("PHPisTheBest!!!")
                .login("UristMcCoder")
                .games(Arrays.asList(game1,game2,game3,game4,game5, game6, game7))
                .build();

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        userRepository.save(user5);
        userRepository.save(user6);

        var shop1 = Shop.builder()
                .name("Steam")
                .address("Bellevue")
                .users(userRepository.findAll())
                .games(Arrays.asList(game1,game2,game3,game4,game5, game6, game7))
                .build();

        //Arrays.asList(user1,user2,user4,user5,user6)

        var shop2 = Shop.builder()
                .name("GOG")
                .address("Warszawa")
                .users(Arrays.asList(user2,user4,user5,user6, user3))
                .games(Arrays.asList(game1,game2,game3,game4,game5, game6, game7))
                .build();

        var shop3 = Shop.builder()
                .name("EGS")
                .address("Bellevue")
                .users(Arrays.asList(user2,user4,user5))
                .games(Arrays.asList(game3,game4,game5, game6, game7))
                .build();

        shopRepository.save(shop1);
        shopRepository.save(shop2);
        shopRepository.save(shop3);
    }

}
