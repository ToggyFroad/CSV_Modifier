package com.csv.reader.test;

import com.csv.reader.main.CSVModifier;
import com.opencsv.CSVWriter;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CSVModifierTest {

    private static final String testCsvFilename = "test/testCSV.csv";
    private static final String expectedOutputFilename = "test/expectedOutput.csv";

    @BeforeAll
    public static void setupCSVModifierTest() {
        try {
            FileWriter outputFile = new FileWriter(new File(testCsvFilename));
            CSVWriter csvWriter = new CSVWriter(outputFile);

            String[] titles = { "filename", "origin", "metadata", "hash" };
            csvWriter.writeNext(titles);

            String[] record1 = { "file1", "London", "a file about London", "e737a6b0734308a08b8586720b3c299548ff77b846e3c9c89db88b63c7ea69b6"};
            csvWriter.writeNext(record1);
            String[] record2 = { "file2", "Surrey", "a file about The National Archives", "a4bf0d05d8805f8c35b633ee67dc10efd6efe1cb8dfc0ecdba1040b551564967" };
            csvWriter.writeNext(record2);
            String[] record55 = { "file55", "Londom", "London was initially incorrectly spelled as Londom", "e737a6b0734308a08b8586720b3c299548ff77b846e3c9c89db88b63c7ea69b6" };
            csvWriter.writeNext(record55);
            String[] record4 = { "file4", "Penrith", "Lake District National Park info", "a4bf0d05d8805f8c35b633ee67dc10efd6efe1cb8dfc0ecdba1040b551564968" };
            csvWriter.writeNext(record4);

            csvWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void csvModifierTest() {
        String column = "origin";
        String oldValue = "Londom";
        String newValue = "London";

        CSVModifier csvModifier = new CSVModifier();
        csvModifier.updateRowValues(testCsvFilename, column, oldValue, newValue);

        File test = new File(testCsvFilename);
        File expected = new File(expectedOutputFilename);

        try {
            Assertions.assertTrue(FileUtils.contentEqualsIgnoreEOL(test, expected, null), "File was not updated as expected!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
