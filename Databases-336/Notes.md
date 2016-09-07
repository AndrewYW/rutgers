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
