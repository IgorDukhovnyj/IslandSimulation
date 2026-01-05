package com.ua.rush.khorinis.service;

import com.ua.rush.khorinis.config.ProbabilityConfig;
import com.ua.rush.khorinis.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimulationService {
    private Island island;

    public SimulationService(Island island) {
        this.island = island;
    }

    public void simulate(int ticks) {
        System.out.println("Симуляцію розпочато: ");
        animalsSetup();
        ExecutorService executorService = Executors.newFixedThreadPool(3); //багатопотоковість, зараз 3 потоки
        for (int i = 0; i < ticks; i++) {
            executorService.submit(this::growPlants); //1 потік  //поки закомментували щоб прибрати багатопотоковість
            executorService.submit(this::animalsEat); //2 потік  //поки закомментували щоб прибрати багатопотоковість + замінили animalsEat на animalsSimulation
            executorService.submit(this::animalsMove); //3 потік
            //growPlants(); //а тут додали замість того що закоментували багатопотоковість
            //animalsSimulation(); //а тут додали замість того що закоментували багатопотоковість + замінили animalsEat на animalsSimulation
            printStatistic();
            sleep1S(); //прибрали затримку 1сек в тіках для швидкого відображення результату в консолі
        }
        executorService.shutdownNow();
        System.out.println("__________________________________________");
        System.out.println("Симуляцію закінчено. Фінальна статистика: ");
        printStatistic();
        System.out.println("__________________________________________");
    }

    private void sleep1S() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void animalsSimulation() {
        animalsMove();
        animalsEat();
    }

    private void animalsMove() {
        for (Cell[] cell : island.getCells()) {
            for (Cell value : cell) {
                value.getAnimals().forEach(animal -> animal.move(island));
            }
        }
    }

    private void growPlants() { //ріст рослин
        var random = new Random();
        for (Cell[] cell : island.getCells()) {
            for (Cell value : cell) {
                var newPlantPercentage = random.nextInt(100);
                if (random.nextDouble() < ProbabilityConfig.PLANT_GROWTH_PROBABILITY) { //вірогідність того що виросте нова рослина вказуємо в конфігу відсоток
                    value.setPlantsCount(value.getPlantsCount() + 1);
                }
            }
        }
    }

    private void printStatistic() {
//        System.out.println("\rPlants count: " + getPlantsCount() + "  Animals count:" + getAnimalsCount()
//                + "  Cell[0][0] sheeps: " + island.getCells()[0][0].getAnimals().size());
        System.out.println("\tКількість рослин: " + getPlantsCount() + ";\tКількість тварин: " + getAnimalsCount() + ";");
        System.out.println("\tКількість тварин у локаціях, візуалізація переміщення між локаціями:"
                + "\n[0][0]: " + island.getCells()[0][0].getAnimals().size()
                + "  [0][1]: " + island.getCells()[0][1].getAnimals().size()
                + "  [0][2]: " + island.getCells()[0][2].getAnimals().size()
                + "  [0][3]: " + island.getCells()[0][3].getAnimals().size()
                + "  [0][4]: " + island.getCells()[0][4].getAnimals().size()
                + "  [0][5]: " + island.getCells()[0][5].getAnimals().size()
                + "  [0][6]: " + island.getCells()[0][6].getAnimals().size()
                + "  [0][7]: " + island.getCells()[0][7].getAnimals().size()
                + "  [0][8]: " + island.getCells()[0][8].getAnimals().size()
                + "  [0][9]: " + island.getCells()[0][9].getAnimals().size()
                + "\n[1][0]: " + island.getCells()[1][0].getAnimals().size()
                + "  [1][1]: " + island.getCells()[1][1].getAnimals().size()
                + "  [1][2]: " + island.getCells()[1][2].getAnimals().size()
                + "  [1][3]: " + island.getCells()[1][3].getAnimals().size()
                + "  [1][4]: " + island.getCells()[1][4].getAnimals().size()
                + "  [1][5]: " + island.getCells()[1][5].getAnimals().size()
                + "  [1][6]: " + island.getCells()[1][6].getAnimals().size()
                + "  [1][7]: " + island.getCells()[1][7].getAnimals().size()
                + "  [1][8]: " + island.getCells()[1][8].getAnimals().size()
                + "  [1][9]: " + island.getCells()[1][9].getAnimals().size()
                + "\n[2][0]: " + island.getCells()[2][0].getAnimals().size()
                + "  [2][1]: " + island.getCells()[2][1].getAnimals().size()
                + "  [2][2]: " + island.getCells()[2][2].getAnimals().size()
                + "  [2][3]: " + island.getCells()[2][3].getAnimals().size()
                + "  [2][4]: " + island.getCells()[2][4].getAnimals().size()
                + "  [2][5]: " + island.getCells()[2][5].getAnimals().size()
                + "  [2][6]: " + island.getCells()[2][6].getAnimals().size()
                + "  [2][7]: " + island.getCells()[2][7].getAnimals().size()
                + "  [2][8]: " + island.getCells()[2][8].getAnimals().size()
                + "  [2][9]: " + island.getCells()[2][9].getAnimals().size()
                + "\n[3][0]: " + island.getCells()[3][0].getAnimals().size()
                + "  [3][1]: " + island.getCells()[3][1].getAnimals().size()
                + "  [3][2]: " + island.getCells()[3][2].getAnimals().size()
                + "  [3][3]: " + island.getCells()[3][3].getAnimals().size()
                + "  [3][4]: " + island.getCells()[3][4].getAnimals().size()
                + "  [3][5]: " + island.getCells()[3][5].getAnimals().size()
                + "  [3][6]: " + island.getCells()[3][6].getAnimals().size()
                + "  [3][7]: " + island.getCells()[3][7].getAnimals().size()
                + "  [3][8]: " + island.getCells()[3][8].getAnimals().size()
                + "  [3][9]: " + island.getCells()[3][9].getAnimals().size()
                + "\n[4][0]: " + island.getCells()[4][0].getAnimals().size()
                + "  [4][1]: " + island.getCells()[4][1].getAnimals().size()
                + "  [4][2]: " + island.getCells()[4][2].getAnimals().size()
                + "  [4][3]: " + island.getCells()[4][3].getAnimals().size()
                + "  [4][4]: " + island.getCells()[4][4].getAnimals().size()
                + "  [4][5]: " + island.getCells()[4][5].getAnimals().size()
                + "  [4][6]: " + island.getCells()[4][6].getAnimals().size()
                + "  [4][7]: " + island.getCells()[4][7].getAnimals().size()
                + "  [4][8]: " + island.getCells()[4][8].getAnimals().size()
                + "  [4][9]: " + island.getCells()[4][9].getAnimals().size()
                + "\n[5][0]: " + island.getCells()[5][0].getAnimals().size()
                + "  [5][1]: " + island.getCells()[5][1].getAnimals().size()
                + "  [5][2]: " + island.getCells()[5][2].getAnimals().size()
                + "  [5][3]: " + island.getCells()[5][3].getAnimals().size()
                + "  [5][4]: " + island.getCells()[5][4].getAnimals().size()
                + "  [5][5]: " + island.getCells()[5][5].getAnimals().size()
                + "  [5][6]: " + island.getCells()[5][6].getAnimals().size()
                + "  [5][7]: " + island.getCells()[5][7].getAnimals().size()
                + "  [5][8]: " + island.getCells()[5][8].getAnimals().size()
                + "  [5][9]: " + island.getCells()[5][9].getAnimals().size()
                + "\n[6][0]: " + island.getCells()[6][0].getAnimals().size()
                + "  [6][1]: " + island.getCells()[6][1].getAnimals().size()
                + "  [6][2]: " + island.getCells()[6][2].getAnimals().size()
                + "  [6][3]: " + island.getCells()[6][3].getAnimals().size()
                + "  [6][4]: " + island.getCells()[6][4].getAnimals().size()
                + "  [6][5]: " + island.getCells()[6][5].getAnimals().size()
                + "  [6][6]: " + island.getCells()[6][6].getAnimals().size()
                + "  [6][7]: " + island.getCells()[6][7].getAnimals().size()
                + "  [6][8]: " + island.getCells()[6][8].getAnimals().size()
                + "  [6][9]: " + island.getCells()[6][9].getAnimals().size()
                + "\n[7][0]: " + island.getCells()[7][0].getAnimals().size()
                + "  [7][1]: " + island.getCells()[7][1].getAnimals().size()
                + "  [7][2]: " + island.getCells()[7][2].getAnimals().size()
                + "  [7][3]: " + island.getCells()[7][3].getAnimals().size()
                + "  [7][4]: " + island.getCells()[7][4].getAnimals().size()
                + "  [7][5]: " + island.getCells()[7][5].getAnimals().size()
                + "  [7][6]: " + island.getCells()[7][6].getAnimals().size()
                + "  [7][7]: " + island.getCells()[7][7].getAnimals().size()
                + "  [7][8]: " + island.getCells()[7][8].getAnimals().size()
                + "  [7][9]: " + island.getCells()[7][9].getAnimals().size());
    }

    private void animalsSetup() {
        var random = new Random();
        for (Cell[] cell : island.getCells()) {
            for (Cell value : cell) {

                CopyOnWriteArrayList<Animal> result = new CopyOnWriteArrayList<>(); //ліст тварин
                //System.out.println("Sheeps count: " + maxValue); //лічильник овець поки прибрали щоб не заважав
                var maxValue1 = random.nextInt(140); //максимальна кількість Овець і Кіз на 1 клітинці з таблиці "Характеристики тварин"
                for (int i = 0; i < maxValue1; i++) {
                    result.add(new Sheep(value,3, 70, 15)); //додаємо нову тварину, нова Вівця - швидкість, вага, скільки їжі для повного насичення
                    result.add(new Goat(value,3, 60, 10)); // нова Коза
                }
                var maxValue2 = random.nextInt(500); //максимальна кількість Мишей на 1 клітинці з таблиці "Характеристики тварин"
                for (int i = 0; i < maxValue2; i++) {
                    result.add(new Mouse(value,1, 0.05, 0.01)); // нова Миша
                }
                var maxValue3 = random.nextInt(50); //максимальна кількість Кабанів на 1 клітинці з таблиці "Характеристики тварин"
                for (int i = 0; i < maxValue3; i++) {
                    result.add(new Boar(value,2, 400, 50)); // новий Кабан
                }
                var maxValue4 = random.nextInt(10); //максимальна кількість Буйволів на 1 клітинці з таблиці "Характеристики тварин"
                for (int i = 0; i < maxValue4; i++) {
                    result.add(new Buffalo(value,3, 700, 100)); // новий Буйвол
                }
                var maxValue5 = random.nextInt(200); //максимальна кількість Качок на 1 клітинці з таблиці "Характеристики тварин"
                for (int i = 0; i < maxValue5; i++) {
                    result.add(new Duck(value,4, 1, 0.15)); // нова Качка
                }
                var maxValue6 = random.nextInt(1000); //максимальна кількість Гусені на 1 клітинці з таблиці "Характеристики тварин"
                for (int i = 0; i < maxValue6; i++) {
                    result.add(new Caterpillar(value,0, 0.01, 0)); // нова Гусінь
                }
                var maxValue7 = random.nextInt(150); //максимальна кількість Кроликів на 1 клітинці з таблиці "Характеристики тварин"
                for (int i = 0; i < maxValue7; i++) {
                    result.add(new Rabbit(value,2, 2, 0.45)); // новий Кролик
                }
                var maxValue8 = random.nextInt(20); //максимальна кількість Оленів, Коней, Орлів на 1 клітинці з таблиці "Характеристики тварин"
                for (int i = 0; i < maxValue8; i++) {
                    result.add(new Deer(value,4, 300, 50)); // новий Олень
                    result.add(new Horse(value,4, 400, 60)); // новий Кінь
                    result.add(new Eagle(value,3, 6, 1)); // новий Орел
                }
                var maxValue9 = random.nextInt(5); //максимальна кількість Ведмедів на 1 клітинці з таблиці "Характеристики тварин"
                for (int i = 0; i < maxValue9; i++) {
                    result.add(new Bear(value,2, 500, 80)); // новий Ведмідь
                }
                var maxValue10 = random.nextInt(30); //максимальна кількість Вовків, Удавів, Лисиць на 1 клітинці з таблиці "Характеристики тварин"
                for (int i = 0; i < maxValue10; i++) {
                    result.add(new Wolf(value,3, 50, 8)); // новий Вовк
                    result.add(new Boa(value,1, 15, 3)); // новий Удав
                    result.add(new Fox(value,2, 8, 2)); // новий Лисиця
                }
                value.setAnimals(result);
            }
        }
    }

    private int getPlantsCount() {
        int result = 0;
        for (Cell[] cell : island.getCells()) {
            for (Cell value : cell) {
                result += value.getPlantsCount();
            }
        }
        return result;
    }

    private int getAnimalsCount() {
        int result = 0;
        for (Cell[] cell : island.getCells()) {
            for (Cell value : cell) {
                result += value.getAnimals().size();
            }
        }
        return result;
    }

    private void animalsEat() {
        for (Cell[] cell : island.getCells()) {
            for (Cell value : cell) {
                value.getAnimals().forEach(Animal::eat);
            }
        }
    }


}
