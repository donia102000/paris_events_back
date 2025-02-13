# Utiliser une image légère OpenJDK 17
FROM openjdk:17-jdk-alpine

# Définir le répertoire de travail
WORKDIR /app

# Exposer le port de l’application
EXPOSE 8089

# Copier le fichier JAR de l’application
ADD target/parisEventProject-5.0.0.jar app.jar

# Exécuter l’application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]