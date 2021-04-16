import com.spiral.Spiral;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class SpiralTest {

    @Test
    void test_print_counterSpiral() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Spiral m = new Spiral(4, 7);
        m.print_counterSpiral();

        assertEquals("1 8 15 22 23 24 25 26 27 28 21 14 7 6 5 4 3 2 9 16 17 18 19 20 13 12 11 10 ", outContent.toString());
    }
}