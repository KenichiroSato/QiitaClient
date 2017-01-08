package capken.com.qiitaclient

import android.support.annotation.IdRes
import android.view.View

/**
 * Created by ken on 2017/01/07.
 */

fun <T: View> View.bindView(@IdRes id: Int): Lazy<T> = lazy {
    findViewById(id) as T
}