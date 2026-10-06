# Аналитический отчет: Образовательная программа МУИТ (IITU, Алматы) [1–3 курсы] и требования IT-рынка Казахстана и СНГ (2026–2027)

---

## ЧАСТЬ 1. База студента МУИТ (IITU) по итогам 1 и 2 курсов (Семестры 1–4)

В МУИТ (International Information Technology University) направления **6B06101 «Computer Science (Компьютерные науки)»**, **6B06110 «Software Engineering (Программная инженерия)»** и **6B06106 «Вычислительная техника и программное обеспечение (ВТиПО)»** (факультет КТиКБ / КИ) строятся по кредитной системе (240 ECTS за 4 года, обучение преимущественно на английском языке, управление ИУП через портал `platonus.iitu.edu.kz` и Каталог элективных дисциплин — форма F-71). Первые два года имеют унифицированное ядро (ООД + Базовые дисциплины).

### 1 курс (Семестры 1–2): Алгоритмизация, Базовый код и Математика
| Блок | Дисциплины в РУП / КЭД МУИТ | Что реально изучается (Стек и темы) | Уровень на выходе |
| :--- | :--- | :--- | :--- |
| **Программирование (Сем. 1)** | *Algorithmization and Programming* / *Introduction to Programming* (5–6 ECTS) | **C++** (реже Python на смежных потоках): типы данных, условия, циклы, массивы (1D/2D), функции, рекурсия, указатели, строки, файловый ввод-вывод. Решение задач в духе контестов (ejudge / Codeforces). | Умение писать консольные программы и решать базовые алгоритмические задачи. |
| **Программирование (Сем. 2)** | *Object-Oriented Programming (OOP)* (5–6 ECTS) | Переход на **Java** (основной язык ООП в МУИТ) или углубленный **C++ / Python**: инкапсуляция, наследование, полиморфизм, абстрактные классы, интерфейсы, обработка исключений (`try-catch`), коллекции (`ArrayList`, `HashMap`), базовый GUI (JavaFX/Swing) или консольные CRUD-проекты. | Понимание синтаксиса Java/C++ и базовых принципов ООП, но без промышленных фреймворков и паттернов. |
| **Математика (Сем. 1–2)** | *Discrete Mathematics*, *Linear Algebra*, *Calculus I & II*, *Physics* | Логика высказываний, теория множеств, комбинаторика, теория графов, матрицы, СЛАУ, векторы, пределы, производные, интегралы, ряды. | Хорошая академическая база для алгоритмов и ML. |
| **Базовые ИКТ и Общеобр.** | *Information and Communication Technologies (ICT)*, *English (Academic/Professional)*, ООД РК (История Казахстана, Философия, Социология/Политология, Казахский/Русский языки) | Работа с ОС, базовый баш/командная строка, основы таблиц/БД, технический английский. | Закрытие обязательного госкомпонента МНВО РК. |

