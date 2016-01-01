package com.example.smarthomeapp.listener;

import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;


/**
 * Created by CheYuLin on 2015/4/18.
 */
public class ReflectionUtil {

    private String tagString;

    public ReflectionUtil(String tagString) {
        this.tagString = tagString;
    }

    private String parSetName(String fieldName) {
        if (null == fieldName || "".equals(fieldName)) {
            return null;
        }
        return "set" + fieldName.substring(0, 1).toUpperCase()
                + fieldName.substring(1);
    }

    private String parGetName(String fieldName) {
        if (null == fieldName || "".equals(fieldName)) {
            return null;
        }
        return "get" + fieldName.substring(0, 1).toUpperCase()
                + fieldName.substring(1);
    }

    private static boolean isPrimaryType(String typeNameString) {
        if (typeNameString.equals("Long") || typeNameString.equals("String") ||
                typeNameString.equals("Integer") || typeNameString.equals("Date") ||
                typeNameString.equals("Float") || typeNameString.equals("Boolean"))
            return true;
        else
            return false;

    }

    private static boolean isPrimaryTypeClass(Class<?> clazz)
    {
        if(clazz==Long.class||clazz==String.class||clazz==Integer.class
                ||clazz==Date.class||clazz==Float.class||clazz==Boolean.class||clazz==Object.class)
            return  true;
        else
            return false;
    }
    /**
     * @param object 待打印信息的Object
     *               Log出Object中所有基本类型信息 Long  String Integer Date
     */
    public void logResponseData(Object object) {
        if (object == null)
            Log.d(tagString, "----------Object Information：Null Object");
        Class<?> cls = object.getClass();
        String tempNameStr = cls.getSimpleName();
        if (isPrimaryType(tempNameStr)) {
            Log.d(tagString, "~~~~~Primary Type Val" + ":" + String.valueOf(object));
            return;
        }
        ArrayList<Field> fieldsArrayList=new ArrayList<>();
        Field[] fields = cls.getDeclaredFields();
        for(Class<?> clazz=object.getClass();!isPrimaryTypeClass(clazz);clazz=clazz.getSuperclass())
        {
            Field[] tmpFields=clazz.getDeclaredFields();
            for(Field f:tmpFields)
            {
//                Log.d("ReflectionUTil",f.getName());
                fieldsArrayList.add(f);
            }
        }
        fields= new Field[fieldsArrayList.size()];
        int tempIndex=0;
        for(Field f:fieldsArrayList)
        {
            fields[tempIndex]=f;
            tempIndex++;
        }
        Log.d(tagString, "----------Object Information：" + cls.getSimpleName() + " Has " + fields.length + " fields");
        for (Field f : fields) {
            try {
                String typeNameString = f.getType().getSimpleName();
                String methodNameString = parGetName(f.getName());
                Method fieldGetMethod = cls.getMethod(methodNameString, new Class[]{});
                Object fieldObject = fieldGetMethod.invoke(object, new Object[]{});
                if (isPrimaryType(typeNameString)) {
                    Log.d(tagString, "~~~~~" + f.getName() + ":" + String.valueOf(fieldObject));
                } else if (typeNameString.equals("List")) {
                    Object[] objects;
                    if (fieldObject == null || (objects = ((ArrayList) fieldObject).toArray()).length == 0) {
                        Log.d(tagString, "----------Object Information：Null List:" + f.getName());
                        continue;
                    }
                    for (Object obj : objects) {
                        logResponseData(obj);
                    }
                } else {
//                    Log.d(tagString, "~~~~~" + fieldObject.getClass().getSimpleName()+ ":");
//                    Log.d(tagString,String.valueOf(fieldObject==null));
//                    Log.d(tagString,String.valueOf(fieldObject instanceof TrainingWorkout));
                    logResponseData(fieldObject);
                }
            } catch (Exception e) {

            }

        }


    }
}
