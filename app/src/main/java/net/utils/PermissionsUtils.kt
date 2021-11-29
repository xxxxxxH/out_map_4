package net.utils

import androidx.appcompat.app.AppCompatActivity
import com.example.weeboos.permissionlib.PermissionRequest
import net.event.MessageEvent
import org.greenrobot.eventbus.EventBus
import java.util.*

class PermissionsUtils {
    companion object {
        private var i: PermissionsUtils? = null
            get() {
                field ?: run {
                    field = PermissionsUtils()
                }
                return field
            }

        @Synchronized
        fun get(): PermissionsUtils {
            return i!!
        }
    }

    fun requestPermissions(activity: AppCompatActivity) {
        PermissionRequest.getInstance().build(activity).requestPermission(
            object : PermissionRequest.PermissionListener {
                override fun permissionGranted() {
                   EventBus.getDefault().post(MessageEvent("permissionGranted"))
                }

                override fun permissionDenied(permissions: ArrayList<String>?) {
                    EventBus.getDefault().post(MessageEvent("permissionDenied"))
                }

                override fun permissionNeverAsk(permissions: ArrayList<String>?) {
                    EventBus.getDefault().post(MessageEvent("permissionDenied"))
                }

            }, Constant.permissions
        )
    }
}