import org.gradle.kotlin.dsl.annotationProcessor
import org.gradle.kotlin.dsl.debugImplementation
import org.gradle.kotlin.dsl.implementation
import org.gradle.kotlin.dsl.releaseImplementation

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "uz.gita.bekzod1205.currencyapp"
    compileSdk = 36

    defaultConfig {
        applicationId = "uz.gita.bekzod1205.currencyapp"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
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
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.fragment)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //lifecycle
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.7")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")
    implementation("androidx.fragment:fragment-ktx:1.8.2")

    //Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    // recommended
    implementation("dev.androidbroadcast.vbpd:vbpd:2.0.4")

    // additional factories that use reflection under hood
    implementation("dev.androidbroadcast.vbpd:vbpd-reflection:2.0.4")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0") // Use the latest stable version

    // Gson Converter (for JSON parsing)
    implementation("com.squareup.retrofit2:converter-gson:2.9.0") // Use the same version as Retrofit


    // Chucker https://github.com/ChuckerTeam/chucker
    debugImplementation ("com.github.chuckerteam.chucker:library:4.2.0")
    releaseImplementation ("com.github.chuckerteam.chucker:library-no-op:4.2.0")
    implementation("com.github.bumptech.glide:glide:5.0.0-rc01")

}