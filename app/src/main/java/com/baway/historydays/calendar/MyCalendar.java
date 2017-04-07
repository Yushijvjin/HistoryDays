package com.baway.historydays.calendar;



import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

import static com.baway.historydays.calendar.APPConfig.CalendarState.TODAY;


public class MyCalendar extends View {

	/** ��Ļ��� */
	private int width;
	/** ��Ļ�߶� */
	private int height;
	/** �������� */
	private int[][] dateNum;
	/** ��������״̬���� */
	private APPConfig.CalendarState[][] calendarStates;
	/** �� */
	private int year;
	/** �� */
	private int month;
	/** �滭�� */
	private DrawCalendar drawCalendar;
	/** ��������� */
	private float dateNumWidth;
	/** ��¼������������С���� */
	private int touchSlop;
	/** �����X������ */
	private float touchX;
	/** �����Y������ */
	private float touchY;
	/** �������ڵ������ */
	private OnCalendarClickListener onCalendarClickListener;
	/** ���������С */
	private int fontSize = 25;
	/** ����������ɫ */
	private int currentMonthFontColor = Color.BLACK;
	/** �Ǳ���������ɫ */
	private int noCurrentMonthFontColor = Color.GRAY;
	/** ����������ɫ */
	private int todayFontColor = Color.WHITE;

	public MyCalendar(Context context) {
		super(context);
		initUI(context);
	}

	public MyCalendar(Context context, AttributeSet attrs) {
		super(context, attrs);
		initUI(context);
	}

