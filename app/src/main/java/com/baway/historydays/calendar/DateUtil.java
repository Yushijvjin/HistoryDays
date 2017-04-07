package com.baway.historydays.calendar;

import java.util.Calendar;


public class DateUtil {

	/**
	 * ��ȡ��ǰ���
	 *
	 * @return
	 */
	public static int getYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}

	/**
	 * ��ȡ��ǰ�·�
	 * 
	 * @return
	 */
	public static int getMonth() {
		return Calendar.getInstance().get(Calendar.MONTH) + 1;// +1����Ϊ��������ֵ�����Ǵ����·ݣ����Ƕ�Ӧ��Calendar.MAY������ֵ��
		// Calendar���·��ϵĳ���ֵ��Calendar.JANUARY��ʼ��0����Calendar.DECEMBER��11
	}

	/**
	 * ��ȡ��ǰ��ʱ��Ϊ���µĵڼ���
	 * 
	 * @return
	 */
	public static int getCurrentMonthDay() {
		return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * ��ȡ��ǰ��ʱ��Ϊ���ܵĵڼ���
	 * 
	 * @return
	 */
	public static int getWeekDay() {
		return Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * ��ȡ��ǰʱ��Ϊ����Ķ��ٵ�
	 * 
	 * @return
	 */
	public static int getHour() {
		return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		// Calendar calendar = Calendar.getInstance();
		// System.out.println(calendar.get(Calendar.HOUR_OF_DAY)); // 24Сʱ��
		// System.out.println(calendar.get(Calendar.HOUR)); // 12Сʱ��
	}

	/**
	 * ��ȡ��ǰ�ķ���ʱ��
	 * 
	 * @return
	 */
	public static int getMinute() {
		return Calendar.getInstance().get(Calendar.MINUTE);
	}

	/**
	 * ͨ�������ݺ��·�ȷ�����µ����ڷֲ�
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static int[][] getMonthNumFromDate(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, 1);// -1����Ϊ����ֵ�����Ǵ����·ݣ����Ƕ�Ӧ��Calendar.MAY������ֵ��

		int days[][] = new int[6][7];// �洢���µ����ڷֲ�

		int firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);// ��ø��µĵ�һ��λ���ܼ�����Ҫע����ǣ�һ�ܵĵ�һ��Ϊ���գ�ֵΪ1��

		int monthDaysNum = getMonthDaysNum(year, month);// ��ø��µ�����
		// ����ϸ��µ�����
		int lastMonthDaysNum = getLastMonthDaysNum(year, month);

		// ��䱾�µ�����
		int dayNum = 1;
		int lastDayNum = 1;
		for (int i = 0; i < days.length; i++) {
			for (int j = 0; j < days[i].length; j++) {
				if (i == 0 && j < firstDayOfWeek - 1) {// ����ϸ��µ�ʣ�ಿ��
					days[i][j] = lastMonthDaysNum - firstDayOfWeek + 2 + j;
				} else if (dayNum <= monthDaysNum) {// ��䱾��
					days[i][j] = dayNum++;
				} else {// ����¸��µ�δ������
					days[i][j] = lastDayNum++;
				}
			}
		}

		return days;

	}

	/**
	 * ���������Լ��·�������ϸ��µ�����
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static int getLastMonthDaysNum(int year, int month) {

		int lastMonthDaysNum = 0;

		if (month == 1) {
			lastMonthDaysNum = getMonthDaysNum(year - 1, 12);
		} else {
			lastMonthDaysNum = getMonthDaysNum(year, month - 1);
		}
		return lastMonthDaysNum;
	}

	/**
	 * ���������Լ��·�����ø��µ�����
	 * 
	 * @param year
	 * @param month
	 * @return ������Ϊ��һ����˵����������������������Ϲ��
	 */
	public static int getMonthDaysNum(int year, int month) {

		if (year < 0 || month <= 0 || month > 12) {// ����������·ݽ��м��ж�
			return -1;
		}

		int[] array = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };// һ���У�ÿ���·ݵ�����

		if (month != 2) {
			return array[month - 1];
		} else {
			if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {// �����ж�
				return 29;
			} else {
				return 28;
			}
		}
	}
}
