package com.maonamassa.banco_de_dados;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.maonamassa.proposalsystem.Demanda;
import com.maonamassa.proposalsystem.Oferta;
import com.maonamassa.usersystem.Contratante;
import com.maonamassa.usersystem.Login;
import com.maonamassa.usersystem.Profissional;

public class Insercao {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("lucas");

    // metodo para criar um novo profissional
    public static Profissional cadastrarProfissional(String nome,  String cpfCnpj, String email, String senha) {

        EntityManager em = emf.createEntityManager();
        
        try
        {
            em.getTransaction().begin();

            // cria e persiste o login para obter o ID gerado automaticamente
            Login login = new Login(email, senha);
            em.persist(login);
            em.flush();  // Garante que o ID seja gerado antes de ser associado

            // cria o profissional (sem setId, pois o ID é gerado pelo banco)
            Profissional profissional = new Profissional(nome, cpfCnpj, email, senha);
            em.persist(profissional);

            em.getTransaction().commit();

            return profissional;
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

    // metodo para criar um novo contratante
    public static Contratante cadastrarContratante(String nome,  String cpfCnpj, String email, String senha) {
    	EntityManager em = emf.createEntityManager();

        try 
        {
            em.getTransaction().begin();

            // cria e persiste o login para obter o ID gerado automaticamente
            Login login = new Login(email, senha);
            em.persist(login);
            em.flush();  // Garante que o ID seja gerado antes de ser associado

            // cria o contratante (sem setId, pois o ID é gerado pelo banco)
            Contratante contratante = new Contratante(nome, cpfCnpj, email, senha); 
            em.persist(contratante);
            em.getTransaction().commit();

            return contratante;
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

    // metodo para criar uma nova demanda
    public static Demanda cadastrarDemanda(Profissional profissional, Contratante contratante, String descricao) {
        EntityManager em = emf.createEntityManager();

        try 
        {
            em.getTransaction().begin();

            // cria a demanda (sem setId, pois o ID é gerado pelo banco)
            Demanda demanda = new Demanda(profissional, contratante, descricao);
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

    // metodo para criar uma nova oferta
    public static Oferta cadastrarOferta(Profissional profissional, Contratante contratante, String descricao, String valor) {
        EntityManager em = emf.createEntityManager();

        try 
        {
            em.getTransaction().begin();

            // cria a oferta (sem setId, pois o ID é gerado pelo banco)
            Oferta oferta = new Oferta(profissional, contratante, descricao, valor);
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

    // metodo para aceitar uma demanda
    public static void aceitarDemanda(Demanda demanda) {
        EntityManager em = emf.createEntityManager();

        try 
        {
            em.getTransaction().begin();
            demanda.aceitarDemanda();
            em.merge(demanda);
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

    // metodo para aceitar uma oferta
    public static void aceitarOferta(Oferta oferta) {
        EntityManager em = emf.createEntityManager();

        try 
        {
            em.getTransaction().begin();
            oferta.aceitarOferta();
            em.merge(oferta);
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

    // metodo para recusar uma demanda
    public static void recusarDemanda(Demanda demanda) {
        EntityManager em = emf.createEntityManager();

        try 
        {
            em.getTransaction().begin();
            demanda.recusarDemanda();
            em.merge(demanda);
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

    // metodo para recusar uma oferta
    public static void recusarOferta(Oferta oferta) {
        EntityManager em = emf.createEntityManager();

        try 
        {
            em.getTransaction().begin();
            oferta.recusarOferta();
            em.merge(oferta);
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

    // metodo para atualizar as informacoes de um contratante
    public static void atualizarContratante(Contratante contratante) {
        EntityManager em = emf.createEntityManager();

        try 
        {
            em.getTransaction().begin();
            em.merge(contratante);
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

    // metodo para atualizar as informacoes de um profissional
    public static void atualizarProfissional(Profissional profissional) {
        EntityManager em = emf.createEntityManager();

        try 
        {
            em.getTransaction().begin();
            em.merge(profissional);
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

    // metodo para criar um projeto

    // metodo para criar um contrato

    // metodo para assinar um contrato (contratante)

    // metodo para assinar um contrato (profissional)

}