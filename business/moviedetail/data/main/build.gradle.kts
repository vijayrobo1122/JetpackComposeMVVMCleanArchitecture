import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.ksp)
    alias(libs.plugins.google.dagger.hilt)
}

val properties = Properties().apply {
    load(project.rootProject.file("local.properties").inputStream())
}

android {
    namespace = "com.app.jetpack.mvvm.business.moviedetail.data.main"
    compileSdk = rootProject.ext.get("compileSdkVersion") as Int

    defaultConfig {

        buildConfigField("String", "BASE_URL", properties.getProperty("movie_base_url"))
        buildConfigField("String", "API_KEY", properties.getProperty("movie_api_key"))

    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {

    implementation(project(":common:domain:api"))
    implementation(project(":common:domain:models"))
    implementation(project(":common:general:extensions"))
    implementation(project(":business:moviedetail:data:entity"))
    implementation(project(":business:moviedetail:domain:main"))
    implementation(project(":business:moviedetail:domain:model"))

    implementation(libs.androidx.paging.compose)

    implementation(libs.androidx.core.ktx)

    implementation(libs.timber)

    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.okhttp.logging.interceptor)

    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    testImplementation(libs.mockk.android)
    testImplementation(libs.mockk.agent)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
