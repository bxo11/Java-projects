import com.spot.Spot;
import org.junit.jupiter.api.Test;

import static java.time.Duration.ofMillis;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class SpotTest {

    @Test
    void test_spot() {
        Random generator = new Random();
        int[] tab_spot = new int[2];

        List<Integer> l = new ArrayList<>();
        for (int j : new int[]{0, -1, 3, 10, 4, 15}) {
            l.add(j);
        }
        Spot s = new Spot();
        tab_spot = s.spot(l, 60);
        assertEquals(60, tab_spot[0] * tab_spot[1]);


        List<Integer> l2 = new ArrayList<>();
        for (int j : new int[]{0, -1, 3, 10, 2, 15}) {
            l2.add(j);
        }

        assertThrows(RuntimeException.class, () -> s.spot(l2, 60));
    }
}