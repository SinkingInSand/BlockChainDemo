package blockChainDemo;

import java.util.Date;

public class Block {
    
	//This Block's Current Hash Value
	public String curHash;
	
	//Previous Block's Hash Value
	public String prevHash;
	
	//Data to store information
	private String data;
    private long timestamp;
    
    private int nonce;
    
    //Default Constructor 
    public Block() {
    	
    }
    //Constructor 1
    public Block(String data, String prevHash) {
    	this.prevHash = prevHash;
        this.data = data;
        this.timestamp = new Date().getTime();
        this.curHash = calculateHash();
        
    }
    //Constructor 2
    public Block(String curHash, String prevHash, String data) {
        this.curHash = curHash;
        this.prevHash = prevHash;
        this.data = data;
    }
    
    //Apply SHA-256 to calculate hash value
    public String calculateHash() {
    	String hash = StringUtil.applySha256(prevHash + Long.toString(timestamp) + Integer.toString(nonce) + data);
    	return hash;
    }
    
    //Solve Hash Puzzle
    public void mineBlock(int difficulty) {
        //Create a string with difficulty * "0"
        String target = new String(new char[difficulty]).replace('\0', '0'); 
        while(!curHash.substring( 0, difficulty).equals(target)) {
            nonce ++;
            curHash = calculateHash();
        }
    }


}
