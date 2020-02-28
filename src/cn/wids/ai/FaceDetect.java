package cn.wids.ai;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.wids.utils.Base64Util;
import cn.wids.utils.GsonUtils;
import cn.wids.utils.HttpUtil;

/**
* ������������Է���
*/
public class FaceDetect {

    /**
    * ��Ҫ��ʾ���������蹤����
    * FileUtil,Base64Util,HttpUtil,GsonUtils���
    * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
    * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
    * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
    * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
    * ����
    */
    public static String detect() {
        // ����url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/detect";
        try {
        	//׼��ͼƬ
        	File file = new File("d:/Test/1001.jpeg");
        	int length = (int) file.length();
        	byte[] b = new byte[length];
        	InputStream is = new FileInputStream(file);
        	is.read(b);
        	//��ȡͼƬ��Base64�����ַ���
        	String img = Base64Util.encode(b);
//        	System.out.println("=================img:" + img);
        	is.close();
            Map<String, Object> map = new HashMap<>();
            map.put("image", img);
            map.put("face_field", "faceshape,facetype,age,beauty");//�趨����ֵ�з�����Щ������Ϣ
            map.put("image_type", "BASE64");
            String param = GsonUtils.toJson(map);
            // ע�������Ϊ�˼򻯱���ÿһ������ȥ��ȡaccess_token�����ϻ���access_token�й���ʱ�䣬 �ͻ��˿����л��棬���ں����»�ȡ��
            String accessToken = AuthService.getAuth();
            String result = HttpUtil.post(url, accessToken, "application/json", param);
//            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String result = FaceDetect.detect();
        System.out.println(result);
        //����json�ַ���
        JSONObject obj = JSONObject.parseObject(result);
        String r = obj.getString("result");
        
        JSONObject obj02 = JSONObject.parseObject(r);
        JSONArray arr = obj02.getJSONArray("face_list");
        JSONObject obj03 = arr.getJSONObject(0);
        String beautyStr = obj03.getString("beauty");
        float beauty = Float.parseFloat(beautyStr);
        
        System.out.println("***************beauty:" + beauty);
        if(beauty > 60){
        	System.out.println("����");
        }
         
        
    }
}