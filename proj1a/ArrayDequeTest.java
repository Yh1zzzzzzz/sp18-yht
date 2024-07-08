
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

class ArrayDequeTest {

    @Test
    public static void testaddsizeempty() {
        ArrayDeque<String> dq = new ArrayDeque<>();
        assertEquals(true, dq.isEmpty());

        dq.addFirst("first");
        assertEquals(1, dq.size());

        dq.addLast("last");
        assertEquals(2, dq.size());

        dq.printDeque();

        String first = dq.removeFirst();
        assertEquals("first", first);

        String last = dq.removeLast();
        assertEquals("last", last);

        assertEquals(0, dq.size());
    }

    public ArrayDeque<Integer> create(int[] array) {
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        for (int x : array) {
            dq.addLast(x);
        }
        return dq;
    }
    @Test
    public static void testgrowshrink() {
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        dq.isEmpty();
        for (int i = 0; i < 4; i++) {
            dq.addLast(i);
            dq.isEmpty();
        }
        for (int i = -4; i < 0; i++) {
            dq.addFirst(i);
        }
        for (int i = 0; i < 4; i++) {
            dq.removeFirst();
            dq.removeLast();
            dq.isEmpty();
        }
        assertEquals(2, dq.size());
        dq.printDeque();
    }
    public static void main(String[] args) {
        testgrowshrink();
    }
}