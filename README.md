# App

SPA with Angular, Spring Boot, H2.

## Development server

To start a local development server, run:

```bash
java -jar be/target/be-1.0-SNAPSHOT.jar
```

```bash
mvn spring-boot:run
```

Once the server is running, open your browser and navigate to `http://localhost:8080/`.

## Building

To build the project run:

```bash
mvn clean install
```

Get all projects:
```bash
curl -H "Content-Type: application/json" http://localhost:8080/api/projects/all
```
Get project:
```bash
curl -H "Content-Type: application/json" http://localhost:8080/api/projects/603646ee-8780-4c83-9b33-aa895b37d5c6
```

Save project:
```bash
curl -H "Content-Type: application/json" http://localhost:8080/api/projects -d '{
"name": "exampleProject",
"description": "example desc",
"tasks": [
    {
        "name": "exampleTask1",
        "description": "example desc 1"
    },
    {
      "name": "exampleTask2",
        "description": "example desc 2"
    }
  ]
}'
```

Update project:
```bash
curl -X PUT -H "Content-Type: application/json" http://localhost:8080/api/projects -d '{
"name": "exampleProjectUpdated",
"description": "example desc",
"tasks": [
    {
        "name": "exampleTask1",
        "description": "example desc 1"
    },
    {
        "name": "exampleTask2",
        "description": "example desc 2"
    }
  ]
}'
```

Delete project:
```bash
curl -X DELETE -H "Content-Type: application/json" http://localhost:8080/api/projects/314d8cc7-a2d5-4f0f-b9fb-db984bb3d4e4
```
