/*
  Handles Evolutions, Pokemon <-> Move identification
*/
public class Pokedex
{
  private Move[] movepool;
  private Pokemon[] pokedex;
  public Pokedex() {
    pokedex = BackendReader.createPokedex();
    movepool = BackendReader.createMovePool();
  }

  public Pokemon createPokemon(int id, int level) {
    Pokemon p = pokedex[id - 1];
    p.newPokeSetup(level);
    int numMoves = 0;
    Move[] moves = new Move[4];
    for (int i = p.getLevel() - 1; i >= 0; i--) {
      if (p.getLevelupMoves()[i] != null) {
        for(int j = 0; j < p.getLevelupMoves()[i].size(); j++) {
          if (numMoves == 4) {
            i = 0;
            break;
          }
          moves[numMoves] = integerToMove(p.getLevelupMoves()[i].get(j));
          numMoves++;
        }
      }
      
    }
    p.setMoves(moves);
    return p;
  }
  
  public Move integerToMove(Integer x) {
    return movepool[x.intValue() - 1];
  }
}