package Task_5_ExceptionHandling_RobustFileProcessing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ProductFilter {
    public static void main(String[] args) {
        String inputFile = "C:\\Java Internship Task Work\\Task_5_ExceptionHandling_RobustFileProcessing\\Products1.csv";
        String outputFile = "C:\\Java Internship Task Work\\Task_5_ExceptionHandling_RobustFileProcessing\\Expensive_Products1.csv";

        BufferedReader reader = null;
        FileWriter writer = null;
        try {
            reader = new BufferedReader(new FileReader(inputFile));
            writer = new FileWriter(outputFile);

            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    String[] parts = line.split(",");
                    if (parts.length != 2) {
                        throw new InvalidProductDataException("Invalid row format: " + line);
                    }
                    String name = parts[0].trim();
                    double price = Double.parseDouble(parts[1].trim());
                    if (price > 1000) {
                        writer.write(name + "," + price + "\n");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid price format in line: " + line);
                } catch (InvalidProductDataException e) {
                    System.out.println(e.getMessage());
                }
            }
            System.out.println("Filtered products written to " + outputFile);
        } catch (IOException e) {
            if (e instanceof java.io.FileNotFoundException) {
                System.err.println("File not found: " + inputFile);
            } else {
                System.err.println("IO Error processing files: " + e.getMessage());
            }
        } finally {
            try {
                if (reader != null) reader.close();
                if (writer != null) writer.close();
            } catch (IOException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }
    }
}
