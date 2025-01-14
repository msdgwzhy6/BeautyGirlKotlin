package com.moon.beautygirlkotlin.utils

import com.moon.beautygirlkotlin.doubanmeizi.model.DoubanMeiziBody
import com.moon.beautygirlkotlin.wei1.model.MeiZiTuBody
import okhttp3.ResponseBody
import org.jsoup.Jsoup
import retrofit2.Response
import java.net.URL
import java.util.*

/**
 * author: moon
 * created on: 18/4/29 下午10:22
 * description:
 */
object DataUtil {

    /**
     * 解析豆瓣 数据
     */
    fun getDouBanList(type: Int, response: Response<ResponseBody>?): List<DoubanMeiziBody> {

        val list = ArrayList<DoubanMeiziBody>()

        if (response?.body() == null) throw java.lang.Exception()

        val string = response?.body()?.string()
        val parse = Jsoup.parse(string)
        val elements = parse.select("div[class=thumbnail]>div[class=img_single]>a>img")
        var meizi: DoubanMeiziBody
        for (e in elements) {
            val src = e.attr("src")
            val title = e.attr("title")

            meizi = DoubanMeiziBody(title, src, type)
            list.add(meizi)
        }
        return list
    }

    /**
     * since 3.0
     * 解析【唯一图库】 html
     */
    fun parserMeiziTuHtml(url: String): List<MeiZiTuBody> {
        val list = ArrayList<MeiZiTuBody>()
        val doc = Jsoup.parse(URL(url).openStream(), "GB2312", url)
        val element = doc.select("div[class=item masonry_brick masonry-brick]>div[class=item_t]>div[class=img]>" +
                "div[class=ABox]>a>img")
        for (e in element) {
            val imageUrl: String = e.attr("src")
            val title: String = e.attr("alt")
            val bean = MeiZiTuBody(0, 354, 236, imageUrl, imageUrl, title, "", 0, 0)
            list.add(bean)
        }
        return list
    }

    /**
     * since 3.0
     *  http://www.umei.cc/bizhitupian/meinvbizhi/
     * 解析【优图美库】 html
     */
    fun parserMeiTuLuHtml(url: String): List<MeiZiTuBody> {
        val list = ArrayList<MeiZiTuBody>()
        val doc = Jsoup.parse(URL(url).openStream(), "UTF-8", url)
        val element = doc.select("div[class=TypeList]>ul>li>a[class=TypeBigPics]")

        for (e in element) {
            val imageUrl: String = e.select("img")[0].attr("src")
            val title: String = e.select("span")[0].html()

            // 将缩略图改为大图
            val imgList = imageUrl.split("/")
            val lastImg = imgList.last()
            if (lastImg.startsWith("rn")) {
                val newUrl = imageUrl.replace("rn", "")
                list.add(MeiZiTuBody(0, 354, 236, newUrl, newUrl, title, "", 0, 0))
            } else {
                list.add(MeiZiTuBody(0, 354, 236, imageUrl, imageUrl, title, "", 0, 0))
            }
        }
        return list
    }
}