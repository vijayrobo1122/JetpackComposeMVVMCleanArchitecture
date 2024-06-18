plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.app.jetpack.mvvm.common.ui.components"
    compileSdk = rootProject.ext.get("compileSdkVersion") as Int

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = rootProject.ext.get("kotlinCompilerVersion") as String
    }
}

dependencies {

    implementation(project(":common:ui:theme"))

    implementation(libs.androidx.material3)

    implementation(libs.landscapist.coil)
    implementation(libs.landscapist.animation)
    implementation(libs.landscapist.placeholder)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
