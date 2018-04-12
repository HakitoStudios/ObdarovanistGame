package com.example.school.samplegame;

public class Main {

    public static void main(String[] args) {
        Cat tom = new Cat();

        String res = tom.voice(4);
        System.out.println(res);

        System.out.println(tom.velocity);
        tom.weight += 3;
        tom.walk();

        System.out.println(tom.velocity);

        Cat mike = new Cat();

        mike.walk();
        mike.walk();

        System.out.println(mike.velocity);

        Dog ball = new Dog();
        System.out.println(ball.voice(3));
    }
}
