# Анализ рынка труда 2026–2027: Strong Junior / AI-Augmented Engineer (Global & СНГ)

## Контекст рынка 2026–2027 («Barbell Market» & Конец модели подмастерья)
В 2026–2027 годах классический найм «джунов под обучение написанию CRUD и перекладыванию JSON» мертв — эту работу делают AI-агенты за секунды. Рынок поляризовался: компании (в СНГ: Яндекс, Т-Банк, Ozon, Avito, Kaspi, Kolesa, Wildberries; глобально: BigTech, AI-native стартапы, FinTech) нанимают выпускников и третьекурсников только в том случае, если они соответствуют профилю **AI-Augmented Systems Engineer (Strong Junior / Pre-Middle)**:
- **Paid to Ship & Verify:** От кандидата ждут понимания **почему** код работает именно так, какие **trade-offs** у архитектурного решения, как **верифицировать** сгенерированный AI код (безопасность, race conditions, утечки памяти, N+1, деградация БД под нагрузкой).
- **Секции собеседований в СНГ и Global:** Помимо Алгоритмов (LeetCode Medium) и Глубокого Языка/Платформы, уже на стажерские и Junior+ позиции дают **System Design / DB Design**, **Code Review / Debugging секцию** (найти баги конкурентности, транзакционности и безопасности в готовом PR) и спрашивают **Observability + отказоустойчивость**.

---

## ЧАСТЬ 1. Полная таксономия технических тем и микро-топиков (Чего не знают 95% студентов)

### 1. Backend & Systems (Java 21+ / Spring Boot 3.x + полиглот-кругозор Go/Python/Rust)

#### 1.1. JVM Internals & Memory Model (JMM)
- **Устройство памяти JVM:** Heap (Eden, Survivor S0/S1, Old/Tenured, Humongous regions в G1), Metaspace (compressed class space), Thread Stack, Direct ByteBuffers (Off-Heap память, `Unsafe` / Foreign Function & Memory API — Project Panama).
- **Java Memory Model (JMM, JSR-133):**
  - **Happens-Before** гарантии (мониторы, `volatile`, старт/join потока, final поля).
  - **Hardware симпатия:** CPU caches (L1/L2/L3), Cache Lines (64 bytes), **False Sharing** (`@Contended`), Store Buffer, Invalidation Queue, Memory Barriers (LoadLoad, StoreStore, LoadStore, StoreLoad).
  - Устройство Object Header: **Mark Word** (хэшкод, возраст объекта, флаги блокировки: unlocked, biased [убран в новых JDK], lightweight lock, heavyweight lock / монитор OS) и **Klass Word** (Compressed OOPs — почему хип ~32GB является порогом).
- **Garbage Collection (GC) в деталях:**
  - **G1GC** (Remembered Sets, Card Table, SATB — Snapshot-At-The-Beginning, Mixed GC, Evacuation Failure).
  - **ZGC (Generational ZGC в Java 21+):** Colored Pointers, Load Barriers, паузы < 1 мс на терабайтных хипах.
  - Чтение GC логов, снятие Heap Dump (`jmap`, JFR — Java Flight Recorder, Async-Profiler, Flame Graphs CPU/Allocations/Locks).

#### 1.2. Java 21+ & Concurrency (Многопоточность на уровне сеньора)
- **Project Loom (Virtual Threads):**
  - Архитектура: Carrier Threads (`ForkJoinPool`), Continuation (монтирование/размонтирование стека из Heap при блокирующем syscall/IO).
  - **Проблема Pinning:** почему `synchronized` блок с IO внутри (до JEP 491 в JDK 24) или нативный фрейм JNI «прибивает» виртуальный поток к системному (`ReentrantLock` вместо `synchronized`).
  - **Structured Concurrency** (`StructuredTaskScope`: `ShutdownOnFailure`, `ShutdownOnSuccess`) и **Scoped Values** (иммутабельная и легкая замена `ThreadLocal` для виртуальных потоков).
- **Классический `java.util.concurrent`:**
  - **AQS (AbstractQueuedSynchronizer):** CLH-очередь, CAS (`Compare-And-Swap`), `LockSupport.park/unpark`.
  - Разница между `ReentrantLock` (fair/non-fair), `ReadWriteLock`, `StampedLock` (оптимистичное чтение).
  - **Concurrent Collections:** `ConcurrentHashMap` (CAS на пустых бакетах, `synchronized` на голове бакета, красно-черные деревья при >8 элементах, `size()` через `CounterCell` по аналогии с `LongAdder`), `CopyOnWriteArrayList`, `ArrayBlockingQueue` vs `LinkedBlockingQueue`.
  - `CompletableFuture`: пулы потоков, комбинирование, обработка ошибок, асинхронные тайм-ауты.

#### 1.3. Network I/O & Linux NIO
- **Модели ввода-вывода:** Blocking I/O vs Non-blocking I/O vs I/O Multiplexing (`select`, `poll`, **`epoll`** в Linux — `epoll_create`, `epoll_ctl`, `epoll_wait`, Edge-Triggered vs Level-Triggered, проблема Thundering Herd) vs Asynchronous I/O (`io_uring`).
- **Java NIO / Netty:** Channels, Buffers, Selectors, EventLoop, **Zero-Copy** (`FileChannel.transferTo` -> системный вызов `sendfile`).
- **Протоколы:**
  - TCP (3-way handshake, TCP Backlog: `somaxconn`, TIME_WAIT, Congestion Control, Head-of-Line blocking).
  - HTTP/1.1 vs HTTP/2 (мультиплексирование, HPACK) vs HTTP/3 (QUIC поверх UDP).
  - **gRPC & Protobuf:** бинарная сериализация, varint кодирование, стриминг (unary, server/client/bidirectional), deadlines, backpressure.

