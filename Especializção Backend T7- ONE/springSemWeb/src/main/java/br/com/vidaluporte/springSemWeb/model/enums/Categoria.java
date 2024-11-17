package br.com.vidaluporte.springSemWeb.model.enums;

public enum Categoria {
    ACAO("Action"),
    ROMACE("Romance"),
    COMEDIA("Comendy"),
    DRAMA("Drama"),
    CRIME("Crime");

    private String categoriaOmdb;

    Categoria(String categoriaOmdb){
        this.categoriaOmdb = categoriaOmdb;
    }

    public static Categoria formString(String text) {
        for (Categoria categoria : Categoria.values())
            if (categoria.categoriaOmdb.equalsIgnoreCase(text)) {

                return categoria;
            }
        throw new IllegalArgumentException("Nenhuma catgoria encontrada para a string.");
    }

}
