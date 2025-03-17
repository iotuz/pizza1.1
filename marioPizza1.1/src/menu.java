import java.util.ArrayList;
import java.util.Collections;

public class menu {
    private ArrayList<String> menu;


    public menu() {
        this.menu = new ArrayList<>();
        menu.add("Peperoni");
        menu.add("Salat Pizza");
        menu.add("Kyllic Pizza");
        menu.add("Bresola Pizza");
        menu.add("Slik pizza");
    }


    public void visMenu() {
        if (menu.isEmpty()) {
            System.out.println("menuen er tomt.");
        } else {
            System.out.println("pizza menu:");
            for (String pizza : menu) {
                System.out.println("- " + pizza);
            }
        }
    }


    public void tilføjPizzaTilMenu (String navn) {
        menu.add(navn);
        System.out.println(navn + " er tilføjet til menuen.");
    }


    public void fjernPizzaFraMenu(String navn) {
        if (menu.remove(navn)) {
            System.out.println(navn + " er fjernet fra menuen.");
        } else {
            System.out.println(navn + " blev ikke fundet i menuen.");
        }
    }


    public boolean søgPizza(String navn) {
        return menu.contains(navn);
    }


    public void sorterMenu() {
        Collections.sort(menu);
        System.out.println("Kataloget er nu sorteret alfabetisk.");
    }
}
