plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.ksp)
    alias(libs.plugins.google.dagger.hilt)
}

android {
    namespace = "com.app.jetpack.mvvm.business.moviedetail.di"
    compileSdk = 34

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {

    implementation(project(":business:moviedetail:data:di"))
    implementation(project(":business:moviedetail:domain:di"))

    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
}
