package com.csc205.project2.shapes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit 5 test suite for the {@link Cylinder} class.
 *
 * Mirrors the style and organization used in {@link SphereTest}.
 */
@DisplayName("Cylinder Tests")
class CylinderTest {

    /** Delta used for floating-point equality assertions. */
    private static final double DELTA = 1e-4;

    // =========================================================================
    // 1. Basic Functionality
    // =========================================================================
    @Nested
    @DisplayName("1 - Basic Functionality")
    class BasicFunctionalityTests {

        @Test
        @DisplayName("Full constructor sets all properties correctly")
        void fullConstructorSetsProperties() {
            Cylinder c = new Cylinder("MyCyl", "Red", 3.0, 7.0);

            assertAll("constructor properties",
                    () -> assertEquals("MyCyl", c.getName()),
                    () -> assertEquals("Red", c.getColor()),
                    () -> assertEquals(3.0, c.getRadius(), DELTA),
                    () -> assertEquals(7.0, c.getHeight(), DELTA)
            );
        }

        @Test
        @DisplayName("Convenience constructor defaults radius and height to 1.0")
        void convenienceConstructorDefaults() {
            Cylinder c = new Cylinder("Unit", "Blue");

            assertAll("convenience constructor",
                    () -> assertEquals("Unit", c.getName()),
                    () -> assertEquals("Blue", c.getColor()),
                    () -> assertEquals(1.0, c.getRadius(), DELTA),
                    () -> assertEquals(1.0, c.getHeight(), DELTA)
            );
        }

        @Test
        @DisplayName("setRadius() and setHeight() update values correctly")
        void settersUpdateValues() {
            Cylinder c = new Cylinder("C", "Green", 2.0, 4.0);
            c.setRadius(5.5);
            c.setHeight(6.5);

            assertAll("setters",
                    () -> assertEquals(5.5, c.getRadius(), DELTA),
                    () -> assertEquals(6.5, c.getHeight(), DELTA)
            );
        }

        @Test
        @DisplayName("toString() contains key shape information")
        void toStringContainsKeyInfo() {
            Cylinder c = new Cylinder("MyCylinder", "Blue", 3.0, 7.0);
            String out = c.toString();

            assertAll("toString content",
                    () -> assertTrue(out.contains("Cylinder")),
                    () -> assertTrue(out.contains("MyCylinder")),
                    () -> assertTrue(out.contains("Blue")),
                    () -> assertTrue(out.contains("units³")),
                    () -> assertTrue(out.contains("units²"))
            );
        }
    }

    // =========================================================================
    // 2. Calculation Accuracy
    // =========================================================================
    @Nested
    @DisplayName("2 - Calculation Accuracy")
    class CalculationAccuracyTests {

        @Test
        @DisplayName("Volume: radius 3, height 7 → π * 9 * 7 = 197.9203")
        void volumeWithRadius3Height7() {
            Cylinder c = new Cylinder("C", "Red", 3.0, 7.0);
            double expected = Math.PI * Math.pow(3.0, 2) * 7.0;
            assertEquals(expected, c.getVolume(), DELTA);
        }

        @Test
        @DisplayName("Surface area: radius 3, height 7 → 2πr² + 2πrh")
        void surfaceAreaWithRadius3Height7() {
            Cylinder c = new Cylinder("C", "Red", 3.0, 7.0);
            double expected = (2 * Math.PI * Math.pow(3.0, 2)) + (2 * Math.PI * 3.0 * 7.0);
            assertEquals(expected, c.getSurfaceArea(), DELTA);
        }

        @Test
        @DisplayName("Unit cylinder (r=1,h=1) -> volume π, surface area 4π")
        void unitCylinderCalculations() {
            Cylinder c = new Cylinder("U", "White", 1.0, 1.0);
            assertEquals(Math.PI, c.getVolume(), DELTA);
            assertEquals(4 * Math.PI, c.getSurfaceArea(), DELTA);
        }

        @Test
        @DisplayName("Calculations update correctly after setters")
        void calculationsUpdateAfterSetters() {
            Cylinder c = new Cylinder("C", "Red", 1.0, 1.0);
            c.setRadius(2.0);
            c.setHeight(3.0);

            double expectedVol = Math.PI * Math.pow(2.0, 2) * 3.0;
            double expectedArea = (2 * Math.PI * Math.pow(2.0, 2)) + (2 * Math.PI * 2.0 * 3.0);

            assertAll("updated calculations",
                    () -> assertEquals(expectedVol, c.getVolume(), DELTA),
                    () -> assertEquals(expectedArea, c.getSurfaceArea(), DELTA)
            );
        }
    }

    // =========================================================================
    // 3. Boundary Testing
    // =========================================================================
    @Nested
    @DisplayName("3 - Boundary Testing")
    class BoundaryTests {

        @Test
        @DisplayName("Zero radius or height throws IllegalArgumentException")
        void zeroDimensionsThrow() {
            assertThrows(IllegalArgumentException.class, () -> new Cylinder("Zr", "Black", 0.0, 1.0));
            assertThrows(IllegalArgumentException.class, () -> new Cylinder("Zh", "Black", 1.0, 0.0));
        }

