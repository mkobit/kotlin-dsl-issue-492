plugins {
  kotlin("jvm") version "1.2.30" apply false
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
