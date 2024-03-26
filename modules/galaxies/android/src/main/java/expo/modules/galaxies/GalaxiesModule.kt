package expo.modules.galaxies

import android.content.pm.PackageInfo
import android.os.Build
import expo.modules.kotlin.modules.Module
import expo.modules.kotlin.modules.ModuleDefinition

class GalaxiesModule : Module() {
  private val context
    get() = requireNotNull(appContext.reactContext)

  private fun getPackageInfo(): PackageInfo? {
    return context.packageManager.getPackageInfo(context.packageName, 0);
  }

  override fun definition() = ModuleDefinition {

    Name("Galaxies")

    // Defines a JavaScript synchronous function that runs the native code on the JavaScript thread.
    Function("getDeviceInfo") {
      val deviceModel = Build.MODEL ?: "Unknown"
      val appVersion = getPackageInfo()?.versionName ?: "Unknown"

      return @Function mapOf(
        "deviceModel" to deviceModel,
        "appVersion" to appVersion,
      )
    }

  }
}
