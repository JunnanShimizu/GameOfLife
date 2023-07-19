/**
 * File: GameOfLife.java
 * Author: Junnan Shimizu
 * Date: 09/26/2021
 */

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Landscape {
    private int rows;
    private int columns;
    private Cell[][] grid;
    public ArrayList<Cell> getNeighbors;

    //Creates a default 2d grid with the size 5 x 5
    public Landscape(){
        this.grid = new Cell[5][5];
        this.rows = 5;
        this.columns = 5;

        for(int x = 0; x < rows; x++){
            for(int y = 0; y < columns; y++){
                this.grid[x][y] = new Cell();
            }
        }
    }

    // constructor to create a landscape object with custom size of rows and columns
    public Landscape(int rows, int columns){
        this.grid = new Cell[rows][columns];
        this.rows = rows;
        this.columns = columns;

        for(int x = 0; x < rows; x++){
            for(int y = 0; y < columns; y++){
                this.grid[x][y] = new Cell();
            }
        }
    }

    // this will completely wipe the grid
    public void reset(){
        this.grid = new Cell[this.rows][this.columns];
    }

    // returns number of rows
    public int getRows(){
        return this.rows;
    }

    // returns number of columns
    public int getColumns(){
        return this.columns;
    }

    //returns cell at specific row and column index
    public Cell getCell(int row, int column){
        return this.grid[row][column];
    }

    // creates a graphic that will show the cells and their interactions
    public void draw(Graphics g, int gridScale){
        for(int x = 0; x < rows; x++){
            for(int y = 0; y < columns; y++){
                grid[x][y].draw(g, x * gridScale, y * gridScale, gridScale);
            }
        }
    }

    // returns a string that represents the grid
    public String toString(){
        String result = "";

        for(int x = 0; x < this.rows; x++){
            for(int y = 0; y < this.columns; y++){
                result += this.grid[x][y];
            }
            result += "\n";
        }

        return result;
    }

    // returns an arrayList of the 8 cells surrounding a specific cell
    public ArrayList<Cell> getNeighbors(int row, int column){
        this.getNeighbors = new ArrayList<Cell>();

        for(int x = row - 1; x < row + 2; x++){
            for(int y = column - 1; y < column + 2; y++){
                if(x > -1 && x < this.rows && y > -1 && y < this.columns){
                    if(grid[x][y] == grid[row][column]){
                        continue;
                    }else{
                        this.getNeighbors.add(this.getCell(x, y));
                    }
                }
            }
        }

        return this.getNeighbors;
    }

    // advances all the cells of grid at once
    public void advance(){
        Cell[][] temp = new Cell[rows][columns];
        for(int x = 0; x < rows; x++){
            for(int y = 0; y < columns; y++){
                temp[x][y] = new Cell(this.grid[x][y].getAlive());
            }
        }

        for(int x = 0; x < rows; x++){
            for(int y = 0; y < columns; y++){
                temp[x][y].updateState(this.getNeighbors(x,y));
            }
        }

        this.grid = temp;
    }

    // similar to advance() however the temp grid is set to the reference of the cell object in the same position of the original grid
    public void virusAdvance(){
        Cell[][] temp = new Cell[rows][columns];
        for(int x = 0; x < rows; x++){
            for(int y = 0; y < columns; y++){
                temp[x][y] = this.grid[x][y];
            }
        }

        for(int x = 0; x < rows; x++){
            for(int y = 0; y < columns; y++){
                temp[x][y].updateState(this.getNeighbors(x,y));
            }
        }

        this.grid = temp;
    }

    // main method to test all the fields, constructors, and methods
    public static void main(String[] args){
        Landscape landscape1 = new Landscape(10, 10);
        System.out.println("Rows: " + landscape1.getRows());
        System.out.println("Columns: " + landscape1.getColumns());
        System.out.println("Cell at 1, 1: " + landscape1.getCell(1,1));
        System.out.println(landscape1.toString());

        Random gen = new Random();
        double density = .3;

        for (int i = 0; i < landscape1.getRows(); i++) {
            for (int j = 0; j < landscape1.getColumns(); j++ ) {
                landscape1.getCell( i, j ).setAlive( gen.nextDouble() <= density );
            }
        }

        ArrayList<Cell> test = landscape1.getNeighbors(1,1);

        for(Cell current : test){
            System.out.print(current);
        }

        System.out.println(landscape1.toString());
        landscape1.advance();
        System.out.println(landscape1.toString());
        landscape1.advance();
        System.out.println(landscape1.toString());

    }
}
