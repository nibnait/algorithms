package zzzTest.JsoupTest;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LicenseUrlSource {
    BeiJing("qyxy.baic.gov.cn", "北京"),
    HeBei("hebscztxyxx.gov.cn", "河北"),
    ShangHai("sgs.gov.cn", "上海"),
    JiangSu("jsgsj.gov.cn", "江苏"),
    JiangXi("gsxt.jxaic.gov.cn", "江西"),
    HeNan("dzhy.haaic.gov.cn", "河南"),
    QingHai("218.95.241.36", "青海"),
    GuangDong("gsxt.gdgs.gov.cn", "广东"),
    SiChuan("gsxt.scaic.gov.cn", "四川"),
    GuiZhou("gsxt.gzgs.gov.cn", "贵州"),
    YunNan("gsxt.ynaic.gov.cn", "云南"),
    AnHui("218.22.14.66", "安徽"),
    HaiNan("202.100.252.118", "海南"),
    XiZang("220.182.3.99", "西藏"),
    HuNan("gsxt.hnaic.gov.cn", "湖南");

    private String url;
    private String chineseDesc;
}
