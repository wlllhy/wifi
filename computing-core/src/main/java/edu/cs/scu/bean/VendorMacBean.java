package edu.cs.scu.bean;

import java.io.Serializable;

public class VendorMacBean implements Serializable{

    private static final long serialVersinUID = 351877796426921776L;
    //Mac地址前缀
    private String macPrefix;
    // 制造厂商
    private String vendorName;

    public static long getSerialVersinUID() {
        return serialVersinUID;
    }

    public String getMacPrefix() {
        return macPrefix;
    }

    public void setMacPrefix(String macPrefix) {
        this.macPrefix = macPrefix;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }
}
