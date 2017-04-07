package domain.nail;
import domain.fastener.Fastener;


/**
 * It represents Nail.
 *
 * @author Alan Malloy & Jixiang Lu
 *
 */
public class Nail extends Fastener {

    private int numberInBox;

    protected Nail() {
    	super();
    }

    /**
     * @return the number of nails per box of this nail product.
     */
    public int getnumberInBox() {
        return numberInBox;
    }

    /**
     * Sets the number of nails per box in this nail product.
     * @param numberInBox the number of nails per box in this nail product
     */
    public void setnumberInBox(int numberInBox) {
        this.numberInBox = numberInBox;
    }

    /**
     * @return the item in string form.
     */
    public String toString() {
        return "Nail(upc: " + getUpc() + ", manufacturerId: " + getManufacturerId() + ", price: " + getPrice() +", length: "+getLength()+", numberInBox: "+numberInBox+ ")";
    }

}
