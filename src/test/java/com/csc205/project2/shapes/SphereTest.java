package com.csc205.project2.shapes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit 5 test suite for the {@link Sphere} class.
 *
 * <p>Tests are organized into five nested categories:</p>
 * <ol>
 *   <li>{@link BasicFunctionalityTests}   — constructors, getters, setters</li>
 *   <li>{@link CalculationAccuracyTests}  — volume and surface area correctness</li>
 *   <li>{@link BoundaryTests}             — zero, very small, and very large values</li>
 *   <li>{@link InputValidationTests}      — negative values, null inputs</li>
 *   <li>{@link InheritanceTests}          — polymorphic and interface behavior</li>
 * </ol>
 *
 * <p><b>Design decision — zero radius:</b> A sphere with radius 0 is geometrically
 * degenerate (a single point). This suite documents the decision that
 * {@code setRadius(0)} should throw {@link IllegalArgumentException}, consistent
 * with the validation applied to all other shape dimensions.</p>
 *
 * @author  YourName
 * @version 1.0
 */
@DisplayName("Sphere Tests")
class SphereTest {

    /** Delta used for all floating-point equality assertions (4 decimal places). */
    private static final double DELTA = 1e-4;

    // =========================================================================
    // 1. Basic Functionality
    // =========================================================================

    /**
     * Tests for constructors, getters, and setters.
     */
    @Nested
    @DisplayName("1 - Basic Functionality")
    class BasicFunctionalityTests {

        /**
         * Verifies that the full constructor correctly assigns name, color,
         * and radius, and that getters return the expected values.
         */
        @Test
        @DisplayName("Full constructor sets all properties correctly")
        void fullConstructorSetsProperties() {
            Sphere sphere = new Sphere("MySphere", "Red", 5.0);

            assertAll("constructor properties",
                    () -> assertEquals("MySphere", sphere.getName()),
                    () -> assertEquals("Red",      sphere.getColor()),
                    () -> assertEquals(5.0,        sphere.getRadius(), DELTA)
            );
        }

        /**
         * Verifies that the two-argument convenience constructor sets name and
         * color while defaulting radius to 1.0.
         */
        @Test
        @DisplayName("Convenience constructor defaults radius to 1.0")
        void convenienceConstructorDefaultsRadius() {
            Sphere sphere = new Sphere("Default", "Blue");

            assertAll("convenience constructor",
                    () -> assertEquals("Default", sphere.getName()),
                    () -> assertEquals("Blue",    sphere.getColor()),
                    () -> assertEquals(1.0,       sphere.getRadius(), DELTA)
            );
        }

        /**
         * Verifies that {@code setName()} updates the name and the change is
         * reflected by the getter.
         */
        @Test
        @DisplayName("setName() updates name correctly")
        void setNameUpdatesName() {
            Sphere sphere = new Sphere("Old", "Green", 3.0);
            sphere.setName("New");
            assertEquals("New", sphere.getName());
        }

        /**
         * Verifies that {@code setColor()} updates the color and the change is
         * reflected by the getter.
         */
        @Test
        @DisplayName("setColor() updates color correctly")
        void setColorUpdatesColor() {
            Sphere sphere = new Sphere("S", "White", 3.0);
            sphere.setColor("Black");
            assertEquals("Black", sphere.getColor());
        }

        /**
         * Verifies that {@code setRadius()} updates the radius and the change
         * is reflected by the getter.
         */
        @Test
        @DisplayName("setRadius() updates radius correctly")
        void setRadiusUpdatesRadius() {
            Sphere sphere = new Sphere("S", "Red", 3.0);
            sphere.setRadius(7.5);
            assertEquals(7.5, sphere.getRadius(), DELTA);
        }

        /**
         * Verifies that {@code toString()} includes the class name, shape name,
         * color, and numeric results for volume and surface area.
         */
        @Test
        @DisplayName("toString() contains key shape information")
        void toStringContainsKeyInfo() {
            Sphere sphere = new Sphere("MySphere", "Red", 5.0);
            String result = sphere.toString();

            assertAll("toString content",
                    () -> assertTrue(result.contains("Sphere"),    "should contain class name"),
                    () -> assertTrue(result.contains("MySphere"),  "should contain shape name"),
                    () -> assertTrue(result.contains("Red"),       "should contain color"),
                    () -> assertTrue(result.contains("units³"),    "should contain volume units"),
                    () -> assertTrue(result.contains("units²"),    "should contain area units")
            );
        }
    }

    // =========================================================================
    // 2. Calculation Accuracy
    // =========================================================================

    /**
     * Tests that verify volume and surface area formulas produce correct results
     * against known mathematical values.
     *
     * <p>Formulas under test:</p>
     * <ul>
     *   <li>Volume:       {@code V  = (4/3) × π × r³}</li>
     *   <li>Surface Area: {@code SA = 4 × π × r²}</li>
     * </ul>
     */
    @Nested
    @DisplayName("2 - Calculation Accuracy")
    class CalculationAccuracyTests {

