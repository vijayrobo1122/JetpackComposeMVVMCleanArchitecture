import java.util.Properties

apply(from = "$rootDir/config/analytics/jacoco.gradle")

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
    namespace = "com.app.jetpack.mvvm.business.artistdetail.data.main"
    compileSdk = rootProject.ext.get("compileSdkVersion") as Int

    defaultConfig {

        buildConfigField("String", "BASE_URL", properties.getProperty("movie_base_url"))
        buildConfigField("String", "API_KEY", properties.getProperty("movie_api_key"))

    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = rootProject.ext.get("jvmTargetVersion") as String
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {

    implementation(project(":common:domain:api"))
    implementation(project(":common:domain:models"))
    implementation(project(":common:general:extensions"))
    implementation(project(":common:network"))
    implementation(project(":business:artistdetail:data:entity"))
    implementation(project(":business:artistdetail:domain:main"))
    implementation(project(":business:artistdetail:domain:model"))

    implementation(libs.kotlinx.coroutines.core)

    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.okhttp.logging.interceptor)

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
