/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iot.dao.repository;

import iot.dao.entity.CustomerMaster;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hatanococoro
 */
public class CustomerMasterDAOTest {
    
    public CustomerMasterDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of findCustomerMasterByName method, of class CustomerMasterDAO.
     */
    @Test
    public void testFindCustomerMasterByName() {
        System.out.println("findCustomerMasterByName");
        String CustomerName = "";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("orderPU");
        CustomerMasterDAO instance = new CustomerMasterDAO(emf) ;
        List<CustomerMaster> result = instance.findCustomerMasterByName(CustomerName);
        
        System.out.println(result.get(0).getCustomerName());

    }
    
}
