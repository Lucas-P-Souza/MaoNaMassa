package com.maonamassa.banco_de_dados;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.maonamassa.usersystem.Contratante;
import com.maonamassa.usersystem.Login;
import com.maonamassa.usersystem.Profissional;

public class Insercao {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin");

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
            Contratante contratante = new Contratante(nome, email, senha, null, null, null, cpfCnpj);
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

}
