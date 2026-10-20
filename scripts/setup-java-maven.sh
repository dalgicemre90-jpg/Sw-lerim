#!/usr/bin/env bash
set -euo pipefail

if ! command -v curl >/dev/null 2>&1; then
  echo "curl is required. Please install curl and try again."
  exit 1
fi

if ! command -v bash >/dev/null 2>&1; then
  echo "bash is required."
  exit 1
fi

if [ -f "$HOME/.sdkman/bin/sdkman-init.sh" ]; then
  echo "SDKMAN detected. Loading environment..."
  . "$HOME/.sdkman/bin/sdkman-init.sh"
else
  echo "Installing SDKMAN..."
  curl -s "https://get.sdkman.io" | bash
  . "$HOME/.sdkman/bin/sdkman-init.sh"
fi

sdk install java 21.0.8-tem
sdk install maven 3.9.9
sdk default java 21.0.8-tem
sdk default maven 3.9.9

echo "Java and Maven setup completed."
java -version
mvn -version
