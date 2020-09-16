package hutool;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author bys
 * @date 2020/9/10 9:30
 */
public class HutoolDemo {

    public static void main(String[] args) throws Exception {

        // Convert 类型转换工具类，用于各种类型数据转换
        //转换为字符串
        int a = 1;
        String aStr = Convert.toStr(a);
        System.out.println(aStr);

        //转换为指定类型数组
        String[] b = {"1", "2", "3", "4"};
        Integer[] bArr = Convert.toIntArray(b);
        System.out.println(Convert.toStr(bArr));

        //转换为日期对象
        String dateStr = "2017-05-06";
        Date date = Convert.toDate(dateStr);
        System.out.println(DateUtil.format(date, "yyyyMMdd"));

        //转换为列表
        String[] strArr = {"a", "b", "c", "d"};
        List<String> strList = Convert.toList(String.class, strArr);
        System.out.println(Convert.toStr(strList));


        // 日期工具类
        System.out.println("----------");
        //Date、long、Calendar之间的相互转换
        //当前时间
        Date date1 = DateUtil.date();
        //Calendar转Date
        date1 = DateUtil.date(Calendar.getInstance());
        //时间戳转Date
        date1 = DateUtil.date(System.currentTimeMillis());

        //自动识别格式转换
        String dateStr1 = "2017-03-01";
        date1 = DateUtil.parse(dateStr1);

        //自定义格式化转换
        date1 = DateUtil.parse(dateStr1, "yyyy-MM-dd");

        //格式化输出日期
        String format = DateUtil.format(date1, "yyyy-MM-dd");

        //获得年的部分
        int year = DateUtil.year(date1);

        //获得月份，从0开始计数
        int month = DateUtil.month(date1);

        //获取某天的开始、结束时间
        Date beginOfDay = DateUtil.beginOfDay(date1);
        Date endOfDay = DateUtil.endOfDay(date1);

        //计算偏移后的日期时间
        Date newDate = DateUtil.offset(date1, DateField.DAY_OF_MONTH, 2);

        //计算日期时间之间的偏移量
        long betweenDay = DateUtil.between(date1, newDate, DateUnit.DAY);


        // JSON解析工具类
        System.out.println("----------");
        PmsBrand brand = new PmsBrand();
        brand.setId(1L);
        brand.setName("小米");
        brand.setShowStatus(1);

        //对象转化为JSON字符串
        String jsonStr = JSONUtil.parse(brand).toString();
        System.out.println(jsonStr);

        //JSON字符串转化为对象
        PmsBrand brandBean = JSONUtil.toBean(jsonStr, PmsBrand.class);
        System.out.println(brandBean.toString());

        List<PmsBrand> brandList = new ArrayList<>();
        brandList.add(brand);
        String jsonListStr = JSONUtil.parse(brandList).toString();
        System.out.println(jsonListStr);

        //JSON字符串转化为列表
        brandList = JSONUtil.toList(new JSONArray(jsonListStr), PmsBrand.class);
        System.out.println(Convert.toStr(brandList));


        // String 工具类
        //判断是否为空字符串
        System.out.println("----------");
        String str = "test";
        StrUtil.isEmpty(str);
        StrUtil.isNotEmpty(str);
        //去除字符串的前后缀
        System.out.println(StrUtil.removeSuffix("a.jpg", ".jpg"));
        System.out.println(StrUtil.removePrefix("a.jpg", "a."));
        //格式化字符串
        String template = "这只是个占位符:{}";
        System.out.println(StrUtil.format(template, "我是占位符"));

        // 读取配置文件
        //获取定义在src/main/resources文件夹中的配置文件
        System.out.println("----------");
        ClassPathResource resource = new ClassPathResource("static/generator.properties");
        Properties properties = new Properties();
        properties.load(resource.getStream());
        System.out.println(Convert.toStr(properties));


        // ReflectUtil 反射工具类
        //获取某个类的所有方法
        System.out.println("----------");
        Method[] methods = ReflectUtil.getMethods(PmsBrand.class);
        System.out.println(Convert.toStr(methods));
        //获取某个类的指定方法
        Method method = ReflectUtil.getMethod(PmsBrand.class, "getId");
        System.out.println(Convert.toStr(method));
        //使用反射来创建对象
        PmsBrand pmsBrand = ReflectUtil.newInstance(PmsBrand.class);
        //反射执行对象的方法
        ReflectUtil.invoke(pmsBrand, "setId", 1L);


        // NumberUtil
        System.out.println("----------");
        double n1 = 1.234;
        double n2 = 1.234;
        double result;
        //对float、double、BigDecimal做加减乘除操作
        result = NumberUtil.add(n1, n2);
        result = NumberUtil.sub(n1, n2);
        result = NumberUtil.mul(n1, n2);
        result = NumberUtil.div(n1, n2);
        //保留两位小数
        BigDecimal roundNum = NumberUtil.round(n1, 2);
        String n3 = "1.234";
        //判断是否为数字、整数、浮点数
        NumberUtil.isNumber(n3);
        NumberUtil.isInteger(n3);
        NumberUtil.isDouble(n3);


        //JavaBean工具类，可用于Map与JavaBean对象的互相转换以及对象属性的拷贝。
        System.out.println("----------");
        PmsBrand brand1 = new PmsBrand();
        brand1.setId(1L);
        brand1.setName("小米");
        brand1.setShowStatus(0);
        //Bean转Map
        Map<String, Object> map = BeanUtil.beanToMap(brand1);
        System.out.println(Convert.toStr(map));
        //Map转Bean
        PmsBrand mapBrand = BeanUtil.mapToBean(map, PmsBrand.class, false);
        System.out.println(mapBrand);
        //Bean属性拷贝
        PmsBrand copyBrand = new PmsBrand();
        BeanUtil.copyProperties(brand1, copyBrand);
        System.out.println(copyBrand.toString());

        // CollUtil集合操作的工具类，定义了一些常用的集合操作。
        System.out.println("----------");
        //数组转换为列表
        String[] array = new String[]{"a", "b", "c", "d", "e"};
        List<String> list = CollUtil.newArrayList(array);
        //join：数组转字符串时添加连接符号
        String joinStr = CollUtil.join(list, ",");
        System.out.println(joinStr);
        //将以连接符号分隔的字符串再转换为列表
        List<String> splitList = StrUtil.split(joinStr, ',');
        System.out.println(Convert.toStr(splitList));
        //创建新的Map、Set、List
        HashMap<Object, Object> newMap = CollUtil.newHashMap();
        HashSet<Object> newHashSet = CollUtil.newHashSet();
        ArrayList<Object> newList = CollUtil.newArrayList();
        //判断列表是否为空
        CollUtil.isEmpty(list);


        // Map操作工具类，可用于创建Map对象及判断Map是否为空。
        System.out.println("----------");
        //将多个键值对加入到Map中
        Map<Object, Object> map1 = MapUtil.of(new String[][]{
                {"key1", "value1"},
                {"key2", "value2"},
                {"key3", "value3"}
        });
        //判断Map是否为空
        MapUtil.isEmpty(map1);
        MapUtil.isNotEmpty(map1);


        //AnnotationUtil 注解工具类，可用于获取注解与注解中指定的值
//        //获取指定类、方法、字段、构造器上的注解列表
//        Annotation[] annotationList = AnnotationUtil.getAnnotations(HutoolController.class, false);
//        System.out.println(Convert.toStr(annotationList));
//        //获取指定类型注解
//        Api api = AnnotationUtil.getAnnotation(HutoolController.class, Api.class);
//        System.out.println(api.description());
//        //获取指定类型注解的值
//        Object annotationValue = AnnotationUtil.getAnnotationValue(HutoolController.class, RequestMapping.class);


        // SecureUtil 加密解密工具类，可用于MD5加密。
        System.out.println("----------");
        //MD5加密
        String str1 = "123456";
        String md5Str = SecureUtil.md5(str1);
        System.out.println(str1);


        // CaptchaUtil 验证码工具类，可用于生成图形验证码。
        System.out.println("----------");
        //生成验证码图片
//        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);
//        try {
//            request.getSession().setAttribute("CAPTCHA_KEY", lineCaptcha.getCode());
//            response.setContentType("image/png");//告诉浏览器输出内容为图片
//            response.setHeader("Pragma", "No-cache");//禁止浏览器缓存
//            response.setHeader("Cache-Control", "no-cache");
//            response.setDateHeader("Expire", 0);
//            lineCaptcha.write(response.getOutputStream());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        // Validator 字段验证器，可以对不同格式的字符串进行验证，比如邮箱、手机号、IP等格式
        System.out.println("----------");
        //判断是否为邮箱地址
        boolean result1 = Validator.isEmail("macro@qq.com");
        System.out.println("Validator isEmail:{}" + result1);
        //判断是否为手机号码
        result1 = Validator.isMobile("18911111111");
        System.out.println("Validator isMobile:{}" + result1);
        //判断是否为IPV4地址
        result1 = Validator.isIpv4("192.168.3.101");
        System.out.println("Validator isIpv4:{}" + result1);
        //判断是否为汉字
        result1 = Validator.isChinese("你好");
        System.out.println("Validator isChinese:{}" + result1);
        //判断是否为身份证号码（18位中国）
        result1 = Validator.isCitizenId("123456");
        System.out.println("Validator isCitizenId:{}" + result1);
        //判断是否为URL
        result1 = Validator.isUrl("http://www.baidu.com");
        System.out.println("Validator isUrl:{}" + result1);
        //判断是否为生日
        result1 = Validator.isBirthday("2020-02-01");
        System.out.println("Validator isBirthday:{}" + result1);


        // DigestUtil 摘要算法工具类，支持MD5、SHA-256、Bcrypt等算法。
        System.out.println("----------");
        String password = "123456";
        //计算MD5摘要值，并转为16进制字符串
        String result2 = DigestUtil.md5Hex(password);
        System.out.println("DigestUtil md5Hex:{}" + result2);
        //计算SHA-256摘要值，并转为16进制字符串
        result2 = DigestUtil.sha256Hex(password);
        System.out.println("DigestUtil sha256Hex:{}" + result2);
        //生成Bcrypt加密后的密文，并校验
        String hashPwd = DigestUtil.bcrypt(password);
        boolean check = DigestUtil.bcryptCheck(password,hashPwd);
        System.out.println("DigestUtil bcryptCheck:{}" + check);


        // HttpUtil Http请求工具类，可以发起GET/POST等请求。
        System.out.println("----------");
//        String response = HttpUtil.get("http://localhost:8080/hutool/covert");
//        System.out.println("HttpUtil get:{}" + response);

    }
}

class PmsBrand{
    private Long id;
    private String name;
    private Integer showStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(Integer showStatus) {
        this.showStatus = showStatus;
    }

    @Override
    public String toString() {
        return "PmsBrand{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", showStatus=" + showStatus +
                '}';
    }
}