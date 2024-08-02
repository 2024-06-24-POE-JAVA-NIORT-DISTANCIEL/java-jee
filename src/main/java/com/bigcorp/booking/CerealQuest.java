package com.bigcorp.booking;

import java.util.Scanner;

public class CerealQuest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean hasBowl = false;
        boolean hasCereal = false;
        boolean hasMilk = false;

        System.out.println("Bienvenue dans CerealQuest !");
        System.out.println("Préférence de céréales :");
        System.out.println("1. Croustillant");
        System.out.println("2. Bien dosé");
        System.out.print("Choisissez une option : ");
        int preference = scanner.nextInt();
        scanner.nextLine(); // Consomme la nouvelle ligne

        while (true) {
            System.out.println("\nQue voulez-vous faire ?");
            System.out.println("1. Prendre un bol");
            System.out.println("2. Verser les céréales");
            System.out.println("3. Verser le lait");
            System.out.print("Choisissez une option : ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consomme la nouvelle ligne

            switch (choice) {
                case 1:
                    hasBowl = true;
                    System.out.println("Vous avez pris un bol.");
                    break;
                case 2:
                    if (!hasBowl) {
                        System.out.println("Game Over: Vous devez prendre un bol d'abord.");
                        return;
                    }
                    hasCereal = true;
                    System.out.println("Vous avez versé les céréales.");
                    break;
                case 3:
                    if (!hasBowl) {
                        System.out.println("Game Over: Vous devez prendre un bol d'abord.");
                        return;
                    }
                    hasMilk = true;
                    System.out.println("Vous avez versé le lait.");
                    break;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
                    continue;
            }

            if (hasBowl && hasMilk && !hasCereal) {
                if (preference == 1) {
                    System.out.println("Game Over: Vos céréales ne sont plus croustillants.");
                } else {
                    System.out.println("Game Over: Il est impossible de bien doser en mettant le lait avant les céréales.");
                }
                return;
            }

            if (hasBowl && hasCereal && hasMilk) {
                System.out.println("You Win: vous pouvez déguster votre déjeuner bien dosé aux céréales croustillants.");
                return;
            }
        }
    }
}
