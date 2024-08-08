package com.bigcorp.booking.model;

public enum Prix {
    PAS_CHER(1, 30),
    MOYEN(31, 60),
    CHER(61, Integer.MAX_VALUE);

    private final int prixMin;
    private final int prixMax;

    // Constructeur
    Prix(int prixMin, int prixMax) {
        this.prixMin = prixMin;
        this.prixMax = prixMax;
    }

    // Getter pour prixMin
    public int getPrixMin() {
        return prixMin;
    }

    // Getter pour prixMax
    public int getPrixMax() {
        return prixMax;
    }

    // Méthode pour vérifier à quelle catégorie appartient un prix donné
    public static Prix fromPrix(int prix) {
        for (Prix p : Prix.values()) {
            if (prix >= p.getPrixMin() && prix <= p.getPrixMax()) {
                return p;
            }
        }
        throw new IllegalArgumentException("Prix non valide : " + prix);
    }
}
