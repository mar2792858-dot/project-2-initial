package com.csc205.project2;

import com.csc205.project2.shapes.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Scanner;

/**
 * Sophisticated driver demonstrating polymorphism, comparative analysis,
 * interactive shape creation, performance timing, and formatted output.
 *
 * Features:
 * - Holds a List<Shape3D> of different concrete shapes (polymorphism)
 * - Interactive prompts to create shapes with validation
 * - Pre-populated sample shapes for quick demo
 * - Analysis: largest volume, largest surface area, best volume/surface ratio
 * - Simple performance timing for volume/surface calculations
 *
 * Usage note: To skip the interactive prompts and run the driver using only
 * the pre-populated default shapes, start the program with the argument
 * "noninteractive" or "auto". For example (PowerShell):
 *
 *   java -cp target/classes com.csc205.project2.ShapeDriver noninteractive
 */
public class ShapeDriver {

    private static final Locale LOCALE = Locale.US; // ensure dot decimal formatting

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Shape3D> shapes = new ArrayList<>();

        printHeader();

        // Add some default shapes to showcase functionality
        addDefaultShapes(shapes);

        // If user passed "noninteractive" as arg, skip interactive creation and run analysis
        boolean interactive = true;
        for (String a : args) {
            if ("noninteractive".equalsIgnoreCase(a) || "auto".equalsIgnoreCase(a)) {
                interactive = false;
            }
        }

        if (interactive) {
            System.out.println("You can add custom shapes interactively. Type 'done' when finished.");
            interactiveCreateLoop(scanner, shapes);
        }

        System.out.println();
        printShapes(shapes);

        System.out.println();
        runAnalysis(shapes);

        System.out.println();
        runPerformanceTiming(shapes);

