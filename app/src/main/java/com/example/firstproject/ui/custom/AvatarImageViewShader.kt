package com.example.firstproject.ui.custom

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import androidx.annotation.ColorInt
import androidx.annotation.Px
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.toRectF
import com.example.firstproject.R
import com.example.firstproject.extenshions.dpToPx

class AvatarImageViewShader @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr:Int = 0
): AppCompatImageView(context, attrs, defStyleAttr) {
    companion object{
        private const val DEFAULT_BORDER_WIDTH = 2
        private const val DEFAULT_BORDER_COLOR = 2
        private const val DEFAULT_SIZE = 40

    }
    @Px
    var borderWidth:Float = context.dpToPx(DEFAULT_BORDER_WIDTH)
    @ColorInt
    private var borderColor = Color.BLACK
    private var initials:String = "??"

    private val avatarPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val borderPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val viewReact = Rect()
    init{
        if(attrs!=null){
            val ta = context.obtainStyledAttributes(attrs, R.styleable.AvatarImageViewShader)
            borderWidth = ta.getDimension(
                R.styleable.AvatarImageViewShader_aivs_borderWidth,
                context.dpToPx(DEFAULT_BORDER_WIDTH)
            )
            borderColor = ta.getColor(R.styleable.AvatarImageViewShader_aivs_borderColor, DEFAULT_BORDER_COLOR)
            initials = ta.getString(R.styleable.AvatarImageViewShader_aivs_initials)?:"??"
            ta.recycle()
        }
        scaleType = ScaleType.CENTER_CROP
        setup()
    }
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        Log.e("AvatarImageViewMask","onMeasure")
        val initSize = resolveDefaultSize(widthMeasureSpec)
        setMeasuredDimension(initSize,initSize)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        Log.e("AvatarImageViewMask","onSizeChanged: ")
        if(w==0) return
        with(viewReact){
            left=0
            top=0
            right=w
            bottom = h
        }
        prepareShader(w,h)
    }
    override fun onDraw(canvas: Canvas) {
        //   super.onDraw(canvas)
        Log.e("AvatarImageViewMask","onDraw: ")
        canvas.drawOval(viewReact.toRectF(),avatarPaint)
        val half = (borderWidth/2).toInt()
        viewReact.inset(half,half)
        canvas.drawOval(viewReact.toRectF(), borderPaint)
    }
    private fun setup() {
        with(borderPaint){
            color = Color.CYAN
            style = Paint.Style.STROKE
            strokeWidth = borderWidth

        }
    }
    private fun prepareShader(w: Int, h: Int) {
       val srcBm = drawable.toBitmap(w,h, Bitmap.Config.ARGB_8888)
       avatarPaint.shader = BitmapShader(srcBm,Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
    }

    private fun resolveDefaultSize(spec:Int):Int{
        return when(MeasureSpec.getMode(spec)){
            MeasureSpec.UNSPECIFIED->{context.dpToPx(DEFAULT_SIZE).toInt()}
            MeasureSpec.AT_MOST -> MeasureSpec.getSize(spec)
            MeasureSpec.EXACTLY -> MeasureSpec.getSize(spec)
            else -> MeasureSpec.getSize(spec)
        }
    }
}