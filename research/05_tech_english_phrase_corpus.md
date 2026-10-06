# ЛИНГВИСТИЧЕСКАЯ МАТРИЦА И КОРПУС ФРАЗ (TECHNICAL ENGLISH CORPUS: A1+/A2 → INTERVIEW-READY ЗА 8 НЕДЕЛЬ)
**Целевой стек:** Java 21, Spring Boot 3.x, PostgreSQL, Kafka, Docker/K8s, AI / Hybrid RAG (проекты **NexusPay** и **AegisAI**).

> **Инженерный принцип «English as Lego»:** Для прохождения техсобеседования не нужен литературный английский (C1). Технический английский состоит из **~50 синтаксических каркасов (Sentence Frames)** и **~300 технических терминов**, которые вы уже знаете. Задача — автоматизировать связки между терминами.

---

## БЛОК 1. Минимальная грамматическая база инженера (The 5 Core Grammar Patterns)

Эти 5 конструкций закрывают **95% всей речи** на системном дизайне, лайвкодинге, дейликах и код-ревью. Всё остальное (Future Perfect Continuous, Past Perfect и т.д.) отбрасываем как избыточный оверхед.

### 1.1. Present Simple — Архитектура, работа систем под капотом, факты
* **Когда использовать:** Описание того, как работает код, JVM, база данных, микросервисы, алгоритмы.
* **Формула:** `[Subject / Component] + [Verb (-s для he/she/it)] + [Object / Purpose]`
* **Топ-15 глаголов инженера:** `handles` (обрабатывает), `stores` (хранит), `validates` (валидирует), `fetches` (достает), `publishes` (публикует), `consumes` (читает из очереди), `blocks` (блокирует), `prevents` (предотвращает), `ensures` (гарантирует), `triggers` (запускает), `routes` (маршрутизирует), `scales` (масштабируется), `allocates` (выделяет память), `persists` (сохраняет в БД), `executes` (выполняет).
* **Sentence Frames & Примеры:**
  * `[Service A] communicates with [Service B] via [Protocol/Broker].`
    * *“The Payment Service communicates with the Notification Service via Kafka.”*
  * `Under the hood, [Component] uses [Mechanism] to [Action].`
    * *“Under the hood, PostgreSQL uses MVCC to handle concurrent transactions without locking readers.”*
  * `This layer validates [Input] and throws an exception if [Condition].`
    * *“The Controller layer validates the request DTO and throws an exception if the payload is invalid.”*

### 1.2. Past Simple vs. Present Perfect — Опыт, резюме, стендапы и инциденты
* **Правило без воды:**
  * **Past Simple (`Verb-ed / V2`)** — действие **закончилось в прошлом** (вчера, на прошлом проекте, в 2025 году, когда фиксил баг). Фокус на истории и деталях: *“Что именно сделал?”*
  * **Present Perfect (`have/has + V3`)** — действие **сделано к текущему моменту**, важен **результат сейчас** (на дейлике, при показе PR, краткий итог опыта).
* **Маркеры:**
  * Past Simple: `yesterday`, `in my previous project`, `when we hit a bottleneck`, `during load testing`.
  * Present Perfect: `already`, `just`, `so far`, `recently`, `over the last 2 years`.
* **Сравнительная матрица:**
  | Ситуация | Past Simple (Факт в прошлом / История) | Present Perfect (Результат к текущему моменту) |
  | :--- | :--- | :--- |
  | **Daily Standup** | *“Yesterday I **fixed** the N+1 query in `OrderRepository`.”* | *“I **have opened** the PR and **pushed** the unit tests — it is ready for review.”* |
  | **Резюме / Опыт** | *“In NexusPay, I **implemented** the Transactional Outbox pattern.”* | *“I **have worked** with Java 21 and Spring Boot for two years.”* |
  | **Инцидент** | *“The pod **crashed** because it **ran** out of heap memory.”* | *“We **have rolled back** the deployment, so production is stable now.”* |

### 1.3. Conditionals Type 1 & Type 2 — Edge-cases, отказоустойчивость и «Что если?»
* **Conditional Type 1 (Реальный сценарий / Edge-case в рантайме):**
  * **Формула:** `If + [Present Simple], [Component] + will + [Base Verb]`
  * **Для чего:** Описание поведения системы при падении узлов, сетевых сбоях, невалидных данных.
  * **Примеры:**
    * *“**If** Kafka **goes** down, the Outbox table **will buffer** the events in PostgreSQL until the broker recovers.”*
    * *“**If** two threads **try** to update the same account balance simultaneously, optimistic locking **will throw** an `OptimisticLockException`.”*
    * *“**If** the input array **is** empty, the method **will return** `-1` immediately.”*
* **Conditional Type 2 (Гипотетический дизайн / Масштабирование / Альтернатива):**
  * **Формула:** `If + [Past Simple (were/had)], I/we + would + [Base Verb]`
  * **Для чего:** Ответы на вопросы интервьюера *“А что, если нагрузка вырастет в 100 раз?”*
  * **Примеры:**
    * *“**If** our read traffic **grew** by 10x, we **would introduce** a Redis cache layer and PostgreSQL read replicas.”*
    * *“**If** we **had** strict low-latency requirements, I **would use** gRPC instead of REST.”*
    * *“**If** this service **were** I/O-bound, I **would enable** Java 21 Virtual Threads.”*

