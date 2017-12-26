import org.apache.commons.codec.binary.Base64; 


/** 
* @author 作者Vladimir E-mail: gyang.shines@gmail.com
* @version 创建时间：2017年12月11日 下午8:18:49 
* 类说明
* Base64组件  
*/
public abstract class Base64Utils {
	/** 
     * 字符编码 
     */  
    public final static String ENCODING = "UTF-8";  
  
    /** 
     * Base64编码 
     *  
     * @param data 待编码数据 
     * @return String 编码数据 
     * @throws Exception 
     */  
    public static String encode(String data) throws Exception {  
  
        // 执行编码  
        byte[] b = Base64.encodeBase64(data.getBytes(ENCODING));  
  
        return new String(b, ENCODING);  
    }  
  
    /** 
     * Base64安全编码<br> 
     * 遵循RFC 2045实现 
     *  
     * @param data 
     *            待编码数据 
     * @return String 编码数据 
     *  
     * @throws Exception 
     */  
    public static String encodeSafe(String data) throws Exception {  
  
        // 执行编码  
        byte[] b = Base64.encodeBase64(data.getBytes(ENCODING), true);  
  
        return new String(b, ENCODING);  
    }  
  
    /** 
     * Base64解码 
     *  
     * @param data 待解码数据 
     * @return String 解码数据 
     * @throws Exception 
     */  
    public static String decode(String data) throws Exception {  
  
        // 执行解码  
        byte[] b = Base64.decodeBase64(data.getBytes(ENCODING));  
  
        return new String(b, ENCODING);  
    }  
}
