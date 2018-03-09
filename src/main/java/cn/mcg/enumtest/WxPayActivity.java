package cn.mcg.enumtest;

/**
 * @author maocg
 * 创建时间：2018/2/6
 * 描述：微信提现活动枚举类
 */
public enum WxPayActivity {
    BWYYJ("BWYYJ", "1218240001", "lkoihgty87hyuiolkjyr5467890okiuy"),
    KLHB("KLHB", "1403480302", "l09ioujhyt6785rfghjiuolkjuyhgtre"),
    CCKL("CCKL", "1272529401", "4md2a8l0zv143g6a4b1v9spoiu65jjqq"),;

    /**
     * 活动名称
     */
    private String key;
    /**
     * 活动使用的商户号
     */
    private String merchantid;
    /**
     * 证书名称
     */
    private String apiKey;

    WxPayActivity(String key, String merchantid, String apiKey) {
        this.key = key;
        this.merchantid = merchantid;
        this.apiKey = apiKey;
    }

    public String getKey() {
        return key;
    }

    public String getMerchantid() {
        return merchantid;
    }

    public String getApiKey() {
        return apiKey;
    }

    public static WxPayActivity getActivity(String key){
        for (WxPayActivity activity: WxPayActivity.values()){
            if(activity.getKey().equals(key)){
                return WxPayActivity.valueOf(key);
            }
        }
        return null;
    }
}
