package domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import datasource.Database;
import domain.power_tool.PowerTool;
import domain.power_tool.PowerToolMapper;
import domain.strip_nail.StripNail;
import domain.strip_nail.StripNailMapper;

/**
 * 
 * 
 * @author Alan Malloy & Jixiang Lu
 *
 */
public class TestVirtualList
{
	/**
	 * 
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
    @Before
    public void setup() throws ClassNotFoundException, SQLException {
        Database.beginTransaction();
    }

    /**
	 * 
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
    @After
    public void teardown() throws ClassNotFoundException, SQLException {
        Database.rollbackTransaction();
        Database.resetAutoIncrement();
    }

    /**
	 * 
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	@Test
	public void testGetCorrectList() throws ClassNotFoundException, SQLException
	{
		StripNail nail1 = StripNailMapper.create("sn_upc", 7, 90, 1.8, 25);
    	PowerTool tool1 = PowerToolMapper.create("pt_upc1", 21, 42, "power_tool_description", true);
    	PowerTool tool2 = PowerToolMapper.create("pt_upc2", 21, 42, "power_tool_description", true);
    	PowerTool tool3 = PowerToolMapper.create("pt_upc3", 21, 42, "power_tool_description", true);
    	nail1.addPowerToolRelation(tool1);
    	nail1.addPowerToolRelation(tool2);
    	nail1.addPowerToolRelation(tool3);
    	Virtualist vl = new Virtualist(nail1.getId(),new StripNailMapper());
    	assertTrue(((PowerTool)vl.get(0)).equals(tool1));
    	assertTrue(((PowerTool)vl.get(1)).equals(tool2));
    	assertTrue(((PowerTool)vl.get(2)).equals(tool3));
	}
	
	/**
	 * 
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	@Test
	public void testOverrideMethod() throws ClassNotFoundException, SQLException{
		StripNail nail1 = StripNailMapper.create("sn_upc", 7, 90, 1.8, 25);
    	PowerTool tool1 = PowerToolMapper.create("pt_upc1", 21, 42, "power_tool_description", true);
    	PowerTool tool2 = PowerToolMapper.create("pt_upc2", 21, 42, "power_tool_description", true);
    	PowerTool tool3 = PowerToolMapper.create("pt_upc3", 21, 42, "power_tool_description", true);
    	nail1.addPowerToolRelation(tool1);
    	nail1.addPowerToolRelation(tool2);
    	nail1.addPowerToolRelation(tool3);
    	Virtualist vl = new Virtualist(nail1.getId(),new StripNailMapper());

    	//add(index,object)
    	vl.add(1, tool3);
    	assertEquals(vl.get(1),tool3);
    	
    	//addAll(collection)
    	ArrayList<Object> ls = new ArrayList<>();
    	ls.add(tool2);
    	vl.addAll(ls);
    	assertEquals(vl.get(vl.size()-1),tool2);
    	
    	//addAll(index, collection)
    	vl.addAll(0, ls);
    	assertEquals(vl.get(0),tool2);
    	
    	//containsAll
    	assertTrue(vl.containsAll(ls));
    	
    	//indexOf(object)
    	assertEquals(vl.indexOf(tool2),0);
    	//lastIndexOf
    	assertEquals(vl.lastIndexOf(tool2),5);
    	
    	//remove(index)
    	vl.remove(5);
    	assertEquals(5, vl.size());
    	
    	//remove(object)
    	vl.remove(tool2);
    	assertFalse(vl.contains(tool2));
    	
    	//removeAll();
    	vl.add(tool2);
    	vl.addAll(0, ls);
    	vl.removeAll(ls);
    	assertFalse(vl.contains(tool2));
    	
    	//retainAll();
    	vl.add(tool2);
    	vl.retainAll(ls);
    	assertEquals(vl.size(),1);
    	
    	//set();
    	vl.set(0, tool1);
    	assertEquals(vl.get(0),tool1);
    	
    	//sublist();
    	List ls2 = vl.subList(0, 1);
    	assertEquals(ls2.get(0),tool1);
    	
    	//toArray()
    	Object ob = vl.toArray()[0];
    	assertEquals(ob,tool1);
    	
    	//toArray(Object[])
    	Object[] oblist = new Object[4];
    	vl.toArray(oblist);
    	assertEquals(oblist[0],tool1);
    	
    	//iterator()
    	Iterator it = vl.iterator();
    	assertTrue(it.hasNext());
    	assertEquals(it.next(),tool1);
    	
    	//listIterator
    	vl.add(tool2);
    	ListIterator lit = vl.listIterator();
    	assertEquals(lit.next(),tool1);
    	assertEquals(lit.next(),tool2);
    	//listIterator(index)
    	lit = vl.listIterator(1);
    	assertEquals(lit.next(),tool2);
    	
    	//clear();
    	vl.clear();
    	assertEquals(vl.size(),0);
    	//isEmpty();
    	assertTrue(vl.isEmpty());
    	
    	
    	

	}


}
