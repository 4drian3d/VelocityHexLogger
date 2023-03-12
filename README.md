# VelocityHexLogger

Log Components into your Velocity console

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
    implementation("io.github.4drian3d:velocityhexlogger:1.0.0")
}

tasks {
    relocate("io.github._4drian3d.velocityhexlogger", "your.package.velocityhexlogger")
    relocate("net.kyori.adventure.text.logger.slf4j", "your.package.component.logger")
}
```

## Usage

```java

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
        hexLogger.info(MiniMessage.miniMessage().deserialize("<rainbow>Started"));
    }
}

```