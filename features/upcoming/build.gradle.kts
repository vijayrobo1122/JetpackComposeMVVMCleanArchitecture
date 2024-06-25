apply(from = "$rootDir/config/analytics/jacoco.gradle")

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.ksp)
    alias(libs.plugins.google.dagger.hilt)
}

android {
    namespace = "com.app.jetpack.mvvm.features.upcoming"
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

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {

    implementation(project(":business:moviedetail:di"))
    implementation(project(":business:moviedetail:domain:main"))
    implementation(project(":business:moviedetail:domain:model"))

    implementation(project(":common:domain:models"))
    implementation(project(":common:domain:api"))
    implementation(project(":common:general:extensions"))
    implementation(project(":common:general:models"))
    implementation(project(":common:layers:presentation:widgets"))
    implementation(project(":common:navigation"))
    implementation(project(":common:ui:compositions"))
    implementation(project(":common:ui:theme"))

    implementation(libs.androidx.paging.compose)

    implementation(libs.androidx.hilt.navigation.compose)

    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.ui.test.junit4)

    debugImplementation(libs.androidx.ui.test.manifest)

    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockk.agent)
    testImplementation(libs.mockk.android)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.inline)
    testImplementation(libs.androidx.core.testing)
    testImplementation(libs.androidx.junit.ktx)
    testImplementation(libs.turbine)
}
