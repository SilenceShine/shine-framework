# shine-framework

shine-framework

### settings.xml

### pom.xml

```xml

<repositories>
    <!--  方式一 : 引入依赖github公开仓库的包  -->
    <repository>
        <id>SilenceShine maven-repository release</id>
        <url>https://raw.githubusercontent.com/SilenceShine/maven-repository/release</url>
    </repository>
    <repository>
        <id>SilenceShine maven-repository snapshot</id>
        <url>https://raw.githubusercontent.com/SilenceShine/maven-repository/snapshot</url>
    </repository>
    <!--  方式二 : 引入GitHub Packages 的包 但是必须再setting.xml中指定相应的token -->
    <repository>
        <id>GitHub Shine-Framework</id>
        <url>https://maven.pkg.github.com/SilenceShine/shine-framework</url>
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
