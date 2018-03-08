package com.mkobit

import org.gradle.api.Plugin
import org.gradle.api.Project

class ExamplePlugin : Plugin<Project> {
  override fun apply(project: Project) {
    project.extensions.create("exampleExtension", ExampleExtension::class.java, "hello")
  }
}

open class ExampleExtension(var myValue: String) {
}