#### 1.4. Spring Boot 3.x & Hibernate / JPA Internals
- **Spring Core под капотом:**
  - Жизненный цикл бина: `BeanDefinitionReader` -> `BeanFactoryPostProcessor` -> конструктор -> DI -> `BeanPostProcessor` (`postProcessBeforeInitialization` / `postProcessAfterInitialization` — **где создаются прокси!**).
  - **JDK Dynamic Proxy vs CGLIB:** почему `@Transactional` или `@Async` не срабатывают при **self-invocation** (вызов метода внутри того же класса), не работают на `private`/`final` методах.
  - Spring Boot AutoConfiguration: `@ConditionalOn...`, `META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports`, AOT-компиляция и GraalVM Native Image.
- **Spring Transaction Management:**
  - Как работает `TransactionInterceptor` + `PlatformTransactionManager` + `TransactionSynchronizationManager` (привязка JDBC Connection к `ThreadLocal`).
  - **Propagation уровни:** `REQUIRED`, `REQUIRES_NEW` (новый физический коннект из пула -> риск **Connection Pool Deadlock** под нагрузкой!), `NESTED` (Savepoints).
  - Подводные камни: `rollbackFor` (по умолчанию откат только на `RuntimeException` и `Error`, но НЕ на checked exceptions), долгие транзакции (вызов внешнего HTTP API или отправка в Kafka внутри `@Transactional` держит соединение из HikariCP и блокировки в БД).
- **JPA / Hibernate Internals:**
  - Persistence Context (First-Level Cache), Dirty Checking (снапшоты состояния при загрузке, `readOnly = true` отключает снапшоты и экономит память/CPU), Flush modes.
  - **Проблема N+1:** диагностика и решение (`JOIN FETCH`, `@EntityGraph`, `@BatchSize`, DTO Projections). Почему `JOIN FETCH` + пагинация (`Pageable`) приводит к `HHH90003004: firstResult/maxResults specified with collection fetch; applying in memory!` (OOM в продакшене) и как это чинить (двухшаговый запрос с ID).
  - Оптимистичные (`@Version`) vs Пессимистичные блокировки (`PESSIMISTIC_WRITE` -> `SELECT ... FOR UPDATE`, `SKIP LOCKED`).
  - **HikariCP:** сайзинг пула (`connections = (core_count * 2) + effective_spindle_count`), `connectionTimeout`, `maxLifetime`, leakDetectionThreshold.

#### 1.5. Полиглот-инструментарий (Go / Python / Rust)
- **Go:** планировщик **GMP** (Goroutines, Machines/OS Threads, Processors), Work Stealing, каналы (структура `hchan`, буферизованные/небуферизованные), `context.Context` для отмены и дедлайнов, почему Go идеален для сетевых прокси/CLI/высоконагруженных микросервисов.
- **Python (для AI/ML сервисов):** `asyncio` event loop, GIL (и free-threaded Python 3.13+), FastAPI + Pydantic v2 (Rust-core валидация), `uv`.
- **Rust (базовое понимание концепций):** Ownership, Borrowing, Lifetimes, отсутствие GC, почему на нем пишут векторные БД (Qdrant), движки (Polars, Tantivy) и системные компоненты.

---

### 2. Database Internals & Data-Intensive Systems

#### 2.1. PostgreSQL Internals (Любимая тема Яндекса, Т-Банка, Ozon, Avito)
- **Архитектура хранения и памяти:**
  - Heap-файлы, страницы по **8 KB** (Page Header, Line Pointers / ItemId, Tuples), **TOAST** (хранение больших полей >2KB: сжатие + вынос в отдельную таблицу).
  - **Shared Buffers**, OS Page Cache, **WAL (Write-Ahead Log):** почему запись в WAL последовательная и быстрая, `fsync`, `synchronous_commit`, Checkpointer, Background Writer.
- **MVCC (Multi-Version Concurrency Control) в Postgres:**
  - Системные поля кортежа: `xmin` (ID транзакции вставки), `xmax` (ID транзакции удаления/обновления), `ctid` (физический адрес: блок + смещение).
  - **Почему `UPDATE` в Postgres = `DELETE` + `INSERT`** новой версии кортежа.
  - **Снимки данных (Snapshots)** и видимость версий, Hint Bits.
  - **Уровни изоляции и аномалии:** Dirty Read, Non-Repeatable Read, Phantom Read, **Lost Update** (потерянное обновление — НЕ предотвращается на `Read Committed` без атомарного `UPDATE x = x + 1` или `SELECT FOR UPDATE`!), **Write Skew** (аномалия рассинхронизации записи — решается только `Serializable` / SSI — Serializable Snapshot Isolation или явными блокировками).
- **VACUUM & Autovacuum:**
  - Откуда берутся **Dead Tuples** и **Table/Index Bloat** (распухание таблиц).
  - **HOT-updates (Heap-Only Tuple):** обновление без модификации индексов, если измененное поле не индексируется и на странице 8 KB есть место (`fillfactor`).
  - **Transaction ID Wraparound** (переполнение 32-битного счетчика `txid` через ~2 млрд транзакций, заморозка кортежей `VACUUM FREEZE`).
  - Настройка Autovacuum для hot-таблиц (`autovacuum_vacuum_scale_factor`, `cost_limit`).
