package com.maonamassa.banco_de_dados;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.maonamassa.proposalsystem.Demanda;
import com.maonamassa.proposalsystem.Oferta;
import com.maonamassa.usersystem.Contratante;
import com.maonamassa.usersystem.Login;
import com.maonamassa.usersystem.Profissional;

public class Consultas {
    
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin");

    // consulta se o Login inserido é valido
    public static boolean validarLogin(String email, String senha) 
    {
        EntityManager em = emf.createEntityManager();
        try 
        {
            Login login = em.find(Login.class, email);
            
            if (login.getEmail().equals(email)){
                if (login.getSenha().equals(senha)){
                    return true;
                }
                else{
                    return false;
                }
            }
            else{
                return false;
            }
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

    // consulta se o email é de um profissional (1) ou de um contratante (0)
    public static boolean isProfessional(String email) 
    {
        EntityManager em = emf.createEntityManager();
        try 
        {
            return em.find(Profissional.class, email) != null;
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

    // consulta um Profissional pelo email e retorna suas informações
    public static Profissional consultarProfissional(String email) 
    {
        EntityManager em = emf.createEntityManager();
        try 
        {
            return em.find(Profissional.class, email);
        } 
        catch (Exception e) 
        {
            return null;
        } 
        finally 
        {
            em.close();
        }
    }

    // consulta um Contratante pelo email cadastrado e retorna suas informações
    public static Contratante consultarContratante(String email) 
    {
        EntityManager em = emf.createEntityManager();
        try 
        {
            return em.find(Contratante.class, email);
        } 
        catch (Exception e) 
        {
            return null;
        } 
        finally 
        {
            em.close();
        }
    }

    // consulta uma Demanda por id
    public static Demanda buscarDemandaPorId(Long demandaId) 
    {
        EntityManager em = emf.createEntityManager();
        try 
        {
            return em.find(Demanda.class, demandaId);
        } 
        finally 
        {
            em.close();
        }
    }


    // consulta uma Oferta por id
    public static Oferta buscarOfertaPorId(Long ofertaId) 
    {
        EntityManager em = emf.createEntityManager();
        try 
        {
            return em.find(Oferta.class, ofertaId);
        } 
        finally 
        {
            em.close();
        }
    }

}