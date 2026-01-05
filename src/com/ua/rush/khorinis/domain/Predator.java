package com.ua.rush.khorinis.domain;

import com.ua.rush.khorinis.config.ProbabilityConfig;

import java.util.Random;

public abstract class Predator extends Animal { //клас Хижаки успадкований від загального абстрактного Animal

    @Override
    public void eat() {
        if (!isAlive) return;
        double neededFood = foodNeeded - saturation;
        if (neededFood <= 0) return;
        var random = new Random();
        var animalsForEat = cell.getAnimals().stream() // витягнули кого можна їсти бо вони Травоїдні, поки що без виключень
                .filter(a -> a != this)
                .filter(a -> a instanceof Herbivore)
                .toList();
        for (Animal animalForEat : animalsForEat) {
            if (neededFood <= 0) break;
            if (random.nextDouble() > ProbabilityConfig.PREDATOR_EAT_PROBABILITY) { //вірогідність з"їсти Тварину у хижака
                continue;
            }
            animalForEat.die(); //з"їли Тварину
            saturation += animalForEat.getWeight(); //хижак отримує збільшення насиченості на вагу тварини яку з"їли
            neededFood = foodNeeded - saturation;
        }
    }
}
