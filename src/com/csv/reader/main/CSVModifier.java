package com.csv.reader.main;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.*;
import java.util.List;

public class CSVModifier {

    /**
     * Updates all cells in a given column from a csv file
     * @param csvFilename The name of the csv file
     * @param column The column where the values are to be edited
     * @param oldValue The value of the cells that need changing
     * @param newValue The new value
     */
    public void updateRowValues(String csvFilename, String column, String oldValue, String newValue) {
        File file = new File (csvFilename);
        try {
            List<String[]> records = getRecordsFromFile(file);
            int columnIndex = 0;
            columnIndex = findColumn(records, column);
            records = updateRecords(records, columnIndex, oldValue, newValue);
            saveUpdatedRecords(records, file);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Fetches the records from a given csv file intoa list
     * @param file The csv file
     * @return List of string arrays representing the csv file records
     * @throws IOException Thrown if file doesn't exist
     */
    private List<String[]> getRecordsFromFile(File file) throws IOException {
        CSVReader csvReader = new CSVReader(new FileReader(file));
        return csvReader.readAll();
    }

    /**
     * Finds the position of the given column to edit
     * @param records Collection of all records from csv
     * @param columnHeader The title of the column to search for
     * @return The index position of the column if exists
     * @throws Exception Thrown if the given column doesn't exist in the csv file
     */
    private int findColumn(List<String[]> records, String columnHeader) throws Exception {
        Integer columnIndex = null;
        int i = 0;

        String[] column = records.get(0);
        while (columnIndex == null && i < column.length) {
            if (column.length >=1 && column[i].equals(columnHeader)) {
                columnIndex = i;
            }
            i++;
        }

        if (columnIndex == null) {
            throw new Exception("Specified column does not exist in this CSV!");
        } else {
            return columnIndex;
        }
    }

    /**
     * Updates any records that need updating
     * @param records Collection of all records from csv
     * @param columnIndex The column containing the values to be edited
     * @param oldValue The value that needs changing
     * @param newValue The new value
     * @return
     */
    private List<String[]> updateRecords(List<String[]> records, int columnIndex, String oldValue, String newValue) {
        int updateRecords = 0;

        for (int i = 0; i < records.size(); i++) {
            if (columnIndex < records.get(i).length && records.get(i)[columnIndex].equals(oldValue)) {
                records.get(i)[columnIndex] = newValue;
                updateRecords++;
            }
        }

        System.out.println(updateRecords + " record(s) were updated.");

        return records;
    }

    /**
     * Saves the given collection of records to the given file
     * @param records The records to be saved
     * @param file The file to save the records to
     * @throws IOException Thrown if file doesn't exist
     */
    private void saveUpdatedRecords(List<String[]> records, File file) throws IOException {
        CSVWriter csvWriter = new CSVWriter(new FileWriter(file));
        csvWriter.writeAll(records);
        csvWriter.close();
    }
}
