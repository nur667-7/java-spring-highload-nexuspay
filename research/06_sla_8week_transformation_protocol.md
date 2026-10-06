# Инженерная схема SLA-трансформации: A1+/A2 → Technical Interview Ready (8 недель)

Цель — не общий B2 (требует ~500–600 часов), а **Domain-Specific Functional Fluency (ESP — English for Specific Purposes)** в узком коридоре: *Live Coding + System Design / Backend Architecture + Behavioral (STAR)*. Когнитивный бюджет: **0 дополнительных часов отдельно на «учебники английского»** за счет **Habit Piggybacking** и **Dual-Coding** поверх технического роадмапа (+20–25 мин чистого моторного тренинга в транзитное время).

---

## 1. Когнитивно-нейролингвистическое ядро (5 SLA-методик)

### 1.1. Lexical Approach (Michael Lewis, 1993) — Чанкинг и Sentence Frames
* **Нейромеханика:** Рабочая память (Working Memory по Baddeley) при говорении на L2 перегружается, если собирать фразу по словам через правила грамматики (декларативная память, префронтальная кора). **Lexical Chunks** и **Sentence Frames** хранятся в процедурной памяти (базальные ганглии) как единые моторные макросы. Во время интервью мозг тратит 90% RAM на алгоритм/архитектуру, а синтаксический каркас выдается автоматически за $O(1)$.
* **Инженерное правило:** Запрет на заучивание изолированных слов (`complexity`, `idempotent`, `bottleneck`). Учатся только **функциональные слоты (frames)**:
  * *Trade-off Frame:* `If we opt for [X], we gain [Y] at the expense of [Z].`
  * *Complexity Frame:* `Time complexity is O(N) because we iterate over [X] once, and space complexity is O(1) since we only store a few pointers.`
  * *Root Cause Frame:* `The root cause of [issue] is that [component] blocks the thread when [condition].`
  * *Clarification Frame:* `Just to make sure I’m on the same page: are we optimizing for [latency] or [throughput]?`

### 1.2. Technique of Shadowing (Alexander Arguelles) — Моторная автоматизация артикуляции
* **Нейромеханика:** На уровне A1+/A2 главный барьер — не незнание слов, а медленная работа **фонологической петли (Phonological Loop)** и рассинхрон между слуховой корой (зона Вернике) и моторной корой (зона Брока). **Blind/Scripted Shadowing** (повторение за спикером с задержкой в 0.3–0.5 секунды вслух с копированием интонации, ритма и связок) перепрошивает речевой аппарат как мышечный навык.
* **Протокол выполнения (15 мин/день в пути или перед кодингом):**
  1. **Шаг 1 (Blind Shadowing, 5 мин):** Слушать 2–3 минутный технический фрагмент и говорить синхронно со спикером, опираясь только на звук (тренировка декодирования потока речи).
  2. **Шаг 2 (Scripted Shadowing, 5 мин):** Тот же отрывок с глазами в транскрипт + выделение интонационных пауз (thought groups) и связок (`going to` → `gonna`, `kind of` → `kinda`, `at the expense of`).
  3. **Шаг 3 (Prosodic Overdrive, 5 мин):** Чтение вслух вслед за диктором на скорости 1.0x–1.1x с гипертрофированной артикуляцией.

### 1.3. Narrow Listening & Comprehensible Input $i+1$ (Stephen Krashen)
* **Нейромеханика:** Слушать подкасты «обо всем» (BBC, сериалы) на A2 бесполезно — лексическая дисперсия стремится к бесконечности. В узком домене (Java/Spring, Databases, NeetCode, System Design) **300–400 высокочастотных глаголов и коллокаций покрывают 85–90% всей речи интервью**.
* **Корпус для Narrow Input (только 3–4 постоянных голоса на 8 недель):**
  * *Алгоритмы:* **NeetCode** (идеальный темп, чистый американский акцент, повторяющиеся паттерны объяснения кода).
  * *System Design & Backend:* **ByteByteGo**, **Hussein Nasser**, **Defog Tech** (короткие 5–10 минутные разборы).
  * *Правило 3 прогонов:* Один 5-минутный ролик смотрится 3 дня подряд (День 1 — понимание сути + выписка 3 чанков; День 2 — Shadowing со скриптом; День 3 — пересказ его архитектуры своими словами за 90 секунд).

