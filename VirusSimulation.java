/**
 * File: GameOfLife.java
 * Author: Junnan Shimizu
 * Date: 09/26/2021
 */

import java.util.Random;

public class VirusSimulation {
    // similar to the main method in LifeSimulation, however calls the virusAdvance() method rather than the advance method
    public static void main(String[] args) throws InterruptedException {
        Landscape scape = new Landscape(100,100);
        Random gen = new Random();
        double density = 0.1;

        // initialize the grid to be 30% full
        for (int i = 0; i < scape.getRows(); i++) {
            for (int j = 0; j < scape.getColumns(); j++ ) {
                scape.getCell( i, j ).setAlive( gen.nextDouble() <= density );
            }
        }

        LandscapeDisplay display = new LandscapeDisplay(scape, 8);

        for(int x = 0; x < 100; x++){
            scape.virusAdvance();
            display.repaint();
            Thread.sleep(250);
        }
    }
}
