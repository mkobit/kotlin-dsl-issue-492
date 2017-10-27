package com.mkobit

import org.gradle.testkit.runner.GradleRunner
import org.junit.Before
import org.junit.Test
import java.io.File

class ExampleTestKitTest {

  private lateinit var gradleKts: File
  private lateinit var gradleRunner: GradleRunner

  @Before
  fun setUp() {
    val projectDir = createTempDir().apply {
      deleteOnExit()
    }
    gradleKts = projectDir.resolve("build.gradle.kts")
    gradleKts.writeText("""
      plugins {
        id("com.mkobit.example-plugin")
      }
    """.trimIndent())
    gradleRunner = GradleRunner.create()
        .withProjectDir(projectDir)
        .withPluginClasspath()
  }

  @Test
  fun `can println out extensions`() {
    gradleKts.appendText("""
      println(extensions)
    """.trimIndent())

    gradleRunner.build()
  }

  @Test
  fun `can access extension by name`() {
    gradleKts.appendText("""
      println(exampleExtension)
    """.trimIndent())

    gradleRunner.build()
  }

  @Test
  fun `can access extension property`() {
    gradleKts.appendText("""
      println(exampleExtension.myValue)
    """.trimIndent())

    gradleRunner.build()
  }

  @Test
  fun `can set extension property`() {
    gradleKts.appendText("""
      exampleExtension {
        myValue = "hello there!"
      }
      println(exampleExtension.myValue)
    """.trimIndent())

    gradleRunner.build()
  }
}
