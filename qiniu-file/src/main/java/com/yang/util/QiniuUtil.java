package com.yang.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.storage.persistent.FileRecorder;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.qiniu.util.StringUtils;
import com.qiniu.util.UrlSafeBase64;

public class QiniuUtil {

	@Value("${qiniu.accessKey}")
	private String accessKey;
	@Value("${qiniu.secretKey}")
	private static String secretKey;
	@Value("${qiniu.bucket}")
	private String bucket;
	/*构造一个带指定Zone对象的配置类
		机房	Zone对象
		华东	Zone.zone0()
		华北	Zone.zone1()
		华南	Zone.zone2()
		北美	Zone.zoneNa0()
		东南亚 Zone.zoneAs0()
	*/
	private Configuration cfg = new Configuration(Zone.zone0());
	private UploadManager uploadManager = new UploadManager(cfg);
	//默认不指定key的情况下，以文件内容的hash值作为文件名
	private String key = null;
	/**
	 * 没有设置returnBody或者回调相关参数时，返回给上传端的格式为hash和key
	 * {"hash":"Ftgm-CkWePC9fzMBTRNmPMhGBcSV","key":"qiniu.jpg"}
	 */
	//简单上传凭证
	public String getSimpleUptoken() {
		Auth auth = Auth.create(accessKey, secretKey);
		String upToken = auth.uploadToken(bucket);
		return upToken;
	}
	//覆盖上传凭证 参数为 想进行覆盖的文件名称，这个文件名称同时可是客户端上传代码中指定的文件名，两者必须一致
	public String getCoverUptoken(String key) {
		Auth auth = Auth.create(accessKey, secretKey);
		String upToken = auth.uploadToken(bucket, key);
		return upToken;
	}
	//自定义上传回复的凭证 {"key":"qiniu.jpg","hash":"Ftgm-CkWePC9fzMBTRNmPMhGBcSV","bucket":"if-bc","fsize":39335}
	public String getReplyUptoken() {
		Auth auth = Auth.create(accessKey, secretKey);
		StringMap putPolicy = new StringMap();
		putPolicy.put("returnBody", "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"fsize\":$(fsize)}");
		long expireSeconds = 3600;
		String upToken = auth.uploadToken(bucket, null, expireSeconds, putPolicy);
		return upToken;
	}
	//带回调业务服务器的凭证 回调参数为json
	public String getJsonCallbackUptoken() {
		Auth auth = Auth.create(accessKey, secretKey);
		StringMap putPolicy = new StringMap();
		putPolicy.put("callbackUrl", "http://api.example.com/qiniu/upload/callback");
		putPolicy.put("callbackBody", "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"fsize\":$(fsize)}");
		putPolicy.put("callbackBodyType", "application/json");
		long expireSeconds = 3600;
		String upToken = auth.uploadToken(bucket, null, expireSeconds, putPolicy);
		return upToken;
	}
	//application/x-www-form-urlencoded来返回参数
	public String getParaCallbackUptoken() {
		Auth auth = Auth.create(accessKey, secretKey);
		StringMap putPolicy = new StringMap();
		putPolicy.put("callbackUrl", "http://api.example.com/qiniu/upload/callback");
		putPolicy.put("callbackBody", "key=$(key)&hash=$(etag)&bucket=$(bucket)&fsize=$(fsize)");
		long expireSeconds = 3600;
		String upToken = auth.uploadToken(bucket, null, expireSeconds, putPolicy);
		return upToken;
	}
	//带数据处理的凭证
	public String getProcessUptoken() {
		Auth auth = Auth.create(accessKey, secretKey);
		StringMap putPolicy = new StringMap();
		//数据处理指令，支持多个指令
		String saveMp4Entry = String.format("%s:avthumb_test_target.mp4", bucket);
		String saveJpgEntry = String.format("%s:vframe_test_target.jpg", bucket);
		String avthumbMp4Fop = String.format("avthumb/mp4|saveas/%s", UrlSafeBase64.encodeToString(saveMp4Entry));
		String vframeJpgFop = String.format("vframe/jpg/offset/1|saveas/%s", UrlSafeBase64.encodeToString(saveJpgEntry));
		//将多个数据处理指令拼接起来
		String persistentOpfs = StringUtils.join(new String[]{
		        avthumbMp4Fop, vframeJpgFop
		}, ";");
		putPolicy.put("persistentOps", persistentOpfs);
		//数据处理队列名称，必填
		putPolicy.put("persistentPipeline", "mps-pipe1");
		//数据处理完成结果通知地址
		putPolicy.put("persistentNotifyUrl", "http://api.example.com/qiniu/pfop/notify");
		long expireSeconds = 3600;
		String upToken = auth.uploadToken(bucket, null, expireSeconds, putPolicy);
		return upToken;
	}
	/**
	 * 
	 * 下边的文件上传 使用的uptoken为简单上传凭证
	 */
	//上传本地文件
	public void uploadLocalFile(String localFilePath) {
		String upToken=this.getSimpleUptoken();	
		try {
		    Response response = uploadManager.put(localFilePath, key, upToken);
		    //解析上传成功的结果
		    DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
		    System.out.println(putRet.key);
		    System.out.println(putRet.hash);
		} catch (QiniuException ex) {
		    Response r = ex.response;
		    System.err.println(r.toString());
		    try {
		        System.err.println(r.bodyString());
		    } catch (QiniuException ex2) {
		        //ignore
		    }
		}
		
	}
	//字节数组上传 使用utf-8编码
	public void uploadLocalFile(byte[] fileBytes) {
		byte[] uploadBytes = fileBytes;
		String upToken=this.getSimpleUptoken();	
		try {
		    Response response = uploadManager.put(uploadBytes, key, upToken);
		    //解析上传成功的结果
		    DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
		    System.out.println(putRet.key);
		    System.out.println(putRet.hash);
		} catch (QiniuException ex) {
		    Response r = ex.response;
		    System.err.println(r.toString());
		    try {
		        System.err.println(r.bodyString());
		    } catch (QiniuException ex2) {
		        //ignore
		    }
		}
	}
	//数据流上传
	public void uploadLocalFile(ByteArrayInputStream byteInputStream) {
		String upToken=this.getSimpleUptoken();	
		try {
		    Response response = uploadManager.put(byteInputStream,key,upToken,null, null);
		    //解析上传成功的结果
		    DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
		    System.out.println(putRet.key);
		    System.out.println(putRet.hash);
		} catch (QiniuException ex) {
		    Response r = ex.response;
		    System.err.println(r.toString());
		    try {
		        System.err.println(r.bodyString());
		    } catch (QiniuException ex2) {
		        //ignore
		    }
		}
		
	}
	//断点续上传
	public void breakpointUpload(String localFilePath) {
		String upToken=this.getSimpleUptoken();	
		String localTempDir = Paths.get(System.getenv("java.io.tmpdir"), bucket).toString();
		try {
		    //设置断点续传文件进度保存目录
		    FileRecorder fileRecorder = new FileRecorder(localTempDir);
		    UploadManager uploadManager = new UploadManager(cfg, fileRecorder);
		    try {
		        Response response = uploadManager.put(localFilePath, key, upToken);
		        //解析上传成功的结果
		        DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
		        System.out.println(putRet.key);
		        System.out.println(putRet.hash);
		    } catch (QiniuException ex) {
		        Response r = ex.response;
		        System.err.println(r.toString());
		        try {
		            System.err.println(r.bodyString());
		        } catch (QiniuException ex2) {
		            //ignore
		        }
		    }
		} catch (IOException ex) {
		    ex.printStackTrace();
		}
	}
	/**
	 * 回调鉴权 未实现
	 */
	public void callbackAuth() {
		//回调地址
		String callbackUrl = "http://api.example.com/qiniu/callback";
		//定义回调内容的组织格式，与上传策略中的callbackBodyType要保持一致
		//String callbackBodyType = "application/x-www-form-urlencoded"; //回调鉴权的签名包括请求内容callbackBody
		String callbackBodyType = "application/json";//回调鉴权的签名不包括请求内容
		/**
		 * 这两个参数根据实际所使用的HTTP框架进行获取
		 */
		//通过获取请求的HTTP头部Authorization字段获得
		String callbackAuthHeader = "xxx";
		//通过读取回调POST请求体获得，不要设置为null
		byte[] callbackBody = null;
		Auth auth = Auth.create(accessKey, secretKey);
		//检查是否为七牛合法的回调请求
		boolean validCallback = auth.isValidCallback(callbackAuthHeader, callbackUrl, callbackBody, callbackBodyType);
		if (validCallback) {
		    //继续处理其他业务逻辑
		} else {
		    //这是哪里的请求，被劫持，篡改了吧？
		}
	}
	/**
	 * 公开文件下载 空间绑定的域名拼接空间中的文件
	 * @throws UnsupportedEncodingException 
	 */
	public String getPublicDownloadUrl(String fileName) throws UnsupportedEncodingException {
		String domainOfBucket = "http://devtools.qiniu.com";
		String encodedFileName = URLEncoder.encode(fileName, "utf-8").replace("+", "%20");
		String publicUrl = String.format("%s/%s", domainOfBucket, encodedFileName);
		Auth auth = Auth.create(accessKey, secretKey);
		long expireInSeconds = 3600;//1小时，可以自定义链接过期时间
		String finalUrl = auth.privateDownloadUrl(publicUrl, expireInSeconds);
		return finalUrl;
	}
	/**
	 * 私有文件下载
	 * @throws UnsupportedEncodingException 
	 */
	public String getPrivateDownloadUrl(String fileName) throws UnsupportedEncodingException {
		String domainOfBucket = "http://devtools.qiniu.com";
		String encodedFileName = URLEncoder.encode(fileName, "utf-8").replace("+", "%20");
		String publicUrl = String.format("%s/%s", domainOfBucket, encodedFileName);
		String accessKey = "your access key";
		String secretKey = "your secret key";
		Auth auth = Auth.create(accessKey, secretKey);
		long expireInSeconds = 3600;//1小时，可以自定义链接过期时间
		String finalUrl = auth.privateDownloadUrl(publicUrl, expireInSeconds);
		return finalUrl;
	}
}
