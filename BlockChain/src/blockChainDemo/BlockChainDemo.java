package blockChainDemo;

import java.util.*;

public class BlockChainDemo {
	
    public static ArrayList<Block> blockChain = new ArrayList();

    public static int difficulty = 5;

    public static void main(String[] args) {
        blockChain.add(new Block("The first block", "0"));
        blockChain.get(0).mineBlock(difficulty);

        blockChain.add(new Block("The second block", blockChain.get(blockChain.size() - 1).curHash));
        blockChain.get(1).mineBlock(difficulty);

        blockChain.add(new Block("The third block", blockChain.get(blockChain.size() - 1).curHash));
        blockChain.get(2).mineBlock(difficulty);
        
        //Invalid block for error test
        //blockChain.add(new Block("The fourth block", blockChain.get(blockChain.size() - 1).prevHash));
        //blockChain.get(3).mineBlock(difficulty);
        
        System.out.println("Validity: " + isChainValid());
        System.out.println(JsonUtil.toJson(blockChain));
        
        
    }
    

    public static Boolean isChainValid(){

        Block currentBlock;
        Block previousBlock;
        
        boolean flag = true;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

   
        for(int i=1;i<blockChain.size();i++){
            currentBlock = blockChain.get(i);
            previousBlock = blockChain.get(i-1);
 
            if(!currentBlock.curHash.equals(currentBlock.calculateHash())){
                System.out.println("The current hash is unmathched");
                flag=false;
            }
    
            if(!previousBlock.curHash.equals(currentBlock.prevHash)){
                System.out.println("The previous hash is unmatched");
                flag=false;
            }

  
            if(!currentBlock.curHash.substring( 0, difficulty).equals(hashTarget)) {
                System.out.println("This block hasn't been mined yet");
                flag=false;
            }
        }

        return flag;
    }


}
