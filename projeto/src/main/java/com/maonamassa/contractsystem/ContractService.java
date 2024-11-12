package com.maonamassa.contractsystem;

import java.util.List;
import com.maonamassa.projectsystem.Projeto;
import javax.persistence.Persistence;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class ContractService 
{
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin");

    // metodo para criar um novo Contrato
    public static Contrato criarContrato(Projeto projeto) 
    {
        EntityManager em = emf.createEntityManager();
        try 
        {
            em.getTransaction().begin();
            Contrato contrato = new Contrato(projeto);
            em.persist(contrato);
            em.getTransaction().commit();
            return contrato;
        } 
        catch (Exception e) 
        {
            em.getTransaction().rollback();
            throw e;
        } 
        finally
        {
        	em.close();
        }

    }

    // metodo para buscar todos os contratos
    public static List<Contrato> buscarContratos() 
    {
        EntityManager em = emf.createEntityManager();
        try 
        {
            return em.createQuery("SELECT c FROM Contrato c", Contrato.class)
                    .getResultList();
        } 
        finally 
        {
            em.close();
        }
    }

    // Método para assinar contrato pelo profissional
    public static void assinarPeloProfissional(Long contratoId) 
    {
        EntityManager em = emf.createEntityManager();
        try 
        {
            em.getTransaction().begin();
            Contrato contrato = em.find(Contrato.class, contratoId);
            if (contrato != null) {
                contrato.assinarPeloProfissional();
                em.merge(contrato);
            }
            em.getTransaction().commit();
        } 
        catch (Exception e) 
        {
            em.getTransaction().rollback();
            throw e;
        } 
        finally 
        {
            em.close();
        }
    }

    // Método para assinar contrato pelo contratante
    public static void assinarPeloContratante(Long contratoId) 
    {
        EntityManager em = emf.createEntityManager();
        try 
        {
            em.getTransaction().begin();
            Contrato contrato = em.find(Contrato.class, contratoId);
            if (contrato != null) 
            {
                contrato.assinarPeloContratante();
                em.merge(contrato);
            }
            em.getTransaction().commit();
        } 
        catch (Exception e) 
        {
            em.getTransaction().rollback();
            throw e;
        } 
        finally 
        {
            em.close();
        }
    }

    // Método para cancelar contrato
    public static void cancelarContrato(Long contratoId) 
    {
        EntityManager em = emf.createEntityManager();
        try 
        {
            em.getTransaction().begin();
            Contrato contrato = em.find(Contrato.class, contratoId);
            if (contrato != null) 
            {
                contrato.cancelarContrato();
                em.merge(contrato);
            }
            em.getTransaction().commit();
        } 
        catch (Exception e) 
        {
            em.getTransaction().rollback();
            throw e;
        } 
        finally 
        {
            em.close();
        }
    }
    
    // Método para buscar um contrato pelo ID
    public static Contrato buscarContratoPorId(Long contratoId) 
    {
        EntityManager em = emf.createEntityManager();
        try 
        {
            return em.find(Contrato.class, contratoId);
        } 
        finally 
        {
            em.close();
        }
    }
}
