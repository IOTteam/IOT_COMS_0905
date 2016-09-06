package iot.dao.entity;

import iot.dao.entity.OrderDetail;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-09-06T11:07:57")
@StaticMetamodel(OrderMaster.class)
public class OrderMaster_ { 

    public static volatile SingularAttribute<OrderMaster, Integer> orderMasterId;
    public static volatile CollectionAttribute<OrderMaster, OrderDetail> orderDetailCollection;
    public static volatile SingularAttribute<OrderMaster, String> orderId;
    public static volatile SingularAttribute<OrderMaster, String> customerId;
    public static volatile SingularAttribute<OrderMaster, Date> orderDate;
    public static volatile SingularAttribute<OrderMaster, Boolean> status;

}