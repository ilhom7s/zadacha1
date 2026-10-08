package org.example.writers;

import org.example.exceptions.FileExceptions;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class TxtResultWriter implements ResultWriter {
    private final String fileName;
    private final String symbol;

    public TxtResultWriter(String fileName, String symbol) {
        this.fileName = fileName;
        this.symbol = symbol;
    }

    @Override
    public void write(Map<String, Double> totals) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, StandardCharsets.UTF_8))) {
            for (String name : totals.keySet()) {
                writer.write(name + symbol + totals.get(name));
                writer.newLine();
            }
        } catch (IOException e) {
            throw new FileExceptions("ошибка при записи в файл: "+fileName,e);
        }
    }
}