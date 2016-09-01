/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iot.dao.repository;

import iot.dao.entity.CustomerMaster;
import iot.dao.repository.exceptions.NonexistentEntityException;
import iot.dao.repository.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author hatanococoro
 */
public class CustomerMasterDAO implements Serializable {

    public CustomerMasterDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CustomerMaster customerMaster) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(customerMaster);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCustomerMaster(customerMaster.getCustomerMasterId()) != null) {
                throw new PreexistingEntityException("CustomerMaster " + customerMaster + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CustomerMaster customerMaster) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            customerMaster = em.merge(customerMaster);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = customerMaster.getCustomerMasterId();
                if (findCustomerMaster(id) == null) {
                    throw new NonexistentEntityException("The customerMaster with id " + id + " no longer exists.");
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
            CustomerMaster customerMaster;
            try {
                customerMaster = em.getReference(CustomerMaster.class, id);
                customerMaster.getCustomerMasterId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The customerMaster with id " + id + " no longer exists.", enfe);
            }
            em.remove(customerMaster);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CustomerMaster> findCustomerMasterEntities() {
        return findCustomerMasterEntities(true, -1, -1);
    }

    public List<CustomerMaster> findCustomerMasterEntities(int maxResults, int firstResult) {
        return findCustomerMasterEntities(false, maxResults, firstResult);
    }

    private List<CustomerMaster> findCustomerMasterEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CustomerMaster.class));
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

    public CustomerMaster findCustomerMaster(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CustomerMaster.class, id);
        } finally {
            em.close();
        }
    }

    public int getCustomerMasterCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CustomerMaster> rt = cq.from(CustomerMaster.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<CustomerMaster> queryCustomerMasterByIdName(String customerName, String customerId) {
        //此函数实现按name、ID、name+ID3种方式查询customer，实现逻辑为当name为空时使用ID查询，ID为空时使用Name查询，若都不为空则利用2个参数一起查询
        EntityManager em = getEntityManager();//创建实体管理
        try {

            String sql = "SELECT c FROM CustomerMaster c WHERE 1=1 and c.status = :flag";

            if (customerId.length() > 0) {

                sql = sql + " and c.customerId = :customerId ";
            }

            if (customerName.length() > 0) {

                sql = sql + " and c.customerName LIKE :customerName ";
            }

            Query query = em.createQuery(sql);

            if (customerId.length() > 0) {

                query.setParameter("customerId", customerId);
            }

            if (customerName.length() > 0) {

                query.setParameter("customerName", "%" + customerName + "%");
            }
            query.setParameter("flag", true);
            return query.getResultList();

        } finally {
            em.close();//关闭实体管理
        }
    }

    public CustomerMaster findCustomerMasterById(String customerId) {

        EntityManager em = getEntityManager();//创建实体管理
        try {

            Query query = em.createQuery("SELECT c FROM CustomerMaster c WHERE c.customerId = :customerId and c.status = :flag");
            query.setParameter("customerId", customerId);
            query.setParameter("flag", true);
            return (CustomerMaster) query.getSingleResult();

        } finally {
            em.close();//关闭实体管理
        }
    }

    public List<CustomerMaster> findAllCustomerMaster() {

        EntityManager em = getEntityManager();//创建实体管理
        try {

            Query query = em.createQuery("SELECT c FROM CustomerMaster c WHERE c.status = :flag");
            query.setParameter("flag", true);
            return query.getResultList();

        } finally {
            em.close();//关闭实体管理
        }
    }

    public List<CustomerMaster> findCustomerMasterByName(String CustomerName) {//通过产品名字查询产品信息（放入了List中）
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT c FROM CustomerMaster c WHERE c.customerName LIKE :CustomerName AND c.status=:flag");
            query.setParameter("CustomerName", "%" + CustomerName + "%");
            query.setParameter("flag", true);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

}
