package domain.supertype;

import datasource.RowGateway;

/**
 * It provides a mapping between  DomainObject and database.
 * 
 * @author Alan Malloy & Jixiang Lu
 *
 */
public abstract class DomainMapper {
	
	/**
     * Pulls values from the Gateway and sets them on the domain object.
     */
	protected void load(DomainObject object, RowGateway gateway) {
		object.setId(gateway.getId());
	}
	
	/**
     * Pulls values from the domain object and saves them in the database.
     */
	protected void save(DomainObject object, RowGateway gateway) {
		gateway.setId(object.getId());
	}

}
