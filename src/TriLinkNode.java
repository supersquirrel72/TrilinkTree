/*****************************************************
*** Class: TriLinkNode
*** Author: Gabriel Winkler
******************************************************
*** Purpose: This class creates all of the variables to be 
***          used in the "Trilink" tree.
******************************************************
*** Changes:December 9th - Created the class
***         Dec. 9th - Fixed all the little red squigglies...  
***
******************************************************/

/**
 *
 * @author Gabriel
 */
public class TriLinkNode {
    private boolean isNullV1 = true;
    private boolean isNullV2 = true;
    public boolean isRemovedV1 = false;
    public boolean isRemovedV2 = false;
    public TriLinkNode parent;
    public TriLinkNode low;
    public TriLinkNode middle;
    public TriLinkNode high;
    private int V1;
    private int V2;
    
    public int getV1() {
        return V1;
    }

    public void setV1(int V1) {
        this.V1 = V1;
        isNullV1 = false;
        isRemovedV1 = false;
    }

    public int getV2() {
        return V2;
    }

    public void setV2(int V2) {
        this.V2 = V2;
        isNullV2 = false;
        isRemovedV2 = false;
    }

    public boolean isNullV1() {
        return isNullV1;
    }

    public boolean isNullV2() {
        return isNullV2;
    }
    
    
}
