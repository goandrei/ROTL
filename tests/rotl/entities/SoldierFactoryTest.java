package rotl.entities;

import org.junit.Test;
import rotl.utilities.XMLParser;
import static org.junit.Assert.*;

public class SoldierFactoryTest {

    private SoldierFactory factory = new SoldierFactory();
    private final String soldiersPath = "resources\\entities_info\\soldiers.xml";

    @Test (expected = IllegalArgumentException.class)
    public void getMisnamedSoldier() {
        XMLParser.parseSoldiersInfo(soldiersPath);
        Soldier misnamed = factory.getSoldier(SoldierType.valueOf("notasoldiertype"));
    }

    @Test
    public void getFighter() {
        XMLParser.parseSoldiersInfo(soldiersPath);
        Soldier fighter = factory.getSoldier(SoldierType.FIGHTER);
        assertTrue(fighter instanceof Fighter);
    }

    @Test
    public void getWarrior() {
        XMLParser.parseSoldiersInfo(soldiersPath);
        Soldier warrior = factory.getSoldier(SoldierType.WARRIOR);
        assertTrue(warrior instanceof Warrior);
    }

    @Test
    public void getDefender() {
        XMLParser.parseSoldiersInfo(soldiersPath);
        Soldier defender = factory.getSoldier(SoldierType.DEFENDER);
        assertTrue(defender instanceof Defender);
    }
}