package com.andrewpatterson.ase.tcp;

import com.andrewpatterson.ase.tcp.representation.TestCase;
import com.andrewpatterson.ase.tcp.representation.TestCaseOrderChromosome;
import org.junit.Assert;

import java.util.ArrayList;


public class TestCaseOrderChromosomeTest {

    public TestCaseOrderChromosomeTest(){

    }
    @org.junit.Test
    public void fitness() throws Exception {

        //1. create some TestCases
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


        //now create a chromosome
        TestCaseOrderChromosome testCaseOrderChromosome = new TestCaseOrderChromosome(testcases);

        //test chromosome order A–B–C–D–E–F–G–H–I–J
        Assert.assertEquals(0.4375, testCaseOrderChromosome.fitness(), 0.0001);


        testcases = new ArrayList<TestCase>();





        testcases.add(new TestCase("I",new int[]{1,1,1,1,1,0,0,0}));
        testcases.add(new TestCase("J",new int[]{0,0,0,0,1,1,1,0}));
        testcases.add(new TestCase("E",new int[]{1,0,0,0,0,0,0,1}));
        testcases.add(new TestCase("B",new int[]{1,1,0,0,0,0,0,0}));
        testcases.add(new TestCase("C",new int[]{1,1,1,0,0,0,0,0}));
        testcases.add(new TestCase("D",new int[]{0,1,1,0,0,0,0,0}));
        testcases.add(new TestCase("F",new int[]{0,0,0,0,0,0,0,1}));
        testcases.add(new TestCase("G",new int[]{0,1,0,0,0,0,0,0}));
        testcases.add(new TestCase("H",new int[]{0,0,0,1,0,0,0,1}));
        testcases.add(new TestCase("A",new int[]{0,0,0,0,0,0,0,0}));

        //now create a chromosome
        testCaseOrderChromosome = new TestCaseOrderChromosome(testcases);

        //test chromosome order A–B–C–D–E–F–G–H–I–J
        Assert.assertEquals(0.90, testCaseOrderChromosome.fitness(), 0.0001);

    }
    @org.junit.Test
    public void fitnessOneShitTestSuite() throws Exception {
        //Test suite detects zero faults, therefore its APFD should be 0.
        //create a testchromosome which misses faults faults
        ArrayList<TestCase> testcases = new ArrayList<TestCase>();

        testcases.add(new TestCase("A",new int[]{0,0,0,0,0,0,0}));
        testcases.add(new TestCase("B",new int[]{0,0,0,0,0,0,0}));
        TestCaseOrderChromosome testCaseOrderChromosome = new TestCaseOrderChromosome(testcases);

        //test chromosome order A–B–C–D–E–F–G–H–I–J
        Assert.assertEquals(0.0, testCaseOrderChromosome.fitness(), 0.0001);
    }
    @org.junit.Test
    public void fitnessGodSuite() throws Exception {
        //Test suite Detects 100% of faults, but does so with one test
        ArrayList<TestCase> testcases = new ArrayList<TestCase>();

        testcases.add(new TestCase("A",new int[]{1,1,1,1,1,1,1}));
        TestCaseOrderChromosome testCaseOrderChromosome = new TestCaseOrderChromosome(testcases);

        //test chromosome order A–B–C–D–E–F–G–H–I–J
        Assert.assertEquals(0.5, testCaseOrderChromosome.fitness(), 0.0001);
    }
    @org.junit.Test
    public void fitnessEvenTesting() throws Exception {
        //Test suite Detects 50% of faults, over two test tests
        ArrayList<TestCase> testcases = new ArrayList<TestCase>();

        testcases.add(new TestCase("A",new int[]{0,1,0,1,0,1,0,1}));
        testcases.add(new TestCase("B",new int[]{1,0,1,0,1,0,1,0}));
        TestCaseOrderChromosome testCaseOrderChromosome = new TestCaseOrderChromosome(testcases);

        //test chromosome order A–B–C–D–E–F–G–H–I–J
        Assert.assertEquals(0.5, testCaseOrderChromosome.fitness(), 0.0001);
    }
    @org.junit.Test
    public void equals() throws Exception {

        //1. create some TestCases
        ArrayList<TestCase> testcases = new ArrayList<TestCase>();

        testcases.add(new TestCase("A",new int[]{0,0,0,0,0,0,0,0}));
        testcases.add(new TestCase("B",new int[]{1,1,0,0,0,0,0,0}));
        testcases.add(new TestCase("C",new int[]{1,1,1,0,0,0,0,0}));
        testcases.add(new TestCase("D",new int[]{0,1,1,0,0,0,0,0}));
        //now create a chromosome
        TestCaseOrderChromosome testCaseOrderChromosome1 = new TestCaseOrderChromosome(testcases);

        //now create another one of the same
        Assert.assertTrue("Chromosomes are the same and equal", testCaseOrderChromosome1.equals(new TestCaseOrderChromosome(testcases)));

        //test chromosome order A–B–C–D–E–F–G–H–I–J

    }
}