### 2 курс (Семестры 3–4): Структуры данных, БД, Сети, ОС и Базовый Web
| Блок | Дисциплины в РУП / КЭД МУИТ | Что реально изучается (Стек и темы) | Уровень на выходе |
| :--- | :--- | :--- | :--- |
| **Алгоритмы и СД (Сем. 3)** | *Algorithms and Data Structures (ADS)* (5–6 ECTS) | Оценка сложности $O(N)$, линейные структуры (Stack, Queue, Linked List), бинарные деревья поиска (BST), кучи (Heap), хэш-таблицы, сортировки (QuickSort, MergeSort, HeapSort), базовые графовые алгоритмы (BFS, DFS, Дейкстра), жадные алгоритмы и основы ДП. (Сильные студенты параллельно ходят в кружок **ACM ICPC**). | Знание классических структур данных; на практике многие студенты забывают реализацию без регулярной практики на LeetCode. |
| **Базы данных (Сем. 3–4)** | *Database Management Systems (DBMS)* / *Проектирование баз данных* (5–6 ECTS) | Реляционная модель, ER-диаграммы, нормальные формы (1NF–3NF/BCNF), **SQL** (DDL, DML, `JOIN`, `GROUP BY`, `HAVING`, подзапросы, базовые индексы, триггеры, хранимые процедуры) на базе **PostgreSQL**, **MySQL** или **Oracle** (в рамках *Oracle Academy*). | Уверенное написание базовых `SELECT/JOIN` и проектирование простых таблиц. Слабое понимание планов выполнения (`EXPLAIN`), уровней изоляции транзакций и блокировок. |
| **Компьютерные сети (Сем. 3–4)** | *Computer Networks* / *Introduction to Networking (Cisco CCNA 1–2)* (5 ECTS) | Модели OSI и TCP/IP, IPv4/IPv6-адресация, подсети (VLSM), маршрутизация и коммутация, VLAN, DHCP, DNS, NAT, работа в **Cisco Packet Tracer**. | Сильная теоретическая база по сетевым уровням L2–L4, но слабая привязка к прикладной веб-разработке (L7: HTTP/2, gRPC, WebSockets, CORS, TLS handshake). |
| **Архитектура ЭВМ и ОС (Сем. 3–4)** | *Computer Architecture*, *Operating Systems* (5 ECTS) | Устройство процессора, кэш-память, регистры, ассемблер (базово). В курсе ОС (часто с привязкой к **Red Hat Academy / Linux**): управление процессами и потоками, планировщики CPU, виртуальная память, файловые системы, базовое администрирование **Linux CLI**, bash-скрипты, права доступа. | Базовые навыки работы в терминале Linux и теория процессов/потоков. |
| **Веб-разработка и ЯП (Сем. 3–4)** | *Web Technologies (WT)* / *Front-end Development* / *Programming in Python* (5–6 ECTS) | **HTML5, CSS3, Bootstrap/Tailwind, базовый JavaScript (DOM, Fetch API)**; на бэкенде во вводных веб-курсах часто дается базовый **PHP + MySQL** или начальный **Python (Flask/Django)** / **Node.js**. | Умение сверстать простую страницу и поднять монолитный учебный CRUD-сайт. |
| **Математика для CS (Сем. 3–4)** | *Probability Theory and Mathematical Statistics* (5 ECTS) | Случайные величины, распределения, проверка гипотез, корреляция, регрессия. | Математический фундамент под Data Science / ML на 3 курсе. |

---

## ЧАСТЬ 2. Дисциплины 3 курса МУИТ (Семестры 5–6) и разрыв с индустрией (Gap Analysis)

На 3 курсе в МУИТ начинается жесткое разделение по траекториям (Major-профилирующие дисциплины + **Minor-программы**, которые выбираются на 5–7 семестры: *Back-end / Java Enterprise, Python / Django, Mobile Development, Machine Learning & Big Data, Cybersecurity, Cloud Computing*).

### Что преподается на 3 курсе МУИТ (Семестры 5–6):
1. **Backend / Enterprise разработка (по выбору траектории):**
   * *Java Enterprise Track:* **Разработка веб- и бизнес-компонентов на Java EE / Spring** — изучаются Servlet API, JSP, Apache Tomcat, **Spring Core, Spring MVC, Spring Boot, Spring Security, Hibernate ORM / JPA, REST API**.
   * *Python Track:* **Разработка корпоративных приложений на Django** (6 ECTS, 6 семестр) — архитектура MTV, Django ORM, админ-панель, шаблоны, базовый DRF (Django REST Framework).
   * *.NET Track (чаще 6–7 семестр):* **Microsoft .NET Framework / C#** — C#, ADO.NET, ASP.NET Core, Entity Framework, WPF/WinForms.
