# MaalGaadi - Backend

## Tech Stack
Java, Spring / Spring Boot, JPA, Hibernate, MySQL, Linode VPS, SendGrid API for Emails.

## Features
- Role based authentication.
- Password Encryption using BCrypt.
- JWT Token Authorization.
- Login via OTP.
- Secure and Protected Endpoints.
- MySQL relational database.
- Sorting and Pagination implemented via JPA.
- Hosted on a Linux VPS using NGINX reverse proxy.

## API Endpoints

Auth APIs (Permitted for all)
- /api/v1/auth/dealer/signup
- /api/v1/auth/driver/signup
- /api/v1/auth/driver/login
- /api/v1/auth/dealer/login
- /api/v1/auth/get-otp?username={username}
- /api/v1/auth/driver/login-otp
- /api/v1/auth/dealer/login-otp

Dealer APIs (Can only be access using JWT token of a DEALER user)
- /api/v1/dealer/drivers
- /api/v1/dealer/drivers-route
- /api/v1/dealer/book
- /api/v1/dealer/drivers-state

Driver APIs (Can only be access using JWT token of a DRIVER user)
- /api/v1/driver/bookings

## How to run? (Linux / MacOS)

### Install JDK 11

### Build JAR File

```
cd <project root folder>

./mvnw clean package  
```

### Create a Shell Script and set up environment variables

```
#!/bin/sh

nohup java \
-DDB_NAME=maal_gaadi \
-DDB_HOST=localhost \
-DDB_PORT=3306 \
-DDB_USER=root \
-DDB_PASSWORD=<db password here> \
-DSENDGRID_KEY=<send grid api key here> \
-jar ./target/maalgaadi-backend-0.0.1-SNAPSHOT.jar > output.log &

echo $! > save_pid.txt
```

Save this file in the Project Root directory with .sh extension.

### Run the backend using the shell script

```
chmod +x <file name>.sh

./<file name>.sh
```

The application will now run on LOCALHOST port 8080.