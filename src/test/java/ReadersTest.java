import org.example.Readers.OtherFormatReader;
import org.example.Readers.TxtReader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ReadersTest {

    @TempDir
    Path tempDir;

    @Test
    void txtReaderSplitsByPipeAndSkipsBlankLines() throws IOException {
        Path file = tempDir.resolve("orders.txt");
        Files.writeString(file, "2026-03-15T10:00:00|A|2000\n\n2026-03-15T11:00:00|B|1000\n");

        List<String[]> lines = new TxtReader(file.toString()).read();

        assertEquals(2, lines.size());
        assertArrayEquals(new String[]{"2026-03-15T10:00:00", "A", "2000"}, lines.get(0));
    }

    @Test
    void otherFormatReaderSplitsByHash() throws IOException {
        Path file = tempDir.resolve("orders");
        Files.writeString(file, "2026-03-15T10:00:00#Aston#500\n");

        List<String[]> lines = new OtherFormatReader(file.toString()).read();

        assertArrayEquals(new String[]{"2026-03-15T10:00:00", "SBER", "500"}, lines.get(0));
    }
}
