package com.example.firstproject.ui.custom

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.util.Log
import android.view.animation.LinearInterpolator
import androidx.annotation.ColorInt
import androidx.annotation.Dimension
import androidx.annotation.Px
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.animation.doOnRepeat
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.toRectF
import com.example.firstproject.R
import com.example.firstproject.extenshions.dpToPx
import kotlin.math.max
import kotlin.math.truncate

@SuppressLint("Recycle")
class AvatarImageView @JvmOverloads constructor(
    context:Context,
    attrs:AttributeSet? = null,
    defStyleAttr:Int = 0
): AppCompatImageView(context, attrs, defStyleAttr) {
    companion object{
        private const val DEFAULT_BORDER_WIDTH = 2
        private const val DEFAULT_BORDER_COLOR = 2
        private const val DEFAULT_SIZE = 40
        val bgColors = arrayOf(
            Color.parseColor("#7BC862"),
            Color.parseColor("#E17076"),
            Color.parseColor("#FAA774"),
            Color.parseColor("#6EC9CB"),
            Color.parseColor("#65AADD"),
            Color.parseColor("#A695E7"),
            Color.parseColor("#EE7AAE"),
            Color.parseColor("#2196F3")
        )
    }
    @Px
    var borderWidth:Float = context.dpToPx(DEFAULT_BORDER_WIDTH)
    @ColorInt
    private var borderColor = Color.BLACK
    private var initials:String = "??"

    private val maskPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val borderPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val avatarPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val initialsPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val viewReact = Rect()
    private val borderReact = Rect()
    private var isAvatarMode =true
    private var size =0
    init{
        if(attrs!=null){
            val ta = context.obtainStyledAttributes(attrs, R.styleable.AvatarImageView)
            borderWidth = ta.getDimension(
                R.styleable.AvatarImageView_aiv_borderWidth,
                context.dpToPx(DEFAULT_BORDER_WIDTH)
            )
            borderColor = ta.getColor(R.styleable.AvatarImageView_aiv_borderColor, DEFAULT_BORDER_COLOR)
            initials = ta.getString(R.styleable.AvatarImageView_aiv_initials)?:"??"
            ta.recycle()
        }
        scaleType = ScaleType.CENTER_CROP
        setup()
        setOnClickListener{ handleLongClick()}
    }
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        Log.e("AvatarImageViewMask","onMeasure")
        val initSize = resolveDefaultSize(widthMeasureSpec)
        setMeasuredDimension(max(initSize,size),max(initSize,size))
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
       if(drawable !=null && isAvatarMode){
           drawAvatar(canvas)
       }else{
           drawInitials(canvas)
       }
        val half = (borderWidth/2).toInt()
        borderReact.set(viewReact)
        borderReact.inset(half,half)
        canvas.drawOval(borderReact.toRectF(), borderPaint)
    }

    override fun onSaveInstanceState(): Parcelable? {
        Log.e("AvatarImageView","onSaveInstanceState:$id")
        val savedState = SavedState(super.onSaveInstanceState())
        savedState.isAvatarMode = isAvatarMode
        savedState.borderWidth = borderWidth
        savedState.borderColor = borderColor
        return savedState
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        Log.e("AvatarImageView","onRestoreInstanceState:$id")
        if(state is SavedState){
            super.onRestoreInstanceState(state)
            isAvatarMode =state.isAvatarMode
            borderWidth =state.borderWidth
            borderColor =state.borderColor
            with(borderPaint){
                color = borderColor
                strokeWidth = borderWidth
            }
        }else{
            super.onRestoreInstanceState(state)
        }
    }
    override fun setImageBitmap(bm: Bitmap?) {
        super.setImageBitmap(bm)
        prepareShader(width, height)
        Log.e("AvatarImageView", "setimageDrawable")
    }
    override fun setImageDrawable(drawable: Drawable?) {
        super.setImageDrawable(drawable)
        prepareShader(width, height)
        Log.e("AvatarImageView", "setImageDrawable")
    }
    override fun setImageResource(resId: Int) {
        super.setImageResource(resId)
        prepareShader(width, height)
        Log.e("AvatarImageView","setImageResource")
    }
    fun setInitials(initials:String){
        Log.e("AvatarImageView","setInitials:$initials")
    this.initials = initials
        if(!isAvatarMode){
            invalidate()
        }
    }
    fun setBorderColor(@ColorInt color:Int){
        Log.e("AvatarImageView","setBorderColor:$color")
        borderColor = color
        invalidate()
    }
    fun setBorderWidths(@Dimension width:Int){
        Log.e("AvatarImageView","setBorderWidth:$width")
        borderWidth = context.dpToPx(width)
        borderPaint.strokeWidth = borderWidth
        invalidate()
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
    private fun prepareShader(w: Int, h: Int) {
        //prepare buffer this
        if(w==0 || drawable == null) return
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
    private fun drawAvatar(canvas: Canvas){
canvas.drawOval(viewReact.toRectF(),avatarPaint)
    }
    private fun drawInitials(canvas: Canvas){
        initialsPaint.color = initialsToColor(initials)
canvas.drawOval(viewReact.toRectF(),initialsPaint,)
        with(initialsPaint){
            color = Color.WHITE
            textAlign = Paint.Align.CENTER
            textSize = height *0.33f

        }
        val offsetY = (initialsPaint.descent() + initialsPaint.ascent())/2
        canvas.drawText(initials,viewReact.exactCenterX(),viewReact.exactCenterY()-offsetY, initialsPaint)
    }
    private fun initialsToColor(letters:String):Int{
        val b = letters[0].toByte()
        val len = bgColors.size
        val d = b/len.toDouble()
        val index = ((d- truncate(d))*len).toInt()
        return bgColors[index]
    }
    private fun handleLongClick(): Boolean{
        val va = ValueAnimator.ofInt(width,width*2).apply {
            duration=600
            interpolator = LinearInterpolator()
            repeatMode = ValueAnimator.REVERSE
            repeatCount = 1
        }
        va.addUpdateListener {
            size = it.animatedValue as Int
            requestLayout()
        }
        va.doOnRepeat { toggleMode() }
        va.start()
        return true
    }
   private fun toggleMode(){
        isAvatarMode = !isAvatarMode
        invalidate()
    }
    private class SavedState:BaseSavedState, Parcelable{
        var isAvatarMode: Boolean = true
        var borderWidth: Float = 0f
        var borderColor: Int = 0

        constructor(superState:Parcelable?) :super(superState){}

        constructor(src:Parcel):super(src){
            // restore state from parcel
            isAvatarMode = src.readInt() == 1
            borderWidth = src.readFloat()
            borderColor = src.readInt()
        }

        override fun writeToParcel(dst: Parcel, flags: Int) {
            // write state to parcel
            super.writeToParcel(dst, flags)
            dst.writeInt(if(isAvatarMode) 1 else 0)
            dst.writeFloat(borderWidth)
            dst.writeInt(borderColor)
        }

        override fun describeContents(): Int  = 0
        companion object CREATOR :Parcelable.Creator<SavedState>{
            override fun createFromParcel(parcel: Parcel): SavedState = SavedState(parcel)

            override fun newArray(size: Int): Array<SavedState?> = arrayOfNulls(size)


        }
    }
}