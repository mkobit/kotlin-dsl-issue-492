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

