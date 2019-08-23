package utils.toword;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author shaoxubao
 * @Date 2019/8/23 14:09
 */
public class WordGenerator {
    private static Configuration configuration = null;
    private static Map<String, Template> allTemplates = null;

    static {
        configuration = new Configuration();
        configuration.setDefaultEncoding("utf-8");
        configuration.setClassForTemplateLoading(WordGenerator.class, "/ftl");
        allTemplates = new HashMap<>();
        try {
            allTemplates.put("test", configuration.getTemplate("test.ftl","UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private WordGenerator() {
        throw new AssertionError();
    }

    public static File createDoc(Map<?, ?> dataMap, String type) {
        String name = "temp" + (int) (Math.random() * 100000) + ".doc";
        File f = new File(name);
        Template t = allTemplates.get(type);
        try {
            // 这个地方不能使用FileWriter因为需要指定编码类型否则生成的Word文档会因为有无法识别的编码而无法打开
            Writer w = new OutputStreamWriter(new FileOutputStream(f), "utf-8");
            t.process(dataMap, w);

            byte[] bFile = new byte[(int) f.length()];
            for (int i = 0; i < bFile.length; i++) {
                w.write(bFile[i]);
            }
            System.out.println("文件："+ f.getName() + "已经被存储在"+ f.getPath()  );
            w.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return f;
    }

}
