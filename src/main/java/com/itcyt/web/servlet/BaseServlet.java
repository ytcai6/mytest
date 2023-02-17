package com.itcyt.web.servlet;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 替换HttpServlet,根据请求的最后一段路径来分发
 */
public class BaseServlet extends HttpServlet {

    //根据路径进行方法分发
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求路径
        String requestURI = req.getRequestURI();//uri:/brand-cast/brand/selectAll
//        System.out.println("requestURI=>"+requestURI);
//        System.out.println("requestContext=>"+req.getContextPath());
//        获取最后一段路径
        int index =requestURI.lastIndexOf('/');
        String methodName = requestURI.substring(index+1);
//        System.out.println("methodName=>"+methodName);

        //执行方法
//        获取BrandServlet字节码对象
        Class<? extends BaseServlet> cls = this.getClass();

//        获取方法Method对象
        try {
            Method method = cls.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
//            System.out.println(method);
            //        执行方法
            try {
                method.invoke(this,req,resp);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

}
