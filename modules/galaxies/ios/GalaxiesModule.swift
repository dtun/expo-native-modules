import ExpoModulesCore

public class GalaxiesModule: Module {
  public func definition() -> ModuleDefinition {

    Name("Galaxies")

    // Defines a JavaScript synchronous function that runs the native code on the JavaScript thread.
    Function("getDeviceInfo") { () -> [String: String] in
      return [
        "deviceModel": UIDevice.current.model,
        "appVersion": Bundle.main.infoDictionary?["CFBundleShortVersionString"] as? String ?? "",
      ]
    }

  }
}
