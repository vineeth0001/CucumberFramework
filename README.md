🔧 Automation Testing Framework
-- This project is a robust, scalable automation testing framework built with a modular design. It incorporates key industry-standard tools and patterns to ensure high maintainability and extensibility.

🚀 Key Features
-- Logging with Log4j2
-- Integrated log4j2 for advanced logging capabilities. Custom logging behavior is managed via:

-- log4j2.xml configuration file for structured logging control

-- A custom Log4j2Filter class that extends the Log4j2 Filter class for dynamic log filtering

Data-Driven Testing
-- The framework supports data-driven testing using external data sources like Excel or CSV, making it easy to run the same test with multiple sets of data.

Cucumber BDD Support
-- Integrated Cucumber for behavior-driven development:

-- Clean separation of feature files and step definitions

-- Hooks for managing setup and teardown logic

-- Tag-based test execution support

Cucumber Reports
-- Maven dependencies are used to generate comprehensive Cucumber reports for better test visibility and reporting.

Maven Build Tool
-- Uses Maven for dependency management and build lifecycle management.

Hooks & Utilities
-- Custom hooks are defined to manage browser lifecycle, reporting setup, and failure handling (like screenshots on failure).

🛠 Tech Stack
Java
Maven
Cucumber
Log4j2
JUnit/TestNG (based on your choice)

Apache POI / OpenCSV (for data-driven capabilities)
