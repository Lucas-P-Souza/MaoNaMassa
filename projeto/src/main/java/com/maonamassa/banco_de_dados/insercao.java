package com.maonamassa.banco_de_dados;

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

public class Insercao {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("lucas");

    // metodo para criar um novo profissional
    public static Profissional cadastrarProfissional(String nome, String cpfCnpj, String email, String senha) {

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            // cria e persiste o login para obter o ID gerado automaticamente
            Login login = new Login(email, senha);
            em.persist(login);
            em.flush(); // Garante que o ID seja gerado antes de ser associado

            // cria o profissional (sem setId, pois o ID é gerado pelo banco)
            Profissional profissional = new Profissional(nome, cpfCnpj, email, senha);
            em.persist(profissional);

            em.getTransaction().commit();

            return profissional;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    // metodo para criar um novo contratante
    public static Contratante cadastrarContratante(String nome, String cpfCnpj, String email, String senha) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            // cria e persiste o login para obter o ID gerado automaticamente
            Login login = new Login(email, senha);
            em.persist(login);
            em.flush(); // Garante que o ID seja gerado antes de ser associado

            // cria o contratante (sem setId, pois o ID é gerado pelo banco)
            Contratante contratante = new Contratante(nome, cpfCnpj, email, senha);
            em.persist(contratante);
            em.getTransaction().commit();

            return contratante;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    // metodo para atualizar as informacoes de um profissional
    public static void atualizarProfissional(Profissional profissional) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.merge(profissional);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    // metodo para atualizar as informacoes de um contratante
    public static void atualizarContratante(Contratante contratante) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.merge(contratante);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    // metodo para criar uma nova demanda
    public static Demanda cadastrarDemanda(Profissional profissional, Contratante contratante, String descricao) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            // cria a demanda (sem setId, pois o ID é gerado pelo banco)
            Demanda demanda = new Demanda(profissional, contratante, descricao);
            em.persist(demanda);
            em.getTransaction().commit();

            return demanda;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    // metodo para criar uma nova oferta
    public static Oferta cadastrarOferta(Profissional profissional, Contratante contratante, String descricao,
            String valor) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            // cria a oferta (sem setId, pois o ID é gerado pelo banco)
            Oferta oferta = new Oferta(profissional, contratante, descricao, valor);
            em.persist(oferta);
            em.getTransaction().commit();

            return oferta;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    // metodo para aceitar uma oferta
    public static void aceitarOferta(Oferta oferta) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            oferta.aceitarOferta();
            em.merge(oferta);
            Projeto projeto = new Projeto();
            projeto.setContratante(oferta.getContratante());
            projeto.setProfissional(oferta.getProfissional());
            em.persist(projeto);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    // metodo para aceitar uma demanda
    public static void aceitarDemanda(Demanda demanda) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            demanda.aceitarDemanda();
            em.merge(demanda);
            Projeto projeto = new Projeto();
            projeto.setContratante(demanda.getContratante());
            projeto.setProfissional(demanda.getProfissional());
            em.persist(projeto);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    // metodo para rejeitar uma Oferta
    public static void recusarOferta(Oferta oferta) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            oferta.recusarOferta();
            em.merge(oferta);
            excluirOferta(oferta);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    // metodo para rejeitar uma demanda
    public static void recusarDemanda(Demanda demanda) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            demanda.recusarDemanda();
            em.merge(demanda);
            excluirDemanda(demanda);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    // metodo para cancelar uma oferta
    public static void cancelarOferta(Oferta oferta) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            oferta.cancelarOferta();
            em.merge(oferta);
            excluirOferta(oferta);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    // metodo para cancelar uma demanda
    public static void cancelarDemanda(Demanda demanda) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            demanda.cancelarDemanda();
            em.merge(demanda);
            excluirDemanda(demanda);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
    // metodo para excluir uma Demanda
    public static void excluirDemanda(Demanda demanda) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(demanda);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    // metodo para excluir uma Oferta
    public static void excluirOferta(Oferta oferta) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(oferta);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    // metodo para criar um projeto
    public static void criarProjeto(Projeto projeto) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(em.merge(projeto));
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    // metodo para atualizar um projeto
    public static void atualizarProjeto(Projeto projeto) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(projeto);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    // metodo para excluir um projeto
    public static void excluirProjeto(Projeto projeto)

    {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(projeto);
            em.remove(projeto);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    // metodo para criar um contrato
    public static void criarContrato(Contrato contrato) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(em.merge(contrato));
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    // metodo para atualizar um contrato
    public static void atualizarContrato(Contrato contrato) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(contrato);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    // metodo para excluir um contrato
    public static void excluirContrato(Contrato contrato) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(contrato);
            em.remove(contrato);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}