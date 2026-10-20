# Sw-lerim

Bu repository, Java 21 + Maven tabanlı bir başlangıç yapısını içerir. Ayrıca Paper ve Velocity sunucu sürümlerinin kurulumu ve fork yapısı için gerekli betikler de eklenmiştir.

## Gereksinimler

- JDK 21
- Maven 3.9+
- Gradle (Paper ve Velocity build için)
- Git
- Linux/macOS/WSL veya Windows PowerShell

## Hızlı kurulum (SDKMAN)

Aşağıdaki komutlar Linux/macOS/WSL için uygundur:

```bash
curl -s "https://get.sdkman.io" | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"
sdk install java 21.0.8-tem
sdk install maven 3.9.9
sdk install gradle
sdk default java 21.0.8-tem
sdk default maven 3.9.9
```

## Paper / Velocity kurulumu

Repo içerisinde hazır kurulum betikleri vardır:

```bash
cd Sw-lerim
./minecraft/scripts/setup-paper-1.21.1.sh
./minecraft/scripts/setup-paper-1.21.11.sh
./minecraft/scripts/setup-velocity-4.0.0.sh
```

İstersen tek komutta hepsini kurmak için:

```bash
cd Sw-lerim
./minecraft/scripts/build-all.sh
```

## Kurulum sonrası klasör yapısı

```text
Sw-lerim/
├── README.md
├── pom.xml
├── .sdkmanrc
├── scripts/
│   ├── setup-java-maven.sh
│   └── verify-env.sh
├── minecraft/
│   ├── README.md
│   ├── scripts/
│   │   ├── setup-paper-1.21.1.sh
│   │   ├── setup-paper-1.21.11.sh
│   │   ├── setup-velocity-4.0.0.sh
│   │   └── build-all.sh
│   └── .gitignore
└── src/
    └── main/
        └── java/
            └── com/
                └── example/
                    └── swlerim/
                        └── App.java
```

## Paper sürümleri

- Paper 1.21.1
- Paper 1.21.11

Açıklama: Bu betikler resmi PaperMC upstream reposunu klonlar ve `./gradlew clean paperclip` ile üretim JAR'ını oluşturur.

## Velocity sürümü

- Velocity 4.0.0

Açıklama: Bu betik resmi Velocity upstream reposunu klonlar ve `./gradlew build` ile JAR'ını üretir.

## Not

Bu repo kendisi Paper/Velocity server kodunu içermez; upstream reposu otomatik kurup build eder. Kendi fork ve custom patch'leri ortaya çıkarmak için bu klasör yapısı üzerine ayrı branch/patch set eklenebilir.
