package iot.dao.entity;

import iot.dao.entity.CustomerPrice;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-09-06T11:07:57")
@StaticMetamodel(CustomerMaster.class)
public class CustomerMaster_ { 

    public static volatile SingularAttribute<CustomerMaster, String> customerPhone;
    public static volatile SingularAttribute<CustomerMaster, String> customerMail;
    public static volatile SingularAttribute<CustomerMaster, String> customerId;
    public static volatile SingularAttribute<CustomerMaster, Integer> customerMasterId;
    public static volatile SingularAttribute<CustomerMaster, String> customerName;
    public static volatile SingularAttribute<CustomerMaster, Boolean> status;
    public static volatile CollectionAttribute<CustomerMaster, CustomerPrice> customerPriceCollection;

}