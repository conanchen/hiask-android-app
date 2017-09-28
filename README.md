extras;android;m2repository

#注意！密钥库口令是  android
C:\Users\Administrator\.android>keytool -list -v -keystore debug.keystore
输入密钥库口令:

密钥库类型: JKS
密钥库提供方: SUN

您的密钥库包含 1 个条目

别名: androiddebugkey
创建日期: 2014-9-25
条目类型: PrivateKeyEntry
证书链长度: 1
证书[1]:
所有者: CN=Android Debug, O=Android, C=US
发布者: CN=Android Debug, O=Android, C=US
序列号: 5a2df74e
有效期开始日期: Thu Sep 25 10:40:41 CST 2014, 截止日期: Sat Sep 17 10:40:41 CST 2044
证书指纹:
         MD5: C7:AC:73:63:43:A2:E4:4C:F0:37:66:53:D0:77:DC:50
         SHA1: 4F:2C:48:26:34:C8:62:A6:77:3A:56:17:8D:C7:00:9C:D2:ED:AA:51
         SHA256: 4B:FA:A9:A0:FA:18:3C:F2:42:9A:94:3A:80:AA:51:CF:68:0F:B2:DD:13:7A:F8:75:9F:EA:65:84:63:22:07:15
         签名算法名称: SHA256withRSA
         版本: 3

扩展:

#1: ObjectId: 2.5.29.14 Criticality=false
SubjectKeyIdentifier [
KeyIdentifier [
0000: D3 E7 CA A0 6D 0B DC C0   68 26 9A 9B 8D E1 A8 1E  ....m...h&......
0010: 39 8E 4B 0C                                        9.K.
]
]



*******************************************
*******************************************


#百度位置服务 http://lbsyun.baidu.com/apiconsole/key
应用编号 	应用名称 	访问应用（AK） 	                应用类别
9616711 	HIASK	lNDtU3LLg2CXhQeNtr6a7qKnUFa9RQwS 	Android端
http://lbsyun.baidu.com/index.php?uuid=android-locsdk/geosdk-android-download

#高德地图 http://lbs.amap.com/dev/key/app
Key名称	                        Key	                          绑定服务
HIASK-MAPSDKKEY	    0cb63f20a55885d177339e9d50f0f87b	    Android平台

#QQ互联 http://op.open.qq.com/mobile_appinfov2/approverUuid?appid=1106139510

#微博互联 http://open.weibo.com/apps/2810973698/info/basic

#Identifying an Android Device – Available Identifiers
http://handstandsam.com/2017/05/04/identifying-an-android-device



# https://blog.synyx.de/2016/08/android-building-apks-for-different-environments-using-build-types-and-product-flavors/

#Fabric崩溃报告
https://www.fabric.io/modus--coltd/android/apps/org.ditto.hiask/dashboard
conan8chan@yahoo.com/fabric$100

#Windows AndroidStudio设置
- SSCap 采用 PAC模式， Socks 5:[1080] Http:[25378]
- 打开studio -- settings -- HTTP Proxy --
    - Manuual proxy configuration --- HTTP --- Host name: 127.0.0.1  Port number: 25378

#Macos AndroidStudio设置
- SScap 采用自动代理模式
- 打开studio -- preference -- HTTP Proxy --
    设置为No Proxy
    
    
#访问服务器开发

- https://apacheignite.readme.io/docs/docker-deployment
    - Pull latest version.
      1. $ sudo docker pull apacheignite/ignite:2.1.0
      2. $ sudo docker run -it --net=host -e "CONFIG_URI=https://raw.githubusercontent.com/apache/ignite/master/examples/config/example-cache.xml" apacheignite/ignite
      
      
