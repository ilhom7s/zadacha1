package org.example.Readers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class TxtReader implements Reader {
    private final File file;

    public TxtReader(String path) {
        this.file = new File(path);
    }

    @Override
    public List<String[]> read() {
        List<String[]> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) continue;
                result.add(line.split("\\|"));
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return result;
    }
}
