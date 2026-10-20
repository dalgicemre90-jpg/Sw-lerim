#!/usr/bin/env bash
set -euo pipefail

if [ -f "$HOME/.sdkman/bin/sdkman-init.sh" ]; then
  . "$HOME/.sdkman/bin/sdkman-init.sh"
fi

java_cmd=$(command -v java || true)
mvn_cmd=$(command -v mvn || true)

if [ -z "$java_cmd" ]; then
  echo "Java not found. Please run ./scripts/setup-java-maven.sh first."
  exit 1
fi

if [ -z "$mvn_cmd" ]; then
  echo "Maven not found. Please run ./scripts/setup-java-maven.sh first."
  exit 1
fi

java -version
mvn -version

echo "Environment is ready for Maven + Java 21 project builds."
