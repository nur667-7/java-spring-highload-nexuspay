# Полный Чек Знаний, Точка Остановки и Единый План («Java Spring 30 Days» + «До НГ: NexusPay & AegisAI»)

> **Дата и время фиксации:** `2026-10-06 13:25 (+05:00)`  
> **Профиль инженера:** Нурбек (3 курс IITU / МУИТ, Алматы)  
> **Целевой грейд (До НГ):** Strong Junior / Pre-Middle Backend & AI-Augmented Engineer (Java 21 LTS, Spring Boot 3.x, PostgreSQL 16, Redis 7, Kafka, ClickHouse, React/TS, Production RAG & Agents, Tech English B2)  
> **Целевые компании:** Kaspi.kz, Halyk Bank, Kolesa Group, Freedom Holding, Jusan Singularity, inDrive, QazCode, BigTech СНГ.

---

## 📍 1. ТОЧКА ОСТАНОВКИ (Где именно мы находимся прямо сейчас)

Оба наших трека (**Core Java High-Load Drills `day01..day04`** и **Боевой проект `NexusPay` до НГ**) синхронизировались в **одной и той же точке — Многопоточность, JMM и Безопасность состояния (Concurrency & Locking)**:

| Трек | Текущий модуль / день | Что только что сделано | Точное место остановки (Следующий шаг) |
| :--- | :--- | :--- | :--- |
| **Трек А: Core Java & JMM Drills** ([`PLAN_30_DAYS.md`](PLAN_30_DAYS.md)) | **День 4 (Тема 4):** Многопоточность (`Race Condition`, `synchronized`, `volatile`, `AtomicInteger` CAS, `Deadlock`) | ✅ Пройден **Шаг 1 из 3**: разобрано почему `count++` = 3 шага CPU (`READ -> ADD -> WRITE`), сравнены пессимистичная блокировка (`synchronized` — `106 ms`) и оптимистичный Lock-Free (`AtomicInteger` / CAS — `30 ms`) в [`day04/ConcurrencyLab.java`](day04/ConcurrencyLab.java).<br>✅ Выдана теория **Шага 2 из 3**: `volatile`, кэши ядер L1/L2, Visibility Problem и Happens-Before. | ⏸ **Остановились на контрольном вопросе Шага 2 из 3:**<br>*«Фоновый воркер крутится в `while (isRunning)`, а главный поток делает `isRunning = false`. Что достаточно поставить: `volatile`, `synchronized` или `AtomicBoolean` — и почему?»*<br>👉 **Сразу после ответа:** **Шаг 3 из 3 — Deadlock** (взаимная блокировка и защита через сортировку ID замков `Lock Ordering`). |
| **Трек Б: Проект «До НГ» (`NexusPay`)** ([`iitu_3rd_year_2month_ultralearning_roadmap.md`](iitu_3rd_year_2month_ultralearning_roadmap.md)) | **Неделя 1, Переход от Дня 1 (Пн) ко Дню 2–3 (Вт–Ср)** | ✅ **Блок 1:** Алгоритм `Two Sum` ($O(N)$ time, $O(N)$ space на `HashMap`), разбор по REACTO на английском.<br>✅ **Блок 2:** Внутренности `java.util.HashMap` в JDK 21.<br>✅ **Блок 3:** Вскрытие `Object Header` (`Mark Word`, `Klass Word`, `Compressed OOPs` 32 GB) через `JOL` в [`JvmMemoryLayoutExperimentTest.java`](nexuspay/src/test/java/kz/iitu/nexuspay/JvmMemoryLayoutExperimentTest.java) и тест GC в [`GcAndJfrExperiment.java`](nexuspay/src/test/java/kz/iitu/nexuspay/GcAndJfrExperiment.java).<br>✅ **Блок 4:** Поднят скелет [`nexuspay/`](nexuspay/), `docker-compose.yml` (`PostgreSQL 16` + `Redis 7`), `application.yml` (`virtual.enabled=true`, `open-in-view=false`) и миграция Flyway [`V1__init_ledger_schema.sql`](nexuspay/src/main/resources/db/migration/V1__init_ledger_schema.sql) (`accounts`, `transfers`, `ledger_entries`). | ⏸ **Остановились перед кодингом сервиса переводов `NexusPay` (Неделя 1, Вт–Ср):**<br>Как только добиваем **Шаг 3 (Deadlock & Lock Ordering)** в `day04`, сразу применяем это знание в `NexusPay`: пишем JPA-сущности и `TransferService` с защитой от Deadlock при встречных переводах (`A -> B` и `B -> A`) через сортировку `min(fromId, toId)` перед `SELECT ... FOR UPDATE`! |

