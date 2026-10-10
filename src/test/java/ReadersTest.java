import org.example.Readers.FileReader;
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
    void otherFormatReaderSplitsByHash() throws IOException {
        Path file = tempDir.resolve("orders");
        Files.writeString(file, "2026-03-15T10:00:00#SBER#500\n");
        List<String[]> lines = new FileReader(file.toString(),"#").read();
        assertArrayEquals(new String[]{"2026-03-15T10:00:00", "SBER", "500"}, lines.get(0));
    }
}
