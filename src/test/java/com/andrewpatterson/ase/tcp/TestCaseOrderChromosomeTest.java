package com.andrewpatterson.ase.tcp;

import org.junit.Assert;

import java.util.ArrayList;


public class TestCaseOrderChromosomeTest {

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
        Assert.assertEquals(0.4375, testCaseOrderChromosome.fitness(), 0.001);
    }

}