### 1.4. Output Hypothesis (Merrill Swain, 1985/1995) — Принудительная генерация
* **Нейромеханика:** Понимание текста/видео задействует только семантическое декодирование (можно понять смысл по 3 ключевым словам, игнорируя грамматику). Переход к говорению требует **Syntactic Processing** и вызывает **Noticing the Gap** — мозг физически фиксирует дыру («я знаю, что такое *deadlock*, но не могу быстро связать *почему поток завис при захвате второго лока*»). Только в момент принудительного *Output* формируются синаптические связи активного словаря.
* **Правило:** Никакого «мысленного проговаривания про себя». Только **аудиогенерация вслух (Vocalized Output)** с записью на диктофон или в режиме Voice-to-Voice с AI.

### 1.5. Spaced Repetition (Anki) с Audio + Cloze Deletion
* **Нейромеханика:** Двусторонние карточки `Слово EN ↔ Перевод RU` вредны — они включают реакцию перевода в голове во время интервью (+500–800 мс латентности на каждую фразу).
* **Формат карточек (строго Cloze Deletion + TTS Audio):**
  * *Front:* `To prevent N+1 queries in Hibernate, we should {{c1::use JOIN FETCH}} or {{c2::configure an @EntityGraph}}.` *(Контекст-триггер в скобках: [fetch strategy])*
  * *Back:* Полная фраза + автопроигрывание аудио (HyperTTS / AwesomeTTS с нейросетевым голосом).
  * *Правило ответа:* Карточка считается пройденной (`Good`), только если фраза произнесена **вслух целиком без запинки за < 3 секунд**. Лимит: **не более 7–10 новых чанков в день** (10 минут ревью утром).

---

## 2. Бесшовная интеграция в тех-роадмап студента 3 курса IITU (Piggybacking & Dual-Coding)

Принцип **Dual-Coding (Allan Paivio)** + **Context-Dependent Memory**: концепт кодируется одновременно как техническая схема/код и как англоязычный речевой паттерн. Если учить Spring на русском, а потом переводить на английский — на интервью мозг делает двойную трансляцию `Мысль → RU → EN`. Если сразу кодировать `Мысль (Схема) → EN Chunk`, стадия русского языка вырезается из пайплайна.

### Матрица наложения английского на ежедневные инженерные блоки (0 лишних часов)

| Технический блок роадмапа | Как трансформируется через Piggybacking | Языковой артефакт на выходе |
| :--- | :--- | :--- |
| **Утренний LeetCode / DSA (45–60 мин)** | **Think-Aloud Protocol (REACTO):** Запрет решать задачу молча.<br>1. Прочитать условие вслух (1 мин).<br>2. Проговорить Brute Force за 60 сек.<br>3. Писать код, комментируя каждую строчку вслух (`Here I initialize a HashMap to store...`).<br>4. Озвучить Time/Space Complexity. | 3 минуты чистого монолога на задачу + автоматизация 30 базовых алгоритмических глаголов (`traverse`, `keep track of`, `decrement`, `edge case`). |
| **Дорога в IITU / между парами (25 мин)** | **10 мин Anki Audio Cloze** + **15 мин Shadowing** вчерашнего ролика NeetCode / ByteByteGo в наушниках. | Разогретый артикуляционный аппарат, 7 новых чанков в процедурной памяти. |
| **Вечерний Backend / System Design (Spring, DB, Kafka)** | **English-Only Engineering Artifacts:**<br>1. Все `git commit` по Conventional Commits на EN.<br>2. В `README.md` пет-проекта писать **Micro-ADR (Architecture Decision Record)** из 4 строк на каждую фичу (`Context → Options → Decision → Trade-offs`).<br>3. Правило **60-Second Voice Summary**: перед закрытием ноутбука записать 1-минутное голосовое самому себе в Telegram («Saved Messages»): *What I built today, what broke, and how I fixed it*. | Готовые письменные и устные ответы про свой проект для секции «Tell me about a challenging project». |
| **Субботний чекпоинт (Feynman Defense)** | **Voice AI Mock Interview (25–30 мин):** Защита изученных за неделю тем (JVM, Индексы, Транзакции, Spring Security) голосом перед ChatGPT Advanced Voice / Claude Voice в роли Strict Senior Backend Interviewer. | Снятие языкового страха, проверка выживаемости чанков под давлением follow-up вопросов. |