        System.out.println("\nThank you for using the 3D Shape Analysis System.");
    }

    private static void printHeader() {
        System.out.println("=== 3D Shape Analysis System ===\n");
    }

    private static void addDefaultShapes(List<Shape3D> shapes) {
        // Safe default shapes demonstrating available concrete classes
        try {
            shapes.add(new Sphere("Red Ball", "Red", 5.0));
            shapes.add(new Cube("Blue Box", "Blue", 4.0));
            shapes.add(new Cylinder("Tall Tube", "Silver", 2.5, 10.0));
            shapes.add(new RectangularPrism("Shipping Box", "Brown", 4.0, 3.0, 5.0));
            shapes.add(new Torus("Donut", "Pink", 1.0, 3.0));
        } catch (IllegalArgumentException e) {
            // Should not happen with these constants, but log if it does
            System.err.println("Failed to add default shape: " + e.getMessage());
        }
    }

    private static void interactiveCreateLoop(Scanner scanner, List<Shape3D> shapes) {
        while (true) {
            System.out.println("\nEnter the shape type to add (sphere, cube, cylinder, rectangularprism, torus) or 'done':");
            String type = scanner.nextLine().trim();
            if (type.equalsIgnoreCase("done")) break;
            if (type.isBlank()) continue;

            try {
                Shape3D s = createShapeFromInput(type.toLowerCase(Locale.ROOT), scanner);
                if (s != null) {
                    shapes.add(s);
                    System.out.println("Added: " + conciseDescription(s));
                } else {
                    System.out.println("Unknown shape type: " + type);
                }
            } catch (IllegalArgumentException ex) {
                System.out.println("Invalid input: " + ex.getMessage());
            } catch (Exception ex) {
                System.out.println("Error creating shape: " + ex.getMessage());
            }
        }
    }

    private static Shape3D createShapeFromInput(String type, Scanner scanner) {
        System.out.println("Enter a name for the shape:");
        String name = scanner.nextLine().trim();
        if (name.isBlank()) name = "Unnamed";

        System.out.println("Enter a color for the shape:");
        String color = scanner.nextLine().trim();
        if (color.isBlank()) color = "Unknown";

        switch (type) {
            case "sphere":
                double radius = promptDouble(scanner, "radius (positive)");
                return new Sphere(name, color, radius);
            case "cube":
                double side = promptDouble(scanner, "side length (positive)");
                return new Cube(name, color, side);
            case "cylinder":
                double r = promptDouble(scanner, "radius (positive)");
                double h = promptDouble(scanner, "height (positive)");
                return new Cylinder(name, color, r, h);
            case "rectangularprism":
            case "rectangular_prism":
            case "prism":
                double length = promptDouble(scanner, "length (positive)");
                double width = promptDouble(scanner, "width (positive)");
                double height = promptDouble(scanner, "height (positive)");
                return new RectangularPrism(name, color, length, width, height);
            case "torus":
                double inner = promptDouble(scanner, "inner (tube) radius (positive)");
                double outer = promptDouble(scanner, "outer (major) radius (must be > inner)");
                return new Torus(name, color, inner, outer);
            default:
                return null;
        }
    }

    private static double promptDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.printf(LOCALE, "Please enter %s:%n", prompt);
            String line = scanner.nextLine().trim();
            try {
                double v = Double.parseDouble(line);
                if (!Double.isFinite(v)) throw new NumberFormatException("non-finite");
                return v;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid finite number.");
            }
        }
    }

    private static void printShapes(List<Shape3D> shapes) {
        System.out.println("Created Shapes:");
        int i = 1;
        for (Shape3D s : shapes) {
            System.out.printf(LOCALE, "%d. %s %n", i++, conciseDescription(s));
            System.out.printf(LOCALE, "   - Surface Area: %.2f square units%n", s.getSurfaceArea());
            System.out.printf(LOCALE, "   - Volume: %.2f cubic units%n%n", s.getVolume());
        }
    }

    private static String conciseDescription(Shape3D s) {
        String className = s.getClass().getSimpleName();
        String details = "";
        if (s instanceof Sphere) {
            Sphere sp = (Sphere) s;
            details = String.format(LOCALE, "{name='%s', radius=%.2f}", sp.getName(), sp.getRadius());
        } else if (s instanceof Cube) {
            Cube c = (Cube) s;
            details = String.format(LOCALE, "{name='%s', side=%.2f}", c.getName(), c.getSideLength());
        } else if (s instanceof Cylinder) {
            Cylinder c = (Cylinder) s;
            details = String.format(LOCALE, "{name='%s', radius=%.2f, height=%.2f}", c.getName(), c.getRadius(), c.getHeight());
        } else if (s instanceof RectangularPrism) {
            RectangularPrism r = (RectangularPrism) s;
            details = String.format(LOCALE, "{name='%s', length=%.2f, width=%.2f, height=%.2f}", r.getName(), r.getLength(), r.getWidth(), r.getHeight());
        } else if (s instanceof Torus) {
            Torus t = (Torus) s;
            details = String.format(LOCALE, "{name='%s', inner=%.2f, outer=%.2f}", t.getName(), t.getInnerRadius(), t.getOuterRadius());
        } else {
            details = String.format("{name='%s'}", s.getName());
        }
        return className + " " + details;
    }

    private static void runAnalysis(List<Shape3D> shapes) {
        if (shapes.isEmpty()) {
            System.out.println("No shapes to analyze.");
            return;
        }

        Optional<Shape3D> maxVolume = shapes.stream().max(Comparator.comparingDouble(Shape3D::getVolume));
        Optional<Shape3D> maxSurface = shapes.stream().max(Comparator.comparingDouble(Shape3D::getSurfaceArea));
        Optional<Shape3D> bestEfficiency = shapes.stream().max(Comparator.comparingDouble(s -> s.getVolume() / s.getSurfaceArea()));

        System.out.println("Analysis Results:");
        maxVolume.ifPresent(s -> System.out.printf(LOCALE, "- Largest Volume: %s (%.2f)\n", s.getName(), s.getVolume()));
        maxSurface.ifPresent(s -> System.out.printf(LOCALE, "- Largest Surface Area: %s (%.2f)\n", s.getName(), s.getSurfaceArea()));
        bestEfficiency.ifPresent(s -> System.out.printf(LOCALE, "- Most Efficient (Volume/Surface): %s (%.2f)\n", s.getName(), s.getVolume() / s.getSurfaceArea()));
    }

    private static void runPerformanceTiming(List<Shape3D> shapes) {
        if (shapes.isEmpty()) return;
        System.out.println("Performance Timing (measuring repeated calculations):");

        final int iterations = 50_000; // keep moderate to finish quickly but measurable

        for (Shape3D s : shapes) {
            long start = System.nanoTime();
            double acc = 0.0;
            for (int i = 0; i < iterations; i++) {
                acc += s.getVolume();
                acc += s.getSurfaceArea();
            }
            long end = System.nanoTime();
            long elapsedNs = end - start;
            double elapsedMs = elapsedNs / 1_000_000.0;
            System.out.printf(LOCALE, "- %s: computed %d volume+surface pairs in %.2f ms (accumulator: %.4f)\n",
                    s.getName(), iterations, elapsedMs, acc);
        }
        System.out.println("(Note: timing includes simple method call overhead and is platform dependent.)");
    }
}
