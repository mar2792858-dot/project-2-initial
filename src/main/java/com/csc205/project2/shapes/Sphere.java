package com.csc205.project2.shapes;

/**
 * Represents a three-dimensional sphere shape.
 * A sphere is defined by its radius and is characterized by its perfectly
 * round surface where every point is equidistant from the center.
 *
 * <p>This class extends {@link Shape3D} and provides implementations for
 * calculating the volume and surface area of a sphere.</p>
 *
 * @author Your Name
 * @version 1.0
 */
public class Sphere extends Shape3D {

    /** The radius of the sphere. Must be greater than zero. */
    private final double radius;

    /**
     * Constructs a new Sphere with the specified name, color, and radius.
     *
     * @param name   the name of the sphere; must not be null or blank
     * @param color  the color of the sphere; must not be null or blank
     * @param radius the radius of the sphere; must be greater than zero
     * @throws IllegalArgumentException if name or color is null/blank,
     *                                  or if radius is not positive
     */
    public Sphere(String name, String color, double radius) {
        super(name, color);
        if (radius <= 0) {
            throw new IllegalArgumentException("Radius must be greater than zero. Provided: " + radius);
        }
        this.radius = radius;
    }

    /**
     * Returns the radius of this sphere.
     *
     * @return the radius of the sphere
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Calculates the diameter of this sphere.
     * The diameter is twice the radius.
     *
     * @return the diameter of the sphere
     */
    public double getDiameter() {
        return radius * 2;
    }

    /**
     * Calculates and returns the volume of this sphere.
     * Uses the formula: V = (4/3) * π * r³
     *
     * @return the volume of the sphere, rounded to two decimal places
     */
    @Override
    public double calculateVolume() {
        return Math.round(((4.0 / 3.0) * Math.PI * Math.pow(radius, 3)) * 100.0) / 100.0;
    }

    /**
     * Calculates and returns the surface area of this sphere.
     * Uses the formula: SA = 4 * π * r²
     *
     * @return the surface area of the sphere, rounded to two decimal places
     */
    @Override
    public double calculateSurfaceArea() {
        return Math.round((4 * Math.PI * Math.pow(radius, 2)) * 100.0) / 100.0;
    }

    /**
     * Returns a formatted string representation of this Sphere,
     * including its name, color, radius, volume, and surface area.
     *
     * @return a string representation of the sphere
     */
    @Override
    public String toString() {
        return String.format(
                "Sphere { name='%s', color='%s', radius=%.2f, volume=%.2f, surfaceArea=%.2f }",
                getName(), getColor(), radius, calculateVolume(), calculateSurfaceArea()
        );
    }

    /**
     * Checks equality based on name, color, and radius.
     *
     * @param o the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sphere)) return false;
        if (!super.equals(o)) return false;
        Sphere sphere = (Sphere) o;
        return Double.compare(sphere.radius, radius) == 0;
    }

    /**
     * Returns the hash code for this Sphere based on its name, color, and radius.
     *
     * @return the hash code value
     */
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Double.hashCode(radius);
        return result;
    }
}
