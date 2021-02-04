package io.stark.localektx.utils

import android.app.Activity
import android.content.Intent
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat

/** Created by Jahongir on 04/02/2021.*/
object RecreateHelper {
    fun recreate(activity: Activity?) {
        activity?.let {
            val restartIntent = Intent(activity, activity::class.java)
            val extras = activity.intent.extras
            if (extras != null) {
                restartIntent.putExtras(extras)
            }
            ActivityCompat.startActivity(
                activity,
                restartIntent,
                ActivityOptionsCompat
                    .makeCustomAnimation(activity, android.R.anim.fade_in, android.R.anim.fade_out)
                    .toBundle()
            )
            activity.finish()
        }
    }
}