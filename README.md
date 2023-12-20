# Employee Management APP

This Spring Boot application serves as an Employee Management API, implementing CRUD operations for employee data. 
Utilizing a JSON file as its database, it provides a lightweight and easy-to-use solution for managing employee records. 
The app includes Swagger integration for seamless API documentation, accessible at http://localhost:8080/swagger-ui.html. 
With a focus on simplicity and versatility, it is an ideal starting point for projects requiring a file-based database solution. 
Deployable on AWS, the project offers a streamlined setup process and can be customized to meet specific requirements, 
making it a valuable resource for developers seeking a robust employee management solution.

## Prerequisites

Make sure you have the following installed on your machine:

- Java Development Kit (JDK) - version 8 or higher
- Maven

## Getting Started

### Clone the Repository

```bash
git clone https://github.com/Anshul0707/employee-management.git
cd employee-management
```

## Build and Run Locally

```mvn clean install
mvn clean install
java -jar target/employee-management.jar
```

The application will start on http://localhost:8081.

## Deploy on AWS

### Launch an EC2 Instance:

- Open the AWS Management Console and navigate to the EC2 dashboard.
- Launch an EC2 instance with the desired specifications.
- Note the public IP or DNS of your EC2 instance.

### SSH into the EC2 Instance using Termius:

- Open Termius and create a new SSH host entry for your EC2 instance.
- Connect to the EC2 instance using SSH.

### Transfer the JAR file to EC2:

- Use SCP to transfer the JAR file to your EC2 instance:

```
scp -i /path/to/your/private-key.pem target/employee-management.jar ec2-user@your-ec2-ip:/path/on/ec2/

```

### Run the JAR on EC2:

- SSH into your EC2 instance and run the JAR file:

```
scp -i /path/to/your/private-key.pem target/employee-management.jar ec2-user@your-ec2-ip:/path/on/ec2/

```

The application will now be accessible on http://your-ec2-ip:8081.

## API Endpoints

### Get All Employees

**Endpoint:** `GET /employees`

**Description:** Retrieve a list of all employees.

### Get Employee by Id

**Endpoint:** `GET /employees/{id}`

**Description:** Retrieve details of a specific employee based on the provided ID.

### Create Employee

**Endpoint:** `POST /employees`

**Description:** Create a new employee.

**Request Body:**

```
{
  "name": "John Doe",
  "designation": "Software Engineer"
}
```

### Update Employee

**Endpoint:** `PUT /employees/{id}`

**Description:** Update details of a specific employee based on the provided ID.

**Request Body:**

```
{
  "name": "John Doe",
  "designation": "Software Engineer"
}
```

### Delete Employee

**Endpoint:** `DELETE /employees/{id}`

**Description:** Delete a specific employee based on the provided ID.

## Swagger Integration

This project includes Swagger for API documentation. You can access the Swagger UI by navigating
to http://localhost:8081/swagger-ui.html after starting the application locally

## Contributing

Contributions are welcome! Fork the repository and submit a pull request.

## License

This project is licensed under the MIT License - see the LICENSE file for details.