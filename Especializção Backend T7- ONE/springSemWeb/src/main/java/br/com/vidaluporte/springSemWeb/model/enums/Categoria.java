package br.com.vidaluporte.springSemWeb.model.enums;

public enum Categoria {
    ACAO("Action", "Ação"),
    ROMACE("Romance", "Romance"),
    COMEDIA("Comendy", "Comédia"),
    DRAMA("Drama", "Drama"),
    CRIME("Crime", "Crime");

    private String categoriaOmdb;
    private String categoriaPortugues;

    Categoria(String categoriaOmdb, String categoriaPortugues){
        this.categoriaPortugues = categoriaPortugues;
        this.categoriaOmdb = categoriaOmdb;
    }

    public static Categoria formString(String text) {
        for (Categoria categoria : Categoria.values())
            if (categoria.categoriaOmdb.equalsIgnoreCase(text)) {

                return categoria;
            }
        throw new IllegalArgumentException("Nenhuma catgoria encontrada para a string.");
    }

    public static Categoria formPortugues(String text) {
        for (Categoria categoria : Categoria.values())
            if (categoria.categoriaPortugues.equalsIgnoreCase(text)) {

                return categoria;
            }
        throw new IllegalArgumentException("Nenhuma catgoria encontrada para a string.");
    }

}
