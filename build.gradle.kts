plugins {
    `java-library`
    alias(libs.plugins.shadow)
    alias(libs.plugins.indra)
}

repositories {
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly(libs.velocity)
    api(libs.adventure.logger)
    compileOnly(libs.terminalconsole)
}

java {
    withSourcesJar()
    withJavadocJar()
}

tasks {
    build {
        dependsOn(shadowJar)
    }
    compileJava {
        options.encoding = Charsets.UTF_8.name()
        options.release.set(11)
    }
    javadoc {
        options.encoding = Charsets.UTF_8.name()
        (options as StandardJavadocDocletOptions).links(
            "https://jd.advntr.dev/api/${libs.versions.adventure.get()}/",
            "https://jd.advntr.dev/text-logger-slf4j/${libs.versions.adventure.get()}/",
            "https://www.slf4j.org/apidocs/",
            "https://docs.oracle.com/en/java/javase/11/docs/api/"
        )
    }
    shadowJar {
        dependencies {
            exclude(dependency("net.kyori:adventure-api:${libs.versions.adventure.get()}"))
            exclude(dependency("net.kyori:adventure-key:${libs.versions.adventure.get()}"))
            exclude(dependency("net.kyori:examination-api:1.3.0"))
            exclude(dependency("net.kyori:examination-string:1.3.0"))
            exclude(dependency("org.slf4j:slf4j-api:1.7.36"))
        }
    }
}

java.toolchain.languageVersion.set(JavaLanguageVersion.of(11))

indra {
    javaVersions {
        testWith().add(11)
    }
    github("4drian3d", "VelocityHexLogger") {
        ci(true)
    }
    publishReleasesTo("OSSRH", "https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
    publishSnapshotsTo("SonatypeSnapshots",  "https://s01.oss.sonatype.org/content/repositories/snapshots/")
    gpl3OrLaterLicense()
    configurePublications {
        artifactId = "velocityhexlogger"
        from(components["java"])

        pom {
            developers {
                developer {
                    id.set("4drian3d")
                    name.set("Adrian Gonzales")
                    email.set("adriangonzalesval@gmail.com")
                }
            }
        }
    }
}
