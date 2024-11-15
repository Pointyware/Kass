import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    `maven-publish`
}

kotlin {
    jvmToolchain(17)
    jvm {

    }
    androidTarget {
        publishLibraryVariants("release", "debug")
    }
    js {
        browser {
            binaries.executable()
        }
    }
    val framework = XCFramework()
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach {
        it.binaries.framework {
            baseName = "assertions"
            isStatic = true
            framework.add(this)
        }
    }

    applyDefaultHierarchyTemplate()
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
        val commonTest by getting {
            dependencies {

            }
        }

        val jvmSharedMain by creating {
            dependsOn(commonMain)
        }
        val jvmSharedTest by creating {
            dependsOn(commonTest)
        }

        val jvmMain by getting {
            dependsOn(jvmSharedMain)
        }
        val jvmTest by getting {
            dependsOn(jvmSharedTest)
            dependencies {
                implementation(libs.truth)
                implementation(libs.mockk)
            }
        }

        val androidMain by getting {
            dependsOn(jvmSharedMain)
        }
        val androidUnitTest by getting {
            dependsOn(jvmSharedTest)
        }
    }
}

android {
    namespace = "org.pointyware.kass.assertions"
    compileSdk = 34
    defaultConfig {
        minSdk = 21
    }
}

group = "org.pointyware.kass"
version = libs.versions.kass.get()

publishing {
    publications {
    }
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/Pointyware/Kass")
            credentials {
                username = providers.gradleProperty("BUILD_USER.user").get()
                password = providers.gradleProperty("BUILD_TOKEN").get()
            }
        }
    }
}
