package com.example.baiduface.util;

import com.baidu.aip.face.AipFace;
import com.baidu.aip.face.FaceVerifyRequest;
import com.baidu.aip.face.MatchRequest;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 每个开发者账号可以创建100个appid
 * 不同appid之间，人脸库互不相通
 * 每个人脸库下，可以创建多个用户组，用户组（group）数量没有限制
 * 每个用户组（group）下，可添加最多无限张人脸，无限个uid
 * 每个用户（uid）所能注册的最大人脸数量没有限制
 */
@Component
public class BaiduFaceUtil {

    private static final String APP_ID = "你的 App ID";
    private static final String API_KEY = "你的 Api Key";
    private static final String SECRET_KEY = "你的 Secret Key";
    private AipFace client;

    public BaiduFaceUtil(){
        client = new AipFace(APP_ID, API_KEY, SECRET_KEY);
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
        //client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
        //client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理
    }


    /**
     *  人脸检测
     * @param image 取决于image_type参数，传入BASE64字符串或URL字符串或FACE_TOKEN字符串
     * @param imageType 图片类型 BASE64、URL、FACE_TOKEN
     */
    public void checkFace(String image, String imageType){
        // 可选参数
        HashMap<String, String> options = new HashMap<String, String>();
        // 包括age,beauty,expression,face_shape,gender,glasses,landmark,landmark72，landmark150，race,quality,eye_status,emotion,face_type信息逗号分隔. 默认只返回face_token、人脸框、概率和旋转角度
        options.put("face_field", "age");
        // 最多处理人脸的数目（一张图片里边的人脸数）默认1最大10
        options.put("max_face_num", "2");
        // 类型 LIVE生活照 IDCARD身份证芯片照 WATERMARK水印证件照公安网小图等 CERT证件照片如拍照身份证等 默认LIVE
        options.put("face_type", "LIVE");
        // 活体检测 NONE LOW较低 NORMAL一般 NORMAL较高  默认NONE
        options.put("liveness_control", "LOW");

        // 返回的数据参考https://ai.baidu.com/ai-doc/FACE/8k37c1rqz#%E4%BA%BA%E8%84%B8%E6%A3%80%E6%B5%8B
        JSONObject res = client.detect(image, imageType, options);
        System.out.println(res.toString(2));
    }

    /**
     * 1：N人脸搜索/认证 具体应用要根据业务判断
     * @param image
     * @param imageType
     * @param groupIdList 从指定的group中进行查找 用逗号分隔，上限20个 示例 3,2
     */
    public void oneToN(String image, String imageType, String groupIdList){
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("max_face_num", "3");
        // 匹配阈值（设置阈值后，score低于此阈值的用户信息将不会返回） 最大100 最小0 默认80 越高速度越快
        options.put("match_threshold", "70");
        // 图片质量控制 参数同活体检测
        options.put("quality_control", "NORMAL");
        options.put("liveness_control", "LOW");
        // 当需要对特定用户进行比对时，指定user_id进行比对。即人脸认证功能。
        options.put("user_id", "233451");
        // 查找后返回的用户数量。返回相似度最高的几个用户，默认为1，最多返回50个。
        options.put("max_user_num", "3");

        /*  返回值：
            face_token	是	string	人脸标志
            user_list	是	array	匹配的用户信息列表
            +group_id	是	string	用户所属的group_id
            +user_id	是	string	用户的user_id
            +user_info	是	string	注册用户时携带的user_info
            +score	是	float	用户的匹配得分
         */
        JSONObject res = client.search(image, imageType, groupIdList, options);
        System.out.println(res.toString(2));
    }

    /**
     * 待识别的图片中，存在多张人脸的情况下，支持在一个人脸库中，一次请求，同时返回图片中所有人脸的识别结果
     * @param image
     * @param imageType
     * @param groupIdList
     */
    public void MToN(String image, String imageType, String groupIdList){
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("max_face_num", "3");
        options.put("match_threshold", "70");
        options.put("quality_control", "NORMAL");
        options.put("liveness_control", "LOW");
        options.put("max_user_num", "3");

        /* 返回参数
         * face_num	是	int	图片中的人脸数量
         * face_list	是	array	人脸信息列表
         * +face_token	是	string	人脸标志
         * +location	是	array	人脸在图片中的位置
         * ++left	是	double	人脸区域离左边界的距离
         * ++top	是	double	人脸区域离上边界的距离
         * ++width	是	double	人脸区域的宽度
         * ++height	是	double	人脸区域的高度
         * ++rotation	是	int64	人脸框相对于竖直方向的顺时针旋转角，[-180,180]
         * +user_list	是	array	匹配的用户信息列表
         * ++group_id	是	string	用户所属的group_id
         * ++user_id	是	string	用户的user_id
         * ++user_info	是	string	注册用户时携带的user_info
         * ++score	是	float	用户的匹配得分
         * 80分以上可以判断为同一人，此分值对应万分之一误识率
         */
        JSONObject res = client.multiSearch(image, imageType, groupIdList, options);
        System.out.println(res.toString(2));
    }

