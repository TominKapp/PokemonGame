import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    Pokedex dex = new Pokedex();
    
    System.out.print("Enter your Pokemon ID (<151):");
    int yourPoke = s.nextInt();
    if (yourPoke > 151) yourPoke = 151;
    
    System.out.print("Enter enemy Pokemon ID (<151):");
    int enemyPoke = s.nextInt();
    if (enemyPoke > 151) enemyPoke = 151;
    
    Pokemon a = dex.createPokemon(yourPoke, 50);
    Pokemon b = dex.createPokemon(enemyPoke, 50);
    
    MyFrame mf = new MyFrame(a, b);
    mf.display();
    /*
    BattleGUI oop = new BattleGUI(0, 0);
    oop.startBattle(a, b);
    */
    

  }
}
