package rotl.entities;

import org.junit.Test;
import static org.junit.Assert.*;

public class FighterTest {

    @Test
    public void takeDamage() {
        Fighter control = new Fighter(100, 100, 0, 0, 0 , 0);
        control.takeDamage(50);
        assertTrue(control.getLife() == 75 && control.getArmor() == 75);
    }
}