- **Индексы (Глубокое понимание):**
  - **B-Tree** (Lehman & Yao алгоритм в PG): структура узлов, селективность, кардинальность, **Composite Indexes** (правило левого префикса, порядок колонок: равенство -> сортировка/диапазон), **Covering Indexes** (`INCLUDE` -> **Index-Only Scan** и роль **Visibility Map**), **Partial Indexes** (`WHERE status = 'PENDING'`).
  - **GIN (Generalized Inverted Index):** полнотекстовый поиск, `JSONB` (`@>`, `?`), массивы; проблема медленной записи и `fastupdate` pending list.
  - **GiST:** геоданные (PostGIS), диапазоны (`tsrange`, Exclusion Constraints — например, запрет пересечения бронирований переговорки на уровне БД!).
  - **BRIN (Block Range Index):** крошечный индекс (минимакс по блокам) для огромных append-only таблиц временных рядов (логи, транзакции), отсортированных физически по времени.
  - **pgvector (HNSW vs IVFFlat):** векторные индексы для AI/RAG прямо в PostgreSQL (trade-off: память, recall, скорость построения).
- **Оптимизатор и `EXPLAIN (ANALYZE, BUFFERS)`:**
  - Чтение плана: `Seq Scan`, `Index Scan`, `Index Only Scan`, `Bitmap Index Scan` + `Bitmap Heap Scan` (что значит `Recheck Cond` и `lossy` блоки, когда не хватило `work_mem`).
  - Алгоритмы соединения: **Nested Loop**, **Hash Join** (in-memory vs batch/spill to disk при нехватке `work_mem`), **Merge Join**.
  - Статистика планировщика (`pg_stats`: гистограммы, MCV — Most Common Values, `ANALYZE`).
- **Масштабирование PostgreSQL:**
  - **Партиционирование (Declarative Partitioning):** Range, List, Hash; Partition Pruning.
  - **Репликация:** Физическая (Streaming Replication по WAL: синхронная / асинхронная, Replication Lag) vs Логическая (Logical Decoding, CDC — Debezium).
  - **Connection Pooling:** почему 1000 коннектов убивают PG (процессная модель + переключение контекста + snapshot-ы) и зачем нужен **PgBouncer** (Transaction pooling mode и его ограничения на prepared statements).
  - **Шардирование:** выбор Shard Key, проблема Cross-Shard Joins и распределенных транзакций, решардинг (Consistent Hashing, виртуальные бакеты).

#### 2.2. Redis (In-Memory Data Structures)
- Однопоточный Event Loop + I/O Threads (Redis 6+), структуры данных под капотом (SDS, SkipList в `ZSET`, Quicklist, Hash tables с инкрементальным рехэшированием).
- **Персистентность:** RDB (fork + Copy-on-Write) vs AOF (`everysec`, `always`).
- **Паттерны кэширования:** Cache-Aside, Read-Through, Write-Through, Write-Behind.
- **Проблемы кэша под высокой нагрузкой и их решение:**
  - **Cache Stampede (Thundering Herd):** истечение популярного ключа -> 10 000 потоков одновременно бьют в БД. Решение: Distributed Lock / Singleflight (в Go) / Probabilistic Early Expiration (PER — алгоритм XFetch).
  - **Cache Penetration:** запросы несуществующих ID мимо кэша в БД. Решение: **Bloom Filter** или кэширование `null` с коротким TTL.
  - **Cache Avalanche:** одновременное истечение тысяч ключей. Решение: Jitter (случайный разброс к TTL).
- **Eviction Policies:** `allkeys-lru`, `volatile-lru`, `allkeys-lfu`.
- **Redis Cluster vs Redis Sentinel:** хэш-слоты (16384), failover.

#### 2.3. Apache Kafka (Event Streaming Internals)
- **Архитектура:** Topics, **Partitions** (единица параллелизма и упорядоченности!), Segments, Offset, Consumer Groups (правило: в одной Consumer Group одну партицию читает максимум один Consumer).
- **Почему Kafka такая быстрая:** Sequential Disk I/O, OS Page Cache, **Zero-Copy** (`sendfile` из файлового кэша ОС прямо в сокет сетевой карты в обход JVM Heap), батчинг и сжатие (LZ4/Zstd).
- **Гарантии доставки и надежности:**
  - Producer: `acks=0`, `acks=1`, `acks=all (-1)` + `min.insync.replicas=2` + `enable.idempotence=true` (PID + Sequence Number).
  - Consumer: ручной коммит оффсетов, обработка дубликатов, **Consumer Rebalance** (Eager vs Incremental Cooperative Rebalancing, почему долгая обработка батча вызывает исключение консьюмера из группы по `max.poll.interval.ms`).
  - **DLQ (Dead Letter Queue)** и паттерн **Retry Topics** с экспоненциальным бэкоффом (не блокируя основную партицию).

#### 2.4. ClickHouse (OLAP & Аналитика)
- **Columnar Storage (Колоночное хранение):** почему агрегации по миллиарду строк выполняются за миллисекунды (векторизованное исполнение SIMD, чтение только нужных колонок, высочайшая степень сжатия LZ4/ZSTD + кодеки Delta/DoubleDelta).
- **Семейство движков MergeTree:** `MergeTree`, `ReplacingMergeTree` (асинхронная дедупликация при фоновых слияниях — почему нужен `FINAL` или `argMax`), `SummingMergeTree`, `AggregatingMergeTree`.
- **Primary Key vs Sorting Key (`ORDER BY`):** разреженный первичный индекс (Sparse Index — одна засечка на каждые `index_granularity = 8192` строк). Почему в ClickHouse нельзя делать частые точечные `UPDATE/DELETE` (мутации) и нужно писать батчами.

