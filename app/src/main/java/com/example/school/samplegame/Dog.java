package com.example.school.samplegame;

public class Dog extends Animal {

    {
        sound = "bow";
    }

    void walk() {
        velocity += 5;
        weight -= 1;
        sound = "re";
    }
}
