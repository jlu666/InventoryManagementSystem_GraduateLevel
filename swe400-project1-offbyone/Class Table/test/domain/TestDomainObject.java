package domain;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test DomaiObject
 * @author Alan Malloy & Jixiang Lu
 *
 */
public class TestDomainObject
{

	/**
	 * Tests Initialization.
	 */
	@Test
	public void testInitialization()
	{
		MockDomainObject ob = new MockDomainObject();
		assertEquals(ob.getId(),-1);
	}

	/**
	 * Tests Equals Methods.
	 */
	@Test
	public void testEqualsMethod(){
		MockDomainObject ob = new MockDomainObject();
		assertEquals(ob.getId(),-1);
		MockDomainObject ob2 = new MockDomainObject();
		assertFalse(ob.equals(ob2));
		ob.setId(2);
		assertFalse(ob.equals(ob2));
		assertFalse(ob2.equals(ob));
		ob2.setId(3);
		assertFalse(ob2.equals(ob));
		ob2.setId(2);
		assertTrue(ob.equals(ob2));
	}

}
