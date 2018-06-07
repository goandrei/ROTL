package rotl.entities;

import org.junit.Test;
import static org.junit.Assert.*;

public class SoldierTypeTest {

    @Test
    public void fighterTest() {
        String fighter = SoldierType.FIGHTER.getSoldierName();
        assertEquals("Fighter", fighter);
    }

    @Test
    public void warriorTest() {
        String warrior = SoldierType.WARRIOR.getSoldierName();
        assertEquals("Warrior", warrior);
    }

    @Test
    public void defenderTest() {
        String defender = SoldierType.DEFENDER.getSoldierName();
        assertEquals("Defender", defender);
    }
}