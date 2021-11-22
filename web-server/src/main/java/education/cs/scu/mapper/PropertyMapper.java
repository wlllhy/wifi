package education.cs.scu.mapper;

import education.cs.scu.entity.PropertyBean;

public interface PropertyMapper {
    int setProperty(PropertyBean propertyBean) throws Exception;
    int addProperty(PropertyBean propertyBean) throws Exception;
    PropertyBean queryProperty(PropertyBean propertyBean) throws Exception;
}
