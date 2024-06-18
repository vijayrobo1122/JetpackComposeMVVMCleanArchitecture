import org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
import org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED
import org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED
import org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED
import org.gradle.api.tasks.testing.logging.TestLogEvent.STANDARD_ERROR
import org.gradle.api.tasks.testing.logging.TestLogEvent.STANDARD_OUT

// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.google.dagger.hilt) apply false
    alias(libs.plugins.google.ksp) apply false
    alias(libs.plugins.detekt) apply false
    id("jacoco")
}

buildscript {
    extra.apply {
        set("compileSdkVersion", 34)
        set("minSdkVersion", 24)
        set("targetSdkVersion", 34)
    }
}

allprojects {

    tasks.register("printAllDependencies") {
        dependsOn("dependencies")
    }

    apply(plugin = "io.gitlab.arturbosch.detekt")
    val analysisDir = file(projectDir)
    val configFile = file("$rootDir/config/detekt/detekt.yml")
    tasks.withType(io.gitlab.arturbosch.detekt.Detekt::class) {
        parallel = true
        config.setFrom(listOf(configFile))
        setSource(analysisDir)
        include("**/src/**/java/**")
        include("**/src/**/kotlin/**")
        include("**/*.kts")
        exclude("**/build/**")
        exclude("**/test*/**")
        exclude("**/androidTest*/**")
        exclude("**/external/cd-android-dependencies/**")
        reports {
            html.required.set(true)
            sarif.required.set(false)
            txt.required.set(false)
            xml.required.set(false)
        }
    }

    // enable console logging for exceptions in unit tests
    tasks.withType(Test::class) {
        testLogging {
            events = setOf(
                FAILED,
                PASSED,
                SKIPPED,
                STANDARD_OUT,
                STANDARD_ERROR
            )
            exceptionFormat = FULL
            showExceptions = true
            showCauses = true
            showStackTraces = true
            showStandardStreams = true
            beforeTest(closureOf<Any> {
                logger.lifecycle("->Start: $this")
            })
            afterTest(closureOf<Any> {
                logger.lifecycle("<-End: $this")
            })
        }
    }
}
