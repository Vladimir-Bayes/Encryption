import org.apache.commons.codec.digest.DigestUtils;

/** 
* @author ����Vladimir E-mail: gyang.shines@gmail.com
* @version ����ʱ�䣺2017��12��11�� ����8:20:26 
* ��˵��
* MD5������� 
*/
public class MD5Utils {
	/** 
     * MD5���� 
     *  
     * @param data ����������
     *             
     * @return byte[] ��ϢժҪ 
     *  
     * @throws Exception 
     */  
    public static byte[] encodeMD5(String data) throws Exception {  
  
        // ִ����ϢժҪ  
        return DigestUtils.md5(data);  
    }  
  
    /** 
     * MD5���� 
     *  
     * @param data 
     *            ���������� 
     * @return byte[] ��ϢժҪ 
     *  
     * @throws Exception 
     */  
    public static String encodeMD5Hex(String data) {  
        // ִ����ϢժҪ  
        return DigestUtils.md5Hex(data);  
    }  
}
