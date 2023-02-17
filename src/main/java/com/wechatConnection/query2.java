package com.wechatConnection;

import com.wechatConnection.test5;
import org.json.JSONObject;

import java.util.Iterator;

public class query2 {

    //根据集合、id查询，以String类型返回返json数据
    public String queryById(String collection,String id) {
        String data = "db.collection(\""+collection+"\").doc(\""+id+"\").get()";
        String all_data;
        test5 t =  new test5();
        all_data = t.run(3,data);
        return all_data;
    }

    //根据集合、json对象查询，以String类型返回返json数据
    public String queryByJsonObj(String collection,JSONObject obj) {

        String data = null;
        String all_data = null;
        String key;
        Object value;
        query2 qy = new query2();
        int num = qy.getTotal(collection,obj)/100+1;
        for(int i=0;i<num;i++) {
            data = "db.collection(\"" + collection + "\").where({";
            Iterator iterator = obj.keys();
            while (iterator.hasNext()) {
                key = (String) iterator.next();
                value = obj.get(key);
                if ((value instanceof String)) {
                    data = data + key + ":" + "\"" + value + "\"" + ",";
                } else {
                    data = data + key + ":" + value + ",";
                }
            }
            data = data.substring(0, data.length() - 1);
            data = data + "}).limit(100).skip(" + 100*i + ").get()";
            //data = data + "}).limit(10).skip(0).get()";
//            System.out.println(data);

            test5 t = new test5();
            all_data += t.run(3, data);
        }
        return all_data;
    }

    //根据集合查询所有记录，以String类型返回返json数据
    public String queryAll(String collection) {
        String data = null;
        String all_data = null;
        query2 qy = new query2();
        int num = qy.getTotal2(collection)/100+1;
        for(int i=0;i<num;i++) {
            data = "db.collection(\"" + collection + "\").where({}).limit(100).skip(" + 100*i + ").get()";
//            System.out.println(data);
            test5 t = new test5();
            all_data += t.run(3, data);
        }
        return all_data;
    }


    //传入集合，json对象，返回查询记录总数
    public int getTotal(String collection,JSONObject object) {

        String data;
        String key;
        Object value;
        data = "db.collection(\""+collection+"\").where({";
        Iterator iterator = object.keys();
        while(iterator.hasNext()){
            key = (String)iterator.next();
            value = object.get(key);
            if((value instanceof String)){
                data = data+key+":"+"\""+value+"\""+",";
            }
            else{
                data = data+key+":"+value+",";
            }
        }
        data = data.substring(0,data.length()-1);
        data=data+"}).limit(10).count()";
        test5 t =  new test5();
        String returndata = t.run(3,data);
        JSONObject obj = new JSONObject(returndata);
        JSONObject obj2  = obj.getJSONObject("pager");
        Integer num = obj2.getInt("Total");
        return num;
    }

    //传入集合，返回集合记录总数
    public int getTotal2(String collection) {
        String data;
        data = "db.collection(\""+collection+"\").count()";
        test5 t =  new test5();
        String returndata = t.run(3,data);
//        System.out.println("returndata=>"+returndata);
        JSONObject obj = new JSONObject(returndata);
        JSONObject obj2  = obj.getJSONObject("pager");
        Integer num = obj2.getInt("Total");
        return num;
    }
}
