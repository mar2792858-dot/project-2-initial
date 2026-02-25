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
* - Volume formula verified against: [source]
* - Surface area formula verified against: [source]
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
* - Volume formula verified against: [source]
* - Surface area formula verified against: [source]
    */
