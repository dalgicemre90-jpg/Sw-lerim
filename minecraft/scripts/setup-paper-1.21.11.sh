#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/../.." && pwd)"
TARGET_DIR="${ROOT_DIR}/minecraft/servers"
mkdir -p "${TARGET_DIR}"
cd "${TARGET_DIR}"

if [ -f "$HOME/.sdkman/bin/sdkman-init.sh" ]; then
  . "$HOME/.sdkman/bin/sdkman-init.sh"
fi

if command -v sdk >/dev/null 2>&1; then
  sdk install java 21.0.8-tem >/dev/null 2>&1 || true
  sdk use java 21.0.8-tem >/dev/null 2>&1 || true
fi

if [ ! -d "paper-1.21.11" ]; then
  git clone --depth 1 --branch ver/1.21.11 https://github.com/PaperMC/Paper.git paper-1.21.11
fi

cd paper-1.21.11
./gradlew clean paperclip

echo "Paper 1.21.11 build completed."
echo "Built jar location: ${TARGET_DIR}/paper-1.21.11/build/libs"