2. **Frontend и Mobile разработка:**
   * *Mobile Technologies and Applications:* разработка под **Android (Java/Kotlin)** в Android Studio (Activities, Fragments, RecyclerView, SQLite/Room, Retrofit) или реже **iOS (Swift)** / **Flutter**.
   * *Modern Frontend / Full-stack элективы:* основы **React.js** (или Angular/Vue), компонентный подход, работа с REST API.
3. **Программная инженерия и Архитектура:**
   * *Software Engineering / Программная инженерия* и *Архитектура программных систем*: SDLC, Agile/Scrum, UML-диаграммы (Use Case, Class, Sequence), сбор требований, паттерны проектирования **GoF (Design Patterns)**, основы тестирования.
4. **Data Science / ML (для соответствующих элективов и Minor):**
   * *Machine Learning* (5 ECTS), *Big Data Analysis*: Python (`NumPy`, `Pandas`, `Scikit-learn`, `Matplotlib`), классические алгоритмы ML (линейная/логистическая регрессия, деревья решений, кластеризация).
5. **Инфраструктура и ИБ (элективы):**
   * Основы информационной безопасности (криптография, сетевая безопасность), облачные вычисления (в рамках академий *AWS / Oracle Cloud*).

---

### Главные пробелы программы МУИТ относительно реальных требований индустрии (Gap Analysis)

Несмотря на то что МУИТ дает одну из лучших практических баз среди вузов Казахстана, между академическим курсом 3-го года и требованиями технического собеседования в Kaspi, Kolesa, Halyk или inDrive существует **7 критических разрывов**:

1. **Отсутствие современной инфраструктурной и DevOps-культуры в учебных проектах:**
   * *В вузе:* Проекты запускаются локально из IDE (`localhost:8080`), код заливается в Git одним коммитом в конце семестра перед защитой (РК1/РК2/Final).
   * *В индустрии:* Обязательны **Git-flow** (PR, code review, rebase, разрешение конфликтов), **Docker & Docker Compose** (упаковка сервиса с БД и кэшем), понимание **CI/CD** (GitLab CI / GitHub Actions) и базовое представление о **Kubernetes (K8s)** и Linux-серверах.
2. **Монолитный учебный подход против микросервисной и Event-Driven архитектуры:**
   * *В вузе:* Даже на курсе Spring Boot / Django пишут монолитный CRUD с рендерингом шаблонов (JSP/Thymeleaf/Django Templates) или простейшим REST.
   * *В индустрии КЗ (особенно финтех):* Тотальное доминирование микросервисов. От Junior/Junior+ требуют понимания брокеров сообщений (**Apache Kafka**, **RabbitMQ**), идемпотентности, паттернов распределенных транзакций (**Saga, Transactional Outbox**), кэширования (**Redis**) и межсервисного взаимодействия (REST, **gRPC**, Protobuf).
3. **Поверхностная работа с Базами Данных и ORM:**
   * *В вузе:* Студент умеет написать `@Entity` в Hibernate или модель в Django ORM и простые SQL-запросы.
   * *В индустрии:* На собеседованиях жестко срезают на проблеме **N+1 запросов** в ORM, отсутствии **миграций БД (Flyway / Liquibase / Alembic)**, непонимании **ACID**, уровней изоляции транзакций (`Read Committed`, `Repeatable Read`, `Serializable`) и феноменов (Dirty Read, Non-repeatable Read, Phantom Read), устройства **B-Tree индексов**, составных индексов и чтения `EXPLAIN ANALYZE`.
4. **Полное отсутствие автоматизированного тестирования в студенческом коде:**
   * *В вузе:* Работоспособность проверяется преподавателем визуально через UI или Postman.
   * *В индустрии:* Код без тестов не проходит ревью. Нужны **Unit-тесты** (`JUnit 5 + Mockito`, `pytest`, `xUnit/Moq`, `Jest/Vitest`), интеграционные тесты (в идеале с **Testcontainers**).
