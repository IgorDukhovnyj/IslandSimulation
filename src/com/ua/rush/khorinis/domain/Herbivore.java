package com.ua.rush.khorinis.domain;

import com.ua.rush.khorinis.config.ProbabilityConfig;

import java.util.Random;

public abstract class Herbivore extends Animal { //клас Травоїдні успадкований від загального абстрактного Animal

    @Override
    public void eat() {
        var random = new Random();
        var neededSaturation = this.foodNeeded - this.saturation;
//        System.out.println(neededSaturation); //можна відобразити на екрані потребу в їжі, буде 0 якщо тварина не рухається, при русі має мінятись
        while (neededSaturation > 0 && random.nextDouble() < ProbabilityConfig.HERBIVORE_EAT_PROBABILITY) {
            if (this.cell.getPlantsCount() > 0) {
                this.cell.dencrementPlantsCount();
                this.saturation++;
                neededSaturation--;
            } else {
                break;
            }
        }


    }

}
