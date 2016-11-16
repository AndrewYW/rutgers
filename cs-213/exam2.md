>Here are the list of topics that will be covered: inheritance, interfaces, abstract classes, polymorphism, UML.
This includes everything we have done in lectures (up to and including Default Methods in Interfaces of Nov 2) and recitation (up to and including Nov 3, Interface Default Methods/OO Design) EXCEPT GUI material. Serialization and design patterns will NOT be on the exam. Lambda expressions will NOT be on the exam either.

>Unlike the first exam, which emphasized specific Java features, the focus on this second exam will be on OO design and implementation, plus UML. So pay attention to lecture and recitation material that specifically address these topics such as when to use inheritance and when not to, how to use interfaces/abstract classes, what are the different ways in which polymorphism can be implemented, etc.

>Also pay attention to particular aspects of UML such as data that is a consequence of associations that don't get included in either of the classes at either end of the association because they are not inherent to the individual classes, association classes and how they come about, uni and bidirectional associations, etc. The recitation problems in UML address these aspects pretty thoroughly.

# Inheritance

- Allows for polymorphism and code reuse
- Using the structure and behavior of a super class in the subclass

- Inheritance conflict resolution rules:
  1. Classes come first : takes priority over default method in an interface
  ```java
    public class Lion {
      public void roar(){
        System.out.println("Lion roar");
      }
    }
    public interface Tiger{
      default void roar(){
        System.out.println("Tiger roar");
      }
    }

    public class Liger extends Lion implements Tiger {
      public static void main(String[] args) {
        new Liger().roar();   //returns Lion roar
      }
    }

  ```
  2. The default method in the most specific sub-interface is used when there are only interface implementations
  3. If neither of the above, then the class must pick the default method by:
    - Overriding it
    - Calling the desired method (`Lion.super.roar()` in Liger)
### When/When not to use Inheritance

- Person/Student/Employee delegation instead of inheritance:
- Scenario 1: Student graduates and becomes employee of university:
  - Solution A: Replace `Student` object with `Employee` object
    - Data from `Student` may need to be preserved, but no place for this in Employee
  - Solution B: Keep inactive `Student` object, create an active `Employee`
    - All `Person` level data is duplicated in both objects, wasting space
    - When change in `Person` level data, must be made in both (tracking for synchronization is a hassle)
- Scenario 2: Student is an employee at the same time
  - Solution: Keep active objects, `Student` and `Employee`
    - `Person` level data is duplicated again
    - When change in `Person` level data, must be made in both
- **Summary: Employee and Student are temporary ROLES played by Person**
  - Inheritance not good design here
  - Use composition/delegation

```java
public class Person {
  private String name;
  private String address;
  public String getAddress() {
    return address;
  }
}

public class Student{
  private Person me;    //Student refers to a Person instance; i.e. Student has a Person

  private Transcript myTranscript;
  public String getAddress() {
    return me.getAddress();     //Person specific functionality delegated to Person object
  }

  public float getGPA() {
    ...
  }
}
```

- **OVERALL:** If class B (`Student`) models a temporary role played by class A (`Person`), then B should not be a subclass of A.
  - B should reference A and use delegation to do A-specific things

# Interfaces

- All methods in interfaces must be public
- A class describes attributes and behaviors of an object - an interface contains behaviors that a class implements
- Cannot instantiate interface
- All methods in interface are abstract (i.e: sub interfaces don't have to override)
- Cannot contain instance fields (only fields in interface must be both static and final)
- **Can extend multiple interfaces**
  - A class that implements an interface must implement all methods in that interface (and possibly chaining up several interfaces)

### How to use

- Conflict resolution between class and interface:

```java
public class X {
     public void m1() {
        System.out.println("X:m1");
     }
  }
  public interface I {
     default void m1() {
        System.out.println("I:m1");
  }
  public class XI extends X implements I {
     public static void main(String[] args) {
        new XI().m1();
     }
  }
```

- Result is "X:m1" - Class overrides interface
- For multiple inheritance, more specific will be implemented:

```java
public class C implements A, B {
     public static void main(String[] args) {
         new C().hello();
     }
 }
```
- Prints "B!" when `public interface B extends A`


# Abstract Classes
- Cannot be instantiated even if methods have been implemented
  - Given `public abstract class Device{}`, will not have `Device device = new Device();`
- If class has abstract method, header must have `abstract` keyword
- Abstract methods cannot have body
   ```java
    public abstract class X {     //includes abstract header
      public abstract void stuff() {  //cannot have body here
        System.out.println("abstract");
      }
    }
    ```
- Can implement non-default constructors:
  - Use by concrete classes
- Any class inheriting current class must either override abstract method or declare itself abstract
  - Eventually a descendant class has to implement the abstract method;

```java
public abstract class Device {
  protected String name;
  protected int horizontalResolution;
  protected int verticalResolution;

  public Device(String name, int hres, int vres) {
    this.name = name;
    horizontalResolution = hres;
    verticalResolution = vres;
  }
}

```

### How to use
- Example animal hierarchy:

```java
public abstract class Animal {
  public void run() {
    System.out.println("run");

  }
}
public abstract class Feline extends Animal {
  public void purr() {
    System.out.println("purr");
  }
}
public abstract class Equine extends Animal {
  public void trot(){
    System.out.println("trot");
  }
}

public class Tiger extends Feline{
  public void purr() {
    System.out.print("Tiger: ");
    super.purr();
  }
  public void run() {
    System.out.print("Tiger: ");
    super.run();
  }
}

public class Zebra extends Equine {
  public void trot() {
    System.out.print("Zebra: ");
    super.trot();
  }
  public void run() {
    System.out.print("Zebra: ");
    super.run();
  }
}


...

Animal[] animals = new Animal[5];
animals[0] = new Tiger();
animals[1] = new Lion();
animals[2] = new Zebra();
animals[3] = new Horse();
animals[4] = new Donkey();

for(int i = 0; i < 5; i++){
  animals[i].run();
}

Equine[] equines = new Equine[3];
equines[0] = (Equine)animals[2];
equines[1] = (Equine)animals[3];
equines[2] = (Equine)animals[4];

for(int i = 0; i < 3; i++){
  equines[i].trot();
}
```
 - `animals[i].run();` yields polymorphic results:
  ```
  Tiger: run
  Lion: run
  Zebra: run
  Horse: run
  Donkey: run

  ```

- `equines[i].trot()` also yields polymorphism:
```
Zebra: trot
Horse: trot
Donkey: trot
```

- Example of interface and abstract class combination that works:

```java
public interface I {
  void stuff();
}
public abstract class X {
  public abstract void stuff();
}

public class Y extends X implements I {
  public void stuff() {}
}

```

- Implement abstract only:
  - At runtime, this would print "D": Compiler knows that the `c` object is of class D

```java
public abstract class C {
  public abstract void write();
}
public class D extends C{
  public void write() {
    System.out.println("D");
  }
  public static void main(String[] args) {
    C c = new D();
    c.write();
  }
}
```

# Polymorphism
- Changing the behavior of a superclass in the subclass
- Ability of an object to take on many forms
- Most common use: parent class reference is used to refer to a child class object
- Object must pass more than one IS-A test

### Ways to implement polymorphism
- Example of polymorphic code:
  - `Person`, `Student`, both have a method `read`
  ```java
    Person p = new Student();
    p.read();
  ```
  - The `Student` `read` method gets called, not the `Person`


# UML