### 1.4. Modal Verbs (`can`, `should`, `have to`, `might`) — Trade-offs и архитектурные решения
* **`can` / `cannot`** — физическая возможность / ограничение технологии:
  * *“Virtual threads **can** handle thousands of concurrent I/O calls, but they **cannot** speed up CPU-bound tasks.”*
* **`should` / `shouldn't`** — best practice, рекомендация (Code Review, дизайн):
  * *“We **should** use `record` for DTOs because they are immutable by default. We **shouldn't** expose JPA entities directly in the controller.”*
* **`have to` / `must` / `need to`** — жесткое техническое требование, инвариант:
  * *“To guarantee exactly-once processing, the consumer **has to** be idempotent.”*
* **`might` / `could`** — риск, потенциальный баг или узкое место (bottleneck):
  * *“Unbounded queues **might** lead to an `OutOfMemoryError` under heavy load.”*

### 1.5. Passive Voice (`is/are/was/were + V3`) — Пайплайны данных и обработка запросов
* **Когда использовать:** Когда неважно, *кто* вызывает действие, а важен **жизненный цикл данных** (Request lifecycle, CI/CD, Kafka pipeline, RAG ingestion).
* **Формула:** `[Data / Request / Event] + is/are (или was/were) + [V3 (Past Participle)] + by/in/to [Component]`
* **Топ-10 связок в Passive Voice:**
  * `is validated by` (валидируется чем-то)
  * `is persisted in` (сохраняется в)
  * `is published to` (публикуется в)
  * `is consumed by` (вычитывается кем-то)
  * `is cached in` (кэшируется в)
  * `is indexed in` (индексируется в)
  * `is split into chunks` (разбивается на чанки)
  * `is converted into embeddings` (конвертируется в эмбеддинги)
  * `is garbage-collected` (очищается сборщиком мусора)
  * `is rolled back` (откатывается)
* **Пример полного пайплайна (NexusPay + AegisAI):**
  * *“First, the payment request **is validated** at the API Gateway. Then, the transaction **is saved** in PostgreSQL, and an event **is written** to the Outbox table in the same transaction. Next, the event **is picked up** by a background poller and **published** to Kafka.”*
  * *“In AegisAI, the document **is split** into chunks, each chunk **is converted** into a vector embedding, and the vectors **are indexed** in PostgreSQL using `pgvector`.”*

---

## БЛОК 2. Полный скрипт и шаблоны фраз для Live Coding

Главное правило Live Coding: **никогда не молчать дольше 10 секунд**. Даже на уровне A2 простые короткие фразы создают впечатление структурного Senior-мышления.

### 2.1. Clarifying Requirements (Уточнение условий задачи — первые 2 минуты)
* `Let me make sure I understand the problem correctly.` — Позвольте убедиться, что я правильно понял задачу.
* `So, we are given [input] and we need to return [output], right?` — Итак, нам дано [вход], и нужно вернуть [выход], верно?
* `What are the constraints on the input size? Can the array/list be empty or null?` — Какие ограничения на размер входа? Может ли массив/список быть пустым или null?
* `Can the input contain negative numbers or duplicates?` — Могут ли во входных данных быть отрицательные числа или дубликаты?
* `Is the input already sorted, or can it be in any order?` — Входные данные уже отсортированы или могут быть в любом порядке?
* `Should I optimize for time complexity or memory usage?` — Мне стоит оптимизировать по времени или по памяти?
* `Does the result need to fit in a standard 32-bit integer, or should I use a 64-bit long to avoid overflow?` — Результат помещается в 32-битный `int`, или лучше взять `long`, чтобы избежать переполнения?

### 2.2. Stating Assumptions (Фиксация допущений перед кодингом)
* `I will assume that the input fits in memory.` — Я буду исходить из того, что входные данные помещаются в память.
* `Let’s assume that if the input is null or empty, we return an empty list.` — Допустим, что если вход null или пустой, мы возвращаем пустой список.
* `For simplicity, I'll start with standard single-threaded execution, and we can discuss thread-safety later.` — Для простоты я начну с однопоточного варианта, а потокобезопасность обсудим позже.

### 2.3. Explaining Brute Force First (Объяснение наивного решения за 30 секунд)
* `First, let me briefly mention the brute-force approach.` — Сначала я кратко обозначу решение «в лоб».
* `The naive solution would be to use two nested loops and check every pair.` — Наивным решением было бы использовать два вложенных цикла и проверить каждую пару.
* `However, that would take O(N^2) time, which is too slow for large inputs.` — Однако это займет $O(N^2)$ по времени, что слишком медленно для больших данных.
* `Should I code the brute-force first, or go straight to the optimal solution?` — Мне написать сначала наивное решение или сразу перейти к оптимальному?

