package iot.dao.entity;

import iot.dao.entity.CustomerPrice;
import iot.dao.entity.OrderDetail;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-09-06T11:07:57")
@StaticMetamodel(ProductMaster.class)
public class ProductMaster_ { 

    public static volatile SingularAttribute<ProductMaster, String> productId;
    public static volatile CollectionAttribute<ProductMaster, OrderDetail> orderDetailCollection;
    public static volatile SingularAttribute<ProductMaster, Integer> productMasterId;
    public static volatile SingularAttribute<ProductMaster, String> productSpec;
    public static volatile SingularAttribute<ProductMaster, String> productName;
    public static volatile SingularAttribute<ProductMaster, Float> productPrice;
    public static volatile SingularAttribute<ProductMaster, String> status;
    public static volatile CollectionAttribute<ProductMaster, CustomerPrice> customerPriceCollection;

}