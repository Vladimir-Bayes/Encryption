import org.apache.commons.codec.digest.DigestUtils;

/** 
* @author 作者Vladimir E-mail: gyang.shines@gmail.com
* @version 创建时间：2017年12月11日 下午8:20:26 
* 类说明
* MD5加密组件 
*/
public class MD5Utils {
	/** 
     * MD5加密 
     *  
     * @param data 待加密数据
     *             
     * @return byte[] 消息摘要 
     *  
     * @throws Exception 
     */  
    public static byte[] encodeMD5(String data) throws Exception {  
  
        // 执行消息摘要  
        return DigestUtils.md5(data);  
    }  
  
    /** 
     * MD5加密 
     *  
     * @param data 
     *            待加密数据 
     * @return byte[] 消息摘要 
     *  
     * @throws Exception 
     */  
    public static String encodeMD5Hex(String data) {  
        // 执行消息摘要  
        return DigestUtils.md5Hex(data);  
    }  
}
