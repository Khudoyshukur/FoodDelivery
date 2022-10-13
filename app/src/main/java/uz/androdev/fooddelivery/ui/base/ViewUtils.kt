package uz.androdev.fooddelivery.ui.base

import android.content.res.Resources
import android.util.TypedValue

/**
 * Created by: androdev
 * Date: 13-10-2022
 * Time: 9:42 PM
 * Email: Khudoyshukur.Juraev.001@mail.ru
 */

fun Resources.dpToPx(dpValue: Float): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dpValue,
        displayMetrics
    )
}