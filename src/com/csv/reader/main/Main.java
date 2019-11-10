package com.csv.reader.main;

public class Main {

    public static void main(String[] args) {
        String filename = args[0];
        String column = args[1];
        String oldValue = args[2];
        String newValue = args[3];

        System.out.println(filename + " " + column + " " + oldValue + " " + newValue);

        CSVModifier csvModifier = new CSVModifier();
        csvModifier.updateRowValues(filename, column, oldValue, newValue);
    }
}