#### Промпт для субботнего голосового AI-интервьюера (System Prompt):
> *"Act as a Senior Backend Engineer conducting a technical interview in English. My current English level is A2 transitioning to B1, so keep your questions clear and concise, one question at a time. Today's topic is [e.g., Database Indexing & ACID Transactions]. Ask me conceptual and scenario-based questions. Wait for my spoken response. First, evaluate my technical accuracy. Second, give me 1–2 natural English sentence frames (lexical chunks) that would make my answer sound more like a mid-level engineer. Then ask a deeper follow-up question."*

---

## 3. Анти-ступор протоколы: преодоление Foreign Language Anxiety (FLA) и Freezing

### Нейробиология ступора на интервью
Когда интервьюер задает длинный вопрос с незнакомым акцентом или скоростью, возникает **Amygdala Hijack** (выброс кортизола и норадреналина). Амигдала подавляет **дорсолатеральную префронтальную кору (dlPFC)** и зону Брока, обнуляя рабочую память. Попытка вспомнить забытое слово в этот момент приводит к полному зависанию (Freezing).

Решение — **Аварийные Речевые Протоколы (Emergency Fallbacks)**, заученные до уровня спинномозгового рефлекса (через мышечный Shadowing). Они дают мозгу **3–5 секунд легальной паузы**, возвращают контроль префронтальной коре и переводят диалог в управляемое русло.

### Протокол А: «Не понял вопрос на слух / Слишком быстро / Сложный акцент»
Никогда не говорить просто *"Sorry, what?"* или *"I don't understand"* (звучит как провал коммуникации). Использовать **Active Verification & Decomposition**:
1. **Если не расслышал целиком (аудио-помеха / темп):**
   * *"Could you rephrase that slightly? The audio cut out for a second."*
   * *"Sorry, could you repeat the last part about [keyword you heard]?"*
2. **Если понял отдельные слова, но не уловил суть вопроса (Echo + Narrowing):**
   * *"Just to make sure I caught your point correctly — are you asking about how [X] works under the hood, or how to scale it?"*
   * *"Let me make sure I'm on the right track. You want me to focus on [X], right?"*
3. **Если вопрос огромный и многосоставной:**
   * *"That’s a great multi-part question. Let me break it down and tackle [Part 1] first, and then move to [Part 2]."*

### Протокол Б: «Забыл слово прямо посреди фразы» (Circumlocution / Анти-зависание)
Запрещено замолкать и искать перевод в голове дольше 1 секунды. Включается **Functional Paraphrase (Описательный обход через действие)**:
* *Формула замены существительного:* `It’s the mechanism / component that allows us to [verb]...`
  * Забыл слово *Idempotency* → *"The property where we can send the same request multiple times without changing the result beyond the initial application."*
  * Забыл слово *Eviction policy* → *"The rule that decides which keys to remove from the cache when memory is full."*
* *Формула легального сброса сломанного предложения (Self-Correction Frame):*
  * *"Let me put it another way to be more precise..."* (полностью сбрасывает запутавшуюся грамматику и позволяет начать простую фразу `Subject + Verb + Object`).

### Протокол В: «Нужно 5–10 секунд подумать над техническим решением» (Time-Buying Bridges)
Молчание в эфире дольше 3 секунд пугает и кандидата, и интервьюера. Нужно **озвучить процесс мышления**:
* *"That’s an interesting edge case. Let me take a few seconds to think through the trade-offs."*
* *"Let’s walk through what happens step by step when a request hits the server..."*
* *"Off the top of my head, there are two ways to approach this. The first one is..."*

---

## 4. Точная инженерная схема 8-недельного языкового тренинга (по неделям и дням)

### Ежедневный микро-шаблон (Пн–Пт, вшитый в тех-роадмап)
* **Утро (во время LeetCode):** 3–5 мин вслух по матрице REACTO (`Repeat → Examples → Approach → Code → Test → Optimize`).
* **Транзит (по дороге в уник / из уника, 25 мин):** 10 мин Anki (Audio Cloze, 7 карточек) + 15 мин Shadowing (1 видеоролик на 3 дня).
* **Вечер (после кодинга / теории, 5 мин):** Коммит + 3 строки ADR на английском + 60-секундное голосовое резюме дня в Telegram (без чтения с листа, с 1 дубля).
* **Суббота (30 мин):** Голосовая защита недели по Фейнману с AI-интервьюером + разбор ошибок.
* **Воскресенье (15 мин):** Аудит своих 5 вечерних голосовых за неделю (выписать 5 мест, где запнулся → превратить в 5 Cloze-карточек Anki на следующую неделю).

