# 🚀 Java 21, Spring Boot 3, High-Load & AI Engineering — Unified «До НГ» Master Repository

> **Инженер:** Нурбек Сайдуали (3 курс IITU / МУИТ, Алматы)  
> **Единая программа:** 30-Day Core Java & High-Load Drills + 8-Week Ultralearning Roadmap («До НГ»: `NexusPay` FinTech Core & `AegisAI` Platform + LeetCode REACTO + Tech English B2)  
> **Полный детальный аудит и чекпоинт:** 👉 [**`PROGRESS_AND_KNOWLEDGE_AUDIT.md`**](PROGRESS_AND_KNOWLEDGE_AUDIT.md)

---

## 📍 ТЕКУЩАЯ ТОЧКА ОСТАНОВКИ (Обновлено: `06.10.2026 13:25`)

* **Активный модуль:** **Тема 4 (День 4) — Многопоточность, JMM, `synchronized`, `AtomicInteger` (CAS), `volatile` & `Deadlock`** + переход к сервису переводов **`NexusPay` (Неделя 1, Вт–Ср)**.
* **Что только что завершено:**
  1. Разобрана физика гонки данных (`Race Condition`) и почему `count++` состоит из 3 шагов процессора (`READ -> ADD -> WRITE`).
  2. Написан и запущен бенчмарк [`day04/ConcurrencyLab.java`](day04/ConcurrencyLab.java) (100 потоков × 10 000 инкрементов = `1 000 000`):
     * `unsafeCount` = `989 802` (потеряно `10 198`)
     * `volatileCount` = `990 399` (потеряно `9 601`)
     * `syncCount` (`synchronized` — пессимистичный монитор) = `1 000 000` за **`106 ms`**
     * `atomicCount` (`AtomicInteger` — оптимистичный Lock-Free CAS) = `1 000 000` за **`30 ms` (в 3.5 раза быстрее!)**
  3. Выдана теория **Шага 2 из 3**: проблема видимости (`Visibility Problem`), кэши ядер CPU `L1/L2`, барьеры памяти `volatile` и `Happens-Before`.
* **На чём именно остановились (Следующее действие при старте):**
  * ❓ **Ответить на контрольный вопрос Шага 2 из 3:**
    > *«В сервисе есть фоновый воркер, который крутится в цикле `while (isRunning)`, и главный поток, который при выключении сервера вызывает `isRunning = false`. Что здесь достаточно поставить на поле `isRunning`: `volatile`, `synchronized` или `AtomicBoolean` — и почему?»*
  * ⏭️ **Следом — Шаг 3 из 3:** `Deadlock` (взаимная блокировка) и защита через строгий порядок захвата замков (`Lock Ordering: min(fromId, toId)`) с прямым переносом в `TransferService` проекта [`nexuspay/`](nexuspay/).

---

## 🧠 ЧЕК ЗНАНИЙ ПО ПРОЙДЕННЫМ ТЕМАМ (Knowledge Audit)

| Модуль / Тема | Лаба / Код в репозитории | Уровень усвоения | Подтвержденные навыки (`[GREEN]`) | На что обратить внимание (`[YELLOW]`) |
| :--- | :--- | :---: | :--- | :--- |
| **1. ООП, Rich Domain Model & Java 21 (`record`, `sealed`, `switch`)** | [`day01/`](day01/) | 🟢 **92%** | Инкапсуляция бизнес-правил в сущности (`order.pay()`, `order.cancel()`), иммутабельность `record` и `String`, защита списков `List.copyOf()`, исчерпывающий `switch` по `sealed interface`. | Практика написания компактного конструктора `record` по памяти. |
| **2. Слои Spring Boot & DI (`Controller -> Service -> Repository`)** | [`nexuspay/`](nexuspay/) | 🟢 **90%** | Четкое понимание границ ответственности слоев, внедрения бинов (DI) и почему контроллер никогда не лезет в репозиторий напрямую в обход бизнес-логики сервиса. | Внутреннее устройство прокси (`JDK Dynamic Proxy` vs `CGLIB`) и `self-invocation` (Неделя 1 Чт). |
| **3. Память JVM (`Stack`, `Heap`, `JOL`, `G1GC` vs `ZGC`, `JFR`)** | [`day02/MemoryLab.java`](day02/MemoryLab.java)<br>[`JvmMemoryLayoutExperimentTest.java`](nexuspay/src/test/java/kz/iitu/nexuspay/JvmMemoryLayoutExperimentTest.java)<br>[`GcAndJfrExperiment.java`](nexuspay/src/test/java/kz/iitu/nexuspay/GcAndJfrExperiment.java) | 🟢 **88%** | Понимание Reachability (GC Roots), перехода выживших объектов в `Old Gen`, разницы `StackOverflowError` и `OutOfMemoryError`, 16-байтного заголовка объекта (`Mark Word` + `Klass Word`) и порога 32 GB для `Compressed OOPs`. | Точная формулировка `Pass-by-Value` для ссылок (перезапись локальной ссылки `ref = null` не трогает оригинал). |
| **4. Коллекции (`ArrayList`, `HashMap`, `equals`/`hashCode`, `Two Sum`)** | [`day03/CollectionsLab.java`](day03/CollectionsLab.java) | 🟢 **95%** | Рост `ArrayList` в `1.5x` с копированием массива; коллизии в одном бакете -> деградация до $O(N)$; двухшаговая проверка в `get(key)` (`hash` -> `equals`); почему ключи должны быть иммутабельными; решение `Two Sum` за $O(N)$ time / $O(N)$ space на английском. | Два условия трансформации бакета в Красно-Черное дерево: `TREEIFY_THRESHOLD = 8` **И** `MIN_TREEIFY_CAPACITY = 64`. |
| **5. Многопоточность (`Race Condition`, `synchronized` vs `AtomicInteger` CAS)** | [`day04/ConcurrencyLab.java`](day04/ConcurrencyLab.java) | 🟢 **94%** | Точное понимание разницы между пессимистичной блокировкой (потоки засыпают, тратится время на Context Switch) и оптимистичным CAS в `AtomicInteger` (потоки не засыпают). | Завершить Шаг 2 (`volatile` Visibility) и Шаг 3 (`Deadlock` Lock Ordering). |
| **6. Инфраструктура БД и Миграции (`Docker`, `PostgreSQL 16`, `Flyway`)** | [`nexuspay/docker-compose.yml`](nexuspay/docker-compose.yml)<br>[`V1__init_ledger_schema.sql`](nexuspay/src/main/resources/db/migration/V1__init_ledger_schema.sql) | 🟢 **90%** | Понимание подхода версионируемых миграций `Flyway` (`V1__init_ledger_schema.sql` + `ddl-auto: validate`) вместо ручного создания таблиц в БД; схема двойной записи (`accounts`, `transfers`, `ledger_entries`). | Практика `SELECT ... FOR UPDATE` и `@Version` в сервисе переводов. |