### 2.4. Transitioning to the Optimal Approach (Переход к оптимальному алгоритму)
* `We can optimize this from O(N^2) down to O(N) by trading space for time.` — Мы можем оптимизировать это с $O(N^2)$ до $O(N)$, разменяв память на время.
* `Instead of scanning the array repeatedly, we can use a HashMap for O(1) lookups.` — Вместо повторного сканирования массива мы можем использовать `HashMap` для поиска за $O(1)$.
* `Here is my plan: first, I will iterate through the array; second, I will store seen elements in a map...` — Мой план такой: во-первых, я пройдусь по массиву; во-вторых, сохраню встреченные элементы в мапу...
* `We can use the Two Pointers / Sliding Window technique here because...` — Здесь мы можем применить технику двух указателей / скользящего окна, потому что...
* `Does this approach sound good to you before I start writing code?` — Такой подход вам подходит, прежде чем я начну писать код?

### 2.5. Explaining While Coding (Комментирование кода вслух)
* `First, I am adding a guard clause for edge cases at the top.` — Сначала я добавляю проверку граничных случаев в начале метода.
* `Here I initialize a HashMap to keep track of frequencies / indices.` — Здесь я инициализирую `HashMap` для отслеживания частот / индексов.
* `In Java 21, I can use a record / `computeIfAbsent` here to keep the code clean.` — В Java 21 я могу использовать `record` / `computeIfAbsent`, чтобы код был чище.
* `Now I’m moving the left pointer forward while the window condition is violated.` — Теперь я сдвигаю левый указатель вперед, пока условие окна нарушено.

### 2.6. Explaining Time & Space Complexity (Как правильно произносить Big-O)
* **Как читается вслух:**
  * $O(1)$ → *“Big O of one”* или *“constant time / space”*
  * $O(\log N)$ → *“Big O of log N”* или *“logarithmic time”*
  * $O(N)$ → *“Big O of N”* или *“linear time”*
  * $O(N \log N)$ → *“Big O of N log N”* или *“log-linear time”*
  * $O(N^2)$ → *“Big O of N squared”* или *“quadratic time”*
* **Шаблоны фраз:**
  * `The time complexity is O(N) because we traverse the array only once, and each HashMap lookup takes O(1) on average.` — Временная сложность — $O(N)$, так как мы проходим массив один раз, а поиск в `HashMap` в среднем занимает $O(1)$.
  * `The space complexity is O(N) in the worst case because the HashMap can store up to N elements.` — Пространственная сложность — $O(N)$ в худшем случае, так как `HashMap` может хранить до $N$ элементов.
  * `Sorting takes O(N log N) time, which dominates the overall complexity.` — Сортировка занимает $O(N \log N)$, что доминирует в общей сложности.

### 2.7. Dry-Running Code on Edge Cases (Ручная трассировка кода)
* `Let’s dry-run this code with a small example: suppose the input is [2, 7, 11] and target is 9.` — Давайте прогоним код вручную на маленьком примере: допустим, вход `[2, 7, 11]`, а цель `9`.
* `On the first iteration, index i is 0, current value is 2, complement is 7. The map is empty, so we put (2 -> 0) into the map.` — На первой итерации индекс `i` равен 0, значение 2, дополнение 7. Мапа пустая, поэтому кладем `(2 -> 0)` в мапу.
* `On the second iteration, current value is 7, complement is 2. We find 2 in the map and return indices [0, 1].` — На второй итерации значение 7, дополнение 2. Находим 2 в мапе и возвращаем `[0, 1]`.
* `Let’s also check an edge case: what if the array has only one element? The loop won't find a match and will safely return an empty array.` — Проверим также граничный случай: что если в массиве всего один элемент? Цикл не найдет пару и безопасно вернет пустой массив.

### 2.8. Handling Hints When Stuck (Спасательные фразы, если застрял)
* `I am currently stuck on how to avoid the nested loop. Let me think out loud for a second.` — Сейчас я застрял на том, как избавиться от вложенного цикла. Позвольте подумать вслух секунду.
* `My intuition tells me we need to precompute something or use a different data structure, like a heap or a monotonic stack.` — Интуиция подсказывает, что нужно что-то предвычислить или взять другую структуру данных, например кучу или монотонный стек.
* `That’s a great hint, thank you! If we sort the intervals by start time first, we can merge them in a single pass.` — Отличная подсказка, спасибо! Если мы сначала отсортируем интервалы по времени начала, то сможем слить их за один проход.
* `Wait, I just spotted an off-by-one error on line 14. It should be `less than`, not `less than or equal to`.` — Подождите, я заметил ошибку на единицу (off-by-one) в строке 14. Там должно быть `<`, а не `<=`.

---

## БЛОК 3. Шаблоны объяснения глубоких технических тем простыми словами (A2 → B1+)

### 3.1. Универсальные связки причинно-следственных связей (Connectors Matrix)

| Функция в речи | Фраза-связка (Lego Frame) | Перевод / Смысл |
| :--- | :--- | :--- |
| **Что происходит внутри** | `Under the hood, ...` / `Behind the scenes, ...` | Под капотом / За кулисами... |
| **Причина проблемы** | `This leads to a bottleneck because...` | Это приводит к узкому месту, потому что... |
| **Следствие** | `As a result, ...` / `Consequently, ...` | В результате... |
| **Цель механизма** | `To prevent [problem], we use [solution].` | Чтобы предотвратить [проблему], мы используем [решение]. |
| **Компромисс** | `The main downside of this approach is...` | Главный минус этого подхода в том, что... |
| **Контраст / Сравнение** | `Unlike [A], [B] does not block...` | В отличие от [A], [B] не блокирует... |
| **Резюме мысли** | `In short, it gives us [benefit] at the cost of [cost].` | Короче говоря, это дает нам [плюс] ценой [минуса]. |

