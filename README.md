# Shinden API

Shinden API is an unofficial API for the Shinden.pl platform, allowing interaction with the service from the code level.

## Table of Contents

- [Installation](#installation)
- [Configuration](#configuration)
- [Usage](#usage)
    - [ShindenApi](#shindenapi)
    - [LoginApi](#loginapi)
    - [UserApi](#userapi)
- [Examples](#examples)
- [License](#license)

## Installation

To use this library, add the following to your build configuration:

**For Maven:**

1. Add JitPack repository to your `pom.xml`:

    ```xml
    <repositories>
        <repository>
            <id>jitpack</id>
            <url>https://jitpack.io</url>
        </repository>
    </repositories>
    ```

2. Add the dependency:

    ```xml
    <dependency>
        <groupId>com.github.kosmateus</groupId>
        <artifactId>shinden4j</artifactId>
        <version>1.0.0</version>
    </dependency>
    ```

**For Gradle:**

1. Add JitPack repository to your `build.gradle`:

    ```gradle
    repositories {
        maven { url 'https://jitpack.io' }
    }
    ```

2. Add the dependency:

    ```gradle
    implementation 'com.github.kosmateus:shinden-api:1.0.0'
    ```

## Configuration

The API uses Google Guice for dependency management. You can configure an instance of `ShindenApi` in several ways:

* **Default Configuration:** Uses the default session manager and locale settings.

```java
ShindenApi api = ShindenApi.create();
```

* **Configuration with Session Manager:** Provide your own session manager.

    ```java
    var sessionManager = new InMemorySessionManager();
    var api = ShindenApi.create(sessionManager);
    ```

* **Configuration with Locale:** Set the locale for translations.

    ```java
    var locale = new Locale("pl", "PL");
    var api = ShindenApi.create(sessionManager, locale);
    ```

## Examples

### Login

```java
public class Example {

    public static void main(String[] args) {
        var api = ShindenApi.create();
        var loginApi = api.login();

        var loginRequest = new LoginRequest("username", "password");
        try {
            var loginDetails = loginApi.login(loginRequest);
            System.out.println("Login successful: " + loginDetails);
        } catch (PageStructureChangedException | JsoupParserException e) {
            e.printStackTrace();
        }
    }
}
```

### Get User overview

```java
public class Example {

    public static void main(String[] args) {
        var api = ShindenApi.create();
        var userApi = api.user();

        var userId = 12345L;
        try {
            var overview = userApi.getOverview(userId);
            System.out.println("User overview: " + overview);
        } catch (NotFoundException | JsoupParserException e) {
            e.printStackTrace();
        }
    }
}
```

## License

Shinden API is licensed under the MIT License. See [LICENSE](LICENSE) for more information.
