/*****************************************************
*** Class: TriLinkTree
*** Author: Gabriel Winkler
******************************************************
*** Purpose: This class stores the input from the GUI, validates it,
***         adds it to a tree, sorts the numbers, and allows for searching 
***         and deleting of integers.
******************************************************
*** Changes:December 9th - Created the class
***         Dec. 9th - Changed names, added buttons and functions.
***         Dec. 9th - Fixed if statements in addNumRec class.
***         Dec. 9th - Simplified the print functions.
***         Dec. 9th - Optimized the way the count is implemented.
******************************************************/

/**
 *
 * @author Gabriel
 */
public class TriLinkTree {
    private int Count1;
    private int Count2;
    private TriLinkNode root;

    
/*****************************************************
*** Method: addNum
*** Author: Gabriel Winkler
******************************************************
*** Purpose: This method handles what happens to an incoming integer. 
******************************************************
*** Changes:December 9th - Created the method
******************************************************/
public void addNum(int num)
    {
        if (root == null)
        {
            root = new TriLinkNode();
            root.setV1(num);
        }
        else
            addNumRec(root, num);
    }

/*****************************************************
*** Method: addNumRec
*** Author: Gabriel Winkler
******************************************************
*** Purpose: This method allows an integer to be added to the tree, handling
***         it according to its designated node.
******************************************************
*** Changes:December 9th - Created the method.
***         Dec. 9th - Fixed multiple if statements.
******************************************************/
    private void addNumRec(TriLinkNode current, int num)
    {  
        if (!current.isNullV1() && current.isRemovedV1 && current.getV1() == num)
            current.isRemovedV1 = false;
        else if (!current.isNullV2() && current.isRemovedV2 && current.getV2() == num)
            current.isRemovedV2 = false;
        else if (current.isNullV2())
        {
            if (num >= current.getV1())
            {
                current.setV2(num);
            }
            else
            {
                if (current.low == null)
                {
                    TriLinkNode tmp = new TriLinkNode();
                    tmp.setV1(num);
                    tmp.parent = current;
                    current.low = tmp;     
                }
                else
                {
                    addNumRec(current.low, num);
                }
            }
        }
        else
        {
            if (num <= current.getV1())
            {
                if (current.low == null)
                {
                    TriLinkNode tmp = new TriLinkNode();
                    tmp.setV1(num);
                    tmp.parent = current;
                    current.low = tmp;     
                }
                else
                    addNumRec(current.low, num);
            }
            else if (num > current.getV1() && num < current.getV2())
            {
                if (current.middle == null)
                {
                    TriLinkNode tmp = new TriLinkNode();
                    tmp.setV1(num);
                    tmp.parent = current;
                    current.middle = tmp;     
                }
                else
                    addNumRec(current.middle, num);
            }
            else // must be greater than or equal to V2
            {
                if (current.high == null)
                {
                    TriLinkNode tmp = new TriLinkNode();
                    tmp.setV1(num);
                    tmp.parent = current;
                    current.high = tmp;     
                }
                else
                    addNumRec(current.high, num);
            }
        }         
    }
    
    public boolean find(int key)
    {
        return recFind(root ,key, false);
    }

    public boolean delete(int key)
    {
        return recFind(root ,key, true);
    }
    
/*****************************************************
*** Method: recFind
*** Author: Gabriel Winkler
******************************************************
*** Purpose: This method makes the search possible. 
******************************************************
*** Changes:December 9th - Created the method
******************************************************/
    private boolean recFind(TriLinkNode current, int key, boolean del)
    {
        boolean answer = false;
        if (current != null)
        {
        if (key <= current.getV1())
        {
            if (current.low != null) answer = recFind(current.low,key,del);
        }
        else if (key > current.getV1())
        {
            if (!current.isNullV2() && current.getV2() > key) 
            {
                if (current.middle != null) answer = recFind(current.middle,key,del);
            }
            if (!current.isNullV2() && current.getV2() <= key) 
            {
                if (current.high != null) answer = recFind(current.high,key,del);
            }  
            
        }
        
        if (current.getV1() == key && !current.isRemovedV1) 
        {
           current.isRemovedV1 = del; //delete if we are deleting
           answer = true; 
        }
            
        
        if (current.getV2() == key && !current.isRemovedV2) 
        {
           current.isRemovedV2 = del; //delete if we are deleting
           answer = true; 
        }      
        }         
        return answer;
    }
    
    
    
    public String printTri()
    {   
        return recPrint(root);
    }

    public String CountNode()
    {   
        Count1 = 0;
        Count2 = 0;
        return recCountNode(root);
    }
    
/*****************************************************
*** Method: recPrint
*** Author: Gabriel Winkler
******************************************************
*** Purpose: This method recursively prints the tree in order from the
***         left-most leaf to the right-most. This puts the printed integers
***         in numerical order from smallest to largest.
******************************************************
*** Changes:December 9th - Created the method
******************************************************/
    private String recPrint(TriLinkNode current)
    {
        String answer = "";

        if (current != null)
        {
            answer += recPrint(current.low);
            if (!current.isRemovedV1 && !current.isNullV1()) answer += current.getV1() + ", ";
            answer += recPrint(current.middle);
            if (!current.isRemovedV2 && !current.isNullV2()) answer += current.getV2() + ", ";
            answer += recPrint(current.high);
        }
        
        return answer;
    }
    
    private String recCountNode(TriLinkNode current)
    {
        

        if (current != null)
        {
            if (current.isNullV2())
                Count1++;
            else
                Count2++;
            
            recCountNode(current.low);
            recCountNode(current.middle);
            recCountNode(current.high);
        }

        
        return "\n\nSingle V nodes: " + Count1 
                + "\nDouble V nodes: " + Count2;
    }
    
}