---

## 🧠 2. АУДИТ ЗНАНИЙ ПО ПРОЙДЕННЫМ ТЕМАМ (Насколько глубоко усвоена база)

Оценка проведена на основе твоих собственных ответов (без подсказок) на Retrieval, Feynman и контрольные вопросы в ходе сессий:

### Сводная таблица владения темами

| № | Тема / Модуль | Практический код в репо | Статус и Оценка | Что ты знаешь твердо (`[GREEN]`) | Зоны на докрутку (`[YELLOW]`) |
| :--- | :--- | :--- | :---: | :--- | :--- |
| **1** | **ООП, Rich Domain Model & Java 21 (`record`, `sealed`, Pattern Matching)** | [`day01/Order.java`](day01/Order.java)<br>[`day01/OrderItem.java`](day01/OrderItem.java)<br>[`day01/OrderStatus.java`](day01/OrderStatus.java)<br>[`day01/Main.java`](day01/Main.java) | 🟢 **92% (Отлично)** | • Четко понимаешь почему поля в `String` и `record` иммутабельны (`final`) и почему `record` идеален для DTO и ключей мапы.<br>• Знаешь защиту инвариантов внутри методов (`order.pay()`, `order.cancel()`) вместо публичных сеттеров.<br>• Понимаешь Defensive Copying (`List.copyOf`) и исчерпывающий `switch` по `sealed interface`. | • Держать в тонусе синтаксис компактного конструктора `record` (`public OrderItem { ... }`) при написании кода с чистого листа. |
| **2** | **Архитектура Spring Boot (Controller → Service → Repository & DI)** | Отработано на рефакторинге Лабы 4 → 5 IITU + конфиг [`nexuspay/src/main/resources/application.yml`](nexuspay/src/main/resources/application.yml) | 🟢 **90% (Отлично)** | • Твоя цитата: *«Контроллер отвечает за прием GET/POST запросов, в него внедрен сервис. В сервисе бизнес-логика и внедрен репозиторий. Контроллер не должен обращаться напрямую к репозиторию — это защита, чтобы в обход бизнес-правил не менять данные»*.<br>• Отлично понимаешь разделение ответственности слоев и Dependency Injection (бины). | • На Неделе 1 (Чт) / Дне 8 углубим как именно Spring создает **прокси (`JDK Proxy` vs `CGLIB`)** через `BeanPostProcessor` и почему вызов `this.method()` ломает `@Transactional`. |
| **3** | **Память JVM (Stack, Heap, Pass-by-Value, Object Header, GC)** | [`day02/MemoryLab.java`](day02/MemoryLab.java)<br>[`JvmMemoryLayoutExperimentTest.java`](nexuspay/src/test/java/kz/iitu/nexuspay/JvmMemoryLayoutExperimentTest.java)<br>[`GcAndJfrExperiment.java`](nexuspay/src/test/java/kz/iitu/nexuspay/GcAndJfrExperiment.java) | 🟢 **88% (Твердо)** | • Верно ответил про работу GC: сборщик сам определяет мусор по достижимости (Reachability от GC Roots), выжившие объекты переходят из Young (`Eden`/`Survivor`) в **`Old Gen`**.<br>• Понимаешь разницу между `StackOverflowError` (глубина фреймов рекурсии) и `OutOfMemoryError: Java heap space` (утечка живых ссылок в куче).<br>• На практике проверил `JOL` (16-байтный заголовок объекта, `Mark Word`, `Compressed OOPs` до 32 GB). | • Зафиксировать точное отличие при передаче ссылки в метод (**Pass-by-Value**): если внутри метода сделать `param = null`, внешняя переменная не изменится, но `param.pay()` изменит объект в общем Heap. |
| **4** | **Collections Framework (`ArrayList`, `LinkedList`, `HashMap`, `equals`/`hashCode`)** | [`day03/CollectionsLab.java`](day03/CollectionsLab.java) | 🟢 **95% (Отлично)** | • **Твой ответ про `ArrayList`:** *«Если вместимости больше нет, создается массив в 1.5 раза больше и все данные из прежнего копируются»* — 100% попадание.<br>• **Твой ответ про коллизии `HashMap`:** *«Элементов будет 5, но все они лягут в один бакет, сложность поиска упадет до $O(N)$»* — 100% попадание.<br>• **Твой ответ про `get(key)` и ключи:** *«Сначала ключ хэшируется и сравнивается hash, поэтому `String` и `record` используют как ключи из-за иммутабельных полей»* — 100% попадание.<br>• **Твой ответ на английском по `Two Sum`:** *«Time complexity $O(N)$, because we check array in just one iteration. Space complexity $O(N)$ to save $N$ elements in Map»*. | • Помнить два числа для дерева в `HashMap` (JDK 8–21): список превращается в Красно-Черное дерево ($O(\log N)$) только когда в бакете **$\ge 8$ элементов (`TREEIFY_THRESHOLD = 8`) И общая длина массива бакетов $\ge 64$ (`MIN_TREEIFY_CAPACITY = 64`)**. |
| **5** | **Многопоточность: Race Condition, `synchronized` vs `AtomicInteger` (CAS)** | [`day04/ConcurrencyLab.java`](day04/ConcurrencyLab.java) | 🟢 **94% (Отлично)** | • **Твой ответ:** *«Два способа посчитать гонку: 1) Синхронизированный (пессимистичный) — каунт блокируется, пока первый считает, остальные ждут (засыпают). 2) Оптимистичный (`AtomicInteger`) — потоки не засыпают, при чтении проверяется, изменил ли кто-то значение; если нет — записываем своё, иначе берем следующее. Он быстрее, так как потоки не засыпают и не тратится время на их пробуждение»* — идеальное инженерное понимание!<br>• Подтверждено бенчмарком: `synchronized` (`106 ms`) vs `AtomicInteger` (`30 ms`). | • Добить текущий **Шаг 2 (`volatile` и Visibility)** и **Шаг 3 (`Deadlock` и `Lock Ordering`)**. |
| **6** | **PostgreSQL & Flyway Migrations (`NexusPay` Core)** | [`docker-compose.yml`](nexuspay/docker-compose.yml)<br>[`V1__init_ledger_schema.sql`](nexuspay/src/main/resources/db/migration/V1__init_ledger_schema.sql) | 🟢 **90% (Отлично)** | • **Твой инсайт про миграции:** *«Значит мы не пишем в самом PostgreSQL руками код создания таблиц, мы пишем их в IDEA в `V1__...sql`, и это и есть миграция первой версии!»* — абсолютно верно, принцип Infrastructure/Schema as Code (`ddl-auto: validate` + Flyway) усвоен. | • Следующий шаг: пощупать `SELECT ... FOR UPDATE` и `Optimistic Locking (@Version)` в коде переводов `NexusPay`. |