5. **Устаревшие разделы силлабусов и недостаток актуальных языков (Go, современный TS, асинхронный Python):**
   * *В вузе:* В курсе Java EE всё еще тратится время на легаси (Servlets, JSP); в Python дают синхронный Django, почти не касаясь **FastAPI + asyncio + SQLAlchemy 2.0**; язык **Go (Golang)** — один из самых востребованных в Kolesa, inDrive, Kaspi, Freedom — в базовой сетке почти отсутствует; во фронтенде часто дают JS без строгого **TypeScript**.
6. **Слабое понимание внутренностей языка (Under the Hood) и многопоточности:**
   * *В вузе:* Оценивается факт работающей лабораторной работы.
   * *В индустрии:* Спрашивают устройство памяти (Stack vs Heap, Garbage Collection в JVM/.NET, GIL в Python, Event Loop в JS, планировщик горутин GMP в Go), примитивы синхронизации (Mutex, Semaphore, Deadlock, Race Condition, `volatile`, `CompletableFuture`).
7. **Качество кода и архитектурная гигиена:**
   * *В вузе:* Бизнес-логика пишется прямо в контроллерах, пароли лежат в коде, обработка ошибок — пустой `catch`, возвращаются сырые БД-сущности вместо DTO.
   * *В индустрии:* Строгое разделение слоев (`Controller -> Service -> Repository`), **SOLID**, Clean Architecture / Hexagonal, валидация входных данных, централизованный перехват ошибок (RFC 7807 / ControllerAdvice), логирование (SLF4J / ELK) и метрики (Prometheus / Grafana).

---

## ЧАСТЬ 3. Анализ рынка труда Казахстана (Алматы / Астана) и СНГ для Intern / Junior / Junior+ (2026–2027)

Рынок Казахстана в 2026–2027 годах характеризуется высокой концентрацией разработки в **Fintech / SuperApps** (Алматы как главный IT-хаб, Астана как хаб GovTech/Telecom/BigData). Компании почти перестали нанимать «нулевых» джунов с улицы по объявлениям на `hh.kz` — **основной вход на позицию Junior происходит через внутренние оплачиваемые стажировки и академии компаний**, куда берут студентов 3–4 курсов с уровнем подготовки условного «старого крепкого Junior».

### 1. Детальный разбор ключевых работодателей Казахстана

