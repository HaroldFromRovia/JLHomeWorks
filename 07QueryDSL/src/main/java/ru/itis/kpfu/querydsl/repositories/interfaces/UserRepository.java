package ru.itis.kpfu.querydsl.repositories.interfaces;

import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import ru.itis.kpfu.querydsl.models.QUser;
import ru.itis.kpfu.querydsl.models.User;

public interface UserRepository extends JpaRepository<User, Long>, QuerydslPredicateExecutor<User>, QuerydslBinderCustomizer<QUser> {

    @Override
    default void customize(QuerydslBindings bindings, QUser qUser) {
        bindings.bind(qUser.shops.any().name).as("shops.name").first(
                StringExpression::containsIgnoreCase
        );
    }

}
