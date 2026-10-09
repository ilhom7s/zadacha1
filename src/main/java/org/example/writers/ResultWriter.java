package org.example.writers;

import java.math.BigDecimal;

import java.util.Map;

public interface ResultWriter {
    void write(Map<String, BigDecimal> totals);
}
