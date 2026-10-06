# 30-Дневный интенсив: Java, Spring Boot 3 & High-Load Архитектура

План построен на когнитивных методиках обучения с доказанной эффективностью:
1. **Retrieval Practice (Активное извлечение)** — ежедневные вопросы самопроверки до и после кодинга (Roediger & Karpicke, 2006).
2. **Spaced Repetition (Интервальное повторение)** — циклы ревизии на 7, 14, 21 и 30 дни (Ebbinghaus, Cepeda et al., 2006).
3. **Deliberate Practice (Осознанная практика)** — фокус на узких местах: воспроизвести баг/узкое горлышко -> замерить -> устранить (K. A. Ericsson).
4. **Feynman Technique (Метод Фейнмана)** — объяснение работы механизма простыми словами за 2-3 предложения без абстракций.
5. **Interleaving (Чередование)** — смешивание концепций ООП, низкоуровневой работы памяти, БД и сети вместо изолированного заучивания (Rohrer & Taylor, 2007).
6. **Dual Coding (Двойное кодирование)** — обязательная фиксация ментальной модели / схемы потока данных (Paivio).

---

## Структура ежедневного спринта (2-3 часа в день)
- **Блок 1 (20 мин) — Retrieval & Mental Model:** Ответ на контрольные вопросы прошлого дня без подглядывания + зарисовка схемы данных/вызовов.
- **Блок 2 (40 мин) — Deep Dive:** Изучение механики "под капотом" (байткод, структуры данных, системные вызовы, протоколы).
- **Блок 3 (50 мин) — Deliberate Coding Drill:** Написание минимального изолированного кода, решающего конкретную задачу или воспроизводящего проблему.
- **Блок 4 (10 мин) — Feynman Check:** 3 предложения в конспект: что произошло, почему именно так, какое ограничение в High-Load.

---

## Неделя 1: Основы ООП, Java 21 Memory Model & Concurrency

### День 1: ООП в бэкенде: Инварианты, Инкапсуляция и Rich Domain Model
- **Теория:** Почему Anemic Domain Model ("геттеры-сеттеры на всё") ломает High-Load. Инварианты сущностей. Принцип наименьших привилегий. Records, Sealed interfaces, Pattern matching в Java 21. Композиция против наследования.
- **Практика:** Спроектировать доменную модель заказа (`Order`, `OrderItem`, `OrderStatus`) с валидацией инвариантов внутри конструкторов/методов. Запретить невалидные состояния через sealed types.
- **Feynman:** Объяснить разницу между DTO (data carrier) и Domain Entity.

### День 2: Java Memory Model (JMM): Stack, Heap & Garbage Collection
- **Теория:** Stack frames, Heap (Young/Old Generation), Metaspace. Pass-by-value ссылок. Принципы работы сборщиков мусора (G1, ZGC). Влияние аллокаций памяти на латентность (p99) и Stop-the-World паузы.
- **Практика:** Написать код, приводящий к `OutOfMemoryError: Java heap space` и `StackOverflowError`. Запустить с флагами JVM `-Xmx64m -XX:+PrintGCDetails`. Замерить просадку.
- **Retrieval:** Чем примитивный тип в Stack отличается от объекта в Heap при передаче в метод?

### День 3: Collections Framework под микроскопом
- **Теория:** Внутреннее устройство `ArrayList` vs `LinkedList`. Хэш-таблицы: `HashMap` (вычисление хэша, бакеты, коллизии, трансформация бакета в красно-черное дерево при `TREEIFY_THRESHOLD = 8`). Почему обычная `HashMap` падает или зацикливается в многопоточке.
- **Практика:** Реализовать кастомную упрощенную хэш-таблицу с разрешением коллизий методом цепочек. Переопределить `equals()` и `hashCode()` с соблюдением контракта. Показать баг при нарушении контракта.
- **Feynman:** Почему сложность поиска в `HashMap` амортизированная $O(1)$, но в худшем случае $O(n)$ или $O(\log n)$?

