package com.andrewpatterson.ase.tcp.representation;

import java.util.Arrays;

/*class representing faults detected, default cost of running test case is 1 i.e. uniform cost*/

public class TestCase{
    private final String testID;
    private final double cost;
    private final int[] faultsMatrix;

    public TestCase(String testID, int[] faultsMatrix){
        this.testID = testID;
        this.faultsMatrix = faultsMatrix;
        cost = 1.0;
    }
    public TestCase(String testID, int[] faultsMatrix, double cost){
        this.testID = testID;
        this.faultsMatrix = faultsMatrix;
        this.cost = cost;

    }

    public String getTestID() {
        return testID;
    }


    public double getCost() {
        return cost;
    }


    public int[] getFaultsMatrix() {
        return faultsMatrix;
    }
    public boolean hasDetectedFault(int i) throws IllegalArgumentException{
        //validate that i is between 0 and faultsMatrix.length
        if(i< 0 || i >=getFaultsMatrix().length)
            throw new IllegalArgumentException("ith element not present in fault matrix");
        return (getFaultsMatrix()[i] == 1);
    }

    @Override
    public String toString() {
        return testID + ": "+ Arrays.toString(faultsMatrix);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestCase testCase = (TestCase) o;

        if (Double.compare(testCase.cost, cost) != 0) return false;
        return testID.equals(testCase.testID) && Arrays.equals(faultsMatrix, testCase.faultsMatrix);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = testID.hashCode();
        temp = Double.doubleToLongBits(cost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + Arrays.hashCode(faultsMatrix);
        return result;
    }
}
