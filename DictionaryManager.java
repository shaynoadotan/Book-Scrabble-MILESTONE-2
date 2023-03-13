package test;


import java.util.HashMap;

public class DictionaryManager
{
    HashMap<String,Dictionary> map=new HashMap<>();


    private static DictionaryManager dm=null;

    public boolean query(String...args)
    {
        String wordToFind =args[args.length-1];
        boolean ans=false;

        for(int i=0; i< args.length-1;i++)
        {
            if(!map.containsKey(args[i]))
                map.put(args[i], new Dictionary(args[i]));
        }

        for(Dictionary dictionary: map.values())
            if(dictionary.query(wordToFind))
                ans=true;
        return ans;
    }

    public boolean challenge(String...args)
    {
        String wordToFind =args[args.length-1];
        boolean ans=false;

        for(int i=0; i< args.length-1;i++)
        {
            if(!map.containsKey(args[i]))
                map.put(args[i], new Dictionary(args[i]));
        }

        for(Dictionary dictionary: map.values())
            if(dictionary.challenge(wordToFind))
                ans=true;


        return ans;
    }

    public int getSize()
    {
       return this.map.size();
    }

    public static DictionaryManager get()
    {
        if (dm==null)
        {
            dm=new DictionaryManager();
            return dm;
        }

        return dm;

    }


}
