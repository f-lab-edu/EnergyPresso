import java.util.Properties

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.eins.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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

    buildFeatures {
        buildConfig=true
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

        buildConfigField("String", "DEVICE_PAIRING_AES_KEY_VALUE", "\"${localProperties["DEVICE_PAIRING_AES_KEY_VALUE"]}\"")
        buildConfigField("String", "DEVICE_PAIRING_AES_IV_VALUE", "\"${localProperties["DEVICE_PAIRING_AES_IV_VALUE"]}\"")

        testInstrumentationRunner = "com.eins.data.CustomTestRunner"
    }
}

dependencies {
    implementation(project(":domain"))

    //aws
    implementation("com.amazonaws:aws-android-sdk-iot:2.16.13")
    implementation("com.amazonaws:aws-android-sdk-cognito:2.16.13")
    implementation("com.amazonaws:aws-android-sdk-mobile-client:2.16.13")
    implementation("com.amazonaws:aws-android-sdk-s3:2.16.13")
    implementation("com.amazonaws:aws-android-sdk-auth-core:2.16.13")

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.retrofit2:converter-scalars:2.6.4")
    implementation("com.squareup.okhttp3:okhttp:4.9.3")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")

    //hilt
    implementation("com.google.dagger:hilt-android:2.48.1")
    kapt("com.google.dagger:hilt-android-compiler:2.48.1")

    // For Robolectric tests.
    testImplementation("com.google.dagger:hilt-android-testing:2.44")
    // ...with Kotlin.
    kaptTest("com.google.dagger:hilt-android-compiler:2.44")


    // For instrumented tests.
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.44")
    // ...with Kotlin.
    kaptAndroidTest("com.google.dagger:hilt-android-compiler:2.44")

    //ktx android
    implementation("androidx.activity:activity-ktx:1.8.2")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.fragment:fragment-ktx:1.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:1.7.3") //kotlin
    implementation("androidx.appcompat:appcompat:1.6.1")

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