    /**
     * 人脸注册入人脸库
     * @param image
     * @param imageType
     * @param groupId 组id
     * @param userId 用户id
     */
    public void faceRegistration(String image, String imageType, String groupId, String userId){
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("user_info", "user's info");
        options.put("quality_control", "NORMAL");
        options.put("liveness_control", "LOW");
        // 操作方式 APPEND: 当user_id在库中已经存在时，对此user_id重复注册时，新注册的图片默认会追加到该user_id下,REPLACE : 当对此user_id重复注册时,则会用新图替换库中该user_id下所有图片,默认使用APPEND
        options.put("action_type", "REPLACE");

        /*  返回值
            log_id	是	uint64	请求标识码，随机数，唯一
            face_token	是	string	人脸图片的唯一标识
            location	是	array	人脸在图片中的位置
            +left	是	double	人脸区域离左边界的距离
            +top	是	double	人脸区域离上边界的距离
            +width	是	double	人脸区域的宽度
            +height	是	double	人脸区域的高度
            +rotation	是	int64	人脸框相对于竖直方向的顺时针旋转角，[-180,180]
         */
        JSONObject res = client.addUser(image, imageType, groupId, userId, options);
        System.out.println(res.toString(2));
    }

    /**
     * 根据组和id更新人脸数据
     * @param image
     * @param imageType
     * @param groupId
     * @param userId
     */
    public void faceUpdate(String image, String imageType, String groupId, String userId){
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("user_info", "user's info");
        options.put("quality_control", "NORMAL");
        options.put("liveness_control", "LOW");
        options.put("action_type", "REPLACE");
        /*
            log_id	是	uint64	请求标识码，随机数，唯一
            face_token	是	string	人脸图片的唯一标识
            location	是	array	人脸在图片中的位置
            +left	是	double	人脸区域离左边界的距离
            +top	是	double	人脸区域离上边界的距离
            +width	是	double	人脸区域的宽度
            +height	是	double	人脸区域的高度
            +rotation	是	int64	人脸框相对于竖直方向的顺时针旋转角，[-180,180]
         */
        JSONObject res = client.updateUser(image, imageType, groupId, userId, options);
        System.out.println(res.toString(2));
    }

    /**
     * 人脸删除
     * @param userId
     * @param groupId
     * @param faceToken
     */
    public void faceDelete(String userId, String groupId, String faceToken){
        HashMap<String, String> options = new HashMap<String, String>();
        /*
            "error_code": 0 成功
         */
        JSONObject res = client.faceDelete(userId, groupId, faceToken, options);
        System.out.println(res.toString(2));
    }

    /**
     * 从组中删除用户
     * @param userId
     * @param groupId
     */
    public void faceDelete(String userId, String groupId){
        HashMap<String, String> options = new HashMap<String, String>();
        JSONObject res = client.deleteUser(groupId, userId, options);
        System.out.println(res.toString(2));
    }

    /**
     * 从组中获取用户信息
     * @param userId
     * @param groupId
     */
    public void getInfoFromFaceGroup(String userId, String groupId){
        HashMap<String, String> options = new HashMap<String, String>();
        /*
            log_id	是	uint64	请求标识码，随机数，唯一
            user_list	是	array	查询到的用户列表
            +user_info	是	string	用户资料，被查询用户的资料
            +group_id	是	string	用户组id，被查询用户的所在组
         */
        JSONObject res = client.getUser(userId, groupId, options);
        System.out.println(res.toString(2));
    }

    /**
     * 获取该用户在组中的人脸列表
     * @param userId
     * @param groupId
     */
    public void getFaceList(String userId, String groupId){
        HashMap<String, String> options = new HashMap<String, String>();
        /*
            log_id	是	uint64	请求标识码，随机数，唯一
            face_list	是	array	人脸列表
            +face_token	是	string	人脸图片的唯一标识
            +ctime	是	string	人脸创建时间
         */
        JSONObject res = client.faceGetlist(userId, groupId, options);
        System.out.println(res.toString(2));
    }

    /**
     * 获取组中的人脸列表
     * @param groupId
     */
    public void getFaceList(String groupId){
        HashMap<String, String> options = new HashMap<String, String>();
        // 可选参数 start 起始数据号 length长度默认100 最大1000
        // user_id_list	 是	 array	用户ID列表
        JSONObject res = client.getGroupUsers(groupId, options);
        System.out.println(res.toString(2));
    }

