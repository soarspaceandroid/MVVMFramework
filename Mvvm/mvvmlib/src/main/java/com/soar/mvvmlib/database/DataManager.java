/*
 * Copyright (C) 2016 Lusfold
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.soar.mvvmlib.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.soar.mvvmlib.database.core.KVManagerImpl;
import com.soar.mvvmlib.database.core.KVManger;


public class DataManager {
    public static final String TAG = "DataManager";
    private static KVManger instance;
    private static DataManager dataManager;
    private Gson gson;


    /**
     * init data
     */
    public DataManager() {
        if(gson == null){
            gson = new Gson();
        }
    }

    /**
     * Init component.
     *
     * @param context      used to open or create the database
     * @param databaseName database name for opening or creating
     */
    public static KVManger init(Context context, String databaseName) {
        SQLiteDatabase database = context.openOrCreateDatabase(databaseName, Context.MODE_PRIVATE, null);
        return init(database);
    }

    /**
     * Init component.
     *
     * @param database
     * @return KVManager instance.
     */
    public static KVManger init(SQLiteDatabase database) {
        if (instance == null) {
            synchronized (KVManger.class) {
                if (instance == null) {
                    instance = new KVManagerImpl(database);
                }
            }
        }
        return instance;
    }




    /**
     * get instance
     * @return
     */
    public synchronized static DataManager getInstance(){
        if(dataManager ==null){
            dataManager = new DataManager();
        }
        return dataManager;
    }


    /**
     * Get KVManager instance.
     *
     * @return KVManager instance.
     */
    public static KVManger getClient() {
        return instance;
    }

    /**
     * Must be called when this app is finishing.
     */
    public static void destroy() {
        if (instance != null) {
            instance.getDatabase().close();
        }
    }

    /**
     *   -------------------------------------------insert updata ------------------------------------
     */


    /**
     * insert boolean
     * @param key
     * @param value
     */
    public void insertBoolean (String key , boolean value){
        if(!TextUtils.isEmpty(key)) {
            getClient().insertOrUpdate(key, String.valueOf(value));
        }
    }


    /**
     * get boolean
     * @param key
     * @return
     */
    public boolean getBoolean(String key){
        if(getClient().keyExists(key)){
            return Boolean.parseBoolean(getClient().get(key));
        }else{
            return false;
        }

    }


    /**
     * insert object
     * @param t
     * @param <T>
     */
    public <T> void  insertObject(T t){
        if(t == null){
            return;
        }
        getClient().insertOrUpdate(t.getClass().getSimpleName() , gson.toJson(t));
    }




    /**
     * insert object
     * @param t
     * @param <T>
     */
    public <T> void  insertObject(String key , T t){
        if(t == null){
            return;
        }
        if(!TextUtils.isEmpty(key)) {
            getClient().insertOrUpdate(key, gson.toJson(t));
        }
    }

    /**
     * get object
     * @param tClass
     * @param <T>
     * @return
     */
    public <T> T getObject(Class<T> tClass){
        if(getClient().keyExists(tClass.getSimpleName())){
            return gson.fromJson(getClient().get(tClass.getSimpleName()) , tClass);
        }else{
            return null;
        }
    }



    /**
     * get object
     * @param tClass
     * @param <T>
     * @return
     */
    public <T> T getObject(String key , Class<T> tClass){
        if(getClient().keyExists(key)){
            return gson.fromJson(getClient().get(key) , tClass);
        }else{
            return null;
        }
    }

    /**
     * insert string
     * @param key
     * @param value
     */
    public void insertString(String key , String value){
        if(!TextUtils.isEmpty(key)) {
            getClient().insertOrUpdate(key, value);
        }
    }


    /**
     * get string
     * @param key
     * @return
     */
    public String getString(String key){
        if(getClient().keyExists(key)){
            return getClient().get(key);
        }else{
            return "";
        }
    }


    /**
     * delete data
     * @param key
     */
    public void deleteData(String key){
        if(getClient().keyExists(key)){
            getClient().delete(key);
        }
    }


    public void closeDB(){
        destroy();
    }

}
