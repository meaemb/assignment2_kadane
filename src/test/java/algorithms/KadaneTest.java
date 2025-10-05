package algorithms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class KadaneTest {

    @Test void empty_or_null(){
        assertEquals(0, Kadane.maxSubarray(null).sum);
        assertEquals(0, Kadane.maxSubarray(new int[]{}).sum);
    }

    @Test void single_element(){
        var r = Kadane.maxSubarray(new int[]{-7});
        assertEquals(-7, r.sum);
        assertEquals(0, r.left);
        assertEquals(0, r.right);
    }

    @Test void all_negative(){
        var r = Kadane.maxSubarray(new int[]{-5,-2,-9});
        assertEquals(-2, r.sum);
        assertEquals(1, r.left);
        assertEquals(1, r.right);
    }

    @Test void mix_positive_negative(){
        var r = Kadane.maxSubarray(new int[]{1, 2, -5, 4, 7, -2});
        assertEquals(11, r.sum); // 4 + 7
        assertEquals(3, r.left);
        assertEquals(4, r.right);
    }

    @Test void zeros(){
        var r = Kadane.maxSubarray(new int[]{0,0,0});
        assertEquals(0, r.sum);
        assertEquals(0, r.left);
        assertEquals(0, r.right);
    }
}
