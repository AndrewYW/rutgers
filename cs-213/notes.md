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


# Lecture 2, Sept 12, 2016

## OOP - Inheritance

```java
public class Point {  //Superclass
  int x,y;
  public Point(int x, int y) {
    this.x = x; this.y = y;
  }
}

Point p = new Point(3,4);

public class ColoredPoint //Subclass
extends Point {
    //Inherits x and y from superclass Point
    //Code REUSE
}

ColoredPoint cp = new ColoredPoint(); //"Implicit super constructor Point() is undefined for default constructor. Must define explicit constructor."

```

## Inheritance - Subclass constructor

```java
public class ColoredPoint
extends Point{
  public ColoredPoint(){    //Default constructor
    super();                //Calls superclass's constructor
    /*Implicit super constructor Point() is undefined for default constructor. Must define an explicit constructor.*/
  }
}
```

- First statement in a subclass constructor should invoke a superclass constructor (Or another constructor in the class, with `this(...)`)
- Default constructor always calls a superclass no-arg constructor

- Problem with previous: `Point` class doesn't have a no-arg constructor

## The ideal inheritance subclass example
```java
public class ColoredPoint
extends Point{
  Strong color;
  public ColoredPoint(int x, int y, String color) {
    super(x,y);   //calls superclass's constructor
    this.color = color;
  }
}
```

- Will this alternative compile? :

```java
public ColoredPoint(int x, int y, String color) {
  this.x = x; this.y = y;
  this.color = color;
}
```
- No `super()`: required

## Inheritance - why call `super()`?

- Think of subclass instance as 2 parts: superclass part (inherited), and new subclass part
- Initialization of superclass part is best done by superclass constructor (code reuse)
- Thus, first initialize superclass part, then code to initialize subclass
- Q: When `ColoredPoint` instance is created, is an inner `Point` instance created as well?
  - A: No -- code reuse, not *instance* reuse

## Inheritance - Fields and Methods

```java
package geometry;

public class Point{
  int x,y;
  public Point(int x, int y) {
    this.x = x; this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public String toString() {
    return x + "," + y;
  }
}
```

```java
package geometry;

public class ColoredPoint
extends Point {
  String color;

  public ColoredPoint(int x, int y, String color) {
    super(x,y);
    this.color = color;
  }

  public String toString() {    //Implementation overrides inherited code
    return super.toString() + "," + color;
  }   //Reusing inherited method code in overriding method is good programming practice
}
```

## Static and Dynamic Types/Private Fields

```java

public class PointApp {
  public static void main(String[] args) {
    Point p1 = new Point(2,3);

    ColoredPoint p2 = new ColoredPoint(4, 5, "blue");

    Point p3 = new ColoredPoint(2,3,"red");
  }
}
```

Static, Compile-Time, Type | Dynamic, Run-Time, Type |
--- | --- |
p1 | 2, 3 |
p2 | 4, 5 (blue) |
p3 | 2, 3 (red) |


## Dynamic Binding  -- IMPORTANT

```java
public class PointApp {
  public static void main(String[] args) {
    Point p1 = new Point(2,3);

    ColoredPoint p2 = new ColoredPoint(4, 5, "blue");

    Point p3 = new ColoredPoint(2,3,"red");

    System.out.println(p2.getColor()); // returns 'blue'
    System.out.println(p3.getX());     // returns 2
    System.out.println("p3 + " + p3);  // returns 'p3 = 2,3,red'
                //Dynamic binding
  }
}
```

- Static type of p3 is `Point`, but dynamic type (Type of instance it points to) is `ColoredPoint`.
- So, the `p3.toString()` static call is bound to dynamic type, `ColoredPoint`
- This results in the overriding version of `toString()` being executed

## Static and Dynamic types cont.

```java
public class PointApp{
  public static void main(Strings[] args) {

    Point p5 = new ColoredPoint(1,2,green);
    System.out.println(p5.getColor());

    ColoredPoint p4 = new Point(5,6);
  }
}
```

- p5: will not compile: because static type of `p5` is `Point`, **ONLY** members of `Point` class can be syntactically referenced by p5. Since `getColor` is not in the `Point` class, compiler flags error
- p4 section: also won't work, the color field isn't entered
  - Analogy: student is missing the student specific information (problem), but has person specific info (not a problem)
  - Not every `Point` is a `ColoredPoint`, so a `Point` instance cannot be referenced by a `ColoredPoint` variable

# Lecture 3, Sept 14 2016 - Inheritance: Private Fields/Static Members

## Inheritance - Private Fields

```java
public class Point {
  private int x, y;
}

public class ColoredPoint extends Point {
  //x and y inherited but HIDDEN

  public int getX() {
    //Override
    return x;
  }
}
```

- Inherited but hidden: subclass cannot see it, so cannot be directly referenced

