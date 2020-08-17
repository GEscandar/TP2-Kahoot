package edu.fiuba.algo3;

public class SystemInfo {
    public static String title() {
        return "TP2 KAHOOT";
    }

    public static String course() {
        return "ALGORITMOS Y PROGRAMACION III - FIUBA";
    }

    public static String lista() {
        return
                "Germán Escandar\n"+
            "Javier Zardain\n"+
            "Santiago Guerra\n"+
            "Miroslav Sapunar\n"+
            "Nicolás De Sousa";
    }

    public static String javaVersion() {
        return System.getProperty("java.version");
    }

    public static String javafxVersion() {
        return System.getProperty("javafx.version");
    }

    public static String software() {
        return ("Java " +javaVersion() + "\n" + "JavaFX " + javafxVersion());
    }

}