package by.laziuk.controllers;

import by.laziuk.cars.impl.Identifiable;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface VehicleType {
    String name() default "";
    Class<? extends Identifiable> clazz();
    String path() default "";
}
