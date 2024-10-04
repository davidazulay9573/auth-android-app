# Dockerfile for Android
FROM openjdk:11-jdk

# Install required packages
RUN apt-get update && apt-get install -y \
    wget \
    unzip \
    && rm -rf /var/lib/apt/lists/*

# Set up Android SDK
ENV ANDROID_SDK_ROOT=/opt/android-sdk
RUN wget https://dl.google.com/android/repository/sdk-tools-linux-3859397.zip -O android-sdk.zip && \
    mkdir -p ${ANDROID_SDK_ROOT} && \
    unzip android-sdk.zip -d ${ANDROID_SDK_ROOT} && \
    rm android-sdk.zip

# Add Android SDK to PATH
ENV PATH="${PATH}:${ANDROID_SDK_ROOT/tools/bin}:${ANDROID_SDK_ROOT/platform-tools}"

# Accept licenses
RUN yes | sdkmanager --licenses

# Install build tools and platform
RUN sdkmanager "platforms;android-30" "build-tools;30.0.3"

# Set the working directory
WORKDIR /app

# Copy the Android project files into the container
COPY . .

# Build the Android app
RUN ./gradlew build

# Command to run (optional, typically you won't run Android apps directly in a container)
CMD ["./gradlew", "assembleDebug"]
