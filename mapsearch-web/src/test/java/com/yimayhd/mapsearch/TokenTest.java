package com.yimayhd.mapsearch;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import com.alibaba.fastjson.JSON;

import net.pocrd.entity.CallerInfo;
import net.pocrd.util.AESTokenHelper;
import net.pocrd.util.AesHelper;
import net.pocrd.util.Base64Util;

/**  
 * @author: jiangzhihu936@jk.cn
 * @date: 2015年4月14日 上午11:03:16
 * 
 */
public class TokenTest {
	public static void main(String[] args) throws UnsupportedEncodingException {
		AesHelper  aesHelper = new AesHelper(Base64Util.decode("eqHSs48SCL2VoGsW1lWvDWKQ8Vu71UZJyS7Dbf/e4zo="), null);
		AESTokenHelper aesTokenHelper = new AESTokenHelper(aesHelper);
		String tk = "pK4%2B%2BoXaWz88WqfTO78L033ZMPiH%2FODx5zwVM%2FgHMEBSA8rCpXwbhgAXnVUUdDgwss3RyEptYK1hMIt9vKQkHwbwWs%2B2EyfRPHgEhGvYLACqKwSn7P%2BNBZweMPmRBqLUGwanXxMZQscQU%2FISkjesWzdvJc2BoXGFkbLiQ6xOS0UqcQLyeASSTCIPKtcreT%2Bo3nF5a1BGf7adowDMFug8FkMMaG22mB7JoChJ52eWokIVZOsyyDKRsmZsqSC1xRKf82evkQviFxhWqozOun4CFcVJnKzSU3Zh2bKmis1TcqBNqio%2FVmvO6yzJXwV3ertiFyKerWJZPr8k2GGNnbsrusRx9O0GVede9Xl1UyDQ%2FIKzFFOACnaJ9PQtR%2BZxPhP8";
		tk = URLDecoder.decode(tk, "UTF-8");
		CallerInfo callerInfo = aesTokenHelper.parseUserToken(tk);
		System.out.println(JSON.toJSON(callerInfo));
	}
}
