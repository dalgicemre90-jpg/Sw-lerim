#!/usr/bin/env bash
set -euo pipefail

if ! command -v curl >/dev/null 2>&1; then
  echo "curl is required. Please install curl first."
  exit 1
fi

if [ -f "$HOME/.sdkman/bin/sdkman-init.sh" ]; then
  . "$HOME/.sdkman/bin/sdkman-init.sh"
else
  echo "Installing SDKMAN..."
  curl -s "https://get.sdkman.io" | bash
  . "$HOME/.sdkman/bin/sdkman-init.sh"
fi

sdk install java 21.0.8-tem || true
sdk install maven 3.9.9 || true
sdk install gradle || true
sdk default java 21.0.8-tem
sdk default maven 3.9.9

echo "JDK 21, Maven, and Gradle are installed."
java -version
mvn -version
gradle -v
