package com.maonamassa.apps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.maonamassa.proposalsystem.Demanda;
import com.maonamassa.proposalsystem.Oferta;
import com.maonamassa.proposalsystem.StatusOfertaDemanda;
import com.maonamassa.usersystem.Contratante;
import com.maonamassa.usersystem.Login;
import com.maonamassa.usersystem.Profissional;

public class AppBanco {

        private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("lucas");

        public static void main(String[] args) {

                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();

                String[] names = {
                                "Ana", "Beatriz", "Carlos", "Daniel", "Eduarda", "Fernanda", "Gabriel", "Heloisa",
                                "Igor", "Julia", "Kleber", "Larissa", "Matheus", "Nathalia", "Otavio", "Paula",
                                "Renato", "Sara", "Thiago", "Ursula", "Valter", "Wellington", "Ximena", "Yuri",
                                "Zilda", "Alex", "Bruna", "Caio", "Davi", "Emilly", "Fabio", "Giovana", "Henrique",
                                "Isabela", "Joao", "Karina", "Leonardo", "Marcela", "Natalia", "Olivia", "Patricia",
                                "Rafael", "Samara", "Tatiane", "Ulisses", "Vinicius", "Wallace", "Xavier", "Yasmin",
                                "Zuleika", "Amanda", "Bruno", "Camila", "Diego", "Elaine", "Felipe", "Gustavo",
                                "Heitor", "Iara", "Jorge", "Kelly", "Luis", "Maria", "Nicolas", "Oscar", "Pedro",
                                "Rodrigo", "Silvana", "Tales", "Ubiratan", "Vanessa", "William", "Xande", "Yara",
                                "Zequinha"
                };

                // Cria a lista inicial de nomes
                List<String> originalList = new ArrayList<>();
                Collections.addAll(originalList, names);

                // Cria 4 ArrayLists com os nomes em ordem aleatória
                ArrayList<String> names1 = new ArrayList<>(originalList);
                ArrayList<String> names2 = new ArrayList<>(originalList);
                ArrayList<String> names3 = new ArrayList<>(originalList);
                ArrayList<String> names4 = new ArrayList<>(originalList);

                // Embaralha cada lista para garantir uma ordem aleatória
                Collections.shuffle(names1);
                Collections.shuffle(names2);
                Collections.shuffle(names3);
                Collections.shuffle(names4);

                List<Profissional> pedreiros = new ArrayList<>();
                List<Contratante> contratantesPedreiro = new ArrayList<>();
                List<Profissional> programadores = new ArrayList<>();
                List<Contratante> contratantesProgramador = new ArrayList<>();

                for (int i = 1; i <= 40; i++) {
                        Profissional pedreiro = new Profissional();
                        pedreiro.setName(names1.get(i));
                        pedreiro.setEmail(names1.get(i) + i + "@email.com");
                        pedreiro.setPassword("senhasenha");
                        pedreiro.setPhone("2199999000" + i);
                        pedreiro.setAddress("Cidade " + i);
                        pedreiro.setProfissao("Pedreiro");
                        pedreiro.setCpfcnpj("12345678" + i);
                        em.persist(pedreiro);
                        pedreiros.add(pedreiro);

                        Contratante contratantePedreiro = new Contratante();
                        contratantePedreiro.setName(names2.get(i));
                        contratantePedreiro.setEmail(names2.get(i) + i + "@gmail.com");
                        contratantePedreiro.setPassword("senhasenha");
                        contratantePedreiro.setPhone("2199999000" + i);
                        contratantePedreiro.setAddress("Cidade " + i);
                        contratantePedreiro.setBuscando("Pedreiro");
                        contratantePedreiro.setCpfcnpj("87654321" + i);
                        em.persist(contratantePedreiro);
                        contratantesPedreiro.add(contratantePedreiro);

                        Profissional programador = new Profissional();
                        programador.setName(names3.get(i));
                        programador.setEmail(names3.get(i) + i + "@hotmail.com");
                        programador.setPassword("senhasenha");
                        programador.setPhone("3199999000" + i);
                        programador.setAddress("Cidade " + i);
                        programador.setProfissao("Programador");
                        programador.setCpfcnpj("23456781" + i);
                        em.persist(programador);
                        programadores.add(programador);

                        Contratante contratanteProgramador = new Contratante();
                        contratanteProgramador.setName(names4.get(i));
                        contratanteProgramador.setEmail(names4.get(i) + i + "@outlook.com");
                        contratanteProgramador.setPassword("senhasenha");
                        contratanteProgramador.setPhone("3199999000" + i);
                        contratanteProgramador.setAddress("Cidade " + i);
                        contratanteProgramador.setBuscando("Programador");
                        contratanteProgramador.setCpfcnpj("76543218" + i);
                        em.persist(contratanteProgramador);
                        contratantesProgramador.add(contratanteProgramador);
                }

                Login login1 = new Login();
                login1.setEmail(pedreiros.get(0).getEmail());
                login1.setSenha(pedreiros.get(0).getPassword());
                em.persist(login1);

                Login login2 = new Login();
                login2.setEmail(programadores.get(0).getEmail());
                login2.setSenha(programadores.get(0).getPassword());
                em.persist(login2);

                Login login3 = new Login();
                login3.setEmail(contratantesPedreiro.get(0).getEmail());
                login3.setSenha(contratantesPedreiro.get(0).getPassword());
                em.persist(login3);

                Login login4 = new Login();
                login4.setEmail(contratantesProgramador.get(0).getEmail());
                login4.setSenha(contratantesProgramador.get(0).getPassword());
                em.persist(login4);

                for (int i = 0; i < 40; i++) {
                        Oferta oferta = new Oferta(pedreiros.get(i), contratantesPedreiro.get(0),
                                        "Oferta de Pedreiro para " + contratantesPedreiro.get(i).getName(),
                                        "1000");
                        oferta.setStatus(StatusOfertaDemanda.AGUARDANDO_ACEITACAO);
                        em.persist(oferta);
                }

                for (int i = 0; i < 40; i++) {
                        Oferta oferta = new Oferta(programadores.get(i), contratantesProgramador.get(0),
                                        "Oferta de Programador para " + contratantesProgramador.get(i).getName(),
                                        "1000");
                        oferta.setStatus(StatusOfertaDemanda.AGUARDANDO_ACEITACAO);
                        em.persist(oferta);
                }

                for (int i = 0; i < 40; i++) {
                        Demanda demanda = new Demanda(pedreiros.get(0), contratantesPedreiro.get(i),
                                        "Demanda de Pedreiro para " + pedreiros.get(0).getName());
                        demanda.setStatus(StatusOfertaDemanda.AGUARDANDO_ACEITACAO);
                        em.persist(demanda);
                }

                for (int i = 0; i < 40; i++) {
                        Demanda demanda = new Demanda(programadores.get(0), contratantesProgramador.get(i),
                                        "Demanda de Programador para " + programadores.get(0).getName());
                        demanda.setStatus(StatusOfertaDemanda.AGUARDANDO_ACEITACAO);
                        em.persist(demanda);
                }

                em.getTransaction().commit();
                em.close();
        }
}