package rotl.entities;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class WarriorTest {

    @Test
    public void takeDamage() {
        Warrior control = new Warrior(100, 100, 0, 0, 0, 0);
        control.takeDamage(50);
        assertTrue(control.getLife() == 75 && control.getArmor() == 75);
    }

}