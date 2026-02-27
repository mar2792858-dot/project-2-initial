package com.csc205.project2.shapes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Torus Tests")
class TorusTest {

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
            Torus t = new Torus("MyTorus", "Purple", 2.0, 5.0);

            assertAll("constructor properties",
                    () -> assertEquals("MyTorus", t.getName()),
                    () -> assertEquals("Purple", t.getColor()),
                    () -> assertEquals(2.0, t.getInnerRadius(), DELTA),
                    () -> assertEquals(5.0, t.getOuterRadius(), DELTA)
            );
        }

        @Test
        @DisplayName("Convenience constructor defaults inner=1.0 outer=2.0")
        void convenienceConstructorDefaults() {
            Torus t = new Torus("Default", "Pink");

            assertAll("convenience constructor",
                    () -> assertEquals("Default", t.getName()),
                    () -> assertEquals("Pink", t.getColor()),
                    () -> assertEquals(1.0, t.getInnerRadius(), DELTA),
                    () -> assertEquals(2.0, t.getOuterRadius(), DELTA)
            );
        }

        @Test
        @DisplayName("setInnerRadius() and setOuterRadius() update values correctly")
        void settersUpdateValues() {
            Torus t = new Torus("T", "Cyan", 1.0, 3.0);
            t.setInnerRadius(1.5);
            t.setOuterRadius(4.5);

            assertAll("setters",
                    () -> assertEquals(1.5, t.getInnerRadius(), DELTA),
                    () -> assertEquals(4.5, t.getOuterRadius(), DELTA)
            );
        }

        @Test
        @DisplayName("toString() contains key shape information")
        void toStringContainsKeyInfo() {
            Torus t = new Torus("Ring", "Gold", 1.0, 2.0);
            String out = t.toString();

            assertAll("toString content",
                    () -> assertTrue(out.contains("Torus")),
                    () -> assertTrue(out.contains("Ring")),
                    () -> assertTrue(out.contains("Gold")),
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
        @DisplayName("Volume: inner=2, outer=5 → 2π² × R × r²")
        void volumeKnownValue() {
            Torus t = new Torus("T", "Purple", 2.0, 5.0);
            double expected = 2 * Math.PI * Math.PI * 5.0 * Math.pow(2.0, 2);
            assertEquals(expected, t.getVolume(), DELTA);
        }

        @Test
        @DisplayName("Surface area: inner=2, outer=5 → 4π² × R × r")
        void surfaceAreaKnownValue() {
            Torus t = new Torus("T", "Purple", 2.0, 5.0);
            double expected = 4 * Math.PI * Math.PI * 5.0 * 2.0;
            assertEquals(expected, t.getSurfaceArea(), DELTA);
        }

        @Test
        @DisplayName("Calculations update after setters")
        void calculationsUpdateAfterSetters() {
            Torus t = new Torus("T", "Purple", 1.0, 3.0);
            t.setInnerRadius(2.0);
            t.setOuterRadius(4.0);

            double expectedVol = 2 * Math.PI * Math.PI * 4.0 * Math.pow(2.0, 2);
            double expectedArea = 4 * Math.PI * Math.PI * 4.0 * 2.0;

            assertAll("updated calculations",
                    () -> assertEquals(expectedVol, t.getVolume(), DELTA),
                    () -> assertEquals(expectedArea, t.getSurfaceArea(), DELTA)
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
        @DisplayName("Zero or non-positive inner/outer radii throw IllegalArgumentException")
        void zeroOrNegativeRadiiThrow() {
            assertThrows(IllegalArgumentException.class, () -> new Torus("Z1", "Black", 0.0, 1.0));
            assertThrows(IllegalArgumentException.class, () -> new Torus("Z2", "Black", 1.0, 0.0));
            assertThrows(IllegalArgumentException.class, () -> new Torus("Neg", "Black", -1.0, 2.0));
            assertThrows(IllegalArgumentException.class, () -> new Torus("Neg", "Black", 1.0, -2.0));
        }

        @Test
        @DisplayName("Outer radius must be strictly greater than inner radius")
        void outerMustBeGreaterThanInner() {
            assertThrows(IllegalArgumentException.class, () -> new Torus("Bad", "Red", 2.0, 2.0));
            assertThrows(IllegalArgumentException.class, () -> new Torus("Bad", "Red", 3.0, 2.0));
        }

        @Test
        @DisplayName("Very small positive radii accepted (Double.MIN_VALUE)")
        void verySmallRadiiAccepted() {
            double inner = Double.MIN_VALUE;
            double outer = Double.MIN_VALUE * 2.0; // ensure outer > inner
            assertDoesNotThrow(() -> new Torus("Tiny", "Clear", inner, outer));
            Torus tiny = new Torus("Tiny", "Clear", inner, outer);
            assertTrue(Double.isFinite(tiny.getVolume()));
            assertTrue(Double.isFinite(tiny.getSurfaceArea()));
        }

        @Test
        @DisplayName("Very large radii produce finite, positive results")
        void veryLargeRadiiProduceFiniteResults() {
            double inner = 1e3;
            double outer = 1e6;
            Torus big = new Torus("Huge", "Silver", inner, outer);

            assertAll("huge torus",
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
        @DisplayName("Negative radii in constructor throw IllegalArgumentException")
        void negativeRadiiInConstructorThrow() {
            assertThrows(IllegalArgumentException.class, () -> new Torus("N", "Red", -1.0, 2.0));
            assertThrows(IllegalArgumentException.class, () -> new Torus("N", "Red", 1.0, -2.0));
        }

        @Test
        @DisplayName("Setters reject invalid values and leave state unchanged")
        void settersRejectInvalidValuesAndLeaveState() {
            Torus t = new Torus("T", "Red", 1.0, 3.0);
            assertThrows(IllegalArgumentException.class, () -> t.setInnerRadius(-1.0));
            assertEquals(1.0, t.getInnerRadius(), DELTA, "innerRadius should remain unchanged after failed setter");

            assertThrows(IllegalArgumentException.class, () -> t.setOuterRadius(0.5)); // would be <= inner
            assertEquals(3.0, t.getOuterRadius(), DELTA, "outerRadius should remain unchanged after failed setter");
        }

        @Test
        @DisplayName("Null or blank name/color throws IllegalArgumentException")
        void nullOrBlankNameOrColorThrows() {
            assertThrows(IllegalArgumentException.class, () -> new Torus(null, "Red", 1.0, 2.0));
            assertThrows(IllegalArgumentException.class, () -> new Torus("N", null, 1.0, 2.0));
            assertThrows(IllegalArgumentException.class, () -> new Torus(" ", "Red", 1.0, 2.0));
            assertThrows(IllegalArgumentException.class, () -> new Torus("N", " ", 1.0, 2.0));
        }
    }

    // =========================================================================
    // 5. Inheritance & Polymorphism
    // =========================================================================
    @Nested
    @DisplayName("5 - Inheritance & Polymorphism")
    class InheritanceTests {

        @Test
        @DisplayName("Torus treated as Shape3D calls correct calculations")
        void torusAsShape3D() {
            Shape3D shape = new Torus("T", "Purple", 2.0, 5.0);

            double expectedVol = 2 * Math.PI * Math.PI * 5.0 * Math.pow(2.0, 2);
            double expectedArea = 4 * Math.PI * Math.PI * 5.0 * 2.0;

            assertAll("polymorphic Shape3D",
                    () -> assertEquals(expectedVol, shape.getVolume(), DELTA),
                    () -> assertEquals(expectedArea, shape.getSurfaceArea(), DELTA)
            );
        }

        @Test
        @DisplayName("Torus treated as ThreeDimensionalShape calls correct calculations")
        void torusAsThreeDimensionalShape() {
            ThreeDimensionalShape s = new Torus("T", "Purple", 1.0, 4.0);
            double expectedVol = 2 * Math.PI * Math.PI * 4.0 * Math.pow(1.0, 2);
            double expectedArea = 4 * Math.PI * Math.PI * 4.0 * 1.0;

            assertAll("polymorphic interface",
                    () -> assertEquals(expectedVol, s.getVolume(), DELTA),
                    () -> assertEquals(expectedArea, s.getSurfaceArea(), DELTA)
            );
        }

        @Test
        @DisplayName("Equal tori have matching equals() and hashCode()")
        void equalsAndHashCodeConsistency() {
            Torus a = new Torus("T", "Purple", 2.0, 5.0);
            Torus b = new Torus("T", "Purple", 2.0, 5.0);

            assertAll("equals and hashCode",
                    () -> assertEquals(a, b),
                    () -> assertEquals(a.hashCode(), b.hashCode())
            );
        }

        @Test
        @DisplayName("Tori with different dimensions are not equal")
        void toriWithDifferentDimensionsNotEqual() {
            Torus a = new Torus("T", "Purple", 2.0, 5.0);
            Torus b = new Torus("T", "Purple", 3.0, 6.0);
            assertNotEquals(a, b);
        }

        @Test
        @DisplayName("getClass().getSimpleName() returns \"Torus\"")
        void classSimpleNameIsTorus() {
            Torus t = new Torus("T", "Purple", 2.0, 5.0);
            assertEquals("Torus", t.getClass().getSimpleName());
        }
    }
}
