package cn.mcg.suanfa;

import java.util.Arrays;

import org.junit.Test;

public class Sort {
	public static void main(String[] args) {

	}

	public void quickSort() {

	}

	public void mergeSort() {

	}

	public void insertSort() {

	}

	public String int2Voice(int num) {
		num = 12345;
		if (num == 0)
			return "零";
		if (num == Integer.MAX_VALUE)
			return "二十一亿四千七百八十三万三千六百四十八";

		int[] number = int2Array(num);
		String numString = num + "";
		String[] a = new String[] { "十", "百", "千", "万", "十万", "百万", "千万", "亿", "十亿" };
		String[] numStr = new String[] { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
		String result = "";
		for (int i = 0; i < number.length; i++) {
			
		}
		if (num < 0)
			result = "负" + result;
		return result;
	}
	@Test
	public void testInt2Array(){
		int num = 12345;
		System.out.println(int2Array(num));
	}
	public int[] int2Array(int num) {
		String number = num + "";
		int[] array = new int[number.length()];
		for (int i = 0; i < number.length(); i++) {
			array[i] = Integer.parseInt(String.valueOf(number.charAt(i)));
			System.out.println(array[i]);
		}
		System.out.println(Arrays.toString(array));
		return array;
	}
}
