package com.example.ktmblockchain.chain;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.logging.Level;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Block {

    private String hash;

    private String previousHash;

    private LocalDateTime localDateTime;

    public Block(String previousHash, Object data){
        this.previousHash = previousHash;
        localDateTime = LocalDateTime.now();
        hash = generateHash(previousHash, data, localDateTime);
    }

    private String generateHash(String previousHash, Object data, LocalDateTime localDateTime) {
        return SHA256Helper.generateHash(previousHash + data.toString() + localDateTime.toString());
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}