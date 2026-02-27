package com.csc205.project2.shapes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit 5 test suite for the {@link RectangularPrism} class.
 *
 * Mirrors the style and organization used in {@link SphereTest} and {@link CylinderTest}.
 */
@DisplayName("RectangularPrism Tests")
class RectangularPrismTest {

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
            RectangularPrism r = new RectangularPrism("Box", "Green", 4.0, 3.0, 5.0);

            assertAll("constructor properties",
                    () -> assertEquals("Box", r.getName()),
                    () -> assertEquals("Green", r.getColor()),
                    () -> assertEquals(4.0, r.getLength(), DELTA),
                    () -> assertEquals(3.0, r.getWidth(), DELTA),
                    () -> assertEquals(5.0, r.getHeight(), DELTA)
            );
        }

        @Test
        @DisplayName("Convenience constructor defaults dimensions to 1.0")
        void convenienceConstructorDefaults() {
            RectangularPrism r = new RectangularPrism("UnitBox", "White");

            assertAll("convenience constructor",
                    () -> assertEquals("UnitBox", r.getName()),
                    () -> assertEquals("White", r.getColor()),
                    () -> assertEquals(1.0, r.getLength(), DELTA),
                    () -> assertEquals(1.0, r.getWidth(), DELTA),
                    () -> assertEquals(1.0, r.getHeight(), DELTA)
            );
        }

        @Test
        @DisplayName("Setters update dimensions correctly")
        void settersUpdateValues() {
            RectangularPrism r = new RectangularPrism("R", "Blue", 2.0, 2.0, 2.0);
            r.setLength(6.5);
            r.setWidth(7.5);
            r.setHeight(8.5);

            assertAll("setters",
                    () -> assertEquals(6.5, r.getLength(), DELTA),
                    () -> assertEquals(7.5, r.getWidth(), DELTA),
                    () -> assertEquals(8.5, r.getHeight(), DELTA)
            );
        }

        @Test
        @DisplayName("toString() contains key shape information")
        void toStringContainsKeyInfo() {
            RectangularPrism r = new RectangularPrism("MyBox", "Magenta", 2.0, 3.0, 4.0);
            String out = r.toString();

            assertAll("toString content",
                    () -> assertTrue(out.contains("RectangularPrism")),
                    () -> assertTrue(out.contains("MyBox")),
                    () -> assertTrue(out.contains("Magenta")),
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
        @DisplayName("Volume: 4 x 3 x 5 = 60.0")
        void volumeKnownValue() {
            RectangularPrism r = new RectangularPrism("B", "G", 4.0, 3.0, 5.0);
            double expected = 4.0 * 3.0 * 5.0;
            assertEquals(expected, r.getVolume(), DELTA);
        }

        @Test
        @DisplayName("Surface area: 2(lw+lh+wh) for 4,3,5 → 94.0")
        void surfaceAreaKnownValue() {
            RectangularPrism r = new RectangularPrism("B", "G", 4.0, 3.0, 5.0);
            double expected = 2 * ((4.0 * 3.0) + (4.0 * 5.0) + (3.0 * 5.0));
            assertEquals(expected, r.getSurfaceArea(), DELTA);
        }

        @Test
        @DisplayName("Calculations update after setters")
        void calculationsUpdateAfterSetters() {
            RectangularPrism r = new RectangularPrism("R", "C", 1.0, 1.0, 1.0);
            r.setLength(2.0);
            r.setWidth(3.0);
            r.setHeight(4.0);

            double expectedVol = 2.0 * 3.0 * 4.0;
            double expectedArea = 2 * ((2.0 * 3.0) + (2.0 * 4.0) + (3.0 * 4.0));

            assertAll("updated calculations",
                    () -> assertEquals(expectedVol, r.getVolume(), DELTA),
                    () -> assertEquals(expectedArea, r.getSurfaceArea(), DELTA)
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
        @DisplayName("Zero dimensions throw IllegalArgumentException")
        void zeroDimensionsThrow() {
            assertThrows(IllegalArgumentException.class,
                    () -> new RectangularPrism("ZL", "Black", 0.0, 1.0, 1.0));
            assertThrows(IllegalArgumentException.class,
                    () -> new RectangularPrism("ZW", "Black", 1.0, 0.0, 1.0));
            assertThrows(IllegalArgumentException.class,
                    () -> new RectangularPrism("ZH", "Black", 1.0, 1.0, 0.0));
        }

        @Test
        @DisplayName("Very small positive dimensions accepted (Double.MIN_VALUE)")
        void verySmallDimensionsAccepted() {
            assertDoesNotThrow(() -> new RectangularPrism("Tiny", "Clear", Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE));
            RectangularPrism tiny = new RectangularPrism("Tiny", "Clear", Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE);
            assertTrue(Double.isFinite(tiny.getVolume()));
            assertTrue(Double.isFinite(tiny.getSurfaceArea()));
        }

        @Test
        @DisplayName("Very large dimensions produce finite, positive results")
        void veryLargeDimensionsProduceFiniteResults() {
            double large = 1e6;
            RectangularPrism big = new RectangularPrism("Huge", "Silver", large, large, large);

            assertAll("huge prism",
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
        @DisplayName("Negative dimensions in constructor throw IllegalArgumentException")
        void negativeDimensionsInConstructorThrow() {
            assertThrows(IllegalArgumentException.class, () -> new RectangularPrism("N", "Red", -1.0, 1.0, 1.0));
            assertThrows(IllegalArgumentException.class, () -> new RectangularPrism("N", "Red", 1.0, -1.0, 1.0));
            assertThrows(IllegalArgumentException.class, () -> new RectangularPrism("N", "Red", 1.0, 1.0, -1.0));
        }

        @Test
        @DisplayName("Negative dimensions via setters throw IllegalArgumentException and leave state unchanged")
        void negativeDimensionsViaSettersThrowAndLeaveState() {
            RectangularPrism r = new RectangularPrism("R", "Red", 2.0, 3.0, 4.0);
            assertThrows(IllegalArgumentException.class, () -> r.setLength(-2.0));
            assertEquals(2.0, r.getLength(), DELTA, "length should remain unchanged after failed setter");

            assertThrows(IllegalArgumentException.class, () -> r.setWidth(-3.0));
            assertEquals(3.0, r.getWidth(), DELTA, "width should remain unchanged after failed setter");

            assertThrows(IllegalArgumentException.class, () -> r.setHeight(-4.0));
            assertEquals(4.0, r.getHeight(), DELTA, "height should remain unchanged after failed setter");
        }

        @Test
        @DisplayName("Null or blank name/color throws IllegalArgumentException")
        void nullOrBlankNameOrColorThrows() {
            assertThrows(IllegalArgumentException.class, () -> new RectangularPrism(null, "Red", 1.0, 1.0, 1.0));
            assertThrows(IllegalArgumentException.class, () -> new RectangularPrism("N", null, 1.0, 1.0, 1.0));
            assertThrows(IllegalArgumentException.class, () -> new RectangularPrism(" ", "Red", 1.0, 1.0, 1.0));
            assertThrows(IllegalArgumentException.class, () -> new RectangularPrism("N", " ", 1.0, 1.0, 1.0));
        }
    }

    // =========================================================================
    // 5. Inheritance & Polymorphism
    // =========================================================================
    @Nested
    @DisplayName("5 - Inheritance & Polymorphism")
    class InheritanceTests {

        @Test
        @DisplayName("RectangularPrism treated as Shape3D calls correct calculations")
        void prismAsShape3D() {
            Shape3D shape = new RectangularPrism("P", "Red", 4.0, 3.0, 5.0);

            double expectedVol = 4.0 * 3.0 * 5.0;
            double expectedArea = 2 * ((4.0 * 3.0) + (4.0 * 5.0) + (3.0 * 5.0));

            assertAll("polymorphic Shape3D",
                    () -> assertEquals(expectedVol, shape.getVolume(), DELTA),
                    () -> assertEquals(expectedArea, shape.getSurfaceArea(), DELTA)
            );
        }

        @Test
        @DisplayName("RectangularPrism treated as ThreeDimensionalShape calls correct calculations")
        void prismAsThreeDimensionalShape() {
            ThreeDimensionalShape s = new RectangularPrism("P", "Red", 2.0, 3.0, 4.0);
            double expectedVol = 2.0 * 3.0 * 4.0;
            double expectedArea = 2 * ((2.0 * 3.0) + (2.0 * 4.0) + (3.0 * 4.0));

            assertAll("polymorphic interface",
                    () -> assertEquals(expectedVol, s.getVolume(), DELTA),
                    () -> assertEquals(expectedArea, s.getSurfaceArea(), DELTA)
            );
        }

        @Test
        @DisplayName("Equal prisms have matching equals() and hashCode()")
        void equalsAndHashCodeConsistency() {
            RectangularPrism a = new RectangularPrism("B", "Red", 4.0, 3.0, 5.0);
            RectangularPrism b = new RectangularPrism("B", "Red", 4.0, 3.0, 5.0);

            assertAll("equals and hashCode",
                    () -> assertEquals(a, b),
                    () -> assertEquals(a.hashCode(), b.hashCode())
            );
        }

        @Test
        @DisplayName("Prisms with different dimensions are not equal")
        void prismsWithDifferentDimensionsNotEqual() {
            RectangularPrism a = new RectangularPrism("B", "Red", 4.0, 3.0, 5.0);
            RectangularPrism b = new RectangularPrism("B", "Red", 5.0, 3.0, 5.0);
            assertNotEquals(a, b);
        }

        @Test
        @DisplayName("getClass().getSimpleName() returns \"RectangularPrism\"")
        void classSimpleNameIsRectangularPrism() {
            RectangularPrism r = new RectangularPrism("B", "Red", 4.0, 3.0, 5.0);
            assertEquals("RectangularPrism", r.getClass().getSimpleName());
        }
    }
}
