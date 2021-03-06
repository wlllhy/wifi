package education.cs.scu.entity;

import java.io.Serializable;

public class UserVisitTimeBean implements Serializable {
    private static final long serialVersinUID = 351877796426921776L;

    // 自增长ID
    private Integer autoId;
    // 商店ID
    private Integer shopId;
    // Mac地址
    private String mac;
    // 访问时间
    private String visitTime;

    public int getAutoId() {
        return autoId;
    }

    public void setAutoId(Integer autoId) {
        this.autoId = autoId;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(String visitTime) {
        this.visitTime = visitTime;
    }
}
