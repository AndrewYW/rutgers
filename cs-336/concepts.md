This document will grow. It lists very basic database concepts which are
important to remember/understand long (forever?) after taking this class.

- Q:Ad Hoc Queries

  -Queries which are written spontaneously by users
  to explore data - unpredicatble from logic standpoint.
  As opposed to predefined queries which power usual front end web user interfaces

Q:Data Independence - Implementation details of data, its location etc does not affect
the queries, the code in general. Database programmer should not have to be aware
of the data implmentation issues.

Q: what are Null Values

Placeholders to denote not existing values, values
which exist but are not known

Q what is Semi-structured Data

Data where values may be tables, sets, text

Q: what is Normalized data

Data in relational tables which have only atomic values
(later we will also introduce other normal forms)

Q: what is Denormalization

Making one large table from smaller tables by Join.
Also creating semistuctured data from flat tables.


Q: what is First normal form

Only atomic values allowed in tables. No sets, no vectors
(Atomic value = indivisible values)

Q what are Entities

Identifiable object with attributes or properties that define their identity

Q: what are Relationships

Association among entities (e.g: x **works at** y, student **enrolls** in course)

Q: what are Attributes

Properties of entities that have value. Exists in domain/range of values.

Q: what is Many to Many relationship?

One entity from A can be associated with more than one from B

Q: what is Many to One relationship

more than 1 entity from A can be associated with at most 1 entity from B.

Q: what are Weak Entities

Entity that can't be uniquely identified by its attributes alone - requires foreign key to create a primary key.

Q: what is a Key

An attribute or collection of attributes that uniquely identifies an entity among an entity set

Q: what does Join do? Outer join - left outer join, right outer join

Fetch data from 2 or more tables, appearing as single set of data.

Right join: returns all rows from right table with matching rows in left table.
Left join: returns all rows from left table with matching rows in right table
Full outer: returns all rows from both tables (left and right combined)

Q: what is meant by Aggregates

Grouping values of multiple rows into a single value
Average, MIN, MAX etc - operators on sets of data

Q: what does Group by do?

Important SQL construct to slice data into partitions.

Q: what are Correlated variables

Two variables perform in a synchronized variable

Q: Nested subqueries

Query in a query

Q: Entity vs Attribute?

Attribute has no attributes, entity does. Entity exist
independently, attribute only in reference to entity


Q: Are all relationships binary?

No, not at all. Relationships like Order between drinker, beer and bar
are inherently ternary (or more) and cannot be decomposed into
sepeare binart relationships.


Q: Query which requires negation (nested or outerjoin)

"Students who do not take databases"

Q: Query which requires self join

"Students who take databases and operating systems"
(Self join: query in which table is compared to itself)

Q: Declarative vs Procedural

Declarative (like SQL) says what not how. Procedural like Java, C++
tells how...provides intructions to the computer step by step.
SQL does not. SQL query optmizer "knows what to do"

Q:Complex Objects

XML, JSON objects, which have values which are sets

Q: Duplicates - when needed when not

Needed for aggregate operators.

Q:Physical vs Logical database layer

Physical - how db is implemented and where it is stored. Logical: what is metadata, entities, relationships,
relational schemes etc.

Q: Scheme vs Instance

Scheme is a "plan" of database - metadata - attributes,
relation names, constraints. Instance - set of tuples,
changes often. Scheme changes rarely.

Q: Difference between NOT IN and NOT EXISTS

See example with nulls. NOT IN sometimes gives unintuitive results
when NULLS are present.

Q: what is a superkey?

Is a subset of attributes which determines all other attributes

Q: what are differences between keys and superkeys?

Each key is a superkey, but not vice versa. Superkeys are supersets of keys in general.
There is usually many more superkeys than keys. Even if there is no functional dependencies
- there is one superkey which is also the only one key - set of all attributes.

Q: what functional dependencies does this instance above satisfy?

A B C
1 0 1
1 1 1


B->A, B->C, A->C, C->A  and all fds entailed (implied), like AB-> C etc.

Q: What are trivial fds?

Fds which are satisfied by every relational instance. X->B where B belongs to X.

Q: what is functional dependency X->A

It a constraint which says that every two tuples which agree on X must also agree on A. It is a many to one relationshop between X and A.
Single value (or no value) for combination of values in X.

Q: Give example of weak entity

Players and Teams, Players are weak entities they need team name to identify a player (number is not enough)

Q: what is objective of normal forms such as BCNF?

