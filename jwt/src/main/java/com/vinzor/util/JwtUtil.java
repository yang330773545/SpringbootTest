package com.vinzor.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {

	//Jwts.builder()返回一个JwtBuilder	
	/**	
	 *  claim(String name, Object value)
	 *	设置自定义JWT声明参数值。
	 *	String	compact()
	 *	实际构建JWT并根据JWT Compact Serialization规则将其序列化为紧凑的URL安全字符串。
	 *	setAudience(String aud)
	 *	设置JWT声明（受众）值。 aud
	 *	setClaims(Claims claims)
	 *	将JWT有效内容设置为JSON Claims实例。
	 *	setClaims(Map<String,Object> claims)
	 *	将JWT有效内容设置为由指定的名称/值对填充的JSON Claims实例。
	 *	setExpiration(Date exp)
	 *	设置JWT声明（到期）值。 exp
	 *	setHeader(Header header)
	 *	使用指定的标头设置（并替换）任何现有标头。
	 *	setHeader(Map<String,Object> header)
	 *	使用指定的标头设置（并替换）任何现有标头。
	 *	setHeaderParam(String name, Object value)
	 *	将指定的名称/值对应用于标头。
	 *	setHeaderParams(Map<String,Object> params)
	 *	将指定的名称/值对应用于标头。
	 *	setId(String jti)
	 *	设置JWT声明（JWT ID）值。 jti
	 *	setIssuedAt(Date iat)
	 *	设置JWT声明（发布时间）值。 iat
	 *	setIssuer(String iss)
	 *	设置JWT Claims （issuer）值。 iss
	 *	setNotBefore(Date nbf)
	 *	设置JWT声明（不是之前）值。 nbf
	 *	setPayload(String payload)
	 *	将JWT的有效负载设置为纯文本（非JSON）字符串。
	 *	setSubject(String sub)
	 *	设置JWT声明（主题）值。 sub
	 *	signWith(SignatureAlgorithm alg, byte[] secretKey)
	 *	使用指定的算法使用指定的密钥对构造的JWT进行签名，从而生成JWS。
	 *	signWith(SignatureAlgorithm alg, Key key)
	 *	使用指定的算法使用指定的密钥对构造的JWT进行签名，从而生成JWS。
	 *	signWith(SignatureAlgorithm alg, String base64EncodedSecretKey)
	 *	使用指定的算法使用指定的密钥对构造的JWT进行签名，从而生成JWS。
	 */
	/**
	 *  HS256：使用SHA-256的HMAC
	 *	HS384：使用SHA-384的HMAC
	 *	HS512：使用SHA-512的HMAC
	 *	RS256：使用SHA-256的RSASSA-PKCS-v1_5
	 *	RS384：使用SHA-384的RSASSA-PKCS-v1_5
	 *	RS512：使用SHA-512的RSASSA-PKCS-v1_5
	 *	PS256：使用SHA-256的RSASSA-PSS和使用SHA-256的MGF1
	 *	PS384：使用SHA-384的RSASSA-PSS和使用SHA-384的MGF1
	 *	PS512：使用SHA-512的RSASSA-PSS和使用SHA-512的MGF1
	 *	ES256：使用P-256和SHA-256的ECDSA
	 *	ES384：使用P-384和SHA-384的ECDSA
	 *	ES512：使用P-521和SHA-512的ECDSA
	 */
	 
	
    private static long EXPIRATION_TIME=3600000L; // 1 hour
 
    private static String SECRET="MDk4ZjZiY2Q0NjIxZDM3M2NhZGU0ZTgzMjY34DFDSSSd";// 秘钥
     
    @Value("${jwt.expire_time}")
    public void setEXPIRATION_TIME(long eXPIRATION_TIME) {
        EXPIRATION_TIME = eXPIRATION_TIME;
    }
 
    @Value("${jwt.secret}")
    public void setSECRET(String sECRET) {
        SECRET = sECRET;
    }
    /**
                *  生成jwtToken
     *
     * @param username
     * @return
     */
    public static String generateToken(String username) {
        HashMap<String, Object> map = new HashMap<>();
        // you can put any data in the map
        map.put("username", username);
        String jwt = Jwts.builder().setClaims(map).setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET).compact();
        return jwt;
    }
 
    /**
     * 校验jwtToken
     *
     * @param token
     * @return
     */
    public static String validateToken(String token) {
        if (token != null) {
            Map<String, Object> body = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
            String username = (String) (body.get("username"));
            if (username == null || username.isEmpty()) {
                throw new TokenValidationException("Wrong token without username");
            } else {
                return username;
            }
        } else {
            throw new TokenValidationException("Missing token");
        }
    }
     
    public static long getEXPIRATION_TIME(){
        return JwtUtil.EXPIRATION_TIME;
    }
 
 
    static class TokenValidationException extends RuntimeException {
        /**
         *
         */
        private static final long serialVersionUID = -7946690694369283250L;
 
        public TokenValidationException(String msg) {
            super(msg);
        }
    }
}
