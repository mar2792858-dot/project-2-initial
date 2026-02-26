package com.csc205.project2.shapes;

/**
 * Concrete implementation of a three-dimensional rectangular prism (cuboid) shape.
 *
 * <p>A rectangular prism is defined by three mutually perpendicular dimensions:
 * {@code length}, {@code width}, and {@code height}. This class extends
 * {@link Shape3D} and provides the geometric formulas for volume and surface area:</p>
 *
 * <ul>
 *   <li><b>Volume:</b> {@code V = l × w × h}</li>
 *   <li><b>Surface Area:</b> {@code SA = 2(lw + lh + wh)}
 *       (sum of all six rectangular faces)</li>
 * </ul>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * RectangularPrism box = new RectangularPrism("MyBox", "Green", 4.0, 3.0, 5.0);
 * System.out.println(box.getVolume());      // 60.0000
 * System.out.println(box.getSurfaceArea()); // 94.0000
 * System.out.println(box);                 // formatted toString output
 * }</pre>
 *
 * @author  YourName
 * @version 1.0
 * @see     Shape3D
 */
public class RectangularPrism extends Shape3D {

    // -------------------------------------------------------------------------
    // Fields
    // -------------------------------------------------------------------------

    /**
     * The length of the rectangular prism, in units.
     * Must be strictly greater than zero.
     */
    private double length;

    /**
     * The width of the rectangular prism, in units.
     * Must be strictly greater than zero.
     */
    private double width;

    /**
     * The height of the rectangular prism, in units.
     * Must be strictly greater than zero.
     */
    private double height;

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Constructs a {@code RectangularPrism} with the specified name, color,
     * length, width, and height.
     *
     * @param name   the name of the prism; must not be {@code null} or blank
     * @param color  the color of the prism; must not be {@code null} or blank
     * @param length the length of the prism; must be &gt; 0
     * @param width  the width of the prism; must be &gt; 0
     * @param height the height of the prism; must be &gt; 0
     * @throws IllegalArgumentException if any dimension is not strictly
     *                                  positive, or if {@code name} / {@code color}
     *                                  is invalid
     */
    public RectangularPrism(String name, String color,
                            double length, double width, double height) {
        super(name, color);
        setLength(length);
        setWidth(width);
        setHeight(height);
    }

    /**
     * Constructs a unit {@code RectangularPrism} (all dimensions = 1.0) with
     * the given name and color.
     *
     * @param name  the name of the prism; must not be {@code null} or blank
     * @param color the color of the prism; must not be {@code null} or blank
     */
    public RectangularPrism(String name, String color) {
        this(name, color, 1.0, 1.0, 1.0);
    }

    // -------------------------------------------------------------------------
    // Shape3D abstract method implementations
    // -------------------------------------------------------------------------

    /**
     * Calculates the volume of this rectangular prism using the formula:
     * <pre>V = l × w × h</pre>
     *
     * @return the volume of the prism, always &gt; 0
     */
    @Override
    protected double calculateVolume() {
        return length * width * height;
    }

    /**
     * Calculates the total surface area of this rectangular prism using the
     * formula:
     * <pre>SA = 2(lw + lh + wh)</pre>
     *
     * <p>This accounts for all six rectangular faces: two length-width faces
     * (top/bottom), two length-height faces (front/back), and two width-height
     * faces (left/right).</p>
     *
     * @return the total surface area of the prism, always &gt; 0
     */
    @Override
    protected double calculateSurfaceArea() {
        return 2 * ((length * width) + (length * height) + (width * height));
    }

    // -------------------------------------------------------------------------
    // Getters and Setters
    // -------------------------------------------------------------------------

    /**
     * Returns the length of this rectangular prism.
     *
     * @return the length in units; always &gt; 0
     */
    public double getLength() {
        return length;
    }

    /**
     * Sets the length of this rectangular prism.
     *
     * @param length the new length; must be strictly greater than zero
     * @throws IllegalArgumentException if {@code length} is &le; 0
     */
    public void setLength(double length) {
        if (length <= 0) {
            throw new IllegalArgumentException(
                    "RectangularPrism length must be greater than zero, but was: " + length);
        }
        this.length = length;
    }

    /**
     * Returns the width of this rectangular prism.
     *
     * @return the width in units; always &gt; 0
     */
    public double getWidth() {
        return width;
    }

    /**
     * Sets the width of this rectangular prism.
     *
     * @param width the new width; must be strictly greater than zero
     * @throws IllegalArgumentException if {@code width} is &le; 0
     */
    public void setWidth(double width) {
        if (width <= 0) {
            throw new IllegalArgumentException(
                    "RectangularPrism width must be greater than zero, but was: " + width);
        }
        this.width = width;
    }

    /**
     * Returns the height of this rectangular prism.
     *
     * @return the height in units; always &gt; 0
     */
    public double getHeight() {
        return height;
    }

    /**
     * Sets the height of this rectangular prism.
     *
     * @param height the new height; must be strictly greater than zero
     * @throws IllegalArgumentException if {@code height} is &le; 0
     */
    public void setHeight(double height) {
        if (height <= 0) {
            throw new IllegalArgumentException(
                    "RectangularPrism height must be greater than zero, but was: " + height);
        }
        this.height = height;
    }

    // -------------------------------------------------------------------------
    // Object overrides
    // -------------------------------------------------------------------------

    /**
     * Returns a formatted string representation of this rectangular prism,
     * extending the base {@link Shape3D#toString()} output with prism-specific
     * dimensional properties.
     *
     * <p>Example output:</p>
     * <pre>
     * RectangularPrism {
     *   Name         : MyBox
     *   Color        : Green
     *   Volume       : 60.0000 units³
     *   Surface Area : 94.0000 units²
     *   Length       : 4.0000 units
     *   Width        : 3.0000 units
     *   Height       : 5.0000 units
     * }
     * </pre>
     *
     * @return a descriptive, multi-line string for this rectangular prism
     */
    @Override
    public String toString() {
        return String.format(
                "%s%n" +
                        "  Length       : %.4f units%n" +
                        "  Width        : %.4f units%n" +
                        "  Height       : %.4f units%n" +
                        "}",
                super.toString().substring(0, super.toString().lastIndexOf('}')).stripTrailing(),
                length,
                width,
                height
        );
    }

    /**
     * Indicates whether some other object is equal to this
     * {@code RectangularPrism}.
     *
     * <p>Two rectangular prisms are equal if their base class properties
     * ({@code name} and {@code color}) are equal and all three dimensions
     * ({@code length}, {@code width}, {@code height}) match exactly.</p>
     *
     * @param obj the reference object with which to compare
     * @return {@code true} if the objects are equal; {@code false} otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        RectangularPrism other = (RectangularPrism) obj;
        return Double.compare(length, other.length) == 0
                && Double.compare(width, other.width) == 0
                && Double.compare(height, other.height) == 0;
    }

    /**
     * Returns a hash code for this rectangular prism, consistent with
     * {@link #equals(Object)}.
     *
     * @return a hash code derived from the base class hash, {@code length},
     *         {@code width}, and {@code height}
     */
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Double.hashCode(length);
        result = 31 * result + Double.hashCode(width);
        result = 31 * result + Double.hashCode(height);
        return result;
    }
}