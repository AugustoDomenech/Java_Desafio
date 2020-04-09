package com.mirante.apirest.model;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;


@Entity
public class User implements Serializable {

    private static long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    
    @Max(value = 100, message  = "O campo 'Nome' deve conter no maximo 100 characters")
    @NotNull(message = "Campo 'Nome' é obrigatório.")
    private String name;    
    
    @NotNull(message = "Campo 'Login' é obrigatório.")
    @Max(value = 15, message  = "O campo 'Nome' deve conter no maximo 100 characters")
    private String login;
    
    @NotNull(message = "Campo 'Password' é obrigatório.")
    private String password;
   
    @ManyToOne
    @JoinColumn(name = "type_id")
    @NotNull(message = "O tipo de usuário deve ser informado.")
    private UserType type;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static void setSerialVersionUID(long serialVersionUID) {
        User.serialVersionUID = serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public LocalDate getRegister_date() {
        return register_date;
    }

    public void setRegister_date(LocalDate register_date) {
        this.register_date = register_date;
    }
    
    private LocalDate register_date;

}
