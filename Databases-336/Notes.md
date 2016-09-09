# Lecture 1 - Sept 7, 2016
## When do you need a database?
- Lots of data
  - Not necessarily
  - Can work with small sets of data as well
- Ad hoc queries
  - Cannot predict what kind of query will be asked
    - Ex. What kind of item are you looking for on Amazon
- Concurrent transactions

## New revolution
- SQL and NoSQL Databases
- Relational and Polyglot databases
- Web changed everything!

## Interesting stuff about databases
- Used to be about boring stuff
  - Employee records
  - Bank records
  - etc.
- Now the field covers all largest sources of data, with many new ideas
  - Web search
  - Data mining
  - Scientific and medical databases
  - Integrating information

## Big Picture
- Database field undergoing transformation
- E.F Codd (Relational model of data, 1970) used to be bible of databases for 30+ years
  - Future users of data banks must be protected from having to know how the data is organized
  - Activities of user at terminals and application programs should be unaffected when internal data representation changes, and even some external reps
  - Existing noninferential formatted data systems provide users with tree structures files or more general network models of data
  - Section 1: inadequacies of above models are discussed. Introduce n-ary relations, normal form for database relations, and concept of universal data sublanguage
  - Section 2: Certain operations on relations are discussed, applied to problems of redundancy and consistency in user's model

## "Commandments" of relational databases
- Data independence
  - Data representation does not affect front end of application
- Data normalization
  -
- Data-centric programming languages - called **query languages**
- Programmer productivity ! SQL
- Declarative, not procedural

## .. are seriously questioned
- NoSQL

## History goes in circles
- Codd criticized pre 1970 databases for lack of clear query languages, data independence, and de-normalized data
  - This is what NoSQL databases are

## What's new
- High performance, web demands
- Hundreds/thousands of simple queries per second
- Data is complex, lots of text, semi structured data, structured data

## So here goes
- SQL - in web applications, no need for ad hoc queries
- Programmer productivity doesn't matter as much

## Code, not Codd, is king
- NoSQL - coding, both on physical layer (ex. managing clusters, indexing, hashing along with 'logic')
  - No more Codd's separation of logical and physical layers of data

## SQL Query snippet

```SQL
SELECT s.bar FROM sells s JOIN beer b ON s.beer = b.name
  WHERE b.manf = X AND
    EXISTS( SELECT * FROM frequents f
      JOIN likes l ON l.drinker = f.drinker JOIN beer
      b1 ON b1.name = l.beer
      WHERE f.bar = s.bar AND b1.manf = b.manf)
```

- All bars that serve a beer from manufacturer X and are frequented by at least one person that likes a beer from that manufacturer.


# Lecture 2 - Intro to SQL - Sept. 9, 2016

## Why SQL?

- SQL is a high level language
  - "What to do", "not how to do it"
  - Avoid data manipulation details needed in procedural languages like Java/C++

## Select-From-Where Statements

- **SELECT** desired attributes
  - What you want to see
- **FROM** one or more tables
  - Which tables
- **WHERE** condition about tuples of tables
  - How you filter

### Running example

- All SQL queries will be based on this schema:
  - Beers(*name*, manf)
  - Bars(*name*, addr, license)
  - Drinkers(*name*, addr, phone)
  - Likes(*drinker*, beer)
  - Sells(*bar*, *beer*, price)
  - Frequences(*drinker*, *bar*)

### Example

- Using **Beers(name, manf)**, what beers are made by Anheuser-Busch?

```SQL

SELECT name
FROM beers
WHERE manf = 'Anheuser-Busch'

```
## * in SELECT clauses

- All attributes of this relation

## Expressions in SELECT Clauses

- Any expression that makes sense can appear as element of SELECT clause
- Using Sells(bar, beer, price):

```SQL
SELECT bar, beer,
  price*114 AS priceinYen
FROM Sells;
```

## Complex conditions WHERE Clause

- Boolean operators **AND**, **OR**, **NOT**
- Comparisons **=**, **<>**, **<**, **>**, **<=**, **>=**
  - And many other operators with boolean-valued results

## Complex conditions

- Using Sells(bar, beer, price), find price Joe's Bar charges for Bud:

```SQL

SELECT price
FROM Sells
WHERE bar = 'Joe's Bar' AND beer = 'Bud';

```

## Patterns

- Using Drinkers(name, addr, phone) find the drinkers with exchange 555:

```SQL

SELECT name
FROM Drinkers
WHERE phone LIKE '%555-____';

```

## Multirelation Queries

- Interesting queries often combine data from more than one relation
- Can address several relations in one query by listing in FROM clause
- Distinguish attributes of same name by "<relation>.<attribute>"

## Example: Joining two relations

- Using relations Likes(drinker, beer) and Frequents(drinker, bar) find the beers liked by at least one person who frequents Joe's Bar

```SQL
SELECT beer
FROM Likes, Frequents
WHERE bar = 'Joe's bar' AND Frequents.drinker = Likes.drinker;
```

### Self Join example

- From Beers(name, manf) find all pairs of beers by the same manufacturer
  - No pairs like (Bud, Bud)
  - Produce pairs in alphabetic order, e.g: (Bud, Miller)

```SQL
SELECT b1.name, b2.name
FROM Beers b1, Beers b2
WHERE b1.manf = b2.manf AND b1.name < b2.name;
```

## Subqueries

- Parenthesized **SELECT-FROM-WHERE** statements can be embedded as a value in a number of places, including **FROM** and **WHERE** clauses

- Find beers liked by at least one person who frequents Joe's Bar:

```SQL
SELECT beer
FROM Likes, (SELECT drinker
             FROM Frequents
             WHERE bar = 'Joe s bar')JD
WHERE Likes.drinker = JD.drinker;
```


 ##  Single-Tuple Subquery

 - Using Sells(bar, beer, price) find bars that serve Miller for same price Joe charges for Bud:

```SQL
SELECT bar
FROM Sells
WHERE beer = 'Miller' AND price = (SELECT price
                                   FROM   Sells
                                   WHERE bar = 'Joe's Bar' AND beer = 'Bud');

```
