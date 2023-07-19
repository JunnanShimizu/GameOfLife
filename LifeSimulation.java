/**
 * File: GameOfLife.java
 * Author: Junnan Shimizu
 * Date: 09/26/2021
 */

import java.util.Random;

public class LifeSimulation {
    // similar to the LandscapeDisplay main method, however calls the advance method, repaint method, and sleep method
    public static void main(String[] args) throws InterruptedException {
       Landscape scape = new Landscape(100,100);
        Random gen = new Random();
        double density = 0.3;

        // initialize the grid to be 30% full
        for (int i = 0; i < scape.getRows(); i++) {
            for (int j = 0; j < scape.getColumns(); j++ ) {
                scape.getCell( i, j ).setAlive( gen.nextDouble() <= density );
            }
        }

        LandscapeDisplay display = new LandscapeDisplay(scape, 8);

        for(int x = 0; x < 100; x++){
            scape.advance();
            display.repaint();
            Thread.sleep(250);
        }
    }
}
