#!/usr/bin/env bash
set -euo pipefail

if [ -f "$HOME/.sdkman/bin/sdkman-init.sh" ]; then
  . "$HOME/.sdkman/bin/sdkman-init.sh"
fi

java -version
mvn -version
gradle -v

echo "Environment validation successful."
