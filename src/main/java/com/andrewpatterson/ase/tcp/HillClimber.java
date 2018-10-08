package com.andrewpatterson.ase.tcp;


import com.andrewpatterson.ase.tcp.representation.TestCase;
import com.andrewpatterson.ase.tcp.representation.TestCaseOrderChromosome;
import org.apache.commons.math3.genetics.Chromosome;
import org.apache.commons.math3.genetics.GeneticAlgorithm;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

/*type randomly evolves Test case permutations*/
public class HillClimber implements Runnable {
    private static RandomDataGenerator rdg = new RandomDataGenerator(GeneticAlgorithm.getRandomGenerator());

    final List<TestCase> domain;
    final int length;

    public HillClimber(Collection<TestCase> domain, int length){
        this.domain = new ArrayList<TestCase>(domain);
        this.length = length;
    }
    public void run() {
        //create a random permutation and evaluate its fitness
        TestCaseOrderChromosome testCaseOrderChromosome = createRandom();


        //now evaluate all the neighbours
        TestCaseOrderChromosome current = testCaseOrderChromosome;
        while(true){
            List<TestCaseOrderChromosome> neighbours = new ArrayList<TestCaseOrderChromosome>(getNeighbours(testCaseOrderChromosome));

            boolean hasChanged = false;
            //now select the fittest individual to continue
            for(TestCaseOrderChromosome tc: neighbours){
                if (tc.fitness() > current.fitness()) {
                    current = tc;
                    hasChanged = true;
                }
            }
            if(hasChanged == true){ //if the current chromosome didnt change then we have found optimum
                break;
            }
        }


        System.out.println(current.toString());


    }

    public TestCaseOrderChromosome createRandom(){

        List<Object> permutation = Arrays.asList(rdg.nextSample(domain, length));

        List<TestCase> rep =new ArrayList<TestCase>();
        for(Object o: permutation){
            rep.add((TestCase) o);
        }
        return new TestCaseOrderChromosome(rep);

    }
    /*Method returns all permutations of a chromosome where difference is 1(i.e. one step change)*/
    private Set<TestCaseOrderChromosome> getNeighbours(TestCaseOrderChromosome testCaseOrderChromosome){
        Set<TestCaseOrderChromosome> neighbours = new HashSet<TestCaseOrderChromosome>();
        for(int i=0; i<testCaseOrderChromosome.representation().size(); i++){
            neighbours.add(getBestAtElement(testCaseOrderChromosome, i));
        }
        return neighbours;
    }
    /* method takes in a chromosome and an element to permutate*/
    private TestCaseOrderChromosome getBestAtElement(TestCaseOrderChromosome testCaseOrderChromosome, int elementPosition){
        TestCaseOrderChromosome current = testCaseOrderChromosome;
        for(TestCase testCase: domain){
            if(testCaseOrderChromosome.representation().contains(testCase)) //cant have repeated tests in our permutation
                continue;

            //create a new chromosome with the current test case at that position
            List<TestCase> rep = new ArrayList<TestCase>(testCaseOrderChromosome.representation());
            rep.add(elementPosition, testCase);
            TestCaseOrderChromosome next = new TestCaseOrderChromosome(rep);

            if(testCaseOrderChromosome.fitness() > next.fitness()){
                current = next;
            }
        }
        return current;
    }


    public static void main(String[] args) throws IOException {
        List<TestCase> testCaseDomain = new ArrayList<TestCase>(Utils.loadTestCasesCSV("smallfaultmatrix.csv"));

        HillClimber hillClimber = new HillClimber(testCaseDomain, 5);

        hillClimber.run();
    }
}
