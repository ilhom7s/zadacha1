import org.example.writers.TxtResultWriter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TxtResultWriterTest {

    @TempDir
    Path tempDir;

    @Test
    void writesOneLinePerCompany() throws IOException {
        Path file = tempDir.resolve("result.txt");
        Map<String, BigDecimal> totals = new LinkedHashMap<>();
        totals.put("A", new BigDecimal("13000.0"));
        totals.put("B", new BigDecimal("5500.0"));

        new TxtResultWriter(file.toString(),"-").write(totals);

        assertEquals(List.of("A-13000.0", "B-5500.0"), Files.readAllLines(file));
    }
}
