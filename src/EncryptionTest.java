import java.io.IOException;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.util.encoders.Hex;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/** 
 * @author 作者Vladimir E-mail: gyang.shines@gmail.com
 * @version 创建时间：2017年12月11日 下午7:24:21 
 * 类说明 
 */
public class EncryptionTest {

	
	
	
	
	public static void main(String[] args){
		BASE64Encoder encoder=new BASE64Encoder(); //  BASE64Encoder sun（oracle）公司的加密
		String str="捂吾铠剐臂而已";
		String encodeSTR=encoder.encode(str.getBytes());
		System.out.println(">>jdk加密>>"+encodeSTR);
		// 解密  
		BASE64Decoder decoder=new BASE64Decoder();
		try {
			System.out.println(">jdk 解码>>>"+new String(decoder.decodeBuffer(encodeSTR))) ;
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Base64 base64=new Base64();
		byte[] byteEncode= base64.encode(str.getBytes());
		System.out.println("Comonc-c加密>>"+new String(base64.encode(str.getBytes())));

		byte[] byteDecode  = base64.decode(byteEncode);
		System.out.println("Comonc-c>解密>"+new String(byteDecode));

		try {
			//生成key
			KeyGenerator keyGenerator=KeyGenerator.getInstance("DES");
			keyGenerator.init(56);
			SecretKey secretKey=keyGenerator.generateKey();
			byte[] bytes=secretKey.getEncoded();

			//key转换
			DESKeySpec desKeySpec=new DESKeySpec(bytes);
			SecretKeyFactory factory=SecretKeyFactory.getInstance("DES");
			Key converSeKey=factory.generateSecret(desKeySpec);

			//加密
			Cipher cipher=Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE,converSeKey);
			byte[] result=cipher.doFinal(str.getBytes());
			System.out.println(">>对称加密>"+ Hex.toHexString(result));

			//解密
			cipher.init(Cipher.DECRYPT_MODE,converSeKey);
			result=cipher.doFinal(result);
			System.out.println(">>对称解密>>"+new String(result)); 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		
		
	}
}
