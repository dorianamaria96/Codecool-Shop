# CodecoolShop - Ecommerce Book App
This is an ecommerce shop app for books made in Java, using Maven as the build tool, Thymeleaf as the templating engine, and PostgreSQL as the database. The app also uses HTML, CSS, and JavaScript for the front-end.

![codecool-shop]("codecool-shop.png")

## Features
The app has the following features:

- User login and session management
- Filtering of books by publisher or category
- Adding products to cart
- Viewing cart contents
- The app uses JDBC for manipulating data in the database using the DAO pattern. SQL is used for creating tables and inserting data into the database.


## Requirements
To build and run the app, you need the following:

- Java 11
- Apache Maven
- PostgreSQL

## Setup
To set up the app, follow these steps:

- Clone the repository:

- Create a PostgreSQL database named "codecoolshop"

- Update the database configuration in the aconnection.properties file from the resource folder

- Set up the Run Configuration, by choosing Maven, where you pick the project folder as a "working directory" and for the "Run" command line section choose "jetty:run"

- Before starting the application, run the sql files from the "sql" directory, where you choose your new database as the Target Data Source, in this order: 'init_db.sql', and then 'data.sql'

- Now you are ready to try the App

The app should now be running at http://localhost:8080.
