package test;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Dictionary {
    String[] files;
    CacheManager LRU; //Existing words
    CacheManager LFU;  //UnExisting words
    BloomFilter bloomFilter;


    public void loadFile(String file)
    {
        try{
            Stream<String> stringStream= Files.lines(Paths.get(file));
            stringStream.forEach(line -> {
                Stream.of(line.split("\\s+")).forEach(word->bloomFilter.add(word));
            });
            stringStream.close();
        }catch (IOException e)
        {
            throw new RuntimeException(e);
        }

    }
    public Dictionary(String...algs)
    {
        files=algs;
        LRU=new CacheManager(400,new LRU());  //Existing words
        LFU=new CacheManager(100, new LFU());  //UnExisting words
        bloomFilter=new BloomFilter(256,"MD5", "SHA1");
        for(String file : algs)
        {
            loadFile(file);
        }
    }

    public boolean query(String string)
    {
        if(LRU.query(string))
        {
            return true;
        }
        if(LFU.query(string))
        {
            return false;
        }

        boolean returnedBloomFilter=bloomFilter.contains(string);
        if(returnedBloomFilter)
        {
            LRU.add(string);
        }
        else
        {
            LFU.add(string);
        }
        return returnedBloomFilter;
    }

    public boolean challenge (String string)
    {
        boolean search;
        try{
            search=IOSearcher.search(string,files);
        }catch (RuntimeException e){
            return false;
        }
        if(search)
        {
            LRU.add(string);
        }
        else {
            LFU.add(string);
        }
        return search;
    }

}
