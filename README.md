# IM2073-SimpleSurvey
### Intro to Design and Project (IM2073) - Android Survey Clicker application

A simple survey module project that let users do a survey on their Android mobile device. This project involves Mobile and Web application communication through a database. The codes here focus mainly on mobile-side of the development.

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white) ![MySQL](https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white)

Tools:
- MySQL
- Android Studio
- XAMPP

Workflow:
1. Retrieve data from MySQL database using php 
2. Data retrieved from php captured and displayed on mobile app. 
3. Mobile responses to survey qns uploaded to database via php


Start Activity             |  Survey Activity
:-------------------------:|:-------------------------:
![StartPage](https://user-images.githubusercontent.com/30825204/115137610-f01cb700-9fdb-11eb-8802-cb1fc7b30a47.PNG)  |  ![Survey Activity](https://user-images.githubusercontent.com/30825204/115137594-d67b6f80-9fdb-11eb-901a-1a0f5137df34.png)


Preview:

Full view - Simulated      |  App view - Live (remote)
:-------------------------:|:-------------------------:
[![Full view - simulated](https://user-images.githubusercontent.com/30825204/116789253-d6ab4e80-aa62-11eb-944d-051ccec385d9.png)](https://user-images.githubusercontent.com/30825204/114790676-ff7cd580-9d39-11eb-896b-82e2db728e73.mp4)|[![App view using Live (remote)](https://user-images.githubusercontent.com/30825204/116789425-91d3e780-aa63-11eb-825b-bb32d0b79396.png)](https://user-images.githubusercontent.com/30825204/114789168-867c7e80-9d37-11eb-918d-9ddb120fa23c.mp4)

<!-- WebApp: [Survey WebApp dashboard.pdf](https://github.com/NovemForxuz/IM2073-SimpleSurvey/files/6314068/WebApp.survey.dashboard.pdf) -->
## Survey Dashboard (Webapp)
The dashboard was created in a separate repository, connecting to the app via backend.

![image](https://user-images.githubusercontent.com/30825204/211903057-77d68c12-1149-4da5-9c85-6be384bd75aa.png)


Database SELECT query (query_questions.php)
:-------------------------:
![php_questions_query](https://user-images.githubusercontent.com/30825204/114873035-c0896700-9daf-11eb-9d08-14a55d2aa897.PNG)

<br>
The project further attempted to apply separation of concerns using MVVM design pattern.
<br><br>

Data flow (MVVM)
:-------------------------:
![Data-Flow](https://user-images.githubusercontent.com/30825204/114879256-df8af780-9db5-11eb-95bf-f3c2fada438c.png)