	public MyCalendar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initUI(context);
	}

	/**
	 * ��������������ɫ
	 * 
	 * @param currentMonthFontColor
	 *            ����������ɫ
	 * @param noCurrentMonthFontColor
	 *            �Ǳ���������ɫ
	 * @param todayFontColor
	 *            ����������ɫ
	 */
	public void setCalendarFontColor(int currentMonthFontColor,
			int noCurrentMonthFontColor, int todayFontColor) {

		this.currentMonthFontColor = currentMonthFontColor;
		this.noCurrentMonthFontColor = noCurrentMonthFontColor;
		this.todayFontColor = todayFontColor;
	}

	/**
	 * ���������ؼ���ͼ
	 */
	public void updateCalendarView() {
		invalidate();
	}

	/**
	 * �������������С
	 * 
	 * @param fontSize
	 */
	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	/**
	 * ������������
	 * 
	 * @param onCalendarClickListener
	 */
	public void setOnCalendarClickListener(
			OnCalendarClickListener onCalendarClickListener) {
		this.onCalendarClickListener = onCalendarClickListener;
	}

	/**
	 * ��ʼ��UI
	 * 
	 * @param context
	 */
	private void initUI(Context context) {
		// ��ʼ������
		year = DateUtil.getYear();
		month = DateUtil.getMonth();

		calendarStates = new APPConfig.CalendarState[6][7];

		drawCalendar = new DrawCalendar(year, month);

		touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();

	}

	/**
	 * ��������ʱ�䲢ˢ��������ͼ
	 * 
	 * @param year
	 * @param month
	 */
	public void setYearMonth(int year, int month) {
		this.year = year;
		this.month = month;
		drawCalendar = new DrawCalendar(year, month);
		invalidate();
	}

	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			// ��¼���������
			touchX = event.getX();
			touchY = event.getY();
			break;
		case MotionEvent.ACTION_UP:
			float touchLastX = event.getX();
			float touchLastY = event.getY();
			if (Math.abs(touchLastX - touchX) < touchSlop
					&& Math.abs(touchLastY - touchY) < touchSlop) {// �ж��Ƿ�����������
				// ��������������������
				int dateNumX = (int) (touchLastX / dateNumWidth);
				int dateNumY = (int) (touchLastY / dateNumWidth);
				// ʹ�ûص�������Ӧ�����������
				onCalendarClickListener.onCalendaeClick(
						dateNum[dateNumY][dateNumX],
						calendarStates[dateNumY][dateNumX]);
			}
			break;
		default:
			break;
		}
		return true;
	}

	/**
	 * ����������
	 * 
	 * @author xiejinxiong
	 * 
	 */
	public interface OnCalendarClickListener {

		/**
		 * �������ڵ������
		 * 
		 * @param dateNum
		 *            ��������
		 * @param calendarState
		 *            ����״̬
		 */
		public void onCalendaeClick(int dateNum, APPConfig.CalendarState calendarState);

	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		// ��ÿؼ����
		width = getMeasuredWidth();
		// �������������
		dateNumWidth = width / 7.0f;
		// ���������߶�
		height = (int) (dateNumWidth * 6);
		// ���ÿؼ����
		setMeasuredDimension(width, height);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		drawCalendar.drawCalendarCanvas(canvas);

	}

	/**
	 * ��װ�滭���������Ļ滭��
	 * 
	 * @author xiejinxiong
	 * 
	 */
	class DrawCalendar {

		/** �滭���ڻ��� */
		private Paint mPaintText;
		/** �滭���յ���Բ �����Ļ��� */
		private Paint mPaintCircle;
		/** ����߶� */
		private float fontHeight;

		public DrawCalendar(int year, int month) {
			// ����·������Ų�����
			dateNum = DateUtil.getMonthNumFromDate(year, month);
			// ��ʼ���滭�ı��Ļ���
			mPaintText = new Paint();
			mPaintText.setTextSize(fontSize);
			mPaintText.setColor(noCurrentMonthFontColor);// ���û�ɫ
			mPaintText.setAntiAlias(true);// ���û��ʵľ��Ч����
			// �������߶�
			FontMetrics fm = mPaintText.getFontMetrics();
			fontHeight = (float) Math.ceil(fm.descent - fm.top)/2;

			// ��ʼ���滭ԲȦ�Ļ���
			mPaintCircle = new Paint();
			mPaintCircle.setColor(Color.argb(100, 112, 199, 244));// ������ɫ
			mPaintCircle.setAntiAlias(true);// ���û��ʵľ��Ч����
		}

		/**
		 * �滭����
		 * 
		 * @param canvas
		 */
		public void drawCalendarCanvas(Canvas canvas) {
			// canvas.drawCircle(width/2, width/2, width/2, mPaint);// ��Բ
			for (int i = 0; i < dateNum.length; i++) {
				for (int j = 0; j < dateNum[i].length; j++) {

					if (i == 0 && dateNum[i][j] > 20) {// �ϸ��µ�����
						drawCalendarCell(i, j, APPConfig.CalendarState.NO_CURRENT_MONTH,
								canvas);
					} else if ((i == 5 || i == 4) && dateNum[i][j] < 20) {// �¸��µ�����
						drawCalendarCell(i, j, APPConfig.CalendarState.NO_CURRENT_MONTH,
								canvas);
					} else {// ��������
						if (dateNum[i][j] == DateUtil.getCurrentMonthDay()) {// �Ƿ�Ϊ��������ں�
							if (year == DateUtil.getYear()
									&& month == DateUtil.getMonth()) {// �Ƿ�Ϊ�������
								drawCalendarCell(i, j, TODAY,
										canvas);
							}
							drawCalendarCell(i, j, APPConfig.CalendarState.CURRENT_MONTH,
									canvas);
						} else {
							drawCalendarCell(i, j, APPConfig.CalendarState.CURRENT_MONTH,
									canvas);
						}
					}
				}
			}
		}

		/**
		 * �滭�������
		 * 
		 * @param i
		 *            �����
		 * @param j
		 *            �����
		 * @param state
		 *            ״̬
		 * @param canvas
		 *            ����
		 */
		private void drawCalendarCell(int i, int j, APPConfig.CalendarState state,
				Canvas canvas) {
			switch (state) {
			case TODAY:// ����
				calendarStates[i][j] = TODAY;
				mPaintText.setColor(todayFontColor);
				canvas.drawCircle(dateNumWidth * j + dateNumWidth / 2,
						dateNumWidth * i + dateNumWidth / 2, dateNumWidth / 2,
						mPaintCircle);
				break;
			case CURRENT_MONTH:// ����
				calendarStates[i][j] = APPConfig.CalendarState.CURRENT_MONTH;
				mPaintText.setColor(currentMonthFontColor);
				break;
			case NO_CURRENT_MONTH:// �Ǳ���
				calendarStates[i][j] = APPConfig.CalendarState.NO_CURRENT_MONTH;
				mPaintText.setColor(noCurrentMonthFontColor);
				break;
			default:
				break;
			}
			// �滭����
			canvas.drawText(dateNum[i][j] + "", dateNumWidth * j + dateNumWidth
					/ 2 - mPaintText.measureText(dateNum[i][j] + "") / 2,
					dateNumWidth * i + dateNumWidth / 2 + fontHeight / 2.0f,
					mPaintText);
		}
	}

}