| Компания | Программа стажировки / Входа | Ключевой технологический стек (Backend / Frontend / Mobile / Data) | Специфика отбора и культура |
| :--- | :--- | :--- | :--- |
| **Kaspi.kz** (Алматы) | **Kaspi Lab** (потоки по Software Engineering, Data Analytics, AI/ML) | **Backend:** Два главных стека — **C# / .NET Core + MS SQL Server** (огромная часть ядра и платежей) и **Java 17/21 + Spring Boot** (Vert.x / Reactor). Также активно используется **Go** и **Python** (ML/DS).<br>**Инфра и БД:** Kafka, RabbitMQ, Redis, MongoDB, ClickHouse, Elasticsearch, Docker, Kubernetes.<br>**Frontend/Mobile:** React/TS, Native Android (Kotlin), iOS (Swift). | **100% офис (Алматы)**, очень динамичный темп. Огромный упор на высоконагруженность (Highload), отказоустойчивость, знание SQL/БД, алгоритмическую базу и бизнес-мышление (разбор продуктовых и архитектурных кейсов). |
| **Kolesa Group** (Алматы) | **Kolesa Academy** и открытая база знаний **Kolesa Upgrade** | **Backend:** **Go (Golang)** и современный **PHP (Laravel/Symfony)**, микросервисы, Python.<br>**БД и Инфра:** PostgreSQL, MySQL, Redis, Elasticsearch, RabbitMQ/Kafka, Docker, K8s.<br>**Frontend:** React, TypeScript, Vue.js.<br>**Mobile:** Kotlin (Android), Swift (iOS). | Эталонная инженерная культура, открытые лекции на YouTube. Очень жесткий фильтр по **soft skills** и культурному коду (открытость, инициативность, отсутствие токсичности). Технически глубоко спрашивают устройство веба, HTTP, БД, чистый код и алгоритмы. |
| **Halyk Bank** (Алматы / Астана) | **Halyk Start** / **Halyk Academy** | **Backend:** **Java 17+ / Spring Boot** (основной стандарт), **Go** (новые высоконагруженные сервисы), частично Python.<br>**БД и Инфра:** PostgreSQL, Oracle, MongoDB, Redis, **Apache Kafka**, Kubernetes, Docker, OpenShift, API Gateways (OAuth2/OIDC).<br>**Frontend/Mobile:** React/TS, Angular, Kotlin, Swift. | Крупнейший энтерпрайз-финтех. На отборе бывают ассессменты и бизнес-игры. На тех. интервью фокус на Java Core, многопоточность, Spring под капотом, транзакции в БД, Kafka и микросервисные паттерны безопасности. |
| **Freedom Holding / Freedom Finance** (Алматы / Астана) | Стажировки **Freedom** (Freedom Bank, Freedom Broker, Aviata/Ticketon/Arbuz в экосистеме) | **Backend:** Очень широкий стек в зависимости от продукта экосистемы: **Java (Spring Boot)**, **Go**, **Python (FastAPI/Django)**, **PHP**, **C#/.NET**.<br>**БД и Инфра:** PostgreSQL, ClickHouse, Redis, Kafka, K8s.<br>**Frontend/Mobile:** React, Vue, Next.js, TypeScript, Kotlin, Swift, Flutter. | Быстрорастущая экосистема (финтех, страховки, тревел, фудтех, телеком). Ценят скорость доставки фич, умение работать с интеграциями сторонних API, понимание микросервисов и наличие рабочих pet-проектов. |
| **Jusan (Alatau City Bank / Jusan Tech)** | **Jusan Singularity** (одна из сильнейших буткемп-школ в РК) | **Backend:** **Java (Spring Boot)**, **Go**.<br>**Frontend/Mobile:** React/TS, iOS (Swift), Android (Kotlin).<br>**Также:** DevOps, Data Science, QA Automation. | Жесткий многоступенчатый отбор в Singularity (тест на логику/математику, алгоритмический контест, интервью). Обучение в формате хардкорного интенсива с последующим наймом лучших на позицию Junior/Junior+. |
| **Chocofamily (Choco)** (Алматы) | **Chocodev Internship** / точечный найм | **Backend:** **Python (FastAPI, Django, asyncio)** — исторически одна из главных Python-школ в РК, также **Go** и **PHP**.<br>**БД и Инфра:** PostgreSQL, Redis, RabbitMQ/Kafka, Docker, K8s.<br>**Mobile/Front:** Kotlin, Swift, Flutter, React/Vue. | Продуктовый подход (Рядом, Рахмет, Choco). Редко берут совсем «сырых» новичков; от кандидатов ждут самостоятельности, продуктового мышления, хорошего знания асинхронного Python/Go и умения аргументировать решения. |
| **Beeline Казахстан (QazCode)** | **BeeWinner** (оплачиваемая стажировка) | **Backend:** **Java (Spring Boot)**, **Python**, **Go**, **C#**.<br>**AI/Data:** Собственная AI-компания **QazCode** (LLM, Big Data, ML, PyTorch, Spark, Airflow).<br>**Frontend/Mobile:** React/Angular, Kotlin/Swift. | Фокус на Digital Telecom, высоконагруженные биллинговые и стриминговые сервисы, плюс мощный вектор в сторону GenAI/ML. Гибридный/удаленный формат возможен чаще, чем в Kaspi. |
| **BTS Digital** (Астана) | Внутренние школы и стажировки (Aitu, eGov-продукты, AI) | **Backend:** **Java (Spring Boot)**, **Go**, **Python**, **Node.js**.<br>**Frontend/Mobile:** React, TypeScript, Vue, Kotlin, Swift.<br>**Фокус:** Big Data, AI, национальные цифровые платформы. | Главный магнит для разработчиков в Астане. Развитое менторство, внимание к качеству архитектуры, высокая культура code review и работы с данными. |
| **inDrive** (Алматы — крупный R&D хаб) | Стажировки и Junior-программы (чаще Mobile, Go, QA, Data) | **Backend:** **Go (Golang)** (основной язык микросервисов), **Python**, ранее PHP.<br>**Mobile:** Сильнейшая мобильная разработка (**Kotlin**, **Swift**).<br>**Инфра:** Highload, K8s, Kafka, PostgreSQL, Redis, gRPC, AWS/GCP. | Международная продуктовая компания. Обязателен **английский язык** (команды распределенные), алгоритмические секции в стиле BigTech (LeetCode Easy/Medium), глубокое знание Go/Mobile и системного дизайна. |

