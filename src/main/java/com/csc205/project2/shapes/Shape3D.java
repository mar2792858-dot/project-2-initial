package com.csc205.project2.shapes;

/**
 * Abstract base class representing a three-dimensional geometric shape.
 *
 * <p>This class provides a common foundation for all 3D shapes, encapsulating
 * shared properties such as {@code name} and {@code color}, and delegating
 * volume and surface area computations to concrete subclasses via abstract
 * methods. It implements the {@link ThreeDimensionalShape} interface, whose
 * concrete {@code getVolume()} and {@code getSurfaceArea()} methods simply
 * forward to the corresponding abstract template methods defined here.</p>
 *
 * <p>Typical usage:</p>
 * <pre>{@code
 * Shape3D sphere = new Sphere("MySphere", "Red", 5.0);
 * System.out.println(sphere);             // formatted toString output
 * System.out.println(sphere.getVolume()); // delegates to Sphere#calculateVolume()
 * }</pre>
 *
 * @author  YourName
 * @version 1.0
 * @see     ThreeDimensionalShape
 */
public abstract class Shape3D implements ThreeDimensionalShape {

    // -------------------------------------------------------------------------
    // Fields
    // -------------------------------------------------------------------------

    /**
     * The human-readable name of this shape (e.g., {@code "Sphere"}, {@code "Cube"}).
     */
    private String name;

    /**
     * The color of this shape (e.g., {@code "Red"}, {@code "#FF5733"}).
     */
    private String color;

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Constructs a {@code Shape3D} with the specified name and color.
     *
     * @param name  the name of the shape; must not be {@code null} or blank
     * @param color the color of the shape; must not be {@code null} or blank
     * @throws IllegalArgumentException if {@code name} or {@code color} is
     *                                  {@code null} or blank
     */
    public Shape3D(String name, String color) {
        setName(name);
        setColor(color);
    }

    /**
     * Default no-argument constructor.
     *
     * <p>Initializes {@code name} and {@code color} to {@code "Unknown"}.
     * Subclasses may call this implicitly or explicitly via {@code super()}.</p>
     */
    protected Shape3D() {
        this("Unknown", "Unknown");
    }

    // -------------------------------------------------------------------------
    // Abstract template methods (subclasses must implement)
    // -------------------------------------------------------------------------

    /**
     * Calculates and returns the volume of this shape.
     *
     * <p>Subclasses must provide a formula-specific implementation.
     * This method is called internally by {@link #getVolume()}.</p>
     *
     * @return the volume of this shape as a {@code double}, always &ge; 0
     */
    protected abstract double calculateVolume();

    /**
     * Calculates and returns the surface area of this shape.
     *
     * <p>Subclasses must provide a formula-specific implementation.
     * This method is called internally by {@link #getSurfaceArea()}.</p>
     *
     * @return the surface area of this shape as a {@code double}, always &ge; 0
     */
    protected abstract double calculateSurfaceArea();

    // -------------------------------------------------------------------------
    // ThreeDimensionalShape interface — concrete implementations
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     *
     * <p>Delegates to {@link #calculateVolume()} to obtain the shape-specific
     * result.</p>
     *
     * @return the volume of this shape
     */
    @Override
    public final double getVolume() {
        return calculateVolume();
    }

    /**
     * {@inheritDoc}
     *
     * <p>Delegates to {@link #calculateSurfaceArea()} to obtain the
     * shape-specific result.</p>
     *
     * @return the surface area of this shape
     */
    @Override
    public final double getSurfaceArea() {
        return calculateSurfaceArea();
    }

    // -------------------------------------------------------------------------
    // Getters and Setters
    // -------------------------------------------------------------------------

    /**
     * Returns the name of this shape.
     *
     * @return the shape's name; never {@code null}
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of this shape.
     *
     * @param name the new name; must not be {@code null} or blank
     * @throws IllegalArgumentException if {@code name} is {@code null} or blank
     */
    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Shape name must not be null or blank.");
        }
        this.name = name;
    }

    /**
     * Returns the color of this shape.
     *
     * @return the shape's color; never {@code null}
     */
    public String getColor() {
        return color;
    }

    /**
     * Sets the color of this shape.
     *
     * @param color the new color; must not be {@code null} or blank
     * @throws IllegalArgumentException if {@code color} is {@code null} or blank
     */
    public void setColor(String color) {
        if (color == null || color.isBlank()) {
            throw new IllegalArgumentException("Shape color must not be null or blank.");
        }
        this.color = color;
    }

    // -------------------------------------------------------------------------
    // Object overrides
    // -------------------------------------------------------------------------

    /**
     * Returns a consistently formatted string representation of this shape.
     *
     * <p>Format:</p>
     * <pre>
     * [ShapeClassName] {
     *   Name         : Sphere
     *   Color        : Red
     *   Volume       : 523.5988 units³
     *   Surface Area : 314.1593 units²
     * }
     * </pre>
     *
     * @return a formatted, multi-line string describing this shape
     */
    @Override
    public String toString() {
        return String.format(
                "%s {%n" +
                        "  Name         : %s%n" +
                        "  Color        : %s%n" +
                        "  Volume       : %.4f units³%n" +
                        "  Surface Area : %.4f units²%n" +
                        "}",
                getClass().getSimpleName(),
                name,
                color,
                getVolume(),
                getSurfaceArea()
        );
    }

    /**
     * Indicates whether some other object is "equal to" this shape.
     *
     * <p>Two {@code Shape3D} instances are considered equal if they have the
     * same runtime class, name, and color.</p>
     *
     * @param obj the reference object with which to compare
     * @return {@code true} if the objects are equal; {@code false} otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Shape3D other = (Shape3D) obj;
        return name.equals(other.name) && color.equals(other.color);
    }

    /**
     * Returns a hash code value for this shape, consistent with
     * {@link #equals(Object)}.
     *
     * @return a hash code derived from {@code name} and {@code color}
     */
    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + color.hashCode();
        return result;
    }
}
