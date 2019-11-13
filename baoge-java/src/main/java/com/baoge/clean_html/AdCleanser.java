package com.baoge.clean_html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.MemoryCacheImageInputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 去广告文章广告信息
 * 1. 文章中销售咨询电话号码
 * 2. 文章尾部推荐阅读
 * 3. 文章头部图片（动图，广告图片）
 */

@Component
public class AdCleanser {

    private final static int MIN_PHONE_LENGTH = 8;
    private final static double AD_TEXT_DENSITY = 0.2;
    private final String PHONE_REGX = "([0-9+]{2,4}\\-)?([0-9]{2,9}\\-)?[\\+|\\d|\\s|\\-|,|\\*]+";
    private final String[] AD_WORDS = {"详情请致电", "VIP热线", "VIPLINE", "咨询热线", "项目电话", "销售热线", "详情咨询", "阅读原文", "推荐阅读", "热门文章",
            "拨打电话", "售楼处咨询"};
    private final String[] RDM_WORDS = {"热门文章", "阅读原文", "推荐阅读", "链接", "更多新闻", "更多资讯", "更多阅读", "今日推荐", "往期问答", "精彩回顾",
            "热门文章", "文章推荐", "更多楼市资讯"};

    public String cleanAdInfo(String html) {
        Document doc = Jsoup.parse(html);
        Element body = doc.body();
        List<Element> list = getHtmlElements(body);
        // 去图片
        processImage(body);
        for (Element ele : list) {
            // 去推荐文章列表
            try {
                processRecommendList(ele);
            } catch (Exception e) {
            }
            // 去广告软文语句
            List<TextNode> nodes = ele.textNodes();
            for (TextNode tn : nodes) {
                processTextNode(tn);
            }
        }
        html = body.html();
        return html;
    }

    private List<Element> getHtmlElements(Element ele) {
        List<Element> eles = new ArrayList<Element>();
        Iterator it = ele.children().iterator();
        while (it.hasNext()) {
            Element childEle = (Element)it.next();
            List<Element> subEles = getHtmlElements(childEle);
            if (subEles != null && subEles.size() > 0) {
                eles.addAll(subEles);
            }
        }
        eles.add(ele);
        return eles;
    }

    /**
     * 去电话号码，广告词软文
     * @param tn
     */
    private void processTextNode(TextNode tn) {
        String text = tn.text();
        if (text == null || text.trim().equals("")) {
            return;
        }
        List<String> contents = new ArrayList<String>();
        String[] strs = text.split("。|！|;|；|!|？|\\?");
        for (String s :  strs) {
            // 切割文本断句
            int fromIndex = text.indexOf(s);
            int endIndex = fromIndex + s.length();
            if (endIndex < text.length()) {
                endIndex += 1;
            }
            contents.add(text.substring(fromIndex, endIndex));
        }
        // 计算电话号码 和 广告词的密度，决定是否保留文本语句
        if (contents.size() > 0) {
            List<String> texts = new ArrayList<String>();
            for (String content : contents) {
                int adLength = 0;
                String phone = matchPhone(content);
                if (phone != null) {
                    adLength += phone.length();
                }
                for (String word : AD_WORDS) {
                    if (content.contains(word.toUpperCase())) {
                        adLength += word.length();
                    }
                }
                double adDensity = adLength * 1.0 / content.length();
                if (adDensity < AD_TEXT_DENSITY) {  // 广告词密度占比小的保留文本
                    texts.add(content);
                }}
            if (texts.size() >= 0) {
                StringBuffer sb = new StringBuffer();
                for (String ntext : texts) {
                    sb.append(ntext);
                }
                tn.text(sb.toString());
            }
        }
    }

