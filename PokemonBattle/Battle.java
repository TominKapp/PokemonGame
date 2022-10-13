public class Battle {

  public static boolean canContinue(Pokemon p) {
    if (p.getCurrentHP() <= 0) {
      return false;
    }
    else {
      return true;
    }
  }
  public static Move useRandomMove(Pokemon p) {
    int x = 0;
    for (int i = 0; i < 4; i++) {
      if (p.getMoves()[i] != null) {
        x++;
      }
    }
    return p.getMoves()[(int)(Math.random()*x)];
  }
  

  public static int goesFirst(Pokemon a, Pokemon b, Move a_1, Move b_1) {
    if (a_1.getPriority() > b_1.getPriority()) {
      return 1;
    }
    else if (b_1.getPriority() > a_1.getPriority()) {
      return 2;
    }
    else {
      if (a.getTotalStats()[5] > b.getTotalStats()[5]) {
        return 1;
      }
      else if (a.getTotalStats()[5] > b.getTotalStats()[5]) {
        return 2;
      }
      else {
        if (Math.random() *2 > 1) return 1;
        else return 2;
      }
    }
  }
  public static int attack(Pokemon a, Pokemon r, Move m) {
    if (m.getDamageClassID() == 1) return 0;
    //modifiers
    double modifier = effectiveBoost(r, m) * ((int)(Math.random() * 16) + 85) / 100.0;
    modifier *= typeBoost(a, m);
    //if (crit) modifier *= 2;

    //change in stats
    double stat = 0;
    if (m.getDamageClassID() == 2) stat = ((double)(a.getStats()[1]))/r.getStats()[2];
    if (m.getDamageClassID() == 3) stat = ((double)(a.getStats()[3]))/r.getStats()[4];

    //formula
    double dmg = (( ((2.0*a.getLevel()/5)+2) *stat*m.getPower()/50) + 2) * modifier;

    return (int)dmg;
  }

  public static double typeBoost(Pokemon a, Move m) {
    for (Type t : a.getTypes()) {
      if (m.getType().equals(t)) return 1.5;
    }
    return 1;
  }
  public static double effectiveBoost(Pokemon r, Move m) {
    double multiplier = 1;
    for (Type t : r.getTypes()) {
      if(m.getType().equals(Type.Bug)) {
        if (t.equals(Type.Psychic) || t.equals(Type.Grass) || t.equals(Type.Dark)) {
          multiplier *= 2;
        }
        else if (t.equals(Type.Fighting) || t.equals(Type.Flying) || t.equals(Type.Poison) || t.equals(Type.Ghost) || t.equals(Type.Steel) || t.equals(Type.Fire) || t.equals(Type.Fairy)) {
          multiplier *= 0.5;
        }
      }
      else if(m.getType().equals(Type.Dark)) {
        if (t.equals(Type.Ghost) || t.equals(Type.Psychic)) {
          multiplier *= 2;
        }
        else if (t.equals(Type.Fighting) || t.equals(Type.Dark) || t.equals(Type.Fairy)) {
          multiplier *= 0.5;
        }
      }
      else if(m.getType().equals(Type.Dragon)) {
        if (t.equals(Type.Dragon)) {
          multiplier *= 2;
        }
        else if (t.equals(Type.Steel)) {
          multiplier *= 0.5;
        }
        else if (t.equals(Type.Fairy)) {
          multiplier *= 0;
        }
      }
      else if(m.getType().equals(Type.Electric)) {
        if (t.equals(Type.Flying) || t.equals(Type.Water)) {
          multiplier *= 2;
        }
        else if (t.equals(Type.Grass) || t.equals(Type.Electric) || t.equals(Type.Dragon)) {
          multiplier *= 0.5;
        }
        else if (t.equals(Type.Ground)) {
          multiplier *= 0;
        }
      }
      else if(m.getType().equals(Type.Fighting)) {
        if (t.equals(Type.Normal) || t.equals(Type.Rock) || t.equals(Type.Steel) || t.equals(Type.Ice) || t.equals(Type.Dark)) {
          multiplier *= 2;
        }
        else if (t.equals(Type.Flying) || t.equals(Type.Poison) || t.equals(Type.Bug) || t.equals(Type.Psychic) || t.equals(Type.Fairy)) {
          multiplier *= 0.5;
        }
        else if (t.equals(Type.Ghost)) {
          multiplier *= 0;
        }
      }
      else if(m.getType().equals(Type.Fire)) {
        if (t.equals(Type.Bug) || t.equals(Type.Steel) || t.equals(Type.Grass) || t.equals(Type.Ice)) {
          multiplier *= 2;
        }
        else if (t.equals(Type.Rock) || t.equals(Type.Fire) || t.equals(Type.Water) || t.equals(Type.Dragon)) {
          multiplier *= 0.5;
        }
      }
      else if(m.getType().equals(Type.Flying)) {
        if (t.equals(Type.Fighting) || t.equals(Type.Bug) || t.equals(Type.Grass)) {
          multiplier *= 2;
        }
        else if (t.equals(Type.Rock) || t.equals(Type.Steel) || t.equals(Type.Electric)) {
          multiplier *= 0.5;
        }
      }
      else if(m.getType().equals(Type.Ghost)) {
        if (t.equals(Type.Ghost) || t.equals(Type.Psychic)) {
          multiplier *= 2;
        }
        else if (t.equals(Type.Dark)) {
          multiplier *= 0.5;
        }
        else if (t.equals(Type.Normal)) {
          multiplier *= 0;
        }
      }
      else if(m.getType().equals(Type.Grass)) {
        if (t.equals(Type.Ground) || t.equals(Type.Rock) || t.equals(Type.Water)) {
          multiplier *= 2;
        }
        else if (t.equals(Type.Flying) || t.equals(Type.Poison) || t.equals(Type.Bug) || t.equals(Type.Steel) || t.equals(Type.Fire) || t.equals(Type.Grass) || t.equals(Type.Dragon)) {
          multiplier *= 0.5;
        }
      }
      else if(m.getType().equals(Type.Ground)) {
        if (t.equals(Type.Poison) || t.equals(Type.Rock) || t.equals(Type.Steel) || t.equals(Type.Fire) || t.equals(Type.Electric)) {
          multiplier *= 2;
        }
        else if (t.equals(Type.Bug) || t.equals(Type.Grass)) {
          multiplier *= 0.5;
        }
      }
      else if(m.getType().equals(Type.Ice)) {
        if (t.equals(Type.Flying) || t.equals(Type.Ground) || t.equals(Type.Grass) || t.equals(Type.Dragon)) {
          multiplier *= 2;
        }
        else if (t.equals(Type.Steel) || t.equals(Type.Fire) || t.equals(Type.Water) || t.equals(Type.Ice)) {
          multiplier *= 0.5;
        }
      }
      else if(m.getType().equals(Type.Normal)) {
        if (t.equals(Type.Rock) || t.equals(Type.Steel)) {
          multiplier *= 0.5;
        }
        if (t.equals(Type.Ghost)) {
          multiplier *= 0;
        }
      }
      else if(m.getType().equals(Type.Poison)) {
        if (t.equals(Type.Grass) || t.equals(Type.Fairy)) {
          multiplier *= 2;
        }
        else if (t.equals(Type.Poison) || t.equals(Type.Ground) || t.equals(Type.Rock) || t.equals(Type.Ghost)) {
          multiplier *= 0.5;
        }
        else if (t.equals(Type.Steel)) {
          multiplier *= 0;
        }
      }
      else if(m.getType().equals(Type.Psychic)) {
        if (t.equals(Type.Fighting) || t.equals(Type.Poison)) {
          multiplier *= 2;
        }
        else if (t.equals(Type.Steel) || t.equals(Type.Psychic)) {
          multiplier *= 0.5;
        }
        else if (t.equals(Type.Dark)) {
          multiplier *= 0;
        }
      }
      else if(m.getType().equals(Type.Rock)) {
        if (t.equals(Type.Flying) || t.equals(Type.Bug) || t.equals(Type.Fire) || t.equals(Type.Ice)) {
          multiplier *= 2;
        }
        else if (t.equals(Type.Fighting) || t.equals(Type.Ground) || t.equals(Type.Steel)) {
          multiplier *= 0.5;
        }
      }
      else if(m.getType().equals(Type.Steel)) {
        if (t.equals(Type.Rock) || t.equals(Type.Ice) || t.equals(Type.Fairy)) {
          multiplier *= 2;
        }
        else if (t.equals(Type.Steel) || t.equals(Type.Fire) || t.equals(Type.Water) || t.equals(Type.Electric)) {
          multiplier *= 0.5;
        }
      }
      else if(m.getType().equals(Type.Water)) {
        if (t.equals(Type.Ground) || t.equals(Type.Rock) || t.equals(Type.Fire)) {
          multiplier *= 2;
        }
        else if (t.equals(Type.Water) || t.equals(Type.Grass) || t.equals(Type.Dragon)) {
          multiplier *= 0.5;
        }
      }
    }
    return multiplier;
  }

}