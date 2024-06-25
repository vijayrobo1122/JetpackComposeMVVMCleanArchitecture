apply(from = "$rootDir/config/analytics/jacoco.gradle")

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.app.jetpack.mvvm.business.moviedetail.domain.model"
    compileSdk = rootProject.ext.get("compileSdkVersion") as Int

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = rootProject.ext.get("jvmTargetVersion") as String
    }
}
