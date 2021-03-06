package education.cs.scu.entity;

import java.io.Serializable;


public class UserVisitBean implements Serializable{

    private static final long serialVersionUID = 1647357927092562559L;

    private Integer shopId;
    private String mmac;
    private Long time;
    private Integer totalFlow;
    private Integer checkInFlow;
    private Double checkInRate;
    private Double shallowVisitRate;
    private Double deepVisitRate;


    public UserVisitBean() {

    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getMmac() {
        return mmac;
    }

    public void setMmac(String mmac) {
        this.mmac = mmac;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Integer getTotalFlow() {
        return totalFlow;
    }

    public void setTotalFlow(Integer totalFlow) {
        this.totalFlow = totalFlow;
    }

    public Integer getCheckInFlow() {
        return checkInFlow;
    }

    public void setCheckInFlow(Integer checkInFlow) {
        this.checkInFlow = checkInFlow;
    }

    public Double getCheckInRate() {
        return checkInRate;
    }

    public void setCheckInRate(Double checkInRate) {
        this.checkInRate = checkInRate;
    }

    public Double getShallowVisitRate() {
        return shallowVisitRate;
    }

    public void setShallowVisitRate(Double shallowVisitRate) {
        this.shallowVisitRate = shallowVisitRate;
    }

    public Double getDeepVisitRate() {
        return deepVisitRate;
    }

    public void setDeepVisitRate(Double deepVisitRate) {
        this.deepVisitRate = deepVisitRate;
    }

    @Override
    public String toString() {
        return "UserVisitBean{" +
                "shopId=" + shopId +
                ", mmac='" + mmac + '\'' +
                ", time=" + time +
                ", totalFlow=" + totalFlow +
                ", checkInFlow=" + checkInFlow +
                ", checkInRate=" + checkInRate +
                ", shallowVisitRate=" + shallowVisitRate +
                ", deepVisitRate=" + deepVisitRate +
                '}';
    }
}
