package com.ua.rush.khorinis.domain;

public class Island {

    private Cell[][] cells;

    public Island(int width, int height) {
        this.cells = new Cell[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                cells[i][j] = new Cell(i, j);
            }

        }
    }

    public Cell[][] getCells() {
        return cells;
    }
}
