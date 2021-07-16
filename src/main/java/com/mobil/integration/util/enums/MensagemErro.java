package com.mobil.integration.util.enums;

public enum MensagemErro {

    ERRO_INESPERADO("Ocorreu um erro inesperado.");

    private final String mensagem;

    MensagemErro(final String mensagem) {
        this.mensagem = mensagem;
    }

    public String mensagem() {
        return mensagem;
    }
}
