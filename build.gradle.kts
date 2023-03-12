plugins {
    `java-library`
    alias(libs.plugins.blossom)
    alias(libs.plugins.shadow)
}

repositories {
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly(libs.velocity)
    api(libs.adventure.logger)
    compileOnly(libs.terminalconsole)
}

tasks {
    compileJava {
        options.encoding = Charsets.UTF_8.name()
        options.release.set(11)
    }
}

java.toolchain.languageVersion.set(JavaLanguageVersion.of(11))
