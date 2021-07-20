package com.mobil.util.enums;

public enum MensagemErro {

    ERRO_INESPERADO("Ocorreu um erro inesperado."),
    ERRO_SALVAR_BANCO("Ocorreu um erro ao salvar."),
    ERRO_CONVERTER_ITINERARIOIN_ITINERARIO("Erro ao converter itinerarioIn em itinerario."),
    ERRO_CONVERTER_ITINERARIO_ITINERARIO_OUT("Erro ao converter itinerario em itinerarioOut."),
    ERRO_CONVERTER_COORDENADAS_IN_COORDENADAS("Erro ao converter coordenadas in em coordenadas."),
    ERRO_CONVERTER_JSONNODE_ITINERARIO_IN("O itinerario em JsonNode não pode ser convertido."),
    ERRO_CONVERTER_LINHAS_IN_LINHAS("A lista de LinhaIn não pode ser convertida em uma lista de Linha.");

    private final String mensagem;

    MensagemErro(final String mensagem) {
        this.mensagem = mensagem;
    }

    public String mensagem() {
        return mensagem;
    }
}
