# Sw-lerim runtime build instructions

## Install prerequisites

```bash
./scripts/install-jdk21-maven-gradle.sh
```

## Validate environment

```bash
./scripts/validate-java-env.sh
```

## Build the project

```bash
mvn clean package
```

## Run the project

```bash
java -jar target/sw-lerim-1.0.0-SNAPSHOT.jar
```

## Upstream Paper and Velocity builds

```bash
mkdir -p ~/minecraft
cd ~/minecraft

git clone https://github.com/PaperMC/Paper.git paper-1.21.1
cd paper-1.21.1
git checkout ver/1.21.1
./gradlew clean paperclip

cd ~/minecraft
git clone https://github.com/PaperMC/Paper.git paper-1.21.11
cd paper-1.21.11
git checkout ver/1.21.11
./gradlew clean paperclip

cd ~/minecraft
git clone https://github.com/PaperMC/Velocity.git velocity-4.0.0
cd velocity-4.0.0
git checkout Velocity-4.0.0
./gradlew build
```
