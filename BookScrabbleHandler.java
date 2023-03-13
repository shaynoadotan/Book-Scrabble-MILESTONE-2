package test;


import java.io.*;
import java.util.Scanner;

public class BookScrabbleHandler implements ClientHandler{

    BufferedReader in;
    PrintWriter out;

    @Override
    public void handleClient(InputStream inFromclient, OutputStream outToClient) {
    try {
        in = new BufferedReader(new InputStreamReader(inFromclient));
        out= new PrintWriter(outToClient,true);


        String line;
        String[] arrStrings;
        boolean ans;
        line=in.readLine();
        arrStrings=line.split(",");
        String[] strings=new String[arrStrings.length-1];
        for(int i=0;i<strings.length;i++)
            strings[i]=arrStrings[i+1];

            if(arrStrings[0].equals("Q"))
            {
                DictionaryManager dm=DictionaryManager.get();


                ans=dm.query(strings);
                if(ans)
                    out.println("true");
                else
                    out.println("false");
            }
            if(arrStrings[0].equals("C"))
            {
                DictionaryManager dm=DictionaryManager.get();

                ans=dm.challenge(strings);
                if(ans)
                   out.println("true");
                else
                   out.println("false");
            }


    }catch (IOException e)
    {e.printStackTrace();}
    }

    @Override
    public void close() {
        try {
            out.close();
            in.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
