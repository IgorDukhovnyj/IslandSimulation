package com.ua.rush.khorinis.domain;

import java.util.Optional;
import java.util.Random;

public class Duck extends Herbivore { //Качка
    public Duck (Cell cell, int speed, double weight, double foodNeeded) {
        this.cell = cell;
        this.speed = speed;
        this.weight = weight;
        this.foodNeeded = foodNeeded;
        this.saturation = foodNeeded; //початкова насиченість, для тесту писали this.saturation = foodNeeded * 0.99;
    }

    @Override
    public void move(Island island) {
        if (!isAlive) { //перевірка для методу die класу Animal - тут тварини помирають якщо насиченість падає і немає рослин
            cell.getAnimals().remove(this);
            return;
        }
        var random = new Random();
        var step = random.nextInt(speed + 1);
        var direction = random.nextInt(4);

        switch (direction) {
            case 0 -> {
                if (cell.getY() > step) {
                    cell.getAnimals().remove(this);
                    cell = island.getCells()[cell.getX()][cell.getY() - step];
                    cell.getAnimals().add(this);
                }
            }
            case 1 -> {
                if (cell.getY() < island.getCells().length - step - 2) {
                    cell.getAnimals().remove(this);
                    cell = island.getCells()[cell.getX()][cell.getY() + step];
                    cell.getAnimals().add(this);
                }
            }
            case 2 -> {
                if (cell.getX() > step) {
                    cell.getAnimals().remove(this);
                    cell = island.getCells()[cell.getX() - step][cell.getY()];
                    cell.getAnimals().add(this);
                }
            }
            case 3 -> {
//                System.out.println(island.getCells()[0].length); //візуалізація координат для налаштування пересування
                if (cell.getX() < island.getCells()[0].length - step - 2) {
                    cell.getAnimals().remove(this);
                    cell = island.getCells()[cell.getX() + step][cell.getY()];
                    cell.getAnimals().add(this);
                }
            }
        }
        saturation -= foodNeeded * 0.1; //тварини помирають
        if (saturation <= 0) {
            isAlive = false;
            cell.getAnimals().remove(this);
        }
    }

    @Override
    public Optional<Animal> reproduce() {
        if (!isAlive) { // перевірка чи жива тварина
            return Optional.empty();
        }
        if (saturation < foodNeeded * 0.8) { // перевірка чи достатньо тварина насичена для розмноження
            return Optional.empty();
        }
        boolean hasPartner = cell.getAnimals().stream() // перевірка чи є пара для розмноження в клітинці
                .anyMatch(a -> a != this && a.getClass() == this.getClass());
        if (!hasPartner) {
            return Optional.empty();
        }
        if (Math.random() > 0.16) { // ймовірність розмноження 16% (коли є 2 тварини в клітинці - настає три варіанти:
            // самка-самка, самець-самець, самка-самець тобто маємо 33% вірогідності що буде пара для розмноження,
            // і ділимо на 2 - вдалося зробити потомство чи ні, тобто ймовірність приблизно 16%
            return Optional.empty();
        }
        Animal child = new Sheep(cell, speed, weight, foodNeeded); // створення нової тварини (потомства)
        //saturation *= 0.5; // можна зменшувати насиченість що втрачається під час розмноження
        return Optional.of(child);
    }
}