---

## 🗺️ 3. ЕДИНАЯ ОБЪЕДИНЕННАЯ ПРОГРАММА ОБУЧЕНИЯ («ДО НГ»)

Мы объединили пошаговые лабораторные дриллы (`day01..day30` из [`PLAN_30_DAYS.md`](PLAN_30_DAYS.md) и [`MONTH_1_DETAILED_ROADMAP.md`](MONTH_1_DETAILED_ROADMAP.md)) с 8-недельным боевым планом [`iitu_3rd_year_2month_ultralearning_roadmap.md`](iitu_3rd_year_2month_ultralearning_roadmap.md).

**Как работает синергия:**
1. Сначала на маленьком изолированном файле (`day01`..`day06`) мы за 30–40 минут **проверяем физику явления** (память, мапа, гонка потоков, дедлок, пул потоков, Virtual Threads) по схеме: *Аналогия → Код-эксперимент → Контрольный вопрос Фейнмана*.
2. Сразу после этого внедряем этот же механизм в боевой микросервис **`NexusPay`** (Месяц 1) и **`AegisAI`** (Месяц 2), параллельно закрывая алгоритмы LeetCode и Tech English ([`iitu_tech_english_a2_to_interview_roadmap.md`](iitu_tech_english_a2_to_interview_roadmap.md)).

### Фаза 1 (Недели 1–4): High-Load Backend, JVM, PostgreSQL, Kafka, Redis & Проект №1 `NexusPay`