### День 4: Многопоточность: Потоки, Гонки Данных и Синхронизация
- **Теория:** Race conditions, Critical sections, Deadlock. Visibility problem и Memory Barriers. Ключевые слова `synchronized`, `volatile`. Отношение Happens-Before в JMM.
- **Практика:** Написать счетчик, инкрементируемый 100 потоками. Показать потерю данных без синхронизации. Исправить через `AtomicInteger` (CAS) и сравнить производительность с `synchronized`.
- **Retrieval:** Гарантирует ли `volatile` атомарность операции `i++`? Почему?

### День 5: Lock-Free структуры, Concurrent Collections & Thread Pools
- **Теория:** Сегментные блокировки в `ConcurrentHashMap`. Lock-free алгоритмы на базе Compare-And-Swap. Пулы потоков: `ThreadPoolExecutor`, расчет размера пула (CPU-bound vs I/O-bound: $N_{cpu} \times (1 + W/C)$), очереди задач (`ArrayBlockingQueue`, `LinkedBlockingQueue`), политики отказа (Discard, CallerRuns).
- **Практика:** Настроить кастомный `ThreadPoolExecutor` с ограниченной очередью. Симулировать перегрузку задачами и обработать `RejectedExecutionException`.
- **Feynman:** Чем опасен `Executors.newFixedThreadPool()` с дефолтной очередью под высокой нагрузкой?

### День 6: Concurrency 2.0: CompletableFuture & Virtual Threads (Project Loom)
- **Теория:** Асинхронная композиция через `CompletableFuture`. Проблема Platform Threads (1 поток ОС = 1 МБ стека, дорогой context switch). Virtual Threads в Java 21: легкие потоки (KiB памяти), планировщик ForkJoinPool, понятие pinning (почему `synchronized` блокирует carrier thread и как помогает `ReentrantLock`).
- **Практика:** Запустить 50 000 платформенных потоков vs 50 000 виртуальных потоков (`Thread.ofVirtual().start()`), симулирующих I/O задержку (sleep). Замерить потребление RAM и CPU.
- **Retrieval:** В каких сценариях Virtual Threads дают выигрыш, а в каких нет?

### День 7: Спринт-контроль 1 (Spaced Repetition & Milestone Project)
- **Проект Дня:** Написать на чистой Java (без фреймворков) многопоточный In-Memory Cache с LRU-вытеснением (Least Recently Used) и TTL (Time-To-Live).
  - Требования: потокобезопасность (`ReentrantReadWriteLock` или `ConcurrentHashMap`), $O(1)$ операции чтения и записи, фоновая очистка протухших ключей без блокировки читателей.
- **Self-Test:** Интервальное повторение ключевых концепций 1-6 дней.

---

## Неделя 2: Spring Core, REST API & Высокопроизводительные БД

### День 8: Spring Core & Внутренности IoC / DI
- **Теория:** Инверсия управления (IoC) и внедрение зависимостей (DI). `ApplicationContext` vs `BeanFactory`. Жизненный цикл бина (Instantiation -> Populate properties -> BeanPostProcessor -> `@PostConstruct` -> Destruction). Проксирование: JDK Dynamic Proxy (по интерфейсам) vs CGLIB (по классам).
- **Практика:** Написать кастомную аннотацию `@ExecutionTime` и собственный `BeanPostProcessor`, замеряющий время выполнения методов через рефлексию/прокси.
- **Feynman:** Почему вызов метода с `@Transactional` из того же класса (`this.method()`) не открывает транзакцию?

### День 9: Spring Boot Magic Demystified
- **Теория:** Что делает `@SpringBootApplication`. Механизм Auto-Configuration: `@EnableAutoConfiguration`, `META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports`, условные аннотации (`@ConditionalOnClass`, `@ConditionalOnMissingBean`, `@ConditionalOnProperty`). Приоритеты конфигурации (`application.yml`, env vars).
- **Практика:** Создать свой мини-starter или кастомный автоконфигурируемый бин, который активируется только при наличии свойства `app.feature.enabled=true`.
- **Retrieval:** Как Spring Boot решает, какой DataSource сконфигурировать по умолчанию?

