package datasource;

import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * It is a row gateway which is used to manage the entity in the Nail table.
 * 
 * @author Alan Malloy & Jixiang Lu
 *
 */
public class NailGateway extends FastenerGateway{

	private int  numberInBox;
	private static final String INSERT_SQL="INSERT INTO nail(id,  number_in_box) VALUES(?, ?)";
	private static final String UPDATE_SQL="UPDATE nail SET number_in_box=? WHERE id=?";
	
	/**
	 * Constructor without any parameters.
	 */
	public NailGateway() {
		super();
	}
	
	/**
	 * Constructor with all fields except TypeCode.
	 * 
	 * @param id
	 * @param upc
	 * @param manufacturerId
	 * @param price
	 * @param length
	 * @param numberInBox
	 */
	protected NailGateway(int id, String upc, int manufacturerId, int price,double length,int numberInBox) {
		super(id, upc, manufacturerId, price,length);
		this.numberInBox = numberInBox;
	}
	
	/**
	 * Gets the TypeCode of NailGateway.
	 * @return  the TypeCode of NailGateway
	 */
	public TypeCode getTypeCode() {
		return TypeCode.Nail;
	}

	/**
	 * Gets the number in box.
	 * @return  the number in box
	 */
	public int getNumberInBox() {
		return numberInBox;
	}

	/**
	 * Sets the number in box
	 * @param numberInBox  the number in box
	 */
	public void setNumberInBox(int numberInBox) {
		this.numberInBox = numberInBox;
	}
	
	/**
	 * Inserts this entity into database.
	 */
	protected int insert() throws ClassNotFoundException, SQLException {
		int id = super.insert();
		PreparedStatement statement = Database.prepareStatement(INSERT_SQL);
		
		statement.setInt(1, id);
        statement.setInt(2, this.getNumberInBox());
        statement.executeUpdate();
        statement.close();
        
        return id;
	}
	
	/**
	 * Updates this entity to database.
	 */
	protected void update() throws ClassNotFoundException, SQLException {
		super.update();
		PreparedStatement statement = Database.prepareStatement(UPDATE_SQL);
		statement.setInt(1, this.getNumberInBox());
        statement.setInt(2, this.getId());
        statement.executeUpdate();
        statement.close();
	}
}
