package com.wechatConnection;

import org.json.JSONObject;
import java.util.Iterator;
import java.io.UnsupportedEncodingException;

public class add4 {

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
        String data2 = null;
        try {
            data2 = new String(data.getBytes("utf-8"),"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        test5 t =  new test5();
        System.out.println("data2=>" + data2);
        all_data = t.run(0,data2);

        return all_data;
    }

}
