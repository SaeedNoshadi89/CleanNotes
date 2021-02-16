object Versions {
    const val gradle_version = "4.1.2"
    const val kotlin_version = "1.4.30"
    const val retrofit_version = "2.9.0"
    const val gson_version = "2.8.6"
    const val room_version = "2.2.6"
    const val glide_version = "4.12.0"
    const val nav_version = "2.2.2"
    const val core_version = "1.3.2"
    const val appcompat_version = "1.2.0"
    const val material_version = "1.2.0-alpha02"
    const val constraintlayout_version = "2.0.4"
    const val Junit_version = "4.+"
    const val extJunit_version = "1.1.2"
    const val espressoCore_version = "3.3.0"
    const val lifecycle_version = "2.3.0"
    const val coroutines_version = "1.3.9"
    const val hilt_version = "2.28-alpha"
    const val hiltAndroidXVersion = "1.0.0-alpha02"
}

object AppDeps {
    const val gradle = "com.android.tools.build:gradle:${Versions.gradle_version}"
    const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin_version}"
    const val navigationGradlePlugin =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.nav_version}"
    const val navigationFragmentKtx =
        "androidx.navigation:navigation-fragment-ktx:${Versions.nav_version}"
    const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.nav_version}"
    const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin_version}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.core_version}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat_version}"
    const val material = "com.google.android.material:material:${Versions.material_version}"
    const val constraintlayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout_version}"
    const val junit = "junit:junit:${Versions.Junit_version}"
    const val extJunit = "androidx.test.ext:junit:${Versions.extJunit_version}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore_version}"
    const val lifecycleViewModel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle_version}"
    const val lifecycleLiveData =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle_version}"
    const val lifecycleRuntime =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle_version}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit_version}"
    const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit_version}"
    const val gson = "com.google.code.gson:gson:${Versions.gson_version}"
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide_version}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide_version}"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines_version}"
    const val coroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines_version}"
    const val roomRuntime = "androidx.room:room-runtime:${Versions.room_version}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room_version}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room_version}"
    const val hiltGradlePlugin =
        "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt_version}"
    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt_version}"
    const val hiltAndroidCompiler =
        "com.google.dagger:hilt-android-compiler:${Versions.hilt_version}"
    const val hiltViewModel =
        "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hiltAndroidXVersion}"
    const val hiltCompiler = "androidx.hilt:hilt-compiler:${Versions.hiltAndroidXVersion}"
}

object CoreDeps {
    const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin_version}"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines_version}"
    const val coroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines_version}"

}