package org.example.Readers;

import org.example.exceptions.FileExceptions;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

            reader.lines().forEach(line->{
                String[] arrayOfWords = line.split(symbol);
                result.add(arrayOfWords);
            });
//

        } catch (FileNotFoundException e) {
            throw  new FileExceptions(String.format("Файл не найден: %s",file.getAbsolutePath()),e);
        }

        catch (IOException e){
            throw new FileExceptions(String.format("Ошибка при чтении файла: %s",file.getAbsolutePath()),e);
        }

        return result;
    }
}
