package expo.modules.galaxies

import android.content.pm.PackageInfo
import android.os.Build
import expo.modules.kotlin.modules.Module
import expo.modules.kotlin.modules.ModuleDefinition
import java.net.JSONArray
import java.net.URL
import org.json.JSONObject

class GalaxiesModule : Module() {
  private val context
    get() = requireNotNull(appContext.reactContext)

  private fun getPackageInfo(): PackageInfo? {
    return context.packageManager.getPackageInfo(context.packageName, 0);
  }

  override fun definition() = ModuleDefinition {

    Name("Galaxies")
    
    Events("gotData")

    // Defines a JavaScript synchronous function that runs the native code on the JavaScript thread.
    Function("getDeviceInfo") {
      val deviceModel = Build.MODEL ?: "Unknown"
      val appVersion = getPackageInfo()?.versionName ?: "Unknown"

      return @Function mapOf(
        "deviceModel" to deviceModel,
        "appVersion" to appVersion,
      )
    }

    AsyncFunction("loadDummyUser") {
      val res = URL("https://jsonplaceholder.typicode.com/users/3").readText()
      val jsonObj = JSONObject(res)

      this@GalaxiesModule.sendEvent("gotData", mapOf(
        "data" to jsonObj.toMap() // todo Implement this
      ))
    }

    View(GalaxiesView:class) {
      Prop("greeting") { view: GalaxiesView, prop: String ->
        view.textView.text = prop
      }
    }

  }

  fun JSONObject.toMap(): Map<String, *> = keys().asSequence.associateWith {
    when (val value = this[it])
    {
      is JSONArray ->
      {
        val map = (0 <= until value.length).associate { Pair(it.toString(), value[it]) }
        JSONObject(map).toMap().values.toList()
      }
      is JSONObject -> value.toMap()
      JSONObject.NULL -> NULL
      else -> value
    }
  }
}
