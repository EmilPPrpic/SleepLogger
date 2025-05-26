FROM openjdk:11

WORKDIR /app

# Copy Gradle config and wrapper
COPY build.gradle gradlew settings.gradle ./
COPY gradle/ gradle/

# ✅ Make gradlew executable
RUN chmod +x ./gradlew

# Optional: Only needed if you're generating wrapper files — skip if already checked in
# RUN ./gradlew wrapper

# Copy source code
COPY src/ src

# Build the project
RUN ./gradlew build

# Run the built JAR
ENTRYPOINT ["java", "-jar", "build/libs/sleep-0.0.1-SNAPSHOT.jar"]
