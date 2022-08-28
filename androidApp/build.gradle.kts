import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.application")
    kotlin("android")
}



android {
    compileSdk = libs.versions.compileSdk.get().toInt()
    defaultConfig {
        applicationId = "com.eungpang.karlflix"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        val omdbApiKey = gradleLocalProperties(rootDir).getProperty("OMDB_API_KEY")

        getByName("debug") {
            buildConfigField("String", "omdbApiKey", "$omdbApiKey")
        }
        getByName("release") {
            isMinifyEnabled = false
            buildConfigField("String", "omdbApiKey", "" + omdbApiKey + "")
        }
    }

    buildFeatures {
        // Enables Jetpack Compose for this module
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }
}

dependencies {
    implementation(project(":shared"))

    implementation(libs.bundles.app.ui)
    implementation(libs.koin.android)
    implementation(libs.coroutines.android)

    testImplementation(libs.junit)

    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.2.1")
}