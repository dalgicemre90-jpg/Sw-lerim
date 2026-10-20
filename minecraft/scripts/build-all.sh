#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/../.." && pwd)"
cd "${ROOT_DIR}"

./minecraft/scripts/setup-paper-1.21.1.sh
./minecraft/scripts/setup-paper-1.21.11.sh
./minecraft/scripts/setup-velocity-4.0.0.sh

echo "All Paper and Velocity server builds have been started."
