package cn.taowd.oa.util;

import com.alibaba.druid.filter.config.ConfigTools;

/**
 * 将数据库密码转换成密文，存储在配置文件中
 * 
 * @author Taowd
 *
 */
public class GetDataBaseCiphertext {

	public static void main(String[] args) throws Exception {
		String password = "admin";
		String[] arr = ConfigTools.genKeyPair(512);
		System.out.println("privateKey:" + arr[0]);
		System.out.println("publicKey:" + arr[1]);
		System.out.println("password:" + ConfigTools.encrypt(arr[0], password));

		/**
		 * 
		 * privateKey:MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEAnzhHjp+sTJEuVACgmY9vtuQSPkkiQWda/bkheRlgJ3Bjaabsd1KsQO3cCbccLYY+N3LfvdT/bSB5tOYzoO7JlQIDAQABAkBqSqECprfYmacq7wxH14jFuLYxHJOgxAXrkfa1qGoXiRJ3BSXmosiPRYL51KO2Vp9Fjj9dQak8YT60UXNg+Jy1AiEA2sHl6+fwAnQtPLEhHUM42xP9C4MlIk+ZV3p2HieybGsCIQC6U45ZFeoUIpQssBsiCOuPzmcejKhZCKjKNDYNcXEh/wIhAMQQGPRQkqqAWbMVxmolv6WKyrcwuW2BDhx7Y7YvOhk1AiEAgoxUqjpefakUpJLnD9usIF//hDoNIgSdLr+SN60KFUUCIHVaIWajF9hyzUQz1Wb649FzIQOk0UA+bDzxyWdxrELl
		 * publicKey:MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJ84R46frEyRLlQAoJmPb7bkEj5JIkFnWv25IXkZYCdwY2mm7HdSrEDt3Am3HC2GPjdy373U/20gebTmM6DuyZUCAwEAAQ==
		 * password:EZvkuL4cLqtWWf3YxG2ICN+LZ2uY/AStIsKW4PJ3M90Vy+S8fbBbi/uxTbTmS1FkvWdXVDK1ZW3BUSOExUzz9g==
		 * 
		 */

	}

}
