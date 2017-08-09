package cn.mcg.leecode;

import org.junit.Test;

/**
 * Author: mac
 * Date: 2017/7/27
 * Description: todo
 */
public class NumberTransfer {
    public final String[] chnNumChar = new String[]{"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
    public final String[] chnUnitSection = new String[]{"", "万", "亿", "万亿"};
    public final String[] chnUnitChar = new String[]{"", "十", "百", "千"};

    @Test
    public void testNumberToChinese() {
        int[] nums = new int[]{304, 4006, 4000, 10003, 10030, 21010011, 101101101};
        for (int i = 0; i < nums.length; i++) {
            System.out.println(numberToChinese(nums[i]));
        }
    }

    public String numberToChinese(int num) {
        String strIns;
        String chnStr = "";
        int unitPos = 0;
        boolean needZero = false;
        if (num == 0)
            return "零";
        while (num > 0) {
            strIns = "";
            int section = num % 10000;
            if (needZero) {
                chnStr = chnNumChar[0] + chnStr;
            }
            // 添加节权（万，亿）
            strIns += (section != 0) ? chnUnitSection[unitPos] : chnUnitSection[0];
            chnStr = strIns + chnStr;
            // 以万为单位，求万以内的权位
            chnStr = sectionToChinese(section, chnStr);
            needZero = (section < 1000) && (section > 0);
            num = num / 10000;
            unitPos++;
        }
        return chnStr;
    }

    private String sectionToChinese(int section, String chnStr) {
        String strIns;
        int unitPos = 0;
        boolean zero = true;
        while (section > 0) {
            int v = section % 10;
            if (v == 0) {
                if (section == 0 || !zero) {
                    zero = true;// zero确保不会出现多个零
                    chnStr = chnNumChar[v] + chnStr;
                }
            } else {
                zero = false;
                strIns = chnNumChar[v]; // 此位置对应等中文数字
                strIns += chnUnitChar[unitPos];// 此位置对应的权位
                chnStr = strIns + chnStr;
            }
            unitPos++;
            section = section / 10;
        }
        return chnStr;
    }
}
