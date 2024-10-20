package com.desenvolvimento.domains.enums;

public enum Conservacao {

    EXCELENTE(1, "EXCELENTE"), BOM(2, "BOM"), MARCASDEUSO(3, "MARCASDEUSO"), GASTO(4, "GASTO");
    private Integer id;
    private String descricao;

    Conservacao(Integer id,String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public static Conservacao toEnum(Integer id){
        if (id==null) return null;
        for (Conservacao c : Conservacao.values()){ //Para cada valor c presente em Conservacao.values(), faça o bloco de código que segue
            if (id.equals(c.getId())){ // se o id do valor c for igual ao id que está sendo comparado
                return c; //retorne o valor correspondente de Conservacao
            }
        }

        throw new IllegalArgumentException("Conservação inválida");
    }
}
