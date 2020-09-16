package ru.itis.kpfu.bentos.rabbitmq.utils;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfoList;
import ru.itis.kpfu.bentos.rabbitmq.consumer.Consumer;
import ru.itis.kpfu.bentos.rabbitmq.consumer.DocumentConsumer;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DescendersUtil {

    public static List<?> findDescenders(String packages, Class<?> clazz) {

        var scan = new ClassGraph()
                .acceptPackages()
                .ignoreClassVisibility()
                .enableClassInfo()
                .enableAnnotationInfo()
                .scan();

        ClassInfoList components = scan.getClassesImplementing(clazz.getName());
        var componentsList = new ArrayList<>(components
                .loadClasses());
        var list = componentsList.stream().map(x -> {
            try {
                return x.getDeclaredConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                throw new IllegalArgumentException(e);
            }
        }).collect(Collectors.toList());

        return list;
    }
}
