plugins {
}

tasks {
  "wrapper"(Wrapper::class) {
    gradleVersion = "4.6"
  }
}

subprojects {
  repositories {
    jcenter()
  }
}


tasks {
  withType<Test> {
    testLogging {
      showStackTraces = true
      setExceptionFormat("short")
    }
  }
}
