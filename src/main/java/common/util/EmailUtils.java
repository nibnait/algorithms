package common.util;

import common.bo.EmailBO;
import io.github.biezhi.ome.OhMyEmail;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.List;
import java.util.Properties;

@Slf4j
public class EmailUtils {


    private static final String COMP_EMAIL = "w@tianbin.org";
    private static final String COMP_PW = "xxx";

    private static final String SENDER_NAME = "系统";


    public static void initEmailCfg(){
        Properties props = new Properties();
        props.setProperty("username", COMP_EMAIL);
        props.setProperty("password", COMP_PW);
        // 开启debug调试
        props.setProperty("mail.debug", "false");  //false
        // 发送服务器需要身份验证
        props.setProperty("mail.smtp.auth", "true");
        // 设置邮件服务器主机名
        props.setProperty("mail.host", "smtp.partner.outlook.cn");
        props.setProperty("mail.smtp.host", "smtp.partner.outlook.cn");
        // 发送邮件协议名称
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");

        OhMyEmail.config(props);
    }


    public static void sendEmail(EmailBO emailBO) {
        OhMyEmail.config(OhMyEmail.SMTP_ENT_QQ(false), COMP_EMAIL, COMP_PW);

        if (StringUtils.isBlank(emailBO.getToAddress()) || StringUtils.isBlank(emailBO.getSubject())) {
            return;
        }

        initEmailCfg();

        String fromNickName = emailBO.getFromNickName();
        if (StringUtils.isBlank(fromNickName)) {
            fromNickName = SENDER_NAME;
        }

        try {
            OhMyEmail ohMyEmail = OhMyEmail.subject(emailBO.getSubject())
                    .from(fromNickName)
                    .to(emailBO.getToAddress());

            if (StringUtils.isNotBlank(emailBO.getCcAddress())) {
                ohMyEmail.cc(emailBO.getCcAddress());
            }
            List<File> files = emailBO.getAttachFiles();
            if(files != null && !files.isEmpty()){
                for(File file:files){
                    ohMyEmail.attach(file);
                }
            }

            ohMyEmail.html(emailBO.getContent());
            ohMyEmail.send();
        } catch (Exception e) {
            log.error("EmailUtils.sendEmailAttachFiles error ", e);
        }

    }

}
