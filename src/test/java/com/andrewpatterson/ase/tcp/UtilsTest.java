package com.andrewpatterson.ase.tcp;

import org.junit.Test;

import java.util.Collection;


public class UtilsTest {
    @Test
    public void loadTestCasesCSV() throws Exception {
        Collection<TestCase> testCaseArrayList = Utils.loadTestCasesCSV("smallfaultmatrix.csv");

        /*manually verified to be correct*/
    }

}