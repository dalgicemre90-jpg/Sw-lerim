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

if [ ! -d "velocity-4.0.0" ]; then
  git clone --depth 1 https://github.com/PaperMC/Velocity.git velocity-4.0.0
  cd velocity-4.0.0
  git checkout --detach Velocity-4.0.0 || true
  cd ..
fi

cd velocity-4.0.0
./gradlew build

echo "Velocity 4.0.0 build completed."
echo "Built jar location: ${TARGET_DIR}/velocity-4.0.0/build/libs"
