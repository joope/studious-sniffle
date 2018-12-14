# studious-sniffle
Push architecture test for distributed systems course

You'll need to download the following JAR files from Maven Central:

    RabbitMQ Java Client
    SLF4J API
    SLF4J Simple

Copy those files in your working directory, along the tutorials Java files.

To compile you only need the Rabbitmq Java Client jar on the classpath.

To run them you'll need all the dependencies, see examples below.

Note: If you're on Windows, use a semicolon instead of a colon to separate items in the classpath.

    You can set an environment variable for the jar files on the classpath e.g.

     $ export CP=.:amqp-client-4.0.2.jar:slf4j-api-1.7.21.jar:slf4j-simple-1.7.22.jar
     $ java -cp $CP Send

    or on Windows:

     > set CP=.;amqp-client-4.0.2.jar;slf4j-api-1.7.21.jar;slf4j-simple-1.7.22.jar
     > java -cp %CP% Send


