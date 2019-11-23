package Storage;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Model.Character;

public class JsonFileStorageParticipant {
    private static JsonFileStorageParticipant HELPER;
    private final static String NAME = "participant";
    private final static String NEXT_ID = "next_id";
    private final static String LIST  = "list";

    public static JsonFileStorageParticipant get(Context context){
        if(HELPER==null){
            HELPER = new JsonFileStorageParticipant(context);
        }
        return HELPER;
    }

    private Context context;
    private JSONObject json;

    private JsonFileStorageParticipant(Context context){
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

    public void insert(int id){
        JSONObject object = new JSONObject();
        int nextId = json.optInt(NEXT_ID);
        try {
            object.put("idPerso",id);
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
    public List<Integer> findAll(){
        List<Integer> list = new ArrayList<>();
        Iterator<String> iterator = json.optJSONObject(LIST).keys();
        while(iterator.hasNext())
            list.add(json.optJSONObject(LIST).optJSONObject(iterator.next()).optInt("idPerso"));
        return list;
    }



    public Character find(int index){
        return new Character(json.optJSONObject(LIST).optJSONObject(""+index));
    }
    public int size(){
        return json.optJSONObject(LIST).length();
    }

    public int nextId(){ return json.optInt(NEXT_ID);}

    public void update(int index, int id){
        JSONObject object = json.optJSONObject(LIST).optJSONObject("" + index);
        try {
            //object.put(Character.COLUMN_PHOTO,photo);
            object.put("idPerso",id);
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
    public void deleteAll(){
        try {
            Iterator<String> iterator = json.optJSONObject(LIST).keys();
            while(iterator.hasNext())
                this.delete(Integer.parseInt(iterator.next()));
            write();


        } catch (Exception e){
            ;
        }

    }
}
