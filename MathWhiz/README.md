🧮 APURBO MATH WHIZ
A Java Servlet & JDBC Math Quiz Game with a Top 10 Leaderboard
Java Tomcat MySQL Maven IntelliJ

Java Web Application Development ·
Live Lab Test #2# APURBO – MATH WHIZ

A web-based mathematical game built with **Java Servlets, JSP and JDBC (MySQL)**. Players answer four questions (prime number, modulus, two trigonometry questions), see their score out of 10, and appear on a Top 10 leaderboard.

> Replace `YOUR NAME` in the JSP headers/titles with your real name before submitting (search the project for `YOUR NAME`).

## Project Objectives
- Build reusable Java methods for mathematical calculations.
- Process requests and responses with a Java Servlet.
- Connect to a database with JDBC and `PreparedStatement`.
- Save scores and display a sorted Top 10 leaderboard.
- Document and submit the project through GitHub.

## Technologies Used
Java 11+, Servlet 4.0 / JSP 2.3, JSTL 1.2, JDBC, MySQL 8, Apache Tomcat 9, Maven, HTML/CSS.

## Software Requirements
- JDK 11 or later
- Apache Tomcat 9.x (uses the `javax.servlet` namespace; for Tomcat 10+ change imports to `jakarta.*`)
- MySQL 8.x
- Maven 3.6+ (or import the project into Eclipse / IntelliJ / NetBeans)

## Project Structure
```
MathWhiz/
├── src/main/java/
│   ├── controller/GameServlet.java   # start, submit, leaderboard
│   ├── model/GameScore.java
│   ├── service/MathService.java      # isPrime, calculateMod, sin/cos/tan
│   ├── dao/ScoreDAO.java             # JDBC INSERT / SELECT
│   └── util/DBUtil.java              # connection helper
├── src/main/resources/db.properties  # DB URL, user, password
├── src/main/webapp/                  # index.jsp, game.jsp, result.jsp, leaderboard.jsp, css/
├── src/test/java/MathServiceTest.java
├── database/math_whiz_db.sql
└── README.md
```

## Database Configuration
1. Start MySQL.
2. Run `database/math_whiz_db.sql` (see below).
3. Edit `src/main/resources/db.properties` with your MySQL user and password.

### Database Creation SQL
```sql
CREATE DATABASE IF NOT EXISTS math_whiz_db;
USE math_whiz_db;

CREATE TABLE IF NOT EXISTS game_scores (
    id          INT           NOT NULL AUTO_INCREMENT PRIMARY KEY,
    player_name VARCHAR(100)  NOT NULL,
    score       INT           NOT NULL,
    difficulty  VARCHAR(20)   NOT NULL DEFAULT 'NORMAL',
    played_at   TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP
);
```

## Steps to Run
1. Clone the repository and open the `MathWhiz` folder.
2. Create the database and update `db.properties` (above).
3. Build: `mvn clean package` → produces `target/MathWhiz.war`.
4. Copy `MathWhiz.war` into Tomcat's `webapps/` folder (or deploy from your IDE), then start Tomcat.
5. Open <http://localhost:8080/MathWhiz/>.

### Run the logic tests (no server or database needed)
```
javac -d out src/main/java/service/MathService.java src/test/java/MathServiceTest.java
java -cp out MathServiceTest
```

## Game Rules and Scoring
| Question | Area | Marks |
|---|---|---|
| Q1 | Prime number (1 = prime, 0 = not prime) | 2 |
| Q2 | Modulus (integers only, divisor never 0) | 2 |
| Q3 | Trigonometry (sin / cos / tan) | 3 |
| Q4 | Trigonometry (sin / cos / tan) | 3 |
| **Total** | | **10** |

- Difficulty is **NORMAL** for all questions and is stored in the database.
- Numbers below 2 are not prime.
- Trigonometry uses degrees, converted to radians with `Math.toRadians`, then `Math.sin / cos / tan`.
- Decimal answers are accepted within a tolerance of **±0.01**.
- Undefined tangent values (90°, 270°) return `NaN` and are never asked.
- All correct answers are calculated by the Java methods, never stored.
- The question values are kept in the session, so the player cannot change them in the browser.

## Test Cases
| Test | Expected |
|---|---|
| Prime 7 / 8 / 1 | 1 / 0 / 0 |
| 17 % 5 | 2 |
| sin 30° / cos 60° / tan 45° | 0.5 / 0.5 / 1.0 |
| Divisor 0 | Validation error |
| All correct | 10 marks |
| Wrong answers | Those marks are not awarded |

## Application Screenshots
### Welcome Page
![Welcome Page](screenshots/home.png)

### Game Page
![Game Page](screenshots/game.png)

### Result Page
![Result Page](screenshots/result.png)

### Leaderboard
![Leaderboard](screenshots/leaderboard.png)

## GitHub Project Directory Link
`https://github.com/<username>/<repository>/tree/main/MathWhiz`
