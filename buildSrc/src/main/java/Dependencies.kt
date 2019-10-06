
object Config{
    val minSdkVersion = 21
    val targetSdkVersion = 29
    val compileSdkVersion = 29
    val buildToolsVersion = "29.0.0"
    val applicationId = "sourceset564.samples.mvvm.movie"
    val versionCode = 1
    val versionName = "1.0"
}

object SuffixBuildType{
    val debug = "-dev"
    val alpha = "-alpha"
    val beta = "-beta"
    val rc = "-rc"
}

object Versions{

    //Commons
    val gradle = "3.5.1"
    val kotlin = "1.3.50"
    val material = "1.0.0"

    //AndroidX
    val appcompat = "1.1.0"
    val constraint = "1.1.3"
    val cardview = "1.0.0"
    val recyclerview = "1.0.0"
    val ktx = "1.1.0"

    //jetpack
    val lifecycle = "2.1.0"

    //data
    val gson = "2.8.5"

    //Network
    val retrofit = "2.5.0"
    val okhttplogging = "3.12.1"

    //media
    val glide = "4.9.0"

    //reactiveX
    val rxjava = "2.2.12"
    val rxandroid = "2.1.1"
    val rxkotlin = "2.4.0"

    //testing
    val junit = "4.12"
    val runner = "1.2.0"
    val espresso = "3.2.0"
}

object AndroidX{
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraint}"
    val appCompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    val cardView = "androidx.cardview:cardview:${Versions.cardview}"
    val ktx = "androidx.core:core-ktx:${Versions.ktx}"
    val gson = "com.google.code.gson:gson:${Versions.gson}"
    val material = "com.google.android.material:material:${Versions.material}"
    val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
}

object Jetpack{
    val lifecycle = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"
}

object Retrofit{
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    val rxAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    val okHttpLogging = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttplogging}"
}

object ReactiveX{
    val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.rxjava}"
    val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxandroid}"
    val rxKotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxkotlin}"
}

object Media {
    val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
//    val compiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
}

object Testing {
    val jUnit = "junit:junit:${Versions.junit}"
    val testRunner = "androidx.test:runner:${Versions.runner}"
    val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"

    val instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
}

object GradleClassPath{
    val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    val gradle = "com.android.tools.build:gradle:${Versions.gradle}"
}