        @Test
        @DisplayName("Very small positive dimensions accepted (Double.MIN_VALUE)")
        void verySmallDimensionsAccepted() {
            assertDoesNotThrow(() -> new Cylinder("Tiny", "Clear", Double.MIN_VALUE, Double.MIN_VALUE));
            Cylinder tiny = new Cylinder("Tiny", "Clear", Double.MIN_VALUE, Double.MIN_VALUE);
            // Ensure numeric results are finite (may underflow to 0.0 but should not throw)
            assertTrue(Double.isFinite(tiny.getVolume()));
            assertTrue(Double.isFinite(tiny.getSurfaceArea()));
        }

        @Test
        @DisplayName("Very large dimensions produce finite, positive results")
        void veryLargeDimensionsProduceFiniteResults() {
            double large = 1e6;
            Cylinder big = new Cylinder("Huge", "Silver", large, large);

            assertAll("huge cylinder",
                    () -> assertTrue(Double.isFinite(big.getVolume()), "volume should be finite"),
                    () -> assertTrue(Double.isFinite(big.getSurfaceArea()), "surface area should be finite"),
                    () -> assertTrue(big.getVolume() > 0, "volume should be positive"),
                    () -> assertTrue(big.getSurfaceArea() > 0, "surface area should be positive")
            );
        }
    }

    // =========================================================================
    // 4. Input Validation
    // =========================================================================
    @Nested
    @DisplayName("4 - Input Validation")
    class InputValidationTests {

        @Test
        @DisplayName("Negative radius/height in constructor throws IllegalArgumentException")
        void negativeDimensionsInConstructorThrow() {
            assertThrows(IllegalArgumentException.class, () -> new Cylinder("N", "Red", -1.0, 1.0));
            assertThrows(IllegalArgumentException.class, () -> new Cylinder("N", "Red", 1.0, -1.0));
        }

        @Test
        @DisplayName("Negative radius/height via setters throws IllegalArgumentException and leaves state unchanged")
        void negativeDimensionsViaSettersThrowAndLeaveState() {
            Cylinder c = new Cylinder("C", "Red", 2.0, 3.0);
            assertThrows(IllegalArgumentException.class, () -> c.setRadius(-2.0));
            assertEquals(2.0, c.getRadius(), DELTA, "radius should remain unchanged after failed setter");

            assertThrows(IllegalArgumentException.class, () -> c.setHeight(-3.0));
            assertEquals(3.0, c.getHeight(), DELTA, "height should remain unchanged after failed setter");
        }

        @Test
        @DisplayName("Null or blank name/color throws IllegalArgumentException")
        void nullOrBlankNameOrColorThrows() {
            assertThrows(IllegalArgumentException.class, () -> new Cylinder(null, "Red", 1.0, 1.0));
            assertThrows(IllegalArgumentException.class, () -> new Cylinder("N", null, 1.0, 1.0));
            assertThrows(IllegalArgumentException.class, () -> new Cylinder(" ", "Red", 1.0, 1.0));
            assertThrows(IllegalArgumentException.class, () -> new Cylinder("N", " ", 1.0, 1.0));
        }
    }

    // =========================================================================
    // 5. Inheritance & Polymorphism
    // =========================================================================
    @Nested
    @DisplayName("5 - Inheritance & Polymorphism")
    class InheritanceTests {

        @Test
        @DisplayName("Cylinder treated as Shape3D calls correct calculations")
        void cylinderAsShape3D() {
            Shape3D shape = new Cylinder("S", "Red", 3.0, 7.0);

            double expectedVol = Math.PI * Math.pow(3.0, 2) * 7.0;
            double expectedArea = (2 * Math.PI * Math.pow(3.0, 2)) + (2 * Math.PI * 3.0 * 7.0);

            assertAll("polymorphic Shape3D",
                    () -> assertEquals(expectedVol, shape.getVolume(), DELTA),
                    () -> assertEquals(expectedArea, shape.getSurfaceArea(), DELTA)
            );
        }

        @Test
        @DisplayName("Cylinder treated as ThreeDimensionalShape calls correct calculations")
        void cylinderAsThreeDimensionalShape() {
            ThreeDimensionalShape s = new Cylinder("S", "Red", 2.0, 5.0);
            double expectedVol = Math.PI * Math.pow(2.0, 2) * 5.0;
            double expectedArea = (2 * Math.PI * Math.pow(2.0, 2)) + (2 * Math.PI * 2.0 * 5.0);

            assertAll("polymorphic interface",
                    () -> assertEquals(expectedVol, s.getVolume(), DELTA),
                    () -> assertEquals(expectedArea, s.getSurfaceArea(), DELTA)
            );
        }

        @Test
        @DisplayName("Equal cylinders have matching equals() and hashCode()")
        void equalsAndHashCodeConsistency() {
            Cylinder a = new Cylinder("C", "Red", 3.0, 7.0);
            Cylinder b = new Cylinder("C", "Red", 3.0, 7.0);

            assertAll("equals and hashCode",
                    () -> assertEquals(a, b),
                    () -> assertEquals(a.hashCode(), b.hashCode())
            );
        }

        @Test
        @DisplayName("Cylinders with different dimensions are not equal")
        void cylindersWithDifferentDimensionsNotEqual() {
            Cylinder a = new Cylinder("C", "Red", 3.0, 7.0);
            Cylinder b = new Cylinder("C", "Red", 4.0, 7.0);
            assertNotEquals(a, b);
        }

        @Test
        @DisplayName("getClass().getSimpleName() returns \"Cylinder\"")
        void classSimpleNameIsCylinder() {
            Cylinder c = new Cylinder("C", "Red", 3.0, 7.0);
            assertEquals("Cylinder", c.getClass().getSimpleName());
        }
    }
}