A: Avoid Update anomalies and redundancies

Q: What is lossless decomposition?

A: Joining "back" the relations results in original table where they were projected from

Q: why do we care about dependency preserving decompositon?

A: Because if do not preserve dependencies we cannot update locally the relations in decomposition. We would potentially have to join
to check for functional dependcies which are lost in decomposition

Q: What is minimal basis?

It is a set of functional depenencies has no 'redundant dependencies", and each dependency has no "redundant" attributes on the left hand side

Q: Can there be more than one key?

Yes, for example for F={A->B, B->C, C->D, D->A} there will be multiple keys

Q: is every superkey  a key?

A: No, opposite is true though

Q: what is the difference between First Normal form and BCNF

A: they serve different objectives. 1NF states that all values are atomic. BCNF attempts to eliminate reduncacies and update anomalies

Q: why update anomalies are bad?

A: because you may have uninteded deletion  as unwanted side effect of a deletion. Redundancy may lead to logical error
(not updating all copies of a value)

Q: What part of speech is associated with attribute

A: adjective is most common, but can be a noun as well. Numerical values are very common as attributes, much more than as entities

Q: Given m entities and n relations how many relational schemes will be formed?

A: m+n

Q: What is primary key, what are candidate keys?

Candidate keys are simply all keys of a scheme. Primary key is one of these keys selected to organize the data around it
- the choice by database designer/adm. So candidate key is synonymous with the notion of key as defined in power points fds.ppt
on sakai

Q: what is foreign key

A FOREIGN KEY in one table points to a PRIMARY KEY in another table.
It is existence constraint which requires that a value in one table MUST exist as primary key in another.

The table containing the foreign key is called the child table, and the table
containing the candidate key is called the referenced or parent table.


Q: does foreign key have to be primary key in both tables (the referencing table and then referneced table)

No, only in refernced table (parent table). For example Beer name in Sells table is foreign key pointing to beer name in
the Beer table


Q: What is referential integrity

Referential integrity is a property of data which, when satisfied, requires every value of one attribute (column)
of a relation (table) to exist as a value of another attribute (column) in a different (or the same) relation (table).

It is existence constraint, a value in one table has to first exist in another table.

Q: What are assertions used for?

To define consistent states of the database. For example assertion could state that evey beer in beer table has toi be sold
by some bar or liked by some drinker (reverse of foreign key)


Q: what are intergrity constraints

Data integrity is normally enforced in a database system by a series of integrity constraints or rules.
Several types of integrity constraints are an inherent part of the relational data model such as functional dependencies,
foreign key constraints, referential integrity, keys and other assertions. Database instance always has to satisfy integrity constraints.



Q: Why is serializability important and how it is defined

Serializability is a property of *schedules* of transactions. Schedule is serializable it is always equivalent (no matter what
is the initial database instance) to *some* serial schedule. It is important because it gurantees consitency of the
final database state.


Q:  What is conflict serialzability


A schedule is conflict serializable if it can be transformed to serial schedule by flipping non-conflicting
opeartions (preserving order of atomic operations/steps in each transaction)

In other words conflict-serializability is defined by equivalence to a serial schedule (no overlapping transactions)
with the same transactions, such that both schedules have the same sets of respective chronologically
ordered pairs of conflicting operations (same precedence relations of respective conflicting operations).



Q: Can a schedule be serializable but not serial?

