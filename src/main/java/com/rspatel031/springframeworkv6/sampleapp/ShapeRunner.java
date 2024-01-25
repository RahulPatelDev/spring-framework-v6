package com.rspatel031.springframeworkv6.sampleapp;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ShapeRunner {

    private final Shape shape;

    public ShapeRunner(@Qualifier("Rectangle") Shape shape) {
        this.shape = shape;
    }

    public void run() {
        System.out.println("Running Shape: " + shape);
        shape.findArea();
    }


    @PostConstruct
    public void postConstruct() {
        System.out.println("******************* Starting Calculation *******************");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("******************* Calculation Completed ******************");
    }


}