---

### 3. Distributed Systems & Reliability (Отказоустойчивость в продакшене)

- **Idempotency (Идемпотентность) — священный Грааль финтеха и биллинга:**
  - Паттерн `Idempotency-Key`: хранение ключа в БД (`UNIQUE` индекс) или Redis со стейт-машиной (`IN_PROGRESS`, `COMPLETED`, `FAILED`), возврат сохраненного ответа при ретрае клиента, защита от гонки через атомарный `INSERT ... ON CONFLICT DO NOTHING` или блокировку.
- **Проблема Dual Write (Двойной записи) и способы ее решения:**
  - Почему нельзя просто сделать `repository.save(order)` и `kafkaTemplate.send(event)` (если БД закомитится, а Kafka упадет или поднимется исключение — система разъедется).
  - **Transactional Outbox Pattern:** запись бизнес-сущности и события в таблицу `outbox_events` в **одной локальной ACID-транзакции**, затем вычитывание через Polling Publisher (`SELECT ... FOR UPDATE SKIP LOCKED`) или **CDC (Debezium)** из WAL лога Postgres в Kafka.
  - **Transactional Inbox Pattern / Consumer Deduplication:** таблица `processed_events (event_id PK)` на стороне получателя (обеспечивает **Effectively-Once** обработку поверх At-Least-Once доставки Kafka).
- **Распределенные транзакции:**
  - Почему **2PC (Two-Phase Commit)** — антипаттерн в микросервисах (блокирующий координатор, деградация латентности по самому медленному узлу).
  - **Saga Pattern:** Choreography (на событиях) vs Orchestration (координатор-стейт-машина), **компенсирующие транзакции** (семантический откат), проблема отсутствия изоляции в Сагах (Dirty Reads между шагами) и семантические блокировки (резервирование средств/товара на холде).
- **Resilience Patterns (Resilience4j / Envoy):**
  - **Timeouts:** Connection timeout vs Read/Request timeout, распространение дедлайнов.
  - **Retries:** Exponential Backoff + **Full Jitter** (почему ретраи без джиттера добивают падающий сервис — Retry Storm).
  - **Circuit Breaker:** состояния `CLOSED -> OPEN -> HALF_OPEN`, пороги ошибок и медленных вызовов.
  - **Rate Limiting:** алгоритмы **Token Bucket**, **Leaky Bucket**, **Fixed Window**, **Sliding Window Log / Counter** (реализация распределенного лимитера на **Redis + Lua-скрипт** для атомарности).
  - **Bulkhead:** изоляция пулов потоков / семафоров, чтобы деградация одного внешнего сервиса не исчерпала все потоки приложения.
- **Distributed Locks (Распределенные блокировки):**
  - Redis (`SET resource_name my_random_value NX PX 30000`, удаление только через Lua-скрипт с проверкой своего `my_random_value`), дискуссия Мартина Клеппманна о Redlock и **Fencing Tokens** (монотонно растущий токен для защиты от GC-пауз), PostgreSQL **Advisory Locks** (`pg_try_advisory_xact_lock`).
- **Observability (Три столпа наблюдаемости — стандарт 2026):**
  - **Logs:** структурированные JSON-логи (ECS/Logback), корреляция через `trace_id` и `span_id` в MDC.
  - **Metrics (Micrometer + Prometheus + Grafana):** типы метрик (`Counter`, `Gauge`, `Histogram`, `Summary`), почему среднее время ответа (average latency) бесполезно и нужно смотреть **p95 / p99 / p99.9** по гистограммам (`histogram_quantile`), **RED** (Rate, Errors, Duration) и **USE** (Utilization, Saturation, Errors) методологии, проблема **High Cardinality labels** (почему нельзя класть `user_id` в тег метрики Prometheus).
  - **Distributed Tracing (OpenTelemetry + Jaeger/Tempo):** автоматическая и ручная инструментация, передача контекста (`traceparent` W3C Trace Context) через HTTP-заголовки и заголовки сообщений Kafka.

---

### 4. DevOps, Cloud & Security

- **Docker & Контейнеризация под капотом:**
  - Как работает контейнер в Linux: **Namespaces** (`pid`, `net`, `mnt`, `ipc`, `uts`, `user` — изоляция видимости), **cgroups v2** (лимиты CPU и RAM), **OverlayFS** (слои образа, Copy-on-Write).
  - **Production Dockerfile:** Multi-stage builds, кэширование слоев зависимостей, запуск от **non-root** пользователя, Distroless / Alpine образы, почему нужно явно задавать `-XX:MaxRAMPercentage=75.0` для JVM в контейнере и почему `OOMKilled` (exit code 137) случается из-за Off-Heap памяти (Metaspace, Thread Stacks, Direct Buffers), если отдать хипу 100% лимита контейнера.
- **Kubernetes (K8s) для разработчика:**
  - Архитектура: Pod, Deployment, ReplicaSet, Service (ClusterIP, NodePort, LoadBalancer), Ingress / Gateway API, ConfigMap, Secret.
  - **Probes (Критично на собесах!):**
    - `startupProbe` (ждет долгого старта Spring Boot).
    - `livenessProbe` (если упала — K8s перезапускает под; **почему в liveness НЕЛЬЗЯ проверять подключение к БД или внешним сервисам** — иначе при кратковременном моргании БД Kubernetes убьет все поды одновременно!).
    - `readinessProbe` (если упала — K8s временно убирает под из балансировки Service Endpoints).
  - **Graceful Shutdown:** получение `SIGTERM` -> остановка приема новых запросов -> завершение текущих транзакций и консьюмеров Kafka в течение `terminationGracePeriodSeconds` -> `SIGKILL`.
  - Requests vs Limits (CPU throttling при низких CPU limits, OOMKill при превышении Memory limits).