---

### Понедельная декомпозиция (Weeks 1–8)

#### Неделя 1: Моторный разблокировщик и Алгоритмический Базис (A1+/A2 → Survival Coding Speak)
* **Фокус:** Снять блок «боюсь открыть рот», автоматизировать озвучку базовых операций с кодом (массивы, строки, указатели, хеш-таблицы).
* **Narrow Input для Shadowing:** 2 видео NeetCode (Two Sum, Valid Anagram).
* **Обязательные Sentence Frames (35 чанков за неделю):**
  * `Here we iterate through the array using two pointers...`
  * `If the element is already in the map, we return...`
  * `To handle the edge case where the input is empty / null...`
  * `This gives us linear time complexity O(N) and constant space O(1).`
* **Пятничный / Субботний стресс-тест:** Решить 2 задачи Easy вслух с включенным таймером (15 мин на каждую) без единой паузы молчания длиннее 5 секунд.

#### Неделя 2: Java Core, Memory Model, Concurrency & Collections
* **Фокус:** Объяснение внутреннего устройства (Under the hood: `HashMap`, ` Garbage Collection`, `Threads`, `volatile`, `synchronized`).
* **Narrow Input для Shadowing:** Короткие разборы JVM internals / Concurrency (Defog Tech).
* **Обязательные Sentence Frames:**
  * `Under the hood, [X] is backed by [Y]...`
  * `When a hash collision occurs, elements are stored in a linked list or a balanced tree.`
  * `To ensure thread safety without locking the entire table, we use...`
  * `The main difference between [A] and [B] comes down to [C].`
* **Субботний AI-чекпоинт:** 20-минутный устный допрос по Java Core + тренировка **Протокола Б (Circumlocution)** — намеренно объяснить 5 терминов (`deadlock`, `race condition`, `immutable`, `heap`, `stack`), не называя сами эти слова.

#### Неделя 3: Реляционные БД, Индексы, Транзакции и SQL Optimization
* **Фокус:** Причинно-следственные связи (Cause & Effect) и описание сценариев отказа (Dirty Read, Lost Update, Index Scan vs Seq Scan).
* **Narrow Input для Shadowing:** Hussein Nasser (Database Indexing, B-Trees, Isolation Levels).
* **Обязательные Sentence Frames:**
  * `Without an index on [column], the database has to perform a full table scan.`
  * `If two transactions try to update the same row concurrently...`
  * `Read Committed prevents dirty reads, whereas Repeatable Read also prevents...`
  * `We can solve this bottleneck by adding a composite index on...`
* **Субботний AI-чекпоинт:** Защита архитектуры БД своего проекта + объяснение планов запросов (`EXPLAIN ANALYZE`) голосом.

#### Неделя 4: Spring Boot Architecture, REST, DI, Security & JPA (N+1)
* **Фокус:** Описание жизненного цикла запроса (Request Lifecycle) от HTTP-контроллера до базы данных.
* **Narrow Input для Shadowing:** Обзоры Spring Security Filter Chain & Spring IoC/AOP.
* **Обязательные Sentence Frames:**
  * `When an HTTP request hits the controller, it first passes through the security filter chain.`
  * `Spring manages the bean lifecycle via Dependency Injection, which decouples...`
  * `We mark the service method as @Transactional so that if an exception is thrown, all changes are rolled back.`
  * `To avoid the N+1 query problem, we fetch the related entities eagerly using JOIN FETCH.`
* **Субботний AI-чекпоинт (Mid-point Mock):** 30 минут: 1 задача Live Coding (Medium) + 15 минут вопросов по Java/Spring/DB. Обязательное применение **Протокола А** (минимум 3 раза переспросить/уточнить вопрос интервьюера через фреймы).