---

### 2. Сводная матрица востребованных стеков в РК и СНГ (2026–2027)

1. **Java Backend (Топ-1 по числу вакансий в банках и энтерпрайзе):**
   * *Ядро:* Java 17 / 21 LTS (Records, Sealed classes, Pattern Matching, Stream API, CompletableFuture, Virtual Threads / Project Loom).
   * *Фреймворки:* Spring Boot 3.x, Spring MVC, Spring Data JPA (Hibernate), Spring Security (JWT, OAuth2, Keycloak).
   * *Инфраструктура:* PostgreSQL, Flyway/Liquibase, Redis, Apache Kafka, Docker, JUnit 5 + Mockito + Testcontainers, Maven/Gradle.
2. **Go (Golang) Backend (Топ-1 по росту спроса в Highload и продуктовых компаниях):**
   * *Ядро:* Go 1.22+, горутины, каналы, `context`, планировщик GMP, устройство `slice` и `map`, интерфейсы, профилирование (`pprof`).
   * *Стек:* Gin / Echo / Chi / Fiber, `pgx` / `sqlx` (без тяжелых ORM), **gRPC + Protobuf**, Kafka / RabbitMQ, Redis, PostgreSQL,ClickHouse, Docker, Unit-тесты (`testing`, `testify`).
3. **Python Backend & AI/ML (Финтех, Стартапы, Data/AI-инженерия):**
   * *Backend:* Python 3.11+, **FastAPI**, `asyncio`, **SQLAlchemy 2.0 (async)**, Pydantic v2, Alembic, Celery + Redis/RabbitMQ, Django / DRF (для поддержки и админок), `pytest`.
   * *AI / Data Engineering (тренд 2026–2027):* Интеграция LLM (RAG, векторные БД Qdrant/pgvector, LangChain/LlamaIndex), PyTorch, Pandas/Polars, Apache Airflow, Spark, ClickHouse.
4. **C# / .NET Backend (Сильная ниша за счет Kaspi.kz и корп. сектора):**
   * *Стек:* C# 12 / .NET 8+, ASP.NET Core Web API, Entity Framework Core / Dapper, MS SQL Server / PostgreSQL, Redis, Kafka/RabbitMQ, xUnit.
5. **Frontend (React экосистема доминирует):**
   * *Стек:* **TypeScript** (безальтернативно), **React 18/19**, **Next.js (App Router, SSR)**, управление состоянием (Zustand, Redux Toolkit, TanStack Query / React Query), Tailwind CSS, Vite, базовое тестирование (Vitest/Playwright), понимание Core Web Vitals и безопасности (XSS, CSRF, CORS).
