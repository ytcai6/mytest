package com.wechatConnection;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class test5 {
    public String run(int x,String query){
        post getpost = new post();
        String url = "https://api.weixin.qq.com/cgi-bin/token";
        String APPID = "wx6dced74cdd82a62e";
        String APPSERECT = "18b591146a64da0cb05e6fe2e4e9053f";

        Map<String, String> datamap = new HashMap<String, String>();
        datamap.put("grant_type","client_credential");
        datamap.put("appid",APPID);
        datamap.put("secret",APPSERECT);

        String result_1 = getpost.sendGet(url,datamap);
//        System.out.println(result_1);
        JSONObject acc_token = new JSONObject(result_1);
        //System.out.println(jsonObject.get("access_token"));
        String access_token = acc_token.get("access_token").toString();

        JSONObject postdata = new JSONObject();
        //postdata.append("access_token",access_token);
        //在json对象中添加元素
        postdata.put("env","env-9gasw0bob9d7c2ed");
        postdata.put("query",query);
//        System.out.println("postdata=>"+postdata);
        String url_2 = null;
        if(x==0){
            url_2 = "https://api.weixin.qq.com/tcb/databaseadd?access_token=";
        }
        else if(x==1){
            url_2 = "https://api.weixin.qq.com/tcb/databaseupdate?access_token=";
        }
        else if(x==2){
            url_2 = "https://api.weixin.qq.com/tcb/databasedelete?access_token=";
        }
        else if(x==3){
            url_2 = "https://api.weixin.qq.com/tcb/databasequery?access_token=";
        }

        url_2 = url_2+access_token;
        String data = new String();
        data = getpost.getJsonData(postdata,url_2);
        return data;
    }
}