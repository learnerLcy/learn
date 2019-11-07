package com.test;



import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 1250052380@qq.com on 2015/5/19.
 */
public class XmlParse {
        Map map = new HashMap();
        public static void xml2JSON( String xml) {
                JSONObject resutStr = null;
                try {
                        resutStr  = XML.toJSONObject(xml);
                } catch (JSONException e) {
                        e.printStackTrace();
                }
                System.out.println(resutStr);

        }

        public static void main(String[] args){
                String xml="<?xml version=\"1.0\" encoding=\"utf-8\" ?><MoBaoAccount MessageType=\"UserMobilePay\" PlatformID=\"b2ctest\"><OrderNo>M20150521084825</OrderNo><TradeAmt>5000.00</TradeAmt><Commission>0.5</Commission><UserID>zhuxiaolong</UserID><MerchID>zhuxiaolong1</MerchID><tradeType>0</tradeType><CustParam>123</CustParam> <NotifyUrl>http://mobaopay.com/callback.do</NotifyUrl><TradeSummary>订单</TradeSummary></MoBaoAccount>";
                xml2JSON(xml);
        }
}
