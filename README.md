# spring-oauth2

## Authentication server

## Create user

    http POST http://localhost:8080/users username=admin password=changeme authority=write

## Create client

    http POST http://localhost:8080/clients clientId=client clientSecret=secret scope=write

### `grant_type=password`

To get an access token:

    curl -v -XPOST -u client:secret "http://localhost:8080/oauth/token?grant_type=password&username=john&password=12345&scope=read"
    http -a client:secret POST localhost:8080/oauth/token grant_type==password username==john password==12345 scope==read

### `grant_type=authentication_code`

Open in the browser and login:

    http://localhost:8080/oauth/authorize?response_type=code&client_id=client&scope=read

You'll be redirected to a non-existing service but you can fetch the code from the URL.
Use the code here to get an access token:

    curl -v -XPOST -u client:secret "http://localhost:8080/oauth/token?grant_type=authorization_code&scope=read&code={CODE}"

### `grant_type=client_credentials`

To get an access token:

    curl -v -XPOST -u client:secret "http://localhost:8080/oauth/token?grant_type=client_credentials&scope=info"
    http -a adviceClient:adviceSecret POST "http://localhost:8080/oauth/token?grant_type=client_credentials&scope=advice"

### `grant_type=[password,refresh_token]`

To get an access token and a refresh token:

    curl -v -XPOST -u client:secret "http://localhost:8080/oauth/token?grant_type=password&username=john&password=12345&scope=read"

### JWT (symmetric and asymmetric key scenarios)

To get an access token and a refresh token:

    curl -v -XPOST -u client:secret "http://localhost:8080/oauth/token?grant_type=password&username=john&password=12345&scope=read"

To get the public key:

    curl -u client:secret "http://localhost:8080/oauth/token_key"

## Asymmetric keys

Generation:

    keytool -genkeypair -alias ssia -keyalg RSA -keysize 2048 -keypass ssia123 -keystore ssia.jks -storepass ssia123

Get public key:

    keytool -list -rfc --keystore ssia.jks | openssl x509 -inform pem -pubkey

## Resource server

Access resource with token:

    curl -H "Authorization:Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9..." "http://localhost:8081/profile/john"
    curl -H "Authorization:Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9..." "http://localhost:8081/metric/john"

## Troubleshouting

### ClassNotFoundException: org.apache.maven.wrapper.MavenWrapperMain

Run:

    /realpath/to/mvn -N io.takari:maven:wrapper

`/realpath/to/mwn` because on my system I have:

    alias mvn=mvn-or-mvnw

