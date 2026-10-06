param(
    [string]$CheckpointMessage = "chore(progress): update learning checkpoint and knowledge audit"
)

$ErrorActionPreference = "Stop"
$RepoRoot = Resolve-Path "$PSScriptRoot\..\..\.."
Set-Location $RepoRoot

Write-Host "=== [1/3] Compiling daily Java labs (day01..day04) ==="
if (!(Test-Path "out")) { New-Item -ItemType Directory -Path "out" | Out-Null }
javac -encoding UTF-8 -d out day01/*.java day02/*.java day03/*.java day04/*.java
Write-Host "Compilation OK."

Write-Host "=== [2/3] Checking Git status ==="
git status -s

Write-Host "=== [3/3] Committing & Pushing checkpoint to GitHub ==="
git add .
$hasChanges = git status --porcelain
if ($hasChanges) {
    git commit -m $CheckpointMessage
    git push
    Write-Host "Successfully pushed checkpoint: $CheckpointMessage"
} else {
    Write-Host "Working tree clean. Nothing new to commit."
}
