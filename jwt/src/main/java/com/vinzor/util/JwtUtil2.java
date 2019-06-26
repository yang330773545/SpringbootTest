package com.vinzor.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

//java-jwt
public class JwtUtil2 {

	// 设置一个私钥，也可以使用KeyProvider产生，参见：
    // @link https://github.com/auth0/java-jwt#using-a-keyprovider
    String key = "Suibianlaiyige";
    // 给定一个算法，如HmacSHA-256
    Algorithm alg = Algorithm.HMAC256(key);

    // 1 签发Token
    public void getJWT(String userName) {
	    Date currentTime = new Date();

	    String token = JWT.create()
	            .withIssuer("yang") // 发行者
	            .withSubject("userid") // 用户身份标识
	            .withAudience("not found") // 用户单位
	            .withIssuedAt(currentTime) // 签发时间
	            .withExpiresAt(new Date(currentTime.getTime() + 24*3600*1000L)) // 一天有效期
	            .withJWTId("001") // 分配JWT的ID
	            .withClaim("data", userName) // 定义公共域信息
	            .sign(alg);
	
	    System.out.println("生成的Token是:"+token);
    }
    // 2 验证Token
    public void verificationJWT(String token) {
	    JWTVerifier verifier = JWT.require(alg)
	            .withIssuer("yang")
	            .withAudience("not found")
	            .build();
	    try{
	        verifier.verify(token);
	        System.out.println("验证通过!");
	    } catch (JWTVerificationException e) {
	        e.printStackTrace();
	        System.out.println("验证失败!");
	    }
    }
    
    // 3 尝试解码
    public void getinformation(String token) {
	    try{
	        DecodedJWT originToken = JWT.decode(token);
	        System.out.println("解码得到发行者是:"+originToken.getIssuer());
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        System.out.println("解码得到签发时间是:"+sdf.format(originToken.getIssuedAt()));
	        System.out.println("解码得到公共域信息是:"+originToken.getClaim("PublicClaimExample").asString());
	    } catch (JWTDecodeException e){
	        e.printStackTrace();
	    }
    }
}
