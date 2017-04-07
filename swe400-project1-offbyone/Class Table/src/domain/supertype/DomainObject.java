package domain.supertype;

/**
 * It represent DomainObject.
 * 
 * @author Alan Malloy & Jixiang Lu
 *
 */
public abstract class DomainObject {

    private int id;

    protected DomainObject() {
    	this.id=-1;
    }

    /**
     * @return int The object id.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Sets the object id to id.
     * @param id The id to assign to the object.
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * 
     * @param o2 another DomainObject
     * @return whether the DomainObject has the same with another DomainObject.
     */
    public boolean equals(DomainObject o2) {
    	if(this.getId()!=-1&&o2.getId()!=-1&&this.id == o2.getId())
    		return true;
    	return false;
    }
    

}
