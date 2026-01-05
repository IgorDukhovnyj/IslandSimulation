package com.ua.rush.khorinis;

import com.ua.rush.khorinis.config.ProbabilityConfig;
import com.ua.rush.khorinis.domain.Cell;
import com.ua.rush.khorinis.domain.Island;
import com.ua.rush.khorinis.service.SimulationService;

import java.util.Random;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Island island = new Island(8, 10);
        SimulationService simulationService = new SimulationService(island); //симуляція лічильника рослин на 20 тіків
        simulationService.simulate(20); //симуляція лічильника рослин на 20 тіків, після її завершення кількість рослин стає початковою в комірках і далі множиться в таблиці
//        printIsland(cells);
//        var cells = island.getCells();
//        for (int i = 0; i < 20; i++) {
//            Thread.sleep(1000);
//            growPlants(cells);
//            printIsland(cells);
//        }
    }

//    private static void growPlants(Cell[][] cells) {
//        var random = new Random();
//        for (Cell[] cell : cells) {
//            for (Cell value : cell) {
//                var newPlantPercentage = random.nextInt(100);
//                if (random.nextDouble() < ProbabilityConfig.PLANT_GROWTH_PROBABILITY) { //вірогідність того що виросте нова рослина 40%
//                    value.setPlantsCount(value.getPlantsCount() + 1);
//                }
//            }
//        }
//    }


//    private static void printIsland(Cell[][] cells) {
//        System.out.print("\n");
//        for (Cell[] cell : cells) {
//            for (Cell value : cell) {
////                System.out.print("|" + cells[i][j].getX() + " " + cells[i][j].getY() + "|\t"); //координати показуємо перелік комірок для візуалізації справності острова
//                var plantInfo = String.format("%3d", value.getPlantsCount());
//                System.out.print("|" + plantInfo + "|\t"); //показуємо кількість рослин в комірках
//            }
//            System.out.println();
//        }
//    }
}
