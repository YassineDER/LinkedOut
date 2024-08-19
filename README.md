# Getting Started

## Part 1: Cloning each sub-directory (Optional)

The project is divided into two sub-directories: `backend` and `frontend`. You can clone each sub-directory to work in separate directories by running:

```bash
git clone --depth 1 --branch dev --no-checkout git@github.com:YassineDER/LinkedOut.git
cd LinkedOut
git sparse-checkout init --cone
git sparse-checkout set backend frontend
git checkout dev
```
You can then start to work on each sub-directory separately and commit your changes like you would do with a normal git repository.

**Note:** Pushing from any of the sub-directories (including the root) will trigger the CI/CD pipeline and deploy the whole project. If you want to avoid this, add `[skip ci]` to the end of your commit message.

# Part 2: Setup the database

The project uses PostgreSQL as the database. Its required to have it ready before running the backend. You can install it either with Docker (Recommanded) or by downloading it from the official website.

```bash
docker run --name linkedout-db -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=securecapita -p 5432:5432 -d postgres:14-alpine
```

The credentials must be respected as they are hardcoded in the backend.

## Part 3: Running the backend

The backend is a Spring Boot application. You can run it in dev profile by creating a bash script called `start`. This script is a bit treaky since it contains sensitive environment variables, thats why he's included in [.gitignore](.gitignore). 

A ready-to-use script can be requested from the project owner to skip the steps below.

Before creating your own start script, you need to:

- Sign up for a free account on [Mailtrap](https://mailtrap.io/), enable Email Testing, get your SMTP credentials (username and password) from your created inbox and set them as `MAIL_USER` and `MAIL_PASS` environment variables. You will receive each email sent by the application in the Mailtrap inbox but won't be able to send emails to real addresses since this is just for developpement environments
- Generate a JWT secret key with 32 characters and set it as `JWT_SECRET` environment variable. You can use a random string generator like [this one](https://jwtsecret.com/generate).
- Create a new project on the Google Cloud Console and enable the [Custom Search API](https://console.cloud.google.com/apis/library/customsearch.googleapis.com). Then, [create a new API key]((https://support.google.com/googleapi/answer/6158862)) if not already created and set it as `GOOGLE_API_KEY` environment variable in the script. 
- Create a new file called `start` (with no extension) in the `backend` directory like the following:

```bash
#!/bin/bash

export DB_URL=jdbc:postgresql://localhost:5432/securecapita
export DB_PASS=postgres
export DB_USER=postgres
export MAIL_USER="<Mailtrap username>"
export MAIL_PASS="<Mailtrap password>"
export JWT_SECRET="<JWT secret>"
export GOOGLE_API_KEY="<Google API key>"

mvn clean spring-boot:run -DskipTests -Dspring.profiles.active=dev
```

Make sure to make the script executable, and then run it:

```bash
chmod +x start
./start
```

## Part 4: Running the frontend

Make sure you have Angular CLI installed globally. If not, you can install it by running:

```bash
npm install -g @angular/cli
```

Then, navigate to the `frontend` folder and install the dependencies:

```bash
npm install
```

Finally, you can start the development server by running:

```bash
ng serve
```

**Note:** The backend must be running before starting the frontend.

# Part 5: Accessing the application

You can access the application by navigating to [http://localhost:4200](http://localhost:4200) in your browser. One admin account is created by default if no users are found in the database:

- Email: `admin@example.com`
- Password: `12345678`