---

### 3.2. Готовые определения и объяснения по 6 ключевым доменам

#### Домен 1: JVM, Memory Management & Garbage Collection
* **Stack vs. Heap:**
  * *“In the JVM, the **Stack** stores local primitives and references to objects. Each thread has its own stack. The **Heap** is shared across all threads and stores the actual objects.”*
* **Garbage Collection (Generational GC / G1 / ZGC):**
  * *“Under the hood, the Heap is divided into the **Young Generation** (Eden and Survivor spaces) and the **Old Generation**. Most objects die young. When Eden is full, a Minor GC runs and moves surviving objects to Survivor and eventually to the Old Generation. In Java 21, we can use **G1GC** for balanced throughput and latency, or **Generational ZGC** if we need sub-millisecond pause times.”*
* **Memory Leaks in Java:**
  * *“Even with a Garbage Collector, memory leaks can happen if we keep unused object references in static collections, unclosed connections, or `ThreadLocal` variables without calling `.remove()`.”*

#### Домен 2: Multithreading, Concurrency & Java 21 Virtual Threads
* **Race Condition & Thread Safety:**
  * *“A **race condition** happens when two or more threads access shared mutable state at the same time, and the final result depends on execution order. To prevent race conditions, we can use **immutable objects** (like Java `records`), **atomic classes** (`AtomicLong` based on CAS — Compare-And-Swap), or explicit locks (`synchronized` / `ReentrantLock`).”*
* **Platform Threads vs. Virtual Threads (Java 21):**
  * *“Traditional platform threads are 1-to-1 wrappers around OS threads. They are heavy — each thread takes about 1 MB of stack memory, and blocking I/O blocks the underlying OS thread. In Java 21, **Virtual Threads** are lightweight threads managed by the JVM. When a virtual thread hits a blocking I/O operation (like a database query or HTTP call), the JVM **unmounts** it from the carrier OS thread so the OS thread can execute other virtual threads. This gives us high throughput with simple synchronous code.”*

#### Домен 3: PostgreSQL — Indexes, Transactions, Isolation & MVCC
* **B-Tree Indexes & Composite Indexes:**
  * *“An index is a separate data structure (usually a **B-Tree**) that allows PostgreSQL to find rows in $O(\log N)$ time instead of doing a full **Sequential Scan** ($O(N)$). However, indexes slow down `INSERT` and `UPDATE` operations because the B-Tree must be updated too. For composite indexes, column order matters: `(user_id, created_at)` works for queries filtering by `user_id`, but not by `created_at` alone.”*
* **MVCC (Multi-Version Concurrency Control):**
  * *“Under the hood, PostgreSQL uses **MVCC**. When we update a row, Postgres does not overwrite it in place — it creates a new version of the row (a new tuple) and marks the old one as obsolete (`xmin`/`xmax`). Because of this, **readers never block writers, and writers never block readers**. Later, the `VACUUM` process cleans up dead tuples.”*
* **ACID & Isolation Levels / Locking:**
  * *“In PostgreSQL, the default isolation level is **Read Committed**, which prevents dirty reads. For financial operations in **NexusPay**, to prevent lost updates when updating balances, we use either **Optimistic Locking** (via `@Version` column) when contention is low, or **Pessimistic Locking** (`SELECT ... FOR UPDATE`) when contention is high.”*

#### Домен 4: Kafka, Transactional Outbox & Saga Pattern
* **Kafka Architecture (Partitions, Consumer Groups, Offset):**
  * *“In Kafka, a **Topic** is split into **Partitions** for scalability. Message ordering is guaranteed only **within a single partition**, not across the whole topic. That is why we use a business key (like `accountId` or `orderId`) as the partition key, so all events for the same entity go to the same partition. Consumers read in a **Consumer Group** — one partition can be consumed by only one consumer within the same group at a time.”*
* **Dual Write Problem & Transactional Outbox:**
  * *“If we save an order to PostgreSQL and immediately publish an event to Kafka, we face the **Dual Write problem**: if the database commit succeeds but Kafka is down, our system becomes inconsistent. To solve this, we use the **Transactional Outbox pattern**. We save both the business entity and the Outbox event into PostgreSQL within the **same local ACID transaction**. Then a background worker (or Debezium CDC) reads the Outbox table and publishes the event to Kafka with **at-least-once** delivery. Because messages can be delivered more than once, our Kafka consumer must be **idempotent** (we track `event_id` in a `processed_events` table).”*
* **Saga Pattern (Distributed Transactions):**
  * *“Since we cannot use distributed 2-Phase Commit across microservices without hurting availability, we use the **Saga pattern**. A Saga is a sequence of local transactions. If one step fails (for example, payment fails after inventory was reserved), the Saga executes **compensating transactions** to roll back the previous steps semantically.”*

