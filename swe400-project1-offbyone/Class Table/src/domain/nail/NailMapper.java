package domain.nail;
import java.sql.SQLException;

import datasource.NailFinder;
import datasource.NailGateway;
import datasource.RowGateway;
import domain.fastener.FastenerMapper;
import domain.supertype.DomainObject;

/**
 * NailMapper provide a mapping between Nail object and database. It is used to manage the Nail Object
 * saving and loading.
 * 
 * @author Alan Malloy & Jixiang Lu
 *
 */
public class NailMapper extends FastenerMapper {


	/**
	 * Finds the nail that uses this id from database.
	 * @param id the Identify number.
	 * @return Nail use this id. If there is no Nail that uses this id, return null.
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	public static Nail find(int id) throws ClassNotFoundException, SQLException{
		NailGateway gateway = NailFinder.find(id);
		return restore(gateway);
	}
	
	/**
	 * Creates a new Nail entity into database.
	 * @param upc upc
	 * @param manufacturerId manufactureId
	 * @param price price
	 * @param length length
	 * @param numberInBox numberInBox
	 * @return  the Nail has been created.
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	public static Nail create(String upc, int manufacturerId, int price, double length, int numberInBox) throws ClassNotFoundException, SQLException{
		Nail nail = new Nail();
		nail.setUpc(upc);
		nail.setManufacturerId(manufacturerId);
		nail.setPrice(price);
		nail.setLength(length);
		nail.setnumberInBox(numberInBox);
		store(nail);
		return nail;
	}
	
	/**
	 * Loads the data into entity from a gateway.
	 * @param gateway the specific gateway
	 * @return the entity with data.
	 */
	public static Nail restore(NailGateway gateway) {
		Nail nail = new Nail();
		NailMapper mapper = new NailMapper();
		mapper.load(nail, gateway);
		return nail;
	}
	
	/**
	 * Stores the data into gateway from an entity.
	 * @param nail the entity
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	public static void store(Nail nail) throws ClassNotFoundException, SQLException{
		NailGateway gateway = new NailGateway();
		NailMapper mapper = new NailMapper();
		mapper.save(nail, gateway);
		gateway.save();
		mapper.load(nail, gateway);
	}
	
    /**
     * Pulls values from the Gateway and sets them on the domain object.
     */
    protected void load(DomainObject object, RowGateway gateway) {
        super.load(object, gateway);
        ((Nail)object).setnumberInBox(((NailGateway)gateway).getNumberInBox());
    }

    /**
     * Pulls values from the domain object and saves them in the database.
     */
    protected void save(DomainObject object,RowGateway gateway){
        super.save(object,gateway);
        ((NailGateway)gateway).setNumberInBox(((Nail)object).getnumberInBox());
    }

}