- **CI/CD & Testing Pyramid:**
  - **Testcontainers (Абсолютный мастхэв):** интеграционные тесты с реальным PostgreSQL, Kafka, Redis и MinIO в Docker-контейнерах вместо фейкового H2 в памяти.
  - Контрактное тестирование и статический анализ в пайплайне (GitHub Actions / GitLab CI): линтеры, сборка, прогон тестов, сканирование уязвимостей (Trivy/Dependabot).
- **Linux Internals & CLI Troubleshooting:**
  - Виртуальная память, Page Faults, Swap, File Descriptors, Inodes.
  - Системные вызовы (`strace`), диагностика на живом сервере за 60 секунд (методика Брендана Грегга): `uptime`, `dmesg -T`, `vmstat 1`, `mpstat -P ALL 1`, `pidstat 1`, `iostat -xz 1`, `free -m`, `ss -tulnp` / `netstat`.
- **Application Security (SecDevOps):**
  - **OAuth 2.1 & OpenID Connect (OIDC):** Authorization Code Flow + **PKCE**, разница между Access Token (короткоживущий JWT) и Refresh Token (ротация, хранение в `HttpOnly, Secure, SameSite=Strict` cookie), устройство JWT (Header, Payload, Signature: HMAC vs RSA/ECDSA, проверка `iss`, `aud`, `exp`, `JWKS` endpoint для публичных ключей, проблема отзыва JWT до истечения TTL).
  - **OWASP Top 10 & AI Security:**
    - **IDOR / BOLA (Broken Object Level Authorization):** проверка прав доступа не только на роль, но и на владение ресурсом (`WHERE id = :id AND owner_id = :currentUserId`).
    - SQL Injection (почему конкатенация в динамических сортировках `ORDER BY` уязвима даже при использовании JPA), SSRF (Server-Side Request Forgery), CSRF, XSS.
    - Хранение паролей (**Argon2id** / BCrypt) и секретов (Vault / K8s Secrets, никаких секретов в Git).

---

### 5. AI Engineering & LLM Orchestration (Суперсила кандидата 2026–2027)

В 2026 году бэкенд-разработчик, умеющий встроить LLM в надежный production-контур с детерминированными гарантиями, ценится на 30–50% выше классического джуна.

- **Production RAG (Retrieval-Augmented Generation) — далеко за пределами `LangChain Hello World`:**
  - **Ingestion Pipeline:** парсинг сложных документов (PDF, таблицы, Markdown), **Semantic & Structure-Aware Chunking** (разбиение по заголовкам/AST с перекрытием + сохранение метаданных документа и родительского контекста — **Parent-Child / Small-to-Big Retrieval**).
  - **Embeddings & Vector Search:** модели эмбеддингов, косинусное расстояние vs скалярное произведение, HNSW индекс в **pgvector** / **Qdrant**, фильтрация по метаданным (Pre-filtering vs Post-filtering).
  - **Hybrid Search (Гибридный поиск):** почему чистый векторный поиск плохо ищет точные артикулы, имена функций и аббревиатуры. Комбинация **Dense Retrieval (Vectors)** + **Sparse Retrieval (BM25 / Full-Text Search в Postgres/Elasticsearch)** со слиянием рангов через **RRF (Reciprocal Rank Fusion)** + финальный **Cross-Encoder Reranker** (например, BGE-Reranker / Cohere Rerank).
  - **Query Transformation:** HyDE (Hypothetical Document Embeddings), Multi-Query / Sub-query decomposition.
- **Agentic Workflows & Structured Outputs:**
  - **Structured Outputs / Constrained Decoding:** гарантированный возврат строго типизированного JSON по JSON Schema / Pydantic / Java Record.
  - **Tool Calling (Function Calling) & MCP (Model Context Protocol):** проектирование идемпотентных, безопасных инструментов для агента, обработка ошибок инструментов, лимит шагов цикла (`ReAct` / Plan-and-Execute / Графы состояний на LangGraph / Spring AI).
  - **Human-in-the-Loop (HITL):** приостановка выполнения графа агента перед критическими мутирующими действиями (списание денег, удаление данных, мерж PR) до подтверждения пользователем.
- **LLM Reliability, Cost & Latency Engineering:**
  - **Streaming:** передача токенов клиенту по **SSE (Server-Sent Events)** или WebSockets для снижения воспринимаемого **TTFT (Time To First Token)**.
  - **Semantic Caching:** кэширование ответов LLM в Redis/pgvector по семантической близости запроса, а также использование **Prompt Caching** провайдеров.
  - **Model Routing & Fallbacks:** маршрутизация простых задач (классификация, роутинг, извлечение сущностей) на быстрые дешевые модели (Flash / Haiku / локальные квантованные модели через vLLM), а сложных — на тяжелые reasoning-модели; автоматический фоллбэк при `429 Rate Limit` или таймауте API.
  - **Асинхронный учет токенов и бюджетирование:** подсчет `input_tokens`/`output_tokens`, квоты на пользователя/тенанта.
