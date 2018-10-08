package com.andrewpatterson.ase.tcp.representation;

import org.apache.commons.math3.genetics.AbstractListChromosome;
import org.apache.commons.math3.genetics.InvalidRepresentationException;

import java.util.List;

/*Chromosome implementation to represent an ordering of test cases*/

public class TestCaseOrderChromosome extends AbstractListChromosome<TestCase>{


    private static List<TestCase> testUniverse;

    public static void setTestUniverse(List<TestCase> testUniverse){
        TestCaseOrderChromosome.testUniverse = testUniverse;
    }
    public static List<TestCase> getTestUniverse(){
        return testUniverse;
    }

    public TestCaseOrderChromosome(List<TestCase> representation) throws InvalidRepresentationException {
        super(representation);
    }

    public TestCaseOrderChromosome(TestCase[] representation) throws InvalidRepresentationException {
        super(representation);
    }
    protected void checkValidity(List<TestCase> list) throws InvalidRepresentationException {

    }

    public List<TestCase> representation(){
        return this.getRepresentation();
    }

    public TestCaseOrderChromosome newFixedLengthChromosome(List<TestCase> list) {
        return null;
    }

    public double fitness() {
        //get the faultmatrix from one of the test cases
        int faultDimensions = getRepresentation().get(0).getFaultsMatrix().length;
        float faultPositionSum = 0;

        for(int i=0; i<faultDimensions; i++){
            faultPositionSum = faultPositionSum+doThis(i, getLength());
        }


        if(faultPositionSum == 0){
            return 0;
        }
        float x = (faultPositionSum / (getLength()*faultDimensions));
        float y = 1.0F/(getLength()*2);
        float xy = x+y;
        return 1-x+y;
    }
    private float doThis(int i, int length){
        int faultPositionSum = 0;
        for(int j=0; j<getLength(); j++){
            if(getRepresentation().get(j).hasDetectedFault(i)){
                faultPositionSum += j+1;

                break;
            }
        }
        return faultPositionSum;
    }
    @Override
    public String toString() {
        StringBuilder tests= new StringBuilder();

        for(TestCase test: getRepresentation()){
            tests.append(" ").append(test.getTestID());
        }
        return tests.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestCaseOrderChromosome testCaseOrderChromosome = (TestCaseOrderChromosome) o;

        for(int i=0;i<getRepresentation().size(); i++){
            if(!testCaseOrderChromosome.representation().get(i).equals((TestCase)getRepresentation().get(i)))
                return false;
        }

        return true;
    }
}
