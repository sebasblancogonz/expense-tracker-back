# ExpenseTracker
This is a service to track your expenses and eventually will evolve to keep track of your financial health.

You will need Java 17 and MongoDB to run it.

To run MongoDB in a docker instance fast and easy, run this command:

docker run -d --name local-mongo -p 27017:27017 -e MONGO_INITDB_ROOT_USERNAME=admin -e MONGO_INITDB_ROOT_PASSWORD=admin -e MONGO_INITDB_DATABASE=expenses mongo

Then you can run the application.
For request examples and available endpoints, you can check the swagger that will be created in the following endpoint:
http://localhost:8080/swagger-ui/index.html