[README.md](https://github.com/user-attachments/files/23687339/README.md)
# 📦 Password Security Analyzer (Java)

A desktop application built in **Java Swing** that analyzes password
strength, calculates entropy, estimates crack time, and demonstrates
hashing methods used in modern security systems.\
Designed for learning, portfolio projects, and security awareness demos.

## 🔐 Features

-   Password strength analysis\
-   Entropy calculation (bits of security)\
-   Strength rating (Weak / Moderate / Strong)\
-   Visual strength bar\
-   Masked password input\
-   Estimated crack time:
    -   Slow PC (1M guesses/sec)
    -   GPU Rig (1B guesses/sec)
    -   Distributed Cluster (100B guesses/sec)
-   SHA-256 hashing (with more hashing methods coming)
-   Clean Java Swing interface\
-   Runs on any OS with Java installed

## 🖼 Screenshots

### Main Interface + Security Details

![Screenshot](./Screenshot.png)

## 🧠 How It Works

### Character Set Detection

The analyzer checks for lowercase letters, uppercase letters, digits,
and symbols.

### Entropy Formula

    entropy = length × log2(characterSetSize)

### Crack Time Estimation

Uses entropy to estimate brute-force time for various hardware
strengths.

### Hashing Implemented

-   SHA-256 (current)
-   BCrypt (coming soon)

## 🚀 Running the Project

### Requirements

-   Java 17+ (tested on Java 22)
-   Eclipse / IntelliJ / NetBeans
-   argon2-jvm JAR (for hashing features)

### Run Instructions

1.  Clone the repo:

        git clone https://github.com/sf-1000/Password-Security-Analyzer.git

2.  Import into your IDE.

3.  Run `user_interface.java`.

## 📂 Project Structure

    Password_project/
     ├── src/
     │    ├── PasswordAnalyzer.java
     │    └── user_interface.java
     ├── .classpath
     ├── .project
     ├── Screenshot.png
     ├── .gitignore

## 🛠 Technologies Used

-   Java Swing\
-   Java 22\
-   SHA-256 hashing\
-   Entropy-based analysis\
-   Eclipse Git Integration\
-   Argon2-JVM library (upcoming)

## 📘 Future Improvements

-   Add Argon2 + BCrypt hashing\
-   Add HaveIBeenPwned API breach check\
-   Real-time strength bar updates\
-   Dark mode\
-   PDF report export\
-   Password generator


