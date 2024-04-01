import ExpoModulesCore

public class GalaxiesModule: Module {
  public func definition() -> ModuleDefinition {

    Name("Galaxies")

    Events("gotData")

    // Defines a JavaScript synchronous function that runs the native code on the JavaScript thread.
    Function("getDeviceInfo") { () -> [String: String] in
      return [
        "deviceModel": UIDevice.current.model,
        "appVersion": Bundle.main.infoDictionary?["CFBundleShortVersionString"] as? String ?? "",
      ]
    }

    Function("loadDummyUser") { () -> Void in
      let url = URL(string: "https://jsonplaceholder.typicode.com/users/3")!
      print("Fetching user data from \(url)")
      let task = URLSession.shared.dataTask(with: url) { data, response, error in
        if let error = error {
          print("Error fetching user data: \(error)")
          return
        }
        guard let data = data else {
          print("No data received")
          return
        }
        do {
          let json = try JSONSerialization.jsonObject(with: data, options: []) as! [String: Any]
          print("User data: \(json)")
          self.sendEvent("gotData", [
            "data": json
          ])
        } catch {
          print("Error parsing user data: \(error)")
        }
      }
      task.resume()
    }

    View(GalaxiesView.self){
      Prop("greeting") {
        (view: GalaxiesView, prop: String?) in
        view.label.text = prop
      }
    }

  }
}
