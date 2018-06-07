package rotl.entities;

import org.junit.Test;
import static org.junit.Assert.*;

public class DefenderTest {

    @Test
    public void takeDamage() {
        Defender control = new Defender(100, 100, 0, 0, 0, 0);
        control.takeDamage(50);
        assertTrue(control.getLife() == 83 && control.getArmor() == 67);
    }
}