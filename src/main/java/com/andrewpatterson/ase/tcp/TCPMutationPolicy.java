package com.andrewpatterson.ase.tcp;

import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.genetics.Chromosome;
import org.apache.commons.math3.genetics.GeneticAlgorithm;
import org.apache.commons.math3.genetics.MutationPolicy;

import java.util.ArrayList;
import java.util.List;

public class TCPMutationPolicy implements MutationPolicy {
    public Chromosome mutate(Chromosome chromosome) throws MathIllegalArgumentException {

        //cast to correct type
        TestCaseOrderChromosome original = (TestCaseOrderChromosome) chromosome;

        //get the representation and make space for the new one
        List<TestCase> repr = original.representation();
        List<TestCase> newRepr = new ArrayList<TestCase>(repr);

        //creata a random point to mutate
        int indexOfTest = GeneticAlgorithm.getRandomGenerator().nextInt(repr.size());

        //put the set of test cases into a list so that we can iterate over them by index
        ArrayList<TestCase> testCases = new ArrayList<TestCase>(TestCaseOrderChromosome.getTestUniverse());

        TestCase testCase;
        while(newRepr.contains((testCase = testCases.get(GeneticAlgorithm.getRandomGenerator().nextInt(testCases.size())))));

        newRepr.set(indexOfTest, testCase);

        return new TestCaseOrderChromosome(newRepr);
    }
}
