package org.example.writers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class TxtResultWriter implements ResultWriter {
    private final String fileName;

    public TxtResultWriter(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void write(Map<String, Double> totals) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, StandardCharsets.UTF_8))) {
            for (String name : totals.keySet()) {
                writer.write(name + " - " + totals.get(name));
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}