        /**
         * Volume of a sphere with radius 3:
         * {@code V = (4/3) × π × 3³ = 113.0973...}
         */
        @Test
        @DisplayName("Volume: radius 3 → 113.0973")
        void volumeWithRadiusThree() {
            Sphere sphere = new Sphere("S", "Red", 3.0);
            double expected = (4.0 / 3.0) * Math.PI * Math.pow(3.0, 3);
            assertEquals(expected, sphere.getVolume(), DELTA);
        }

        /**
         * Volume of a sphere with radius 1 (unit sphere):
         * {@code V = (4/3) × π ≈ 4.1888}
         */
        @Test
        @DisplayName("Volume: unit sphere (radius 1) → 4.1888")
        void volumeUnitSphere() {
            Sphere sphere = new Sphere("S", "Red", 1.0);
            double expected = (4.0 / 3.0) * Math.PI;
            assertEquals(expected, sphere.getVolume(), DELTA);
        }

        /**
         * Volume of a sphere with radius 5:
         * {@code V = (4/3) × π × 125 = 523.5988}
         */
        @Test
        @DisplayName("Volume: radius 5 → 523.5988")
        void volumeWithRadiusFive() {
            Sphere sphere = new Sphere("S", "Red", 5.0);
            double expected = (4.0 / 3.0) * Math.PI * Math.pow(5.0, 3);
            assertEquals(expected, sphere.getVolume(), DELTA);
        }

        /**
         * Surface area of a sphere with radius 3:
         * {@code SA = 4 × π × 3² = 113.0973...}
         */
        @Test
        @DisplayName("Surface area: radius 3 → 113.0973")
        void surfaceAreaWithRadiusThree() {
            Sphere sphere = new Sphere("S", "Red", 3.0);
            double expected = 4 * Math.PI * Math.pow(3.0, 2);
            assertEquals(expected, sphere.getSurfaceArea(), DELTA);
        }

        /**
         * Surface area of a unit sphere:
         * {@code SA = 4 × π ≈ 12.5664}
         */
        @Test
        @DisplayName("Surface area: unit sphere (radius 1) → 12.5664")
        void surfaceAreaUnitSphere() {
            Sphere sphere = new Sphere("S", "Red", 1.0);
            double expected = 4 * Math.PI;
            assertEquals(expected, sphere.getSurfaceArea(), DELTA);
        }

        /**
         * Surface area of a sphere with radius 5:
         * {@code SA = 4 × π × 25 = 314.1593}
         */
        @Test
        @DisplayName("Surface area: radius 5 → 314.1593")
        void surfaceAreaWithRadiusFive() {
            Sphere sphere = new Sphere("S", "Red", 5.0);
            double expected = 4 * Math.PI * Math.pow(5.0, 2);
            assertEquals(expected, sphere.getSurfaceArea(), DELTA);
        }

        /**
         * Verifies that changing the radius via {@code setRadius()} causes
         * subsequent volume and surface area calls to reflect the new value.
         */
        @Test
        @DisplayName("Calculations update correctly after setRadius()")
        void calculationsUpdateAfterSetRadius() {
            Sphere sphere = new Sphere("S", "Red", 1.0);
            sphere.setRadius(4.0);

            double expectedVolume = (4.0 / 3.0) * Math.PI * Math.pow(4.0, 3);
            double expectedArea   = 4 * Math.PI * Math.pow(4.0, 2);

            assertAll("updated calculations",
                    () -> assertEquals(expectedVolume, sphere.getVolume(),      DELTA),
                    () -> assertEquals(expectedArea,   sphere.getSurfaceArea(), DELTA)
            );
        }
    }

    // =========================================================================
    // 3. Boundary Testing
    // =========================================================================

    /**
     * Tests for edge-case inputs: zero, very small, and very large radii.
     *
     * <p><b>Decision — zero radius:</b> A radius of exactly {@code 0} is
     * rejected with {@link IllegalArgumentException}. A point in space is not
     * a sphere, so accepting {@code 0} would produce meaningless volume and
     * surface area values and violate the class contract of {@code radius > 0}.</p>
     */
    @Nested
    @DisplayName("3 - Boundary Testing")
    class BoundaryTests {

        /**
         * A radius of exactly zero is degenerate (a point, not a sphere).
         * The expected behavior is an {@link IllegalArgumentException}.
         */
        @Test
        @DisplayName("Zero radius throws IllegalArgumentException")
        void zeroRadiusThrowsException() {
            assertThrows(IllegalArgumentException.class,
                    () -> new Sphere("S", "Red", 0.0),
                    "A zero radius should be rejected as it produces a degenerate sphere");
        }