#### Неделя 5: Поведенческое интервью (Behavioral / STAR) и Презентация Опыта
* **Фокус:** Прошедшее время (Past Simple — только 25 ключевых неправильных/правильных инженерных глаголов в Past: `built`, `designed`, `debugged`, `migrated`, `reduced`, `ran into`, `resolved`).
* **Структура:** Написание и заучивание (через Shadowing собственной эталонной записи) **4 историй по методу STAR** (Situation, Task, Action, Result) + **60-секундный Self-Pitch ("Tell me about yourself")**.
  1. *Story 1:* Сложный баг / проблема с производительностью.
  2. *Story 2:* Архитектурное решение в пет-проекте / командном проекте IITU.
  3. *Story 3:* Дедлайн / быстрое освоение новой технологии.
  4. *Story 4:* Конфликт / код-ревью / технический спор.
* **Обязательные Sentence Frames:**
  * `I’m a third-year Computer Science student at IITU focusing on backend engineering with Java and Spring...`
  * `The main challenge we ran into was that...`
  * `To address this, I took ownership of [X] and implemented [Y]...`
  * `As a result, we reduced response time from [X] ms to [Y] ms.`
* **Субботний AI-чекпоинт:** Behavioral Mock Interview (AI пытается подловить вопросами *"Why didn't you use X instead?"*, *"What would you do differently?"*).

#### Неделя 6: System Design Basics (Кэширование, Очереди, Масштабирование, Микросервисы)
* **Фокус:** Язык архитектурных компромиссов (Trade-offs, Bottlenecks, Single Point of Failure, Eventual Consistency).
* **Narrow Input для Shadowing:** ByteByteGo (Rate Limiter, URL Shortener, Notification System).
* **Обязательные Sentence Frames:**
  * `Before jumping into the design, let’s clarify the functional and non-functional requirements.`
  * `To handle read-heavy traffic, we can place a Redis cache in front of the database using the Cache-Aside pattern.`
  * `To decouple service A from service B and handle traffic spikes, we introduce a message queue like Kafka.`
  * `The trade-off here is eventual consistency, which is acceptable for this use case because...`
* **Субботний AI-чекпоинт:** Спроектировать вслух за 25 минут *URL Shortener* или *Rate Limiter* от сбора требований до обработки отказов.

#### Неделя 7: Дебаггинг, Production Incidents, Testing & CI/CD
* **Фокус:** Гипотезы и расследование инцидентов (Conditional sentences 1 & 2 типа без сложной грамматики: `If the service goes down, I would first check...`).
* **Обязательные Sentence Frames:**
  * `My first step to troubleshoot high latency in production would be checking the logs and metrics...`
  * `If the CPU usage spikes to 100%, I would take a thread dump to look for infinite loops or deadlocks.`
  * `In unit tests, we mock external dependencies using Mockito to test business logic in isolation.`
  * `For integration testing, we spin up a real PostgreSQL container using Testcontainers.`
* **Субботний AI-чекпоинт:** Сценарий «Production on Fire» — интервьюер дает симптомы падения сервиса, задача — вслух задавать диагностические вопросы и локализовать проблему на английском.

#### Неделя 8: Полная симуляция под давлением (Full Dress Rehearsal & Anti-Freeze Stress Tests)
* **Фокус:** Сведение всех 4 блоков (Self-Pitch + Behavioral + Live Coding + System Design/Backend Q&A) в единый 45–60 минутный марафон.
* **Ежедневный режим Пн–Пт:**
  * **Пн:** Полный прогон Self-Pitch + 4 STAR историй + вопросы о компании/роли (`Reverse Interview Questions`).
  * **Вт:** 45-минутный Live Coding Mock (Medium задача) с жестким комментированием каждого шага.
  * **Ср:** 45-минутный Backend Deep Dive (Java + Spring + SQL) с AI в режиме *Aggressive Interrupting Interviewer* (перебивает и просит уточнить).
  * **Чт:** 45-минутный System Design + Architecture Defense своего главного проекта на GitHub.
  * **Пт:** Аудит слабых мест, прогон топ-100 Cloze-карточек в Anki, легкий Shadowing для тонуса.
* **Суббота (Финальный Экзамен):** 60-минутное полное сквозное интервью (5 мин Pitch → 20 мин Live Coding → 20 мин Backend/System Design → 10 мин Behavioral → 5 мин вопросы интервьюеру). Запись аудио целиком и контрольный замер: 0 переходов на русский, < 2 секунд заминки при сложных вопросах за счет автоматического срабатывания Протоколов А/Б/В.