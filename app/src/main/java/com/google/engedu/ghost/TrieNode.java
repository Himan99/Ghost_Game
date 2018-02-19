/* Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.engedu.ghost;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


public class TrieNode {
    private HashMap<String, TrieNode> children;
    private boolean isWord;

    public TrieNode() {
        children = new HashMap<>();
        isWord = false;
    }

    public void add(String s,TrieNode n,int i) {
        if(i==s.length())
            return ;
        String c=String.valueOf(s.charAt(i));

        if(!n.children.containsKey(c))
        {
            n.children.put(c,new TrieNode());
            if(i==s.length()-1)
                n.children.get(c).isWord=true;
        }
        add(s,n.children.get(c),i+1);
    }

    public boolean isWord(String s) {
        return SearchNode(s) != null;
    }
    private TrieNode SearchNode(String s)
    {
        TrieNode n=this;
        String c;
        for (int i=0;i<s.length();i++)
        {
            c=String.valueOf(s.charAt(i));
            if(!n.children.containsKey(c))
                return null;
            n=n.children.get(c);
        }
        return n;
    }

    public String getAnyWordStartingWith(String s) {
        return null;
    }

    public String getGoodWordStartingWith(String s) {
        Random random = new Random();
        String x = s;
        TrieNode temp = SearchNode(s);
        if (temp == null){
            return "noWord";
        }
        if(temp.isWord)
        {
            return "sameAsPrefix";
        }
        // get a random word
        ArrayList<String> charsNoWord = new ArrayList<>();
        ArrayList<String> charsWord = new ArrayList<>();
        String c;

        while (true){
            charsNoWord.clear();
            charsWord.clear();
            for (String ch: temp.children.keySet()){
                if (temp.children.get(ch).isWord){
                    charsWord.add(ch);
                } else {
                    charsNoWord.add(ch);
                }
            }
            System.out.println("------>"+charsNoWord+" "+charsWord);
            if (charsNoWord.size() == 0){
                if(charsWord.size() == 0){
                    return "sameAsPrefix";
                }
                s += charsWord.get( random.nextInt(charsWord.size()) );
                break;
            } else {
                c = charsNoWord.get( random.nextInt(charsNoWord.size()) );
                s += c;
                temp = temp.children.get(c);
            }
        }
        if(x.equals(s)){
            return "sameAsPrefix";
        }else{
            return s;
        }
    }
    public String getGoodWordStartingWith (String s,int whoEndsFirst)
    {
        TrieNode temp = SearchNode(s);
        if (temp == null){
            return "noWord";
        }
        else if(temp.isWord==true)
        {
            return "sameAsPrefix";
        }
        else
        {
            return rec(temp,s.length(),whoEndsFirst);
        }
    }
    private String rec (TrieNode n,int len,int WhoEndsFirst)
    {
        String s=null;
        String t=null;
        for(String c : n.children.keySet())
        {
            if(n.children.get(c).isWord&&len%2==WhoEndsFirst)
                return c;
            t=rec(n.children.get(c),len+1,WhoEndsFirst);
            if(t!=null)
                s+=c+t;
        }
        return s;
    }
}
