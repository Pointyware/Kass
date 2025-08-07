import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    `maven-publish`
    alias(libs.plugins.publishMultiplatform)
}

kotlin {
    jvmToolchain(21)
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
                username = providers.gradleProperty("github.user").get()
                password = providers.gradleProperty("github.token").get()
            }
        }
    }
}

mavenPublishing {
    publishToMavenCentral()

    signAllPublications()

    coordinates("org.pointyware.kass", "assertions", libs.versions.kass.get())

    pom {
        name = "Kass"
        description = "Kotlin Assertions"
        inceptionYear = "2024"
        url = "http://github.com/Pointyware/Kass"
        licenses {
            license {
                name = "GNU General Public License, 3.0"
                url = "https://www.gnu.org/licenses/gpl-3.0.txt"
            }
        }
        developers {
            developer {
                id = "TSampley"
                name = "Taush Sampley"
                email = "tsampley@pointyware.org"
            }
        }
        scm {
            val repo = "github.com/Pointyware/Kass"
            connection = "scm:git:git://$repo.git"
            developerConnection = "scm:git:ssh://$repo.git"
            url = "http://$repo/"
        }
    }
}
