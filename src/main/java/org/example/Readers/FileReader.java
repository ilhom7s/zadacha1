package org.example.Readers;

import org.example.exceptions.FileExceptions;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FileReader implements Reader {
    private final File file;
private final String symbol;
    public FileReader(String path, String symbol) {
        this.file = new File(path);
        this.symbol = symbol;
    }

    @Override
    public List<String[]> read() {
        List<String[]> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) continue;
                result.add(line.split(symbol));
            }
        } catch (FileNotFoundException e) {
            throw  new FileExceptions("Файл не найден:"+file.getAbsolutePath(),e);
        }
        catch (IOException e){
            throw new FileExceptions("Ошибка при чтении файла:"+file.getAbsolutePath(),e);
        }
        return result;
    }
}
