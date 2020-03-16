package com.tester.utils;

import com.tester.model.InterfaceName;

import java.util.Locale;
import java.util.ResourceBundle;

public class ConfigFile {
    //该文件主要是将每个接口完整的url拼接出来
    private static  ResourceBundle bundle = ResourceBundle.getBundle("application", Locale.CANADA);


    public static String getUrl(InterfaceName interfaceName){
        String sitwxurl = bundle.getString("wx.test.url");
        String sitweburl = bundle.getString("web.test.url");
        String prowxurl = bundle.getString("wx.pro.url");
        String proweburl = bundle.getString("web.pro.url");
        String adress = "";
        String wxtestUrl;
        String webtestUrl;
        String wxproUrl;
        String webproUrl;
        if (interfaceName == InterfaceName.ISFIRSTOPEN){
            adress = bundle.getString("invite.isFirstOpen.url");
        }
        if (interfaceName == InterfaceName.FIRSTOPEN){
            adress = bundle.getString("invite.firstOpen.url");
        }
        if (interfaceName == InterfaceName.SHARE){
            adress = bundle.getString("invite.share.url");
        }
        if (interfaceName == InterfaceName.LIST){
            adress = bundle.getString("invite.list.url");
        }
        if (interfaceName == InterfaceName.RECEIVERLIST){
            adress = bundle.getString("invite.receiveList.url");
        }
        if (interfaceName == InterfaceName.FIRSTOPENLIST){
            adress = bundle.getString("invite.firstOpenList.url");
        }
        if (interfaceName == InterfaceName.RECEIVER){
            adress = bundle.getString("invite.receive.url");
        }if (interfaceName == InterfaceName.OPENRECEIVE){
            adress = bundle.getString("invite.openReceive.url");
        }
        if (interfaceName == InterfaceName.LOGIN){
            adress = bundle.getString("voucherTrade.report");
        }
        if (interfaceName == InterfaceName.VOUCHERQUERY){
            adress = bundle.getString("voucher.query");
        }
        wxtestUrl = sitwxurl + adress;
        webtestUrl = sitweburl + adress;
        wxproUrl = prowxurl + adress;
        webproUrl = proweburl + adress;
        return webtestUrl;

    }
}
