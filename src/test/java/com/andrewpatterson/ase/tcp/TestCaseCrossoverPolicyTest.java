package com.andrewpatterson.ase.tcp;

import org.apache.commons.math3.genetics.ChromosomePair;
import org.apache.commons.math3.genetics.GeneticAlgorithm;
import org.apache.commons.math3.random.RandomGenerator;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestCaseCrossoverPolicyTest {

    @Test
    public void crossover() {
        //inject the random da3ta generator to make test deterministic
        RandomGenerator randomGenerator = mock(RandomGenerator.class);
        when(randomGenerator.nextInt(Matchers.anyInt())).thenReturn(2); //half way crossover
        GeneticAlgorithm.setRandomGenerator(randomGenerator);


        TCPMutationPolicy tcpMutationPolicy = new TCPMutationPolicy();
        TestCaseCrossoverPolicy testCaseCrossoverPolicy = new TestCaseCrossoverPolicy();

        //now create a chromosome
        createChromosomeDomain();
        TestCaseOrderChromosome parent1 = createTestFirstParent();
        TestCaseOrderChromosome parent2 = createTestSecondParent();

        ChromosomePair chromosomePair = testCaseCrossoverPolicy.crossover(parent1, parent2);

        TestCaseOrderChromosome child1 = (TestCaseOrderChromosome)chromosomePair.getFirst();
        TestCaseOrderChromosome child2 = (TestCaseOrderChromosome)chromosomePair.getSecond();

        //examine children and check theyre correct
        Assert.assertEquals(createExpectedChromosome(), child1);


        //expectedRepresentation
    }
    private TestCaseOrderChromosome createTestFirstParent(){
        ArrayList<TestCase> testcases = new ArrayList<TestCase>();
        testcases.add(new TestCase("A",new int[]{0,0,0,0,0,0,0,0}));
        testcases.add(new TestCase("B",new int[]{1,1,0,0,0,0,0,0}));
        testcases.add(new TestCase("C",new int[]{1,1,1,0,0,0,0,0}));
        testcases.add(new TestCase("D",new int[]{0,1,1,0,0,0,0,0}));
        return new TestCaseOrderChromosome(testcases);

    }
    private TestCaseOrderChromosome createTestSecondParent(){
        ArrayList<TestCase> testcases = new ArrayList<TestCase>();
        testcases.add(new TestCase("E",new int[]{0,0,0,0,0,0,0,0}));
        testcases.add(new TestCase("F",new int[]{1,1,0,0,0,0,0,0}));
        testcases.add(new TestCase("G",new int[]{1,1,1,0,0,0,0,0}));
        testcases.add(new TestCase("H",new int[]{0,1,1,0,0,0,0,0}));
        return new TestCaseOrderChromosome(testcases);

    }
    private TestCaseOrderChromosome createExpectedChromosome(){
        ArrayList<TestCase> testcases = new ArrayList<TestCase>();
        testcases.add(new TestCase("A",new int[]{0,0,0,0,0,0,0,0}));
        testcases.add(new TestCase("B",new int[]{1,1,0,0,0,0,0,0}));
        testcases.add(new TestCase("E",new int[]{0,0,0,0,0,0,0,0}));
        testcases.add(new TestCase("F",new int[]{1,1,0,0,0,0,0,0}));
        return new TestCaseOrderChromosome(testcases);

    }
    private TestCaseOrderChromosome createExpectedChromosomeSecondChild(){
        ArrayList<TestCase> testcases = new ArrayList<TestCase>();
        testcases.add(new TestCase("E",new int[]{0,0,0,0,0,0,0,0}));
        testcases.add(new TestCase("F",new int[]{1,1,0,0,0,0,0,0}));
        testcases.add(new TestCase("C",new int[]{1,1,1,0,0,0,0,0}));
        testcases.add(new TestCase("D",new int[]{0,1,1,0,0,0,0,0}));
        return new TestCaseOrderChromosome(testcases);

    }
    private void createChromosomeDomain(){
        ArrayList<TestCase> testcases = new ArrayList<TestCase>();

        testcases.add(new TestCase("A",new int[]{0,0,0,0,0,0,0,0}));
        testcases.add(new TestCase("B",new int[]{1,1,0,0,0,0,0,0}));
        testcases.add(new TestCase("C",new int[]{1,1,1,0,0,0,0,0}));
        testcases.add(new TestCase("D",new int[]{0,1,1,0,0,0,0,0}));
        testcases.add(new TestCase("E",new int[]{1,0,0,0,0,0,0,1}));
        testcases.add(new TestCase("F",new int[]{0,0,0,0,0,0,0,1}));
        testcases.add(new TestCase("G",new int[]{0,1,0,0,0,0,0,0}));
        testcases.add(new TestCase("H",new int[]{0,0,0,1,0,0,0,1}));
        testcases.add(new TestCase("I",new int[]{1,1,1,1,1,0,0,0}));
        testcases.add(new TestCase("J",new int[]{0,0,0,0,1,1,1,0}));

        TestCaseOrderChromosome.setTestUniverse(testcases);
    }
}