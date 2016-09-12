# Lecture 1, Sept 7, 2016

## Topics covered

- Object oriented programming and design
  - Classes, objects, members, encapsulation
  - Inheritance, interfaces, abstract classes, polymorphism
- Unified Modeling Language to represent OOD
- Graphical User Interfaces with Java FX 8
- Design patterns
- Android programming
- Multithreading
- Lambdas and Streams (Java 8 features)

## Brief history of OOP languages

- **Simula-67**: first OOPL,
  - Ole Johan Dahl and Kristen Nygaard (Norway)
- **Smalltalk-80**: Alan Kay, Xerox PARC in the 70s
- **C++**: Bjarne Stroustrup
  - Implemented in 1982, first commercial compilers in 1988
  - Inspired by Simula features, built with C syntax
  - C++ is a hybrid language - can compile C code with a C++ compiler
- **Java**: James Gosling (SUN Microsystems)
  - Originally conceived as a hardware independent software platform used in consumer electronics
  - **WORA** - **W**rite **O**nce **R**un **A**nywhere
  - Java 1.0 released in 1996
  - JVMs available for SPARC, Solaris, Windows NT, Windows 95, Linux
  - Supported by Netscape Navigator 2.0 browser

## OOP - Constructors, Inheritance
#### Constructor
- A constructor creates an object: T/**F**
  - A constructor initializes an object
  - In statement **new X()**, the **new X** creates an X object, while **X()** part calls the no-arg constructor of X on behalf of new object to initialize it

- When an object is created with **new**, its fields are initialized to its intrinsic default values (zero for int, null for object references, etc.): **T**/F

- **public class Point {}**
  - Will this class definition compile? **Y**/N
  - To create a new instance of Point: **new Point();**
  - The compile throws a default constructor

- Default constructor is thrown only when there is no defined constructor

```java

public class Point {
  int x, y;
  public Point(int x, int y) {
    this.x = x; this.y = y;
  }
  public Point(int x) {
    this(x, 0);
  }
  public Point() {
    this(0, 0);
  }
}
```

test
2
