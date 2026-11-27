
# 👨‍💻 DEVELOPER INFORMATION

| Information | Details |
|---|---|
| **Name** | Apurbo Kumar Roy |
| **Student ID** | `it24008` |
| **Department** | Information and Communication Technology (ICT) |
| **Faculty** | Engineering Faculty |
| **University** | Mawlana Bhashani Science and Technology University (MBSTU) |
| **Programming Language** | Java 11+ |
| **Project Type** | Individual Academic Assignment |

---

# 🚀 CampusBoot
### A Mini Annotation-Based Java Framework

<p align="center">
  <img src="https://img.shields.io/badge/Java-11%2B-orange?style=for-the-badge&logo=openjdk&logoColor=white" />
  <img src="https://img.shields.io/badge/Reflection-Enabled-blue?style=for-the-badge" />
  <img src="https://img.shields.io/badge/Custom-Annotations-purple?style=for-the-badge" />
  <img src="https://img.shields.io/badge/Dependencies-None-success?style=for-the-badge" />
</p>

<p align="center">
  <b>Understanding the Internal Working of Spring Boot Through Java Reflection & Custom Annotations.</b>
</p>

---

## 📖 1. Project Overview

**CampusBoot** is a lightweight, annotation-based mini framework developed using Java Reflection and Custom Annotations.

The primary goal of this project is to understand how modern Java frameworks, especially Spring Boot, work internally.

Instead of using Spring Boot or any external libraries, CampusBoot implements its own basic mechanisms for object management, configuration injection, runtime validation, and method-based routing.

This project demonstrates how annotations combined with Java Reflection can automate repetitive programming tasks.

## 🎯 2. Project Objectives

- Understand the fundamentals of Java Reflection.
- Create and use custom Java annotations.
- Implement runtime annotation processing.
- Develop a basic IoC (Inversion of Control) container.
- Inject configuration values dynamically.
- Validate object fields using annotations.
- Implement a simple method-based routing system.
- Understand the fundamental concepts behind Spring Boot.

## 🛠️ 3. Technologies & Tools

| Technology | Purpose |
|---|---|
| Java 11+ | Core programming language |
| Java Reflection API | Runtime class, field and method inspection |
| Custom Annotations | Metadata-based configuration |
| IntelliJ IDEA | Development environment |
| Git | Version control |
| GitHub | Source code hosting |

**External Libraries:** None.

---

## 🏗️ 4. System Architecture

CampusBoot is organized into three major functional components.

```text
                 CAMPUSBOOT FRAMEWORK
                          |
             +------------+------------+
             |            |            |
         Container     Validator      Router
             |            |            |
        +----+----+       |            |
        |         |       |            |
    @Component  @Value  @Range       @Route
        |         |       |            |
        +---------+-------+------------+
                          |
                   Java Reflection
                          |
                  Runtime Processing
                          |
                    Application
```

### Architecture Explanation

1. **Container:** Creates and manages component objects.
2. **Validator:** Checks whether object fields satisfy validation rules.
3. **Router:** Finds and executes methods based on requested paths.
4. **Java Reflection:** Connects annotations with runtime processing.

---

## 🏷️ 5. Custom Annotations

CampusBoot implements four custom annotations.

### 5.1 @Component

- Target: Class
- Retention: Runtime
- Purpose: Marks a class as a managed component.

Example:

```java
@Component("gradeController")
public class GradeController {
}
```

If the annotation value is empty, the framework automatically generates the bean name using the class name with its first letter converted to lowercase.

### 5.2 @Value

- Target: Field
- Retention: Runtime
- Purpose: Injects configuration values into fields.

Example:

```java
@Value("app.name")
private String appName;

@Value("app.passMark")
private int passMark;
```

The Container retrieves the values from a configuration Map and injects them using Reflection.

### 5.3 @Range

- Target: Field
- Retention: Runtime
- Purpose: Validates integer values within a specified range.

Example:

```java
@Range(
    min = 0,
    max = 100,
    message = "Score must be between 0 and 100"
)
private int score;
```

The Validator checks whether the actual field value falls within the inclusive minimum and maximum limits.

### 5.4 @Route

- Target: Method
- Retention: Runtime
- Purpose: Maps a path to a Java method.

Example:

```java
@Route(path = "/hello")
public String hello() {
    return "Welcome to " + appName;
}
```

The Router searches for the matching route and invokes the method using Reflection.

---

## ⚙️ 6. Core Components

### 6.1 Container — IoC & Dependency Injection

The `Container` class manages application objects.

Main responsibilities:

- Scans classes for `@Component`.
- Creates objects through no-argument constructors.
- Registers objects in a `Map<String, Object>`.
- Injects String and int configuration values.
- Provides access to registered objects through `getBean()`.

Important methods:

```java
scan(Class<?>... classes);

injectValues(Map<String, String> config);

getBean(String name);
```

### 6.2 Validator — Runtime Data Validation

The `Validator` class checks fields annotated with `@Range`.

Main method:

```java
public static List<String> validate(Object obj)
```

It returns an empty list when all validation rules are satisfied. Otherwise, it returns descriptive violation messages.

### 6.3 Router — Method-Based Routing

The `Router` class connects paths with Java methods.

Main method:

```java
public static String dispatch(Container c, String path)
```

It searches registered beans, finds matching `@Route` methods, and invokes them.

If no matching path exists, it returns:

```text
404 Not Found: /unknown
```

---

## 📂 7. Project Structure

```text
CampusBoot/
│
├── src/
│   │
│   ├── Component.java
│   ├── Value.java
│   ├── Range.java
│   ├── Route.java
│   │
│   ├── Container.java
│   ├── Validator.java
│   ├── Router.java
│   │
│   ├── GradeController.java
│   ├── ReportPrinter.java
│   ├── Student.java
│   └── Main.java
│
├── answers.txt
├── README.md
└── .gitignore
```

