package com.trojan.test;/**
 * @Description
 * @Author dgj
 * @Date 2020/4/2
 * @Version 1.0
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

/**
 * @ClassName TestReciveEs
 * @Description TODO
 * @Author dgj
 * @Date 2020/4/2
 * @Version 1.0
 */
@Controller
public class TestReciveEs {
    @RequestMapping("/testRes")
    @ResponseBody
    public void testReceive(HttpServletRequest request,HttpServletResponse response) throws IOException {
        System.out.println("------------------"+request.getQueryString()+"------------");

//        System.out.println("go here");
        Map<String, String[]> map= request.getParameterMap();
        for(String key : map.keySet()){
            System.out.println(key);
            System.out.println(request.getParameter(key));
        }
        response.getWriter().write("{\"version\":\"1.0\",\"clientCode\":\"48310030\",\"retCode\":\"0000\",\"retMsg\":\"SUCCESS\",\"MAC\":\"EBF2B411CAD0286BD856046111AFE336\"}");
//        String biz_content = request.getParameter("MAC");
//        String sign = request.getParameter("fileName");
//        System.out.println(request.getClass().getName());
////        System.out.println("biz_content:"+biz_content);
////        System.out.println("sign:"+sign);
//        BufferedReader br = null;
//        StringBuilder sb = new StringBuilder("");
//        try
//        {
//            br = request.getReader();
//            String str;
//            while ((str = br.readLine()) != null)
//            {
//                System.out.println(str);
//                sb.append(str);
//            }
//            br.close();
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//        }
//        finally
//        {
//            if (null != br)
//            {
//                try
//                {
//                    br.close();
//                }
//                catch (IOException e)
//                {
//                    e.printStackTrace();
//                }
//            }
//        }
//        System.out.println("------:"+sb.toString());
    }
}
