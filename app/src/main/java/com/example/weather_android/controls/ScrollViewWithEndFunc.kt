package com.example.weather_android.controls

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.ScrollView
import kotlin.math.abs
enum class DirectionScroll{
    UP, Down, NON
}

class ScrollViewWithEndFunc (
    context: Context?,
    attrs: AttributeSet?,
    defStyle: Int
) : ScrollView(context, attrs, defStyle) {
    constructor(context: Context?) : this(context, null, 0) {}
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0) {}

    interface OnScrollListener {
        fun onScrollChanged(scrollView: ScrollViewWithEndFunc?, x: Int, y: Int, oldX: Int, oldY: Int)
        fun onEndScroll(scrollView: ScrollViewWithEndFunc?)

    }

    private var direction: DirectionScroll = DirectionScroll.NON
    private var isScrolling = false
    private var isTouching = false
    private var scrollingRunnable: Runnable? = null
    private var onScrollListener: OnScrollListener? = null

    fun setOnScrollListener(onScrollListener: OnScrollListener) {
        this.onScrollListener = onScrollListener
    }

    fun removeOnScrollListener() {
        this.onScrollListener = null
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(ev: MotionEvent): Boolean {
        val action = ev.action
        if (action == MotionEvent.ACTION_MOVE) {
            isTouching = true; isScrolling = true
        } else if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL) {
            if (isTouching && !isScrolling) {
                onScrollListener?.onEndScroll(this)
            }
            isTouching = false
        }

        if (action == MotionEvent.ACTION_DOWN) {
            direction = DirectionScroll.Down
        }
        if (action == MotionEvent.ACTION_UP) {
            direction = DirectionScroll.UP
        }
        return super.onTouchEvent(ev)
    }

    override fun onScrollChanged(x: Int, y: Int, oldX: Int, oldY: Int) {
        super.onScrollChanged(x, y, oldX, oldY)
        if (abs(oldY - y) > 0) {
            scrollingRunnable?.let { removeCallbacks(it) }
            scrollingRunnable = Runnable {
                if (isScrolling && !isTouching) {
                    onScrollListener?.onEndScroll(this@ScrollViewWithEndFunc)
                }
                isScrolling = false
                scrollingRunnable = null
            }
            postDelayed(scrollingRunnable, 200)
        }
        onScrollListener?.onScrollChanged(this, x, y, oldX, oldY)
    }

    public fun getDirection(): DirectionScroll {
        return direction
    }
}