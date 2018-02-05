plugins {
  `java-gradle-plugin`
  // Case 3:
  kotlin("jvm") version "1.1.51"
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
  // Case 3:
  implementation(kotlin("stdlib"))
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