#### Домен 5: Docker & Kubernetes
* **Docker (Image vs. Container, Multi-stage build):**
  * *“A Docker **Image** is an immutable template with application code and dependencies, while a **Container** is a running instance of that image. We use **multi-stage builds** (build with Maven in stage 1, copy only the `.jar` into a slim JRE image in stage 2) to keep production images small and secure.”*
* **Kubernetes (Pods, Deployments, Probes):**
  * *“In Kubernetes, the smallest deployable unit is a **Pod**. A **Deployment** manages ReplicaSets and handles rolling updates with zero downtime. We configure **Liveness probes** (via Spring Boot Actuator) so K8s knows when to restart a deadlocked pod, and **Readiness probes** so K8s doesn't route traffic to a pod while the Spring context is still warming up.”*

#### Домен 6: AI Engineering — LLMs, Embeddings & Hybrid RAG
* **Why RAG (Retrieval-Augmented Generation)?**
  * *“LLMs do not know our private data and can hallucinate. **RAG** solves this by retrieving relevant context from our own knowledge base first and injecting it into the prompt before the LLM generates an answer.”*
* **Vector Search vs. Hybrid RAG (BM25 + Dense Vectors + Reranker):**
  * *“In **AegisAI**, pure vector search (semantic similarity via `pgvector` HNSW index) is great for capturing meaning, but it often fails on exact keyword matches like error codes, SKUs, or function names. That is why we implemented **Hybrid RAG**. We run two searches in parallel: **vector search** for semantic meaning and **BM25 full-text search** for exact keywords. Then we merge the results using **Reciprocal Rank Fusion (RRF)** and pass the top candidates through a **Cross-Encoder Reranker** before sending the final context to the LLM. This dramatically reduces hallucinations.”*

---

## БЛОК 4. Скрипты для System Design секции

На System Design интервьюер ждет **структуру**. Используйте этот 5-шаговый скрипт как рельсы.

### Шаг 1. Clarifying Functional & Non-Functional Requirements (Первые 5 минут)
* `Before jumping into the architecture, I’d like to clarify both functional and non-functional requirements.` — Прежде чем переходить к архитектуре, я хотел бы уточнить функциональные и нефункциональные требования.
* `What are the core features we need to focus on today?` — На каких ключевых фичах нам нужно сфокусироваться сегодня?
* `What is our expected scale in terms of DAU (Daily Active Users) and peak RPS (Requests Per Second)?` — Каков ожидаемый масштаб по DAU и пиковому RPS?
* `Is the system read-heavy or write-heavy? What is the read-to-write ratio (for example, 10:1 or 100:1)?` — Система ориентирована больше на чтение или на запись? Каково соотношение чтений к записям?
* `What is more critical for this domain: strong consistency (like in payments) or high availability and low latency (like in a social feed)?` — Что важнее для этого домена: строгая консистентность (как в платежах) или высокая доступность и низкая задержка?

### Шаг 2. High-Level Design (Верхнеуровневая схема — 10 минут)
* `Let’s start with a high-level architecture and trace the request flow from the client to the database.` — Начнем с верхнеуровневой архитектуры и проследим путь запроса от клиента до БД.
* `At the entry point, clients talk to a Load Balancer (Nginx / AWS ALB) which routes traffic to our API Gateway.` — На входе клиенты обращаются к балансировщику нагрузки, который направляет трафик на наш API Gateway.
* `The API Gateway handles SSL termination, authentication (JWT validation), and rate limiting.` — API Gateway отвечает за SSL-терминацию, аутентификацию и ограничение частоты запросов (rate limiting).
* `Behind the gateway, we have stateless Spring Boot microservices communicating synchronously via REST/gRPC for queries, and asynchronously via Kafka for state-changing events.` — За шлюзом находятся stateless-микросервисы на Spring Boot, которые общаются синхронно по REST/gRPC для запросов чтения и асинхронно через Kafka для событий изменения состояния.

### Шаг 3. Identifying Bottlenecks & Single Points of Failure (Узкие места)
* `Looking at this initial design, the primary database is a Single Point of Failure (SPOF) and will become a bottleneck under high load.` — Глядя на этот начальный дизайн, основная БД является единой точкой отказа и станет узким местом под высокой нагрузкой.
* `If the downstream service is slow, synchronous HTTP calls will exhaust our thread pool and cause cascading failures.` — Если нижестоящий сервис отвечает медленно, синхронные HTTP-вызовы исчерпают пул потоков и вызовут каскадное падение.
* `Hot keys / celebrity users could overload a single database shard or Redis node.` — «Горячие ключи» могут перегрузить отдельный шард БД или узел Redis.

### Шаг 4. Scaling, Caching & Database Strategy (Масштабирование и Кэширование)
* **Stateless Scaling:**
  * *“Since our Spring Boot services are stateless, we can scale them horizontally behind a load balancer using Kubernetes HPA (Horizontal Pod Autoscaler).”*
