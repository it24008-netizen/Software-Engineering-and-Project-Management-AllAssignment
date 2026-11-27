# CampusBoot — Mini Annotation Framework

A small Java 11+ project demonstrating custom runtime annotations and Java Reflection.
It implements a tiny IoC container, configuration value injection, field validation, and
method-based routing without Spring or any external libraries.

## Requirements
- JDK 11 or newer
- IntelliJ IDEA (or another Java IDE)

## Project structure
```text
CampusBoot/
├── src/                 # All Java source files (default package)
├── answers.txt          # Part E: reflection questions
├── README.md
└── .gitignore
```

## Run from IntelliJ IDEA
1. Extract the ZIP file.
2. Open IntelliJ IDEA and choose **File → Open**.
3. Select the extracted `CampusBoot` folder.
4. If IntelliJ asks, configure a Project SDK of Java 11 or newer.
5. Mark `src` as **Sources Root** if it is not detected automatically:
   right-click `src` → **Mark Directory as** → **Sources Root**.
6. Open `src/Main.java` and click the green Run icon beside `main`.

## Run from a terminal
From the CampusBoot project folder:

```bash
javac -d out src/*.java
java -cp out Main
```

## Expected output
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

## Implementation overview
- `@Component`: marks a class for container registration.
- `@Value`: injects a String or int from a configuration map.
- `@Range`: validates an int field against an inclusive minimum and maximum.
- `@Route`: maps a URL-like path to a no-argument String method.
- `Container`: creates and stores component instances and injects configuration.
- `Validator`: checks fields annotated with `@Range`.
- `Router`: finds and invokes methods annotated with `@Route`.

All four annotations use `RetentionPolicy.RUNTIME`, so Reflection can inspect them
while the program is running.

## GitHub upload
Create a new **public** repository named `CampusBoot` on GitHub. Do not initialize it
with a README if you plan to push this existing project. Then run these commands in
the CampusBoot folder (replace the URL with your repository URL):

```bash
git init
git add .
git commit -m "Build CampusBoot mini annotation framework"
git branch -M main
git remote add origin https://github.com/YOUR-USERNAME/CampusBoot.git
git push -u origin main
```

Before submitting, check that the repository contains all Java files, `answers.txt`,
and `README.md`. Replace the ZIP filename's student ID if your official ID is different.