- **Evals (Оценка качества) & Guardrails (Безопасность AI):**
  - **LLM Evaluation Pipeline (RAGAS / DeepEval / собственные тесты):** метрики **Faithfulness** (отсутствие галлюцинаций относительно найденного контекста), **Answer Relevance**, **Context Precision & Recall**. Сборка Golden Dataset (50–100 эталонных пар вопрос-ответ) и запуск Evals в CI/CD при изменении промптов или чанкинга!
  - **Guardrails:** защита от **Prompt Injection** и **Indirect Prompt Injection** (когда вредоносная инструкция спрятана в индексируемом документе или письме), маскирование персональных данных (**PII Redaction**) до отправки во внешний LLM API, валидация выходных данных.

---

## ЧАСТЬ 2. Какие пет-проекты восхищают техлидов в 2026–2027 годах (И какие вызывают зевоту)

### Анти-паттерны портфолио (Что сразу отправляет резюме в корзину):
1. Очередной Todo-лист, блог, клон Twitter или интернет-магазин с пустыми контроллерами `UserController -> UserService -> UserRepository`, где вся логика — это `save()` и `findAll()`.
2. Проекты с базой H2 в памяти, без миграций (Flyway/Liquibase), без реальных конкурентных сценариев и без единого интеграционного теста на Testcontainers.
3. «Тонкие обертки над OpenAI/Gemini API», где запрос клиента просто проксируется в LLM в синхронном HTTP-потоке без очередей, ретраев, стриминга, гибридного поиска и оценки качества (Evals).
4. Отсутствие цифр в `README.md`: нет архитектурной схемы, нет обоснования выбора технологий (ADR — Architecture Decision Records), нет результатов нагрузочного тестирования (k6 / Gatling) до и после оптимизации.

### Что заставляет техлида сказать «Берем не глядя!»:
Техлид на собеседовании ищет **инженерную зрелость (Production Readiness)** и **доказательство борьбы с реальными проблемами**:
- В репозитории есть папка `docs/adr/` (3–5 коротких документов, почему выбран Transactional Outbox вместо синхронного вызова, почему выбран индекс HNSW + GIN в Postgres вместо отдельной векторной БД).
- Настроены **Testcontainers**, проверяющие конкурентные гонки (например, 50 потоков через `CountDownLatch` одновременно пытаются списать баланс или забронировать последний слот, и тест доказывает, что баланс не уходит в минус и нет Lost Update).
- Есть `docker-compose.yml`, который одной командой поднимает весь стек вместе с **Prometheus, Grafana (с преднастроенным дашбордом в JSON!) и Jaeger/Tempo**.
- В `README.md` есть раздел **Performance & Benchmarks**: скриншоты `EXPLAIN (ANALYZE, BUFFERS)` и графики из Grafana под нагрузкой **k6** (например: *«Устранение N+1 и добавление композитного покрывающего индекса + Redis Cache-Aside с защитой от Cache Stampede снизило p99 latency с 840 мс до 14 мс при 1500 RPS»*).

---

## ЧАСТЬ 3. Как объединить ВСЕ темы в 2 мощных сквозных проекта за 2 месяца

Вместо 10 мелких поделок нужно построить **2 глубоких, связанных между собой или взаимодополняющих production-grade проекта** (каждый по 4 недели), которые закрывают **100% матрицы компетенций** Backend + DB Internals + Distributed Systems + DevOps + AI Engineering.

---

### ПРОЕКТ №1 (Недели 1–4): High-Load Event-Driven FinTech Core & Real-Time Fraud/Analytics Engine
**Концепция:** Ядро платежного/биллингового процессинга с гарантированной консистентностью, защитой от двойных списаний, распределенными сагами, аналитикой в реальном времени и гибридным AI-модулем анализа подозрительных транзакций (Risk/Dispute Copilot).

#### Архитектура и какие темы она закрывает:
1. **Core Ledger & Account Service (Java 21 + Spring Boot 3.x + Virtual Threads + PostgreSQL):**
   - **Модель двойной записи (Double-Entry Bookkeeping):** никаких `UPDATE balance = balance - 100` без истории проводок. Неизменяемый журнал проводок (`ledger_entries`) + материализованный баланс счета.
   - **Борьба с Concurrency и Deadlocks:** пессимистичные блокировки `SELECT ... FOR UPDATE` со **строгой сортировкой ID счетов по возрастанию** при переводе между двумя счетами (классическая защита от взаимной блокировки / Deadlock!), плюс сравнение с оптимистичной блокировкой (`@Version`) и атомарным `UPDATE ... WHERE balance >= :amount` под нагрузкой.
   - **Строгая идемпотентность (`Idempotency-Key`):** двухфазный перехватчик (Redis быстрый фильтр + уникальное ограничение в PostgreSQL) с хэшированием тела запроса (возврат `422 Unprocessable Entity`, если тот же ключ прислан с другими параметрами, или `409 Conflict`, если запрос еще в статусе `IN_PROGRESS`).
2. **Distributed Workflows & Reliability (Kafka + Outbox + Saga):**
   - **Transactional Outbox Pattern:** запись проводки и события в таблицу `outbox` в одной транзакции, фоновый воркер с `SELECT ... FOR UPDATE SKIP LOCKED` (или Debezium CDC) отправляет батчи в **Apache Kafka** с `acks=all` и `enable.idempotence=true`.
   - **Saga Orchestrator:** межсервисный процесс оплаты заказа с резервированием средств (Hold -> Capture / Release при отказе склада или антифрода) и компенсирующими транзакциями.
   - **Transactional Inbox (Consumer Idempotency):** дедупликация событий по `event_id` на стороне консьюмеров, настроенные **Retry Topics + DLQ**.
3. **High-Performance Caching & Rate Limiting (Redis):**
   - Распределенный **Sliding Window / Token Bucket Rate Limiter** на атомарном **Lua-скрипте** в Redis.
   - Кэширование курсов валют и профилей мерчантов с защитой от **Cache Stampede** (Singleflight / блокировка обновления).
