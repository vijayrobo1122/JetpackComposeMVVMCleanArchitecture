apply(from = "$rootDir/config/analytics/jacoco.gradle")

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.ksp)
    alias(libs.plugins.google.dagger.hilt)
}

android {
    namespace = "com.app.jetpack.mvvm.features.artistdetail"
    compileSdk = rootProject.ext.get("compileSdkVersion") as Int

    defaultConfig {
        minSdk = rootProject.ext.get("minSdkVersion") as Int
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = rootProject.ext.get("jvmTargetVersion") as String
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = rootProject.ext.get("kotlinCompilerVersion") as String
    }
}

dependencies {

    implementation(project(":business:artistdetail:di"))
    implementation(project(":business:artistdetail:domain:main"))
    implementation(project(":business:artistdetail:domain:model"))

    implementation(project(":common:domain:api"))
    implementation(project(":common:domain:models"))
    implementation(project(":common:general:extensions"))
    implementation(project(":common:general:models"))
    implementation(project(":common:layers:presentation:widgets"))
    implementation(project(":common:navigation"))
    implementation(project(":common:ui:compositions"))
    implementation(project(":common:ui:resources:strings"))
    implementation(project(":common:ui:theme"))

    implementation(libs.androidx.material3)

    implementation(libs.androidx.hilt.navigation.compose)

    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.ui.test.junit4)

    testImplementation(libs.kotlinx.coroutines.test)

    testImplementation(libs.mockk.agent)
    testImplementation(libs.mockk.android)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.inline)
    testImplementation(libs.androidx.core.testing)
    testImplementation(libs.androidx.junit.ktx)
    testImplementation(libs.turbine)
}
