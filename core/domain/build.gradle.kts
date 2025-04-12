plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "com.github.artnehay.insightnews.core.domain"
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
}