package com.yquick.simp.service.impl;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import org.apache.axis.client.Call;
import org.apache.axis.encoding.XMLType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.yquick.simp.service.RuiTaiService;
import com.yquick.simp.utils.MailUtil;

@PropertySource("classpath:application.properties")
@org.springframework.stereotype.Service("ruiTaiService")
public class RuiTaiServiceImpl implements RuiTaiService {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    MailUtil mailUtil;
    //测试瑞泰接口
    @Value("${RT_ADDRESS}")
    public String RT_ADDRESS;

    @Override
    public String getAuth(JSONObject data) throws Exception {
        logger.info("验证手机号参数："+data);
        String result = "";
        String phone = data.getString("phone");
        org.apache.axis.client.Service service = new org.apache.axis.client.Service();
        String targetNameSpace = "http://webservices.sinosoft.com";
        Call call = (Call) service.createCall();
        call.setTargetEndpointAddress(new URL(RT_ADDRESS));
        call.setOperationName(new QName(targetNameSpace, "QueryClinetPolicyCount"));
        call.addParameter(new QName(targetNameSpace, "phoneNumber"), XMLType.XSD_STRING, ParameterMode.IN);
        call.addParameter(new QName(targetNameSpace, "sysIndicator"), XMLType.XSD_STRING, ParameterMode.IN);
        call.setReturnType(XMLType.XSD_STRING);
        call.setUseSOAPAction(true);
        call.setSOAPActionURI(targetNameSpace + "QueryClinetPolicyCount");
        result = call.invoke(new Object[]{phone, "0"}).toString();
        logger.info("验证手机号返回值："+result);
        return result;
    }

    @Override
    public String getInfo(JSONObject data) throws Exception {
        logger.info("获取保单信息参数："+data);
        String result = "";
        String id = data.getString("id");
        org.apache.axis.client.Service service = new org.apache.axis.client.Service();
        String targetNameSpace = "http://webservices.sinosoft.com";
        Call call = (Call) service.createCall();
        call.setTargetEndpointAddress(new URL(RT_ADDRESS));
        call.setOperationName(new QName(targetNameSpace, "QueryClinetPolicyList"));
        call.addParameter(new QName(targetNameSpace, "idNumber"), XMLType.XSD_STRING, ParameterMode.IN);
        call.addParameter(new QName(targetNameSpace, "sysIndicator"), XMLType.XSD_STRING, ParameterMode.IN);
        call.setReturnType(XMLType.XSD_STRING);
        call.setUseSOAPAction(true);
        call.setSOAPActionURI(targetNameSpace + "QueryClinetPolicyList");
        result = call.invoke(new Object[]{id, "0"}).toString();
        logger.info("获取保单信息返回值："+result);
        return result;
    }

    @Override
    public Object sendEmail(JSONObject data) {
        Object result = mailUtil.sendEmail(data);
        return result;
    }
}
