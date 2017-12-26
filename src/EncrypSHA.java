import java.security.MessageDigest;  
import java.security.NoSuchAlgorithmException; 


/** 
* @author 作者Vladimir E-mail: gyang.shines@gmail.com
* @version 创建时间：2017年12月11日 下午8:12:01 
* 类说明 
*/
public class EncrypSHA {
	public byte[] eccrypt(String info) throws NoSuchAlgorithmException{  
        MessageDigest md5 = MessageDigest.getInstance("SHA");  
        byte[] srcBytes = info.getBytes();  
        //使用srcBytes更新摘要  
        md5.update(srcBytes);  
        //完成哈希计算，得到result  
        byte[] resultBytes = md5.digest();  
        return resultBytes;  
    }  
  
    /** 
     * @param args 
     * @throws NoSuchAlgorithmException  
     */  
    public static void main(String[] args) throws NoSuchAlgorithmException {  
        String msg = "捂吾铠剐臂而已";  
        EncrypSHA sha = new EncrypSHA();  
        byte[] resultBytes = sha.eccrypt(msg);  
        System.out.println("明文是：" + msg);  
        System.out.println("密文是：" + new String(resultBytes));  
          
    }  
}
