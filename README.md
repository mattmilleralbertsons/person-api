# Install Directions
# All Platforms
* Install JDK-17 or higher

# Windows
```
java -version 
//Verify you are on JDK17

cd c:\path\to\person-api
mvnw.cmd clean install
java -jar c:\path\to\person-api\target\person-api-0.0.1-SNAPSHOT.jar
```

# Unix/Mac
```
java -version 
//Verify you are on JDK17

cd /path/to/person-api
./mvnw clean install
java -jar ./target/person-api-0.0.1-SNAPSHOT.jar
```

# Curl to create a new person
```
curl --location 'localhost:8080/people' \
--header 'Content-Type: application/json' \
--data '{
    "firstName": "Monika",
    "lastName": "Smith"
}'
```

# Curl to get person
```
curl --location 'localhost:8080/people/xxxxxx-xxxx-xxxxxx-xxxxxx'
```

# IDE
Get the code in your favorite IDE so we can do some live coding.