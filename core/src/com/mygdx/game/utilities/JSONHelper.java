package com.mygdx.game.utilities;

import org.json.simple.JSONObject;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Set;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONHelper {

    String defaultFolderLocation = System.getProperty("user.home") + "\\";
    String levelFileLocation = defaultFolderLocation+ "level.json";
    String coinFileLocation = defaultFolderLocation+ "coin.json";
    String myFileLocation = "";

    JSONObject obj = new JSONObject();
    //boolean fileAvailable = false;

    public String read(String attrType, String key, boolean printObjValues) {
        JSONParser parser = new JSONParser();
        String retValue=new String();
        String ZERO ="0";

        if(attrType==null)
            myFileLocation = levelFileLocation;
        else if(attrType.equals("LEVEL"))
            myFileLocation = levelFileLocation;
        else if(attrType.equals("COIN"))
            myFileLocation = coinFileLocation;
        else
            myFileLocation = levelFileLocation;

        try {
            Object inpFileObject = parser.parse(new FileReader(myFileLocation));
            //parsing the JSON string inside the file that we created earlier.
            JSONObject jsonObject = (JSONObject) inpFileObject;
            retValue = (String) jsonObject.get(key);

            if(printObjValues)
                System.out.println("Searched for :"+key+", found:"+retValue);

        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            try (FileWriter file = new FileWriter(myFileLocation)) {
                //If COIN FILES not found, create tokens for each level with default value of ZERO
                if(attrType.equals("COIN")){
                    //obj=readAll(myFileLocation);
                    obj.put("COIN1",ZERO);
                    obj.put("COIN2",ZERO);
                    obj.put("COIN3",ZERO);
                    obj.put("COIN4",ZERO);
                    obj.put("COIN5",ZERO);
                    obj.put("COIN6",ZERO);
                    obj.put("COIN7",ZERO);
                    obj.put("COIN8",ZERO);
                    obj.put("COIN9",ZERO);
                    obj.put("COIN10",ZERO);
                    obj.put("COIN11",ZERO);
                    obj.put("COIN12",ZERO);
                    file.write(obj.toJSONString());
                    retValue=ZERO;
                }
                //File Writer creates a file in write mode at the given location
                //file.write(obj.toJSONString());
                //write function is use to write in file,
                //here we write the Json object in the file
                file.flush();
            }catch (IOException e2) {
                e2.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return retValue;
    }

    public JSONObject readAll(String myFileLocation) {

        JSONParser parser = new JSONParser();
        //JsonParser to convert JSON string into Json Object
        //String[][] kv = new String [24][2];
        String currentKey;
        

        try {
            Object inpFileObject = parser.parse(new FileReader(myFileLocation));
            //parsing the JSON string inside the file that we created earlier.

            JSONObject jsonObject = (JSONObject) inpFileObject;

            //System.out.println(jsonObject);
            //Json string has been converted into JSONObject

            Set set1 = jsonObject.keySet() ;
            Iterator itr = set1.iterator();

            while(itr.hasNext())
            {
                currentKey = itr.next().toString();
                //System.out.println(currentKey);
                obj.put(currentKey,jsonObject.get(currentKey));
            }
            //System.out.println(obj);
        } catch (FileNotFoundException e) {
           // e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return obj;
    }

    public boolean write(String attrType, String key, String value, boolean printObjValues){
        //read the file and load teh key values into "obj"

        if(attrType==null)
            myFileLocation = levelFileLocation;
        else if(attrType.equals("LEVEL"))
            myFileLocation = levelFileLocation;
        else if(attrType.equals("COIN"))
            myFileLocation = coinFileLocation;
        else
            myFileLocation = levelFileLocation;

        obj=readAll(myFileLocation);
        //Start writing the Key Value Pairs
        obj.put(key,value);


        try (FileWriter file = new FileWriter(myFileLocation)) {
            //File Writer creates a file in write mode at the given location
            file.write(obj.toJSONString());
            //write function is use to write in file,
            //here we write the Json object in the file
            file.flush();
        }catch (IOException e) {
            e.printStackTrace();
        }
        if (printObjValues){
            System.out.println(obj);
        }
        return true;
    }
}
