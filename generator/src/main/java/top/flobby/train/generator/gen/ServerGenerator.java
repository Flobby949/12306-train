package top.flobby.train.generator.gen;

import freemarker.template.TemplateException;
import top.flobby.train.generator.utils.FreemarkerUtil;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : Flobby
 * @program : train
 * @description :
 * @create : 2023-11-21 16:10
 **/

public class ServerGenerator {
    static String toPath = "generator/src/main/java/top/flobby/train/generator/test/";

    static {
        new File(toPath).mkdirs();
    }

    public static void main(String[] args) throws IOException, TemplateException {
        FreemarkerUtil.initConfig("test.ftl");
        Map<String, Object> param = new HashMap<>();
        param.put("domain", "Test1");
        FreemarkerUtil.generator(toPath + "Test1.java", param);
    }
}
