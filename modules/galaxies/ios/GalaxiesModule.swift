import ExpoModulesCore

public class GalaxiesModule: Module {

  public func definition() -> ModuleDefinition {
   
    Name("Galaxies")

    Events("gotData")

    Function("getDeviceInfo") { () -> [String: String] in
      return [
        "deviceModel": UIDevice.current.model,
        "appVersion": Bundle.main.infoDictionary?["CFBundleShortVersionString"] as? String ?? ""
      ]
    }

    // Function that makes an HTTP call to get dummy data
    Function("loadDummyUser") { () -> Void in
      let url = URL(string: "https://jsonplaceholder.typicode.com/users/2")!
      let task = URLSession.shared.dataTask(with: url) { data, response, error in
        guard let data = data else { return }
        do {
          let json = try JSONSerialization.jsonObject(with: data, options: [])
          print(json)
          self.sendEvent("gotData", [
                "data": json
          ])
        } catch {
          print(error)
        }
      }
      task.resume()
    }

    View(GalaxiesView.self) {
      Prop("greeting") { (view: GalaxiesView, prop: String?) in
          view.label.text = prop
      }
    }

    Function("getApiKey") { () -> String in
     return Bundle.main.object(forInfoDictionaryKey: "GALACTIC_API_KEY") as! String
    }

  }
}
