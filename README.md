# Exercise35_1 JavaFX App

This JavaFX application was created to complete Programming Exercise 35.1 from the Liang Java textbook.

## Description

The application demonstrates the performance difference between using **batch updates** and **non-batch updates** when inserting 1,000 randomly generated records into a MySQL database.

### Features:
- Connect to a local MySQL database.
- Insert 1,000 records using:
  - `addBatch()` and `executeBatch()`
  - individual `executeUpdate()` calls
- Display the elapsed time for each operation.

## Technologies Used

- Java 17
- JavaFX
- MySQL 8
- MySQL JDBC Connector
- IntelliJ IDEA

## Database Setup

Ensure MySQL is installed and running. Create the database and table using the following SQL:

```sql
CREATE DATABASE Exercise35_1;
USE Exercise35_1;

CREATE TABLE Temp (
  num1 DOUBLE,
  num2 DOUBLE,
  num3 DOUBLE
);
```

## How to Run

1. Clone or download the project.
2. Open the project in IntelliJ IDEA.
3. Make sure the `mysql-connector-java` library is added to the project.
4. Update database connection details in `DBUtil.java` if needed.
5. Run `Main.java`.
6. Use the GUI to:
   - Connect to the database
   - Perform batch or non-batch inserts
   - View elapsed times for each method

## Author
Jason Hollin  
