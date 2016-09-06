package iot.dao.entity;

import iot.dao.entity.OrderMaster;
import iot.dao.entity.ProductMaster;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-09-06T11:07:57")
@StaticMetamodel(OrderDetail.class)
public class OrderDetail_ { 

    public static volatile SingularAttribute<OrderDetail, OrderMaster> orderMasterId;
    public static volatile SingularAttribute<OrderDetail, ProductMaster> productId;
    public static volatile SingularAttribute<OrderDetail, Integer> orderQty;
    public static volatile SingularAttribute<OrderDetail, Float> orderPrice;
    public static volatile SingularAttribute<OrderDetail, Integer> orderDetailId;
    public static volatile SingularAttribute<OrderDetail, Boolean> status;

}