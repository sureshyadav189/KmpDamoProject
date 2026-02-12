import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    
    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            export(project(":core-network"))
            export(project(":feature:search:data"))
            export(project(":feature:search:domen"))
            export(project(":feature:search:ui"))

            export(project(":feature:detail:data"))
            export(project(":feature:detail:domen"))
            export(project(":feature:detail:ui"))

            isStatic = true
        }
    }
    
    sourceSets {
        androidMain.dependencies {

        }
        commonMain.dependencies {
            api(projects.coreNetwork)

            api(projects.feature.search.data)
            api(projects.feature.search.domen)
            api(projects.feature.search.ui)

            api(projects.feature.detail.data)
            api(projects.feature.detail.domen)
            api(projects.feature.detail.ui)

            implementation(libs.koin.core)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        iosMain.dependencies {

        }
    }
}

android {
    namespace = "com.example.kmpdamoproject.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}
