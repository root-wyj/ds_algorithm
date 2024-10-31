package com.wyj.ds_algorithm.smartUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 字典树
 */
public class TrieNode {

    private Map<Character, TrieNode> children;

    private boolean ifWordEnd;

    public TrieNode() {
        children = new HashMap<>();
    }

    public void insert(String str) {
        if (str == null || str.length() == 0) {
            return;
        }
        TrieNode next = this;
        for (int i=0; i<str.length(); i++) {
            TrieNode trieNode = next.children.get(str.charAt(i));
            if (trieNode == null) {
                trieNode = new TrieNode();
                next.children.put(str.charAt(i), trieNode);
            }
            next = trieNode;
        }
        next.ifWordEnd = true;
    }

    public boolean search(String str) {
        if (str == null) {
            return false;
        }
        TrieNode next = this;
        for (int i=0; i<str.length(); i++) {
            TrieNode trieNode = next.children.get(str.charAt(i));
            if (trieNode == null) {
                return false;
            }
            next = trieNode;
        }
        return next.ifWordEnd;
    }

    public static void main(String[] args) {
        TrieNode trieNode = new TrieNode();
        trieNode.insert("abc");
        trieNode.insert("xyz");
        trieNode.insert("aaa");
        trieNode.insert("abdc");
        System.out.println("abc:" + trieNode.search("abc"));
        System.out.println("afc:" + trieNode.search("afc"));
    }

}
