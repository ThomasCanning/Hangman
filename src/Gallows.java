public class Gallows {

    //This class has been made obsolete by the GUI

    public static void draw(int incorrectGuesses) {
        switch (incorrectGuesses) {
            case 1 -> {
                System.out.println("|        ");
                System.out.println("|        ");
                System.out.println("|       ");
                System.out.println("|       ");
                System.out.println("|       ");
                System.out.println("|        ");
                System.out.println("|         ");
            }
            case 2 -> {
                System.out.println("|--------");
                System.out.println("|      ||");
                System.out.println("|       ");
                System.out.println("|       ");
                System.out.println("|       ");
                System.out.println("|        ");
                System.out.println("|         ");
            }
            case 3 -> {
                System.out.println("|--------");
                System.out.println("|      ||");
                System.out.println("|     [00]");
                System.out.println("|     [__]");
                System.out.println("|           ");
                System.out.println("|        ");
                System.out.println("|         ");
            }
            case 4 -> {
                System.out.println("|--------");
                System.out.println("|      ||");
                System.out.println("|     [00]");
                System.out.println("|     [__]");
                System.out.println("|       |---");
                System.out.println("|        ");
                System.out.println("|         ");
            }
            case 5 -> {
                System.out.println("|--------");
                System.out.println("|      ||");
                System.out.println("|     [00]");
                System.out.println("|     [__]");
                System.out.println("|   ---||---");
                System.out.println("|        ");
                System.out.println("|          ");
            }
            case 6 -> {
                System.out.println("|--------");
                System.out.println("|      ||");
                System.out.println("|     [00]");
                System.out.println("|     [__]");
                System.out.println("|   ---||---");
                System.out.println("|      | ");
                System.out.println("|     o   ");
            }
            case 7 -> {
                System.out.println("|--------");
                System.out.println("|      ||");
                System.out.println("|     [00]");
                System.out.println("|     [__]");
                System.out.println("|   ---||---");
                System.out.println("|      ||");
                System.out.println("|     o  o");
            }
        }
    }
}
