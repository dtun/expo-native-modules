package expo.modules.galaxies

import expo.modules.kotlin.modules.Module
import expo.modules.kotlin.modules.ModuleDefinition

class GalaxiesModule : Module() {

  override fun definition() = ModuleDefinition {

    Name("Galaxies")

    // Defines a JavaScript synchronous function that runs the native code on the JavaScript thread.
    Function("hello") {
      "Hello world! 👋"
    }

  }
}
