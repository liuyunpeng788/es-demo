package com.stargazer.personal.esdemo.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.stargazer.personal.esdemo.dto.UserDto;
import org.apache.commons.codec.binary.Base64;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

/**
 * @author <a href="mailto:liumch@fosun.com">刘梦超</a>
 * @date 2019/3/11 21:27
 * @description: TODO
 * @modified: TODO
 * @version: 1.0
 **/
public class CommonUtils {
    /**
     * 生成base64 的url安全（不会转义）的字符串
     * @return 安全字符串
     */
    public static String getUrlSafeBase64String(){
//        long uuid = UUID.randomUUID().timestamp();
//        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
//        buffer.putLong(uuid);
//        return Base64.encodeBase64URLSafeString(buffer.array());
        long uuid = UUID.randomUUID().getLeastSignificantBits();
        byte[] bytes = new byte[] {
                (byte) uuid,
                (byte) (uuid >> 8),
                (byte) (uuid >> 16),
                (byte) (uuid >> 24),
                (byte) (uuid >> 32),
                (byte) (uuid >> 40),
                (byte) (uuid >> 48),
                (byte) (uuid >> 56)};
        return Base64.encodeBase64URLSafeString(bytes);
    }

    public static Date parseEsDate(String str){
        Date date= null;
        try{
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZZ", Locale.CHINA);
            date = dateFormat.parse(str);
        }catch (Exception ex){
            ex.printStackTrace();

        }
        return date;

    }
    /**
     * 测试es查询结果映射为bean
     */
    public static void jsonToBeanTest() throws Exception{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZZ", Locale.CHINA);
        System.out.println(dateFormat.parse("2019-03-12T17:52:03.191+0800"));

        JSONObject json = JSONObject.parseObject("{\"id\":\"yDzGg5C5-4w\",\"name\":\"吴六\",\"age\":22,\"birthday\":\"2019-03-12T17:52:03.191+0800\",\"description\":\" 在2019声音·责任医药界人大代表政协委员座谈会上，复星医药总裁兼首席执行官吴以芳表示，国家相关部门在政策上进一步支持加快药品的审评审批，对于医药行业来说意义非凡，但是在此基础上，我们可以尝试进行更深层次的改革。例如，开展稳定性试验的时候，同步进行审评审批，数据后期补充提交；允许在更大的范围内对新药注册的申报准许“滚动式”提交；对有突破性疗效的新药实现有条件批准等等。对于审批制度和流程也可以合理地将部分审批改为备案和平行审查，优化和简化相关程序和要求。这样对创新研发也是一种鼓励，也会大大提高效率。\"}");
        System.out.println(json.toJSONString());
        UserDto dto = json.toJavaObject(UserDto.class);
        System.out.println(dto);
    }

    /**
     * 测试按任意字符分隔
     */
    public static void splitTest(){
        String strQuery = "中午我吃了一碗饭，两碗粥，三个馒头,你呢？";
        String[] terms = strQuery.split("[,&，]");
        for(String str: terms ){
            System.out.println(str);
        }
    }
    public static void main(String[] args) throws Exception{

        splitTest();

    }
}
