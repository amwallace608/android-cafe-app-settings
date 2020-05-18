package com.amwallace.droidcafe.Model;

import java.io.Serializable;

//class for accumulating deserts in an order
public class DessertOrder implements Serializable {
    private int numSandiwches;
    private int numFroyo;
    private int numDonuts;

//constructors
    public DessertOrder() {
        this.numSandiwches = 0;
        this.numDonuts = 0;
        this.numFroyo = 0;
    }

    public DessertOrder(int numSandiwches, int numFroyo, int numDonuts) {
        this.numSandiwches = numSandiwches;
        this.numFroyo = numFroyo;
        this.numDonuts = numDonuts;
    }
//getters and setters
    public int getNumSandiwches() {
        return numSandiwches;
    }

    public void setNumSandiwches(int numSandiwches) {
        this.numSandiwches = numSandiwches;
    }

    public int getNumFroyo() {
        return numFroyo;
    }

    public void setNumFroyo(int numFroyo) {
        this.numFroyo = numFroyo;
    }

    public int getNumDonuts() {
        return numDonuts;
    }

    public void setNumDonuts(int numDonuts) {
        this.numDonuts = numDonuts;
    }
}
