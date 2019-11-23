package Storage;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import Model.Character;
import Model.Monster;

public class JSONFileStorageMonster  {
    private static JSONFileStorageMonster HELPER;
    private final static String NAME = "monsterStorage";
    private final static String NEXT_ID = "next_id";
    private final static String LIST  = "list";

    public static JSONFileStorageMonster get(Context context){
        if(HELPER==null){
            HELPER = new JSONFileStorageMonster(context);
        }
        return HELPER;
    }

    private Context context;
    private JSONObject json;

    private JSONFileStorageMonster(Context context){
        this.context = context;
        read();
    }
    private void createJson(){
        json = new JSONObject();
        try {
            json.put(LIST, new JSONObject());
            json.put(NEXT_ID,1);
        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void insert(String name, int dd, String photo){
        JSONObject object = new JSONObject();
        int nextId = json.optInt(NEXT_ID);
        System.out.println("NEXT ID :"+nextId);
        try {
            object.put(Monster.COLUMN_NAME_ID,nextId);
            object.put(Monster.COLUMN_NAME_NAME,name);
            object.put(Monster.COLUMN_NAME_DD,dd);
            object.put(Monster.COLUMN_NAME_PHOTO,photo);
            json.optJSONObject(LIST).put(""+ nextId, object);
            json.put(NEXT_ID,nextId+1);
            write();
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }

    private void read(){
        try {
            FileInputStream in = context.openFileInput(NAME);
            if(in!=null){
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
                String temp;
                StringBuilder builder = new StringBuilder();
                while((temp=bufferedReader.readLine())!=null){
                    builder.append(temp);
                }
                in.close();
                json = new JSONObject(builder.toString());
            }
        } catch (FileNotFoundException e) {
            createJson();
            write();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void write(){
        try {
            FileOutputStream out = context.openFileOutput(NAME,Context.MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(out);
            writer.write(json.toString());
            writer.close();
        }catch (Exception e ) {
            e.printStackTrace();
        }
    }
    public List<Monster> findAll(){
        List<Monster> list = new ArrayList<>();
        Iterator<String> iterator = json.optJSONObject(LIST).keys();
        while(iterator.hasNext())
            list.add(new Monster(json.optJSONObject(LIST).optJSONObject(iterator.next())));
        return list;
    }
    public Monster find(int index){
        return new Monster(json.optJSONObject(LIST).optJSONObject(""+index));
    }
    public int size(){
        return json.optJSONObject(LIST).length();
    }

    public int nextId(){ return json.optInt(NEXT_ID);}

    public void update(int index, String name, int dd, String photo){
        JSONObject object = json.optJSONObject(LIST).optJSONObject("" + index);
        try {
            object.put(Monster.COLUMN_NAME_PHOTO,photo);
            object.put(Monster.COLUMN_NAME_NAME,name);
            object.put(Monster.COLUMN_NAME_DD,dd);

            write();
        }
        catch (JSONException e ){
            e.printStackTrace();
        }
    }
    public void delete(int index){
        json.optJSONObject(LIST).remove(""+index);
        write();

    }
}
