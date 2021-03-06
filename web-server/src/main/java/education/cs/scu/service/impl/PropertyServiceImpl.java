package education.cs.scu.service.impl;

import education.cs.scu.entity.PropertyBean;
import education.cs.scu.mapper.PropertyMapper;
import education.cs.scu.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyMapper propertyMapper;
    @Override
    public int addProperty(PropertyBean propertyBean) throws Exception {
        return propertyMapper.addProperty(propertyBean);
    }

    @Override
    public int setProperty(PropertyBean propertyBean) throws Exception {
        return propertyMapper.setProperty(propertyBean);
    }

    @Override
    public PropertyBean queryProperty(PropertyBean propertyBean) throws Exception{
        return propertyMapper.queryProperty(propertyBean);
    }
}
