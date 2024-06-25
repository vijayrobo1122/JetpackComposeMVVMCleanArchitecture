apply(from = "$rootDir/config/analytics/jacoco.gradle")

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.app.jetpack.mvvm.common.domain.api"
    compileSdk = rootProject.ext.get("compileSdkVersion") as Int

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = rootProject.ext.get("jvmTargetVersion") as String
    }
}

dependencies {

    implementation(project(":common:domain:models"))

    implementation(libs.androidx.paging.compose)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.androidx.lifecycle.livedata.core.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