* **Caching (Cache-Aside / TTL / Eviction):**
  * *“To offload the database on read-heavy paths, I would introduce a **Redis** cache using the **Cache-Aside** pattern. The application checks Redis first; on a cache miss, it queries PostgreSQL, populates Redis with a TTL, and returns the data.”*
  * *“To prevent a **Cache Stampede** when a popular key expires, we can use a distributed lock or early background refresh.”*
* **Database Scaling (Read Replicas, Partitioning, Sharding):**
  * *“First, we separate reads and writes: all writes go to the **Primary (Master)** node, and reads go to **Read Replicas** (accepting a small replication lag).”*
  * *“When the table grows beyond tens of millions of rows, we can use **declarative table partitioning** in PostgreSQL (for example, partitioning transactions by month) and eventally **horizontal sharding** by `user_id` or `account_id`.”*

### Шаг 5. Articulating Trade-offs (Формулировка компромиссов — маркер Senior-уровня)
> Выучите эти 5 фраз наизусть. Каждое архитектурное решение заканчивайте одной из них:
1. `The trade-off here is latency versus consistency.` — Компромисс здесь — задержка против консистентности.
2. `By choosing asynchronous processing via Kafka, we gain high throughput and decoupling, but we have to deal with eventual consistency in the UI.` — Выбирая асинхронную обработку через Kafka, мы выигрываем в пропускной способности и слабой связности, но нам приходится иметь дело с итоговой согласованностью (eventual consistency) в интерфейсе.
3. `Adding a Redis cache reduces database load and gives us sub-millisecond reads, at the cost of cache invalidation complexity and potential stale data.` — Добавление кэша Redis снижает нагрузку на БД и дает чтение быстрее миллисекунды, но ценой сложности инвалидации кэша и риска устаревших данных.
4. `Pessimistic locking guarantees zero conflicts for hot accounts, but the trade-off is lower throughput because concurrent transactions have to wait in line.` — Пессимистическая блокировка гарантирует отсутствие конфликтов для «горячих» счетов, но компромисс — снижение пропускной способности, так как параллельные транзакции ждут в очереди.
5. `Sharding gives us near-infinite write scalability, but we lose cross-shard ACID transactions and joins become expensive.` — Шардирование дает почти бесконечное масштабирование по записи, но мы теряем кросс-шардовые ACID-транзакции, а джойны становятся дорогими.

---

## БЛОК 5. Скрипты для HR & Behavioral Interview

### 5.1. "Tell me about yourself" — 90-секундный Elevator Pitch (Формула: Present → Past/Projects → Why You)

> **Готовый скрипт (читается за 75–90 секунд в спокойном темпе):**
>
> *“Sure! I’m a Backend Engineer specializing in **Java 21, Spring Boot, PostgreSQL, and event-driven microservices with Kafka**, and I also build production-ready **AI and RAG systems**.*
>
> *Over the past couple of years, I’ve focused on building high-reliability backend systems where data consistency and performance really matter. For example, I architected **NexusPay**, a distributed payment processing backend where I solved the dual-write problem using the **Transactional Outbox pattern**, implemented **idempotent Kafka consumers**, and prevented race conditions on account balances using **PostgreSQL locking and Java 21 Virtual Threads**.*
>
> *In parallel, I built **AegisAI**, an enterprise knowledge assistant powered by **Hybrid RAG**. Instead of relying on simple vector search, I combined **PostgreSQL `pgvector` semantic search with BM25 keyword search and a Cross-Encoder Reranker**, which significantly improved retrieval accuracy and cut down hallucinations.*
>
> *I’m someone who cares deeply about clean architecture, testability with Testcontainers, and understanding how tools work under the hood. Right now, I’m looking to join an international engineering team where I can tackle high-load backend challenges and contribute from day one.”*

---

### 5.2. Презентация пет-проектов по формуле STAR + Google XYZ

**Формула Google XYZ:** `Accomplished [X] as measured by [Y], by doing [Z].`

#### Кейс 1: NexusPay (High-Reliability Payment & Ledger System)
* **Situation (Контекст):**
  * *“In **NexusPay**, I was designing a distributed payment and ledger system that needed to process money transfers reliably without double-charging users or losing events when downstream services or Kafka were temporarily unavailable.”*
* **Task (Задача):**
  * *“My goal was to guarantee **zero lost transactions**, prevent race conditions during concurrent balance updates, and ensure **at-least-once event delivery** with **idempotent processing**.”*
* **Action (Что сделал конкретно по стеку):**
  * *“First, I used **Spring Boot 3 and Java 21 Virtual Threads** to handle high concurrent I/O throughput. Second, to prevent lost updates on account balances, I implemented **pessimistic locking (`SELECT FOR UPDATE`) with deterministic lock ordering** by account ID to eliminate deadlocks. Third, to solve the dual-write problem between PostgreSQL and Kafka, I implemented the **Transactional Outbox pattern** with a background poller and deduplication table (`processed_events`) on the consumer side.”*
* **Result (XYZ-результат):**
  * *“As a result, the system achieved **100% financial data consistency** across 10,000+ simulated concurrent transfers in load tests, eliminated race conditions and deadlocks, and survived simulated Kafka broker outages without losing a single payment event.”*

