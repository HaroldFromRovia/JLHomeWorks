package ru.itis.kpfu.querydsl.repositories.implementations;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.itis.kpfu.querydsl.dto.UserDto;
import ru.itis.kpfu.querydsl.dto.UserRequest;
import ru.itis.kpfu.querydsl.models.User;
import ru.itis.kpfu.querydsl.repositories.interfaces.UserByRequestRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

import static ru.itis.kpfu.querydsl.models.QUser.user;

@Repository
@RequiredArgsConstructor
public class UserByRequestRepositoryImpl implements UserByRequestRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<UserDto> findByRequest(UserRequest request) {
        BooleanBuilder predicate = new BooleanBuilder();

        if (request.getLogin() != null) {
            predicate.or(user.login.containsIgnoreCase(request.getLogin()));
        }
        if (request.getNickname() != null) {
            predicate.or(user.nickname.containsIgnoreCase(request.getNickname()));
        }
        if (request.getShopName() != null) {
            predicate.or(user.shops.any().name.containsIgnoreCase(request.getShopName()));
        }

        return new JPAQuery<User>(entityManager)
                .select(user.login, user.nickname)
                .from(user)
                .where(predicate)
                .fetch().stream()
                .map(row -> UserDto.builder()
                        .login(row.get(user.login))
                        .nickname(row.get(user.nickname))
                        .build())
                .collect(Collectors.toList());
    }


}