#### Неделя 1: Ядро JVM, Многопоточность Java 21, Spring Core и Двойная Запись `NexusPay`
* [x] **Этап 1.1 (День 1):** ООП, Инварианты, Rich Domain Model, Java 21 `record`, `sealed`, Pattern Matching — [`day01/`](day01/) ✅
* [x] **Этап 1.2 (День 2):** Память JVM (`Stack` vs `Heap`, `Pass-by-Value`, `OOM` vs `SOE`), `JOL` (`Mark Word`, `Compressed OOPs` 32GB), `G1GC` vs `Generational ZGC`, `JFR` — [`day02/`](day02/) + [`nexuspay/`](nexuspay/) ✅
* [x] **Этап 1.3 (День 3):** Внутренности `ArrayList` vs `LinkedList`, `HashMap` (бакеты, коллизии, дерево `8 / 64`), контракт `equals()`/`hashCode()`, `Two Sum` ($O(N)$) — [`day03/`](day03/) ✅
* [x] **Этап 1.4 (Инфраструктура `NexusPay`):** Docker Compose (`PostgreSQL 16` + `Redis 7`), `application.yml`, миграция Flyway `V1__init_ledger_schema.sql` (`accounts`, `transfers`, `ledger_entries`) — [`nexuspay/`](nexuspay/) ✅
* [ ] **Этап 1.5 (День 4 — МЫ ЗДЕСЬ 📍):**
  * [x] Шаг 1: Race Condition, `count++`, `synchronized` vs `AtomicInteger` (CAS) — [`day04/ConcurrencyLab.java`](day04/ConcurrencyLab.java) ✅
  * [ ] **Шаг 2 (Текущий вопрос):** Проблема видимости (Visibility), `volatile` и Happens-Before ⏳
  * [ ] **Шаг 3:** `Deadlock` (4 условия Кофмана) и защита через **Lock Ordering** (`min(id1, id2)`) ⏳
* [ ] **Этап 1.6 (День 5–6 + `NexusPay` Transfers):**
  * `ConcurrentHashMap` (CAS + синхронизация на бакете), `ReentrantLock` (AQS), `ThreadPoolExecutor` (очереди и политики отказа `Abort` / `CallerRuns`), `CompletableFuture`.
  * **Java 21 Virtual Threads (Project Loom):** Carrier Threads, проблема `Pinning` в `synchronized` vs `ReentrantLock`.
  * **Внедрение в `NexusPay`:** Реализация `TransferService` (Двойная запись + Deadlock-free `SELECT ... FOR UPDATE` + тест `CountDownLatch` на 100 параллельных потоков встречных переводов).
* [ ] **Этап 1.7 (День 7–10):**
  * **Milestone Drill:** Потокобезопасный In-Memory `LRU Cache` с TTL.
  * **Spring Core & REST API в `NexusPay`:** Жизненный цикл бина, `BeanPostProcessor`, `JDK Proxy` vs `CGLIB`, ловушка `self-invocation`, `@Transactional` Propagation, DTO на `record`, `@Valid`, `ProblemDetail` (RFC 7807), Spring Security 6 (Stateless JWT + Argon2id + защита от IDOR).

#### Неделя 2: Внутренности PostgreSQL, MVCC, Индексы, Hibernate N+1 и Идемпотентность (Дни 11–15)
* [ ] **Этап 2.1:** Страницы 8 KB, `TOAST`, `WAL`, `MVCC` (`xmin`, `xmax`, `ctid`), `VACUUM`, `HOT-updates`, уровни изоляции, `Lost Update` и `Write Skew`.
* [ ] **Этап 2.2:** Движок **`Idempotency-Key`** в `NexusPay` (таблица `idempotency_keys`, защита от двойного списания денег при сетевых ретраях).
* [ ] **Этап 2.3:** Индексы `B-Tree` (Leftmost Prefix, Covering Index `INCLUDE`, Partial Index), `GIN`, `GiST`, `BRIN`. Разбор планов `EXPLAIN (ANALYZE, BUFFERS)` на 5 000 000 строк + декларативное партиционирование `ledger_entries`.
* [ ] **Этап 2.4:** Ловушки JPA/Hibernate: First-Level Cache, Dirty Checking, проблема **N+1** (`JOIN FETCH`, `@EntityGraph`, DTO-проекции), критическая ловушка `HHH90003004` (`JOIN FETCH` + `Pageable` -> OOM), настройка пула **HikariCP** + **PgBouncer**.