4. **OLAP Analytics & Real-Time Risk Scoring (ClickHouse + AI Risk Copilot):**
   - Поток транзакций из Kafka батчами заливается в **ClickHouse** (`ReplacingMergeTree`, партиционирование по месяцам, разреженный индекс по `(merchant_id, timestamp)`). Молниеносные аналитические дашборды (агрегации по миллионам транзакций за <20 мс).
   - **Инженерный AI-модуль (Dispute & Fraud Investigator):** при попадании транзакции в карантин запускается AI-агент со **Structured Output**, который через **Tool Calling** запрашивает агрегаты аномалий клиента из ClickHouse, ищет похожие паттерны мошенничества и правила регулятора через **Hybrid RAG (`pgvector` + FTS)** и формирует структурированный вердикт для офицера безопасности с цитированием правил и оценкой риска.
5. **Production Infrastructure & Verification:**
   - **Партиционирование** таблицы транзакций в PostgreSQL по времени (`RANGE`), индексы **B-Tree (Covering)** и **BRIN** для архивных партиций, настроенный **Autovacuum**.
   - **Observability:** сквозной **OpenTelemetry** трейсинг (`HTTP -> Spring -> Outbox -> Kafka -> Consumer -> ClickHouse`), метрики **Micrometer/Prometheus** (латентность p95/p99, размер очереди Outbox, количество срабатываний Circuit Breaker и Rate Limiter) и готовый дашборд в **Grafana**.
   - **Нагрузочный стенд k6:** скрипт, стреляющий 1000+ RPS, и отчет с графиками до/после оптимизации индексов и пула соединений HikariCP/PgBouncer.

---

### ПРОЕКТ №2 (Недели 5–8): Autonomous Multi-Agent Code & Incident Intelligence Platform (Production AI + Systems Engineering)
**Концепция:** Платформа для инженерных команд, которая индексирует кодовую базу, документацию, логи/трейсы инцидентов и Pull Request-ы, выполняя автоматическое глубокое ревью кода (поиск багов конкурентности, утечек, SQL-инъекций и нарушений архитектуры) и расследование инцидентов (Root Cause Analysis), с полноценным **Evaluation-пайплайном (RAGAS)** и защитой от Prompt Injection.

*(Можно написать основной бэкенд-оркестратор на **Java 21 / Spring Boot 3 (Spring AI / LangChain4j)** или **Go**, а сервис индексации/Evals — на **Python (FastAPI)**, тем самым показав полиглот-архитектуру по gRPC и Kafka!)*

#### Архитектура и какие темы она закрывает:
1. **Ingestion & Hybrid Search Engine (PostgreSQL + `pgvector` + GIN FTS):**
   - Асинхронный пайплайн индексации через **Kafka** (при пуше в Git или загрузке документации): AST-чанкинг кода (по классам/методам с сохранением графа вызовов) и семантический чанкинг документации (**Parent-Child chunks**).
   - **Настоящий Hybrid Search в одном SQL-запросе PostgreSQL:** комбинация векторного поиска по **HNSW** индексу (`pgvector` cosine distance) и полнотекстового поиска по **GIN** индексу (`tsvector` / BM25) с объединением через **Reciprocal Rank Fusion (RRF)** и последующим реранкингом (**Cross-Encoder**).
2. **Stateful Agentic Workflow & Tool Execution:**
   - Граф агентов с жестким контролем цикла:
     - *Router / Planner* -> декомпозирует задачу (например, «Почему вырос p99 на эндпоинте `/checkout` после релиза v1.4?»).
     - *Tool Executor* -> вызывает безопасные идемпотентные инструменты: поиск по гибридному индексу кода, чтение git diff, запрос метрик/ошибок, статический анализ.
     - *Critic / Verifier (Self-Reflection)* -> проверяет сгенерированный ответ на галлюцинации и соответствие JSON-схеме (**Structured Outputs**).
     - *Human-in-the-Loop* -> требует подтверждения перед созданием фикса/тикета.
3. **Real-Time Streaming, Semantic Cache & Cost Control:**
   - Стриминг хода рассуждений и токенов ответа клиенту через **SSE (Server-Sent Events)**.
   - **Semantic Cache + Token Budget Limiter в Redis:** кэширование частых запросов и жесткие квоты на расход токенов по пользователям/организациям (`OAuth2.1 / JWT` мульти-тенантность с проверкой прав доступа на уровне строк/документов — защита от **IDOR** и утечки чужого контекста в RAG!).
   - **Resilience4j поверх LLM API:** Circuit Breaker, Retry с Exponential Backoff + Jitter, Fallback на резервную модель при таймауте или `429 Too Many Requests`.
4. **AI Guardrails & Automated Evaluation Pipeline (Киллер-фича для резюме!):**
   - **Guardrails:** входной фильтр против Prompt Injection / Indirect Prompt Injection (в комментариях к коду или PR) и автоматическая очистка секретов/API-ключей/PII регулярками и легковесным классификатором перед отправкой в облачную LLM.
   - **Automated Evals в CI/CD:** Golden Dataset из 50 сложных тестовых кейсов. Скрипт считает **Context Recall, Context Precision, Faithfulness и Answer Relevance**, сравнивая качество **Naive Vector RAG vs Hybrid RRF + Reranker** с конкретными цифрами в `README.md` (например: *«Переход с наивного векторного поиска на Hybrid Search (HNSW + GIN BM25 + RRF + Reranker) и AST-чанкинг поднял Context Recall@5 с 0.61 до 0.89 и снизил уровень галлюцинаций в 3.4 раза»*).
