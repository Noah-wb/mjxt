package cn.wids.ai;
import java.util.*;

import cn.wids.utils.Base64Util;
import cn.wids.utils.FileUtil;
import cn.wids.utils.GsonUtils;
import cn.wids.utils.HttpUtil;

/**
* ����ע��
*/
public class FaceAdd {

    /**
    * ��Ҫ��ʾ���������蹤����
    * FileUtil,Base64Util,HttpUtil,GsonUtils���
    * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
    * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
    * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
    * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
    * ����
    */
    public static String add(String path,String dept,String userId) {
        // ����url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/add";
        try {
        	byte[] bytes1 = FileUtil.readFileByBytes(path);
            String image1 = Base64Util.encode(bytes1);
        	
            Map<String, Object> map = new HashMap<>();
            map.put("image", image1);
            map.put("group_id", dept);
            map.put("user_id", userId);
            map.put("user_info", "�����Ȱ�");
            map.put("liveness_control", "NORMAL");
            map.put("image_type", "BASE64");
            map.put("quality_control", "LOW");

            String param = GsonUtils.toJson(map);

            // ע�������Ϊ�˼򻯱���ÿһ������ȥ��ȡaccess_token�����ϻ���access_token�й���ʱ�䣬 �ͻ��˿����л��棬���ں����»�ȡ��
            String accessToken = AuthService.getAuth();

            String result = HttpUtil.post(url, accessToken, "application/json", param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
//        FaceAdd.add();
    }
}