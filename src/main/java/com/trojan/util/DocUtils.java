package com.trojan.util;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class DocUtils {
	/**
	 * 添加请求头
	 * 
	 * @param conn
	 */
	public static void addHeader(Connection conn) {
		conn.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		conn.header("Accept-Encoding", "gzip, deflate, sdch");
		conn.header("Accept-Language", "zh-CN,zh;q=0.8");
		conn.header("User-Agent",
				"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
	}

	/**
	 * 获取目标网页内容
	 * 
	 * @param url
	 * @return
	 */
	public static Document getDoc(String url) {
		int i = 10;
		Document doc = null;
		while (i > 0) {
			try {
				Connection conn = Jsoup.connect(url).timeout(30000);
				addHeader(conn);
				doc = conn.get();

			} catch (Exception e) {
				e.printStackTrace();
				try {
					Connection conn = Jsoup.connect(url).timeout(30000);
					addHeader(conn);
					doc = conn.post();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			if (doc != null) {
				break;
			}
		}
		if(doc==null){
			throw new RuntimeException("获取目标文件失败");
		}
		return doc;
	}
}
