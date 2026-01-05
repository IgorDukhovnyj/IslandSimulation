package com.ua.rush.khorinis.domain;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Cell {
    private int x;
    private int y;
    private int plantsCount;
    private CopyOnWriteArrayList<Animal> animals;

    public Cell() {
    }

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public CopyOnWriteArrayList<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(CopyOnWriteArrayList<Animal> animals) {
        this.animals = animals;
    }

    public synchronized int getPlantsCount() {
        return plantsCount;
    }

    public synchronized void setPlantsCount(int plantsCount) {
        this.plantsCount = plantsCount;
    }

    public void incrementPlantsCount() {
        this.plantsCount++;
    }

    public void dencrementPlantsCount() {
        this.plantsCount--;
    }
}