6. **Mobile (Высокий спрос в SuperApps):**
   * *Android:* **Kotlin**, Coroutines & Flow, **Jetpack Compose**, Clean Architecture + MVVM/MVI, Dagger/Hilt/Koin, Retrofit, Room.
   * *iOS:* **Swift**, Swift Concurrency (`async/await`), **SwiftUI + UIKit**, MVVM/VIPER/TCA, Combine.
   * *Cross-platform:* **Flutter (Dart)** — активно используется в банках второго эшелона, стартапах и сервисах экосистем (Freedom, Choco).

---

### 3. Что реально спрашивают на собеседованиях (Intern / Junior / Junior+ в 2026–2027)

Процесс отбора в 2026–2027 гг. состоит из **4 этапов**: (1) Онлайн-тест / Алгоритмический контест (HackerRank / LeetCode платформа) -> (2) Тестовое задание (или Live-coding) -> (3) Техническое интервью (60–90 мин) -> (4) Культурный фит с Team Lead / HR.

#### Блок А. Алгоритмы и структуры данных (Live-coding, 20–30 минут)
Уровень: **LeetCode Easy — уверенный Medium**.
* **Темы, которые дают в 90% случаев:**
  1. `HashMap` / `HashSet`: *Two Sum, Group Anagrams, Subarray Sum Equals K, Top K Frequent Elements, LRU Cache (на Junior+)*.
  2. Два указателя и Скользящее окно (Two Pointers & Sliding Window): *Valid Palindrome, Container With Most Water, Longest Substring Without Repeating Characters*.
  3. Стек и Очередь: *Valid Parentheses, Daily Temperatures, Min Stack*.
  4. Бинарный поиск (Binary Search): поиск в отсортированном/сдвинутом массиве, бинарный поиск по ответу.
  5. Списки и Деревья: *Reverse Linked List, Detect Cycle, Merge Two Sorted Lists, Maximum Depth of Binary Tree, Invert Binary Tree, BFS/DFS обход*.
* **Вопросы на понимание:** «Какая асимптотика по времени и по памяти у твоего решения?», «Как устроена HashMap внутри? Что происходит при коллизии? Что такое `hashCode` и `equals` контракт (в Java) / почему `map` в Go не потокобезопасна?».

#### Блок Б. Базы данных и SQL (Критическая секция в Kaspi, Halyk, Kolesa, Freedom)
1. **Практика SQL (написать запрос вживую):**
   * Найти топ-3 сотрудников с наибольшей зарплатой в каждом департаменте (оконные функции: `ROW_NUMBER()`, `RANK()`, `DENSE_RANK() OVER (PARTITION BY ... ORDER BY ...)`).
   * Найти клиентов, которые совершили транзакции на сумму более X за последний месяц (`JOIN` + `GROUP BY` + `HAVING` + фильтрация дат).
   * Разница между `INNER JOIN`, `LEFT JOIN`, `FULL OUTER JOIN`, `UNION` vs `UNION ALL`, `WHERE` vs `HAVING`.
2. **Теория СУБД:**
   * **Индексы:** Как устроен B-Tree индекс? Почему индекс не срабатывает при `LIKE '%abc'` или вызове функции от колонки? В каком порядке ставить колонки в составном индексе? Что такое Covering Index?
   * **Транзакции:** Расшифровать **ACID**. Какие проблемы параллельного доступа существуют (Dirty Read, Non-Repeatable Read, Phantom Read, Lost Update) и какой уровень изоляции в PostgreSQL/MS SQL стоит по умолчанию (`Read Committed`)?
   * **Блокировки:** Оптимистичные (версионирование) против пессимистичных (`SELECT ... FOR UPDATE`), как возникает Deadlock в БД.

