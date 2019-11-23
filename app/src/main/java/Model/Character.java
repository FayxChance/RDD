package Model;

import android.widget.ImageView;

import org.json.JSONObject;

import java.io.Serializable;

import Storage.JSONFileStorageCharacter;

public class Character implements Serializable {


    public static String COLUMN_NAME_ID="id";
    public static int _COLUMN_NUMBER_ID=0;
    public static String COLUMN_NAME_PHOTO="photo";
    private static int COLUMN_NUM_PHOTO=1;
    public static String COLUMN_NAME="name";
    private static int COLUMN_NUM_NAME=2;
    public static String COLUMN_RACE="race";
    private static int COLUMN_NUM_RACE=3;
    public static String COLUMN_INTEL="intel";
    private static int COLUMN_NUM_INTEL=4;
    public static String COLUMN_STRENGTH="strength";
    private static int COLUMN_NUM_STRENGTH=5;
    public static String COLUMN_AGILITY="agility";
    private static int COLUMN_NUM_AGILITY=6;

    private int id;
    private String name, race;
    private int intel, strength, agility;
    private String photo;
    public Character(){
        this("yolo","",0,0,0,null);
    }

    public Character(int id, String name, String race, int intel, int strength, int agility, String photo) {
        this.id=id;
        this.name=name;
        this.race=race;
        this.agility=agility;
        this.strength=strength;
        this.intel=intel;
        this.photo = photo;
    }

    public Character(String name, String race, int intel, int strength, int agility, String photo) {
        this(-1,name,race,intel,strength,agility,photo);
    }

    public Character(JSONObject object) {
        this(object.optInt(COLUMN_NAME_ID),object.optString(COLUMN_NAME),object.optString(COLUMN_RACE),
                object.optInt(COLUMN_INTEL),object.optInt(COLUMN_STRENGTH),object.optInt(COLUMN_AGILITY),
                object.optString(COLUMN_NAME_PHOTO)
        );
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public int getIntel() {
        return intel;
    }

    public void setIntel(int intel) {
        this.intel = intel;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getId() {
        return id;
    }
}
