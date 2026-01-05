package com.ua.rush.khorinis.config;

public interface ProbabilityConfig {
    double PLANT_GROWTH_PROBABILITY = 1; //вірогідність того що виросте нова рослина,
                                        // в ТЗ цього немає, робимо для механізму смерті тварин від голоду,
                                        // якщо поставити 0 то рослин взагалі немає
    double HERBIVORE_EAT_PROBABILITY = 1; //вірогідність з"їсти Рослину у травоїдних, згідно ТЗ це 100% у всіх

    double PREDATOR_EAT_PROBABILITY = 0.1; //вірогідність з"їсти Тварину у хижака
}
