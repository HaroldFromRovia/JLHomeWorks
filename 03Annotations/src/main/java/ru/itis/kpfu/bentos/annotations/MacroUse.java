package ru.itis.kpfu.bentos.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface MacroUse {

    Class<?> input() default Object.class;

    String macroName () default "";

}
