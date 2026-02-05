package Test;

import org.junit.Test;
import static org.junit.Assert.*;
import Class.Set;

public class SetTest {
    @Test
    public void testSection_RemovesCommonElements() {

        Set a = new Set();
        a.insert(1);
        a.insert(2);
        a.insert(3);

        Set b = new Set();
        b.insert(2);
        b.insert(4);

        a.section(b);

        assertTrue(a.member(1));
        assertFalse(a.member(2));
        assertTrue(a.member(3));
    }

    @Test
    public void testSection_NoCommonElements() {
        Set a = new Set();
        a.insert(1);
        a.insert(2);

        Set b = new Set();
        b.insert(3);
        b.insert(4);

        a.section(b);

        assertTrue(a.member(1));
        assertTrue(a.member(2));
    }

    @Test
    public void testSection_WithEmptySet() {
        Set a = new Set();
        a.insert(1);

        Set b = new Set();

        a.section(b);

        assertTrue(a.member(1));
    }

    @Test
    public void testContainsArithTriple_Empty() {
        Set s = new Set();
        assertFalse(s.containsArithTriple());
    }

    @Test
    public void testContainsArithTriple_Size2() {
        Set s = new Set();
        s.insert(1);
        s.insert(3);
        assertFalse(s.containsArithTriple());
    }

    @Test
    public void testContainsArithTriple_False() {
        Set s = new Set();
        s.insert(1);
        s.insert(2);
        s.insert(4);
        assertFalse(s.containsArithTriple());
    }

    @Test
    public void testContainsArithTriple_True() {
        Set s = new Set();
        s.insert(1);
        s.insert(3);
        s.insert(5);
        assertTrue(s.containsArithTriple());
    }

    @Test
    public void testContainsArithTriple_MoreElements() {
        Set s = new Set();
        s.insert(1);
        s.insert(2);
        s.insert(3);
        s.insert(10);
        assertTrue(s.containsArithTriple()); // 1,2,3
    }

    @Test
    public void testInsert_EmptySet() {
        Set s = new Set();
        s.insert(5);
        assertTrue(s.member(5));
    }

    @Test
    public void testInsert_LargestElement() {
        Set s = new Set();
        s.insert(1);
        s.insert(2);
        s.insert(3);
        s.insert(10);
        assertTrue(s.member(10));

        int[] arr = s.toArray();
        assertEquals(4, arr.length);
        assertEquals(10, arr[0]);
    }

    @Test
    public void testInsert_Duplicate() {
        Set s = new Set();
        s.insert(5);
        s.insert(5);

        int[] arr = s.toArray();
        assertEquals(1, arr.length);
    }

    @Test
    public void testInsert_DescendingOrder() {
        Set s = new Set();
        s.insert(10);
        s.insert(5);
        s.insert(1);

        assertTrue(s.member(10));
        assertTrue(s.member(5));
        assertTrue(s.member(1));
    }

    @Test
    public void testInsert_AscendingOrder() {
        Set s = new Set();
        s.insert(1);
        s.insert(5);
        s.insert(10);

        assertTrue(s.member(1));
        assertTrue(s.member(5));
        assertTrue(s.member(10));
    }

    @Test
    public void testMember_NotFound() {
        Set s = new Set();
        s.insert(1);
        s.insert(3);
        s.insert(5);

        assertFalse(s.member(2));
        assertFalse(s.member(4));
        assertFalse(s.member(10));
    }

    @Test
    public void testToArray_EmptySet() {
        Set s = new Set();
        int[] arr = s.toArray();
        assertEquals(0, arr.length);
    }

    @Test
    public void testSection_RemoveAll() {
        Set a = new Set();
        a.insert(1);
        a.insert(2);
        a.insert(3);

        Set b = new Set();
        b.insert(1);
        b.insert(2);
        b.insert(3);

        a.section(b);

        assertFalse(a.member(1));
        assertFalse(a.member(2));
        assertFalse(a.member(3));
    }
}
