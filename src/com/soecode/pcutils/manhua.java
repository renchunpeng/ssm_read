package com.soecode.pcutils;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by 任春鹏 on 2018/7/31.
 */
public class manhua {
    private static String bookUrl = "http://coco.hhxxee.com/comic/34058/";
    private static String path = "C:/manhua";
    private static int count = 0;

    public static void main(String[] args) {
        ArrayList<String> catalog = getCatalog(bookUrl);
        for (int i = 0; i < catalog.size(); i++) {
            downBycatalog(catalog.get(i));
        }
    }

    /**
     * 下载图片
     * @param urlList 图片地址
     * @param path 本地存放路径
     */
    private static void downloadPicture(String urlList,String path) {
        createPath(path);
        path += "/"+urlList.substring(urlList.lastIndexOf("/"),urlList.length());
        URL url = null;
        try {
            url = new URL(urlList);
            DataInputStream dataInputStream = new DataInputStream(url.openStream());

            FileOutputStream fileOutputStream = new FileOutputStream(new File(path));
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int length;

            while ((length = dataInputStream.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            fileOutputStream.write(output.toByteArray());
            dataInputStream.close();
            fileOutputStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建文件夹
     * @param filePath
     */
    private static void createPath(String filePath){
        File fp = new File(filePath);
        // 目录已存在创建文件夹
        if (!fp.exists()) {
            // 目录不存在的情况下，会抛出异常
            fp.mkdir();
        }
    }

    /**
     * 使用jsonp获取文档内容
     * @param url
     */
    private static Document getDoc(String url){
        Document doc = null;
        try {
            // HtmlUnit 模拟浏览器
            WebClient webClient = new WebClient(BrowserVersion.CHROME);
            webClient.getOptions().setJavaScriptEnabled(true);              // 启用JS解释器，默认为true
            webClient.getOptions().setCssEnabled(false);                    // 禁用css支持
            webClient.getOptions().setThrowExceptionOnScriptError(false);   // js运行错误时，是否抛出异常
            webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
            webClient.getOptions().setTimeout(10 * 1000);                   // 设置连接超时时间
            HtmlPage page = webClient.getPage(url);
            webClient.waitForBackgroundJavaScript(30 * 1000);               // 等待js后台执行30秒

            String pageAsXml = page.asXml();

            // Jsoup解析处理
            doc = Jsoup.parse(pageAsXml);
//            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        return doc;
    }

    /**
     * 获取书记所有目录
     * @param url
     */
    private static ArrayList<String> getCatalog(String url){
        Elements links = getDoc(url).select(".cVolList").select("div");//所有目录的div
        ArrayList<String> catalogLists = new ArrayList<>();
        for (int i = links.size()-1; i >= 0; i--) {
            String catalogUrl = links.get(i).select("a").attr("href");
            catalogLists.add(catalogUrl);
        }
        return catalogLists;
    }

    /**
     * 按章节下载图片
     * @param catalogUrl
     */
    private static void downBycatalog(String catalogUrl){
        count++;
        System.out.println("第"+ count +"章开始下载+++++++++++++++++++++++");
        int num = 50;
        for (int i = 1; i <= num; i++) {
            try {
                String tempUrl = catalogUrl+ "?p="+ i +"&s=1";
                Elements elements = getDoc(tempUrl).select(".e").select("#ComicPic");
                String imgUrl = getDoc(tempUrl).select(".e").select("#ComicPic").attr("src");
                downloadPicture(imgUrl, path);
                System.out.println("第"+ i +"页下载完成");
            }catch (Exception e){
                System.out.println("第"+ count +"章下载完成+++++++++++++++++++++++");
                break;
            }

        }
    }
}
