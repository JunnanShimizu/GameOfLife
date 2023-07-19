/**
 * File: GameOfLife.java
 * Author: Junnan Shimizu
 * Date: 09/26/2021
 */

import java.awt.*;
import java.util.ArrayList;

public class Cell {
    private boolean alive;

    public Cell(){ // default == dead
        this.alive = false;
    }

    // can set the cell to dead or alive (true/false)
    public Cell(boolean alive){
        this.alive = alive;
    }

    // returns state of the cell
    public boolean getAlive(){
        return this.alive;
    }

    // sets the cell to false/dead
    public void reset(){ // alive set to false
        this.alive = false;
    }

    // sets the cell to custom input
    public void setAlive(boolean alive){
        this.alive = alive;
    }

    // sets te cell color to darkGrey if alive
    public void draw(Graphics g, int x, int y, int scale){
        g.drawOval(x, y, scale, scale);
        if(this.getAlive()){
            g.setColor(Color.darkGray);
            g.fillOval(x, y, 10, 10);
        }
    }

    // this will update the state of the cell depending on the number of neighbors and the state of the cell
    public void updateState(ArrayList<Cell> neighbors){
        int numAliveNeighbors = 0;
        for(Cell neighbor : neighbors){
            if(neighbor.getAlive()){
                numAliveNeighbors++;
            }
        }

        if(this.getAlive()) {
            if (numAliveNeighbors != 2 && numAliveNeighbors != 3) {
                this.alive = false;
            }
        }

        if(this.getAlive() == false && numAliveNeighbors == 3) {
            this.alive = true;
        }
    }

    // returns 0 if the cell is alive, " " if dead
    public String toString(){ //returns 0 if true, " " if false
        if(this.alive) {
            return "0";
        }
        else {
            return " ";
        }
    }

    // used to test all the fields, constructors, and methods
    public static void main(String[] args){
        Cell cell1 = new Cell();
        Cell cell2 = new Cell(true);

        System.out.println("Should return false: " + cell1.getAlive());
        System.out.println("Should return true: " + cell2.getAlive());
        cell1.setAlive(true);
        System.out.println("Should return true: " + cell1.getAlive());
        System.out.println(cell1.toString());
    }
}