#### Кейс 2: AegisAI (Production Hybrid RAG & AI Agent Platform)
* **Situation (Контекст):**
  * *“When building **AegisAI**, a technical documentation and compliance assistant, I noticed that standard naive RAG with vector search alone often missed exact technical terms, error codes, and API identifiers, leading to incomplete answers or hallucinations.”*
* **Task (Задача):**
  * *“I needed to redesign the retrieval and generation pipeline to achieve high precision on both semantic questions and exact keyword queries while keeping latency low.”*
* **Action (Что сделал конкретно по стеку):**
  * *“I built a **Hybrid RAG pipeline** backed by **Spring Boot and PostgreSQL**. I used structure-aware chunking so code blocks and tables weren't split mid-sentence. For retrieval, I combined **dense vector search (`pgvector` with HNSW index)** and **sparse keyword search (BM25 / Postgres Full-Text Search)**, merged the results using **Reciprocal Rank Fusion (RRF)**, and added a **Cross-Encoder Reranking step**. I also added strict prompt guardrails requiring the LLM to cite source chunk IDs.”*
* **Result (XYZ-результат):**
  * *“This architecture improved retrieval hit-rate on technical queries by over **35% compared to vector-only search**, reduced hallucinations to near zero by enforcing grounded citations, and kept p95 retrieval latency under **200 milliseconds**.”*

---

### 5.3. Ответы на сложные поведенческие вопросы (Bugs, Conflicts, Deadlines)

#### Вопрос 1: "Tell me about a challenging bug you fixed." (История про баг: N+1 / Deadlock / Memory Leak)
* **Скрипт ответа (Deadlock / Connection Pool Exhaustion):**
  * *“While load-testing **NexusPay**, I noticed that under concurrent transfers between the same pair of accounts, requests started timing out and HikariCP threw connection pool exhaustion errors.*
  * *I inspected `pg_stat_activity` and thread dumps, and identified the root cause: when User A transferred to User B at the same time as User B transferred to User A, two transactions locked the rows in opposite order, causing a classic database deadlock and holding connections too long.*
  * *To fix this, I enforced **deterministic lock ordering**: before acquiring `SELECT FOR UPDATE` locks, the service always sorts the account IDs in ascending order. I also added an integration test with **Testcontainers** and `CompletableFuture` running 50 concurrent cross-transfers. After the fix, deadlocks dropped to zero and p99 latency stabilized.”*

#### Вопрос 2: "Tell me about a time you disagreed with a teammate or tech lead." (Конфликт / Архитектурный спор)
* **Правило:** Никогда не критикуйте личность — переводите спор в плоскость **данных, бенчмарков и trade-offs**.
* **Скрипт ответа:**
  * *“During a design review, a teammate suggested using synchronous REST calls between the Order Service and three downstream services because it was faster to implement. I was concerned that if one downstream service slowed down, it would cause cascading failures and hurt checkout availability.*
  * *Instead of arguing based on opinions, I proposed a quick comparison table of trade-offs and built a small prototype showing how a slow downstream mock blocked our thread pool. Together, we agreed on a pragmatic compromise: we kept the initial validation synchronous, but moved notifications and analytics to Kafka via an Outbox table. That kept the code simple while protecting the core checkout flow.”*

#### Вопрос 3: "How do you handle tight deadlines or changing requirements?" (Дедлайны)
* **Скрипт ответа:**
  * *“When facing a tight deadline, I focus on **scope prioritization and communication**. First, I align with the Product Owner or Tech Lead to separate **must-have core functionality (MVP)** from nice-to-have features. Second, I ship a clean, reliable vertical slice with automated tests for the critical path first, and document any deliberate simplifications as tech debt tickets so nothing is forgotten. Importantly, I communicate early if I see a risk, rather than waiting until the deadline day.”*

---

## БЛОК 6. Английский для ежедневной работы в международной команде

### 6.1. Daily Standup Formulas (Шаблон на 45 секунд: Yesterday → Today → Blockers)
* **Вчера (Past Simple / Present Perfect):**
  * *“Yesterday I worked on `[ticket-123]` — I implemented the Kafka consumer for payment events and wrote integration tests with Testcontainers.”*
  * *“I have already opened the PR and addressed the initial review comments.”*
* **Сегодня (Future / Plan):**
  * *“Today I’m going to merge `[ticket-123]` once it’s approved, and then pick up `[ticket-124]` to optimize the slow query in the reporting endpoint.”*
* **Блокеры (Blockers):**
  * *“No blockers on my side.”* (Блокеров нет.)
  * *“I have one minor blocker: I’m waiting for the updated API contract from the frontend/platform team. Could someone point me to the right person after the standup?”*

### 6.2. Code Review / PR Comments (Вежливый, конструктивный тон — Non-defensive English)

