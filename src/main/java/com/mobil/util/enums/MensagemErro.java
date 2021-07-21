package com.mobil.util.enums;

public enum MensagemErro {

    ERRO_INESPERADO("Ocorreu um erro inesperado."),
    ERRO_SALVAR_BANCO("Ocorreu um erro ao salvar."),
    ERRO_CONVERTER_ITINERARIOIN_ITINERARIO("Erro ao converter itinerarioIn em itinerario."),
    ERRO_CONVERTER_ITINERARIO_ITINERARIO_OUT("Erro ao converter itinerario em itinerarioOut."),
    ERRO_CONVERTER_JSONNODE_ITINERARIO_IN("O itinerario em JsonNode não pode ser convertido."),
    ERRO_BUSCA_LINHA("Linha não encontrada."),
    ERRO_ID_LINHA_NAO_INFORMADO("Id linha não informado"),
    ERRO_CODIGO_NAO_INFORMADO("Codigo não informado."),
    ERRO_BUSCA_ITINERARIO("Itinerario não encontrado.");

    private final String mensagem;

    MensagemErro(final String mensagem) {
        this.mensagem = mensagem;
    }

    public String mensagem() {
        return mensagem;
    }
}