### День 10: Чистый REST API: Валидация, DTO & RFC 7807 ProblemDetail
- **Теория:** Архитектурные границы: Controller -> Service -> Repository. DTO на Java `record` (никаких сущностей наружу!). Валидация через `jakarta.validation` (`@Valid`, `@NotNull`, `@Size`). Централизованная обработка ошибок: `@RestControllerAdvice`, `ProblemDetail` (RFC 7807). HTTP статусы и идемпотентность (GET/PUT/DELETE vs POST).
- **Практика:** Реализовать REST контроллер с пагинацией (`Pageable`), DTO валидацией и глобальным обработчиком исключений, возвращающим RFC 7807.
- **Feynman:** Чем опасен возврат JPA-сущностей напрямую в JSON-ответ контроллера?

### День 11: Индексация в реляционных БД (PostgreSQL) для High-Load
- **Теория:** Как устроен B-Tree индекс: страницы, балансировка, селективность. Составные индексы и Leftmost Prefix Rule. Index Scan vs Index Only Scan vs Sequential Scan. Анализ плана запроса: `EXPLAIN (ANALYZE, BUFFERS)`.
- **Практика:** Поднять PostgreSQL через Docker. Сгенерировать 500 000 строк. Написать медленный запрос по условию `WHERE age > 25 AND city = 'Berlin'`. Посмотреть `EXPLAIN ANALYZE`. Создать оптимальный составной индекс, добиться Index Only Scan, замерить ускорение.
- **Retrieval:** Почему индекс на булево поле (2 значения) почти всегда бесполезен для планировщика?

### День 12: JPA / Hibernate: Ловушки производительности
- **Теория:** Persistence Context, Entity Lifecycle (Transient, Managed, Detached, Removed). First-Level Cache, Dirty Checking. Проблема N+1: причины возникновения при Lazy Loading. Способы решения: `JOIN FETCH`, `@EntityGraph`, DTO Projection.
- **Практика:** Воспроизвести проблему N+1 на связке `User -> List<Order>`. Включить логирование SQL (`show-sql`, `generate_statistics`). Исправить через `JOIN FETCH` в JPQL и через DTO-проекцию. Сравнить количество SQL-запросов и потребление памяти.
- **Feynman:** В чем разница между `FetchType.EAGER` и `JOIN FETCH`? Почему EAGER — антипаттерн?

### День 13: Транзакции, Уровни Изоляции & HikariCP Connection Pool
- **Теория:** ACID свойства. `@Transactional(readOnly = true)` vs `@Transactional`. Уровни изоляции транзакций: Read Uncommitted, Read Committed, Repeatable Read, Serializable. Аномалии: Dirty Read, Non-repeatable Read, Phantom Read. HikariCP: формула пула соединений ($Connections = ((core\_count * 2) + effective\_spindle\_count)$), таймауты (`connectionTimeout`, `leakDetectionThreshold`).
- **Практика:** Симулировать гонку баланса счета (Lost Update) при двух одновременных списаниях. Решить проблему через пессимистическую блокировку (`@Lock(LockModeType.PESSIMISTIC_WRITE)` / `SELECT ... FOR UPDATE`) и оптимистическую блокировку (`@Version`).
- **Retrieval:** Почему длинные транзакции с сетевыми вызовами (HTTP/REST) внутри убивают Connection Pool?

### День 14: Миграции схем БД: Flyway & Zero-Downtime
- **Теория:** Управление эволюцией схемы БД. Версионирование миграций с Flyway (`V1__...sql`, `R__...sql`). Принципы Zero-Downtime миграций (Expand and Contract pattern): добавление колонок с дефолтами, переименование без простоя, безопасное удаление.
- **Практика:** Настроить Flyway в Spring Boot. Написать миграции с индексами и внешними ключами. Протестировать откат и валидацию хэш-сумм миграций.
- **Feynman:** Как безопасно переименовать колонку в таблице на 100 млн записей без остановки сервиса?

