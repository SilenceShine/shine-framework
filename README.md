# shine-framework
shine-framework

### settings.xml

###  pom.xml

```xml
<repositories>
    <repository>
        <id>io.github.SilenceShine</id>
        <url>https://raw.githubusercontent.com/SilenceShine/maven-repository/release</url>
    </repository>
</repositories>
```

```xml
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>io.github.SilenceShine</groupId>
            <artifactId>shine-framework-spring</artifactId>
            <version>0.3.1-SNAPSHOT</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-webflux</artifactId>
    </dependency>

    <dependency>
        <groupId>io.github.SilenceShine</groupId>
        <artifactId>shine-framework-spring-core</artifactId>
    </dependency>

    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>${lombok.version}</version>
    </dependency>
</dependencies>
```

See https://github.com/SilenceShine/shine-framework-demo for demo
