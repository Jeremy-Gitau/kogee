#!/usr/bin/env bash
set -euo pipefail

set -e -u -o pipefail

echo "Running ktlint format..."
./gradlew ktlintFormat

echo "ktlint format passed."

echo "Running ktlint check..."
./gradlew ktlintCheck

echo "ktlint check passed."