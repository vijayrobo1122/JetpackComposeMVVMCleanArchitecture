plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.ksp)
    alias(libs.plugins.google.dagger.hilt)
}

android {
    namespace = "com.app.jetpack.mvvm.business.artistdetail.di"
    compileSdk = rootProject.ext.get("compileSdkVersion") as Int

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {

    implementation(project(":business:artistdetail:data:di"))
    implementation(project(":business:artistdetail:domain:di"))

    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
}