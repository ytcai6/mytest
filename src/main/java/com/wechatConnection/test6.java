package com.wechatConnection;


import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class test6 {

    public static void main(String args[]) {

        //测试数据
        JSONObject obj = new JSONObject();
        obj.put("admin", "zxx");
        obj.put("faceImg", "https://thirdwx.qlogo.cn/mmopen/vi_32/mtSutrSlArVEXODvt7j25bBsmtaGeCVJ2ib6N7Bz9PWSC6duXib2EL4icJNl8sJ2Av59Sa2HgibjDGiaibXbUicvicKIAA/132");
        obj.put("nickName", "老刘test");
        obj.put("number", 56462);
        obj.put("password", "123456");
        obj.put("signature", "this is a test sign");


        /**
         * 新增数据
         */
//        add2 a = new add2();
//        String json = "{\"number\":12345678910,\"password\":\"testx\",\"faceImg\":\"testx\",\"signature\":\"testx\",\"nickName\":\"testx\",\"admin\":\"testx\"}\n";
//        org.json.JSONObject obj1 = new org.json.JSONObject(json);
//        System.out.println(obj1);
//        a.add("chat_users",obj);
//        System.out.println("all_data:"+a.add("test",obj));


        /**
         * 更新数据
         */
//        update2 up = new update2();
//        up.updateById("test","6d85a2b962c67ede0f0961ac6a08c644",obj1);
//        System.out.println("all_data:"+up.updateById("test","6d85a2b962c67ede0f0961ac6a08c644",obj));


        /**
         * 删除数据
         */
//        remove2 rm = new remove2();
//        rm.removeById("test","058dfefe62c69d9c0c6637363703295e");
//        System.out.println("all_data:"+rm.removeById("test","16db756f62c6802709f4fa1b11a1e8f4"));

        /**
         * 查询数据
         */
        query2 qy = new query2();
//        String chat_users = qy.queryAll("chat_users");
//        String newJsonString = chat_users.substring(4);
////        System.out.println(newJsonString);
//        JSONObject jsonObject = JSONObject.parseObject(newJsonString);
//        String data = jsonObject.getString("data");
//        data = data.replace("\\", "");
//        StringBuilder stringBuilder = new StringBuilder(data);
//        List<Integer> indexList = new ArrayList<>();
//        int fromIndex = 1;
//        int index = stringBuilder.indexOf("{", fromIndex);
//        while (index!=-1) {
//            indexList.add(index - 1);
//            fromIndex = index + 1;
//            index = stringBuilder.indexOf("{", fromIndex);
//        }
//        for (int i = indexList.size()-1; i >= 0 ; i--) {
//            stringBuilder.deleteCharAt(indexList.get(i));
//        }
//        indexList = new ArrayList<>();
//        fromIndex = 1;
//        index = stringBuilder.indexOf("}", fromIndex);
//        while (index!=-1) {
//            indexList.add(index+1);
//            fromIndex = index + 1;
//            index = stringBuilder.indexOf("}", fromIndex);
//        }
//        for (int i = indexList.size()-1; i >= 0 ; i--) {
//            stringBuilder.deleteCharAt(indexList.get(i));
//        }
//        String jsonString = stringBuilder.toString();

        String json = "{\"_id\":\"\",\"_openid\":\"\",\"admin\":\"test\",\"faceImg\":\"\",\"nickName\":\"\",\"number\":0,\"password\":\"\",\"signature\":\"\"}";
        org.json.JSONObject jsonObject = new org.json.JSONObject(json);
        jsonObject.remove("_id");
        jsonObject.remove("_openid");
        if(jsonObject.getInt("number")==0){
            jsonObject.remove("number");
        }
        Iterator<String> keys = jsonObject.keys();
        List<String> deleteKeys = new ArrayList<>();
        while (keys.hasNext()) {
            String key = keys.next();
            if (jsonObject.get(key) == null || jsonObject.get(key) == "") {
                deleteKeys.add(key);
            }
        };
        for (int i = 0; i < deleteKeys.size(); i++) {
            jsonObject.remove(deleteKeys.get(i));
        }
        String chat_users = qy.queryByJsonObj("chat_users", jsonObject);

        String newJsonString = chat_users.substring(4);
//        System.out.println(newJsonString);
        com.alibaba.fastjson.JSONObject tempJsonObject = com.alibaba.fastjson.JSONObject.parseObject(newJsonString);
        String pager = tempJsonObject.getString("pager");
        JSONObject jsonPager = new JSONObject(pager);
        int total = jsonPager.getInt("Total");
        String data = tempJsonObject.getString("data");
        data = data.replace("\\", "");
        StringBuilder stringBuilder = new StringBuilder(data);
        List<Integer> indexList = new ArrayList<>();
        int fromIndex = 1;
        int index = stringBuilder.indexOf("{", fromIndex);
        while (index!=-1) {
            indexList.add(index - 1);
            fromIndex = index + 1;
            index = stringBuilder.indexOf("{", fromIndex);
        }
        for (int i = indexList.size()-1; i >= 0 ; i--) {
            stringBuilder.deleteCharAt(indexList.get(i));
        }
        indexList = new ArrayList<>();
        fromIndex = 1;
        index = stringBuilder.indexOf("}", fromIndex);
        while (index!=-1) {
            indexList.add(index+1);
            fromIndex = index + 1;
            index = stringBuilder.indexOf("}", fromIndex);
        }
        for (int i = indexList.size()-1; i >= 0 ; i--) {
            stringBuilder.deleteCharAt(indexList.get(i));
        }
        String jsonString = stringBuilder.toString();
//        System.out.println(jsonString);
        JSONArray result = new JSONArray(jsonString);

        JSONArray newJSONArray = new JSONArray(1);
        for (int i = 0; i < 1; i++) {
            newJSONArray.put(i, result.get(0+i));
        }
//        System.out.println(newJSONArray);

//        qy.getTotal("test",obj);
//        System.out.println(qy.getTotal("test",obj));

    }

    @Test
    public void test() {
        String jsonString = "{\"note\":\"\",\"img\":\"\",\"getphone\":\"13543519591\",\"sendpersonid\":\"\",\"sendphone\":\"\",\"endtime\":\"1233453\",\"getplace\":\"test\",\"sendperson\":\"\",\"type\":\"test\",\"userid\":\"\",\"gettime\":\"123456\",\"nickname\":\"test\",\"strattime\":\"\",\"payment\":\"\",\"sendplace\":\"\",\"status\":\"1\"}";
        add2 a = new add2();
        JSONObject jsonObject = new JSONObject(jsonString);
//        System.out.println(jsonObject);
        long endtime = Long.parseLong(jsonObject.getString("endtime"));
        jsonObject.put("endtime", endtime);
        jsonObject.put("gettime", System.currentTimeMillis());
        jsonObject.put("starttime", System.currentTimeMillis());
        jsonObject.put("payment", 0);
        int status = Integer.parseInt(jsonObject.getString("status"));
        jsonObject.put("status", status);
        a.add("order", jsonObject);
    }

}
