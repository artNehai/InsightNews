plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "com.github.artnehay.insightnews.core.testing"
    compileSdk = libs.versions.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
    }
}

java {
    toolchain {
        val version = libs.versions.java.get().toInt()
        languageVersion.set(JavaLanguageVersion.of(version))
    }
}

dependencies {

    implementation(project(":core:domain"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.junit)
    implementation(libs.kotlinx.coroutines.testing)
}