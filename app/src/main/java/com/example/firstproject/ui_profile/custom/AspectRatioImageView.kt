package com.example.firstproject.ui_profile.custom

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import com.example.firstproject.R


class AspectRatioImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
): androidx.appcompat.widget.AppCompatImageView(context, attrs, defStyleAttr)
{
    companion object{
    private const val DEFAULT_APSECT_RATIO=1.70f
            }
    private var aspetcRatio = DEFAULT_APSECT_RATIO
    init{
if(attrs!=null){
    val a = context.obtainStyledAttributes(attrs, R.styleable.AspectRatioImageView)
    aspetcRatio = a.getFloat(R.styleable.AspectRatioImageView_aspectRatio, DEFAULT_APSECT_RATIO)
    a.recycle()
}
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val newHeigth = (measuredHeight/aspetcRatio).toInt()
        setMeasuredDimension(measuredHeight, newHeigth)
    }

}