    /***
     * 去文尾推荐阅读
     * @param ele
     */
    private void processRecommendList(Element ele) throws Exception {
        String text = ele.text();
        if (ele.tagName().equals("body")) {
            return;
        }
        for (String word : RDM_WORDS) {
            if (text.contains(word)) {
                String baseTagName = ele.tagName();
                // 本身为list中的一个元素
                if ((baseTagName.equals("p") || baseTagName.equals("span")) && ele.siblingElements().size() > 0) {
                    int i = 0;
                    List<Element> elist = new ArrayList<Element>();
                    Element sibling = ele;
                    while (sibling.nextElementSibling() != null && i < 10) {
                        sibling = sibling.nextElementSibling();
                        if (sibling.tagName().equals("p") || sibling.tagName().equals("span")) {
                            elist.add(sibling);
                            i++;
                        }
                    }
                    if (elist.size() >= 3) {
                        ele.remove();
                        for (Element e : elist) {
                            e.remove();
                        }
                    }
                } else {  // 向上找父节点 为P 或者 SPAN节点，兄弟节点为table，ul的删除
                    Set<String> blockNames = new HashSet<String>();
                    blockNames.add("table");
                    blockNames.add("ul");
                    blockNames.add("blockquote");
                    Element parent = ele.parent();
                    int i = 0;
                    while (i < 3 && parent != null) {
                        if (parent.tagName().equals("p") || parent.tagName().equals("span") || parent.tagName().equals("blockquote")) {
                            break;
                        }
                        parent = parent.parent();
                    }
                    if (parent != null) {
                        String tagName = parent.tagName();
                        if (blockNames.contains(tagName)) {
                            parent.remove();
                        } else if (tagName.equals("p") || tagName.equals("span")) {
                            int j = 0;
                            while (parent.nextElementSibling() != null && j < 2) {
                                String nextSibingName = parent.nextElementSibling().tagName();
                                if (blockNames.contains(nextSibingName)) {
                                    parent.nextElementSibling().remove();
                                    break;
                                }
                                parent = parent.nextElementSibling();
                                j++;
                            }
                        }
                    }
                }
            }
        }
    }

    private void processImage(Element ele) {
        Elements imgEles = ele.getElementsByTag("img");
        if (imgEles.size() > 0) {
            Element imageEle = imgEles.get(0);
            String imageUrl = imgEles.get(0).attr("src");
            try {
                byte[] bytes = getHtmlFile(imageUrl, null);
                if (bytes != null && bytes.length > 0) {
                    BufferedImage bi = ImageIO.read(new ByteArrayInputStream(bytes));
                    int height = bi.getHeight();
                    int width = bi.getWidth();
                    if (width > height * 2) {
                        imageEle.remove();
                    } else {
                        String imageType = getImageType(bytes);
                        if (imageType.equals("gif")) {
                            imageEle.remove();
                        }
                    }
                }
            } catch (Exception e) {
            }

        }
    }

    private String matchPhone(String str) {
        String phone = null;
        Pattern pattern = Pattern.compile(PHONE_REGX);
        Matcher m = pattern.matcher(str);
        while (m.find()) {
            String tmpString = m.group().trim();
            if (tmpString.length() >= MIN_PHONE_LENGTH) {
                //System.out.println(str + "      " + phone);
                phone = tmpString;
                break;
            }
        }
        return phone;
    }

    public static byte[] getHtmlFile(String url, String filePath) {
        if (url == null || "".equals(url)) {
            return null;
        }
        HttpURLConnection conn = null;
        InputStream ins = null;
        FileOutputStream bos = null;
        ByteArrayOutputStream baos = null;
        try {
            conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setConnectTimeout(3000);
            conn.setReadTimeout(3000);
            conn.setRequestMethod("GET");
            if (filePath != null && !filePath.trim().equals("")) {
                File tmpFile = new File(filePath);
                bos = new FileOutputStream(tmpFile);
            }
            /** 响应请求：取响应后的cookie并根据条件设置cookie信息   */
            ins = conn.getInputStream();
            baos = new ByteArrayOutputStream();

            byte[] b = new byte[1024];
            int len;
            while ((len = ins.read(b)) != -1) {
                baos.write(b, 0, len);
                if (bos != null) {
                    bos.write(b, 0, len);
                }
            }
            return baos.toByteArray();
        } catch (Exception e) {
        } finally {
            try {
                if (ins != null) {
                    ins.close();
                }
                if (baos != null) {
                    baos.close();
                }
                if (bos != null) {
                    bos.close();
                }
                if (conn != null) {
                    conn.disconnect();
                }
            } catch (Exception ie) {
            }
        }
        return null;
    }