```java
public class ColoredPoint extends Point {
  //x and y hidden
  //getX() is not overridden
}

public class PointApp{
  public static void main(String[] args) {

    ColoredPoint cp = new ColoredPoint(4, 5, "blue");

    System.out.println(cp.x); //Will not compile, x is hidden

    System.out.println(cp.getX());  //Returns 4
  }
}
```

## Inheritance - Static Members

```java

public class Supercl {
  static int x;
  public static void m() {
    System.out.println("in class Supercl");
  }
}

public class Subcl extends Supercl {

}

public class StaticTest {
  public static void main(String[] args) {
    Supercl supercl = new Supercl();

    System.out.println(supercl.x);  // returns 0

    Subcl subcl = new Subcl();

    System.out.println(subcl.x); // returns 0 - inherited from Supercl

    subcl.m(); //"in class Supercl" - inherited from Supercl
  }
}
```

- Static fields and methods are inherited

- Now, if Subcl class had info:

```java
public class Subcl extends Supercl {
  int x = 3;
}

public class StaticTest {
  public static void main(String[] args) {
    Subcl subcl = new Subcl();

    System.out.println(subcl.x); // returns 3 - instance field x

    Supercl supercl = new Subcl();
    //Static type       //dynamic type

    System.out.println(supercl.x);    // returns 0 - inherited static field x


  }
}

```

- Dynamic binding doesn't apply to static fields
- Inherited static fields are statically bound to

## Static method call binding

```java
public class Sorter {
  public static void sort(String[] names) {
    System.out.println("simple sort");

  }
}

public class IllustratedSorter extends Sorter {

  //override
  public static void sort(String[] names) {
    System.out.println("illustrated sort");

  }
}

Sorter p = new IllustratedSorter();
//static type   //dynamic type

p.sort();  //"simple sort"
```

## Inheritance: Object Class/equals method

### Object Class

- Root of java class hierarchy
  - Every class ultimately is a subclass of `java.lang.Object`
- Methods in `Object` : all of these are inherited by **any** class (since every class is implicitly a subclass of `Object`)
  - `equals`: compares address of objects
  - `toString`: returns address of object
  - `hashCode`: returns hash code value for object

- Must generally override `equals` and `toString`

### Writing code banking on equals being there

```java

public class Searcher {
  public static <T> boolean sequentialSearch(T[] list, T target) {
    for (int i =0; i < list.length; i++) {
      if(target.equals(list[i])) {
                //Don't know what T will be at runtime, but guaranteed to have equals method
        return true;
      }

      return false;
    }
  }
}

```

- Since `Object` class defines equals, you can independently

### Overriding `equals`

- Boiler-plate way to override `equals` (e.g. `Point`):

```java

public class Point {
  int x,y;
  ...

        //header must be same as in Object class
  public boolean equals(Object o) {
    if (o == null || !(o instanceof Point)) {
                        //runtime check
      return false;     //true if dynamic type is Point or subclass of Point
    }

    Point other = (Point)o;

    return x == other.x && y == other.y;
      //Used to implement equality as appropriate (here, if x and y coordinates are equal)
  }
}

```

#### Calling the `Point equals` method

```java

Point p = new Point(3,4);

p.equals(p); // True

Point cp = new ColoredPoint(3,4,"black");

p.equals(cp); // True

String s = "(3,4)";

p.equals(s); //False
             //instanceof shows that s isn't a Point instance

```

## Background: Method Overloading/Overriding

- **Method Overloading**: Two methods in a class have the same name but different numbers, types, or sequences of parameters

```java

class Test {
  int m(int x) {...}
  int m(float y) {...}
}

class Test {
  int m(int x) {...}
  float m(float y) {...}
}

class Test {
  int m(int x) {...}
  float m(int y) {...}
}
```

- Return type doesn't matter
- Overloaded if same name, but different signatures
  - Signature = name + parameters (return type **not** included in signature)

-**Method Overriding**: A method in the subclass has the same signature as in the superclass

### `equals` overload/override

```java

public class Point {
  int x,y;

  public boolean equals(Object o) {
    if (o == null || !(o instanceof Point)) {
      return false;
    }

    Point other = (Point)o;
    return x == other.x && y == other.y;
  }

  public boolean equals(Point p) {
    if (p==null) {
      return false;
    }

    return x == p.x && x == p.y;
  }
}

Object o = new Object();
Point p = new Point(3,4);
Object op = new Point(3,4);

p.equals(o); //false
p.equals(p); //true
p.equals(op); //true

op.equals(o); //false     calls 1st method
op.equals(p); //true    calls 1st method
op.equals(op);//true  calls 1st method

```

# Lecture 5, Sept. 19 2016 - Graphical User Interface, Programmatic Design

## Preparing to build GUIs in Java FX

- Need Java 8, latest Eclipse (Neon)
- Install e(fx) plugin, see:
- https://www.eclipse.org/efxclipse/install.html
- To create FX project in Eclipse, do:
  - File -> New -> Other -> JavaFX -> JavaFX project
