# AI Interaction Log

This document serves as a log of interactions with AI systems, capturing prompts, responses, and reflections on the outcomes. It is intended to help users track their engagements with AI and improve future interactions.

## Driver

/**
* AI GENERATION DOCUMENTATION
* ===========================
* AI Tool Used: Claude - Sonnet 4.6
* Generation Date: 2/24/2026
*
* Original Prompt:
* "Create an abstract base class `Shape3D.java` with these requirements:
  Must Include:
* Implements the `ThreeDimensionalShape` interface
* Concrete implementations of `getVolume()` and `getSurfaceArea()` that call the abstract methods
* Common properties: `name` (String), `color` (String) (Properties in Java are typically private fields with public getters and setters)
* Constructor(s) for initialization
* `toString()` method that formats output consistently
* Getter/setter methods as appropriate

Please add proper JavaDoc documentation."
*
* Follow-up Prompts (if any):
* 1. "Can you please explain this block of code?

@Override
public int hashCode() {
    int result = name.hashCode();
    result = 31 * result + color.hashCode();
    return result;
}
"
* 2.
"Can you break down what a hashCode() method is and what is the 
benefit to returning a hashCode value for these shapes?"
*
* Manual Modifications:
* - [List any changes you made to the AI output]
* - [Explain why changes were necessary]

## Class 1 : Sphere.java
/**
* AI GENERATION DOCUMENTATION
* ===========================
* AI Tool Used: Claude - Sonnet 4.6
* Generation Date: 2/25/2026
*
* Original Prompt:
* "Perfect. Onto the next task. We will be creating 5 Concrete Shapes, one at a time. Each shape must include the following:
  For Each Shape Class:
* Extends Shape3D (which implements ThreeDimensionalShape)
* Implement the abstract methods from ThreeDimensionalShape
* Include proper constructors with validation
* Override `toString()` with shape-specific formatting
* Add any shape-specific methods if needed
* proper Java documentation
* input validation where appropriate

The first shape class I would like you to create is a Sphere with Properties: radius."
*
* Follow-up Prompts (if any):
* 1. "Can you explain to me the use of muliplication by 100 and division by 100 in this block of code:
     @Override public double calculateSurfaceArea() { return Math.round((4 * Math.PI * Math.pow(radius, 2)) * 100.0) / 100.0; }"
* 2. "[Refinement prompt 2]"
*
* Manual Modifications:
* - [List any changes you made to the AI output]
* - [Explain why changes were necessary]
*
* Formula Verification:
* - Volume formula verified against: https://www.wolframalpha.com/input?i=sphere
* - Surface area formula verified against: https://www.wolframalpha.com/input?i=sphere
    */
## Class 2: Cube.java

/**
* AI GENERATION DOCUMENTATION
* ===========================
* AI Tool Used: Claude - Sonnet 4.6
* Generation Date: 2/25/2026
*
* Original Prompt:
* "Great, lets move onto the Cube class. 
* Please create this class and include the property sideLength."
*
* Follow-up Prompts (if any):
* 1. "[Refinement prompt 1]"
* 2. "[Refinement prompt 2]"
*
* Manual Modifications:
* - [List any changes you made to the AI output]
* - [Explain why changes were necessary]
*
* Formula Verification:
* - Volume formula verified against: https://www.wolframalpha.com/input?i=what+is+the+formula+for+surface+area+and+volume+of+a+cube
* - Surface area formula verified against: https://www.wolframalpha.com/input?i=what+is+the+formula+for+surface+area+and+volume+of+a+cube
    */

# Class 3: Cylinder
/**
* AI GENERATION DOCUMENTATION
* ===========================
* AI Tool Used: Claude - Sonnet 4.6
* Generation Date: 2/26/2026
*
* Original Prompt:
* "Let's continue. The next class is the Cylinder class, which will have a radius and a height property."
*
* Follow-up Prompts (if any):
* 1. "[Refinement prompt 1]"
* 2. "[Refinement prompt 2]"
*
* Manual Modifications:
* - [List any changes you made to the AI output]
* - [Explain why changes were necessary]
*
* Formula Verification:
* - Volume formula verified against: https://www.wolframalpha.com/input?i=cylinder
* - Surface area formula verified against: https://www.wolframalpha.com/input?i=cylinder
    */

# Class 4: RectangularPrism
* AI GENERATION DOCUMENTATION
* ===========================
* AI Tool Used: Claude - Sonnet 4.6
* Generation Date: 2/26/2026
*
* Original Prompt:
* "Great. The now can you create a RectangularPrism class with length, width, and height properties."
*
* Follow-up Prompts (if any):
* 1. "[Refinement prompt 1]"
* 2. "[Refinement prompt 2]"
*
* Manual Modifications:
* - [List any changes you made to the AI output]
* - [Explain why changes were necessary]
*
* Formula Verification:
* - Volume formula verified against: https://www.wolframalpha.com/input?i2d=true&i=rectangular+prism
* - Surface area formula verified against: https://www.wolframalpha.com/input?i=2+%28h+w+%2B+d+%28h+%2B+w%29%29&assumption=%22ClashPrefs%22+-%3E+%7B%22Math%22%7D
    */

# Class 5: Torus
* AI GENERATION DOCUMENTATION
* ===========================
* AI Tool Used: Claude - Sonnet 4.6
* Generation Date: 2/26/2026
*
* Original Prompt:
* "Great. The last shape class that will extend Shape3D is Torus and it needs innerRadius and outerRadius properties please."
*
* Follow-up Prompts (if any):
* 1. "[Refinement prompt 1]"
* 2. "[Refinement prompt 2]"
*
* Manual Modifications:
* - [List any changes you made to the AI output]
* - [Explain why changes were necessary]
*
* Formula Verification:
* - Volume formula verified against: [https://www.wolframalpha.com/input?i2d=true&i=torus]
* - Surface area formula verified against: [https://www.wolframalpha.com/input?i2d=true&i=torus]
    */


For the testing classes, I started with creating the SphereTest class using this prompt:

"Okay, thank you. I am moving onto the next phase, developing a test suite. The instructions are as follows:
For each shape class, generate JUnit 5 test classes that include:
Test Categories:
* Basic Functionality: Constructor, getters, setters
* Calculation Accuracy: Volume and surface area with known values
* Boundary Testing: Zero values, very small/large numbers
* Input Validation: Negative values, null inputs
* Inheritance Testing: Polymorphic behavior verification

Here is an example for test scenarios for Sphere:
// Test with known mathematical results
@Test void sphereVolumeCalculation() {
// Volume of sphere with radius 3 should be 4/3 * π * 3³ = 113.097...
}
// Test boundary conditions
@Test void sphereWithZeroRadius() {
// What should happen? Document your decision.
}
// Test polymorphism
@Test void sphereAsShape3D() {
// Verify it works when treated as Shape3D reference
}


You can start by creating a class for the sphere named SphereTest"

I then used Github copilot to debug this test class, and then create similar test classes for the other shapes. 
I did not document the prompts for the other test classes, 
but they were similar to the one above, just replacing the shape.