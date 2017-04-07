package domain.strip_nail;
import java.sql.SQLException;
import java.util.ArrayList;

import datasource.RowGateway;
import datasource.StripNailFinder;
import datasource.StripNailGateway;
import domain.Virtualist;
import domain.fastener.FastenerMapper;
import domain.supertype.DomainObject;

/**
 * StripNailMapper provide a mapping between StripNail object and database. It is used to manage the StripNail Object
 * saving and loading.
 * 
 * @author Alan Malloy & Jixiang Lu
 *
 */
public class StripNailMapper extends FastenerMapper {


	/**
	 * Finds the StripNail that uses this id from database.
	 * @param id the Identify number.
	 * @return StripNail uses this id. If there is no StripNail that uses this id, return null.
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	public static StripNail find(int id) throws ClassNotFoundException, SQLException{
		StripNailGateway gateway = StripNailFinder.find(id);
		return restore(gateway);
	}
	
	/**
	 * Creates a new StripNail entity into database.
	 * @param upc upc
	 * @param manufacturerId manufacturerId
	 * @param price price
	 * @param length length
	 * @param numberInStrip numberInStrip
	 * @return the StripNail has been created
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	public static StripNail create(String upc, int manufacturerId, int price, double length, int numberInStrip) throws ClassNotFoundException, SQLException{
		StripNail nail = new StripNail();
		nail.setUpc(upc);
		nail.setManufacturerId(manufacturerId);
		nail.setPrice(price);
		nail.setLength(length);
		nail.setNumberinStrip(numberInStrip);
		store(nail);
		return nail;
	}
	
	/**
	 * Loads the data into entity from a gateway.
	 * @param gateway the specific gateway
	 * @return the entity with data.
	 */
	public static StripNail restore(StripNailGateway gateway) {
		StripNail stripNail = new StripNail();
		StripNailMapper mapper = new StripNailMapper();
		mapper.load(stripNail, gateway);
		
		return stripNail;
	}
	
	/**
	 * Stores the data into gateway from an entity.
	 * @param nail the entity
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	public static void store(StripNail nail) throws ClassNotFoundException, SQLException{
		StripNailGateway gateway = new StripNailGateway();
		StripNailMapper mapper = new StripNailMapper();
		mapper.save(nail, gateway);
		gateway.save();
		mapper.load(nail, gateway);
	}
	
	/**
	 *  Gets all the StripNails related to this PowerTool's id.
	 * @param id the PowerTool's id
	 * @return  all the StripNails related to this PowerTool's id.
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws SQLException SQLException
	 */
	public static ArrayList<StripNail> getStripNailsByPowerTool(int id) throws ClassNotFoundException, SQLException{
		ArrayList<StripNailGateway> gateways = StripNailFinder.findByPowerTool(id);
		ArrayList<StripNail> nails = new ArrayList<>();
		StripNailMapper mapper = new StripNailMapper();
		for(StripNailGateway gateway:gateways){
			StripNail nail = new StripNail();
			mapper.load(nail, gateway);
			nails.add(nail);
		}
		return nails;
	}

	
    /**
     * Pulls values from the Gateway and sets them on the domain object.
     */
    @SuppressWarnings("unchecked")
	protected void load(DomainObject object, RowGateway gateway) {
        super.load(object, gateway);
        ((StripNail)object).setNumberinStrip(((StripNailGateway)gateway).getNumberInStrip());
        ((StripNail)object).setPowerTools(new Virtualist(gateway.getId(),new StripNailMapper()));
    }

    /**
     * Pulls values from the domain object and saves them in the database.
     */
    protected void save(DomainObject object,RowGateway gateway){
        super.save(object,gateway);
        ((StripNailGateway)gateway).setNumberInStrip(((StripNail)object).getNumberinStrip());
    }


}
