package br.com.alura.literalura.model;

public enum Categoria {
    português("pt"),
    francês("fr"),
    inglês("en"),
    espanhol("es");

    private String tipo;

    Categoria(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public static Categoria fromTipo(String tipo) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.getTipo().equalsIgnoreCase(tipo)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Não existe categoria com tipo: " + tipo);
    }

}