        /**
         * A very small but positive radius should be accepted and produce
         * numerically consistent (near-zero) volume and surface area.
         */
        @Test
        @DisplayName("Very small radius (1e-9) is accepted and computes correctly")
        void verySmallRadiusComputesCorrectly() {
            double r = 1e-9;
            Sphere sphere = new Sphere("Tiny", "Red", r);

            double expectedVolume = (4.0 / 3.0) * Math.PI * Math.pow(r, 3);
            double expectedArea   = 4 * Math.PI * Math.pow(r, 2);

            assertAll("tiny sphere",
                    () -> assertTrue(sphere.getVolume() > 0,        "volume should be positive"),
                    () -> assertTrue(sphere.getSurfaceArea() > 0,   "surface area should be positive"),
                    () -> assertEquals(expectedVolume, sphere.getVolume(),      1e-40),
                    () -> assertEquals(expectedArea,   sphere.getSurfaceArea(), 1e-25)
            );
        }

        /**
         * A very large radius should be accepted and produce a finite, positive
         * result without overflow to {@link Double#POSITIVE_INFINITY}.
         */
        @Test
        @DisplayName("Very large radius (1e6) is accepted and produces finite results")
        void veryLargeRadiusProducesFiniteResult() {
            double r = 1e6;
            Sphere sphere = new Sphere("Huge", "Blue", r);

            assertAll("huge sphere",
                    () -> assertTrue(Double.isFinite(sphere.getVolume()),      "volume should be finite"),
                    () -> assertTrue(Double.isFinite(sphere.getSurfaceArea()), "surface area should be finite"),
                    () -> assertTrue(sphere.getVolume() > 0,                   "volume should be positive"),
                    () -> assertTrue(sphere.getSurfaceArea() > 0,              "surface area should be positive")
            );
        }

        /**
         * The smallest positive double value should be accepted without throwing.
         */
        @Test
        @DisplayName("Double.MIN_VALUE radius is accepted")
        void doubleMinValueRadiusIsAccepted() {
            assertDoesNotThrow(() -> new Sphere("S", "Red", Double.MIN_VALUE));
        }
    }

    // =========================================================================
    // 4. Input Validation
    // =========================================================================

    /**
     * Tests that confirm illegal inputs are rejected with appropriate exceptions.
     */
    @Nested
    @DisplayName("4 - Input Validation")
    class InputValidationTests {

        /**
         * A negative radius is geometrically meaningless and must be rejected.
         */
        @Test
        @DisplayName("Negative radius in constructor throws IllegalArgumentException")
        void negativeRadiusInConstructorThrows() {
            assertThrows(IllegalArgumentException.class,
                    () -> new Sphere("S", "Red", -1.0));
        }

        /**
         * Calling {@code setRadius()} with a negative value must be rejected
         * even after a valid sphere has been constructed.
         */
        @Test
        @DisplayName("Negative radius via setRadius() throws IllegalArgumentException")
        void negativeRadiusViaSetterThrows() {
            Sphere sphere = new Sphere("S", "Red", 3.0);
            assertThrows(IllegalArgumentException.class,
                    () -> sphere.setRadius(-5.0));
        }

        /**
         * A null name must be rejected to preserve the non-null contract.
         */
        @Test
        @DisplayName("Null name throws IllegalArgumentException")
        void nullNameThrows() {
            assertThrows(IllegalArgumentException.class,
                    () -> new Sphere(null, "Red", 3.0));
        }

        /**
         * A blank name (whitespace only) must be rejected.
         */
        @Test
        @DisplayName("Blank name throws IllegalArgumentException")
        void blankNameThrows() {
            assertThrows(IllegalArgumentException.class,
                    () -> new Sphere("   ", "Red", 3.0));
        }

        /**
         * A null color must be rejected to preserve the non-null contract.
         */
        @Test
        @DisplayName("Null color throws IllegalArgumentException")
        void nullColorThrows() {
            assertThrows(IllegalArgumentException.class,
                    () -> new Sphere("S", null, 3.0));
        }

        /**
         * A blank color (whitespace only) must be rejected.
         */
        @Test
        @DisplayName("Blank color throws IllegalArgumentException")
        void blankColorThrows() {
            assertThrows(IllegalArgumentException.class,
                    () -> new Sphere("S", "  ", 3.0));
        }

        /**
         * Passing {@link Double#NaN} as radius must be rejected, as NaN
         * would silently propagate into all calculations.
         */
        @Test
        @DisplayName("NaN radius throws IllegalArgumentException")
        void nanRadiusThrows() {
            assertThrows(IllegalArgumentException.class,
                    () -> new Sphere("S", "Red", Double.NaN));
        }

