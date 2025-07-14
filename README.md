# servlet-project

A simple web application built using Java Servlet (J2EE) following the MVC architecture, connected to a MySQL database.

## Project Structure

- `controller/` — Servlet controllers
- `dao/` — Data Access Objects for DB operations
- `model/` — JavaBeans / POJOs
- `WebContent/` — JSP pages

## Technologies & Dependencies

- Java Servlet API
- JSP
- JSTL 1.2 ([Download](https://mvnrepository.com/artifact/javax.servlet/jstl/1.2))
- MySQL Connector/J ([Download](https://dev.mysql.com/downloads/connector/j/))
- Apache Tomcat
- MySQL Server

## How to Run

1. Install Apache Tomcat
2. Download JSTL and MySQL Connector JARs, and place them in `WebContent/WEB-INF/lib/`
3. Import this project into Eclipse (or your IDE)
4. Configure Tomcat server in your IDE
5. Run the project on server
6. Access via `http://localhost:8080/MyServletProject/`

## Notes

This project was developed as part of my university coursework to practice servlet-based web applications with database interaction using MVC pattern.

