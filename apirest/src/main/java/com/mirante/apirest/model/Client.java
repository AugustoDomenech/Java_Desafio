package com.mirante.apirest.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;

import java.util.List;

@Entity
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
   
    @NotNull(message = "Campo 'Nome' é obrigatório.")
    private String name;
    
    @NotNull(message = "Campo 'Documento' é obrigatório.")    
    private String document;

    @OneToMany( cascade = CascadeType.ALL ,mappedBy = "client")
    private List<Fone> fones;

    @NotNull(message = "Campo 'Data de nascimento' é obrigatório.")    
    @PastOrPresent(message = "A data de aniversario não pode ser uma data futura.")
    private LocalDate birthDate;

    @Column(name = "name_mother")
    @Max(value = 100, message  = "O campo 'Nome da mãe' deve conter no maximo 100 characters")
    @Pattern(regexp="[a-z]")
    private String nameMother;

    @Column(name = "name_father")
    @Max(value = 100, message  = "O campo 'Nome do pai' deve conter no maximo 100 characters")
    @Pattern(regexp="[a-z]")
    private String nameFather;

    @Column(name = "register_date")
    private LocalDate registerDate;

    @Column(name = "user_register") 
    private int userRegister;

    @Column(name = "client_type")
    @NotNull(message = "Campo 'Tipo de cliente' é obrigatório.")
    private ClientType clientType;

    public List<Fone> getFones() {
        return fones;
    }

    public void setFones(List<Fone> fones) {
        this.fones = fones;
    }

    
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getNameMother() {
        return nameMother;
    }

    public void setNameMother(String nameMother) {
        this.nameMother = nameMother;
    }

    public String getNameFather() {
        return nameFather;
    }

    public void setNameFather(String nameFather) {
        this.nameFather = nameFather;
    }

    public LocalDate getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDate registerDate) {
        this.registerDate = registerDate;
    }

    public int getUserRegister() {
        return userRegister;
    }
    
    public void setUserRegister(int userRegister) {
        this.userRegister = userRegister;
    }


    public ClientType getClientType() {
        return clientType;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }

    //Comparação entre objetos
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Client)) {
            return false;
        }
        Client other = (Client) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    //Descrição geral do objeto
    @Override
    public String toString() {
        return "Client[ "
                + "id = "           + getId()
                + "name = "         + getName()
                + "document = "     + getDocument()
                + "birthDate = "    + getBirthDate()
                + "nameMother = "   + getNameMother()
                + "nameFather = "   + getNameFather()
                + "registerDate = " + getUserRegister()
                + "clientType = "   + getClientType()
                + " ]";
    }
}