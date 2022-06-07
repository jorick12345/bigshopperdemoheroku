package nl.hu.bep.shopping.model;

public class BoodschappenlijstRequest {
    public String getName() {
        return name;
    }

    public Shopper getOwner() {
        return owner;
    }

    private String name;
    private Shopper owner;
}
