# Task Manager 21S Cucumber Autotests
Cucumber auto tests for Task Manager 21S application API
## Setup
1. Install [Java 20 SDK](https://www.oracle.com/java/technologies/downloads/)
2. Download and install [Node.js and npm](https://docs.npmjs.com/cli/v7/configuring-npm/install)
3. Download [Maven](https://maven.apache.org/download.cgi) and then [Setup](https://phoenixnap.com/kb/install-maven-windows)
4. Install [IntelliJ IDEA](https://www.jetbrains.com/idea/download/) (Ultimate/Community Edition)
5. Install MySQL database from [MySQL Installer](https://dev.mysql.com/downloads/installer/) or run on Docker following guide [Docker MySQL](https://geshan.com.np/blog/2022/02/mysql-docker-compose/)
6. Check if MySQL database is running on the machine or as a Docker container
7. Clone [Project](https://github.com/task-mana-21s/task-manager-webapp) GitHub repository
8. Open project in IntelliJ IDEA
9. Update database connection string, username and password in [application.yml](src%2Fmain%2Fresources%2Fapplication.yml) file if needed
10. Configure Front-End by selecting "Maven->Install" in the Maven menu.
11. Run Back-End by selecting "Run" or "Shift+F10"
12. Clone [Test Project](https://github.com/task-mana-21s/task-manager-autotests) GitHub repository
13. Open project in IntelliJ IDEA
14. Run tests by selecting "Maven->Test" in the Maven menu.

