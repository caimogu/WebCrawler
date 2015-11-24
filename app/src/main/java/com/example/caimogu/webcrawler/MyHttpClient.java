package com.example.caimogu.webcrawler;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Caimogu on 2015/11/23.
 */
public class MyHttpClient
{
    private static String charset = "utf-8";
    private static File file;
    //private String filePath = "/mnt/sdcard/HtmlFile/teacher.html";
    //private InputStream htm_in = null;
    //public static List<Teacher> list;
    public TeacherManager teacherMana;

    //String url;
    //Teacher teacher;

    public void getClient() throws Exception
    {
        //file = new File(filePath);

        teacherMana.list = new ArrayList<Teacher>();

        HttpEntity entity = oneHttpget("http://121.248.70.214/jwweb/ZNPK/Private/List_JS.aspx?xnxq=20150&js=");

        if(entity != null)
        {
            Log.i("Caimogu","-----------getClient---------");
            //htm_in = entity.getContent();
            //String str = EntityUtils.toString(entity);
            //htm_str = InputStreamToString(htm_in);

            //html_substr = selectHtmlString(htm_str);
            String allString = EntityUtils.toString(entity);//从get到的选出html语句
            String scripts = selectHtmlString(allString);//选出html代码段
            //saveAsFile(file, html_substr);//保存html信息

             getTeacherInfo(scripts);//从html信息中选出每个教师的信息


        }


    }

    public HttpEntity oneHttpget(String url) throws Exception//一次请求
    {
        HttpClient client = new DefaultHttpClient();
        HttpUriRequest request = new HttpGet(url);
        HttpResponse response = client.execute(request);
        HttpEntity entity = response.getEntity();

        return entity;
    }

    public void getTeacherInfo(String scripts)//从html信息中选出每个教师的信息
    {
//        Document doc = Jsoup.parse(allString);
//        Elements es = doc.select("script");
//        Element e = es.first();
//        String scripts = e.html();
        Document doc = Jsoup.parse(scripts);
        //Elements es = doc.getElementsByTag("option");
        Elements es = doc.getElementsByAttribute("value");

        for(int i=0; i<es.size(); ++i)
        {
            Teacher teacher = new Teacher();
            teacher.id = es.get(i).val();
            teacher.name = es.get(i).text();
            teacherMana.list.add(teacher);
            //System.out.println("Id: " +es.get(i).val() +"    name: "+es.get(i).text());
        }
        Log.i("Caimogu", "-----------getTeacherInfo---------");
//        for(Teacher t:list)
//        {
//            System.out.println(t.getId()+"---"+t.getName());
//        }


    }

    public String selectHtmlString(String allString)//选出html代码段
    {
        Document doc = Jsoup.parse(allString);
        Elements es = doc.select("script");
        Element e = es.first();
        String scripts = e.html();
        return scripts;
        /*
        String str[] = htm_str.split("'");
        String html = str[1];
        */

        //System.out.println("截取的代码："+html);
        //return html;
    }

/*
    public static String InputStreamToString(InputStream in_st) throws IOException
    {
        // BufferedReader buff = new BufferedReader(new InputStreamReader(in_st, charset));
        BufferedReader buff = new BufferedReader(new InputStreamReader(in_st));
        StringBuffer res = new StringBuffer();
        String line = "";
        while((line = buff.readLine()) != null)
        {
            res.append(line);
        }
        Log.i("Caimogu","-----------InputStreamToString---------");
        return res.toString();
    }

    public static void saveAsFile(File filepath, String str)
    {


        try {
            //OutputStreamWriter outs = new OutputStreamWriter(new FileOutputStream(filepath, true), "utf-8");

            OutputStreamWriter outs = new OutputStreamWriter(new FileOutputStream(filepath, true),charset);
            outs.write(str);
            outs.close();
            Log.i("Caimogu", "-----------saveAsFile---------");
        } catch (IOException e) {
            System.out.println("Error at save html...");
            e.printStackTrace();
        }
    }






    public String htmlToString() throws IOException
    {
        //InputStreamReader is = new InputStreamReader(new FileInputStream(filepath));
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
        StringBuilder sb = new StringBuilder();

        String line = null;
        while ((line = reader.readLine()) != null)
        {
            sb.append(line + "\n");
        }
        return sb.toString();
    }
    */
}
