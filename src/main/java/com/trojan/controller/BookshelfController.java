package com.trojan.controller;

import java.util.Date;
import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.trojan.entity.Bookshelf;

@Controller
public class BookshelfController extends MainService {

    /**
     * 通过id查找书架内容
     *
     * @param id
     * @return
     */
    @RequestMapping("/findBookShelfById")
    @ResponseBody
    public Bookshelf findById(int id) {
        Bookshelf bookShelf = bookshelfService.findById(id);
        return bookShelf;
    }

    /**
     * 新增书架记录
     *
     * @param bookshelf
     */
    @RequestMapping("/addBook")
    @ResponseBody
    public void addBook(@RequestBody Bookshelf bookshelf) {
        bookshelf.setReadtime(new Date());
        bookshelfService.addBook(bookshelf);
    }

    /**
     * 删除书架记录
     *
     * @param bookshelf
     */
    @RequestMapping("/removeBookInfo")
    @ResponseBody
    public void removeBookInfo(@RequestBody Bookshelf bookshelf) {
        bookshelfService.removeBookInfo(bookshelf);
    }

    /**
     * 更新书架记录
     *
     * @param bookshelf
     */
    @RequestMapping("/updateBookInfo")
    @ResponseBody
    public void updateBookInfo(@RequestBody Bookshelf bookshelf) {
        bookshelfService.updateBookInfo(bookshelf);
    }

    /**
     * 查找用户下书架记录
     *
     * @param userId
     * @param novelId
     * @return
     */
    @RequestMapping("/findByUserIdAndNovelId")
    @ResponseBody
    public Bookshelf findByUserIdAndNovelId(int userId, int novelId) {
        Bookshelf bookShelfInfo = bookshelfService.findByUserIdAndNovelId(userId, novelId);
        logger.info(bookShelfInfo.toString());
        return bookShelfInfo;
    }

    @RequestMapping("/testSql")
    @ResponseBody
    public void testSql() {
        logger.info("begin");
        HashMap map = bookshelfService.testSql();
        for (Object key : map.keySet()) {
            System.out.println("key= " + key + " and value= " + map.get(key));
        }
        logger.info("end ");
    }

}
