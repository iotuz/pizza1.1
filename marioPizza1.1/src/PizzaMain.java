import java.util.Scanner;

public class PizzaMain {
    private static menu menu = new menu();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean kører = true;

        while (kører) {
            visMenu();
            kører = håndterBrugerValg();
        }
        System.out.println("Programmet afsluttes. Tak for at bruge IKEAKatalog!");
    }


    private static void visMenu() {
        System.out.println(" Pizza Menu ");
        System.out.println("1 - Se Menu");
        System.out.println("2 - Tilføj pizza");
        System.out.println("3 - Fjern pizza");
        System.out.println("4 - Søg efter pizza");
        System.out.println("5 - Sorter pizza");
        System.out.println("6 - Afslut");
        System.out.print("Vælg en mulighed: ");
    }


    private static boolean håndterBrugerValg() {
        int valg = scanner.nextInt();
        scanner.nextLine(); // For at fange linjeskift

        switch (valg) {
            case 1:
                menu.visMenu();
                break;
            case 2:
                System.out.print("Indtast navn på pizza: ");
                String nyPizza = scanner.nextLine();
                menu.tilføjPizzaTilMenu(nyPizza);
                break;
            case 3:
                System.out.print("Indtast navn på pizza, der skal fjernes: ");
                String fjernPizza = scanner.nextLine();
                menu.fjernPizzaFraMenu(fjernPizza);
                break;
            case 4:
                System.out.print("Indtast navn på pizza til søgning: ");
                String søgPizza = scanner.nextLine();
                if (menu.søgPizza(søgPizza)) {
                    System.out.println(søgPizza + " findes i menuen.");
                } else {
                    System.out.println(søgPizza + " blev ikke fundet.");
                }
                break;
            case 5:
                menu.sorterMenu();
                break;
            case 6:
                return false;
            default:
                System.out.println("Ugyldigt valg! Prøv igen.");
        }
        return true;
    }
}
