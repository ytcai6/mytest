package com.wechatConnection;

public class remove2 {

    //根据集合、id删除，以String类型返回返json数据
    public String removeById(String collection,String id) {
        String all_data;
        String data = "db.collection(\""+collection+"\").where({_id:\""+id+"\"}).remove()";

        test5 t =  new test5();
        all_data = t.run(2,data);

        return all_data;
    }
}
