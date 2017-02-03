# Topics on exam:
1. Design patterns: State, Singleton, Iterator, Template Method.
2. Multithreading and synchronization on shared resources.
3. Streams (including the collect operation using Collectors, to be covered on 12/14)

### Design Patterns
- **Design Pattern**: General repeatable solution to a commonly occurring problem in software design
- Three categories:
  - *Creational* : To do with object creation process
    - **Singleton**: Ensure a class only has one instance, and provide a global point of access to it
  - *Structural* : Static composition and structure of classes and objects
    - Adapter: Convert interface of a class into another interface clients expect. Lets classes work together that couldn't otherwise because of incompatible interfaces.
  - *Behavioral* : To do with dynamic interaction between classes and objects
    - **State**: Allows object to alter its behavior when its internal state changes. Appears to change its class.
    - **Iterator**: Provide way to access elements of an aggregate object sequentially without exposing underlying representation.


### Design Pattern: State

- General implementation pattern:
  - Abstract class that specifies state methods: in general these could be entry/body/exit methods
  - Subclasses of the abstract class define different specific states

 Sample State UML diagram:
 ![](http://i.imgur.com/9oph7Jh.png)

 ![](http://i.imgur.com/7XMZoWC.png)

 - Sample code:

```java
public interface State {
  public void doAction(Context context);
}

public class StartState implements State {

   public void doAction(Context context) {
      System.out.println("Player is in start state");
      context.setState(this);
   }

   public String toString(){
      return "Start State";
   }
}

public class StopState implements State {

   public void doAction(Context context) {
      System.out.println("Player is in stop state");
      context.setState(this);
   }

   public String toString(){
      return "Stop State";
   }
}

public class StatePatternDemo {
   public static void main(String[] args) {
      Context context = new Context();

      StartState startState = new StartState();
      startState.doAction(context);

      System.out.println(context.getState().toString());

      StopState stopState = new StopState();
      stopState.doAction(context);

      System.out.println(context.getState().toString());
   }
}
```

- Result will be:

Player is in start state

Start State

Player is in stop state

Stop State

### Design Pattern: Singleton

- Ensure class has only one instance, provide global point of access to it.
- The single private constructor ensures that an instance of Singleton cannot be created using New.

```java

class CalcClearState{
  ...
  private static CalcClearState instance = null;
  private CalcClearState(){

  }
  ...
  public static CalcClearState getInstance() {
    if(instance == null) {
      instance = new CalcClearState();
    }
    return instance;
  }
  ...
}

```

```java

public class SingleObject {

   //create an object of SingleObject
   private static SingleObject instance = new SingleObject();

   //make the constructor private so that this class cannot be
   //instantiated
   private SingleObject(){}

   //Get the only object available
   public static SingleObject getInstance(){
      return instance;
   }

   public void showMessage(){
      System.out.println("Hello World!");
   }
}

public class SingletonPatternDemo {
   public static void main(String[] args) {

      //illegal construct
      //Compile Time Error: The constructor SingleObject() is not visible
      //SingleObject object = new SingleObject();

      //Get the only object available
      SingleObject object = SingleObject.getInstance();

      //show the message
      object.showMessage();
   }
}
```
### Design Pattern: Iterator

- Access contents of a collection without exposing internal representation
- Support overlapping multiple traversals
- Provide uniform interface for traversing different collections - support polymorphic iteration
- Generally there are 4 solutions for similar problems:
  1. Iterate by directly accessing nodes.
    - Only works if Nodes and front(first node) are accessible to clients, which means they are public. BAD
    - Nodes ideally should be `protected`
  2. Iterate via method invocation
    ```java
    for(list.reset(); list.hasNext();){
      System.out.println(list.next());
    }
    ```

    - Fails if there's a nested loop iterating across the same list. Not viable.
  3. Separate Iterator from the LinkedList
    -
  4. Generalization with Interface
    - Have the `LinkedListIterator` class implement an interface:

    ```java
    public interface Iterator<T>{
      boolean hasNext():
      T next();
      void remove();
    }

    ```
    - Have LinkedList implement method to return an instance of the LinkedListIterator:

    ```java
    class LinkedListIterator<T> implements Iterator<T>{
      ...
    }
    public class LinkedList<T>{
      public Iterator<T> iterator(){
        return new LinkedListIterator<T>(this);
      }
    }
    while(iterator1.hasNext()){
      ...
      while(iterator2.hasNext()){
        ...
      }
    }

    ```

### Design Pattern: Template Method

- Implements an algorithm, or set sequence of actions: each action is a method, some of which are abstract because implementations are specific to concrete subclasses
- Abstract methods are referred to as "hook" methods
- Template method hosted in abstract class, but **the method itself is not abstract**
- Each specific algorithm can extend the abstract host class, and provide its own specific version of the hook method.

```java
public abstract class Game {
   abstract void initialize();    //these are hook methods
   abstract void startPlay();
   abstract void endPlay();

   //template method
   public final void play(){

      //initialize the game
      initialize();

      //start game
      startPlay();

      //end game
      endPlay();
   }
}

public class Cricket extends Game {

   @Override
   void endPlay() {
      System.out.println("Cricket Game Finished!");
   }

   @Override
   void initialize() {
      System.out.println("Cricket Game Initialized! Start playing.");
   }

   @Override
   void startPlay() {
      System.out.println("Cricket Game Started. Enjoy the game!");
   }
}

public class Football extends Game {

   @Override
   void endPlay() {
      System.out.println("Football Game Finished!");
   }

   @Override
   void initialize() {
      System.out.println("Football Game Initialized! Start playing.");
   }

   @Override
   void startPlay() {
      System.out.println("Football Game Started. Enjoy the game!");
   }
}

public class TemplatePatternDemo {
   public static void main(String[] args) {

      Game game = new Cricket();
      game.play();
      System.out.println();
      game = new Football();
      game.play();		
   }
}

/*
Result:
Cricket Game Initialized! Start playing.
Cricket Game Started. Enjoy the game!
Cricket Game Finished!

Football Game Initialized! Start playing.
Football Game Started. Enjoy the game!
Football Game Finished!
*/
```

- Another example:

```java
public abstract class DataProcessor{
  //template method
  public final void process(Resource resource){
    try{
      open(resource);
      Data data = read(resource);
      processData(data);
      close(resource);
    } catch(OpenCloseException o){
      reportError(o);
    } catch(ReadException r){
      reportError(r);
    }
  }

  //non abstract method
  protected void processData(Data data){
    ...
  }

  //hook methods
  protected abstract void open(Resource resource);
  protected abstract Data read(Resource resource);
  protected abstract void close(Resource resource);
  protected abstract void reportError(Exception e);
}

public class DatabaseProcessor extends DataProcessor{
  protected void open(Resource resource){ ... } //database connection
  protected Data read(Resource resource){ ... } //SQL statement(s)
  protected void close(Resource resource){ ... } //database connection
  protected void reportError(Exception e){ ... } //write to database log
}

public class FileProcessor extends DataProcessor{
  protected void open(Resource resource){ ... } //open file
  protected Data read(Resource resource){ ... } //read file
  protected void close(Resource resource){ ... } //close file
  protected void reportError(Exception e){ ... } //write to log file
}

public class NetworkProcessor extends DataProcessor{
  protected void open(Resource resource){ ... } //open network stream
  protected Data read(Resource resource){ ... } //read from stream
  protected void close(Resource resource){ ... } //close network stream
  protected void reportError(Exception e){ ... } //write to a network location
}

...
//use database
DataProcessor dproc = new DatabaseProcessor();
Resource dresource = new DatabaseResource();
...
dproc.process(dresource);

//use file
DataProcessor dproc = new FileProcessor();
Resource dresource = new FileResource();
...
dproc.process(dresource);

//use network
DataProcessor dproc = new NetworkProcessor();
Resource dresource = new FileResource();
...
dproc.process(dresource);
```

#### Example 2: Graph DFS

- Since DFS serves as basis for various graph algorithms, can be implemented with template methods to be overridden appropriately by DFS-based applications/algorithms
- The base DFS code does traversal through the graph, while providing hooks for:
  - Restarting DFS at different vertices
  - Doing stuff on getting to a vertex
  - Doing stuff just before leaving a vertex(to back up to previous recursive level)

```java
public abstract class DFS {
  protected graph G;
  protected boolean[] visited;
  protected int[] info;

  public DFS(Graph g){
    this.G = G; visited = new boolean[G.n];
    for (int v = 0; v < G.n; v++){
      visited[v] = false;
    }
    info = new int[G.n];
  }

  public final int[] dfs(){ //template method
    for (int v = 0; v < G.n; v++){
      if(!visited[v]){
        restart();
        dfs(v);
      }
    }
    return info;
  }

  protected final void dfs(int v){ //template method
    preAction(v); visited[v] = true;
    Iterator<Integer> iter = G.neighborsIterator(v);
    while (iter.hasNext()){
      int v = iter.next();
      if(!visited[v]) { dfs(v); }
    }
    postAction(v);
  }

  protected abstract void restart();    //hooks
  protected abstract void preAction(int v);
  protected abstract void postAction(int v);
}

//Topological sort:
public class Topsort extends DFS{
  protected int topNum;

  public Topsort(Graph g){
    super(G);
    topNum = n-1;
  }

  //redefine hooks
  protected void restart() { }    //do nothings
  protected void preAction(int v){ }

  protected void postAction(int v) {    //slot v in sequence
    info[topNum--] = v;
  }
}

//Connected components
public class ConnComp extends DFS{
  protected int currComp;
  public ConnComp(Graph g){
    super(G);
    currComp = 0;
  }

  protected void restart(){ currComp++; } //for next component
  protected void preAction(int v){ info[v] = currComp; }
  protected void postAction(int v){ } //do nothing
}

//TopSort and ConnComp USAGE

DFS topsort = new Topsort(graph);
int[] topsequence = topsort.dfs();

DFS connectedComps = new ConnComp(graph);
int[] components = connectedComps.dfs();
```
### Multithreading

- Why threads:
  - Thread runs asynchronously, independent of thread that created it
  - Java application or applet itself runs as a thread, and can spin off as many other threas as needed
  - Collection of asynchronously running threads may communicate with each other indirectly via buffer, or directly by invoking methods on each other
  - Several tasks can be done in parallel, resulting in:
    - Improved execution time for app as a whole
    - Improved turn around time seen by user - thread can display data on the fly as it comes from server instead of blocking until all data is available
  - Asynchronous computing places onus on programmer to ensure that:
    - program avoids race conditions (two threads trying to update variable at same time)
    - program maintains consistency of data - e.g: two transactions both withdraw money from account, but second does not see withdrawal of first.
- Simple Prime number class:

```java
public class Primes{
  static int countPrimes(int n){
    int count = 0; p = 2;
    while (p <= n){
      int d;
      for (d=2; d <= p/2; d++){
        if ((p%d) == 0){
          break;
        }
      }
      if (d > p/2){
        count++;
      }
      p++;
    }
    return count;
  }

  public static void main(String[] args) throws IOException{
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter n: ");
    int n = Integer.parseInt(sc.nextLine());
    System.out.println("Number of primes <= " + n + " : " + countPrimes(n));
  }
}
```

- Watching progress of a prime number counter:
  - **Program-controlled interrupts**:
    - Have program break at regular intervals
    - Divide range 2-n into k intervals: k is determined by program
    - AFter number of primes for an interval have been found, interrupt and print
  - **User-driven interrupts**:
    - Have user interrupt program when needed
    - How to record status at every interrupt so computation can be resumed correctly?
      - Solution: On every interrupt program keeps churning out primes, even as its interacting with user. i.e: The time intensive I/O with user should not stop the program from main work, of counting primes.
      - How to have two independent executions at the same time:
        - One that interacts with user:
        - Another that keeps counting primes
        - Answer: run two independent *threads* in the program.
- How to make Primes multithreaded:
  1. Extend `java.lang.Thread` class
    ```java
    public class PrimesThread extends Thread{ }

    ```
  2. Place primes counting code in a method called run that is specifically defined by the `Thread` class (and will be overridden by `PrimesThread`) so it can be executed independently:
    ```java
    public void run(){
      count=0; p = 2;
      while (p <= n){
        int d;
        for (d=2; d <= p/2; d++){
          if ((p%d) == 0){
            break;
          }
        }
        if (d > p/2){
          count++;
        }
        p++;
      }
    }

    ```
  3. Since `run()` method does not return values, need to make count and p static fields that can be shared by the main method to report progress on demand.
  4. Define constructor that starts up an independent thread for run (Calling run directly instead of calling start will not start an independent thread)
    ```java
    public PrimesThread(int n) {
      this.n = n;
      start();
    }
    ```
    - Start method is defined by `Thread` class:
      - Sets up necessary resources to run an independent thread
      - Start up the thread to execute the `run` method
  5. Change main method to:
    - Set up an independent thread to count primes
    - On every user interruption, report current number of primes computed

    ```java
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter n: ");
    n = Integer.parseInt(sc.nextLine());

    new PrimesThread(n);    //this runs the prime thread run() method

    while(true){
      System.out.print(" ? ");
      String line = sc.nextLine();
      if(line.equals("quit")) {
        break;
      }
      System.out.println("At " + (p-1) + ", number of primes = " + count);
    }
    System.out.println("At " + (p-1) + ", number of primes = " + count);
    ```
- Output:

```
> java PrimesThread
Enter n: 100000
  ?         //hit enter here
  At 73740, number of primes = 7254
  ?
  At 100000, number of primes = 9592

```

- What if a class supports multithreading by extending `Thread`, but it already needs to extend a different class?
   - Implement the `java.lang.Runnable` interface instead of extending `Thread`
   - This interface only prescribes a single method: `void run()`, that must be implemented.
- In general it is preferable to design multithreading class by implementing `Runnable` even if class does not extend another, to provide future extensibility

```java

public class PrimesRunnable implements Runnable {
  static in count, p; int n;
  static Thread pThread;

  public PrimesRunnable(int n) {
    this.n = n;
    pThread = new Thread(this);   //Thread constructor accepts Runnable object and creates a Thread object with Runnable object as the target
    pThread.start();
  }

  public void run(){ ... }

}

```

- If prime thread is done, main thread should be terminated by breaking out of main while loop:

```java

while (true) {
  if (primeThread.getState() == Thread.State.TERMINATED) {
    break;
  }
  ...

  if(line.equals("quit")){
    primeThread.interrupt();    //interrupt prime thread
    break;
  }
}

loop condition for run changes:
public void run() {
  while(!Thread.interrupted() && p <= n){ ... }
}
```

- Multiple threads through same code:

```java

public class Interleave implements Runnable {
  public Interleave(String name) {
    new Thread(this, name).start();  //Thread constructor that accepts runnable target as well as name
  }

  public void run() {
    for (int i = 0; i < 4; i++){
      System.out.println(Thread.currentThread().getName());
      try{
        Thread.sleep((int)Math.random()*1000);
      } catch (InterruptedException e) { }
    }
  }

  public static void main(String[] args){
    new Interleave("Java");
    new Interleave("Sumatra");
  }
}

```

### Synchronization

- Synchronized method implements mutual exclusion: only one thread can execute the method at any time. A thread must acquire a lock ont he object on which this method is invoked.
- Requesting thread will then be blocked
- Unblocked -> Blocked threads must compete to reacquire lock (not FIFO)

- Waiting and notification:
  - `wait()` : Object class method that thread holding a lock can issue.
    - Relinquishes lock and becomes not runnable
    - Waiting thread can be released from wait when thread currently holding the object lock calls `notify` or `notifyAll` method on that object.
    - If several threads are all waiting on a lock:
      - `notify` will release a randomly chosen waiting thread, which then becomes blocked to contend with the other blocked threads
      - `notifyAll` releases all waiting threads, which all become blocked.

### Streams

- Want to get a list of movies with ratings less than 3:
  1. Iterator version: Implement a filter+mapper that will filter movies for some predicate, and map to movie name:

    ```java

    System.out.println(
      filterMap(movies, m -> m.getRating() < 3, Movie::getName)
    );
    ```

  2. Stream version: Source movies list to a stream and apply a sequence of stream operations:

  ```java

  List<String> badMovies =
    movies.Stream()       //returns an instance of interface type
    .filter(m -> m.getRating() < 3)       //lambda - Intermed op
    .map(Movie::getName)                  //lambda - intermed op
    .collect(toList());                   //terminal op
  System.out.println(badMovies);


  //Breaking it down:
  Stream<Movie> movieStream = movies.stream();
  movieStream = movieStream.filter(m -> m.getRating() < 3);
  Stream<String> movieNameStream = movieStream.map(Movie::getName);
  List<String> movieNameList = movieNameStream.collect(toList());

  ```

  - Stream benefits:  
    - Declarative: Specify what you want to get done, don't worry about how
    - COmposable: Can put together chain of operations to express a complex processing pipeline while keeping code and intention clear
    - Parallelizable: Streams can be run in parallel with a trivial change:
      `movies.parallelStream().filter(...)`

- Terminal operations:
  - Can return primitive, object, or void:

```java

//Count
movies.stream()
  .filter( ... )
  .count();  //returns a long int

//forEach
movies.stream()
  .filter( ... )
  .sorted(comparing(Movie::getName).reversed())   //Returns a comparator that reverse comparison order of comparator on which it is applied
  .map(Movie::getName)
  .forEach(System.out::println);

```

#### Stream sources

- Values, Array, Numerical Range, File, Functions(iterate, generate)
  - Iterate: Takes a seed parameter of type T, and a `UnaryOperator<T>` (special kind of `Function` interface that has same result type as input)
  - Generate: Takes a `Supplier<T>` as a parameter and generates an infinite sequence of type T elements

```java

//Values
Stream<String> gimme =
  Stream.of("...", "fsdfs", "sdfasdf");

gimme.map(String::toUpperCase)
  .forEach(System.out::println);      //yields the 3 above in all caps

//Array
int[] primes = {2,3,5,7,11,13,19,23,29};

IntStream primeStream = Arrays.stream(primes);  //Interface IntStream for streams that hold primitive int values
System.out.println(primeStream.sum());    //Reduction method in IntStream; yields 112

//Numerical range
IntStream
  .rangeClosed(1,10)     //1 through 10 inclusive -- using .range(1,10) gives right open range 1 through 9
  .map(i -> i*i)        
  .forEach(System.out::println);

//File
try{
  Stream<String> lines = Files.lines(Paths.get("file.txt"));
  lines
    .map(line -> line.split(" ").length)
    .forEach(System.out::println);        //yields number of words in each line of file.txt
} catch (IOException e){
  System.out.println(e.getMessage());
}

//Iterate
Stream
  .iterate("*", s -> s + "*")
  .limit(6)
  .forEach(System.out::println);    //prints the * pyramid

//Generate
Stream
  .generate(Math::random)
  .limit(5)
  .forEach(System.out::println);    //Infinite sequence of random numbers capped at 5

//Generate 2
Stream
  .generate(() -> 1);   //infinite stream of 1s

```

#### Useful Stream Operations

```java

//FINDING AND MATCHING
//Find any
System.out.println(
  movies                  //Any 2014 movie in movies list with 5 star rating
    .stream()
    .filter(m -> m.getYear() == 2014 && m.getRating() == 5)
    .map(Movie::getName)
    .findAny()
    .orElse("No match");
);

//Short-circuiting
movies
  .stream()
  .filter(m -> {
      System.out.println("filtering" + m.getName());
      return m.getRating() == 1);
  })
  .map(m -> {
      System.out.println("mapping" + m.getName());
      return m.getName();
  })
  .findAny()
  .ifPresent(System.out::println);

//find first
System.out.println(
  movies
    .stream()
    .filter(m -> m.getRating() == 4)
    .map(Movie::getName)
    .findFirst()
    .orElse("No Match");
);

//Predicate matching
//Any item
System.out.println(
  movies
    .stream()
    .anyMatch(m -> m.getCategory() == Genre.MYSTERY && m.getRating() > 3)
);

//All items match predicate
System.out.println(
  Arrays
    .stream(cars)             //Sample: {"Honda", "Civic", "2016"}, ...
    .map(mmy -> mmy[2])       //year position
    .allMatch(y -> y.equals("2016"))
);

//REDUCE
//Sum (number of words in input file)
try{
  Stream<String> line = Files.lines(Paths.get("file.txt"));
  lines
    .map(line -> line.split(" ").length)
    .reduce(Integer::sum)                           //.reduce takes a BinaryOperator<T> instance parameter
    .ifPresent(System.out::println);
} catch(IOException e){
  System.out.println(e.getMessage());
}

//Product (find factorial of n)
IntStream is = IntStream.rangeClosed(1,n);
int fact = is.reduce(1,(x,y) -> x*y);

//Average rating of all movies
Optional<Integer> opt =
  movies
    .stream()
    .map(Movie::getRating)
    .reduce(Integer::sum);
try{
  System.out.println(opt.get()*1f/movies.stream().count());   //get method returns contained value
} catch (NoSuchElementException e){
  System.out.println("No movies in list");
}

//Averaging with IntStream (same as above)

OptionalDouble optDbl =
  movies
    .stream()
    .mapToInt(Movie::getRating)
    .average();
System.out.println(optDbl.orElse(0));

//flatMap - avg word length in input file
//Have to flatten Stream<String[]> to Stream<String>
try{
  Stream<String> lines = Files.lines(Paths.get("alice.txt"));

  Optional<Double> avg =
  lines
    .map(line -> line.split(" "))
    .flatMap(Arrays::Stream)
    .mapToInt(String::length)
    .average();

  avg.ifPresent(System.out::println);
} catch (IOException e) {
  System.out.println(e.getMessage());
}

//cross-pairs
List<Integer> L1 = Arrays.asList(2,3,7,9);
List<Integer> L2 = Arrays.asList(4,5,8);

L1.stream()
  .flatmap(i -> L2.stream()
                  .map(j -> new int[]{i,j}))
  .forEach(a -> System.out.println(Arrays.toString(a)));

//.toArray()
String[] badMovies =
  movies.stream()
        .filter(m -> m.getRating() < 3)
        .map(Movie::getName)
        .toArray(String[]::new);

//.toArray for IntStream, DoubleStream, LongStream returns an int[], double[], and long[]
```
