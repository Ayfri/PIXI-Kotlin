./gradlew clean
./gradlew build

./gradlew preparePublish
./gradlew publishAllPublicationsToSonatypeRepository closeAndReleaseSonatypeStagingRepository
