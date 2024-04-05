package expo.modules.galaxies

import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import expo.modules.kotlin.modules.Module
import expo.modules.kotlin.modules.ModuleDefinition
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL

class GalaxiesModule : Module() {
  private val context
    get() = requireNotNull(appContext.reactContext)

  private fun getPackageInfo(): PackageInfo? {
    return context.packageManager.getPackageInfo(context.packageName, 0);
  }

  override fun definition() = ModuleDefinition {

    Name("Galaxies")

    Events("gotData")

    Function("getDeviceInfo") {
      val deviceModel = Build.MODEL ?: "Unknown"
      val appVersion = getPackageInfo()?.versionName ?: "Unknown"

      return@Function mapOf(
        "deviceModel" to deviceModel,
        "appVersion" to appVersion
      )
    }

    // Function that makes an HTTP call to get dummy data
    AsyncFunction("loadDummyUser") {
      val response = URL("https://jsonplaceholder.typicode.com/users/3").readText()
      val jsonObject = JSONObject(response)

      this@GalaxiesModule.sendEvent("gotData", mapOf(
        "data" to jsonObject.toMap()
      ))
    }

    View(GalaxiesView::class) {
      Prop("greeting") { view: GalaxiesView, prop: String ->
        view.textView.text = prop
      }
    }


    Function("getApiKey") {
      val applicationInfo = appContext?.reactContext?.packageManager?.getApplicationInfo(appContext?.reactContext?.packageName.toString(), PackageManager.GET_META_DATA)
      return@Function applicationInfo?.metaData?.getInt("GALACTIC_API_KEY")
    }
  }


  fun JSONObject.toMap(): Map<String, *> = keys().asSequence().associateWith {
    when (val value = this[it])
    {
      is JSONArray ->
      {
        val map = (0 until value.length()).associate { Pair(it.toString(), value[it]) }
        JSONObject(map).toMap().values.toList()
      }
      is JSONObject -> value.toMap()
      JSONObject.NULL -> null
      else            -> value
    }
  }
}
