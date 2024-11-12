package com.maonamassa.proposalsystem;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import com.maonamassa.usersystem.Contratante;
import com.maonamassa.usersystem.Profissional;

public class ProposalService 
{
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin");

    // metodo para criar uma nova Demanda
    public static Demanda criarDemanda(Profissional profissional, Contratante contratante, 
    		                           String descricao) 
    {
        EntityManager em = emf.createEntityManager();
        try 
        {
            em.getTransaction().begin();
            Demanda demanda = new Demanda(profissional, contratante, descricao, 
            			                  StatusOfertaDemanda.AGUARDANDO_ACEITACAO);
            em.persist(demanda);
            em.getTransaction().commit();
            return demanda;
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

    // metodo para criar uma nova Oferta
    public static Oferta criarOferta(Profissional profissional, Contratante contratante, 
    		                         String descricao, String valor) 
    {
        EntityManager em = emf.createEntityManager();
        try 
        {
            em.getTransaction().begin();
            Oferta oferta = new Oferta(profissional, contratante, descricao, valor, 
            		                   StatusOfertaDemanda.AGUARDANDO_ACEITACAO);
            em.persist(oferta);
            em.getTransaction().commit();
            return oferta;
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

    // metodo para aceitar uma Demanda
    public static void aceitarDemanda(Long demandaId) 
    {
        EntityManager em = emf.createEntityManager();
        try 
        {
            em.getTransaction().begin();
            Demanda demanda = em.find(Demanda.class, demandaId);
            if (demanda != null) 
            {
                demanda.aceitarDemanda();
                em.merge(demanda);
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

    // Método para recusar uma Demanda
    public static void recusarDemanda(Long demandaId) 
    {
        EntityManager em = emf.createEntityManager();
        try 
        {
            em.getTransaction().begin();
            Demanda demanda = em.find(Demanda.class, demandaId);
            if (demanda != null) 
            {
                demanda.recusarDemanda();
                em.merge(demanda);
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

    // metodo para aceitar uma Oferta
    public static void aceitarOferta(Long ofertaId) 
    {
        EntityManager em = emf.createEntityManager();
        try 
        {
            em.getTransaction().begin();
            Oferta oferta = em.find(Oferta.class, ofertaId);
            if (oferta != null) 
            {
                oferta.aceitarOferta();
                em.merge(oferta);
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

    // metodo para recusar uma Oferta
    public static void recusarOferta(Long ofertaId) 
    {
        EntityManager em = emf.createEntityManager();
        try 
        {
            em.getTransaction().begin();
            Oferta oferta = em.find(Oferta.class, ofertaId);
            if (oferta != null) 
            {
                oferta.recusarOferta();
                em.merge(oferta);
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

    // metodo para buscar uma Demanda por id
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


    // metodo para buscar uma Oferta por id
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
