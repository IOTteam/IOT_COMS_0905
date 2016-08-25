/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iot.dao.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hatanococoro
 */
@Entity
@Table(name = "customer_price")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CustomerPrice.findAll", query = "SELECT c FROM CustomerPrice c"),
    @NamedQuery(name = "CustomerPrice.findByRanges", query = "SELECT c FROM CustomerPrice c WHERE c.ranges = :ranges"),
    @NamedQuery(name = "CustomerPrice.findByRangePrice", query = "SELECT c FROM CustomerPrice c WHERE c.rangePrice = :rangePrice"),
    @NamedQuery(name = "CustomerPrice.findByCustomerPriceId", query = "SELECT c FROM CustomerPrice c WHERE c.customerPriceId = :customerPriceId"),
    @NamedQuery(name = "CustomerPrice.findByStatus", query = "SELECT c FROM CustomerPrice c WHERE c.status = :status")})
public class CustomerPrice implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "ranges")
    private int ranges;
    @Basic(optional = false)
    @Column(name = "range_price")
    private float rangePrice;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "customer_price_id")
    private Integer customerPriceId;
    @Column(name = "status")
    private String status;
    @JoinColumn(name = "customer_master_id", referencedColumnName = "customer_master_id")
    @ManyToOne(optional = false)
    private CustomerMaster customerMasterId;
    @JoinColumn(name = "product_master_id", referencedColumnName = "product_master_id")
    @ManyToOne(optional = false)
    private ProductMaster productMasterId;

    public CustomerPrice() {
    }

    public CustomerPrice(Integer customerPriceId) {
        this.customerPriceId = customerPriceId;
    }

    public CustomerPrice(Integer customerPriceId, int ranges, float rangePrice) {
        this.customerPriceId = customerPriceId;
        this.ranges = ranges;
        this.rangePrice = rangePrice;
    }

    public int getRanges() {
        return ranges;
    }

    public void setRanges(int ranges) {
        this.ranges = ranges;
    }

    public float getRangePrice() {
        return rangePrice;
    }

    public void setRangePrice(float rangePrice) {
        this.rangePrice = rangePrice;
    }

    public Integer getCustomerPriceId() {
        return customerPriceId;
    }

    public void setCustomerPriceId(Integer customerPriceId) {
        this.customerPriceId = customerPriceId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CustomerMaster getCustomerMasterId() {
        return customerMasterId;
    }

    public void setCustomerMasterId(CustomerMaster customerMasterId) {
        this.customerMasterId = customerMasterId;
    }

    public ProductMaster getProductMasterId() {
        return productMasterId;
    }

    public void setProductMasterId(ProductMaster productMasterId) {
        this.productMasterId = productMasterId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (customerPriceId != null ? customerPriceId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CustomerPrice)) {
            return false;
        }
        CustomerPrice other = (CustomerPrice) object;
        if ((this.customerPriceId == null && other.customerPriceId != null) || (this.customerPriceId != null && !this.customerPriceId.equals(other.customerPriceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "iot.dao.entity.CustomerPrice[ customerPriceId=" + customerPriceId + " ]";
    }
    
}