Sure. The whole point is to have non-serial schedules (allow concurrency, one transaction "interrupting another)
but still keep consistency. There are plenty of non-serial schedules which are serializable.  





Q: what is isolation in ACID

Isolation is typically defined at database level as a property that defines how/when the changes made
by one operation become visible to other.  The highest level of isolation makes a transaction totally "unaware"
of other transactions which conurrently operate in the system.

Serializable

This is the highest isolation level.

With a lock-based concurrency control DBMS implementation, serializability requires read and write locks (
acquired on selected data) to be released at the end of the transaction.

Repeatable reads

The same data item read twice should have the same value. (like in our examples of sum of total number of drinkers in
3 bars)

Read committed

read committed is an isolation level that guarantees that any data read is committed at the moment it is read.
It simply restricts the reader from seeing any intermediate, uncommitted, 'dirty' read.
It makes no promise whatsoever that if the transaction re-issues the read, it will find the same data;
data is free to change after it is read.

Read uncommitted
This is the lowest isolation level.
In this level, dirty reads are allowed, so one transaction may see not-yet-committed changes made by other transactions.

Q:  What does atomicity mean

An atomic transaction is an indivisible and irreducible series of database operations
such that either all occur, or nothing occurs. ALL OR NOTHING


Q:   What does consistency mean

Consistency in database systems refers to the requirement that any given database transaction m
ust change affected data only in allowed ways.

Any data written to the database must be valid according to all defined rules,
including constraints, cascades, triggers, and any combination thereof.


Q: What are blind writes

Assignment statments which are not preceded by reads.

Q: example of schedule which is not conflict serializable

T1            T2
read(Q)

            write(Q)

write(Q)


Q: When we do not need concurrency control - full concurrency is allowed

All transactions operate on disjoint data sets. All transactions are queries - no updates.

Q:  What is lock?

A lock, as a read lock or write lock, is used when multiple users need to access a database concurrently. This prevents data from being corrupted or invalidated when multiple users try to read while others write to the database. Any single user can only modify those database records (that is, items in the database) to which they have applied a lock that gives them exclusive access to the record until the lock is released. Locking not only provides exclusivity to writes
but also prevents (or controls) reading of unfinished modifications (AKA uncommitted data).

Q: Pessimistic vs Optimistic concurrency control

Pessimistic - assumes things will go wrong, so applies preventive measures like LOCKS. Optimistic. Things will go well
- rarely a problem will occur  (inconsitent database state) than we will deal with it (roll back for example)

Q: What is the negative side effect of locks

Deadlock. Livelock. Unnecessary delays stopping transactions which have to wait for other transactions to release locks.

Q: Locking tables vs locking record fields - what is better?

Locking smaller units of data is less "selfish" - since it restricts other transactions less. But if a transactions operates on
a whole table, it is more economic to just issue one lock than potentially massive number of small locks which have to be recorded somewhere (lock table).
So there is an overhead of too many locks.

Q: What are the main differences between NoSQL and relational databases?

NoSQL are usually schemeless, store semistructured, nested objects, support no joins, ACID is not supported.

Q: What are NoSQL good for?

Where performance and scalability are key concern. Massive web applications with heavy traffic. Data which is semistructured (i.e. not rigid
structure of relations, nesting, complex objects (Fowler's aggregates), text.

Q: What are Relational Databases good for?

Lots of exploratory queries, concurrency control (ACID), multiple levels of isolation, security,  Structured data. Ad hoc queries.

Q: What is CAP theorem?

In presence of network partitions you cannot acheive both consistency and availability. And on the web we almost always chose availability.
Worst case (as Fowler said) we apologize later.

Q: What are key value stores?

Sets of values (associative arrays) which can be complex objects which are indexed by a key.

More on NoSQL vs SQL databases (very nice short article)

https://msdn.microsoft.com/en-us/magazine/hh547103.aspx


An Alternative, Not a Replacement, for Relational Databases

"The NoSQL and document databases provide an alternative to relational databases, not a replacement.
Each has its place, and they simply provide you with more options from which to choose.
But how to choose?
An important gauge is the Consistency, Availability and Partition Tolerance (CAP) theorem.
It says that when working in distributed systems, you can only have two of the three guarantees (the C, the A or the P),
so you have to pick what’s important. If Consistency is the most critical, then you need to go with a relational database.


A common example of where Consistency would be the most important guarantee is in a banking application or
perhaps one that runs a nuclear facility. In these scenarios, it’s critical that every single piece of data is accounted
for at every moment. If someone makes a withdrawal, you really need to know about it when you’re looking at his account balance.
Therefore, you’ll probably want a relational database with a high level of control over its transactions.
A term you’ll hear frequently is “eventual consistency,” or as expressed on the RavenDB site: “better stale than offline.”
In other domains, eventual consistency is sufficient.
It’s OK if data you’re retrieving isn’t up-to-the-millisecond accurate.

Perhaps, then, it’s more important that some version of the data is available, rather than waiting for all of the transactions to catch up. This is related to the A (Availability) in CAP, which is focused on server uptime. Knowing that you’ll always have access to the database takes precedence and is a huge benefit to database performance (that is, document databases are fast!). You’ll find that the P, Partition Tolerance,
is also important to the document databases, especially when scaling horizontally"

Q: What is more important for shopping applications? Consistency or Availability?
A: Availability

Q: What is precision, recall in information retrieval?


Q: TF and IDF definitions and explanations

Q: What would be IDF value of a term which appears in all documents?
