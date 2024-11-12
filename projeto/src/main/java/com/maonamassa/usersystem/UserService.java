package com.maonamassa.usersystem;

import javax.persistence.Persistence;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class UserService 
{

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("maonamassa");

    // metodo para criar um novo profissional
    public static Profissional cadastrarProfissional(String nome, String email, String senha, String cpfCnpj) {
        EntityManager em = emf.createEntityManager();
        
        try 
        {
            em.getTransaction().begin();

            // cria e persiste o login para obter o ID gerado automaticamente
            Login login = new Login(email, senha);
            em.persist(login);
            em.flush();  // Garante que o ID seja gerado antes de ser associado

            // cria o profissional (sem setId, pois o ID é gerado pelo banco)
            Profissional profissional = new Profissional(nome, email, senha, null, null, cpfCnpj, null, null);
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
    public static Contratante cadastrarContratante(String nome, String email, String senha, String cpfCnpj) {
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

    // metodo para buscar um usuário pelo ID (que é o mesmo nas tabelas)
    public static User buscarUsuarioPorId(Long id) 
    {
        EntityManager em = emf.createEntityManager();

        try 
        {
            User user = em.find(Profissional.class, id);
            if (user == null) user = em.find(Contratante.class, id);
            return user;
        } 
        finally 
        {
            em.close();
        }
    }

    // metodo para validar login de um usuário
    public static boolean validarLogin(String email, String senha) 
    {
        EntityManager em = emf.createEntityManager();
        try 
        {
            Login login = em.createQuery("SELECT l FROM Login l WHERE l.email = :email AND l.senha = :senha", Login.class)
                            .setParameter("email", email)
                            .setParameter("senha", senha)
                            .getSingleResult();
            return login != null;
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
