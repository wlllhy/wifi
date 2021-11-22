package edu.cs.scu.dao;

import edu.cs.scu.bean.VendorMacBean;

import java.util.List;

public interface VendorMacDao {
    // 添加信息
    void addVendorMac(VendorMacBean vendorMacBean);

    void addVendorMacBatch(List<VendorMacBean> vendorMacBeanList);

    // 根据Mac前缀获取制造商
    VendorMacBean getVendorByMac(String mac);
}
