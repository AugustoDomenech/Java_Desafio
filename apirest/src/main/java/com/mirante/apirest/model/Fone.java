package com.mirante.apirest.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

import org.hibernate.annotations.ForeignKey;

@Entity
public class Fone implements Serializable {

    private static long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Client client;
    
    private String DDD;
    private String number;
    
    @ManyToOne
    private Client client_id;
    
    @ManyToOne
    @JoinColumn(name = "typeFone")
    private FoneType typeFone;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date registerDate;
    
    private int userRegister;
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fone)) {
            return false;
        }
        Fone other = (Fone) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mirantecrud.entity.ClientFone[ id=" + getId() + " ]";
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }

    public String getDDD() {
        return DDD;
    }


    public void setDDD(String DDD) {
        this.DDD = DDD;
    }


    public String getNumber() {
        return number;
    }


    public void setNumber(String number) {
        this.number = number;
    }

    public FoneType getTypeFone() {
        return typeFone;
    }

    public void setTypeFone(FoneType typeFone) {
        this.typeFone = typeFone;
    }

    /**
     * @return the registerDate
     */
    public Date getRegisterDate() {
        return registerDate;
    }

    /**
     * @param registerDate the registerDate to set
     */
    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    /**
     * @return the userRegister
     */
    public int getUserRegister() {
        return userRegister;
    }

    /**
     * @param userRegister the userRegister to set
     */
    public void setUserRegister(int userRegister) {
        this.userRegister = userRegister;
    }
    /**
     * @return the client
     */
    public Client getClient() {
        return client;
    }

    /**
     * @param client the client to set
     */
    public void setClient(Client client) {
        this.client = client;
    }
    
}
