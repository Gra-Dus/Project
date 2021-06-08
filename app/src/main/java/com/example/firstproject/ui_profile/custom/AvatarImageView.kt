package com.example.firstproject.ui_profile.custom

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

class AvatarImageView @JvmOverloads constructor(
    context:Context,
    attrs:AttributeSet? = null,
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

    private val maskPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val borderPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val viewReact = Rect()
    private lateinit var resultBm: Bitmap
    private lateinit var maskBm: Bitmap
    private lateinit var srcBm: Bitmap
    init{
if(attrs!=null){
    val ta = context.obtainStyledAttributes(attrs, R.styleable.AvatarImageView)
        borderWidth = ta.getDimension(
            R.styleable.AvatarImageView_aiv_borderWidth,
            context.dpToPx(DEFAULT_BORDER_WIDTH)
        )
       borderColor = ta.getColor(R.styleable.AvatarImageView_aiv_borderColor, DEFAULT_BORDER_COLOR)
    initials = ta.getString(R.styleable.AvatarImageView_aiv_initials)?:"??"

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
        prepareBitmaps(w,h)
    }
    override fun onDraw(canvas: Canvas) {
     //   super.onDraw(canvas)
        Log.e("AvatarImageViewMask","onDraw: ")
       // canvas.drawBitmap(resultBm,viewReact,viewReact,null)
        canvas.drawOval(viewReact.toRectF(), borderPaint)
    }
    private fun setup() {
        with(maskPaint){
            color = Color.RED
            style = Paint.Style.FILL
        }
        with(borderPaint){
            color = borderColor
            style = Paint.Style.STROKE
            strokeWidth = borderWidth

        }
    }
private fun prepareBitmaps(w: Int, h: Int) {
    //prepare buffer this
    maskBm = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888)
    val maskCanvas = Canvas(maskBm)
    maskCanvas.drawOval(viewReact.toRectF(),maskPaint)
    resultBm =maskBm.copy(Bitmap.Config.ARGB_8888, true)
    maskPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
srcBm = drawable.toBitmap(w,h, Bitmap.Config.ARGB_8888)
    val resultCanvas = Canvas(resultBm)
    resultCanvas.drawBitmap(maskBm,viewReact,viewReact, null)
    resultCanvas.drawBitmap(srcBm,viewReact,viewReact, maskPaint)
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