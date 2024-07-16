# germedica file upload Service API

Germedica file upload Service API is a Spring Boot-based application that upload csv file and provide access to content via REST endpoints
## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

Before running the application, ensure you have the following installed:

- [Docker](https://www.docker.com/get-started)
- [Docker Compose](https://docs.docker.com/compose/install/)

### Installing and Running with Docker

1. **Clone the Repository**

    ```bash
    git clone https://github.com/abhinav-jain09/gerimedica.git
    cd gerimedica
    ```

2. **Build the Docker Image**

   To build the Docker image for the application, run the following command:

    ```bash
    docker build -t gerimedica .
    ```

   This command builds a Docker image named `beam` using the Dockerfile in the current directory.

3. **Running the Application with Docker Compose**

   Once the image is built, you can run the application using Docker Compose:

    ```bash
    docker-compose up
    ```

   This command starts all services defined in your `docker-compose.yml` file. The application should now be running and accessible.

## API Endpoints

The application exposes the following endpoints:
### Endpoints

#### 1. Upload CSV File

- **URL**: `/items/upload`
- **Method**: `POST`
- **Content-Type**: `multipart/form-data`
- **Description**: Uploads a CSV file and stores the data in the H2 database.
- **Postman Setup**:
   - Set the method to `POST`.
   - Set the URL to `http://localhost:8080/items/upload`.
   - In the **Body** tab, select `form-data`.
   - Add a key named `file` of type `File`, and upload the CSV file.

#### 2. Fetch All Data

- **URL**: `/items`
- **Method**: `GET`
- **Description**: Retrieves all data from the H2 database.
- **Postman Setup**:
   - Set the method to `GET`.
   - Set the URL to `http://localhost:8080/items`.

#### 3. Fetch Data by Code

- **URL**: `/items/{code}`
- **Method**: `GET`
- **Description**: Retrieves data by code from the H2 database.
- **Path Variable**: `code` - the unique code of the item.
- **Postman Setup**:
   - Set the method to `GET`.
   - Set the URL to `http://localhost:8080/items/{code}`.
   - Replace `{code}` with the actual code value.

#### 4. Delete All Data

- **URL**: `/items`
- **Method**: `DELETE`
- **Description**: Deletes all data from the H2 database.
- **Postman Setup**:
   - Set the method to `DELETE`.
   - Set the URL to `http://localhost:8080/items`.



## Swagger API Documentation -- due to time constraint I have not configure  this

You can access the Swagger UI for API documentation at:

`http://localhost:8080/germedica/webjars/swagger-ui/index.html`

This UI provides an interactive way to explore and test the API endpoints.

## Testing with Postman

A Postman collection is available for easier testing of the API endpoints. You can import the collection into Postman using the provided JSON file:

1. Open Postman.
2. Click on the 'Import' button at the top left.
3. Select 'Raw text' and paste the content of the `UploadServicePostman-collection.json` file.
4. Click 'Continue' and then 'Import'.

## Built With

- [Spring Boot](https://spring.io/projects/spring-boot) - The framework used
- [Maven](https://maven.apache.org/) - Dependency Management
- [Docker](https://www.docker.com/) - Containerization platform

## Authors

- Abhinav Jain


