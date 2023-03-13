package test;


import java.util.HashMap;
import java.util.LinkedHashSet;

public class LRU implements CacheReplacementPolicy{

LinkedHashSet<String> words;

public LRU()
{
    this.words=new LinkedHashSet<>();
}
    @Override
    public void add(String word) {
    this.words.remove(word);
    this.words.add(word);

    }

    @Override
    public String remove() {

    String word = words.iterator().next();
    words.remove(word);
    return word;
    }
}
