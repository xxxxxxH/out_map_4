package net.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

class NearByUtils {
    companion object {
        private var i: NearByUtils? = null
            get() {
                field ?: run {
                    field = NearByUtils()

                }
                return field
            }

        @Synchronized
        fun get(): NearByUtils {
            return i!!
        }
    }

    fun createLinearLayout(context: Context, activity: Activity): LinearLayout {
        val view = LinearLayout(context)
        val p = LinearLayout.LayoutParams(
            ScreenUtils.getScreenSize(activity)[1],
            ScreenUtils.getScreenSize(activity)[0] / 5
        )
        view.orientation = LinearLayout.HORIZONTAL
        view.weightSum = 3.0f
        view.layoutParams = p
        view.gravity = Gravity.CENTER
        return view
    }

    fun createChildView(context: Context, activity: Activity, s: String, id: Int): TextView {
        val view = TextView(context)
        val p = ViewGroup.LayoutParams(
            ScreenUtils.getScreenSize(activity)[1] / 3,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        view.layoutParams = p
        view.text = s
        view.gravity = Gravity.CENTER
        view.isAllCaps = true
        view.setCompoundDrawables(null, setDrawableTop(context, id), null, null)
        view.setOnClickListener {
            click(context, s)
        }
        return view
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    fun setDrawableTop(context: Context, id: Int): Drawable {
        val d = context.resources.getDrawable(id)
        d.setBounds(0, 0, d.minimumWidth, d.minimumHeight)
        return d
    }

    private fun click(context: Context, s: String) {
        if (!PackageUtils.get().checkAppInstalled(context, "com.google.android.apps.maps")) {
            Toast.makeText(context, "not found google map", Toast.LENGTH_SHORT).show()
            return
        }
        PackageUtils.get()
            .startApp(context, Uri.parse("http://maps.google.com/maps?q=" + s + "&hl=en"))
    }
}