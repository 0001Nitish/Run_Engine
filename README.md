# Run_Engine
Clone the Repository: git clone https://github.com/0001Nitish/Run_Engine.git cd

Install Dependencies: Ensure you have Maven installed. Run the following command to install the necessary dependencies: mvn clean install

Database Setup:

Ensure you have a running instance of a database (e.g., MySQL, PostgreSQL).

Update the application.properties file with your database configuration: properties spring.datasource.url=jdbc:postgres://localhost:5432/your_database spring.datasource.username=your_username spring.datasource.password=your_password spring.jpa.hibernate.ddl-auto=update

DDL

-- Database: rule

-- DROP DATABASE IF EXISTS rule;

CREATE DATABASE rule
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'English_India.1252'
    LC_CTYPE = 'English_India.1252'
    LOCALE_PROVIDER = 'libc'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

Run the Application: Use the following command to start the Spring Boot application: mvn spring-boot:run

Access the Application: Open your browser and navigate to http://localhost:8080.

Postman Collection:{"collection":{"info":{"_postman_id":"bcb9244f-64b0-47ad-bd35-b3a1ce66301f","name":"Rule_Engine","schema":"https://schema.getpostman.com/json/collection/v2.1.0/collection.json","updatedAt":"2024-10-18T18:24:41.000Z","createdAt":"2024-10-16T14:21:46.000Z","lastUpdatedBy":"37272584","uid":"37272584-bcb9244f-64b0-47ad-bd35-b3a1ce66301f"},"item":[{"name":"health","id":"515ead4e-2667-43d7-bd52-76002a1ae329","protocolProfileBehavior":{"disableBodyPruning":true},"request":{"method":"GET","header":[]},"response":[],"uid":"37272584-515ead4e-2667-43d7-bd52-76002a1ae329"},{"name":"create","id":"152bc2f6-d309-4fb6-a924-f142202a5a78","protocolProfileBehavior":{"disableBodyPruning":true},"request":{"method":"POST","header":[],"body":{"mode":"raw","raw":"\"age > 30 AND department == 'Sales'\"","options":{"raw":{"language":"json"}}},"url":{"raw":"localhost:8080/api/rules/create","host":["localhost"],"port":"8080","path":["api","rules","create"]}},"response":[],"uid":"37272584-152bc2f6-d309-4fb6-a924-f142202a5a78"},{"name":"rule","id":"e45ca5fe-05a0-4c8a-94fa-69bd6ed9a9d1","protocolProfileBehavior":{"disableBodyPruning":true},"request":{"method":"GET","header":[],"body":{"mode":"raw","raw":"","options":{"raw":{"language":"json"}}},"url":{"raw":"localhost:8080/api/rules/1","host":["localhost"],"port":"8080","path":["api","rules","1"]}},"response":[],"uid":"37272584-e45ca5fe-05a0-4c8a-94fa-69bd6ed9a9d1"},{"name":"evaluate","id":"14af35ac-8a6f-44c6-8a66-c858e9eb5e5b","protocolProfileBehavior":{"disableBodyPruning":true},"request":{"method":"GET","header":[],"body":{"mode":"raw","raw":"[\"age > 30 AND department == 'Sales'\", \"age < 25 AND department == 'Marketing'\"]","options":{"raw":{"language":"json"}}},"url":{"raw":"localhost:8080/api/rules/evaluate","host":["localhost"],"port":"8080","path":["api","rules","evaluate"]}},"response":[],"uid":"37272584-14af35ac-8a6f-44c6-8a66-c858e9eb5e5b"}]}}
