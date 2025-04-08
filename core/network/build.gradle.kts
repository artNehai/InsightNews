plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.jetbrainsKotlinSerialization)
}

android {
    namespace = "com.github.artnehay.insightnews.core.network"
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

    implementation(libs.androidx.core.ktx)
    // Hilt
    implementation(libs.hilt)
    ksp(libs.hilt.compiler)
    // Network
    implementation(libs.squareup.retrofit)
    implementation(libs.kotlinx.serialization)
    implementation(libs.jakewharton.retrofit.serialization.converter)
    implementation(libs.squareup.okhttp3)
}