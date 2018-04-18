plugins {
  `lifecycle-base`
  kotlin("jvm") version "1.2.30" apply false
}

tasks {
  "wrapper"(Wrapper::class) {
    gradleVersion = "4.7"
  }
}

val reportAll by tasks.creating(TestReport::class) {
  destinationDir = file("$buildDir/testReports")
}
tasks["build"].dependsOn(reportAll)

subprojects {
  pluginManager.apply("java-gradle-plugin")
  repositories {
    jcenter()
  }

  tasks.withType<Test> {
    reportAll.reportOn(this)
    ignoreFailures = true
    testLogging {
      showStackTraces = true
      setExceptionFormat("short")
    }
  }
}

