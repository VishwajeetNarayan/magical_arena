import java.util.Random;

public class MagicalArena {
    private Player playerA;
    private Player playerB;
    private Random dice;

    public MagicalArena(Player playerA, Player playerB) {
        this.playerA = playerA;
        this.playerB = playerB;
        this.dice = new Random();
    }

    public void fight() {
        while (playerA.isAlive() && playerB.isAlive()) {
            Player attacker = playerA.getHealth() <= playerB.getHealth() ? playerA : playerB;
            Player defender = attacker == playerA ? playerB : playerA;

            int attackRoll = rollDice();
            int defenseRoll = rollDice();

            int attackDamage = attacker.getAttack() * attackRoll;
            int defenseStrength = defender.getStrength() * defenseRoll;
            int damageDealt = Math.max(0, attackDamage - defenseStrength);

            defender.reduceHealth(damageDealt);

            System.out.printf("%s attacks %s: Attack Roll = %d, Defense Roll = %d, Damage Dealt = %d, Defender Health = %d%n",
                attacker == playerA ? "Player A" : "Player B",
                defender == playerA ? "Player A" : "Player B",
                attackRoll, defenseRoll, damageDealt, defender.getHealth());
        }

        if (playerA.isAlive()) {
            System.out.println("Player A wins!");
        } else {
            System.out.println("Player B wins!");
        }
    }

    private int rollDice() {
        return dice.nextInt(6) + 1;
    }

    public static void main(String[] args) {
        Player playerA = new Player(50, 5, 10);
        Player playerB = new Player(100, 10, 5);
        MagicalArena arena = new MagicalArena(playerA, playerB);
        arena.fight();
    }
}
