package com.rspatel031.springframeworkv6.sampleapp;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class Circle implements Shape {
    @Override
    public void findArea() {
        System.out.println("Calculating Area of Circle!!");
    }
}
