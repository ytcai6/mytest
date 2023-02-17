package com.itcyt.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.itcyt.pojo.PageBean;
import com.itcyt.service.OrderService;
import com.wechatConnection.*;
import org.json.JSONArray;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    @Override
    public String selectAll() {
        query2 qy = new query2();
        String order = qy.queryAll("order");
        String newJsonString = order.substring(4);
//        System.out.println(newJsonString);
        JSONObject jsonObject = JSONObject.parseObject(newJsonString);
        String data = jsonObject.getString("data");
        data = data.replace("\\", "");
        StringBuilder stringBuilder = new StringBuilder(data);
        List<Integer> indexList = new ArrayList<>();
        int fromIndex = 1;
        int index = stringBuilder.indexOf("{", fromIndex);
        while (index != -1) {
            indexList.add(index - 1);
            fromIndex = index + 1;
            index = stringBuilder.indexOf("{", fromIndex);
        }
        for (int i = indexList.size() - 1; i >= 0; i--) {
            stringBuilder.deleteCharAt(indexList.get(i));
        }
        indexList = new ArrayList<>();
        fromIndex = 1;
        index = stringBuilder.indexOf("}", fromIndex);
        while (index != -1) {
            indexList.add(index + 1);
            fromIndex = index + 1;
            index = stringBuilder.indexOf("}", fromIndex);
        }
        for (int i = indexList.size() - 1; i >= 0; i--) {
            stringBuilder.deleteCharAt(indexList.get(i));
        }
        String jsonString = stringBuilder.toString();
        return jsonString;
    }

    //
    @Override
    public void deleteById(String _id) {
        remove2 rm = new remove2();
        rm.removeById("order", _id);
    }

    @Override
    public void add(String order) {
        add2 a = new add2();
//        System.out.println("order=>"+order);
        org.json.JSONObject orderJson = new org.json.JSONObject(order);
        orderJson.remove("_id");
        orderJson.remove("_openid");

        //结束日期时间换时间戳
        String endtime1 = orderJson.getString("endtime");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = null;
        try {
            date = simpleDateFormat.parse(endtime1);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        long ts = date.getTime();
        String endtime = String.valueOf(ts);
        long endtimeLong = Long.parseLong(endtime);
//        long endtimeLong = Long.parseLong(orderJson.getString("endtime"));
        orderJson.put("endtime", endtimeLong);

        //订单金额录入
        String paymentString = orderJson.getString("payment");
        float payment = Float.parseFloat(paymentString);
//        System.out.println("payment=>" + payment);
        orderJson.put("payment", payment);

        orderJson.put("gettime", System.currentTimeMillis());
        orderJson.put("starttime", System.currentTimeMillis());
        a.add("order", orderJson);
    }

    @Override
    public void editById(String newUser) {
        update2 up = new update2();
        System.out.println("newUser=>"+newUser);
        org.json.JSONObject newUserJson = new org.json.JSONObject(newUser);
        String _id = newUserJson.getString("_id");
        newUserJson.remove("_id");
        newUserJson.remove("_openid");
//        System.out.println(newUserJson);
        up.updateById("order", _id, newUserJson);
    }

    //
    @Override
    public void deleteByIds(String[] ids) {
        remove2 rm = new remove2();
        for (int i = 0; i < ids.length; i++) {
            rm.removeById("order", ids[i]);
        }
    }

    @Override
    public PageBean selectByPageAndCondition(int currentPage, int pageSize, String order) {

        query2 qy = new query2();
        org.json.JSONObject jsonObject = new org.json.JSONObject(order);
//        System.out.println("jsonObject=>"+jsonObject);
        jsonObject.remove("_id");
        jsonObject.remove("_openid");
//        System.out.println("order=>" + order);
        Iterator<String> keys = jsonObject.keys();
        List<String> deleteKeys = new ArrayList<>();
        while (keys.hasNext()) {
            String key = keys.next();
            if ((jsonObject.get(key) == null) || (jsonObject.get(key) == "")) {
                deleteKeys.add(key);
            }
        };
        for (int i = 0; i < deleteKeys.size(); i++) {
            jsonObject.remove(deleteKeys.get(i));
        }

        //status字段字符串转整型
        String orders = null;
        try {
            String status = jsonObject.getString("status");
            int statusInt = Integer.parseInt(status);
            jsonObject.put("status", statusInt);
        } catch (Exception e) {
//            e.printStackTrace();
        }

        if (jsonObject.isEmpty()) {
            orders = qy.queryAll("order");
        } else {
            orders = qy.queryByJsonObj("order", jsonObject);
        }

        String newJsonString = orders.substring(4);
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

//        System.out.println("list=>"+list);
//        for (Object o : list) {
//            System.out.println(o);
//        }

        return pageBean;
    }
}
