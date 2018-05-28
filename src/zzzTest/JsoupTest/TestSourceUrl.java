package zzzTest.JsoupTest;

public interface TestSourceUrl {
    //北京！！！还没test
    String beiJing = "";    //TODO test北京URL
    //河北 √
    String heBeiUrl2 = "http://www.hebscztxyxx.gov.cn/noticehb/notice/view?uuid=E_qAUjuf7CB8BG8lk9IKyiNWKtlKMG1o";
    String heBeiUrl = "http://www.hebscztxyxx.gov.cn/noticehb/notice/view?uuid=UG5KbQC.TvMgx8G6IO7C_MIhcIe.QQSX";
    //湖南
    String huNanUrl = "http://gsxt.hnaic.gov.cn/notice/notice/view?uuid=ZuhxbApOrkPXPR3bxEE8RZ37uEYNBooo";
    //山西（识别出来的是文本）    404
    String shanxiUrl = "http://sx.gsxt.gov.cn/index.jspx/EntInfo.jspx?id=YmZrbG9xcXVyd3Fzc21zZW9n";
    //上海
    String shangHaiUrl = "http://www.sgs.gov.cn/notice/notice/view?uuid=9nqL_YzklLDteKGSu6GKAg==";
    //江苏
    String jiangSu_In = "http://www.jsgsj.gov.cn:58888/ecipplatform/jiangsu.jsp?org=D7AAF49ABDED20EA0E281524C450AE45&id=85F75AC581F8F294DBDAA94CA282A378&seqId=432B1984C1B1FD74FFD5CEE0CC7B6641&activeTabId=";
    String jiangSu_Final = "http://www.jsgsj.gov.cn:58888/ecipplatform/publicInfoQueryServlet.json?pageView=true&org=D7AAF49ABDED20EA0E281524C450AE45&id=0C4E2A6C07781D7EA919A28E972DA712&seqId=900C2187754223A667A46855C08F7D75";
    //广东
    String guangDong2 = "http://gsxt.gdgs.gov.cn//GSpublicity/GSpublicityList.html?jumpid=rO0ABXQATXtzZXJ2aWNlOmVudEluZm8sZW50Tm86MzdjNzdiY2UtMDE1Yy0xMDAwLWUwMDEtN2E5%0D%0ANjBhMGEwMTE1LHJlZ09yZzo0NDEzMDIwMTJ9%0D%0A";
    String guangDong = "http://gsxt.gdgs.gov.cn/GSpublicity/GSpublicityList.html?jumpid=rO0ABXQASntzZXJ2aWNlOmVudEluZm8sZW50Tm86YTQyOWRhOWQtMDE2Mi0xMDAwLWUwMDAtNDEy%0D%0AMzBhMDkwMTE1LHJlZ09yZzo0NDE0MDJ9%0D%0A";
    //四川
    String SiChuan = "http://gsxt.scaic.gov.cn/notice/notice/view?uuid=IkXwZcJFipEJqxu2uUqJyIzC17Qo64zG";
    //江西    TODO 需解密pripid
    String JiangXi  = "http://gsxt.jxaic.gov.cn/ECPS/qyxxgsAction_initQyxyxxMain.action?qylx=9999&nbxh=20160801142645241282&qylxFlag=2&zch=360108610008710";
    String JiangXi_Final  = "http://gsxt.jxaic.gov.cn/baseinfo/queryenterpriseinfoByRegnore.do?pripid=MjAxNjA4MDExNDI2NDUyNDEyODI=";
    //河南
    String HeNan = "http://dzhy.haaic.gov.cn/yzt/toHandleQuery.do?id=YmVobG9xcXF4dXJ1cmp2Z3Nq&uniScID=amNrbW9ycXVeVXVycWxcaXVl&jumpFlag=false";
    String HeNan_Final = "http://dzhy.haaic.gov.cn/yzt/ent/queryEntInfoById.do?str=YmVobG9xcXF4dXJ1cmp2Z3Nq&page=1&pageSize=1";
    //青海
    String QingHai = "http://218.95.241.36:8035/toHandleQuery.do?id=YmdqbG9xcXBye3RudGl2Z25k&uniScID=amNtb29ycXReVXhyPWpFcnAm&jumpFlag=false";
    //贵州
    String GuiZhou2 = "http://gsxt.gzgs.gov.cn/company/detail.jspx?id=57E5678388A1925E1123D4D2DDA9F1FF&jyzk=jyzc&jumpFlag=false";
    String GuiZhou = "http://gsxt.gzgs.gov.cn/company/detail.jspx?id=0A7805FD138E9F9A9BF0B683CCE8F357&jyzk=jyzc&jumpFlag=false";
    String GuiZhou_Final = "http://gsxt.gzgs.gov.cn/company/basic.jspx?id=57E5678388A1925E1123D4D2DDA9F1FF";
    //云南
    String YunNan2 = "http://gsxt.ynaic.gov.cn/notice/notice/view?uuid=Ubg6qUKVDFra3oK6eZ9AzbAnJGhQ1vzU";
    String YunNan = "http://gsxt.gzgs.gov.cn/company/detail.jspx?id=0A7805FD138E9F9A9BF0B683CCE8F357&jyzk=jyzc&jumpFlag=false";
    //安徽
    String AnHui2 = "http://218.22.14.66:8082/yzt/toHandleQuery.do?id=ZWFnbG9xcXh0d3NudGt2YnNp&uniScID=amNqcG9ycnFeVXNCdWs+YnB5&jumpFlag=false&nsukey=P2plowDpjF46V2fKVKOAfzazs%2FWh0ilBUmxfDG1qXw3xX%2FB7fDPuVYJ1%2B8m70XjfgAwk1qjc0fGP6XZp9r8UoBCM%2FqAe748CdlWcXPWaoVvJQJ7LNrYb3skD2MjdEQmDl7jnF2Di2BC1wzC3rbB5SEa%2FaBN1I4vAF0nbv6xJR%2BT4kNk9cxFDJgV4vdhoYkc7lQv1p2GPJr0jvZNcSPV2%2Bw%3D%3D";
    String AnHui = "http://218.22.14.66:8082/yzt/toHandleQuery.do?id=ZWFnbG9xcXBvdHVxeGhzY21i&uniScID=amNqcG9ycXBeVXNBVWtGJnJz&jumpFlag=false&nsukey=AmakEV0bgUEKEWUnMqeYMM7BM1Qz5Sf8qlnsnSCiGAbbGlcdRMKGNOym%2BXGr3F4UdoHCfBs%2BRkzePdnNJCA37B3EDym2UhdM0sMoeDz7cO7sF99MDIRxnCJs1QwAy6p3njA7BXuWxoXFFQPBtZSaAAW3HXBsTBiaxEZovGdOcuA6now%2FRY6Mgv0c8czeu%2BJJ57XTKoFlIWS2FS7R8JOn9Q%3D%3D";
    String AnHui_Final = "http://218.22.14.66:8082/yzt/ent/queryEntInfoById.do?str=ZWFnbG9xcXh0d3NudGt2YnNp&page=1&pageSize=1";
    //海南
    String HaiNan = "http://202.100.252.118:8079/yzt/toHandleQuery.do?id=YmVobG9xcXFzd3VxdWd2ZnFk&uniScID=amNrcm9ycXheVXZFdH1GTXB2&jumpFlag=false&nsukey=%2B6bRDrDLDbIg7zPv8g6Scy%2FUYH9M8dRKZ%2FhG9jxPVPlU0lkoB6qo8n19RbOoAoksrWp6OmYLGE2GQgyurN5D32APo2I2g64wnZouWde%2BXzhuPrOVli6VK%2F4drmqWEhqfGj6Hk8uk9ts1mzShf8iIq7Ua0WK5fa%2Bsj%2Fk%2FYwIUfFyy3d0OC71bxtFpKjiiUQ3VQxDG%2F59SqgvBrx0CSL0CBA%3D%3D";
    //西藏
    String XiZang = "http://220.182.3.99:9079/yztww/toHandleQuery.do?id=YmFndHZ1eHRwe3N2cg==&uniScID=amNscG9yenFeVXdFdmt5JHEo&jumpFlag=false";


}
