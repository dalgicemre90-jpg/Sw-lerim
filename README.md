# Sw-lerim

Bu repository, Java 21 ve Maven tabanlı bir başlangıç yapısını içerir. Proje, JDK 21 ve Maven kurulumunu kolaylaştıracak şekilde hazırlanmıştır.

## Gereksinimler

- JDK 21
- Maven 3.9+
- Git
- Linux/macOS/WSL veya Windows PowerShell

## Hızlı kurulum (SDKMAN)

Aşağıdaki komutlar Linux/macOS/WSL için uygundur:

```bash
curl -s "https://get.sdkman.io" | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"
sdk install java 21.0.8-tem
sdk install maven 3.9.9
sdk default java 21.0.8-tem
sdk default maven 3.9.9
```

Daha sonra proje klasöründe şunları çalıştır:

```bash
mvn -version
java -version
mvn clean package
java -jar target/sw-lerim-1.0.0-SNAPSHOT.jar
```

## Repo içindeki otomatik kurulum dosyaları

- `scripts/setup-java-maven.sh` : SDKMAN ile JDK 21 ve Maven kurulumu yapar
- `scripts/verify-env.sh` : Java/Maven ortamını kontrol eder
- `.sdkmanrc` : proje bazlı Java/Maven sürümünü tutar

## Proje çalıştırma

```bash
./scripts/setup-java-maven.sh
./scripts/verify-env.sh
mvn clean package
```

## Yapı

```text
.
├── pom.xml
├── README.md
├── .gitignore
├── .sdkmanrc
├── scripts/
│   ├── setup-java-maven.sh
│   └── verify-env.sh
└── src/
    └── main/
        └── java/
            └── com/
                └── example/
                    └── swlerim/
                        └── App.java
```

## Java sürümü

`pom.xml` dosyasında Java sürümü `21` olarak tanımlanmıştır.

## Not

Bu repo, JDK ve Maven ortamının doğru kurulmasına yardımcı olacak şekilde hazırlandı. Gerçek JDK binary dosyaları GitHub reposuna eklenmez; kurulum, SDKMAN veya sistem paketleri ile yapılır.