### День 15: Спринт-контроль 2 (Spaced Repetition & Milestone Project)
- **Проект Дня:** Полноценный микросервис "Учет заказов" на Spring Boot 3 + PostgreSQL + Flyway:
  - Строгие границы слоев (Controller -> Service -> Repository).
  - DTO на records, `@Valid`, `ProblemDetail`.
  - Оптимизированные запросы с DTO-проекциями и `JOIN FETCH` (0 N+1 запросов).
  - Настроенный HikariCP с логированием утечек соединений.
- **Self-Test:** Интервальное повторение тем 1-14 дней.

---

## Неделя 3: Кэширование, Асинхронность & Event-Driven Architecture

### День 16: Стратегии кэширования & Архитектура Redis
- **Теория:** Паттерны кэширования: Cache-Aside, Write-Through, Write-Behind, Refresh-Ahead. Архитектура Redis: однопоточный event loop, структуры данных (Strings, Hashes, Lists, Sets, Sorted Sets). Проблемы кэширования: Cache Stampede (Dogpiling), Cache Penetration, Cache Avalanche. Борьба с ними (рандомизация TTL, Mutex lock, Bloom filters).
- **Практика:** Развернуть Redis в Docker. Реализовать паттерн Cache-Aside вручную с помощью `StringRedisTemplate`. Настроить TTL и обработку miss-сценария.
- **Feynman:** Как происходит Cache Stampede при истечении популярного ключа и как его предотвратить?

### День 17: Spring Cache Abstraction & Redis Integration
- **Теория:** Аннотации `@Cacheable`, `@CachePut`, `@CacheEvict`. `CacheManager`, сериализация/десериализация (GenericJackson2JsonRedisSerializer vs JDK serialization). Пул соединений Lettuce.
- **Практика:** Интегрировать `@Cacheable` в сервис заказов. Настроить JSON-сериализатор. Проверить очистку кэша через `@CacheEvict` при обновлении сущности.
- **Retrieval:** Почему сериализация через стандартный `JdkSerializationRedisSerializer` не рекомендуется для продакшена?

### День 18: Асинхронность в Spring: Task Execution & Application Events
- **Теория:** `@Async` и конфигурация `ThreadPoolTaskExecutor`. Отличие от Virtual Threads. Обработка исключений в асинхронных методах (`AsyncUncaughtExceptionHandler`). Spring Events: decoupling компонентов с помощью `ApplicationEventPublisher` и `@EventListener` / `@TransactionalEventListener` (фазы `AFTER_COMMIT`, `BEFORE_COMMIT`).
- **Практика:** Реализовать отправку уведомления клиенту через Spring Event после успешного коммита транзакции создания заказа (`TransactionPhase.AFTER_COMMIT`).
- **Feynman:** Что произойдет, если бросить исключение внутри `@EventListener(phase = AFTER_COMMIT)`? Окатит ли это транзакцию БД?

### День 19: Apache Kafka: Архитектура распределенного лога
- **Теория:** Почему не очереди (RabbitMQ), а распределенный коммит-лог. Брокеры, Топики, Партиции. Сегменты и смещения (Offsets). Producer: `acks` (0, 1, all), партиционирование (по ключу / round-robin), batching, compression. Consumer: Consumer Groups, Rebalance протокол, коммит офсетов (автоматический vs ручной).
- **Практика:** Запустить кластер Kafka в Docker Compose. Отправить сообщения через консольный producer, вычитать через консольный consumer с указанием consumer group. Продемонстрировать распределение партиций между двумя консьюмерами.
- **Retrieval:** За счет чего Kafka гарантирует порядок сообщений? Гарантируется ли порядок глобально по всему топику?

