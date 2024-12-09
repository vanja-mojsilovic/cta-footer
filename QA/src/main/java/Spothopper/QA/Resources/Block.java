package Spothopper.QA.Resources;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Block {
	
	// Variables
	private int index;
    private long timestamp;
    private String data;
    private String previousHash;
    private String hash;
    
    // Constructor
    public Block(int index, long timestamp, String data, String previousHash) {
        this.index = index;
        this.timestamp = timestamp;
        this.data = data;
        this.previousHash = previousHash;
        this.hash = calculateHash();
    }
    
    // Methods
    public String calculateHash() {
        String input = index + Long.toString(timestamp) + data + previousHash;
        
        return applySha256(input);
    }
    
    public static String applySha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Getters
    public String getHash() { return hash; }
    public String getPreviousHash() { return previousHash; }
    public String getData() { return data; }
}
