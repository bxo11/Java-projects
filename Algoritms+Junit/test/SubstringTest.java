import com.substring.Substring;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubstringTest {

    @Test
    void test_subString() {
        Substring substring = new Substring();
        assertEquals(3, substring.subString("abcd", "cdabcdab"));
        assertEquals(7, substring.subString("123", "231231231231231231"));

        assertThrows(IllegalArgumentException.class, () -> substring.subString("abc", "abcd"));
        assertThrows(IllegalArgumentException.class, () -> substring.subString("abcx", "abcd"));
    }
}