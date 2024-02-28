plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "pl.romzes.wallpaperfinder"
    compileSdk = 34

    defaultConfig {
        applicationId = "pl.romzes.wallpaperfinder"
        minSdk = 25
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
    buildFeatures {
        viewBinding = true
    }
}

    val retrofitVersion = "2.9.0"
    val daggerVersion = "2.50"
    val dagger = "com.google.dagger:dagger:$daggerVersion"
    val daggerCompiler = "com.google.dagger:dagger-compiler:$daggerVersion"
    val daggerAndroid = "com.google.dagger:dagger-android:$daggerVersion"
    val daggerAndroidSupport = "com.google.dagger:dagger-android-support:$daggerVersion"
    val daggerAndroidCompiler = "com.google.dagger:dagger-android-processor:$daggerVersion"

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    //https://mvnrepository.com/artifact/androidx.lifecycle/lifecycle-viewmodel-ktx
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.fragment:fragment-ktx:1.6.2")
    // https://mvnrepository.com/artifact/com.github.bumptech.glide/glide
    implementation("com.github.bumptech.glide:glide:5.0.0-rc01")

    implementation("androidx.recyclerview:recyclerview:1.3.2")
    // For control over item selection of both touch and mouse driven selection
    implementation("androidx.recyclerview:recyclerview-selection:1.1.0")
    implementation ("com.github.bumptech.glide:recyclerview-integration:4.14.2")

    //retrofit
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")

    //splash
    implementation("androidx.core:core-splashscreen:1.0.1")
    //swipe to refresh
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    //dagger2
    implementation(dagger)
    kapt(daggerCompiler)
    implementation(daggerAndroid)
    implementation(daggerAndroidSupport)
    kapt(daggerAndroidCompiler)

    val retrofitVersion = "2.9.0"
    val daggerVersion = "2.50"
    val dagger = "com.google.dagger:dagger:$daggerVersion"
    val daggerCompiler = "com.google.dagger:dagger-compiler:$daggerVersion"
    val daggerAndroid = "com.google.dagger:dagger-android:$daggerVersion"
    val daggerAndroidSupport = "com.google.dagger:dagger-android-support:$daggerVersion"
    val daggerAndroidCompiler = "com.google.dagger:dagger-android-processor:$daggerVersion"

}