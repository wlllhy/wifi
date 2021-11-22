package education.cs.scu.service;

import education.cs.scu.entity.PropertyBean;


public interface PropertyService {
    int addProperty(PropertyBean propertyBean) throws Exception;
    int setProperty(PropertyBean propertyBean) throws Exception;
    PropertyBean queryProperty(PropertyBean propertyBean) throws Exception;
}
