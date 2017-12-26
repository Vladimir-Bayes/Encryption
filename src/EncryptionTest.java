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
 * @author ����Vladimir E-mail: gyang.shines@gmail.com
 * @version ����ʱ�䣺2017��12��11�� ����7:24:21 
 * ��˵�� 
 */
public class EncryptionTest {

	
	
	
	
	public static void main(String[] args){
		BASE64Encoder encoder=new BASE64Encoder(); //  BASE64Encoder sun��oracle����˾�ļ���
		String str="�������б۶���";
		String encodeSTR=encoder.encode(str.getBytes());
		System.out.println(">>jdk����>>"+encodeSTR);
		// ����  
		BASE64Decoder decoder=new BASE64Decoder();
		try {
			System.out.println(">jdk ����>>>"+new String(decoder.decodeBuffer(encodeSTR))) ;
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Base64 base64=new Base64();
		byte[] byteEncode= base64.encode(str.getBytes());
		System.out.println("Comonc-c����>>"+new String(base64.encode(str.getBytes())));

		byte[] byteDecode  = base64.decode(byteEncode);
		System.out.println("Comonc-c>����>"+new String(byteDecode));

		try {
			//����key
			KeyGenerator keyGenerator=KeyGenerator.getInstance("DES");
			keyGenerator.init(56);
			SecretKey secretKey=keyGenerator.generateKey();
			byte[] bytes=secretKey.getEncoded();

			//keyת��
			DESKeySpec desKeySpec=new DESKeySpec(bytes);
			SecretKeyFactory factory=SecretKeyFactory.getInstance("DES");
			Key converSeKey=factory.generateSecret(desKeySpec);

			//����
			Cipher cipher=Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE,converSeKey);
			byte[] result=cipher.doFinal(str.getBytes());
			System.out.println(">>�ԳƼ���>"+ Hex.toHexString(result));

			//����
			cipher.init(Cipher.DECRYPT_MODE,converSeKey);
			result=cipher.doFinal(result);
			System.out.println(">>�Գƽ���>>"+new String(result)); 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		
		
	}
}
