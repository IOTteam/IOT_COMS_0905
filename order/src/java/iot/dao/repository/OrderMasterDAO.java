/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iot.dao.repository;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import iot.dao.entity.OrderDetail;
import iot.dao.entity.OrderMaster;
import iot.dao.repository.exceptions.IllegalOrphanException;
import iot.dao.repository.exceptions.NonexistentEntityException;
import iot.dao.repository.exceptions.PreexistingEntityException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

/**
 *
 * @author hatanococoro
 */
public class OrderMasterDAO implements Serializable {

    public OrderMasterDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OrderMaster orderMaster) throws PreexistingEntityException, Exception {
        if (orderMaster.getOrderDetailCollection() == null) {
            orderMaster.setOrderDetailCollection(new ArrayList<OrderDetail>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<OrderDetail> attachedOrderDetailCollection = new ArrayList<OrderDetail>();
            for (OrderDetail orderDetailCollectionOrderDetailToAttach : orderMaster.getOrderDetailCollection()) {
                orderDetailCollectionOrderDetailToAttach = em.getReference(orderDetailCollectionOrderDetailToAttach.getClass(), orderDetailCollectionOrderDetailToAttach.getOrderDetailId());
                attachedOrderDetailCollection.add(orderDetailCollectionOrderDetailToAttach);
            }
            orderMaster.setOrderDetailCollection(attachedOrderDetailCollection);
            em.persist(orderMaster);
            for (OrderDetail orderDetailCollectionOrderDetail : orderMaster.getOrderDetailCollection()) {
                OrderMaster oldOrderMasterIdOfOrderDetailCollectionOrderDetail = orderDetailCollectionOrderDetail.getOrderMasterId();
                orderDetailCollectionOrderDetail.setOrderMasterId(orderMaster);
                orderDetailCollectionOrderDetail = em.merge(orderDetailCollectionOrderDetail);
                if (oldOrderMasterIdOfOrderDetailCollectionOrderDetail != null) {
                    oldOrderMasterIdOfOrderDetailCollectionOrderDetail.getOrderDetailCollection().remove(orderDetailCollectionOrderDetail);
                    oldOrderMasterIdOfOrderDetailCollectionOrderDetail = em.merge(oldOrderMasterIdOfOrderDetailCollectionOrderDetail);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findOrderMaster(orderMaster.getOrderMasterId()) != null) {
                throw new PreexistingEntityException("OrderMaster " + orderMaster + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OrderMaster orderMaster) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OrderMaster persistentOrderMaster = em.find(OrderMaster.class, orderMaster.getOrderMasterId());
            Collection<OrderDetail> orderDetailCollectionOld = persistentOrderMaster.getOrderDetailCollection();
            Collection<OrderDetail> orderDetailCollectionNew = orderMaster.getOrderDetailCollection();
            List<String> illegalOrphanMessages = null;
            for (OrderDetail orderDetailCollectionOldOrderDetail : orderDetailCollectionOld) {
                if (!orderDetailCollectionNew.contains(orderDetailCollectionOldOrderDetail)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain OrderDetail " + orderDetailCollectionOldOrderDetail + " since its orderMasterId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<OrderDetail> attachedOrderDetailCollectionNew = new ArrayList<OrderDetail>();
            for (OrderDetail orderDetailCollectionNewOrderDetailToAttach : orderDetailCollectionNew) {
                orderDetailCollectionNewOrderDetailToAttach = em.getReference(orderDetailCollectionNewOrderDetailToAttach.getClass(), orderDetailCollectionNewOrderDetailToAttach.getOrderDetailId());
                attachedOrderDetailCollectionNew.add(orderDetailCollectionNewOrderDetailToAttach);
            }
            orderDetailCollectionNew = attachedOrderDetailCollectionNew;
            orderMaster.setOrderDetailCollection(orderDetailCollectionNew);
            orderMaster = em.merge(orderMaster);
            for (OrderDetail orderDetailCollectionNewOrderDetail : orderDetailCollectionNew) {
                if (!orderDetailCollectionOld.contains(orderDetailCollectionNewOrderDetail)) {
                    OrderMaster oldOrderMasterIdOfOrderDetailCollectionNewOrderDetail = orderDetailCollectionNewOrderDetail.getOrderMasterId();
                    orderDetailCollectionNewOrderDetail.setOrderMasterId(orderMaster);
                    orderDetailCollectionNewOrderDetail = em.merge(orderDetailCollectionNewOrderDetail);
                    if (oldOrderMasterIdOfOrderDetailCollectionNewOrderDetail != null && !oldOrderMasterIdOfOrderDetailCollectionNewOrderDetail.equals(orderMaster)) {
                        oldOrderMasterIdOfOrderDetailCollectionNewOrderDetail.getOrderDetailCollection().remove(orderDetailCollectionNewOrderDetail);
                        oldOrderMasterIdOfOrderDetailCollectionNewOrderDetail = em.merge(oldOrderMasterIdOfOrderDetailCollectionNewOrderDetail);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = orderMaster.getOrderMasterId();
                if (findOrderMaster(id) == null) {
                    throw new NonexistentEntityException("The orderMaster with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OrderMaster orderMaster;
            try {
                orderMaster = em.getReference(OrderMaster.class, id);
                orderMaster.getOrderMasterId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The orderMaster with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<OrderDetail> orderDetailCollectionOrphanCheck = orderMaster.getOrderDetailCollection();
            for (OrderDetail orderDetailCollectionOrphanCheckOrderDetail : orderDetailCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This OrderMaster (" + orderMaster + ") cannot be destroyed since the OrderDetail " + orderDetailCollectionOrphanCheckOrderDetail + " in its orderDetailCollection field has a non-nullable orderMasterId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(orderMaster);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OrderMaster> findOrderMasterEntities() {
        return findOrderMasterEntities(true, -1, -1);
    }

    public List<OrderMaster> findOrderMasterEntities(int maxResults, int firstResult) {
        return findOrderMasterEntities(false, maxResults, firstResult);
    }

    private List<OrderMaster> findOrderMasterEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<OrderMaster> cq = cb.createQuery(OrderMaster.class);
            Metamodel metamodel = em.getMetamodel();
            EntityType<OrderMaster> orderMaster_ = metamodel.entity(OrderMaster.class);
            Root<OrderMaster> orderMaster = cq.from(OrderMaster.class);
            cq.where(cb.equal(orderMaster.get(orderMaster_.getSingularAttribute("status")), true));
            Query q = em.createQuery(cq);
            
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public OrderMaster findOrderMaster(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OrderMaster.class, id);
        } finally {
            em.close();
        }
    }
    
     public OrderMaster findOrderMasterByOrderId(String orderId) {
        EntityManager em = getEntityManager();
        try {
            Query queryById = em.createQuery("SELECT o FROM OrderMaster o WHERE o.orderId = :orderId");
            queryById.setParameter("orderId", orderId);
            return (OrderMaster)queryById.getSingleResult();
        } finally {
            em.close();
        }
    }
     
     //通过客户ID查询订单头档
     public List<OrderMaster> findOrderMasterByCustomerId(String customerId){
     
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT o FROM OrderMaster o WHERE o.customerId = :customerId and o.status = :status");
            query.setParameter("customerId", customerId);
            query.setParameter("status", true);
            return query.getResultList();
        }catch(NoResultException exception){
            return null;
        }finally {
            em.close();
        }
         
     }

    public int getOrderMasterCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OrderMaster> rt = cq.from(OrderMaster.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
        //通过ID，日期查询订单头档
    public List<OrderMaster> queryOrderMasterByDate(String firstOrderDate,String lastOrderDate,String orderId){
        EntityManager em = getEntityManager();
        try{
            Date first = new Date();
            Date last = new Date();
            String[] dateDivide = firstOrderDate.split("-");
            if(dateDivide.length ==3){
                int year = Integer.parseInt(dateDivide [0].trim());//去掉空格
                int month = Integer.parseInt(dateDivide [1].trim());  
                int day = Integer.parseInt(dateDivide [2].trim());  
                Calendar c = Calendar.getInstance();//获取一个日历实例  
                c.set(year, month-1, day);//设定日历的日期 
                first = c.getTime();
            }
            String[] dateDivide2 = lastOrderDate.split("-");
            if(dateDivide2.length ==3){
                int year = Integer.parseInt(dateDivide2 [0].trim());//去掉空格
                int month = Integer.parseInt(dateDivide2 [1].trim());  
                int day = Integer.parseInt(dateDivide2 [2].trim());  
                Calendar c = Calendar.getInstance();//获取一个日历实例  
                c.set(year, month-1, day);//设定日历的日期 
                last = c.getTime();
            }//以上步骤为裁剪日期，目的是将string类型的日期转换为date类型
           
            Query query ;
            String sql ="SELECT c FROM OrderMaster as c WHERE 1=1 ";
            if (firstOrderDate.length()>0) {//判断初始日期是否为空，不为空则添加查询语句
                sql = sql + "and c.orderDate  >:firstOrderDate ";
            }
            if (lastOrderDate.length()>0) {//判断终止日期是否为空，不为空则添加查询语句
                sql = sql + "and c.orderDate <:lastOrderDate";
            }
            if(orderId.length()>0){//判断orderID是否为空，不为空则添加查询语句
               sql=sql+" AND c.orderId LIKE :orderId";
               } 
            query = em.createQuery(sql);
            if (orderId.length()>0) {
                query.setParameter("orderId", orderId);
            }
            if (firstOrderDate.length()>0) {
                query.setParameter("firstOrderDate", first);
            }
            if (lastOrderDate.length()>0) {
                query.setParameter("lastOrderDate", last);
            }
            return  query.getResultList();
        }finally{
           em.close();
        }
    }
}