- Class containing main must be a subclass of javafx.application.Application

## Programmatic Layout

- Fahrenheit Celsius converter - Version 1

```java

@Override
public void start(Stage primaryStage) {
  GridPane root = makeGridPane();
  Scene scene = new Scene(root);
  primaryStage.setScene(scene);
  primaryStage.setTitle("Fahrenheit Celsius converter");
  primaryStage.setResizeable()
}

private static GridPane makeGridPane() {
  // all the widgets
  Text fText = new Text("Fahrenheit");
  Text cText = new Text("Celsius");
  TextField f = new Textfield();
  TextField c = new TextField();
  Button f2c = new Button(">>>");
  Button c2f = new Button("<<<");

  Gridpane gridpane = new GridPane();
  gridpane.add(fText, 0, 0);
  gridpane.add(f2c, 1, 0);
  gridpane.add(cText, 2, 0);
  gridpane.add(f, 0, 1);
  gridpane.add(c2f, 1, 1);
  gridpane.add(c, 2, 1);


  f.setPrefColumnCount(5);
  f.setPromptText("-40.0");
  c.setPrefColumnCount(5);
  c.setPromptText("-40.0");
  gridpane.setHgap(10);
  gridpane.setVgap(10);
  gridpane.setPadding(new Insets(10,10,10,10));
  GridPane.setValignment(fText, VPos.BOTTOM);
  GridPane.setValignment(cText, VPos.BOTTOM);

  return gridpane;
}

public static void main(String[] args) {
  launch(args);
}


```

- `String.format("%5.1f", fval);`
  - Field (output) of 5 characters, with 1 place after decimal: *xxx.x*


## Model View Control Design patter

- Data, UI, 'Business' logic
- Model: set of classes that store and manage data
- View: set of Java classes and non Java design artifacts (xml, css, etc.) that implement the UI
- Controller: set of classes that broke between Model and View


## View: Set up SceneBuilder
- Get SceneBuilder 8.2.0 at Gluon:
  - http://gluonhq.com/open-source/scene-builder/
  - Allows for SceneBuilder in Exclipse
  -


```java

public void start(Stage primaryStage) throws Exception{
  FXMLLoader loader = new FXMLLoader();
  loader.setLocation(getClass().getResource("/f2c/view/F2c.fxml"));
}

```

- This is to set fxml stuff


# Lecture Sept. 26 2016 - Static members of class - Why/when inner classes

## Static for non OOP

- Write a program to echo whatever is typed in:

```java

public class Echo {
  public static void main (String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("> ");
    String line = br.readLine();
    System.out.println(line);
  }
}

```

## Static methods for functions

- Extreme use of static methods: `java.lang.Math` class in which every method is static
  - Every method implements a mathematical function, and nothing needs to be kept in field of an object - no state to be maintained
- `Math` methods can be called directly on the class:
  `double sqroot = Math.sqrt(35);`
- Not able to create an instance of the `Math` class

## static fields for constants

## Static fields for Sharing among Instances

- Use of static field: to record data value as property of class, to be shared among all instances, but is not a constant (can be changed)
- ex: Counter that keeps track of how many objects of a class are in existence

```java

public class Tiger {
  ...
  private static int count = 0;
  public Tiger(...) {
    ...
    count++;

  }
  protected void finalize() throws Throwable {
    ...
    count--;
  }
  ...
}

```

## Static (Class) fields and Methods mixed with Non-static (instance) fields and methods

- When a method is defined as static, you can still use it in instance

### Static: Access

- Always use class name to get at static members of a class, even in situations where you can use an instance, so that code adheres to design implication of static
- `Tiger.getCount();`

## Static/Nonstatic mix: another example

- Parsing a string into an integer, e.g: "123" -> 123 - where to provide this functionality?
- Choices:
  - Have `String` instance method, say, `parseAsInteger` that returns an `int`,:
    - `int i = "123".parseAsInteger();`
    - Bad design
  - Have `String` static method:
    - `int i = String.parseAsInteger("123");`
  - Have `Integer` static method:
    - `int i = Integer.parseInt("123");`
## Inner classes

- Can access static members through instances (not recommended), but cannot access non static (instances) fields

## Interfaces

### Comparing for inequality in an algorithm implementation

- Every class is a type
- Every interface is a type
- Solution is to use a pre-existing interface that is known to prescribe an inequality comparison method
- Or, define appropriate interface that exists
- The interface introduces a type that can be checked by the compiler for match between caller and callee
- e.g: `java.lang.Comparable` interface, which defines a `compareTo` method

```java

public static <T extends Comparable<T>>
  list[index].compareTo(target)

```
- Type `T` is not just any class, but one that implements the `java.lang.Comparable` interface, or extends a class (any number of levels down the inheritance chain) that implements this interface
