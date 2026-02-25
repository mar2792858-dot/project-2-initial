package com.csc205.project2.shapes;

/**
 * Represents a three-dimensional cube shape.
 * A cube is a regular hexahedron with six equal square faces,
 * twelve equal edges, and eight vertices.
 *
 * <p>This class extends {@link Shape3D} and provides implementations for
 * calculating the volume and surface area of a cube.</p>
 *
 * @author Your Name
 * @version 1.0
 */
public class Cube extends Shape3D {

    /** The length of each side of the cube. Must be greater than zero. */
    private final double sideLength;

    /**
     * Constructs a new Cube with the specified name, color, and side length.
     *
     * @param name       the name of the cube; must not be null or blank
     * @param color      the color of the cube; must not be null or blank
     * @param sideLength the length of each side of the cube; must be greater than zero
     * @throws IllegalArgumentException if name or color is null/blank,
     *                                  or if sideLength is not positive
     */
    public Cube(String name, String color, double sideLength) {
        super(name, color);
        if (sideLength <= 0) {
            throw new IllegalArgumentException("Side length must be greater than zero. Provided: " + sideLength);
        }
        this.sideLength = sideLength;
    }

    /**
     * Returns the side length of this cube.
     *
     * @return the side length of the cube
     */
    public double getSideLength() {
        return sideLength;
    }

    /**
     * Calculates the diagonal length across one face of the cube.
     * Uses the formula: d = s√2
     *
     * @return the face diagonal length, rounded to two decimal places
     */
    public double getFaceDiagonal() {
        return Math.round((sideLength * Math.sqrt(2)) * 100.0) / 100.0;
    }

    /**
     * Calculates the space diagonal of the cube — the longest diagonal
     * that passes through the interior from one corner to the opposite corner.
     * Uses the formula: d = s√3
     *
     * @return the space diagonal length, rounded to two decimal places
     */
    public double getSpaceDiagonal() {
        return Math.round((sideLength * Math.sqrt(3)) * 100.0) / 100.0;
    }

    /**
     * Calculates and returns the volume of this cube.
     * Uses the formula: V = s³
     *
     * @return the volume of the cube, rounded to two decimal places
     */
    @Override
    public double calculateVolume() {
        return Math.round(Math.pow(sideLength, 3) * 100.0) / 100.0;
    }

    /**
     * Calculates and returns the surface area of this cube.
     * A cube has 6 equal square faces, so the formula is: SA = 6 * s²
     *
     * @return the surface area of the cube, rounded to two decimal places
     */
    @Override
    public double calculateSurfaceArea() {
        return Math.round((6 * Math.pow(sideLength, 2)) * 100.0) / 100.0;
    }

    /**
     * Returns a formatted string representation of this Cube,
     * including its name, color, side length, volume, and surface area.
     *
     * @return a string representation of the cube
     */
    @Override
    public String toString() {
        return String.format(
                "Cube { name='%s', color='%s', sideLength=%.2f, volume=%.2f, surfaceArea=%.2f }",
                getName(), getColor(), sideLength, calculateVolume(), calculateSurfaceArea()
        );
    }

    /**
     * Checks equality based on name, color, and side length.
     *
     * @param o the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cube)) return false;
        if (!super.equals(o)) return false;
        Cube cube = (Cube) o;
        return Double.compare(cube.sideLength, sideLength) == 0;
    }

    /**
     * Returns the hash code for this Cube based on its name, color, and side length.
     *
     * @return the hash code value
     */
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Double.hashCode(sideLength);
        return result;
    }
}