package com.wechatConnection;

import org.json.JSONObject;

import java.util.Iterator;

public class update2 {

    //根据id更新，传入集合、id、要更新的json对象，以String类型返回返json数据
    public String updateById(String collection,String id, JSONObject obj) {
        String data;
        String key;
        Object value;
        String all_data;

        data = "db.collection(\""+collection+"\").doc(\""+id+"\").update({data:{";
        Iterator iterator = obj.keys();
        while(iterator.hasNext()){
            key = (String)iterator.next();
            value = obj.get(key);
            if((value instanceof String)){
                data = data+key+":"+"\""+value+"\""+",";
            }
            else{
                data = data+key+":"+value+",";
            }
        }
        data = data.substring(0,data.length()-1);
        data=data+"}})";
//        System.out.println(data);

        test5 t =  new test5();
        all_data = t.run(1,data);
        return all_data;
    }
}
