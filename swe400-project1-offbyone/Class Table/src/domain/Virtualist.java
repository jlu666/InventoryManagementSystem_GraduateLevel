package domain;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import domain.power_tool.PowerToolMapper;
import domain.strip_nail.StripNailMapper;
import domain.supertype.DomainMapper;

/**
 * Virtualist is used as virtual proxy and it is used to contains a list of PowerTools or  a list of StripNails which
 * have a relationship with a StripNail or PowerTool.
 *  
 * @author Alan Malloy & Jixiang Lu
 *
 */
public class Virtualist implements List
{
	private List source;
	private DomainMapper loader;
	private int id;
	
	/**
	 * Constructor: create a Virtualist with a id and loader.
	 * @param id to find a list of entities that has a relationship with this id.
	 * @param loader is used to identify the type of entities in the list. If loader is StripNailMapper, 
	 * the type of entities in the list is PowerTool. If loader is PowerToolMapper, the type of entities 
	 * in the list is StripNail. 
	 */
	public Virtualist(int id,DomainMapper loader){
		this.id = id;
		this.loader = loader;
	}
	
	/**
	 * Load the data into list. And return the list.
	 * @return the list of entities.
	 */
	private List load(){
		try{
			if(loader instanceof StripNailMapper){
				return PowerToolMapper.getPowerToolsByStripNail(id);
			}
			else if(loader instanceof PowerToolMapper){
				return StripNailMapper.getStripNailsByPowerTool(id); 
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}catch(ClassNotFoundException ex){
			ex.printStackTrace();
		}
		return new ArrayList<>();
	}
	
	/**
	 * @return list contains data. If the source has been loaded, return list. Otherwise, load the list and return the list. 
	 */
	private List getSource(){
		if(source==null){
			source = load();		
		}
		return source;
	}
	
	/**
	 * 
	 */
	@Override
	public boolean add(Object e)
	{
		return getSource().add(e);
	}

	/**
	 * 
	 */
	@Override
	public void add(int index, Object element)
	{
		getSource().add(index, element);
		
	}

	/**
	 * 
	 */
	@Override
	public boolean addAll(Collection c)
	{
		return getSource().addAll(c);
	}

	/**
	 * 
	 */
	@Override
	public boolean addAll(int index, Collection c)
	{
		return getSource().addAll(index, c);
	}

	/**
	 * 
	 */
	@Override
	public void clear()
	{
		getSource().clear();
		
	}

	/**
	 * 
	 */
	@Override
	public boolean contains(Object o)
	{
		return getSource().contains(o);
	}

	/**
	 * 
	 */
	@Override
	public boolean containsAll(Collection c)
	{
		
		return getSource().containsAll(c);
	}

	/**
	 * 
	 */
	@Override
	public Object get(int index)
	{
		return getSource().get(index);
	}

	/**
	 * 
	 */
	@Override
	public int indexOf(Object o)
	{

		return getSource().indexOf(o);
	}

	/**
	 * 
	 */
	@Override
	public boolean isEmpty()
	{
		return getSource().isEmpty();
	}

	/**
	 * 
	 */
	@Override
	public Iterator iterator()
	{
		return getSource().iterator();
	}

	/**
	 * 
	 */
	@Override
	public int lastIndexOf(Object o)
	{
		return getSource().lastIndexOf(o);
	}

	/**
	 * 
	 */
	@Override
	public ListIterator listIterator()
	{
		return getSource().listIterator();
	}

	/**
	 * 
	 */
	@Override
	public ListIterator listIterator(int index)
	{
		return getSource().listIterator(index) ;
	}

	/**
	 * 
	 */
	@Override
	public boolean remove(Object o)
	{
		return getSource().remove(o);
	}

	/**
	 * 
	 */
	@Override
	public Object remove(int index)
	{
		return getSource().remove(index);
	}

	/**
	 * 
	 */
	@Override
	public boolean removeAll(Collection c)
	{
		return getSource().removeAll(c);
	}

	/**
	 * 
	 */
	@Override
	public boolean retainAll(Collection c)
	{

		return getSource().retainAll(c);
	}

	/**
	 * 
	 */
	@Override
	public Object set(int index, Object element)
	{

		return getSource().set(index, element);
	}

	/**
	 * 
	 */
	@Override
	public int size()
	{

		return getSource().size();
	}

	/**
	 * 
	 */
	@Override
	public List subList(int fromIndex, int toIndex)
	{

		return getSource().subList(fromIndex, toIndex);
	}

	/**
	 * 
	 */
	@Override
	public Object[] toArray()
	{
		return getSource().toArray();
	}

	/**
	 * 
	 */
	@Override
	public Object[] toArray(Object[] a)
	{
		return getSource().toArray(a);
	}

}
