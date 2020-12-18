package com.github.permissions

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import com.github.permissions.interfaces.AllowCallback
import com.github.permissions.interfaces.RefusedCallback

/**
 * simply encapsulate the dynamic permission application, the user only needs
 * to customize the permission group, and both allow and deny have their related
 * callback operations
 */
object Permissions {

    private var activity: Activity? = null
    private var permission: Array<String>? = null
    private var refusedCallback: RefusedCallback? = null
    private var allowCallback: AllowCallback? = null
    private var refusedCallbackNum = 0
    private var allowCallbackNum = 0

    /**
     * provide an example of the acitivity interface
     * @param activity an example of the acitivity
     */
    @JvmStatic
    fun with(activity: Activity): Permissions {
        this.activity = activity
        return this
    }

    /**
     * dynamic request permission group
     * @param permission permission group
     */
    @JvmStatic
    fun request(permission: Array<String>): Permissions {
        if (Build.VERSION.SDK_INT >= 23) {
            this.permission = permission
            ActivityCompat.requestPermissions(activity!!, permission, 1)
        }
        return this
    }

    //register a callback for listening
    @JvmStatic
    fun OnRefusedCall(refusedCallback: RefusedCallback): Permissions {
        this.refusedCallback = refusedCallback
        return this
    }

    //register a callback for listening
    @JvmStatic
    fun OnAllowCall(allowCallback: AllowCallback): Permissions {
        this.allowCallback = allowCallback
        return this
    }

    //provide foreach loop permission operation
    @JvmStatic
    fun build() {
        permission?.forEach {
            if (activity?.let { it1 ->
                    ContextCompat.checkSelfPermission(
                        it1,
                        it
                    )
                } != PackageManager.PERMISSION_GRANTED) {
                ++refusedCallbackNum
            } else {
                ++allowCallbackNum
            }
        }
        if (refusedCallbackNum != 0) refusedCallback?.refuseDoWork()
        if (allowCallbackNum != 0) allowCallback?.allOwDoWork()
    }
}