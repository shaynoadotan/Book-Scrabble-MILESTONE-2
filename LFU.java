package test;


import java.util.Collections;
import java.util.LinkedHashMap;

public class LFU implements CacheReplacementPolicy{

    LinkedHashMap<String,Integer> words;

    public LFU()
    {
        this.words=new LinkedHashMap<>();
    }
    @Override
    public void add(String word)
    {
        Integer amount=words.remove(word);
        if(amount == null)/**initialize - seen for the first time **/
        {
            words.put(word,1);
        }
        else /**adding the word and increase the amount **/
        {
            words.put(word,amount+1);
        }
    }

    @Override
    public String remove() {
        String[] tmp=new String[2];
        words.forEach((k,v) ->
        {
            if(tmp[0]==null)/**initialize - seen for the first time **/
            {
                tmp[0]=k;
                tmp[1]=String.valueOf(v);

            }
            else/**Check if this word appears less than the min **/
            {
                if(Integer.parseInt(tmp[1])>v) //update the min seen word
                {
                    tmp[0]=k;
                    tmp[1]=String.valueOf(v);
                }
            }
        });
        words.remove(tmp[0]);
        return tmp[0];
    }
}
