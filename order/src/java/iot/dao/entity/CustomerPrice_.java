package iot.dao.entity;

import iot.dao.entity.CustomerMaster;
import iot.dao.entity.ProductMaster;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-09-06T11:07:57")
@StaticMetamodel(CustomerPrice.class)
public class CustomerPrice_ { 

    public static volatile SingularAttribute<CustomerPrice, Integer> customerPriceId;
    public static volatile SingularAttribute<CustomerPrice, Integer> ranges;
    public static volatile SingularAttribute<CustomerPrice, Float> rangePrice;
    public static volatile SingularAttribute<CustomerPrice, ProductMaster> productMasterId;
    public static volatile SingularAttribute<CustomerPrice, CustomerMaster> customerMasterId;
    public static volatile SingularAttribute<CustomerPrice, Boolean> status;

}