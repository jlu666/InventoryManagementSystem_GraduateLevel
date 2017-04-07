package domain.fastener;
import datasource.FastenerGateway;
import datasource.RowGateway;
import domain.inventory_item.InventoryItemMapper;
import domain.supertype.DomainObject;

/**
 * FastenerMapper provide a mapping between Fastener object and database. It is used to manage the Fastener Object
 * saving and loading.
 * 
 * @author Alan Malloy & Jixiang Lu
 *
 */
public class FastenerMapper extends InventoryItemMapper {


    /**
     * Pulls values from the Gateway and sets them on the domain object.
     */
    protected void load(DomainObject object, RowGateway gateway){
        super.load(object, gateway);
        ((Fastener)object).setLength(((FastenerGateway)gateway).getLength());
    }

    /**
     * Pulls values from the domain object and saves them in the database.
     */
    protected void save(DomainObject object,RowGateway gateway){
		super.save(object, gateway);
		((FastenerGateway)gateway).setLength(((Fastener)object).getLength());
    }

}
