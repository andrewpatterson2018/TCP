package com.andrewpatterson.ase.tcp;

import org.apache.commons.math3.genetics.GeneticAlgorithm;
import org.apache.commons.math3.random.RandomGenerator;
import org.junit.Test;
import org.mockito.Matchers;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TCPMutationPolicyTest {
    @Test
    public void mutateNormal() throws Exception {

        //our test should change the second element(B) to
        //inject the random da3ta generator to make test deterministic
        RandomGenerator randomGenerator = mock(RandomGenerator.class);
        when(randomGenerator.nextInt(Matchers.anyInt())).thenReturn(2).thenReturn(5);
        GeneticAlgorithm.setRandomGenerator(randomGenerator);


        TCPMutationPolicy tcpMutationPolicy = new TCPMutationPolicy();

        //now create a chromosome
        createChromosomeDomain();
        TestCaseOrderChromosome testCaseOrderChromosome = createTestChromosome();


        TestCaseOrderChromosome actual = (TestCaseOrderChromosome)tcpMutationPolicy.mutate(testCaseOrderChromosome);

        //expectedRepresentation

    }
    @Test
    public void mutateTestCaseAlreadyPresent() throws Exception {

        //our test should change the second element(B) to
        //inject the random data generator to make test deterministic
        RandomGenerator randomGenerator = mock(RandomGenerator.class);
        when(randomGenerator.nextInt(Matchers.anyInt())).thenReturn(2).thenReturn(0).thenReturn(5);
        GeneticAlgorithm.setRandomGenerator(randomGenerator);


        TCPMutationPolicy tcpMutationPolicy = new TCPMutationPolicy();

        //now create a chromosome
        createChromosomeDomain();
        TestCaseOrderChromosome testCaseOrderChromosome = createTestChromosome();


        TestCaseOrderChromosome actual = (TestCaseOrderChromosome)tcpMutationPolicy.mutate(testCaseOrderChromosome);

        System.out.println(actual);
        //expectedRepresentation

    }
    private TestCaseOrderChromosome createTestChromosome(){
        ArrayList<TestCase> testcases = new ArrayList<TestCase>();
        testcases.add(new TestCase("A",new int[]{0,0,0,0,0,0,0,0}));
        testcases.add(new TestCase("B",new int[]{1,1,0,0,0,0,0,0}));
        testcases.add(new TestCase("C",new int[]{1,1,1,0,0,0,0,0}));
        testcases.add(new TestCase("D",new int[]{0,1,1,0,0,0,0,0}));
        return new TestCaseOrderChromosome(testcases);

    }
    private TestCaseOrderChromosome createExpectedChromosome(){
        ArrayList<TestCase> testcases = new ArrayList<TestCase>();
        testcases.add(new TestCase("A",new int[]{0,0,0,0,0,0,0,0}));
        testcases.add(new TestCase("F",new int[]{0,0,0,0,0,0,0,1}));
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