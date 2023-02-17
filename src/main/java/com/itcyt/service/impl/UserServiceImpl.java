package com.itcyt.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.itcyt.pojo.PageBean;
import com.itcyt.service.UserService;
import com.wechatConnection.*;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserServiceImpl implements UserService {

    @Override
    public String selectAll() {
        query2 qy = new query2();
        String chat_users = qy.queryAll("chat_users");
        String newJsonString = chat_users.substring(4);
//        System.out.println(newJsonString);
        JSONObject jsonObject = JSONObject.parseObject(newJsonString);
        String data = jsonObject.getString("data");
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
        return jsonString;
    }

    @Override
    public void deleteById(String _id) {
        remove2 rm = new remove2();
        rm.removeById("chat_users",_id);
    }

    @Override
    public void add(String user) {
        add2 a = new add2();
        org.json.JSONObject userJson = new org.json.JSONObject(user);
        userJson.remove("_id");
        userJson.remove("_openid");
        String number = (String) userJson.get("number");
        long newNumber = Long.parseLong(number);
        userJson.put("number", newNumber);
        a.add("chat_users",userJson);
    }

    @Override
    public void editById(String newUser) {
        update2 up = new update2();
        org.json.JSONObject newUserJson = new org.json.JSONObject(newUser);
        String _id = newUserJson.getString("_id");
        newUserJson.remove("_id");
        newUserJson.remove("_openid");
        up.updateById("chat_users", _id, newUserJson);
    }

    @Override
    public void deleteByIds(String[] ids) {
        remove2 rm = new remove2();
        for (int i = 0; i < ids.length; i++) {
            rm.removeById("chat_users",ids[i]);
        }
    }

    @Override
    public PageBean selectByPageAndCondition(int currentPage, int pageSize, String user) {
        query2 qy = new query2();
        org.json.JSONObject jsonObject = new org.json.JSONObject(user);
        jsonObject.remove("_id");
        jsonObject.remove("_openid");
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

        String chat_users = null;
        if (jsonObject.isEmpty()) {
            chat_users = qy.queryAll("chat_users");
        } else {
            chat_users = qy.queryByJsonObj("chat_users", jsonObject);
        }



        String newJsonString = chat_users.substring(4);
//        System.out.println(newJsonString);
        JSONObject tempJsonObject = JSONObject.parseObject(newJsonString);
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
        JSONArray result = new JSONArray(jsonString);

        int begin = (currentPage - 1) * pageSize;
        int size = 0;
        if (begin + pageSize < result.length()) {
            size = pageSize;
        } else {
            size = result.length() - begin;
        }

        JSONArray newJSONArray = new JSONArray(size);
        for (int i = 0; i < size; i++) {
            newJSONArray.put(i, result.get(begin+i));
        }


        String pager = tempJsonObject.getString("pager");
        org.json.JSONObject jsonPager = new org.json.JSONObject(pager);
        int total = jsonPager.getInt("Total");

        PageBean pageBean = new PageBean();

        List<Object> list = newJSONArray.toList();

        pageBean.setRows(list);
        pageBean.setTotalCount(total);

        return pageBean;
    }
}