#### Неделя 3: Распределенные системы, Redis, Apache Kafka, Transactional Outbox & Saga (Дни 16–22)
* [ ] **Этап 3.1:** Проблема `Dual-Write` и паттерн **Transactional Outbox** (`SELECT ... FOR UPDATE SKIP LOCKED`) + **Transactional Inbox** (дедупликация `processed_events`).
* [ ] **Этап 3.2:** Архитектура **Apache Kafka** (Партиции, сегменты, Zero-Copy, `acks=all`, `min.insync.replicas=2`, Idempotent Producer, Consumer Groups, Retry Topics + **DLQ**).
* [ ] **Этап 3.3:** **Saga Pattern** (Orchestration vs Choreography, компенсирующие транзакции `Hold -> Capture / Release`).
* [ ] **Этап 3.4:** **Redis High-Load:** Cache-Aside, защита от **Cache Stampede**, **Cache Penetration**, **Cache Avalanche** (TTL Jitter) + атомарный **Token Bucket Rate Limiter на Lua-скрипте** + **Resilience4j** (`CircuitBreaker`, `Retry` с Jitter, `Bulkhead`).

#### Неделя 4: ClickHouse OLAP, Observability, Профайлинг и Стресс-тестирование `NexusPay` (Дни 23–30)
* [ ] **Этап 4.1:** **ClickHouse** (`MergeTree`, `ReplacingMergeTree`, батч-вставка аналитики транзакций из Kafka).
* [ ] **Этап 4.2:** **Observability:** Micrometer + Prometheus + Grafana (перцентили `p95/p99`, RED/USE), распределенный трейсинг **OpenTelemetry** (`trace_id` сквозь HTTP -> Outbox -> Kafka -> ClickHouse).
* [ ] **Этап 4.3:** Дашборд **React 19 + TypeScript (Vite)** для `NexusPay`.
* [ ] **Этап 4.4:** Стресс-тестирование на **k6** (500–1500 RPS), поиск узких мест (Thread Dump `jstack`, Heap Dump `jmap`, Flame Graphs), финализация Проекта №1 `NexusPay`.

---

### Фаза 2 (Недели 5–8): Production AI Engineering, Hybrid RAG, Агенты, K8s & Проект №2 `AegisAI`
* [ ] **Неделя 5:** AST & Parent-Child Chunking, эмбеддинги, индекс `pgvector HNSW`, **Hybrid Search** в PostgreSQL (`pgvector` + `GIN tsvector BM25` + слияние рангов **RRF** + **Cross-Encoder Reranker**), защита от AI-IDOR (RBAC на уровне векторов).
* [ ] **Неделя 6:** Мульти-агентная архитектура (`Router -> Planner -> Tool Executor -> Critic`), **Structured Outputs**, безопасный **Tool Calling / MCP**, **Human-in-the-Loop (HITL)**, стриминг токенов по **SSE**, **Semantic Cache** в Redis, UI копайлота на React + TS.
* [ ] **Неделя 7:** Инженерная оценка качества ИИ (**RAGAS Evals** на Golden Dataset в CI/CD: `Faithfulness`, `Context Recall`), AI Security Guardrails (защита от Prompt Injection и утечки PII/секретов), деплой в **Kubernetes** (`startup/readiness/liveness` пробы, Graceful Shutdown).
* [ ] **Неделя 8:** Полиглот-кругозор (Go GMP scheduler / Python asyncio), секция **Code Review & Bug Hunting**, **System Design** (Wallet, Rate Limiter, Enterprise RAG), упаковка резюме по формуле Google XYZ и выход на рынок.

---

## 🛠️ 4. КАК АВТОМАТИЧЕСКИ ПРОДОЛЖАТЬ ОБУЧЕНИЕ В ANTIGRAVITY (Скиллы)

В проект встроены 2 специальных скилла Antigravity (в папке [`.agents/skills/`](.agents/skills/) и продублированы глобально в `~/.gemini/config/skills/`):

1. **[`java-highload-mentor`](.agents/skills/java-highload-mentor/SKILL.md)** — автоматически активируется, когда ты пишешь *«продолжим»*, *«следующий шаг»*, *«погнали дальше»*. Он читает этот файл, видит точную точку остановки и ведет тебя по протоколу: **Аналогия → Код-лаба → Проверка в терминале → Вопрос Фейнмана/Retrieval**.
2. **[`learning-progress-tracker`](.agents/skills/learning-progress-tracker/SKILL.md)** — автоматически активируется, когда ты просишь *«сделай чек прогресса»*, *«обнови статус»* или *«запушь прогресс в гитхаб»*. Он обновляет матрицу знаний, проверяет сборку кода и пушит чекпоинт в GitHub.
