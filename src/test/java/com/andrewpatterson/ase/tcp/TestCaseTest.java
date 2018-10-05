package com.andrewpatterson.ase.tcp;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestCaseTest {

    @Test
    public void getFaultsMatrix() {
    }

    @Test
    public void hasDetectedFault() {
        TestCase testcase1 = new TestCase("B",new int[]{1,1,0,0,0,0,0,0});

        Assert.assertTrue("Different tests are not equal", testcase1.hasDetectedFault(1));
    }

    @Test
    public void equals() {
        //create two test cases
        TestCase testcase1 = new TestCase("A",new int[]{0,0,0,0,0,0,0,0});
        TestCase testcase2 = new TestCase("B",new int[]{1,1,0,0,0,0,0,0});

        Assert.assertTrue("Different tests are not equal", !testcase1.equals(testcase2));

        //same case
        TestCase testCase3 = new TestCase("A",new int[]{0,0,0,0,0,0,0,0});

        Assert.assertTrue("same objects are equal", testcase1.equals(testCase3));

    }
}