package com.mkobit

import org.gradle.testkit.runner.GradleRunner
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.File

class ExampleTestKitKotlinDslTest {

  private lateinit var gradleRunner: GradleRunner

  @Before
  fun setUp() {
    val projectDir = createTempDir().apply {
      deleteOnExit()
    }
    gradleRunner = GradleRunner.create()
        .withProjectDir(projectDir)
        .withPluginClasspath()
        .forwardOutput()
        .withArguments("--info")
  }

  @After
  fun tearDown() {
    gradleRunner.projectDir.deleteRecursively()
  }

  private fun printBuildFile() {
    val divider = "-=".repeat(20)
    gradleRunner.kotlinBuildFile.let { kotlinBuildFile ->
      if (kotlinBuildFile.exists()) {
        println("${kotlinBuildFile.name} contents:")
        println(divider)
        println(kotlinBuildFile.readText())
        println(divider)
      }
    }
    gradleRunner.groovyBuildFile.let { groovyBuildFile ->
      if (groovyBuildFile.exists()) {
        println("${groovyBuildFile.name} contents:")
        println(divider)
        println(groovyBuildFile.readText())
        println(divider)
      }
    }
    println()
  }

  private fun File.writePluginsBlock() = writeText("""
      plugins {
        id("com.mkobit.example-plugin")
      }
    """.trimIndent() + System.lineSeparator())

  private val GradleRunner.kotlinBuildFile: File
    get() = projectDir.resolve("build.gradle.kts")

  private val GradleRunner.groovyBuildFile: File
    get() = projectDir.resolve("build.gradle")

  @Test
  fun `(kotlin) can println out plugins`() {
    gradleRunner.kotlinBuildFile.run {
      writePluginsBlock()
      appendText("""
          for (plugin in plugins) {
              println(plugin)
          }
      """.trimIndent())
    }
    printBuildFile()

    gradleRunner.build()
  }

  @Test
  fun `(kotlin) can println out extensions`() {
    gradleRunner.kotlinBuildFile.run {
      writePluginsBlock()
      appendText("""
          println(extensions)
      """.trimIndent())
    }
    printBuildFile()

    gradleRunner.build()
  }

  @Test
  fun `(kotlin) can access extension by name`() {
    gradleRunner.kotlinBuildFile.run {
      writePluginsBlock()
      appendText("""
          println(exampleExtension)
      """.trimIndent())
    }
    printBuildFile()

    gradleRunner.build()
  }

  @Test
  fun `(kotlin) can access extension property`() {
    gradleRunner.kotlinBuildFile.run {
      writePluginsBlock()
      appendText("""
          println(exampleExtension.myValue)
      """.trimIndent())
    }

    gradleRunner.build()
  }

  @Test
  fun `(kotlin) can set extension property`() {
    gradleRunner.kotlinBuildFile.run {
      writePluginsBlock()
      appendText("""
          exampleExtension {
            myValue = "hello there!"
          }
          println(exampleExtension.myValue)
      """.trimIndent())
    }

    gradleRunner.build()
  }

  @Test
  fun `(groovy) can println out plugins`() {
    gradleRunner.groovyBuildFile.run {
      writePluginsBlock()
      appendText("""
          for (plugin in plugins) {
              println(plugin)
          }
      """.trimIndent())
    }
    printBuildFile()

    gradleRunner.build()
  }

  @Test
  fun `(groovy) can println out extensions`() {
    gradleRunner.groovyBuildFile.run {
      writePluginsBlock()
      appendText("""
          println(extensions)
      """.trimIndent())
    }
    printBuildFile()

    gradleRunner.build()
  }

  @Test
  fun `(groovy) can access extension by name`() {
    gradleRunner.groovyBuildFile.run {
      writePluginsBlock()
      appendText("""
          println(exampleExtension)
      """.trimIndent())
    }
    printBuildFile()

    gradleRunner.build()
  }

  @Test
  fun `(groovy) can access extension property`() {
    gradleRunner.groovyBuildFile.run {
      writePluginsBlock()
      appendText("""
          println(exampleExtension.myValue)
      """.trimIndent())
    }

    gradleRunner.build()
  }

  @Test
  fun `(groovy) can set extension property`() {
    gradleRunner.groovyBuildFile.run {
      writePluginsBlock()
      appendText("""
          exampleExtension {
            myValue = "hello there!"
          }
          println(exampleExtension.myValue)
      """.trimIndent())
    }

    gradleRunner.build()
  }
}