        /**
         * Passing {@link Double#POSITIVE_INFINITY} as radius must be rejected,
         * since infinite dimensions produce meaningless results.
         */
        @Test
        @DisplayName("Infinite radius throws IllegalArgumentException")
        void infiniteRadiusThrows() {
            assertThrows(IllegalArgumentException.class,
                    () -> new Sphere("S", "Red", Double.POSITIVE_INFINITY));
        }

        /**
         * Verifies that an invalid setter call leaves the original radius
         * unchanged (strong exception guarantee).
         */
        @Test
        @DisplayName("Failed setRadius() leaves original radius unchanged")
        void failedSetRadiusLeavesStateUnchanged() {
            Sphere sphere = new Sphere("S", "Red", 3.0);
            assertThrows(IllegalArgumentException.class,
                    () -> sphere.setRadius(-1.0));
            assertEquals(3.0, sphere.getRadius(), DELTA,
                    "Radius should remain unchanged after a failed setter call");
        }
    }

    // =========================================================================
    // 5. Inheritance & Polymorphism
    // =========================================================================

    /**
     * Tests that verify {@code Sphere} behaves correctly when treated as its
     * parent types — {@link Shape3D} and {@link ThreeDimensionalShape}.
     */
    @Nested
    @DisplayName("5 - Inheritance & Polymorphism")
    class InheritanceTests {

        /**
         * A {@code Sphere} reference assigned to a {@code Shape3D} variable
         * must still call the correct overridden calculations at runtime.
         */
        @Test
        @DisplayName("Sphere treated as Shape3D calls correct calculations")
        void sphereAsShape3D() {
            Shape3D shape = new Sphere("S", "Red", 3.0);

            double expectedVolume = (4.0 / 3.0) * Math.PI * Math.pow(3.0, 3);
            double expectedArea   = 4 * Math.PI * Math.pow(3.0, 2);

            assertAll("polymorphic Shape3D",
                    () -> assertEquals(expectedVolume, shape.getVolume(),      DELTA),
                    () -> assertEquals(expectedArea,   shape.getSurfaceArea(), DELTA)
            );
        }

        /**
         * A {@code Sphere} reference assigned to a {@code ThreeDimensionalShape}
         * interface variable must correctly dispatch to sphere-specific methods.
         */
        @Test
        @DisplayName("Sphere treated as ThreeDimensionalShape calls correct calculations")
        void sphereAsThreeDimensionalShape() {
            ThreeDimensionalShape shape = new Sphere("S", "Red", 5.0);

            double expectedVolume = (4.0 / 3.0) * Math.PI * Math.pow(5.0, 3);
            double expectedArea   = 4 * Math.PI * Math.pow(5.0, 2);

            assertAll("polymorphic ThreeDimensionalShape",
                    () -> assertEquals(expectedVolume, shape.getVolume(),      DELTA),
                    () -> assertEquals(expectedArea,   shape.getSurfaceArea(), DELTA)
            );
        }

        /**
         * Verifies that {@code Sphere} is an instance of both {@code Shape3D}
         * and {@code ThreeDimensionalShape}.
         */
        @Test
        @DisplayName("Sphere is an instance of Shape3D and ThreeDimensionalShape")
        void sphereInstanceChecks() {
            Sphere sphere = new Sphere("S", "Red", 3.0);

            assertAll("instanceof checks",
                    () -> assertInstanceOf(Shape3D.class,              sphere),
                    () -> assertInstanceOf(ThreeDimensionalShape.class, sphere)
            );
        }

        /**
         * Two spheres with identical properties must be considered equal, and
         * their hash codes must match.
         */
        @Test
        @DisplayName("Equal spheres have matching equals() and hashCode()")
        void equalsAndHashCodeConsistency() {
            Sphere a = new Sphere("S", "Red", 3.0);
            Sphere b = new Sphere("S", "Red", 3.0);

            assertAll("equals and hashCode",
                    () -> assertEquals(a, b,                "equal spheres should satisfy equals()"),
                    () -> assertEquals(a.hashCode(), b.hashCode(), "equal spheres must have same hashCode()")
            );
        }

        /**
         * Two spheres with different radii must not be considered equal.
         */
        @Test
        @DisplayName("Spheres with different radii are not equal")
        void spheresWithDifferentRadiiAreNotEqual() {
            Sphere a = new Sphere("S", "Red", 3.0);
            Sphere b = new Sphere("S", "Red", 4.0);
            assertNotEquals(a, b);
        }

        /**
         * Verifies that {@code getClass().getSimpleName()} on a sphere returns
         * {@code "Sphere"}, confirming the runtime type used in {@code toString()}.
         */
        @Test
        @DisplayName("getClass().getSimpleName() returns \"Sphere\"")
        void classSimpleNameIsSphere() {
            Sphere sphere = new Sphere("S", "Red", 3.0);
            assertEquals("Sphere", sphere.getClass().getSimpleName());
        }
    }
}
