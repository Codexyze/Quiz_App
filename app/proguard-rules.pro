# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-keep class com.example.quizapp.data.Models.**{*;}

####################################
## 🔐 General Required Rules
####################################

# Keep Kotlin metadata (for reflection, serialization, etc.)
-keepclassmembers class ** {
    @kotlin.Metadata *;
}

-keepattributes *Annotation*

####################################
## 🧠 Jetpack Compose
####################################

# Keep Compose runtime & UI
-keep class androidx.compose.** { *; }
-keepclassmembers class androidx.compose.** { *; }

####################################
## 🧪 ViewModels (especially with Hilt)
####################################

# Keep ViewModels (especially annotated with @HiltViewModel)
-keep public class * extends androidx.lifecycle.ViewModel
-keep @dagger.hilt.android.lifecycle.HiltViewModel class * { *; }

# If you use SavedStateHandle or ViewModelProviders
-keepclassmembers class * extends androidx.lifecycle.ViewModel {
    <init>(...);
}

####################################
## 💉 Hilt / Dagger
####################################

-keep class dagger.hilt.** { *; }
-keep interface dagger.hilt.** { *; }
-keep class dagger.** { *; }
-keep interface dagger.** { *; }

# Keep modules and injectors
-keep @dagger.Module class * { *; }
-keep @dagger.Provides class * { *; }
-keep @javax.inject.Inject class * { *; }

####################################
## 🌐 Ktor
####################################

# Keep Ktor client, serialization plugins
-keep class io.ktor.** { *; }
-dontwarn io.ktor.**

# Keep kotlinx.serialization
-keep class kotlinx.serialization.** { *; }
-dontwarn kotlinx.serialization.**

####################################
## 📦 Models / Data Classes / Serializable
####################################

# Keep classes that are @Serializable
-keep @kotlinx.serialization.Serializable class * { *; }

# If using Gson or Moshi instead
-keep class com.example.quizapp.data.Models.** { *; }

# Keep enum values
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

####################################
## 🧼 Optional: Logging, errors
####################################

# Keep Timber / Log output if used
-dontwarn timber.log.**
-dontwarn android.util.Log

####################################
## 💣 Prevent class/method stripping (just in case)
####################################

# Don’t remove static initializers (important for reflection-based libs)
-keepclassmembers class * {
    static <clinit>();
}

# Don’t strip these classes
-keep class com.example.quizapp.** { *; }

####################################
## 🚫 Optional: Don’t obfuscate your packages (for debug or stack traces)
####################################

# Uncomment this if you want to keep original names (helpful in dev)
# -dontobfuscate
