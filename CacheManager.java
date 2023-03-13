package test;


import java.util.LinkedHashSet;

public class CacheManager
{
	LinkedHashSet<String> wordsInCache;
    CacheReplacementPolicy policy;
    int cacheSize;
    int currentSize;

    /**CTOR **/
    public CacheManager(int size,CacheReplacementPolicy policy)
    {
        this.wordsInCache=new LinkedHashSet<>();
        this.policy=policy;
        this.cacheSize=size;
        this.currentSize=0;
    }

    public boolean query(String word)
    {
        return this.wordsInCache.contains(word);
    }

    public void add(String word)
    {
        if(this.currentSize==this.cacheSize)/**Full Cache **/
        {
            String wordToRemove=policy.remove();
            this.policy.add(word);
            this.wordsInCache.remove(wordToRemove);
            this.wordsInCache.add(word);
        }
        else
        {
            this.policy.add(word);
            this.wordsInCache.add(word);
            this.currentSize++;
        }
    }

}
