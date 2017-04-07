package datasource;

import java.sql.SQLException;

/**
 * RowGateway super class.
 * 
 * @author Alan Malloy & Jixiang Lu
 *
 */
public abstract class RowGateway {
	
	private int id;
	
	/**
	 * Constructor: the identify number will be set at -1.
	 */
	public RowGateway() {
		this.id = -1;
	}
	
	/**
	 * Constructor with specific id.
	 * @param id the specific id.
	 */
	protected RowGateway(int id) {
		this.id = id;
	}
	
	/**
	 * Gets the identify number.
	 * @return the identify number
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * If the identify number is -1, insert the entity into database. Otherwise, update the entity.
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	public void save() throws ClassNotFoundException, SQLException {
		if(this.shouldInsert()) {
			this.setId(this.insert());
		} else {
			this.update();
		}
	}
	
	/**
	 * Sets the identify number.
	 * @param id id.
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Return whether the entity should be insert into database or update to the database.
	 * @return true if the entity should be inserted into database. Otherwise, return false.
	 */
	private boolean shouldInsert() {
		return this.id < 1;
	}
	
	/**
	 * Inserts this entity into database.
	 */
	protected abstract int insert() throws ClassNotFoundException, SQLException;
	
	/**
	 * Updates this entity to database.
	 */
	protected abstract void update() throws ClassNotFoundException, SQLException;
	
}
