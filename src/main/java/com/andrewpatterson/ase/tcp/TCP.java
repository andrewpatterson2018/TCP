package com.andrewpatterson.ase.tcp;

import org.apache.commons.math3.genetics.*;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TCP {
    public static Population getInitialPopulation(int size, int length) throws IOException {
        List<TestCase> testCaseDomain = new ArrayList<TestCase>(Utils.loadTestCasesCSV("smallfaultmatrix.csv"));

        TestCaseOrderChromosome.setTestUniverse(testCaseDomain); //set the domain of tests that the chromosome can draw from
        Population population = new ElitisticListPopulation(size, 0); // no elitism

        while(population.getPopulationLimit()!=population.getPopulationSize()){
            RandomDataGenerator rdg = new RandomDataGenerator(GeneticAlgorithm.getRandomGenerator());

            List<Object> permutation = Arrays.asList(rdg.nextSample(testCaseDomain, length));
            List<TestCase> rep =new ArrayList<TestCase>();
            for(Object o: permutation){
                rep.add((TestCase) o);
            }
            population.addChromosome(new TestCaseOrderChromosome(rep));

        }

        return population;


    }
    public static void main(String[] args) throws IOException {
        // initialize a new genetic algorithm
        GeneticAlgorithm ga = new GeneticAlgorithm(
                new TestCaseCrossoverPolicy(),
                1,
                new TCPMutationPolicy(),
                0.10,
                new TournamentSelection(3)
        );

        Population initial = getInitialPopulation(100, 5);

        // stopping condition
        StoppingCondition stopCond = new FixedGenerationCount(10000);

        // run the algorithm
        Population finalPopulation = ga.evolve(initial, stopCond);

        // best chromosome from the final population
        Chromosome bestFinal = finalPopulation.getFittestChromosome();

        System.out.println(bestFinal +" APFD: "+bestFinal.getFitness());
    }
}
