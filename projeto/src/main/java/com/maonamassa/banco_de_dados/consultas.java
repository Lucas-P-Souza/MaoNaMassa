package com.maonamassa.banco_de_dados;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.maonamassa.contractsystem.Contrato;
import com.maonamassa.projectsystem.Projeto;
import com.maonamassa.proposalsystem.Demanda;
import com.maonamassa.proposalsystem.Oferta;
import com.maonamassa.usersystem.Contratante;
import com.maonamassa.usersystem.Login;
import com.maonamassa.usersystem.Profissional;

public class Consultas {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("lucas");

    // consulta se o Login inserido é valido
    public static boolean validarLogin(String email, String senha) {
        EntityManager em = emf.createEntityManager();
        try {
            Login login = em.find(Login.class, email);

            if (login.getEmail().equals(email)) {
                if (login.getSenha().equals(senha)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        } finally {
            em.close();
        }
    }

    // consulta se o email inserido já está cadastrado
    public static boolean emailCadastrado(String email) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Login.class, email) != null;
        } catch (Exception e) {
            return false;
        } finally {
            em.close();
        }
    }

    // consulta se o email é de um profissional (1) ou de um contratante (0)
    public static boolean isProfessional(String email) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Profissional.class, email) != null;
        } catch (Exception e) {
            return false;
        } finally {
            em.close();
        }
    }

    // consulta um Profissional pelo email e retorna suas informações
    public static Profissional consultarProfissional(String email) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Profissional.class, email);
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    // consulta um Contratante pelo email cadastrado e retorna suas informações
    public static Contratante consultarContratante(String email) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Contratante.class, email);
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    // consulta profissionais que atendem a profissao digitada
    public static List<Profissional> buscarProfissionaisPorProfissao(String profissao) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT p FROM Profissional p WHERE p.profissao = :profissao", Profissional.class)
                    .setParameter("profissao", profissao).getResultList();
        } finally {
            em.close();
        }
    }

    // consulta contratantes que atendem o "buscando" digitado
    public static List<Contratante> buscarContratantesPorBuscando(String buscando) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT c FROM Contratante c WHERE c.buscando = :buscando", Contratante.class)
                    .setParameter("buscando", buscando).getResultList();
        } finally {
            em.close();
        }
    }

    // consulta todas as ofertas recebidas por um contratante
    public static List<Oferta> buscarOfertasRecebidas(Contratante contratante) {
        EntityManager em = emf.createEntityManager();
        try {
            // A consulta assume que "Oferta" tem um campo "contratante"
            return em.createQuery("SELECT o FROM Oferta o WHERE o.contratante = :contratante", Oferta.class)
                    .setParameter("contratante", contratante)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    // Consulta todas as demandas enviadas por um contratante
    public static List<Demanda> buscarDemandasEnviadas(Contratante contratante) {
        EntityManager em = emf.createEntityManager();
        try {
            // A consulta assume que "Demanda" tem um campo "contratante"
            return em.createQuery("SELECT d FROM Demanda d WHERE d.contratante = :contratante", Demanda.class)
                    .setParameter("contratante", contratante)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    // consulta todas as ofertas enviadas por um profissional
    public static List<Oferta> buscarOfertasEnviadas(Profissional profissional) {
        EntityManager em = emf.createEntityManager();
        try {
            // A consulta assume que "Oferta" tem um campo "profissional"
            return em.createQuery("SELECT o FROM Oferta o WHERE o.profissional = :profissional", Oferta.class)
                    .setParameter("profissional", profissional)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    // consulta todas as demandas recebidas por um profissional
    public static List<Demanda> buscarDemandasRecebidas(Profissional profissional) {
        EntityManager em = emf.createEntityManager();
        try {
            // A consulta assume que "Demanda" tem um campo "profissional" que representa o
            // profissional que recebeu a demanda
            return em.createQuery("SELECT d FROM Demanda d WHERE d.profissional = :profissional", Demanda.class)
                    .setParameter("profissional", profissional)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    // consulta todos os projetos de um profissional
    public static List<Projeto> buscarProjetosPorProfissional(Profissional profissional) {
        EntityManager em = emf.createEntityManager();
        try {
            // A consulta assume que "Projeto" tem um campo "profissional"
            return em.createQuery("SELECT p FROM Projeto p WHERE p.profissional = :profissional", Projeto.class)
                    .setParameter("profissional", profissional)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    // consulta todos os projetos de um contratante
    public static List<Projeto> buscarProjetosPorContratante(Contratante contratante) {
        EntityManager em = emf.createEntityManager();
        try {
            // A consulta assume que "Projeto" tem um campo "contratante"
            return em.createQuery("SELECT p FROM Projeto p WHERE p.contratante = :contratante", Projeto.class)
                    .setParameter("contratante", contratante)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    // consulta todos os contratos de um profissional
    public static List<Contrato> buscarContratosPorProfissional(Profissional profissional) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT c FROM Contrato c WHERE c.profissional = :profissional", Contrato.class)
                    .setParameter("profissional", profissional)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    // consulta todos os contratos de um contratante
    public static List<Contrato> buscarContratosPorContratante(Contratante contratante) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT c FROM Contrato c WHERE c.contratante = :contratante", Contrato.class)
                    .setParameter("contratante", contratante)
                    .getResultList();
        } finally {
            em.close();
        }
    }

}