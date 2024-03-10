
import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.eins.energypresso"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.eins.energypresso"
        minSdk = 26
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    viewBinding{
        enable=true
    }
    buildFeatures {
        buildConfig=true
        compose=true
        buildConfig=true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.2"
    }

    val localProperties = Properties().run {
        load(File(rootProject.projectDir, "local.properties").inputStream())
        this
    }

    defaultConfig {
        buildConfigField("String", "NAVER_CLIENT_ID", "\"${localProperties["naver_client_id"]}\"")
        buildConfigField("String", "NAVER_CLIENT_SECRET", "\"${localProperties["naver_client_secret"]}\"")
        buildConfigField("String", "NAVER_CLIENT_NAME", "\"${localProperties["naver_client_name"]}\"")

        buildConfigField("String", "CUSTOMER_SPECIFIC_ENDPOINT", "\"${localProperties["CUSTOMER_SPECIFIC_ENDPOINT"]}\"")
        buildConfigField("String", "AWS_IOT_POLICY_NAME", "\"${localProperties["AWS_IOT_POLICY_NAME"]}\"")
        buildConfigField("String", "KEYSTORE_NAME", "\"${localProperties["KEYSTORE_NAME"]}\"")
        buildConfigField("String", "KEYSTORE_PASSWORD", "\"${localProperties["KEYSTORE_PASSWORD"]}\"")
        buildConfigField("String", "CERTIFICATE_ID", "\"${localProperties["CERTIFICATE_ID"]}\"")
    }
}

dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation("androidx.navigation:navigation-runtime-ktx:2.7.7")
    implementation("androidx.navigation:navigation-compose:2.7.7")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    //compose
    val composeBom = platform("androidx.compose:compose-bom:2024.02.01")
    implementation(composeBom)
    androidTestImplementation(composeBom)

    // Choose one of the following:
    // Material Design 3
    implementation("androidx.compose.material3:material3")

    // Android Studio Preview support
    implementation("androidx.compose.ui:ui-tooling-preview")
    debugImplementation("androidx.compose.ui:ui-tooling")

    // UI Tests
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // Optional - Included automatically by material, only add when you need
    // the icons but not the material library (e.g. when using Material3 or a
    // custom design system based on Foundation)
    implementation("androidx.compose.material:material-icons-core")
    // Optional - Add full set of material icons
    implementation("androidx.compose.material:material-icons-extended")
    // Optional - Add window size utils
    implementation("androidx.compose.material3:material3-window-size-class")

    // Optional - Integration with activities
    implementation("androidx.activity:activity-compose:1.8.2")
    // Optional - Integration with ViewModels
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
    // Optional - Integration with LiveData
    implementation("androidx.compose.runtime:runtime-livedata")
    // Optional - Integration with RxJava
    implementation("androidx.compose.runtime:runtime-rxjava2")

    //hilt
    implementation("com.google.dagger:hilt-android:2.48.1")
    kapt("com.google.dagger:hilt-android-compiler:2.48.1")

    //ktx android
    implementation("androidx.activity:activity-ktx:1.8.2")
    implementation("androidx.fragment:fragment-ktx:1.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:1.7.3")

    //aws
    implementation("com.amazonaws:aws-android-sdk-iot:2.16.+")
    implementation("com.amazonaws:aws-android-sdk-cognito:2.16.+")
    implementation("com.amazonaws:aws-android-sdk-mobile-client:2.16.+")
    implementation("com.amazonaws:aws-android-sdk-s3:2.16.+")
    implementation("com.amazonaws:aws-android-sdk-auth-core:2.16.+")

    //네아로
    implementation("com.navercorp.nid:oauth-jdk8:5.9.0") // jdk 8

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}

kapt {
    correctErrorTypes = true
}