package com.wechatConnection;

import org.json.JSONObject;

import java.util.Iterator;

public class add2 {

    //传入集合，要增加的json对象,以String类型返回返json数据
    public String add(String collection,JSONObject obj) {
        String data;
        String key;
        Object value;
        String all_data;
        data = "db.collection(\""+collection+"\").add({data:[{";
        Iterator iterator = obj.keys();
        while(iterator.hasNext()){
            key = (String)iterator.next();
            value = obj.get(key);
            if((value instanceof String)){
                data = data+key+":"+"\\\""+value+"\\\""+",";
            }
            else{
                data = data+key+":"+value+",";
            }
        }
        data = data.substring(0,data.length()-1);
        data=data+"}]})";

//        System.out.println("data=>"+data);
        test5 t =  new test5();
        all_data = t.run(0,data);

        return all_data;
    }

}