| Ситуация | Как писать НЕ надо (Токсично / Слишком резко) | Как писать НАДО (Международный стандарт) |
| :--- | :--- | :--- |
| **Указать на баг / N+1** | *“This code is broken. It causes N+1 queries.”* | *“It looks like calling `getOrders()` inside the loop might trigger an N+1 query issue. Could we use `@EntityGraph` or `JOIN FETCH` in the repository instead?”* |
| **Предложить улучшение (Nit)** | *“Change this DTO to a record.”* | *“Nit (non-blocking): since we are on Java 21, what do you think about using a `record` here to make the DTO immutable?”* |
| **Попросить тест** | *“You forgot tests for edge cases.”* | *“The happy path looks great! Could we also add a unit test covering the case when the Kafka payload is null or duplicate?”* |
| **Ответить на комментарий в своем PR** | *“Ok, fixed.”* / *“No, you are wrong.”* | *“Good catch, fixed in the latest commit (`a1b2c3d`).”* / *“Good point! I chose this approach because `[reason]`, but I’m happy to switch if you think `[alternative]` is cleaner.”* |

### 6.3. Async Slack Messages (Структура идеального сообщения: Context → Finding → Ask)
> В международных командах запрещено писать просто *“Hi”* и ждать ответа (**No Hello rule**). Всегда пишите весь контекст сразу:

* **Шаблон 1: Запрос ревью PR**
  * *“Hey team 👋 When you have a moment, could someone take a look at PR #142 (`[link]`)? It adds idempotency keys to the payment webhook. It’s ~150 lines of code, mostly in `WebhookService` and tests. No rush, aiming to merge by tomorrow morning. Thanks!”*
* **Шаблон 2: Вопрос с предварительным ресерчем (Rule of 30 minutes)**
  * *“Hi @alex! Quick question regarding the `user-events` Kafka topic. I’m implementing the consumer for `[ticket-45]` and noticed that some messages have a `null` partition key in staging. I checked the producer in `AuthService` (line 84) and it seems we pass `event.getUserId()` before the ID is generated. Is that intentional, or should I open a fix PR?”*

### 6.4. Asking for Help / Clarifying Tasks (Уточнение требований на груминге/планировании)
* `Could we clarify the acceptance criteria for this edge case? What should happen if the external API returns a 429 Too Many Requests?` — Можем уточнить критерии приемки для этого граничного случая? Что должно происходить, если внешний API вернет 429?
* `Is database migration included in the scope of this ticket, or should we split it into a separate task?` — Миграция БД входит в скоуп этого тикета или лучше вынести ее в отдельную задачу?
* `Just to make sure we are on the same page: I will implement X first, and we will leave Y for the next sprint, right?` — Просто чтобы убедиться, что мы друг друга правильно поняли: сначала я реализую X, а Y оставим на следующий спринт, верно?

### 6.5. Incident Discussions (Коммуникация во время падения продакшена — спокойно и по фактам)
* **Принятие в работу:**
  * *“I see the alert on high 5xx error rates in `payment-service`. Looking into logs and metrics now — will post an update in 5 minutes.”*
* **Локализация проблемы:**
  * *“Update: the issue started right after deployment `v1.4.2` at 14:10 UTC. HikariCP connections are maxed out due to a missing index on the new `audit_logs` query.”*
* **Митигация (Сначала тушим пожар, потом чиним код):**
  * *“To restore service immediately, I am rolling back `payment-service` to `v1.4.1`. Rollback is in progress.”*
* **Закрытие инцидента:**
  * *“Update: rollback completed, error rates and latency are back to normal. I will prepare a post-mortem and add the missing `CREATE INDEX CONCURRENTLY` migration before re-deploying.”*

---

## ПЛАН СПРИНТА НА 8 НЕДЕЛЬ: КАК ВЫУЧИТЬ ЭТОТ КОРПУС БЕЗ ЗУБРЕЖКИ СЛОВАРЕЙ

| Недели | Фокус спринта | Ежедневная практика (30 мин/день) | Артефакт / Критерий готовности (Definition of Done) |
| :--- | :--- | :--- | :--- |
| **Недели 1–2** | **Грамматический каркас + Storytelling** (Блоки 1 и 5) | Проговаривать вслух (с таймером на диктофон) 90-сек питч и истории про **NexusPay** и **AegisAI** в Past Simple / Present Simple. | Рассказываете о себе и 2 пет-проектах за 3 минуты без подглядывания в текст и без пауз длиннее 3 секунд. |
| **Недели 3–4** | **Глубокая техбаза простыми словами** (Блок 3) | Метод «Резиновой уточки»: брать по 3 вопроса в день (JVM, Locks, MVCC, Outbox, Hybrid RAG) и объяснять их вслух за 60 секунд через связки `Under the hood...`, `To prevent...`. | 20 главных технических концепций отскакивают от зубов простыми предложениями из 10–12 слов. |
| **Недели 5–6** | **Live Coding вслух** (Блок 2) | Решать по 1 алгоритмической задаче (Easy/Medium), проговаривая **каждый шаг вслух** на английском: Clarify → Brute Force → Optimal → Big-O → Dry Run. | Способны решать задачу 30 минут, комментируя свои действия на автопилоте фразами из Блока 2. |
| **Недели 7–8** | **System Design & Рабочая коммуникация** (Блоки 4 и 6) | Разбирать 2 архитектуры в неделю (Payment System, RAG Pipeline, Rate Limiter, Notification Service) по 5-шаговому скрипту + писать mock-комментарии к PR и стендапы. | Проводите полный 35-минутный System Design на английском, заканчивая каждый выбор фразой `The trade-off here is...`. |