    /**
     * 将用户复制到其他组
     * @param userId
     * @param srcGroupId
     * @param dstGroupId
     */
    public void copyFaceToOtherGroup(String userId, String srcGroupId, String dstGroupId){
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("src_group_id", srcGroupId);
        options.put("dst_group_id", dstGroupId);

        // error_code 0 为成功
        JSONObject res = client.userCopy(userId, options);
        System.out.println(res.toString(2));
    }

    /**
     * 创建组
     * @param groupId
     */
    public void createGroup(String groupId){
        HashMap<String, String> options = new HashMap<String, String>();
        JSONObject res = client.groupAdd(groupId, options);
        System.out.println(res.toString(2));
    }

    /**
     * 删除组
     * @param groupId
     */
    public void deleteGroup(String groupId){
        HashMap<String, String> options = new HashMap<String, String>();
        JSONObject res = client.groupDelete(groupId, options);
        System.out.println(res.toString(2));
    }

    /**
     * 获取组列表
     * @param start
     * @param length
     */
    public void selectGroup(String start, String length){
        HashMap<String, String> options = new HashMap<String, String>();
        JSONObject res = client.getGroupList(options);
        System.out.println(res.toString(2));
    }

    /**
     * 身份验证
     */
    public void verification(String image, String imageType, String idCardNumber, String name){
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("quality_control", "NORMAL");
        options.put("liveness_control", "LOW");
        // score	是	float	与公安小图相似度可能性，用于验证生活照与公安小图是否为同一人，有正常分数时为[0~1]，推荐阈值0.8，超过即判断为同一人
        JSONObject res = client.personVerify(image, imageType, idCardNumber, name, options);
        System.out.println(res.toString(2));
    }

    /**
     * 语音校验码
     * @param appId
     */
    public void soundCheck(String appId){
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("appid", appId);

        // 语音校验码接口
        // session_id	是	string	语音校验码会话id
        // code	是	string	语音验证码，数字形式，3~6位数字
        JSONObject res = client.videoSessioncode(options);
        System.out.println(res.toString(2));
    }

    /**
     * 视频活体检测
     * @param sessionId
     * @param video 参数为本地路径
     */
    public void videoLiveDetection(String sessionId, String video){

        HashMap<String, String> options = new HashMap<String, String>();
        /*
            score	是	float	活体检测分数
            thresholds	是	array	阈值参考，实际业务应用中，请以score>阈值判定通过，可直接选择不同误识别率的阈值，无需对应具体的分值，选择阈值参数即可。
            code	是	array	语音校验码信息
            create	是	string	生成的校验码，通过create和identify两个字段的对比，可以判断上传的视频是否为目标视频。
            identify	是	string	语音识别出来的校验码
            pic_list	是	array	抽取图片信息列表
            pic_list[i].face_id	是	string	face唯一ID
            pic_list[i].pic	是	string/encryption	base64编码后的图片信息
         */
        JSONObject res = client.videoFaceliveness(sessionId, video, options);
        System.out.println(res.toString(2));

        /* 参数为二进制数组
        byte[] file = readFile("video.mp4");
        res = client.videoFaceliveness(file, sessionId, options);
        System.out.println(res.toString(2));
        */
    }

    /**
     *
     * @param image1 可以为url或facetoken,base64
     * @param image2
     */
    public void faceContrast(String image1, String imageType1, String image2, String imageType2){
        MatchRequest req1 = new MatchRequest(image1, imageType1);
        MatchRequest req2 = new MatchRequest(image2, imageType2);
        ArrayList<MatchRequest> requests = new ArrayList<>();
        requests.add(req1);
        requests.add(req2);

        /*
         * score	是	float	人脸相似度得分
         * face_list	是	array	人脸信息列表
         * +face_token	是	string	人脸的唯一标志
         */
        JSONObject res = client.match(requests);
        System.out.println(res.toString(2));
    }

    /**
     * 人脸基础信息，人脸质量检测，基于图片的活体检测
     * @param image
     * @param imageType
     */
    public void onlineLiveDetection(String image, String imageType){
        FaceVerifyRequest req = new FaceVerifyRequest(image, imageType);
        ArrayList<FaceVerifyRequest> list = new ArrayList<>();
        list.add(req);
        /*
         * log_id	是	uint64	请求唯一标识码，随机数
         * face_liveness	是	float	活体分数值
         * thresholds	是	array	由服务端返回最新的阈值数据（随着模型的优化，阈值可能会变化），可以作为活体判断的依据。 frr_1e-4：万分之一误识率的阈值；frr_1e-3：千分之一误识率的阈值；frr_1e-2：百分之一误识率的阈值。误识率越底，准确率越高。
         * face_list	是	array	每张图片的详细信息描述，如果只上传一张图片，则只返回一个结果。
         */
        JSONObject res = client.faceverify(list);
        System.out.println(res.toString(2));
    }
}