### День 20: Spring Kafka: Надежность и Идемпотентность
- **Теория:** `KafkaTemplate`, `@KafkaListener`. Конфигурация надежности: Idempotent Producer (`enable.idempotence=true`), доставка "At least once" vs "Exactly once". Обработка ошибок консьюмера: Retryable Topic, Dead Letter Queue (DLQ / DLT), `DefaultErrorHandler` с экспоненциальным бэкоффом. Идемпотентный консьюмер: дедупликация сообщений в БД/Redis.
- **Практика:** Реализовать консьюмер с ручным подтверждением (`Acknowledgment`), DLQ-топиком для бракованных сообщений и таблицей дедупликации (`message_id` unique constraint).
- **Feynman:** Почему в распределенных системах практически невозможно гарантировать "Exactly-Once Processing" без идемпотентного консьюмера?

### День 21: Паттерны устойчивости (Resilience) в High-Load
- **Теория:** Каскадные сбои (Cascading failures). Circuit Breaker (Состояния: Closed, Open, Half-Open). Rate Limiting (Token Bucket, Leaky Bucket). Retry с Jitter. Bulkhead (изоляция ресурсов). Библиотека Resilience4j.
- **Практика:** Обернуть вызов внешнего нестабильного API в `@CircuitBreaker` и `@RateLimiter` с использованием Resilience4j. Протестировать переход Circuit Breaker в Open state при серии ошибок и fallback ответ.
- **Retrieval:** Для чего добавляется случайная задержка (Jitter) при повторных попытках (Retry)?

### День 22: Спринт-контроль 3 (Spaced Repetition & Milestone Project)
- **Проект Дня:** Интеграция Event-Driven контура в систему заказов:
  - Создание заказа публикует событие в Kafka.
  - Фоновый сервис биллинга вычитывает событие, проверяет идемпотентность через Redis, списывает средства.
  - При сбое биллинга — отправка в DLQ с алертом.
  - Чтение статуса заказа кэшируется в Redis (Cache-Aside).
- **Self-Test:** Интервальное повторение тем 1-21 дней.

---

## Неделя 4: High-Load инженерия, Профайлинг, Безопасность & Capstone

### День 23: Безопасность в High-Load: Spring Security & Stateless JWT
- **Теория:** Архитектура `SecurityFilterChain`. Почему сессии на сервере убивают горизонтальное масштабирование. Stateless аутентификация через JWT (JSON Web Tokens). Валидация подписи (HMAC / RSA) без похода в БД на каждый запрос. Защита от утечки токенов и Refresh Token ротация через Redis.
- **Практика:** Настроить `SecurityFilterChain` на Spring Boot 3, кастомный `JwtAuthenticationFilter`, генерацию и валидацию токенов. Исключить обращение к базе данных при валидации валидного JWT.
- **Feynman:** Почему валидация JWT экономит ресурсы БД в высоконагруженных системах, и какова цена этой оптимизации (проблема отзыва токена)?

### День 24: Тестирование отказоустойчивых систем: Testcontainers
- **Теория:** Пирамида тестирования: Unit vs Integration vs E2E. Моки против реальных зависимостей (почему H2 для тестов вместо Postgres ведет к скрытым багам). Testcontainers: запуск реальных PostgreSQL, Redis, Kafka в Docker на время прогона тестов. DynamicPropertyRegistry.
- **Практика:** Написать интеграционный тест с `@SpringBootTest` и `Testcontainers`, проверяющий полный цикл: HTTP запрос -> запись в PostgreSQL -> публикация в Kafka -> получение консьюмером.
- **Retrieval:** Почему интеграционные тесты с реальной БД через Testcontainers надежнее моков через Mockito?

### День 25: Observability: Метрики, Actuator, Prometheus & Grafana
- **Теория:** Три столпа Observability: Metrics, Logs, Traces. Spring Boot Actuator: эндпоинты здоровья (`/actuator/health/readiness`, `/liveness`). Micrometer: Counters, Gauges, Timers. Экспорт метрик в Prometheus. Золотые сигналы мониторинга: Latency, Traffic, Errors, Saturation.
- **Практика:** Подключить Actuator и Micrometer. Добавить кастомный `Timer` для замера времени критической бизнес-операции. Развернуть Prometheus + Grafana в Docker, построить дашборд с RPS, ошибками 5xx и p95/p99 latency.
- **Feynman:** В чем разница между средним временем ответа (Mean Latency) и 99-м перцентилем (p99)? Почему среднее обманывает инженера?

