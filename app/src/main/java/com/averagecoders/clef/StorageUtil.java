package com.averagecoders.clef;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

/**
 * Created by harish on 18-Dec-17.
 * <p>
 * Dear Maintainer:
 * When I wrote the code, only I and God knew what it was.
 * Now, Only God knows!
 * <p>
 * total_hours =
 */

public class StorageUtil {

    private final String STORAGE = "com.averagecoders.clef.STORAGE";
    private SharedPreferences sharedPreferences;
    private Context context;

    public StorageUtil(Context context) {
        this.context = context;
    }

    //function to store audio
    public void storeAudio(ArrayList<Audio> audioList){

        sharedPreferences = context.getSharedPreferences(STORAGE,Context.MODE_PRIVATE);
        SharedPreferences.Editor sEditor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(audioList);
        sEditor.putString("audioArrayList",json);
        sEditor.apply();
    }

    //function to store the audio indices
    public void storeAudioIndex(int index){
        sharedPreferences = context.getSharedPreferences(STORAGE,Context.MODE_PRIVATE);
        SharedPreferences.Editor sEditor = sharedPreferences.edit();
        sEditor.putInt("audioIndex",index);
        sEditor.apply();
    }

    //function that loads audio
    public ArrayList<Audio> loadAudio(){
        sharedPreferences = context.getSharedPreferences(STORAGE,Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("audioArrayList",null);
        java.lang.reflect.Type type = new TypeToken<ArrayList<Audio>>(){
        }.getType();
        return gson.fromJson(json,type);
    }

    public int loadAudioIndex() {
        sharedPreferences = context.getSharedPreferences(STORAGE, Context.MODE_PRIVATE);
        return sharedPreferences.getInt("audioIndex", -1);//return -1 if no data found
    }

    public void clearCachedAudioPlaylist() {
        sharedPreferences = context.getSharedPreferences(STORAGE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
