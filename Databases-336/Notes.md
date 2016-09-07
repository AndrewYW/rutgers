# Databases 336
- Tomasz Imielinski
  - timielinski@gmail.com

# Class content
- Weeks 1/2 - Intro, SQL
- Week 3 - Data modeling, Tables, Queries
- Week 4 - Functional dependencies and decompositions
- Week 5 - Decompositions and Normal Forms
- Week
## Teaching method
- Learn by doing

## Grading
- Projects - 50%
- Quizzes/exams - 25%
- Final job interview - 15%
- Homeworks - 10%
- Extra credit - Best work, selection for presentation, great job presenting
- Circle of trust - Distinguished class participation

## Quizzes
- SQL, Data modeling, relational theory, transaction, IR

## Mini quizzes
- Real movie reviews
  - Search and rank according to cosine similarity and TFIDF
- Discover patterns in data
- Mongo DB project

### Textbook reference
- Garcia Molina, Ullman, Widom - "Database Concepts"

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

## What's new
-
