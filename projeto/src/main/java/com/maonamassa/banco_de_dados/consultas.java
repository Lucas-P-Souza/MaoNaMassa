package com.maonamassa.banco_de_dados;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.maonamassa.usersystem.Login;

public class Consultas {
    
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin");

    // consulta se o login inserido é valido
    public static boolean validarLogin(String email, String senha) 
    {
        EntityManager em = emf.createEntityManager();
        try 
        {
            Login login = em.find(Login.class, email);
            return login != null && login.getSenha().equals(senha);
        } 
        catch (Exception e) 
        {
            return false;
        } 
        finally 
        {
            em.close();
        }
    }

    // consulta se o email inserido já está cadastrado
    public static boolean emailCadastrado(String email) 
    {
        EntityManager em = emf.createEntityManager();
        try 
        {
            return em.find(Login.class, email) != null;
        } 
        catch (Exception e) 
        {
            return false;
        } 
        finally 
        {
            em.close();
        }
    }

    

}
