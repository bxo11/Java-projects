import com.maxTriplet.Triplet;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.*;

class TripletTest {

    @Test
    void test_maxTriplet() {
        List list1 = new ArrayList();
        for (int j : new int[]{-5, -7, 10, 1}) {
            list1.add(j);
        }

        List list2 = new ArrayList();
        for (int j : new int[]{1, 2, 0, 1}) {
            list2.add(j);
        }

        List list3 = new ArrayList();
        for (int j : new int[]{-5, -7, -10, -1}) {
            list3.add(j);
        }

        Triplet t = new Triplet();
        assertEquals(6, t.maxTriplet(list1));
        assertEquals(4, t.maxTriplet(list2));
        assertTimeout(ofMillis(50), () -> t.maxTriplet(list3));
        //assertEquals(-13,t.maxTriplet(list3));

        List list4 = new ArrayList();
        for (int j : new int[]{-5, -7}) {
            list4.add(j);
        }

        assertThrows(IllegalArgumentException.class, () -> t.maxTriplet(list4));
    }
}