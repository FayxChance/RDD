package Model;

import android.widget.ImageView;

import org.json.JSONObject;

import java.io.Serializable;

public class Monster implements Serializable {
    public static String COLUMN_NAME_ID="id";
    public static int _COLUMN_NUMBER_ID=0;
    public static String COLUMN_NAME_PHOTO="photo";
    private static int COLUMN_NUM_PHOTO=1;
    public static String COLUMN_NAME_NAME="name";
    private static int COLUMN_NUM_NAME=2;
    public static String COLUMN_NAME_DD="dd";
    private static int COLUMN_NUM_DD=2;


    private int id,dd;
    private String name;
    private String photo;


    public Monster(){
        this("yolo",0);
    }

    public Monster(int id, String name, int dd,String photo) {
        this.id=id;
        this.name=name;
        this.dd=dd;
        this.photo=photo;
    }

    public Monster(JSONObject object) {
        this(
                object.optInt(COLUMN_NAME_ID),
                object.optString(COLUMN_NAME_NAME),
                object.optInt(COLUMN_NAME_DD),
                object.optString(COLUMN_NAME_PHOTO)
        );
    }

    public Monster(String name, int dd) {
        this(-1,name,dd,"");
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDD() {
        return dd;
    }

    public void setDD(int dd) {
        this.dd = dd;
    }


    public int getId() {
        return id;
    }
}
