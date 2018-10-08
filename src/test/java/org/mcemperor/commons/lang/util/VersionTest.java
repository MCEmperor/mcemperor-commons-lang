package org.mcemperor.commons.lang.util;

import org.mcemperor.commons.lang.util.Version;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Test for {@code nl.mcemperor.commons.lang.util.Version}.
 * 
 * @author Maurits de Jong
 */
public class VersionTest {

	@Test
	public void testVersionCompareTo() {
		Version v20 = new Version(2, 0);
		Version v200 = new Version(2, 0, 0);
		Version v201 = new Version(2, 0, 1);
		
		assertTrue(new Version(1, 8, 0).compareTo(new Version(2)) < 0);
		
		assertEquals(0, v20.compareTo(v200));
		assertEquals(-1, v200.compareTo(v201));
		assertEquals(1, v201.compareTo(v200));
	}
	
	@Test
	public void testUpgrade() {
		Version downgraded = new Version(3, 5, 8);
		Version upgradedFirstComponent = new Version(4);
		Version upgradedSecondComponent = new Version(3, 6);
		Version upgradedThirdComponent = new Version(3, 5, 9);
		Version upgradedFourthComponent = new Version(3, 5, 8, 1);
		
		Version upgradedFaultyFourthComponent = new Version(3, 5, 8, 2);
		
		assertEquals(upgradedFirstComponent, downgraded.upgrade(0));
		assertEquals(upgradedSecondComponent, downgraded.upgrade(1));
		assertEquals(upgradedThirdComponent, downgraded.upgrade(2));
		assertEquals(upgradedFourthComponent, downgraded.upgrade(3));
		
		assertNotEquals(upgradedFaultyFourthComponent, downgraded.upgrade(3));
	}
	
	@Test
	public void testUpgradeDeeperLevel() {
		Version v1 = new Version(1, 8, 0);
		Version v2 = v1.upgrade(3);
		
		assertEquals(new Version(1, 8, 0, 1), v2);
	}
	
	@Test
	public void testUpgradeShallowerLevel() {
		Version v1 = new Version(1, 8, 5, 2);
		Version v2 = v1.upgrade(1);
		
		assertEquals(new Version(1, 9), v2);
	}
}
