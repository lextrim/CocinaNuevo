
plugins {
    id("com.android.application") version "8.5.2" apply false
    kotlin("android") version "1.9.25" apply false
    kotlin("kapt") version "1.9.25" apply false
    kotlin("plugin.serialization") version "1.9.25" apply false
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}
