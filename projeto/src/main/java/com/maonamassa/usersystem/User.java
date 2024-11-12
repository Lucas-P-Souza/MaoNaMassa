package com.maonamassa.usersystem;

import javax.persistence.*;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User 
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String cpfcnpj;
    private String email;
    private String password;
    private String phone;
    private String address;


    private Boolean logado;
    
    public User() {}

    public User (String nome, String email, String senha, String cpfCnpj) 
    {
        this.name = nome;
        this.email = email;
        this.password = senha;
        this.cpfcnpj = cpfCnpj;
        this.logado = false;
    }

    public User(String nome, String email, String senha, String telefone, String endereco, 
    		    String cpfCnpj) 
    {
        this.name = nome;
        this.email = email;
        this.password = senha;
        this.phone = telefone;
        this.address = endereco;
        this.cpfcnpj = cpfCnpj;
        this.logado = false;
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    
    public String getCpfcnpj() {return cpfcnpj;}
    public void setCpfcnpj(String cpfcnpj) {this.cpfcnpj = cpfcnpj;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

    public String getPhone() {return phone;}
    public void setPhone(String phone) {this.phone = phone;}

    public String getAddress() {return address;}
    public void setAddress(String address) {this.address = address;}

    public Boolean getLogado() {return logado;}
    public void setLogado(Boolean logado) {this.logado = logado;}
}

