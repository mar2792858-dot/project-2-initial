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
    // made non-final so the radius can be changed via setRadius()
    private double radius;

    /**
     * Convenience constructor that defaults radius to 1.0.
     *
     * @param name  the name of the sphere
     * @param color the color of the sphere
     */
    public Sphere(String name, String color) {
        this(name, color, 1.0);
    }

    /**
     * Constructs a new Sphere with the specified name, color, and radius.
     *
     * @param name   the name of the sphere; must not be null or blank
     * @param color  the color of the sphere; must not be null or blank
     * @param radius the radius of the sphere; must be greater than zero and finite
     * @throws IllegalArgumentException if name or color is null/blank,
     *                                  or if radius is not positive/finite
     */
    public Sphere(String name, String color, double radius) {
        super(name, color);
        validateRadius(radius);
        this.radius = radius;
    }

    /**
     * Validates a radius value without mutating state.
     */
    private static void validateRadius(double r) {
        if (!Double.isFinite(r) || r <= 0.0) {
            throw new IllegalArgumentException("Radius must be a finite number greater than zero. Provided: " + r);
        }
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
     * Sets the radius of this sphere after validating the input.
     * Strong exception guarantee: state is unchanged on failure.
     *
     * @param newRadius the new radius; must be finite and greater than zero
     * @throws IllegalArgumentException if newRadius is not finite or <= 0
     */
    public void setRadius(double newRadius) {
        validateRadius(newRadius);
        this.radius = newRadius;
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
     * @return the volume of the sphere (full double precision)
     */
    @Override
    public double calculateVolume() {
        return (4.0 / 3.0) * Math.PI * Math.pow(radius, 3);
    }

    /**
     * Calculates and returns the surface area of this sphere.
     * Uses the formula: SA = 4 * π * r²
     *
     * @return the surface area of the sphere (full double precision)
     */
    @Override
    public double calculateSurfaceArea() {
        return 4 * Math.PI * Math.pow(radius, 2);
    }

    /**
     * Delegate to superclass formatting to preserve units and multi-line layout.
     */
    @Override
    public String toString() {
        return super.toString();
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
