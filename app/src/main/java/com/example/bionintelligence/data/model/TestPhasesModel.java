package com.example.bionintelligence.data.model;

public class TestPhasesModel {
    private int productive;
    private double phaseOne;
    private double phaseTwo;
    private double phaseThree;
    private double phaseFour;
    private double phaseFive;
    private double phaseSix;

    public TestPhasesModel(int productive, double phaseOne, double phaseTwo, double phaseThree, double phaseFour,
                           double phaseFive, double phaseSix) {
        this.phaseOne = phaseOne;
        this.phaseTwo = phaseTwo;
        this.phaseThree = phaseThree;
        this.phaseFour = phaseFour;
        this.phaseFive = phaseFive;
        this.phaseSix = phaseSix;
        this.productive = productive;
    }

    public int getProductive() {
        return productive;
    }

    public void setProductive(int productive) {
        this.productive = productive;
    }

    public double getPhaseOne() {
        return phaseOne;
    }

    public void setPhaseOne(double phaseOne) {
        this.phaseOne = phaseOne;
    }

    public double getPhaseTwo() {
        return phaseTwo;
    }

    public void setPhaseTwo(double phaseTwo) {
        this.phaseTwo = phaseTwo;
    }

    public double getPhaseThree() {
        return phaseThree;
    }

    public void setPhaseThree(double phaseThree) {
        this.phaseThree = phaseThree;
    }

    public double getPhaseFour() {
        return phaseFour;
    }

    public void setPhaseFour(double phaseFour) {
        this.phaseFour = phaseFour;
    }

    public double getPhaseFive() {
        return phaseFive;
    }

    public void setPhaseFive(double phaseFive) {
        this.phaseFive = phaseFive;
    }

    public double getPhaseSix() {
        return phaseSix;
    }

    public void setPhaseSix(double phaseSix) {
        this.phaseSix = phaseSix;
    }
}
