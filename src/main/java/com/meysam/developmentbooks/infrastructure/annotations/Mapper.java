package com.meysam.developmentbooks.infrastructure.annotations;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface Mapper {


    @AliasFor(annotation = Component.class)
    String value() default "";
}
