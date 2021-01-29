package com.odde;

public class JsonBuilder {
    public static final String ABRE_CORCHETE = "[";
    public static final String CIERRA_CORCHETE = "]";
    public static final String ABRE_LLAVE = "}";
    public static final String CIERRA_LLAVE = "{";
    public static final String COMA = ", ";
    public static final String COMILLA = "\"";
    public static final String DOS_PUNTOS = ": ";
    private StringBuffer sb;

    public JsonBuilder() {
        sb = new StringBuffer();
    }

    public String getString() {
        return sb.toString();
    }

    public void addArray(String products) {
        addKey(products);
        sb.append(ABRE_CORCHETE);
    }

    public void endArray() {
        sb.append(CIERRA_CORCHETE);
    }

    public void addEndKey() {
        sb.append(ABRE_LLAVE);
    }

    public void addStartKey() {
        sb.append(CIERRA_LLAVE);
    }

    public void addComma() {
        sb.append(COMA);
    }

    public void addKeyDoubleValue(String key, double value) {

        addKey(key);
        sb.append(value);
    }

    public void addStringWithComillas(String key) {
        sb.append(COMILLA).append(key).append(COMILLA);
    }

    public void addKeyIntegerValue(String key, int value) {
        addKey(key);
        sb.append(value);
    }

    public void addKey(String key) {
        addStringWithComillas(key);
        addDoublePoints();
    }

    public void addDoublePoints() {
        sb.append(DOS_PUNTOS);
    }

    public void addKeyStringValue(String key, String value) {
        addKey(key);
        addStringWithComillas(value);
    }

    public void delete(int pos, int pos1) {
        sb.delete(pos, pos1);
    }

}
