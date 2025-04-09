/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tqs.sets;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import tqs.sets.BoundedSetOfNaturals;

/**
 * @author ico0
 */
class BoundedSetOfNaturalsTest {
    private BoundedSetOfNaturals setA;
    private BoundedSetOfNaturals setB;
    private BoundedSetOfNaturals setC;


    @BeforeEach
    public void setUp() {
        setA = new BoundedSetOfNaturals(1);
        setB = BoundedSetOfNaturals.fromArray(new int[]{10, 20, 30, 40, 50, 60});
        setC = BoundedSetOfNaturals.fromArray(new int[]{50, 60});
    }

    @AfterEach
    public void tearDown() {
        setA = setB = setC = null;
    }

    @Test
    public void testAddElement() {

        setA.add(99);
        assertTrue(setA.contains(99), "add: added element not found in set.");
        assertEquals(1, setA.size());

        try {
            setB.add(11);
            System.out.println("Elemento 11 adicionado com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao adicionar 11: " + e.getMessage());
        }
    }

    @Test
    public void testAddFromBadArray() {
        int[] elems = new int[]{10, -20, -30};

        // must fail with exception
        assertThrows(IllegalArgumentException.class, () -> setA.add(elems));
    }

    @Test
    void testAddDuplicateElementThrowsException() {
        BoundedSetOfNaturals set = new BoundedSetOfNaturals(5);
        set.add(3);
        assertThrows(IllegalArgumentException.class, () -> set.add(3));
    }

    @Test
    void testAddNegativeElementThrowsException() {
        BoundedSetOfNaturals set = new BoundedSetOfNaturals(5);
        assertThrows(IllegalArgumentException.class, () -> set.add(-1));
    }

    @Test
    void testAddBeyondMaxSizeThrowsException() {
        BoundedSetOfNaturals set = new BoundedSetOfNaturals(2);
        set.add(1);
        set.add(2);
        assertThrows(IllegalArgumentException.class, () -> set.add(3));
    }

    @Test
    void testFromArrayCreatesCorrectSet() {
        int[] values = { 1, 2, 3 };
        BoundedSetOfNaturals set = BoundedSetOfNaturals.fromArray(values);
        assertEquals(3, set.size());
        assertTrue(set.contains(1) && set.contains(2) && set.contains(3));
    }

    @Test
    void testIntersectsWithCommonElement() {
        BoundedSetOfNaturals setA = BoundedSetOfNaturals.fromArray(new int[] { 1, 2, 3 });
        BoundedSetOfNaturals setB = BoundedSetOfNaturals.fromArray(new int[] { 3, 4, 5 });
        assertTrue(setA.intersects(setB));
    }

    @Test
    void testIntersectsWithNoCommonElement() {
        BoundedSetOfNaturals setA = BoundedSetOfNaturals.fromArray(new int[] { 1, 2, 3 });
        BoundedSetOfNaturals setB = BoundedSetOfNaturals.fromArray(new int[] { 4, 5, 6 });
        assertFalse(setA.intersects(setB));
    }

    @Test
    void testIntersectsWithEmptySet() {
        BoundedSetOfNaturals setA = BoundedSetOfNaturals.fromArray(new int[] { 1, 2, 3 });
        BoundedSetOfNaturals setB = new BoundedSetOfNaturals(5);
        assertFalse(setA.intersects(setB));
    }

    @Test
    void testEqualsWithSameObject() {
        BoundedSetOfNaturals setA = new BoundedSetOfNaturals(5);
        setA.add(1);
        setA.add(2);
        assertEquals(setA, setA);
    }

    @Test
    void testEqualsWithNull() {
        BoundedSetOfNaturals setA = new BoundedSetOfNaturals(5);
        assertNotEquals(null, setA);
    }

    @Test
    void testEqualsWithDifferentClass() {
        BoundedSetOfNaturals setA = new BoundedSetOfNaturals(5);
        assertNotEquals(setA, "String");
    }

    @Test
    void testEqualsWithDifferentElements() {
        BoundedSetOfNaturals setA = new BoundedSetOfNaturals(5);
        BoundedSetOfNaturals setB = new BoundedSetOfNaturals(5);
        setA.add(1);
        setB.add(2);
        assertNotEquals(setA, setB);
    }

    @Test
    void testHashCodeForEqualObjects() {
        BoundedSetOfNaturals setA = new BoundedSetOfNaturals(5);
        BoundedSetOfNaturals setB = new BoundedSetOfNaturals(5);
        setA.add(1);
        setA.add(2);
        setB.add(1);
        setB.add(2);
        assertEquals(setA.hashCode(), setB.hashCode());
    }
}
