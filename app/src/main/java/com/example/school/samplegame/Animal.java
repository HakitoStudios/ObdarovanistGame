package com.example.school.samplegame;

public class Animal {

    float weight;

    String name;

    float velocity;

    String sound;

    void walk() {
        velocity += 3;
        weight -= 1;
    }

    String voice(int count) {
        String res = "";
        for (int i = 0; i < count; i++) {
            res += sound;
        }
        return res;
    }
}
