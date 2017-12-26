import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;  
import java.security.NoSuchAlgorithmException;
import java.util.Base64; 

public class EncrypMD5 {
	public byte[] eccrypt(String info) throws NoSuchAlgorithmException, UnsupportedEncodingException{  

        MessageDigest md5 = MessageDigest.getInstance("MD5");  
        byte[] srcBytes = info.getBytes("UTF-8");  

        md5.update(srcBytes);  

        byte[] resultBytes = md5.digest();  
        return resultBytes;  
    }  
      
      
    public static void main(String args[]) throws NoSuchAlgorithmException, UnsupportedEncodingException{  

        String msg = "545253234";
        EncrypMD5 md5 = new EncrypMD5();  
        byte[] resultBytes = md5.eccrypt(msg);  
          
        System.out.println("����: " + new String(resultBytes));  
        System.out.println("����: " + Base64.getEncoder().encodeToString(resultBytes)); 
        System.out.println("����: " + msg);  
        
        
        final Base64.Decoder decoder = Base64.getDecoder();
        final Base64.Encoder encoder = Base64.getEncoder();
        final String text = "�ִ�����";
        final byte[] textByte = text.getBytes("UTF-8");
        //����
        final String encodedText = encoder.encodeToString(textByte);
        System.out.println(encodedText);
        //����
        System.out.println(new String(decoder.decode(encodedText), "UTF-8"));

  
        
        
    }  
}