---

## 📂 СТРУКТУРА ЕДИНОГО РЕПОЗИТОРИЯ

```text
Java Spring/
├── README.md                                     # Главный дашборд проекта и точка остановки
├── PROGRESS_AND_KNOWLEDGE_AUDIT.md               # Полный чек знаний, цитаты ответов и единый план
├── AGENTS.md                                     # Контекст для ИИ-агента (автозагрузка точки остановки)
├── .agents/skills/                               # Кастомные скиллы Antigravity
│   ├── java-highload-mentor/SKILL.md             # Скилл интерактивного ментора по единой программе
│   └── learning-progress-tracker/SKILL.md        # Скилл аудита знаний и авто-синхронизации с GitHub
│
├── day01/                                        # [DONE] Rich Domain Model, Invariants, Java 21 record/sealed/switch
├── day02/                                        # [DONE] JVM Memory: Stack, Heap, Pass-by-Value, OOM & SOE
├── day03/                                        # [DONE] Custom SimpleHashMap, Collisions, equals/hashCode bugs
├── day04/                                        # [IN PROGRESS] Concurrency: Race Condition, synchronized vs CAS, volatile
│
├── nexuspay/                                     # ПРОЕКТ №1 («До НГ»): High-Load Event-Driven FinTech Core
│   ├── docker-compose.yml                        # PostgreSQL 16 + Redis 7
│   ├── build.gradle                              # Java 21 LTS, Spring Boot 3.4, Flyway, Testcontainers, JOL
│   └── src/
│       ├── main/resources/application.yml        # Virtual Threads enabled, open-in-view=false, HikariCP
│       ├── main/resources/db/migration/
│       │   └── V1__init_ledger_schema.sql        # Схема двойной записи: accounts, transfers, ledger_entries
│       └── test/java/kz/iitu/nexuspay/
│           ├── JvmMemoryLayoutExperimentTest.java # Вскрытие Object Header (Mark Word, Compressed OOPs) через JOL
│           └── GcAndJfrExperiment.java           # Бенчмарк аллокаций G1GC vs Generational ZGC + JFR
│
├── PLAN_30_DAYS.md                               # 30-дневный план Core Java, Spring Boot 3 & High-Load
├── MONTH_1_DETAILED_ROADMAP.md                   # Подробный роадмап 1-го месяца с аналогиями Фейнмана
├── iitu_3rd_year_2month_ultralearning_roadmap.md # 8-недельный мастер-план «До НГ» (NexusPay + AegisAI + Алгоритмы)
├── iitu_tech_english_a2_to_interview_roadmap.md  # Программа прокачки Tech English (A2 -> B2 Interview Ready)
├── day_01_detailed_plan.md                       # Почасовой боевой план старта NexusPay
├── diagnostic_week1.md                           # Диагностический чек-лист Недели 1
├── cs_market_analysis_2026_2027.md               # Анализ требований рынка 2026–2027 (КЗ, СНГ, Global)
└── research/                                     # Глубокая аналитика по стеку, когнитивным методикам и фразам EN
```

---

## ⚡ БЫСТРЫЙ ЗАПУСК ЛАБОРАТОРНЫХ И ПРОЕКТА

### 1. Запуск изолированных Core-дриллов (`day01` – `day04`)
```powershell
javac -encoding UTF-8 -d out day01/*.java day02/*.java day03/*.java day04/*.java
java "-Dfile.encoding=UTF-8" -cp out day01.Main
java "-Dfile.encoding=UTF-8" -cp out day02.MemoryLab
java "-Dfile.encoding=UTF-8" -cp out day03.CollectionsLab
java "-Dfile.encoding=UTF-8" -cp out day04.ConcurrencyLab
```

### 2. Запуск `NexusPay` (Docker + Тесты памяти JOL)
```powershell
cd nexuspay
docker compose up -d
./gradlew test --info
```
