plugins {
    `java-library`
    alias(libs.plugins.shadow)
    `maven-publish`
    signing
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
}

java.toolchain.languageVersion.set(JavaLanguageVersion.of(11))

/*
publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifactId = "velocityhexlogger"
            repositories {
                maven {
                    credentials(PasswordCredentials::class)
                    val central = "https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/"
                    val snapshots = "https://s01.oss.sonatype.org/content/repositories/snapshots/"
                    if (project.version.toString().endsWith("SNAPSHOT")) {
                        name = "SonatypeSnapshots"
                        setUrl(snapshots)
                    } else {
                        name = "OSSRH"
                        setUrl(central)
                    }
                }
            }
            from(components["java"])
            pom {
                url.set("https://github.com/4drian3d/VelocityHexLogger")
                licenses {
                    license {
                        name.set("GNU General Public License version 3 or later")
                        url.set("https://opensource.org/licenses/GPL-3.0")
                    }
                }
                scm {
                    connection.set("scm:git:https://github.com/4drian3d/VelocityHexLogger.git")
                    developerConnection.set("scm:git:ssh://git@github.com/4drian3d/VelocityHexLogger.git")
                    url.set("https://github.com/4drian3d/VelocityHexLogger")
                }
                developers {
                    developer {
                        id.set("4drian3d")
                        name.set("Adrian Gonzales")
                        email.set("adriangonzalesval@gmail.com")
                    }
                }
                issueManagement {
                    name.set("GitHub")
                    url.set("https://github.com/4drian3d/VelocityHexLogger/issues")
                }
                ciManagement {
                    name.set("GitHub Actions")
                    url.set("https://github.com/4drian3d/VelocityHexLogger/actions")
                }
                name.set(project.name)
                description.set(project.description)
                url.set("https://github.com/4drian3d/VelocityHexLogger")
            }
        }
    }
}

signing {
    useGpgCmd()
    sign(configurations.archives.get())
    sign(publishing.publications["mavenJava"])
}
*/