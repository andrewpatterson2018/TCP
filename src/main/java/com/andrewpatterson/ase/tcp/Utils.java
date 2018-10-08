package com.andrewpatterson.ase.tcp;

import com.andrewpatterson.ase.tcp.representation.TestCase;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Utils {
    public static Collection<TestCase> loadTestCasesCSV(String path) throws IOException {
        Set<TestCase> testCaseSet = new HashSet<TestCase>();

        //load in set of testcases
        Reader in = new FileReader(path);
        Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
        for (CSVRecord record : records) {

            String testID = record.get(0); //TEST ID

            //read the rest of the row and fill into an array
            int[] matrix = new int[record.size()-1];
            for(int i=1; i<record.size(); i++){
                matrix[i-1] = Integer.parseInt(record.get(i));
            }
            testCaseSet.add(new TestCase(testID, matrix));
        }
        return testCaseSet;
    }

}