5. **Cloud-Native Deployment (Docker + Kubernetes + GitHub Actions):**
   - Multi-stage non-root Dockerfiles, Helm-чарт или манифесты **Kubernetes** с правильно разделенными `startupProbe`, `livenessProbe` и `readinessProbe`, `Graceful Shutdown` и CI/CD пайплайном, гоняющим **Testcontainers** и **RAG Evals**.

---

## ЧАСТЬ 4. Понедельный план реализации на 8 недель (2 месяца)

| Неделя | Фокус теории (Deep Dive) | Практика в пет-проектах (Deliverables) |
| :--- | :--- | :--- |
| **Неделя 1** | JVM Internals (Heap, G1/ZGC, JMM, Happens-Before), Java 21 Virtual Threads, Concurrency (`AQS`, `ConcurrentHashMap`, Locks), Spring Core & Proxies. | Старт **Проекта №1 (FinTech Core)**: схема БД (Flyway), ядро двойной записи, Spring Security (OAuth2/JWT), решение проблемы Deadlock и Lost Update при конкурентных переводах. Тесты на **Testcontainers** с 50 конкурентными потоками. |
| **Неделя 2** | PostgreSQL Internals (MVCC, `xmin/xmax`, Vacuum, Bloat, HOT, уровни изоляции, B-Tree/GIN/GiST/BRIN, `EXPLAIN ANALYZE`, HikariCP/PgBouncer). | Партиционирование таблицы транзакций, настройка покрывающих и частичных индексов, реализация строгой идемпотентности (`Idempotency-Key`). Замер планов запросов `EXPLAIN (ANALYZE, BUFFERS)` на 5 млн сгенерированных строк. |
| **Неделя 3** | Distributed Systems (Transactional Outbox, Inbox, Kafka internals, Saga pattern, Resilience4j, Redis internals, Cache Stampede, Lua Rate Limiting). | Подключение **Kafka** + реализация **Transactional Outbox** (`FOR UPDATE SKIP LOCKED`) + **Inbox** дедупликации + **Saga Orchestrator**. Добавление **Redis** (Cache-Aside с защитой от Stampede + распределенный Rate Limiter на Lua). |
| **Неделя 4** | ClickHouse (MergeTree, колоночное хранение), Observability (OpenTelemetry, Prometheus, Grafana), Нагрузочное тестирование (k6), Linux & Docker. | Поток из Kafka в **ClickHouse**, AI Risk Copilot для спорных транзакций. Полная обвязка **OpenTelemetry + Prometheus + Grafana**. Нагрузочный тест **k6**, упаковка в multi-stage Docker, оформление эталонного `README.md` с ADR и графиками. **Проект №1 готов.** |
| **Неделя 5** | Production RAG Internals: Embeddings, `pgvector` (HNSW), FTS (BM25/GIN), Hybrid Search + RRF, Cross-Encoder Reranking, AST/Parent-Child Chunking. | Старт **Проекта №2 (AI Code & Incident Platform)**: пайплайн индексации репозиториев и логов через Kafka, реализация **Hybrid Search (HNSW + GIN + RRF)** прямо в PostgreSQL + реранкинг. |
| **Неделя 6** | Agentic Workflows, Structured Outputs, Tool Calling, MCP, SSE Streaming, gRPC, Go/Python взаимодействие. | Реализация графа агентов со **Structured Outputs**, безопасным **Tool Calling**, проверкой галлюцинаций (Verifier step), **Human-in-the-Loop** и стримингом ответа по **SSE**. |
| **Неделя 7** | AI Reliability, Guardrails (Prompt Injection, PII/Secret scrubbing), Semantic Caching, LLM Evaluation (RAGAS: Faithfulness, Recall, Precision). | Внедрение **Guardrails** (фильтр секретов и Prompt Injection), **Semantic Cache** и бюджетов токенов в Redis, Resilience4j для LLM вызовов. Сборка Golden Dataset и запуск **Evaluation Pipeline** с таблицей метрик. |
| **Неделя 8** | Kubernetes (Pods, Probes, Graceful Shutdown, Limits), Linux troubleshooting (`strace`, `epoll`, `vmstat`), упаковка портфолио и подготовка к секциям System Design. | Манифесты **Kubernetes** (`liveness`/`readiness`/`startup` probes, Graceful Shutdown), CI/CD в GitHub Actions. Финальная полировка обоих репозиториев, запись 2-минутных демо-гифок/видео работы систем и дашбордов Grafana. **Оба проекта полностью готовы к защите на собеседовании.** |

---

## Резюме для стратегии подготовки
Кандидат, который на собеседовании не просто говорит «я использовал Spring, Postgres, Kafka и прикрутил LLM», а открывает свой репозиторий и показывает:
1. Как он воспроизвел и починил **Lost Update** и **Deadlock** в интеграционном тесте на Testcontainers,
2. Как он реализовал **Transactional Outbox + Inbox** для гарантии консистентности между Postgres и Kafka,
3. Как он ускорил выборку в 50 раз через покрывающий индекс и показал `EXPLAIN (ANALYZE, BUFFERS)` и графики p99 в **Grafana** под нагрузкой **k6**,
4. Как он построил **Hybrid Search (HNSW + BM25 RRF)** с **Guardrails** и доказал рост **Context Recall** на своем Evaluation-датасете,

— автоматически переходит из категории «один из 500 откликнувшихся джунов» в категорию **«инженер уровня Middle, которого нужно забирать с оффером прямо сейчас»**.