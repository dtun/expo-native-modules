package expo.modules.galaxies

import android.content.Context
import android.graphics.Color
import android.util.TypedValue
import android.view.Gravity
import android.widget.TextView
import expo.modules.kotlin.AppContext
import expo.modules.kotlin.views.ExpoView

class GalaxiesView(context: Context, appContext: AppContext) : ExpoView(context, appContext) {
  internal val textView = TextView(context).also {
    it.layoutParams = LayoutParams(
      android.view.ViewGroup.LayoutParams.MATCH_PARENT,
      android.view.ViewGroup.LayoutParams.MATCH_PARENT,
    )
    it.setBackgroundColor(Color.RED)
    it.gravity(Gravity.CENTER)
    it.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)
    it.setTextColor(Color.WHITE)

    addView(it)
  }
}
