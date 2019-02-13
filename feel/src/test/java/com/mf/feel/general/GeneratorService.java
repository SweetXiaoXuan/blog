package com.mf.feel.general;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.apache.commons.lang3.StringUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;
import java.util.regex.Pattern;

/**
 * <p>
 * 代码生成器演示
 * </p>
 */
public class GeneratorService {
    /**
     * <p>
     * MySQL 生成演示
     * </p>
     */
    public static void main(String[] args) throws FileNotFoundException {
        String path = Objects.requireNonNull(GeneratorService.class.getClassLoader().getResource("")).getPath();
        if (path.startsWith("/")) {
            path = path.substring(1);
        }
        int index = path.indexOf("target");
        if (index != -1) {
            path = path.substring(0, index);
        }
        MybatisPlusConfig config = loadResource();
//        String[] table = config.getTables();
        AutoGenerator mpg = new AutoGenerator();
        // 选择 freemarker 引擎，默认 Veloctiy
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        Properties props = System.getProperties(); //获得系统属性集
        String osSep = props.getProperty("file.separator");
        System.out.println(osSep);

        if (osSep.equals("/")) {
            gc.setOutputDir(config.getUnixOutputDir());//for *nix
            System.out.println("unix");
        } else {
            gc.setOutputDir(path + config.getOutputDir());//for windows
            System.out.println("windows");
        }
        gc.setFileOverride(true);
        gc.setActiveRecord(true);// 不需要 ActiveRecord 特性的请改为false
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(false);// XML columList
//         .setKotlin(true) 是否生成 kotlin 代码
        gc.setAuthor(scannerInfo("author"));

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setControllerName("%sController");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
//        dsc.setTypeConvert(new MySqlTypeConvert() {
//            // 自定义数据库表字段类型转换【可选】
//            @Override
//            public DbColumnType processTypeConvert(String fieldType) {
//                System.out.println("转换类型：" + fieldType);
//                // 注意！！processTypeConvert 存在默认类型转换，如果不是你要的效果请自定义返回、非如下直接返回。
//                return super.processTypeConvert(gc,fieldType);
//            }
//        });
        dsc.setDriverName(config.getDriverName());
        dsc.setUsername(config.getUserName());
        dsc.setPassword(config.getPassword());
        dsc.setUrl(config.getUrl());
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // strategy.setCapitalMode(true);// 全局大写命名 ORACLE 注意
        if (config.getTablePre() != null) {
            strategy.setTablePrefix(config.getTablePre());// 此处可以修改为您的表前缀
        }
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        strategy.setInclude(scanner("表名")); // 需要生成的表
        // strategy.setExclude(new String[]{"test"}); // 排除生成的表
        strategy.setEntityLombokModel(true);
        // 自定义实体父类
//         strategy.setSuperEntityClass("com.js.archive");
        // 自定义实体，公共字段
        // strategy.setSuperEntityColumns(new String[] { "test_id", "age" });
        // 自定义 mapper 父类
//         strategy.setSuperMapperClass("com.js.archive.mapper");
        // 自定义 service 父类
//         strategy.setSuperServiceClass("com.js.archive.service");
        // 自定义 service 实现类父类
//         strategy.setSuperServiceImplClass("com.js.archive.service.impl");
        // 自定义 controller 父类
//         strategy.setSuperControllerClass("com.js.archive.controller");
        // 【实体】是否生成字段常量（默认 false）
        // public static final String ID = "test_id";
        // strategy.setEntityColumnConstant(true);
        // 【实体】是否为构建者模型（默认 false）
        // public User setName(String name) {this.name = name; return this;}
        // strategy.setEntityBuilderModel(true);
        mpg.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(config.getParent());
        pc.setController(config.getController());
        pc.setService(config.getService());
        pc.setEntity(config.getEntity());
        pc.setMapper(config.getMapper());
        pc.setXml(config.getXml());

//        pc.setModuleName("test");
        mpg.setPackageInfo(pc);

        // 注入自定义配置，可以在 VM 中使用 cfg.abc 【可无】
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
            }
        };

        // 自定义 xxList.jsp 生成
//        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
//        focList.add(new FileOutConfig("/template/list.jsp.vm") {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                // 自定义输入文件名称
//                return "D://my_" + tableInfo.getEntityName() + ".jsp";
//            }
//        });
//        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 调整 xml 生成目录演示
//        focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                return "/develop/code/xml/" + tableInfo.getEntityName() + ".xml";
//            }
//        });
//        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 关闭默认 xml 生成，调整生成 至 根目录
        TemplateConfig tc = new TemplateConfig();
//        tc.setXml(null);
        mpg.setTemplate(tc);

        // 自定义模板配置，可以 copy 源码 mybatis-plus/src/main/resources/templates 下面内容修改，
        // 放置自己项目的 src/main/resources/templates 目录下, 默认名称一下可以不配置，也可以自定义模板名称
        // TemplateConfig tc = new TemplateConfig();
        // tc.setController("...");
        // tc.setEntity("...");
        // tc.setMapper("...");
        // tc.setXml("...");
        // tc.setService("...");
        // tc.setServiceImpl("...");
        // 如上任何一个模块如果设置 空 OR Null 将不生成该模块。
        // mpg.setTemplate(tc);

        // 执行生成
        mpg.execute();

        // 打印注入设置【可无】
        System.err.println(mpg.getCfg().getMap().get("abc"));
    }

    /**
     * 读取配置文件
     *
     * @return com.js.archive.test.generator.MybatisPlusConfig
     * @author xurunfei
     */
    private static MybatisPlusConfig loadResource() throws FileNotFoundException {
        URL file = GeneratorService.class.getClassLoader().getResource("MybatisPlusConfig.yml");
        Yaml yaml = new Yaml();
        assert file != null;
        Map map = yaml.load(new FileInputStream(file.getFile()));
        String mapStr = yaml.dump(map);
        return yaml.loadAs(mapStr, MybatisPlusConfig.class);
    }

    private static String rmPreName(String name) {
        System.out.println(name);
        return name.substring(name.indexOf("_"), name.length() - 1);
    }

    /**
     * <p>
     * 读取控制台表内容
     * </p>
     */
    public static String[] scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入" + tip + "(输入n结束)：");
        List<String> tables = new ArrayList<>();

        while (scanner.hasNext(Pattern.compile("[^n].*"))) {
            String ipt = scanner.nextLine();
            if (StringUtils.isEmpty(ipt)) {
                break;
            }
            tables.add(ipt);
            System.out.println("请继续输入" + tip + "(输入n结束)：");
        }
        System.out.println(tables);
        if (tables.size() == 0) {
            throw new MybatisPlusException("未输入表名" + tip + "！");
        }
        return tables.toArray(new String[0]);
    }

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scannerInfo(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入" + tip + "：");
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }
}