    public static String getImageType(byte[] bytes) throws Exception {
        String type = "";
        ByteArrayInputStream bais = null;
        MemoryCacheImageInputStream mcis = null;
        try {
            bais = new ByteArrayInputStream(bytes);
            mcis = new MemoryCacheImageInputStream(bais);
            Iterator itr = ImageIO.getImageReaders(mcis);
            if (itr.hasNext()) {
                ImageReader reader = (ImageReader) itr.next();
                String imageName = reader.getClass().getSimpleName();
                if (imageName != null) {
                    if ("GIFImageReader".equals(imageName)) {
                        type = "gif";
                    } else if ("JPEGImageReader".equals(imageName)) {
                        type = "jpg";
                    } else if ("PNGImageReader".equals(imageName)) {
                        type = "png";
                    } else if ("BMPImageReader".equals(imageName)) {
                        type = "bmp";
                    }else {
                        type = "noPic";
                    }
                }
            }
        } catch (Exception e) {
            type = "noPic";
        } finally {
            if (bais != null) {
                try {
                    bais.close();
                } catch (Exception ioe) {
                }
            }
            if (mcis != null) {
                try {
                    mcis.close();
                } catch (Exception ioe) {
                }
            }
        }
        return type;
    }

    public String cleanContent(String content) {
        String[] wordsCleanJu = {"视频出处", "微信公众号", "微博", "欢迎关注", "知乎专栏", "微信号",
                "详情请致电", "VIP热线", "VIPLINE", "咨询热线", "项目电话", "销售热线", "详情咨询",
                "阅读原文", "推荐阅读", "热门文章", "拨打电话", "售楼处咨询", "热门文章", "阅读原文",
                "推荐阅读", "链接", "更多新闻", "更多资讯", "更多阅读", "今日推荐", "往期问答", "精彩回顾",
                "热门文章", "文章推荐", "更多楼市资讯"};
        String[] wordsCleanBackAll = {"延伸阅读"};
        String[] wordsCleanAll = {"转载请注明来源及作者，侵权必究"};

        String regex = "^.*[(¿)|(?)|(，)|(,)|(?)|(!)|(。)|(.)|(¡)|(!)|(!)|(！)].*$";

        // 去掉文本中含有wordsCleanJu中任意一个词的一句
        for (String word : wordsCleanJu) {
            if (content.contains(word)) {
                int i = 1, j = 1;
                int index = content.lastIndexOf(word);
                String subStr = null;
                String subStrBack = content.substring(index, index + i);
                while (!subStrBack.matches(regex)) {
                    i++;
                    subStrBack = content.substring(index, index + i);
                }
                String subStrBefore = content.substring(index - j, index);
                while (!subStrBefore.matches(regex)) {
                    j++;
                    subStrBefore = content.substring(index - j, index);
                }
                subStr = subStrBefore + subStrBack;
                subStr = subStr.substring(1, subStr.length());
                content = content.replace(subStr, "");
            }
        }

        // 去掉文本中含有wordsCleanBackAll中任意一词的所有后面文本
        for (String wordBack : wordsCleanBackAll) {
            if (content.contains(wordBack)) {
                int wordBackIndex = content.indexOf(wordBack);
                content = content.substring(1, wordBackIndex);
            }
        }

        // 清除文本中含有wordsCleanAll中任意一词的整个文本
        for (String word : wordsCleanAll) {
            if (content.contains(word)) {
                return null;
            }
        }

        // 过滤文本中电话号码
        String phone_regx = "([0-9+]{2,4}\\-)?([0-9]{2,9}\\-)?[\\+|\\d|\\s|\\-|,|\\*]+";
        String phone = null;
        Pattern pattern = Pattern.compile(phone_regx);
        Matcher m = pattern.matcher(content);
        while (m.find()) {
            phone = m.group().trim();
            content = content.replace(phone, "");
        }

        return content;
    }

}
