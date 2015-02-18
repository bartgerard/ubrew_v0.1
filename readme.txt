# README v0.0.1

STEP-BY-STEP BOOTSTRAP BILL

1. Start your DataBase if required.
2. Configure your application server.
   a. Register your database in the server.xml file under GlobalNamingResources.
   b. Add the required libraries for your database to the application server lib folder.
3. Start your application server.
4. Start the core modules.
   a. userService
   b. translationService
   ...
5. Start your webapplication.

COMMON EXCEPTIONS

1. No Current Session
   a. Add @Transactional to the method.
