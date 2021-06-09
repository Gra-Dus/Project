package com.example.firstproject.ui.custom

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.example.firstproject.R


class AspectRatioImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
): AppCompatImageView(context, attrs, defStyleAttr)
{
    companion object{
    private const val DEFAULT_ASPECT_RATIO=1.70f
            }
    private var aspectRatio = DEFAULT_ASPECT_RATIO
    init{
if(attrs!=null){
    val a = context.obtainStyledAttributes(attrs, R.styleable.AspectRatioImageView)
    aspectRatio = a.getFloat(R.styleable.AspectRatioImageView_aspectRatio, DEFAULT_ASPECT_RATIO)
    a.recycle()
}
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val newHeigth = (measuredHeight/ aspectRatio).toInt()
        setMeasuredDimension(measuredHeight, newHeigth)
    }

}