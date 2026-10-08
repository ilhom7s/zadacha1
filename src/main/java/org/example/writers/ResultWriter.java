package org.example.writers;

import java.util.Map;

public interface ResultWriter {
    void write(Map<String, Double> totals);
}
