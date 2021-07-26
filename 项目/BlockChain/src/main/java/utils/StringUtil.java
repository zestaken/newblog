package utils;

import jdk.nashorn.internal.runtime.ECMAException;

import java.security.Key;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.Signature;
import java.util.Base64;

public class StringUtil {

    /**
     * 应用SHA256算法接收输入字符串计算并返回哈希字符串
     * @param input
     * @return
     * @throws Exception
     */
    public static String applySha256(String input) throws Exception {
            //返回实现指定摘要算法的 MessageDigest 对象。此处是SHA-256算法
            MessageDigest digest = MessageDigest.getInstance("SHA-256"); //getInstance有异常
            //根据输入的bytes数组完成哈希计算。
            byte[] hash = digest.digest(input.getBytes("UTF-8"));//getBytes有异常
            StringBuffer hexString = new StringBuffer();
            for(int i = 0; i < hash.length; i++) {
                //将生成的哈希字节数组每一字节（8bit）转换16进制数字符串
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) {
                    //当生成的16进制数只有一位时，在末尾添0，丢弃生成的16进制数（因为8位应是两位的16进制数，除非前面全为0）
                    hexString.append("0");
                }
                //将每一个字节的转换结果连接
                hexString.append(hex);
            }
            return hexString.toString();
    }

    /**
     * 根据秘钥获得字符串
     * @param key
     * @return
     */
    public static String getStringFromKey(Key key) {
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }

    public static byte[] applyECDSASig(PrivateKey privateKey, String input) {
        Signature dsa;
        byte[] output = new byte[0];
        try{

        } catch (Exception e) {
            
        }
    }
}
