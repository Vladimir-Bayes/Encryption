import java.security.InvalidKeyException;  
import java.security.KeyPair;  
import java.security.KeyPairGenerator;  
import java.security.NoSuchAlgorithmException;  
import java.security.interfaces.RSAPrivateKey;  
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;

import javax.crypto.BadPaddingException;  
import javax.crypto.Cipher;  
import javax.crypto.IllegalBlockSizeException;  
import javax.crypto.NoSuchPaddingException;  

/** 
* @author 作者Vladimir E-mail: gyang.shines@gmail.com
* @version 创建时间：2017年12月11日 下午8:09:19 
* 类说明 
*/
public class EncrypRSA {
	/** 
     * 加密 
     * @param publicKey 
     * @param srcBytes 
     * @return 
     * @throws NoSuchAlgorithmException 
     * @throws NoSuchPaddingException 
     * @throws InvalidKeyException 
     * @throws IllegalBlockSizeException 
     * @throws BadPaddingException 
     */  
    protected byte[] encrypt(RSAPublicKey publicKey,byte[] srcBytes) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{  
        if(publicKey!=null){  
            //Cipher负责完成加密或解密工作，基于RSA  
            Cipher cipher = Cipher.getInstance("RSA");  
            //根据公钥，对Cipher对象进行初始化  
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);  
            byte[] resultBytes = cipher.doFinal(srcBytes);  
            return resultBytes;  
        }  
        return null;  
    }  
      
    /** 
     * 解密  
     * @param privateKey 
     * @param srcBytes 
     * @return 
     * @throws NoSuchAlgorithmException 
     * @throws NoSuchPaddingException 
     * @throws InvalidKeyException 
     * @throws IllegalBlockSizeException 
     * @throws BadPaddingException 
     */  
    protected byte[] decrypt(RSAPrivateKey privateKey,byte[] srcBytes) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{  
        if(privateKey!=null){  
            //Cipher负责完成加密或解密工作，基于RSA  
            Cipher cipher = Cipher.getInstance("RSA");  
            //根据公钥，对Cipher对象进行初始化  
            cipher.init(Cipher.DECRYPT_MODE, privateKey);  
            byte[] resultBytes = cipher.doFinal(srcBytes);  
            return resultBytes;  
        }  
        return null;  
    }  
  
    /** 
     * @param args 
     * @throws NoSuchAlgorithmException  
     * @throws BadPaddingException  
     * @throws IllegalBlockSizeException  
     * @throws NoSuchPaddingException  
     * @throws InvalidKeyException  
     */  
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {  
        EncrypRSA rsa = new EncrypRSA();  
        String msg = "捂吾铠剐臂而已";
//        String msg = "{\"sites\":{\"site\":[{\"id\":\"1\",\"name\":\"菜鸟教程\",\"url\":\"www.runoob.com\"},{\"id\":\"2\",\"name\":\"菜鸟工具\",\"url\":\"c.runoob.com\"},{\"id\":\"3\",\"name\":\"Google\",\"url\":\"www.google.com\"}]}}";
        //KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象  
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");  
        //初始化密钥对生成器，密钥大小为1024位  
        keyPairGen.initialize(1024);  
        //生成一个密钥对，保存在keyPair中  
        KeyPair keyPair = keyPairGen.generateKeyPair();  
        //得到私钥  
        RSAPrivateKey privateKey = (RSAPrivateKey)keyPair.getPrivate();               
        //得到公钥  
        RSAPublicKey publicKey = (RSAPublicKey)keyPair.getPublic();  
        
        
        System.out.println(publicKey);
        System.out.println(privateKey);
        
        //用公钥加密  
        byte[] srcBytes = msg.getBytes();  
        byte[] resultBytes = rsa.encrypt(publicKey, srcBytes);  
          
        //用私钥解密  
        byte[] decBytes = rsa.decrypt(privateKey, resultBytes);  
          
        System.out.println("明文是:" + msg);  
        System.out.println("加密后的原始结果是:" + new String(resultBytes));
        System.out.println("加密后以Base64编码的结果是："+Base64.getEncoder().encodeToString(resultBytes));
        System.out.println("解密后是:" + new String(decBytes));  
    }  
}
