package edu.cs.scu.dao.factory;

import edu.cs.scu.dao.*;
import edu.cs.scu.dao.impl.*;

public class DaoFactory {

    public static PropertyDao getPropertyDao() {
        return new PropertyDaoImpl();
    }

    public static ShopDao getShopDao() {
        return new ShopDaoImpl();
    }

    public static TaskDao getTaskDao() {
        return new TaskDaoImpl();
    }

    public static UserDaoImpl getUserDao() {
        return new UserDaoImpl();
    }

    public static UserVisitDaoImpl getUserVisitDao() {
        return new UserVisitDaoImpl();
    }

    public static UserVisitTimeDaoImpl getUserVisitTimeDao() {
        return new UserVisitTimeDaoImpl();
    }

    public static VendorMacDao getVendorMacDao() {
        return new VendorMacDaoImpl();
    }
}
