package com.rspatel031.springframeworkv6.sampleapp;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("Rectangle")
public class Rectangle implements Shape {
    @Override
    public void findArea() {
        System.out.println("Calculating Area of Rectangle!!");
    }
}
