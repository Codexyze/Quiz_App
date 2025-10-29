plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    kotlin("plugin.serialization") version "2.1.10"

    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("org.jetbrains.dokka") version "1.9.20"
}

android {
    namespace = "com.example.quizapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.quizapp"
        minSdk = 27
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.example.quizapp.di.HiltTestRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources= true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = true
            isShrinkResources= true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //Ktor
//    implementation("io.ktor:ktor-client-core:2.3.5") // Core Ktor client
//    implementation("io.ktor:ktor-client-cio:2.3.5")  // For making network requests
//    implementation("io.ktor:ktor-client-content-negotiation:2.3.5") // For automatic JSON serialization
//    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.5") // Kotlinx serialization support
//    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0") // JSON parser
//    implementation("org.slf4j:slf4j-simple:2.0.9") // or latest

    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.client)
    implementation(libs.ktor.client.content)
    implementation(libs.ktor.client.seralization)
    implementation(libs.ktor.client.json)
    implementation(libs.logger)

    //navigation JETPACK COMPOSE

    implementation(libs.androidx.navigation)

    //Hilt
    implementation(libs.dagger.hilt)
    kapt(libs.dagger.hilt.compiler)
    //Most Error making Library
    implementation(libs.dagger.hilt.navigation)
//    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    //Mock Test ktor
    testImplementation(libs.ktor.client.mock)
    //testImplementation("io.ktor:ktor-client-mock:2.3.5")

    androidTestImplementation(libs.ktor.client.mock)

   // androidTestImplementation("io.ktor:ktor-client-mock:2.3.5")


    //hilt testing
    // For Robolectric tests.
    testImplementation("com.google.dagger:hilt-android-testing:2.51.1")
    // ...with Kotlin.
    kaptTest("com.google.dagger:hilt-android-compiler:2.51.1")
    // ...with Java.
    testAnnotationProcessor("com.google.dagger:hilt-android-compiler:2.51.1")


    // For instrumented tests.
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.51.1")
    // ...with Kotlin.
    kaptAndroidTest("com.google.dagger:hilt-android-compiler:2.51.1")
    // ...with Java.
    androidTestAnnotationProcessor("com.google.dagger:hilt-android-compiler:2.51.1")

}

// Dokka configuration
tasks.dokkaHtml.configure {
    outputDirectory.set(layout.buildDirectory.dir("dokka/html"))

    dokkaSourceSets {
        named("main") {
            displayName.set("Quiz App Repository Implementation")
            documentedVisibilities.set(setOf(
                org.jetbrains.dokka.DokkaConfiguration.Visibility.PUBLIC,
                org.jetbrains.dokka.DokkaConfiguration.Visibility.PROTECTED
            ))

            // Include source code links
            sourceLink {
                localDirectory.set(file("src/main/java"))
                remoteLineSuffix.set("#L")
            }
        }
    }
}
