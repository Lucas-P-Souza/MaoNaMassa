package com.maonamassa.projectsystem;

import java.time.LocalDate;
import com.maonamassa.proposalsystem.Oferta;
import com.maonamassa.proposalsystem.Demanda;
import javax.persistence.Persistence;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class ProjectService 
{
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin");

    // metodo para criar um novo projeto
    public static Projeto criarProjeto(Oferta oferta, Demanda demanda, String nomeProjeto, 
    		                           String tipoServico, String descricaoProjeto, 
    		                           LocalDate dataInicio, LocalDate dataFim, String valorCombinado) 
    {
        EntityManager em = emf.createEntityManager();
        try
        {
            em.getTransaction().begin();

            Projeto projeto = new Projeto(oferta, demanda, nomeProjeto, tipoServico, 
            		                      descricaoProjeto, dataInicio, dataFim, valorCombinado);
            em.persist(projeto);

            em.getTransaction().commit();
            return projeto;
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

    // metodo para buscar um projeto pelo ID
    public static Projeto buscarProjetoPorId(Long id) 
    {
        EntityManager em = emf.createEntityManager();

        try 
        {
            return em.find(Projeto.class, id);
        } 
        finally 
        {
            em.close();
        }
    }

    // metodo para atualizar informações do projeto
    public static Projeto atualizarProjeto(Long id, String novoNomeProjeto, String novaDescricao, 
    		                               String novoValorCombinado) {
        EntityManager em = emf.createEntityManager();
        try 
        {
            em.getTransaction().begin();
            Projeto projeto = em.find(Projeto.class, id);
            if (projeto != null) 
            {
                projeto.setNomeProjeto(novoNomeProjeto);
                projeto.setDescricaoProjeto(novaDescricao);
                projeto.setValorCombinado(novoValorCombinado);
                em.merge(projeto);
            }

            em.getTransaction().commit();
            return projeto;
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

    // metodo para excluir um projeto
    public static boolean excluirProjeto(Long id) 
    {
        EntityManager em = emf.createEntityManager();
        try 
        {
            em.getTransaction().begin();
            Projeto projeto = em.find(Projeto.class, id);
            if (projeto != null)
            {
                em.remove(projeto);
                em.getTransaction().commit();
                return true;
            } 
            else 
            {
                em.getTransaction().rollback();
                return false;
            }
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
}
