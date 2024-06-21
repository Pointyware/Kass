import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    `maven-publish`
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
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

version = libs.versions.kass.get()

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["kotlin"])
            groupId = "org.pointyware.kass"
            artifactId = "assertions"
            version = libs.versions.kass.get()
        }
    }
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/Pointyware/Kass")
            credentials {
                username = project.findProperty("GITHUB_USER") as String?
                password = project.findProperty("GITHUB_TOKEN") as String?
            }
        }
    }
}