### File Descriptions

| File | Description |
|---|---|
| Component.java | Defines the Component annotation |
| Value.java | Defines the Value annotation |
| Range.java | Defines the Range annotation |
| Route.java | Defines the Route annotation |
| Container.java | Implements IoC and value injection |
| Validator.java | Implements runtime validation |
| Router.java | Implements method-based routing |
| GradeController.java | Demonstrates component and route annotations |
| ReportPrinter.java | Demonstrates component scanning skip behavior |
| Student.java | Demonstrates range validation |
| Main.java | Application entry point |
| answers.txt | Reflection questions and answers |

---

## ▶️ 8. Installation & Execution

### System Requirements

- JDK 11 or higher.
- IntelliJ IDEA or another Java IDE.
- Git (optional, for cloning the repository).

### Step 1: Clone the Repository

```bash
git clone https://github.com/YOUR-USERNAME/CampusBoot.git
```

### Step 2: Open the Project

Open the downloaded project folder in IntelliJ IDEA.

### Step 3: Configure Java SDK

Set the Project SDK to Java 11 or higher.

### Step 4: Run the Application

Open:

```text
src/Main.java
```

Run the `main()` method.

### Alternative: Run Using Terminal

Compile:

```bash
javac -d out src/*.java
```

Execute:

```bash
java -cp out Main
```

---

## 🧪 9. Testing & Expected Output

The application demonstrates the following test scenarios:

| Test | Input / Request | Expected Result |
|---|---|---|
| Component Registration | GradeController | Registered |
| Component Skipping | ReportPrinter | Skipped |
| Value Injection | app.name | CampusBoot |
| Value Injection | app.passMark | 40 |
| Score Validation | 120 | Violation |
| Score Validation | 85 | OK |
| Semester Validation | 15 | Violation |
| Route Mapping | /hello | Welcome to CampusBoot |
| Route Mapping | /status | Pass mark is 40 |
| Unknown Route | /unknown | 404 Not Found |

### Console Output

```text
[Container] Registered: gradeController (GradeController)
[Container] Skipped: ReportPrinter (no @Component)
[Container] Injected: app.name -> GradeController.appName
[Container] Injected: app.passMark -> GradeController.passMark

--- Validation ---
VIOLATION: score - Score must be between 0 and 100 (actual: 120)
OK
VIOLATION: semester - Value out of range (actual: 15)

--- Routing ---
[Router] /hello -> Welcome to CampusBoot
[Router] /status -> Pass mark is 40
[Router] /unknown -> 404 Not Found: /unknown
```

---

## 🔬 10. How Does CampusBoot Relate to Spring Boot?

CampusBoot demonstrates simplified versions of several Spring concepts.

| CampusBoot | Spring Equivalent |
|---|---|
| `@Component` | `@Component` |
| `@Value` | `@Value` |
| `@Range` | Jakarta Validation `@Min` and `@Max` |
| `@Route` | `@GetMapping` / `@RequestMapping` |
| Container | Spring IoC Container |

**Important Difference:**

Real Spring Boot provides a much more advanced dependency injection system, component scanning, bean lifecycle management, HTTP request handling, automatic configuration, and many other features.

CampusBoot implements only the basic concepts for educational purposes.

---

## 📚 11. Learning Outcomes

By developing this project, I gained practical knowledge of:

- Java Reflection API.
- Custom Annotation Development.
- Runtime Annotation Processing.
- Inversion of Control (IoC).
- Configuration Value Injection.
- Dynamic Field Validation.
- Reflection-Based Method Invocation.
- Basic Framework Architecture.
- The internal concepts behind Spring Boot.

---

## ⚠️ 12. Current Limitations

CampusBoot is an educational mini framework, not a production-ready web framework.

Current limitations include:

- Supports only String and int configuration injection.
- Uses no-argument constructors for component creation.
- Supports only no-argument methods returning String for routes.
- Supports only int fields for range validation.
- Does not provide a real HTTP server.
- Does not implement full Spring-style dependency injection or bean lifecycle management.

---

## 🚀 13. Future Improvements

Possible extensions include:

- Package-based automatic component scanning.
- Constructor-based dependency injection.
- Support for more configuration data types.
- Advanced validation annotations.
- HTTP server integration.
- Exception handling and logging.
- Bean lifecycle management.

These are potential future enhancements and are not part of the current implementation.

---

## 📜 14. Academic Information

| Category | Details |
|---|---|
| Project Name | CampusBoot |
| Assignment Topic | Java Reflection & Custom Annotations |
| Programming Language | Java 11+ |
| Assignment Type | Individual |
| University | MBSTU |
| Department | ICT |
| Student ID | it24008 |

**Academic Purpose:** This project was developed to demonstrate practical knowledge of Java Reflection, Custom Annotations, IoC, runtime validation, and method routing.

---

## 👨‍💻 15. About the Developer

### Apurbo Kumar Roy

Undergraduate Student  
Department of Information and Communication Technology (ICT)  
Engineering Faculty  
Mawlana Bhashani Science and Technology University (MBSTU)

**Student ID:** `it24008`

Interested in Software Engineering, Java Development, Problem Solving, and Modern Software Architecture.

---

<p align="center">
  <b>☕ Built with Java | Powered by Reflection | Driven by Learning</b>
</p>

<p align="center">
  <i>CampusBoot — Understanding Frameworks by Building One.</i>
</p>

<p align="center">
  ⭐ Thank you for visiting CampusBoot! ⭐
</p>
