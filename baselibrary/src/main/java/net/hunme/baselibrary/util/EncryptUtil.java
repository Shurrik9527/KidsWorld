package net.hunme.baselibrary.util;

import com.google.gson.Gson;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;



public class EncryptUtil {
	/**
	 * 用MD5算法进行加密
	 * 
	 * @param str
	 *            需要加密的字符串
	 * @return MD5加密后的结果
	 */
	public static String encodeMD5String(String str) {
		return encode(str, "MD5");
	}

	/**
	 * 用SHA算法进行加密
	 * 
	 * @param str
	 *            需要加密的字符串
	 * @return SHA加密后的结果
	 */
	public static String encodeSHAString(String str) {
		return encode(str, "SHA");
	}



	private static String encode(String str, String method) {
		MessageDigest md = null;
		String dstr = null;
		try {
			md = MessageDigest.getInstance(method);
			md.update(str.getBytes());
			dstr = new BigInteger(1, md.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return dstr;
	}


	/**
	 * 加签
	 * 
	 * @param map Map参数（向服务端接口传递参数通过map传进来）
	 * @param msec 当前时间毫秒数
	 * @return 加密后的密钥
	 */
	public static String getSign(Map<String, Object> map, String msec) {
		String jsonStr = new Gson().toJson(map);
		String dataSign = EncryptUtil.encodeSHAString(jsonStr + msec);

		return dataSign;
	}

	/**
	 * 验签
	 *
	 * @param map 服务端返回date 字段用MAP包起来
	 * @param msec 服务端返回当前毫秒数
	 * @param sign 服务端返回签名
     * @return true通过 false 非法访问
     */
	public static boolean verify(Map<String, Object> map, String msec, String sign) {
		String dataSign = getSign(map, msec);
		if (dataSign.equals(sign)) {
			return true;
		} else {
			return false;
		}
	}
}