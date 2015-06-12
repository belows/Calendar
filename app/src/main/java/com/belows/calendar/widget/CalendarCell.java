package com.belows.calendar.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.belows.calendar.R;
import com.belows.calendar.util.CalendarUtil;
import com.belows.calendar.util.ChineseCalendar;

/**
 * Created by belows on 15/6/12.
 */
public class CalendarCell extends View {

    private ChineseCalendar mCalendar;
    private Paint mPaint;
    private boolean mHasEvent;
    private boolean mSelected;
    private String mHolidayOrWork;


    private float mCalendarTextSize;
    private int mCalendarTextColor;

    private float mLunarTextSize;
    private int mLunarTextColor;

    private float mHolidayTextSize;
    private int mHolidayTextColor;

    private float mHolidayBgColor;

    private int mEventMarkColor;
    private float mEventMarkRadius;
    private float mEventMarkBottomMargin;

    public CalendarCell(Context context) {
        this(context, null);
    }

    public CalendarCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        initVariables(context, attrs);
    }

    public void updateView(ChineseCalendar pCalendar, String pHolidayOrWork, boolean pHasEvent, boolean pSelected) {
        mCalendar = pCalendar;
        mHolidayOrWork = pHolidayOrWork;
        mHasEvent = pHasEvent;
        mSelected = pSelected;

        invalidate();
    }

    public void setCalendar(ChineseCalendar pCalendar) {
        if (mCalendar != null && CalendarUtil.isSameDay(mCalendar, pCalendar)) {
            return;
        }
        mCalendar = pCalendar;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int _centerX = getWidth() / 2;
        int _centerY = getHeight() / 2;
        int _radius = _centerX < _centerY ? _centerX : _centerY;

        mPaint.setTextAlign(Paint.Align.CENTER);

        if (mSelected) {
            mPaint.setColor(Color.GREEN);
            canvas.drawCircle(_centerX, _centerY, _radius, mPaint);
        } else if (CalendarUtil.isToday(mCalendar)) {
            mPaint.setStrokeWidth(5);
            mPaint.setColor(Color.RED);
            mPaint.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(_centerX, _centerY, _radius - 5, mPaint);
        }

        mPaint.setStyle(Paint.Style.FILL);
        if (mCalendar != null) {

            mPaint.setColor(mCalendarTextColor);
            mPaint.setTextSize(mCalendarTextSize);
            canvas.drawText(mCalendar.get(ChineseCalendar.DATE) + "", _centerX, mCalendarTextSize, mPaint);

            mPaint.setColor(mLunarTextColor);
            mPaint.setTextSize(mLunarTextSize);
            canvas.drawText(mCalendar.getChinese(ChineseCalendar.CHINESE_DATE) + "", _centerX, mCalendarTextSize + mLunarTextSize, mPaint);

            if (mHasEvent) {
                mPaint.setColor(mEventMarkColor);
                canvas.drawCircle(_centerX, _centerY * 2 - mEventMarkBottomMargin, mEventMarkRadius, mPaint);
            }
        }
    }

    private void initVariables(Context pContext, AttributeSet pAttrs) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);

        TypedArray _typedArray = pContext.obtainStyledAttributes(pAttrs, R.styleable.CalendarCell);
        mCalendarTextSize = _typedArray.getDimension(R.styleable.CalendarCell_calendar_text_size, 30);
        mCalendarTextColor = _typedArray.getColor(R.styleable.CalendarCell_calendar_text_color, Color.BLACK);

        mLunarTextSize = _typedArray.getDimension(R.styleable.CalendarCell_lunar_text_size, 30);
        mLunarTextColor = _typedArray.getColor(R.styleable.CalendarCell_lunar_text_color, Color.BLACK);

        mHolidayTextSize = _typedArray.getDimension(R.styleable.CalendarCell_holiday_text_size, 15);
        mHolidayTextColor = _typedArray.getColor(R.styleable.CalendarCell_holiday_text_color, Color.WHITE);

        mHolidayBgColor = _typedArray.getColor(R.styleable.CalendarCell_holiday_bg_color, Color.GREEN);

        mEventMarkColor = _typedArray.getColor(R.styleable.CalendarCell_event_mark_color,Color.GREEN);
        mEventMarkRadius = _typedArray.getDimension(R.styleable.CalendarCell_event_mark_radius,15);
        mEventMarkBottomMargin = _typedArray.getDimension(R.styleable.CalendarCell_event_mark_margin_bottom,15);
        _typedArray.recycle();
    }
}
