package dynamic_arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author  : Odinga David
 * @since   : 12/20/21, Mon
 */class DynamicArrayTest {
     private DynamicArray<Integer> array;

    @BeforeEach
    void setUp() {
       array = new DynamicArray<>();
    }

    @Test
    void size() {
        assertEquals(0, array.size());

        array.add(0);
        assertEquals(1, array.size());

        array.addAll(1,2,4);
        assertEquals(4, array.size());


        array.removeAt(0);
        assertEquals(3, array.size());
    }

    @Test
    void isEmpty() {
        assertTrue(array.isEmpty());

        array.add(50);
        assertFalse(array.isEmpty());

        array.remove(50);
        assertTrue(array.isEmpty());
    }

    @Test
    void get() {
        array.add(7);

        assertEquals(7, array.get(0));

        assertThrows(IndexOutOfBoundsException.class, ()-> array.get(1));
    }

    @Test
    void set() {
        assertThrows(IndexOutOfBoundsException.class, ()->array.set(1, 9));

        array.add(0);
        array.set(0, 1);
        assertEquals(1, array.get(0));
    }

    @Test
    void clear() {
        array.addAll(0,8,99, 90, 40);
        array.clear();

        assertEquals(0, array.size());
    }

    @Test
    void add() {
        array.add(7);

        assertEquals(1, array.size());
    }

    @Test
    void addAll() {
        array.addAll(1,3,5,6,7,80);
        assertEquals(6, array.size());
    }

    @Test
    void removeAt() {
        array.addAll(70,80,90);
        assertEquals(80, array.removeAt(1));
    }

    @Test
    void remove() {
        array.addAll(10,20,30,40);
        assertTrue(array.remove(40));
        assertFalse(array.remove(40));
    }

    @Test
    void indexOf() {
        array.addAll(1,3,4,5,6,7,8,9);
        assertEquals(1, array.indexOf(3));
        assertEquals(3, array.indexOf(5));
        assertEquals(7, array.indexOf(9));
    }

    @Test
    void contains() {
        array.addAll(1,3,4,5,6,7,8,9);
        assertTrue(array.contains(1));

        array.remove(1);
        assertFalse(array.contains(1));

        assertFalse(array.contains(100));
    }
}
