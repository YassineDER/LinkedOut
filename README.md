# Getting Started

![Authentication home page - LinkedOut](https://ax0judwwk3y8.objectstorage.eu-paris-1.oci.customer-oci.com/n/ax0judwwk3y8/b/static/o/authPage.png)

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
docker run --name linkedout-db -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=securecapita -p 5432:5432 -v linkedout-db:/var/lib/postgresql/data -d postgres:14-alpine
```

The credentials must be respected as they are hardcoded in the backend.

## Part 3: Running the backend

The backend is a Spring Boot application. You can run it in dev profile by creating a bash script called `start` in the `backend` folder. This script is a bit treaky since it contains sensitive environment variables, thats why he's included in [.gitignore](.gitignore). 

### **A ready-to-use script can be requested from the project owner to skip this part.**

Before creating your own start script, you need to:

- Make sure JDK 17 or higher is installed and set as the default Java version.
- Install [Maven](https://maven.apache.org/download.cgi) if not already installed.
- Sign up for a free account on [Mailtrap](https://mailtrap.io/), enable Email Testing, get your SMTP credentials (username and password) from your created inbox and set them as `MAIL_USER` and `MAIL_PASS` environment variables. You will receive each email sent by the application in the Mailtrap inbox but won't be able to send emails to real addresses since this is just for developpement environments.

![SMTP Credentials example - Mailtrap](https://ax0judwwk3y8.objectstorage.eu-paris-1.oci.customer-oci.com/n/ax0judwwk3y8/b/static/o/mailtrap.png)

- Generate a JWT secret key with 32 characters and set it as `JWT_SECRET` environment variable. You can use a random string generator like [this one](https://jwtsecret.com/generate).
- Create a new project on the Google Cloud Console and enable the [Custom Search API](https://console.cloud.google.com/apis/library/customsearch.googleapis.com). Then, [create a new API key]((https://support.google.com/googleapi/answer/6158862)) if not already created and set it as `GOOGLE_API_KEY` environment variable in the script.
- The OCI API KEY is requested from the project owner, for obvious reasons.
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
export OCI_USER="ocid1.user.oc1..aaaaaaaanrltas6ynxfmfobvyapd5mxktmeql6qlb6x3uz233kdb6r27extq"
export OCI_FINGERPRINT="b2:2e:fc:6c:92:81:8f:29:7f:cd:58:96:65:d7:b1:60"
export OCI_TENANCY="ocid1.tenancy.oc1..aaaaaaaaa4whtfd2gnpqw3fg2hcmbzchuljqk4ygzplatmtmsm5p3folyxiq"
export OCI_KEY_B64="<OCI API KEY in base64 format" # Requested from the project owner

mvn clean spring-boot:run -DskipTests -Dspring.profiles.active=dev
```
**All the env varaibles are mandatory.**

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


