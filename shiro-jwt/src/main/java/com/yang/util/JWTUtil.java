package com.yang.util;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JWTUtil {

	// 设置一个私钥，也可以使用KeyProvider产生，参见：
    // @link https://github.com/auth0/java-jwt#using-a-keyprovider
   // private static String key = "jiadefangfaqianming";
    // 给定一个算法，如HmacSHA-256
   // private static Algorithm alg = Algorithm.HMAC256(key);
    
	//这里使用密码作为私钥
	
	public static String generateJWT(String userName,String passWord) {
		Algorithm alg = Algorithm.HMAC256(passWord);
		Date currentTime = new Date();
	    String token = JWT.create()
	            .withIssuer("yangyang") // 发行者
	            .withIssuedAt(currentTime) // 签发时间
	            .withExpiresAt(new Date(currentTime.getTime() + 24*3600*1000L)) // 一天有效期
	            .withClaim("data", userName) // 定义公共域信息
	            .sign(alg);	
	    log.info("生成jwt");
	    return token;
	}

    public static String getUsername(String token) {
        log.info("获取jwt中数据");
    	try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("data").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }
    
    public static boolean verificationJWT(String token,String userName,String passWord) {
    	Algorithm alg = Algorithm.HMAC256(passWord);
	    JWTVerifier verifier = JWT.require(alg)
	            .withIssuer("yangyang")
	            .withClaim("data", userName)
	            .build();
	    try{
	        verifier.verify(token);
	        log.info("jwt验证通过");
	        return true;
	    } catch (JWTVerificationException e) {
	        log.info("jwt验证失败");
	        return false;
	    }
    }



}
