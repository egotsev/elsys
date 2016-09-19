package org.elsys.equalsAndHashCode;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

public class EqualsAndHashCodeTest {

	@Test
	public void testOperatorEquals() {
		Name n1 = new Name("pesho", "ivanov");
		Name n2 = new Name("pesho", "ivanov");
		
		assertFalse(n1 == n2);
		assertTrue(n1.equals(n2));
	}
	
	@Test
	public void testStringEquals() {
		assertTrue("pesho".equals("pesho"));
		assertTrue("pesho" == "pesho");
		String pesho = new String("pesho");
		assertFalse(pesho == "pesho");
		assertTrue(pesho.equals("pesho"));
	}
	
	@Test
	public void testListContains() {
		ArrayList<Name> names = new ArrayList<>();
		names.add(new Name("mitko", "petrov"));
		names.add(new Name("mitko", "petroff"));
		names.add(new Name("mitko", null));
		
		assertTrue(names.contains(new Name("mitko", null)));
	}
	
	@Test
	public void testHashCode() {
		Name name1 = new Name("mitko", "petrov");
		Name name2 = new Name("mitko", "petrov");
		
		assertEquals(name1, name2);
		assertEquals(name1.hashCode(), name2.hashCode());
	}
	
	@Test
	public void testSetAddObject() {
		Set<Name> names = new HashSet<>();
		Name name1 = new Name("mitko", "petrov");
		names.add(name1);
		names.add(new Name("mitko", "petroff"));
		names.add(new Name("mitko", null));
		
		assertTrue(names.contains(new Name("mitko", null)));
		assertTrue(names.contains(new Name("mitko", "petrov")));
		assertTrue(names.contains(new Name("mitko", "petroff")));
	}

}
