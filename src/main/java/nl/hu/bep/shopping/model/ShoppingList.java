package nl.hu.bep.shopping.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShoppingList implements NamedObject {
    private String name;
    private Shopper owner;
    private static List<ShoppingList> allLists = new ArrayList<>();
    private List<Item> ListItems;

    public ShoppingList(String nm, Shopper owner) {
        this.name = nm;
        this.owner = owner;
        allLists.add(this);
        ListItems = new ArrayList<>();
    }

    public void clearItems(){
        ListItems.clear();
    }


    public List<Item> getListItems() {
        return Collections.unmodifiableList(ListItems);
    }

    public boolean addItem(Product p, int amount) {
        Item newItem = new Item(p, amount);
        if (!ListItems.contains(newItem)) {
            return ListItems.add(newItem);
        }
        return ListItems.get(ListItems.indexOf(newItem)).addAmount(amount);
    }
    public Item getItem(String name) {
        return ListItems.stream().filter(e -> e.getName().equals(name)).findFirst().orElse(null);
    }

    @JsonProperty("id")
    public String getName() {
        return name;
    }
    @JsonProperty("owner")
    public Shopper getOwner() {
        return owner;
    }
    @JsonIgnore
    public static List<ShoppingList> getAllLists() {
        return Collections.unmodifiableList(allLists);
    }
}
