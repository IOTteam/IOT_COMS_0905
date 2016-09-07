/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iot.dao.repository;

import iot.dao.entity.CustomerMaster;
import iot.dao.entity.CustomerPrice;
import iot.dao.entity.CustomerPrice_;
import iot.dao.entity.ProductMaster;
import iot.dao.entity.User;
import iot.dao.repository.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

/**
 *
 * @author hatanococoro
 */
public class CustomerPriceDAO implements Serializable {

    public CustomerPriceDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CustomerPrice customerPrice) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(customerPrice);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CustomerPrice customerPrice) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            customerPrice = em.merge(customerPrice);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = customerPrice.getCustomerPriceId();
                if (findCustomerPrice(id) == null) {
                    throw new NonexistentEntityException("The customerPrice with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CustomerPrice customerPrice;
            try {
                customerPrice = em.getReference(CustomerPrice.class, id);
                customerPrice.getCustomerPriceId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The customerPrice with id " + id + " no longer exists.", enfe);
            }
            em.remove(customerPrice);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CustomerPrice> findCustomerPriceEntities() {
        return findCustomerPriceEntities(true, -1, -1);
    }

    public List<CustomerPrice> findCustomerPriceEntities(int maxResults, int firstResult) {
        return findCustomerPriceEntities(false, maxResults, firstResult);
    }

    private List<CustomerPrice> findCustomerPriceEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CustomerPrice.class));
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

    public CustomerPrice findCustomerPrice(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CustomerPrice.class, id);
        } finally {
            em.close();
        }
    }

    public CustomerPrice findCustomerPriceByCustProdRange(CustomerMaster customerMasterId, ProductMaster productMasterId, int orderQty) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT c FROM CustomerPrice c WHERE c.customerMasterId = :customerMasterId and c.productMasterId = :productMasterId AND c.ranges <:orderQty order by c.ranges DESC ");
            //查询结果以range为准降序排列
            query.setParameter("customerMasterId", customerMasterId);//赋值
            query.setParameter("productMasterId", productMasterId);//赋值
            query.setParameter("orderQty", orderQty);//赋值
            return (CustomerPrice) query.getResultList().get(0);//查询结果取第一个结果
        } finally {
            em.close();
        }
    }

    public List<CustomerPrice> findCustomerPriceAllCondition(CustomerMaster customerMasterName, ProductMaster productMasterName, String PriceMin, String PriceMax, String RangesMin, String RangesMax) {
        EntityManager em = getEntityManager();
        try {
            String sql = "SELECT cp FROM CustomerPrice cp WHERE 1=1 AND cp.status=:flag";
            CustomerMaster cm = new CustomerMaster();
            ProductMaster pm = new ProductMaster();

            if (true) {
                sql = sql + " AND cp.customerMasterId=:customerMasterId";
            }
            if (true) {
                sql = sql + " AND cp.productMasterId=:productMasterId";
            }
            if (PriceMin.length() > 0) {
                sql = sql + " AND cp.rangePrice>:PriceMin";
            }
            if (PriceMax.length() > 0) {
                sql = sql + " AND cp.rangePrice<:PriceMax";
            }
            if (RangesMin.length() > 0) {

                sql = sql + " AND cp.ranges>:RangesMin";
            }
            if (RangesMax.length() > 0) {
                sql = sql + " AND cp.ranges<:RangesMax";
            }
            Query query = em.createQuery(sql);
            query.setParameter("flag", true);
            if (true) {
                query.setParameter("customerMasterId", customerMasterName);
            }
            if (true) {
                query.setParameter("productMasterId", productMasterName);
            }
            if (PriceMin.length() > 0) {
                query.setParameter("PriceMin", Float.parseFloat(PriceMin));
            }
            if (PriceMax.length() > 0) {
                query.setParameter("PriceMax", Float.parseFloat(PriceMax));
            }
            if (RangesMin.length() > 0) {
                query.setParameter("RangesMin", Integer.parseInt(RangesMin));
            }
            if (RangesMax.length() > 0) {
                query.setParameter("RangesMax", Integer.parseInt(RangesMax));
            }
            return query.getResultList();
        } finally {
            em.close();
        }

    }

    //客户产品单价表条件查询
    public Map findCustomerPricesByCondition(List<CustomerMaster> CustomerMasterList, List<ProductMaster> ProductMasterList, 
                                                                String PriceMin, String PriceMax, String RangesMin, String RangesMax ,
                                                                int MaxResults, int FirstResult){
        EntityManager em = getEntityManager(); 
        try { 
        //创建安全查询工厂
        CriteriaBuilder cb = em.getCriteriaBuilder();
        //创建查询主语句
        CriteriaQuery<CustomerPrice> cq = cb.createQuery(CustomerPrice.class);
        //数量查询语句
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        //定义实体类型
        Root<CustomerPrice> customerPrice = cq.from(CustomerPrice.class);
        //创建计数主语句
        countQuery.select(cb.count(customerPrice));
        
        //构造过滤条件
        List<Predicate> predicatesList = new ArrayList<Predicate>();
        
        Predicate customer = cb.or();
        for(int m = 0;m < CustomerMasterList.size();m++){
            //循环加入查询条件customerMasterId
            customer = cb.or(customer,cb.equal(customerPrice.get(CustomerPrice_.customerMasterId), CustomerMasterList.get(m)));
        }
        predicatesList.add(customer);
        
        Predicate product = cb.or();
        for(int n = 0;n < ProductMasterList.size();n++){
            //循环加入查询条件productMasterId
            product = cb.or(product,cb.equal(customerPrice.get(CustomerPrice_.productMasterId), ProductMasterList.get(n)));
        }
        predicatesList.add(product);
        
        if(PriceMin.length() > 0){
            //添加查询条件，rangePrice > PriceMin
            predicatesList.add(cb.gt(customerPrice.get(CustomerPrice_.rangePrice), Float.parseFloat(PriceMin)));
        }
        if(PriceMax.length() > 0){
            //添加查询条件，rangePrice < PriceMax
            predicatesList.add(cb.lt(customerPrice.get(CustomerPrice_.rangePrice), Float.parseFloat(PriceMax)));
        }
        if(RangesMin.length() > 0){
            //添加查询条件，ranges > RangesMin
            predicatesList.add(cb.gt(customerPrice.get(CustomerPrice_.ranges), Integer.parseInt(RangesMin)));
        }
        if(RangesMax.length() > 0){
            //添加查询条件，ranges < RangesMax
            predicatesList.add(cb.lt(customerPrice.get(CustomerPrice_.ranges), Integer.parseInt(RangesMax)));
        }
        //添加查询条件，未被逻辑删除
        predicatesList.add(cb.equal(customerPrice.get(CustomerPrice_.status), true));
        
        //将查询条件加入查询主语句
        cq.where(predicatesList.toArray(new Predicate[predicatesList.size()]));
        //创建查询
        Query q = em.createQuery(cq);
        
        //查询分页
        q.setMaxResults(MaxResults);
        q.setFirstResult(FirstResult);
        //取得查询结果
        List<CustomerPrice> customerPricesList = q.getResultList();
        
        //查询数据总量
        countQuery.where(predicatesList.toArray(new Predicate[predicatesList.size()]));
        Query count = em.createQuery(countQuery);
        Long count_Long = (Long)count.getSingleResult();
        
        //使用Map保存数据
        Map map = new HashMap();
        map.put("customerPricesList", customerPricesList);
        map.put("count", count_Long);
        
        //返回结果
        return map;
            
        } catch (NoResultException e) {
            //无查询结果返回null
            return null;
        }

    }
    
    public int getCustomerPriceCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CustomerPrice> rt = cq.from(CustomerPrice.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    public List findByProductMasterId(ProductMaster productMasterId) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT c FROM CustomerPrice c WHERE c.productMasterId = :productMasterId");
            query.setParameter("productMasterId", productMasterId);
            return query.getResultList();
        } finally{
            em.close();
        }
    }

    public Long count(Integer customerPriceId) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT count(c) FROM CustomerPrice c WHERE c.customerPriceId = :customerPriceId");
            query.setParameter("customerPriceId", customerPriceId);
            return (Long)query.getSingleResult();
        } finally{
            em.close();
        }
    }

}
