---
name: learning-progress-tracker
description: >-
  Tracks, audits, and syncs Nurbek's exact learning stopping point, topic
  mastery scores ([GREEN]/[YELLOW]/[RED]), completed code labs (day01..day30,
  nexuspay, aegisai), and pushes checkpoints to GitHub. Activate when the user
  asks where we stopped, requests a knowledge check/audit, finishes a topic, or
  asks to save/commit/push learning progress to GitHub.
---

# Learning Progress Tracker & GitHub Sync (`learning-progress-tracker`)

## Overview
Maintains single-source-of-truth tracking across Nurbek's unified learning repository:
- [`PROGRESS_AND_KNOWLEDGE_AUDIT.md`](../../../PROGRESS_AND_KNOWLEDGE_AUDIT.md) — Exact stopping point, current question, and detailed knowledge evaluation matrix.
- [`README.md`](../../../README.md) — Public GitHub dashboard summarizing completed modules, architecture, and current checkpoint.

## Workflow

### 1. Audit Current Knowledge & Stopping Point
1. Inspect the latest completed code in `dayXX/` and [`nexuspay/`](../../../nexuspay/).
2. Evaluate user responses from the current session using the 3-tier rubric:
   - `[GREEN]` (85–100%): Explains root cause and hardware/JVM mechanics accurately in own words.
   - `[YELLOW]` (60–84%): Correct intuition, needs precision on edge cases or constants (e.g., `MIN_TREEIFY_CAPACITY = 64`, `volatile` non-atomicity).
   - `[RED]` (<60%): Concept confusion; schedule immediate code drill before advancing.
3. Update Section 1 (`ТОЧКА ОСТАНОВКИ`), Section 2 (`АУДИТ ЗНАНИЙ`), and Section 3 checkboxes in [`PROGRESS_AND_KNOWLEDGE_AUDIT.md`](../../../PROGRESS_AND_KNOWLEDGE_AUDIT.md) and [`README.md`](../../../README.md).

### 2. Verify & Sync to GitHub
1. Run the verification and sync script:
   [sync_progress.ps1](./scripts/sync_progress.ps1)
2. Or execute manually:
   ```powershell
   javac -encoding UTF-8 -d out day01/*.java day02/*.java day03/*.java day04/*.java
   git status -s
   git add .
   git commit -m "docs(progress): checkpoint at <current-topic>"
   git push
   ```
