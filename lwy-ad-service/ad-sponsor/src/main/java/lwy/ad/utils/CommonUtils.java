package lwy.ad.utils;

import lwy.ad.exception.AdException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.time.DateUtils;

import java.util.Date;

public class CommonUtils {

    private static String[] parsePattern = {
        "yyyy-MM-dd","yyyy/MM/dd","yyyy.MM.dd"
    };

    //MD5消息摘要算法，一种被广泛使用的密码散列函数，可以产生出一个128位（16字节）
    //的散列值，用于确保信息传输完整一致
    public static String md5(String value){

        return DigestUtils.md5Hex(value).toUpperCase();
    }
    //将String类型的日期转换为Date类型
    public static Date pareStringDate(String dateString)
            throws AdException {
        try{
            return DateUtils.parseDate(
                    dateString,parsePattern
            );
        }catch (Exception ex){
            throw new AdException(ex.getMessage());
        }
    }
}
