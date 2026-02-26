package com.csc205.project2.shapes;

/**
 * Concrete implementation of a three-dimensional cylinder shape.
 *
 * <p>A cylinder is defined by a circular cross-section of a given {@code radius}
 * and a perpendicular {@code height}. This class extends {@link Shape3D} and
 * provides the geometric formulas for volume and surface area:</p>
 *
 * <ul>
 *   <li><b>Volume:</b> {@code V = π × r² × h}</li>
 *   <li><b>Surface Area:</b> {@code SA = 2π × r² + 2π × r × h}
 *       (two circular caps + lateral surface)</li>
 * </ul>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * Cylinder c = new Cylinder("MyCylinder", "Blue", 3.0, 7.0);
 * System.out.println(c.getVolume());      // 197.9203
 * System.out.println(c.getSurfaceArea()); // 188.4956
 * System.out.println(c);                 // formatted toString output
 * }</pre>
 *
 * @author  YourName
 * @version 1.0
 * @see     Shape3D
 */
public class Cylinder extends Shape3D {

    // -------------------------------------------------------------------------
    // Fields
    // -------------------------------------------------------------------------

    /**
     * The radius of the cylinder's circular cross-section, in units.
     * Must be strictly greater than zero.
     */
    private double radius;

    /**
     * The height (length) of the cylinder along its central axis, in units.
     * Must be strictly greater than zero.
     */
    private double height;

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Constructs a {@code Cylinder} with the specified name, color, radius,
     * and height.
     *
     * @param name   the name of the cylinder; must not be {@code null} or blank
     * @param color  the color of the cylinder; must not be {@code null} or blank
     * @param radius the radius of the circular base; must be &gt; 0
     * @param height the height of the cylinder; must be &gt; 0
     * @throws IllegalArgumentException if {@code radius} or {@code height}
     *                                  is not strictly positive, or if
     *                                  {@code name} / {@code color} is invalid
     */
    public Cylinder(String name, String color, double radius, double height) {
        super(name, color);
        setRadius(radius);
        setHeight(height);
    }

    /**
     * Constructs a unit {@code Cylinder} (radius = 1.0, height = 1.0) with
     * the given name and color.
     *
     * @param name  the name of the cylinder; must not be {@code null} or blank
     * @param color the color of the cylinder; must not be {@code null} or blank
     */
    public Cylinder(String name, String color) {
        this(name, color, 1.0, 1.0);
    }

    // -------------------------------------------------------------------------
    // Shape3D abstract method implementations
    // -------------------------------------------------------------------------

    /**
     * Calculates the volume of this cylinder using the formula:
     * <pre>V = π × r² × h</pre>
     *
     * @return the volume of the cylinder, always &gt; 0
     */
    @Override
    protected double calculateVolume() {
        return Math.PI * radius * radius * height;
    }

    /**
     * Calculates the total surface area of this cylinder using the formula:
     * <pre>SA = 2π × r² + 2π × r × h</pre>
     *
     * <p>This accounts for both circular caps (top and bottom) and the
     * lateral (side) surface area.</p>
     *
     * @return the total surface area of the cylinder, always &gt; 0
     */
    @Override
    protected double calculateSurfaceArea() {
        return (2 * Math.PI * radius * radius) + (2 * Math.PI * radius * height);
    }

    // -------------------------------------------------------------------------
    // Getters and Setters
    // -------------------------------------------------------------------------

    /**
     * Returns the radius of this cylinder's circular base.
     *
     * @return the radius in units; always &gt; 0
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Sets the radius of this cylinder's circular base.
     *
     * @param radius the new radius; must be strictly greater than zero
     * @throws IllegalArgumentException if {@code radius} is &le; 0
     */
    public void setRadius(double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException(
                    "Cylinder radius must be greater than zero, but was: " + radius);
        }
        this.radius = radius;
    }

    /**
     * Returns the height of this cylinder.
     *
     * @return the height in units; always &gt; 0
     */
    public double getHeight() {
        return height;
    }

    /**
     * Sets the height of this cylinder.
     *
     * @param height the new height; must be strictly greater than zero
     * @throws IllegalArgumentException if {@code height} is &le; 0
     */
    public void setHeight(double height) {
        if (height <= 0) {
            throw new IllegalArgumentException(
                    "Cylinder height must be greater than zero, but was: " + height);
        }
        this.height = height;
    }

    // -------------------------------------------------------------------------
    // Object overrides
    // -------------------------------------------------------------------------

    /**
     * Returns a formatted string representation of this cylinder, extending
     * the base {@link Shape3D#toString()} output with cylinder-specific
     * dimensional properties.
     *
     * <p>Example output:</p>
     * <pre>
     * Cylinder {
     *   Name         : MyCylinder
     *   Color        : Blue
     *   Volume       : 197.9203 units³
     *   Surface Area : 188.4956 units²
     *   Radius       : 3.0000 units
     *   Height       : 7.0000 units
     * }
     * </pre>
     *
     * @return a descriptive, multi-line string for this cylinder
     */
    @Override
    public String toString() {
        return String.format(
                "%s%n" +
                        "  Radius       : %.4f units%n" +
                        "  Height       : %.4f units%n" +
                        "}",
                // Grab base output, stripping its closing brace to append our fields
                super.toString().substring(0, super.toString().lastIndexOf('}')).stripTrailing(),
                radius,
                height
        );
    }

    /**
     * Indicates whether some other object is equal to this {@code Cylinder}.
     *
     * <p>Two cylinders are equal if their base class properties ({@code name}
     * and {@code color}) are equal and both {@code radius} and {@code height}
     * match exactly.</p>
     *
     * @param obj the reference object with which to compare
     * @return {@code true} if the objects are equal; {@code false} otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        Cylinder other = (Cylinder) obj;
        return Double.compare(radius, other.radius) == 0
                && Double.compare(height, other.height) == 0;
    }

    /**
     * Returns a hash code for this cylinder, consistent with
     * {@link #equals(Object)}.
     *
     * @return a hash code derived from the base class hash, {@code radius},
     *         and {@code height}
     */
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Double.hashCode(radius);
        result = 31 * result + Double.hashCode(height);
        return result;
    }
}