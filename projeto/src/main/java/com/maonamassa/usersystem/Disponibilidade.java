package com.maonamassa.usersystem;

public enum Disponibilidade {

    DISPONIVEL_INTEGRAL("Disponível - Integral"),
    DISPONIVEL_DIURNO("Disponível - Diurno (8h às 12h)"),
    DISPONIVEL_VESPERTINO("Disponível - Vespertino (13h às 17h)"),
    DISPONIVEL_NOTURNO("Disponível - Noturno (18h às 22h)"),
    EM_PROJETO("Em Projeto"),
    INDISPONIVEL("Indisponível");

    private final String descricao;

    Disponibilidade(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
