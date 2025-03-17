import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

class PizzaShop {
    private final Map<Integer, String> pizzaMenu;
    private final Map<Integer, Double> pizzaPrices;
    private final Map<LocalTime, List<String>> orderTimes;
    private double totalCost;
    private int pizzaId;
    private final Scanner scanner;

    public PizzaShop() {
        pizzaMenu = new HashMap<>();
        pizzaPrices = new HashMap<>();
        orderTimes = new HashMap<>();
        scanner = new Scanner(System.in);
        totalCost = 0.0;
        pizzaId = 1;
        initializeMenu();
    }

    private void initializeMenu() {
        addPizzaToMenu("Slik", 74.99);
        addPizzaToMenu("Pepperoni", 69.99);
        addPizzaToMenu("Vegansk", 79.99);
        addPizzaToMenu("Hawaii", 59.99);
    }

    private void addPizzaToMenu(String name, double price) {
        pizzaMenu.put(pizzaId, name);
        pizzaPrices.put(pizzaId, price);
        pizzaId++;
    }

    public void startOrdering() {
        while (true) {
            displayMainMenu();
            int option = getUserInput();
            if (option == 1) {
                addPizzasToOrder();
            } else if (option == 2) {
                addNewPizzaToMenu();
            } else if (option == 3) {
                displayOrderTimes();
            } else {
                System.out.println("Ugyldigt valg. Indtast 1, 2 eller 3.");
            }
        }
    }

    private void displayMainMenu() {
        System.out.println("Vælg en mulighed:");
        System.out.println("1. Bestil pizzaer");
        System.out.println("2. Tilføj en ny pizza");
        System.out.println("3. Se ordrer og afhentningstider");
    }

    private int getUserInput() {
        String input = scanner.nextLine();
        int number = -1;

        if (input.matches("\\d+")) {
            number = Integer.parseInt(input);
        } else {
            System.out.println("Ugyldigt input. Indtast et tal.");
        }

        return number;
    }

    private void addPizzasToOrder() {
        List<String> order = new ArrayList<>();
        double orderCost = 0.0;

        while (true) {
            displayPizzaMenu();
            int choice = getUserInput();
            if (choice == 0) break;

            if (pizzaMenu.containsKey(choice)) {
                order.add(pizzaMenu.get(choice));
                orderCost += pizzaPrices.get(choice);
                System.out.println(pizzaMenu.get(choice) + " er tilføjet til din ordre.");
            } else {
                System.out.println("Ugyldigt valg. Vælg et gyldigt pizzanummer.");
            }
        }

        if (!order.isEmpty()) {
            finalizeOrder(order, orderCost);
        }
    }

    private void displayPizzaMenu() {
        System.out.println("Vælg en pizza at tilføje (eller indtast 0 for at afslutte bestillingen):");
        for (Map.Entry<Integer, String> entry : pizzaMenu.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue() + " - kr" + pizzaPrices.get(entry.getKey()));
        }
    }

    private void finalizeOrder(List<String> order, double orderCost) {
        totalCost += orderCost;
        LocalTime pickupTime = LocalTime.now().plusMinutes(30);
        orderTimes.putIfAbsent(pickupTime, new ArrayList<>());
        orderTimes.get(pickupTime).addAll(order);
        System.out.println("Din ordre er blevet afgivet. Total pris: kr" + totalCost);
        System.out.println("Dine pizzaer vil være klar til afhentning kl: " + pickupTime);
    }

    private void addNewPizzaToMenu() {
        System.out.println("Indtast navnet på den nye pizza:");
        String newPizzaName = scanner.nextLine();

        System.out.println("Indtast prisen på " + newPizzaName + ":");
        String input = scanner.nextLine();
        double newPizzaPrice = -1;

        if (input.matches("\\d+(\\.\\d+)?")) {
            newPizzaPrice = Double.parseDouble(input);
        }

        if (newPizzaPrice > 0) {
            addPizzaToMenu(newPizzaName, newPizzaPrice);
            System.out.println(newPizzaName + " er blevet tilføjet til menuen til kr" + newPizzaPrice);
        } else {
            System.out.println("Ugyldig pris. Indtast et gyldigt tal.");
        }
    }

    private void displayOrderTimes() {
        if (orderTimes.isEmpty()) {
            System.out.println("Ingen aktive ordrer.");
        } else {
            System.out.println("Ordrer klar til afhentning på følgende tidspunkter:");
            for (Map.Entry<LocalTime, List<String>> entry : orderTimes.entrySet()) {
                System.out.println(entry.getKey() + " - " + entry.getValue());
            }
        }
    }
}

public class PizzaShopRegister {
    public static void main(String[] args) {
        PizzaShop pizzaShop = new PizzaShop();
        pizzaShop.startOrdering();
    }
}
