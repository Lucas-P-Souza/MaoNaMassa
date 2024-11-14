package com.maonamassa.usersystem;

public class Sessao {

    public Sessao() {}

    // Atributos estáticos para armazenar o usuário logado
    private static String nome;
    private static String cpfCnpj;
    private static String email;
    private static String senha;
    private static String telefone;
    private static String endereco;
    private static Boolean isProfissional;
    private static Boolean isContratante;

    public String getNome() {return nome;}
    public void setNome(String nome) {Sessao.nome = nome;}

    public String getCpfCnpj() {return cpfCnpj;}
    public void setCpfCnpj(String cpfCnpj) {Sessao.cpfCnpj = cpfCnpj;}

    public String getEmail() {return email;}
    public void setEmail(String email) {Sessao.email = email;}

    public String getSenha() {return senha;}
    public void setSenha(String senha) {Sessao.senha = senha;}

    public String getTelefone() {return telefone;}
    public void setTelefone(String telefone) {Sessao.telefone = telefone;}

    public String getEndereco() {return endereco;}
    public void setEndereco(String endereco) {Sessao.endereco = endereco;}

    public Boolean getIsProfissional() {return isProfissional;}
    public void setIsProfissional(Boolean isProfissional) {Sessao.isProfissional = isProfissional;}

    public Boolean getIsContratante() {return isContratante;}
    public void setIsContratante(Boolean isContratante) {Sessao.isContratante = isContratante;}

    // Profissional ----------------------------------------------------------------------

    private static Profissional profissionalLogado;
    private static String profissao;
    private static String areaAtuacao;
    private static Disponibilidade disponibilidade;

    public String getProfissao() {return profissao;}
    public void setProfissao(String profissao) {Sessao.profissao = profissao;}

    public String getAreaAtuacao() {return areaAtuacao;}
    public void setAreaAtuacao(String areaAtuacao) {Sessao.areaAtuacao = areaAtuacao;}

    public Disponibilidade getDisponibilidade() {return disponibilidade;}
    public void setDisponibilidade(Disponibilidade disponibilidade) {Sessao.disponibilidade = disponibilidade;}


    public Profissional getProfissionalLogado() {
        return profissionalLogado;
    }

    public void logarProfissional(Profissional profissionalLogado) {
        Sessao.profissionalLogado = profissionalLogado;
        nome = profissionalLogado.getName();
        cpfCnpj = profissionalLogado.getCpfcnpj();
        email = profissionalLogado.getEmail();
        telefone = profissionalLogado.getPhone();
        endereco = profissionalLogado.getAddress();
        profissao = profissionalLogado.getProfissao();
        areaAtuacao = profissionalLogado.getAreaAtuacao();
        isProfissional = true;
        isContratante = false;
    }

    
    public static void logoutProfissional() {
        profissionalLogado = null;
    }

    // Contratante ----------------------------------------------------------------------

    private static Contratante contratanteLogado;

    private static String descricao;
    private static String buscando;

    public String getBuscando() {return buscando;}
    public void setBuscando(String buscando) {Sessao.buscando = buscando;}

    public String getDescricao() {return descricao;}
    public void setDescricao(String descricao) {Sessao.descricao = descricao;}

    public Contratante getContratanteLogado() {
        return contratanteLogado;
    }

    public void logarContratante(Contratante contratanteLogado) {
        Sessao.contratanteLogado = contratanteLogado;
        nome = contratanteLogado.getName();
        cpfCnpj = contratanteLogado.getCpfcnpj();
        email = contratanteLogado.getEmail();
        telefone = contratanteLogado.getPhone();
        endereco = contratanteLogado.getAddress();
        descricao = contratanteLogado.getDescricao();
        buscando = contratanteLogado.getBuscando();
        isProfissional = false;
        isContratante = true;
    }

    public void logoutContratante() {
        contratanteLogado = null;
    }

}
