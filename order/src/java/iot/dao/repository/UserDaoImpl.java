/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iot.dao.repository;

import iot.dao.entity.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
public class UserDaoImpl implements UserDAO{
    
    @PersistenceContext(unitName = "orderPU")  
    private EntityManagerFactory emf;
    
    @Override
    public List<User> getAllUser(){

        emf = Persistence.createEntityManagerFactory("orderPU");
        EntityManager em = emf.createEntityManager();        
        Query query = em.createQuery("SELECT u FROM User u");
        List<User> users = query.getResultList();
        em.close();
        
        return users;
   }
    
    @Override
    public User getUser(String userId){

        emf = Persistence.createEntityManagerFactory("orderPU");
        EntityManager em = emf.createEntityManager();        
        Query query = em.createQuery("SELECT u FROM User u where u.userId=:userId");
        query.setParameter("userId", userId);
        User user =(User)query.getSingleResult();
        em.close();
        
        return user;
   }
    
    //通过用户名和密码来查找用户
    @Override
    public User getUserByNameAndPassword(String username,String password){
        
        //创建实体工厂，并使用工厂构建实体管理
        emf = Persistence.createEntityManagerFactory("orderPU");
        EntityManager em = emf.createEntityManager();  
        
        try {
        
        //创建安全查询工厂
        CriteriaBuilder cb = em.getCriteriaBuilder();
        //创建查询主语句
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        //实例化元模型类
        Metamodel metamodel = em.getMetamodel();
        EntityType<User> user_ = metamodel.entity(User.class);
        //定义实体类型
        Root<User> user = cq.from(User.class);
        //构造过滤条件
        Predicate condition = cb.and(cb.equal(user.get(user_.getSingularAttribute("userName")), username),
                                     cb.equal(user.get(user_.getSingularAttribute("password")), password));
        cq.where(condition);
        //创建查询
        Query q = em.createQuery(cq);
        //返回查询结果
        User userInfo =(User)q.getSingleResult();
        return userInfo;
            
        } catch (NoResultException e) {
           return null;
        }finally{
            em.close();
        }
        
    }
    

    
    @Override
    public void addUser(User user){
    
        emf = Persistence.createEntityManagerFactory("orderPU");
        EntityManager em = emf.createEntityManager();        
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();        
    }
    
    @Override
    public void editUser(User user){
    
        emf = Persistence.createEntityManagerFactory("orderPU");
        EntityManager em = emf.createEntityManager();        
        em.getTransaction().begin();
        User oldUser = em.find(User.class, user.getLoginId());
        oldUser.setPassword(user.getPassword());
        oldUser.setUserName(user.getUserName());
        em.persist(oldUser);
        em.getTransaction().commit();
        em.close();
        
    }
    
    @Override
    public void delUser(int loginId){
    
        emf = Persistence.createEntityManagerFactory("orderPU");
        EntityManager em = emf.createEntityManager();        
        em.getTransaction().begin();
        User oldUser = em.find(User.class, loginId);
        em.remove(oldUser);
        em.getTransaction().commit();
        
        em.close();
    }
   
    
}
