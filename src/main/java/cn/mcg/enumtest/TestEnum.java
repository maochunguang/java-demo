package cn.mcg.enumtest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author maocg
 * 创建时间：2018/2/6
 * 描述：TODO
 */
public class TestEnum {
    public static void main(String[] args) {
        WxPayActivity activity = WxPayActivity.getActivity("KLHB");
        System.out.println(activity.getKey());
        System.out.println(activity.getApiKey());
        System.out.println(activity.getMerchantid());

        Map<String ,String> localMap = new HashMap<>();
        localMap.put("aaa","aaa");
        localMap.put("bbb","bbb");
        localMap.put("ccc","ccc");
        ThreadLocal local = new ThreadLocal();
        local.set(localMap);

        System.out.println(((Map)local.get()).get("bbb"));
    }
}
