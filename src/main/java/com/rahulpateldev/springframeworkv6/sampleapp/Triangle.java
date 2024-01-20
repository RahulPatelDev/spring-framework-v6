package com.rahulpateldev.springframeworkv6.sampleapp;

import org.springframework.stereotype.Component;

@Component
public class Triangle implements Shape {
    @Override
    public void findArea() {
        System.out.println("Calculating Area of Triangle!!");
    }
}