### День 26: Профайлинг и поиск узких мест (Bottleneck Hunting)
- **Теория:** CPU Bound vs Memory Leak vs Lock Contention. Как работают семплирующие профайлеры (async-profiler). Снятие и анализ Thread Dumps (`jstack`) и Heap Dumps (`jmap`, Eclipse Memory Analyzer). Анализ GC-логов.
- **Практика:** Написать код с утечкой памяти (статическая коллекция) и захватом лока. Запустить под нагрузкой. Снять Thread Dump и обнаружить заблокированный поток. Снять Heap Dump и найти виновника утечки памяти в VisualVM / async-profiler.
- **Retrieval:** Как определить Deadlock по Thread Dump?

### День 27: Тюнинг под нагрузку: Virtual Threads & HTTP Clients
- **Теория:** Включение Virtual Threads в Spring Boot 3.2+ (`spring.threads.virtual.enabled=true`). Внутренности embedded Tomcat. Замена блокирующего RestTemplate на `RestClient` / `WebClient`. Ограничения: Pinning при обращении к нативным библиотекам или `synchronized`.
- **Практика:** Сравнить пропускную способность (Throughput / RPS) сервиса с блокирующим I/O на стандартных потоках Tomcat vs с включенными Virtual Threads при 1 000 одновременных соединений (k6 / wrk).
- **Feynman:** Почему Virtual Threads не ускоряют вычисления CPU, но многократно увеличивают пропускную способность сервисов с I/O-блокировками?

### Дни 28–29: Финальный Capstone Проект: Flash-Sale / High-Load Ticket Booking
- **Архитектура системы:**
  - Клиент авторизуется по JWT.
  - Бронирование билета/товара при ограниченном стоке (высокая конкуренция за ресурс, защита от перепродажи).
  - Защита от перегрузки: Rate Limiting через Redis Token Bucket.
  - Быстрая проверка стока в Redis с атомарным декрементом через Lua-скрипт (`DECRBY`).
  - Асинхронный сброс заказов в PostgreSQL через Kafka (Write-Behind / Event-Driven).
  - Обработка сбоев через DLQ и Circuit Breaker.
  - Наблюдаемость: метрики Prometheus, кастомные таймеры p99, healthchecks.
- **Нагрузочное тестирование (Stress Testing):**
  - Написание сценария на k6 / wrk (10 000 RPS, всплеск трафика).
  - Мониторинг в реальном времени: потребление памяти, Hikari connection pool, задержка Kafka.
  - Анализ узких мест и тюнинг параметров.

### День 30: Ревизия, Защита Архитектуры & Дорожная карта на будущее
- **Активности:**
  - Комплексное интервальное тестирование по всем 30 темам (50 вопросов с закрытыми ответами и кодинг-задачами).
  - Оформление README и архитектурной диаграммы (C4 Model) Capstone-проекта в портфолио.
  - Определение вектора дальнейшего роста: Sharding/Partitioning БД, Distributed Transactions (Saga pattern), gRPC, Kubernetes.

---

## Чеклист методов обучения на каждый день

| День | Метод обучения | Действие |
|---|---|---|
| **Все дни** | Retrieval Practice | Начать день с ответов на вопросы "Retrieval" прошлого дня без документации |
| **Все дни** | Deliberate Practice | Писать изолированный код, воспроизводящий проблему (баг / утечка / дедлок), а затем фикс |
| **Все дни** | Feynman Technique | Сформулировать ответ на вопрос "Feynman" в 3 предложениях своими словами |
| **Все дни** | Dual Coding | Зарисовать архитектурную схему (поток данных, стек вызовов, структура памяти) |
| **Дни 7, 15, 22, 30** | Spaced Repetition | Выделенный день повторения, сборка сквозного проекта недели, тестирование памяти |
