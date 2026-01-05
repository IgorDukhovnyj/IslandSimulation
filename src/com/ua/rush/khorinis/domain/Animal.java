package com.ua.rush.khorinis.domain;

import java.util.Optional;

public abstract class Animal { //загальний абстрактний клас для написання поведінки всіх тварин

    protected Cell cell; //координати тварини
    protected double weight; //вага тварини
    protected int speed; //швидкість переміщення тварини за 1 тік
    protected double foodNeeded; //потреба у їжі, показник повного насичення
    protected double saturation; //насиченість
    protected boolean isAlive = true;

    public abstract void eat();
    public abstract void move(Island island);
    public abstract Optional<Animal> reproduce();

    public boolean isAlive() {
        return isAlive;
    }
    public void die() {
        isAlive = false;
    }

    public double getWeight() {
        return weight;
    }

}
