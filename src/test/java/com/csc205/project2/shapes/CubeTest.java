package com.csc205.project2.shapes;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Cube} class.
 *
 * Covers:
 * - Basic Functionality: constructor, getters, immutability (no setter)
 * - Calculation Accuracy: volume and surface area for known values
 * - Boundary Testing: zero, very small, and very large side lengths
 * - Input Validation: negative values, null/blank name and color
 * - Inheritance Testing: polymorphic behavior via Shape3D reference
 */
public class CubeTest {

    // -----------------------------
    // Basic Functionality
    // -----------------------------
    @Test
    public void testConstructorAndGetters_and_Immutability() {
        Cube cube = new Cube("MyCube", "Blue", 3.0);
        assertEquals(3.0, cube.getSideLength(), 1e-12);
        assertEquals("MyCube", cube.getName());
        assertEquals("Blue", cube.getColor());

        // The provided Cube implementation does not expose a setter for sideLength;
        // verify that no public setSideLength method exists (immutability expectation).
        boolean hasSetter = Arrays.stream(Cube.class.getMethods())
                .anyMatch(m -> m.getName().equals("setSideLength"));
        assertFalse(hasSetter, "Cube should not expose a setSideLength(...) method");
    }

    // -----------------------------
    // Calculation Accuracy
    // -----------------------------
    @Test
    public void testVolumeAndSurfaceArea_knownValues() {
        Cube cube = new Cube("UnitCube", "White", 3.0);
        // For side = 3.0: volume = 27.0, surface area = 6 * 9 = 54.0
        assertEquals(27.0, cube.calculateVolume(), 1e-9);
        assertEquals(54.0, cube.calculateSurfaceArea(), 1e-9);

        // Test a non-integer side length and verify rounding behavior used by Cube
        double side = 1.234;
        Cube c2 = new Cube("C2", "Green", side);
        double expectedVolume = Math.round(Math.pow(side, 3) * 100.0) / 100.0;
        double expectedSurface = Math.round((6 * Math.pow(side, 2)) * 100.0) / 100.0;
        assertEquals(expectedVolume, c2.calculateVolume(), 1e-12);
        assertEquals(expectedSurface, c2.calculateSurfaceArea(), 1e-12);
    }

    // -----------------------------
    // Boundary Testing
    // -----------------------------
    @Test
    public void testZeroAndNegative_sideLengths_throw() {
        assertThrows(IllegalArgumentException.class, () -> new Cube("Z", "Black", 0.0));
        assertThrows(IllegalArgumentException.class, () -> new Cube("Neg", "Black", -1.0));
    }

    @Test
    public void testVerySmall_and_veryLarge_sideLengths() {
        // Very small but positive (Double.MIN_VALUE is > 0) — rounding may result in 0.0
        double tiny = Double.MIN_VALUE; // smallest positive non-zero double
        Cube tinyCube = new Cube("Tiny", "Transparent", tiny);
        double tinyVolume = tinyCube.calculateVolume();
        // Because Cube rounds to 2 decimal places, extremely tiny volumes become 0.0
        assertEquals(0.0, tinyVolume, 0.0);

        // Very large
        double large = 1e6;
        Cube largeCube = new Cube("Large", "Silver", large);
        double expectedLargeVolume = Math.round(Math.pow(large, 3) * 100.0) / 100.0;
        double expectedLargeSurface = Math.round((6 * Math.pow(large, 2)) * 100.0) / 100.0;
        assertEquals(expectedLargeVolume, largeCube.calculateVolume(), 1e-6);
        assertEquals(expectedLargeSurface, largeCube.calculateSurfaceArea(), 1e-6);
    }

    // -----------------------------
    // Input Validation
    // -----------------------------
    @Test
    public void testNullOrBlankNameOrColorThrows() {
        // Null name
        assertThrows(IllegalArgumentException.class, () -> new Cube(null, "Red", 1.0));
        // Null color
        assertThrows(IllegalArgumentException.class, () -> new Cube("N", null, 1.0));
        // Blank name
        assertThrows(IllegalArgumentException.class, () -> new Cube(" ", "Red", 1.0));
        // Blank color
        assertThrows(IllegalArgumentException.class, () -> new Cube("N", " ", 1.0));
    }

    // -----------------------------
    // Inheritance / Polymorphism
    // -----------------------------
    @Test
    public void testPolymorphicBehavior_viaShape3D() {
        Shape3D shape = new Cube("Poly", "Orange", 2.0);
        // Shape3D#getVolume/getSurfaceArea delegate to the concrete implementations
        assertEquals(shape.getVolume(), 8.0, 1e-9); // 2^3 = 8
        assertEquals(shape.getSurfaceArea(), 24.0, 1e-9); // 6 * 4 = 24

        // toString() should include class name, name, and color
        String s = shape.toString();
        assertTrue(s.contains("Cube"));
        assertTrue(s.contains("Poly"));
        assertTrue(s.contains("Orange"));
    }
}
