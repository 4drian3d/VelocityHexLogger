# VelocityHexLogger
![WorkFlow](https://img.shields.io/github/actions/workflow/status/4drian3d/VelocityHexLogger/gradle.yml?style=flat-square)
[![Discord](https://img.shields.io/discord/899740810956910683?color=7289da&label=Discord)](https://discord.gg/5NMMzK5mAn)
![](https://img.shields.io/maven-central/v/io.github.4drian3d/velocityhexlogger?style=flat-square)

Log Components into your Velocity console with Hex Support

## Installation

```kotlin
plugins {
    java
    id("com.github.johnrengelman.shadow:shadow") version "8.1.0"
}

repositories {
  mavenCentral()
}

dependencies {
    implementation("io.github.4drian3d:velocityhexlogger:1.0.1")
}

tasks {
    relocate("io.github._4drian3d.velocityhexlogger", "your.package.velocityhexlogger")
    relocate("net.kyori.adventure.text.logger.slf4j", "your.package.component.logger")
}
```

## Usage

```java
@Plugin(id = "my-plugin")
public class MyVelocityPlugin {
    private final ProxyServer proxyServer;
    private final HexLogger hexLogger;
    
    @Inject
    public MyVelocityPlugin(ProxyServer proxyServer, HexLogger hexLogger) {
        this.proxyServer = proxyServer;
        this.hexLogger = hexLogger;
    }
    
    @Subscribe
    public void onProxyInitialization(ProxyInitializationEvent event) {
        hexLogger.info(MiniMessage.miniMessage().deserialize("<rainbow>Plugin Started"));
    }
}
```