package com.trojan.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.trojan.entity.Bookshelf;
import com.trojan.entity.Category;
import com.trojan.entity.Chapter;
import com.trojan.entity.Directory;
import com.trojan.entity.User;
import com.trojan.util.DocUtils;

/**
 * 小说爬取 来源笔趣阁
 *
 * @author Trojan
 */
@Controller

public class ReptileController extends MainService {

    // 定义内容来源
    public final static String TARGETURL = "http://www.xbiquge.la";

    /**
     * 获取章节列表
     *
     * @param url
     * @return
     */
    public JSONObject getChapterList(String url) {
        logger.debug("--getChapterList run----");
        if (!url.startsWith(TARGETURL)) {
            url = TARGETURL + url;
        }
        // 页面信息
        JSONObject pageInfo = new JSONObject();
        // 获取目标网页内容
        Document doc = DocUtils.getDoc(url);
        if (doc == null) {
            throw new RuntimeException("no doc exist");
        }
        Elements chapterListInfo = doc.select("#list a");

        // 获取小说名称
        String novelName = doc.select("#info h1").text();
        // 从数据库中获取当前小说的信息
        System.out.println(directoryService);
        Directory novelInfo = directoryService.selectNovelByName(novelName);
        // 获取当前小说的id
        int nId = novelInfo.getnId();

        // 获取小说头部信息
        Elements infos = doc.select("#info p").select("p");

        // 作者
        String authorInfo = infos.get(0).text();
        String author = authorInfo.substring(authorInfo.indexOf("：") + 1, authorInfo.length());
        pageInfo.put("author", author);
        novelInfo.setAuthor(author);

        // 最后更新时间
        String timeInfo = infos.get(2).text();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date lastUpdateTime;
        try {
            lastUpdateTime = simpleDateFormat.parse(timeInfo.substring(timeInfo.indexOf("：") + 1, timeInfo.length()));
            novelInfo.setLastUpdateTime(lastUpdateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // 简介
        Elements intros = doc.select("#intro");
        String intro = intros.select("p").remove(1).text();
        if (intro.length() > 125) {
            intro = intro.substring(0, 125) + "...";
        }
        novelInfo.setIntroduction(intro);

        // 章节信息
        List<Chapter> chapterInfo = new ArrayList<Chapter>();
        String coverPic = doc.select("#fmimg img").attr("src");
        novelInfo.setCoverPic(coverPic);

        // 类别
        String cateName = doc.select(".con_top").select("a").last().text();
        logger.debug(cateName);
        Category cate = categoryService.selectCateByName(cateName);
        // 如果类别为空新增类别信息
        if (cate == null) {
            cate = new Category();
            cate.setcName(cateName);
            categoryService.addCategory(cate);
            cate = categoryService.selectCateByName(cateName);
        }
        novelInfo.setcId(cate.getcId());

        // 更新小说信息
        directoryService.updateNovelInfo(novelInfo);
        // 章节
        List<Chapter> chapterList = new ArrayList<Chapter>();
        for (Element element : chapterListInfo) {
            String chapterUrl = "http://www.xbiquge.la" + element.select("a").attr("href");
            String chapterName = element.select("a").html();
            Chapter chapter = new Chapter();
            chapter.setChapterName(chapterName);
            chapter.setChapterUrl(chapterUrl);
            chapter.setnId(nId);
            chapterList.add(chapter);
            chapterInfo.add(chapter);
        }

        if (chapterList.size() > 0) {
            chapterService.batchAddChapter(chapterList);
        }
        pageInfo.put("novelInfo", novelInfo);
        pageInfo.put("chapterInfo", chapterInfo);
        logger.debug("--getChapterList end----");
        return pageInfo;

    }

    /**
     * 跳转到网站主页
     *
     * @param map
     * @return
     */
    @RequestMapping("/index")
    public String pageIndex(ModelMap map) {
        logger.debug("--pageIndex run----");
        // List<HashMap<String, Object>> selectAll =
        // categoryService.selectAll();
        // map.put("list", selectAll);
        logger.debug("--pageIndex run----");
        return "index";
    }

    /**
     * 从数据库中获取novel列表
     *
     * @return
     */
    @RequestMapping("/getDirectory")
    @ResponseBody
    public JSONObject getDirectory(@RequestBody Map<String, Object> map) {
        String isFirst = map.get("isFirst").toString();
        logger.debug("--getDirectory run----");
        List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        if ("true".equals(isFirst)) {
            list = directoryService.selectAllByPage(0, 100);
        } else {
            list = directoryService.selectAll();
        }
        int totalCount = directoryService.getNovelCount();
        logger.debug("--getDirectory end----");
        JSONObject json = new JSONObject();
        json.put("novelList", list);
        json.put("totalCount", totalCount);
        return json;
    }

    /**
     * 从数据库中获取novel列表 分页
     *
     * @return
     */
    @RequestMapping("/getDirectoryByPage")
    @ResponseBody
    public List<HashMap<String, Object>> getDirectoryByPage(int currentPage, int pageSize) {
        logger.debug("--getDirectory run----");
        List<HashMap<String, Object>> list = directoryService.selectAllByPage(currentPage, pageSize);
        logger.debug("--getDirectory end----");
        return list;
    }

    /**
     * 搜索
     *
     * @return
     */
    @RequestMapping("/searchNovel")
    @ResponseBody
    public List<HashMap<String, Object>> searchNovel(@RequestBody Directory directory) {
        logger.debug("--searchNovel run----");
        String novelName = directory.getnName();
        if (novelName == null) {
            novelName = "";
        }
        List<HashMap<String, Object>> list = directoryService.selectByKeyWord(novelName);
        logger.debug("--searchNovel end----");
        return list;
    }

    /**
     * 获取章节列表
     *
     * @param directory
     * @return
     */
    @RequestMapping("/getChapter")
    @ResponseBody
    public JSONObject getChapter(@RequestBody Directory directory) {
        logger.debug("-----getChapter run-----");
        int novelId = directory.getnId();
        Directory novel = new Directory();
        if (novelId != 0) {
            novel = directoryService.selectById(novelId);
        } else {
            String novelName = directory.getnName();
            String novelUrl = directory.getnURL();

            // 有名称通过名称获取,无名称通过地址获取
            if ("".equals(novelName) || novelName == null) {
                novel.setnURL(novelUrl);
            } else {
                novel = directoryService.selectNovelByName(novelName);
            }
        }

        JSONObject pageInfo = getChapterList(novel.getnURL());
        logger.debug("-----getChapter end-----");
        return pageInfo;
    }

    /**
     * 获取小说内容
     *
     * @param chapter
     * @return
     */
    @RequestMapping("/getNovelContent")
    @ResponseBody
    public JSONObject getNovelContent(HttpServletRequest req, @RequestBody Chapter chapter) {
        logger.debug("-----getNovelContent run-----");

        String chapterUrl = chapter.getChapterUrl();
        // 上送地址是否包含目标网站域名
        if (!chapterUrl.startsWith(TARGETURL)) {
            chapterUrl = TARGETURL + chapterUrl;
        }
        JSONObject pageInfo = getNovelByChapterUrl(req, chapterUrl);
        return pageInfo;
    }

    @RequestMapping("/getContentByUrl")
    @ResponseBody
    public JSONObject getContentByUrl(HttpServletRequest req, @RequestBody Map<String,Object> requestMap){
        String chapterUrl= (String) requestMap.get("chapterUrl");
        JSONObject pageInfo = getNovelByChapterUrl(req, chapterUrl);
        return pageInfo;
    }
    public JSONObject getNovelByChapterUrl(HttpServletRequest req, @RequestBody String chapterUrl) {
//        System.out.println("chapterUrl" + chapterUrl);
//        return null;
        // 获取目标网页内容
        Document doc = DocUtils.getDoc(chapterUrl);

        JSONObject pageInfo = new JSONObject();
        // 章节信息
        Elements bookInfo = doc.select(".bookname");

        // 章节路径
        Elements linkInfos = bookInfo.select(".bottem1").select("a");

        // 章节名
        String chapterName = bookInfo.select("h1").text();
        // 上一章
        String preChapterUrl = linkInfos.get(1).attr("href");
        pageInfo.put("preChapterUrl", preChapterUrl);

        // 目录
        String chapterListUrl = linkInfos.get(2).attr("href");
        pageInfo.put("chapterListUrl", chapterListUrl);
        // 下一章
        String nextChapterUrl = linkInfos.get(3).attr("href");
        pageInfo.put("nextChapterUrl", nextChapterUrl);

        // 内容
        Elements content = doc.select("#content");

        // 去垃圾信息
        content.select("p").remove();

        // 文本内容
        String novelContent = content.html();

        pageInfo.put("content", novelContent);
        pageInfo.put("chapterName", chapterName);

        // 获取session
        HttpSession session = req.getSession();
        User userInfo = (User) session.getAttribute("user");

        // 判断用户是否登录
        if (userInfo != null) {
            int userId = userInfo.getId();
//			session.setAttribute("user", userInfo);
            Chapter chapterInfo = chapterService.findByChapterUrl(chapterUrl);
            int novelId = chapterInfo.getnId();
            Directory dirInfo = directoryService.selectById(novelId);

            // 判断是否存在于书架
            Bookshelf bookshelf = bookshelfService.findByUserIdAndNovelId(userId, novelId);
            if (bookshelf != null) {
                bookshelf.setChapter(chapterInfo);
                bookshelf.setReadtime(new Date());
                bookshelfService.updateBookInfo(bookshelf);
            } else {
                bookshelf = new Bookshelf();
                bookshelf.setChapter(chapterInfo);
                bookshelf.setUser(userInfo);
                bookshelf.setReadtime(new Date());
                bookshelf.setDirectory(dirInfo);
                bookshelfService.addBook(bookshelf);
            }
        }
        return pageInfo;
    }

    /**
     * 查找用户下书架记录
     *
     * @param user
     * @return
     */
    @RequestMapping("/findByUserId")
    @ResponseBody
    public List<HashMap<String, Object>> findByUserId(@RequestBody User user) {
        List<HashMap<String, Object>> bookList = bookshelfService.findByUserId(user.getId());
        return bookList;
    }

    @RequestMapping("/getChapterById")
    @ResponseBody
    public JSONObject getChapterById(int nId) {
        Directory directory = directoryService.selectById(nId);
        JSONObject chapter = getChapterList(directory.getnURL());
        return chapter;

    }

    /**
     * 获取小说总数
     *
     * @return
     */
    public int getNovelCount() {
        int count = directoryService.getNovelCount();
        return count;
    }

    /**
     * 获取章节数
     *
     * @param id
     * @return
     */
    public int getChapterCount(int id) {
        int count = chapterService.getChapterCount(id);
        return count;
    }

}