#### Блок В. Сети, Архитектура и Инфраструктура
* Чем отличается TCP от UDP? Как проходит 3-way handshake?
* Что происходит в деталях, когда пользователь вводит `https://kaspi.kz` в браузере? (DNS -> TCP -> TLS -> HTTP -> Load Balancer / Nginx -> Backend -> DB).
* Структура HTTP-запроса/ответа: методы (`GET, POST, PUT, PATCH, DELETE`), идемпотентность методов (чем `PUT` отличается от `POST` и `PATCH`), коды состояния (`200, 201, 204, 301/302, 400, 401 vs 403, 404, 409, 422, 429, 500, 502, 503, 504`).
* Аутентификация и Авторизация: как устроен **JWT** (Header, Payload, Signature), зачем нужна пара `Access Token + Refresh Token`, чем отличается от сессионных кук (`HttpOnly, Secure, SameSite`).
* Брокеры сообщений (для Junior+): зачем нужен Kafka/RabbitMQ вместо синхронного HTTP-вызова? Что будет, если одно и то же сообщение о списании денег придет дважды (идемпотентность консьюмера)?

#### Блок Г. Типовые тестовые задания (2026–2027)
На стажировки и Junior-позиции тестовое задание обычно дается на 3–5 дней и проверяет **инженерную культуру**, а не только работоспособность:
* **Пример для Backend (Java / Go / Python / C#):**
  * *Задача:* Разработать REST API микросервис (например, сервис перевода средств между счетами, сервис бронирования билетов или сокращатель ссылок с аналитикой).
  * *Что ждут проверяющие (чек-лист сеньора при проверке):*
    1. Запуск одной командой через **`docker-compose up`** (приложение + PostgreSQL + Redis).
    2. Наличие миграций БД (**Flyway / Liquibase / Alembic / golang-migrate**), а не `ddl-auto: update`.
    3. Корректная работа с конкурентностью (например, защита от двойного списания баланса при параллельных запросах через транзакции и блокировки).
    4. Чистая слоистая архитектура, валидация входных DTO, единый формат ошибок, пагинация списков (`limit/offset` или `cursor`), документация **Swagger / OpenAPI**.
    5. Наличие **Unit и интеграционных тестов**, чистое `README.md` с инструкцией запуска и архитектурными решениями, аккуратная история коммитов.
* **Пример для Frontend (React / TS):**
  * Разработать SPA-дашборд (например, каталог авто/товаров или трекер финансов) с фильтрацией, сортировкой, пагинацией через URL-параметры (`searchParams`), дебаунсом поиска, обработкой состояний `loading / error / empty`, строгой типизацией TypeScript без `any`, адаптивной версткой и кэшированием запросов.
* **Новое требование 2026–2027 (AI-native разработка):**
  * Компании разрешают и приветствуют использование AI-ассистентов в работе, но на интервью просят **вживую объяснить каждую строчку своего тестового задания** и внести в него изменение на глазах у интервьюера. Кандидаты, сгенерировавшие проект через LLM без понимания того, как там работают транзакции или DI, отсеиваются за 5 минут.

---

## Резюме для студента 3 курса МУИТ: Как закрыть разрыв за 3–6 месяцев
1. **Выбрать 1 узкий стек** (например, *Java + Spring Boot*, или *Go*, или *Python + FastAPI*, или *React + TypeScript*, или *Kotlin Android*) и не распыляться на все предметы семестра одинаково.
2. **Привязать курсовые проекты 3 курса МУИТ к промышленному стандарту:** если по силлабусу требуют проект на Spring Boot или Django — сразу оборачивать его в `Docker Compose`, подключать `PostgreSQL + Flyway + Redis`, писать `JUnit/pytest` тесты и выкладывать на GitHub с документацией.
3. **Ежедневная база:** прорешать 70–100 базовых задач на **LeetCode** (NeetCode 150 roadmap: Arrays, Hashing, Two Pointers, Sliding Window, Stack, Binary Search, Trees) и прокачать **SQL** (оконные функции, индексы, транзакции).
4. **Целиться в наборы академий:** отслеживать весенние/осенние/летние наборы в **Kaspi Lab, Kolesa Academy, Jusan Singularity, Halyk Start, BeeWinner, Chocodev** — это главный социальный лифт на IT-рынке Казахстана.