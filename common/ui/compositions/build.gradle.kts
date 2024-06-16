import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
}
val properties = Properties().apply {
    load(project.rootProject.file("local.properties").inputStream())
}

android {
    namespace = "com.app.jetpack.mvvm.common.ui.compositions"
    compileSdk = 34

    defaultConfig {

        buildConfigField("String", "IMAGE_BASE_URL", properties.getProperty("movie_image_url"))

    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.5"
    }
}

dependencies {

    implementation(project(":common:general:extensions"))
    implementation(project(":common:general:models"))
    implementation(project(":common:ui:resources:strings"))
    implementation(project(":common:ui:components"))
    implementation(project(":common:ui:theme"))
    implementation(project(":common:ui:widgets"))

    implementation(libs.androidx.material3)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.paging.compose)

    implementation(libs.androidx.constraintlayout.compose)

    implementation(libs.landscapist.coil)
    implementation(libs.landscapist.animation)
    implementation(libs.landscapist.placeholder)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
