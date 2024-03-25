import ExpoModulesCore

public class GalaxiesModule: Module {
  public func definition() -> ModuleDefinition {

    Name("Galaxies")

    // Defines a JavaScript synchronous function that runs the native code on the JavaScript thread.
    Function("hello") {
      return "Hello world! 👋"
    }

  }
}
