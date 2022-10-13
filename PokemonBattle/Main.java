class Main {
  public static void main(String[] args) {
    Pokedex dex = new Pokedex();
    
    Pokemon a = dex.createPokemon(18, 50);
    Pokemon b = dex.createPokemon(20, 50);
    
    MyFrame mf = new MyFrame(a, b);
    mf.display();
    /*
    BattleGUI oop = new BattleGUI(0, 0);
    oop.startBattle(a, b);
    */
    

  }
}