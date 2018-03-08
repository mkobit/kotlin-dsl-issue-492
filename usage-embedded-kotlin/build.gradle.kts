plugins {
  `java-gradle-plugin`
  // Case 2:
  `embedded-kotlin`
}

tasks {
  "wrapper"(Wrapper::class) {
    gradleVersion = "4.5.1"
  }
}

repositories {
  jcenter()
}

dependencies {
  testImplementation("junit:junit:4.12")
}

gradlePlugin {
  plugins.invoke {
    // Don't get the extensions for NamedDomainObjectContainer here because we only have a NamedDomainObjectContainer
    // See https://github.com/gradle/kotlin-dsl/issues/459
    "example" {
      id = "com.mkobit.example-plugin"
      implementationClass = "com.mkobit.ExamplePlugin"
    }
  }
}
