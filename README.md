# spring-oauth2

## Authentication

### `grant_type=password`

To get an access token:

    curl -v -XPOST -u client:secret "http://localhost:8080/oauth/token?grant_type=password&username=john&password=12345&scope=read"

### `grant_type=authentication_code`

Open in the browser and login:

    http://localhost:8080/oauth/authorize?response_type=code&client_id=client&scope=read

You'll be redirected to a non-existing service but you can fetch the code from the URL.
Use the code here to get an access token:

    curl -v -XPOST -u client:secret "http://localhost:8080/oauth/token?grant_type=authorization_code&scope=read&code={CODE}"

### `grant_type=client_credentials`

To get an access token:

    curl -v -XPOST -u client:secret "http://localhost:8080/oauth/token?grant_type=client_credentials&scope=info"

### `grant_type=[password,refresh_token]`

To get an access token and a refresh token:

    curl -v -XPOST -u client:secret "http://localhost:8080/oauth/token?grant_type=password&username=john&password=12345&scope=read"

### JWT (symmetric and asymmetric key scenarios)

To get an access token and a refresh token:

    curl -v -XPOST -u client:secret "http://localhost:8080/oauth/token?grant_type=password&username=john&password=12345&scope=read"

## Troubleshouting

### ClassNotFoundException: org.apache.maven.wrapper.MavenWrapperMain

Run:

    /realpath/to/mvn -N io.takari:maven:wrapper

`/realpath/to/mwn` because on my system I have:

    alias mvn=mvn-or-mvnw

