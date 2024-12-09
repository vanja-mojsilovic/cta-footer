package Spothopper.QA.Resources;

import Spothopper.QA.Resources.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Blockchain {
	private List<Block> chain;

    public Blockchain() {
        chain = new ArrayList<>();
        chain.add(createGenesisBlock());
    }

    private Block createGenesisBlock() {
        return new Block(0, new Date().getTime(), "Genesis Block", "0");
    }

    public void addBlock(String data) {
        Block previousBlock = chain.get(chain.size() - 1);
        Block newBlock = new Block(chain.size(), new Date().getTime(), data, previousBlock.getHash());
        chain.add(newBlock);
    }

    public void printBlockchain() {
        for (Block block : chain) {
            System.out.println("Index: " + chain.indexOf(block));
            System.out.println("Data: " + block.getData());
            System.out.println("Hash: " + block.getHash());
            System.out.println("Previous Hash: " + block.getPreviousHash());
            System.out.println("--------------");
        }
    }
    
    public List<Block> getChain() {
        return chain;
    }
}
