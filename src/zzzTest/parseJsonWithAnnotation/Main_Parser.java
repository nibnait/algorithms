package zzzTest.parseJsonWithAnnotation;

import org.apache.commons.lang3.StringUtils;
import zzzTest.JsonParseTest.JsonHelperTest.JsonHelper;
import zzzTest.parseJsonWithAnnotation.Models.*;
import zzzTest.parseJsonWithAnnotation.abstractClass.ReasonParserConstants;
import zzzTest.parseJsonWithAnnotation.abstractClass.RunShopAuditType;
import zzzTest.parseJsonWithAnnotation.reasonParsers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Main_Parser {

    public static void main(String[] args) throws IOException {
//        Path path = Paths.get("/Users/nibnait/Desktop/拒绝15250.txt");
//        Path path = Paths.get("/Users/nibnait/Desktop/拒绝14112.txt");
        Path path = Paths.get("/Users/nibnait/Desktop/2.txt");
        String reason = new String(Files.readAllBytes(path));
        RefuseReasonDisplay refuseReasonDisplay = refactorRefuseReason(reason);
        System.out.println(refuseReasonDisplay.toString());
    }

    private static RefuseReasonDisplay refactorRefuseReason(String reason){
        RefuseReasonDisplay refuseReasonDisplay = new RefuseReasonDisplay();
        if (StringUtils.isBlank(reason)) {
            return refuseReasonDisplay;
        }
        StringBuilder storeInfoOtherStr = new StringBuilder();
        StringBuilder qualificationInfoOtherStr = new StringBuilder();
        Map<String, String> reasonMap = new HashMap<>();
        try {
            reasonMap = JsonHelper.toJsonObject(reason, Map.class);
        } catch (Exception e) {
            refuseReasonDisplay.setStoreInfoReason(new StoreInfoReason(reason));
            if (reason.contains("4") || reason.contains("5")) {
                refuseReasonDisplay.setQualificationInfoReason(new QualificationInfoReason(reason));
            }
            return refuseReasonDisplay;
        }
        Map<String, String> storeReasonMap = getStoreReasomMap(reasonMap);
        Map<String, String> logoReasonMap = getLogoReasonMap(reasonMap);
        Map<String, String> legalReasonMap = getLegalReasonMap(reasonMap);
        Map<String, String> licenseReasonMap = getLicenseReasonMap(reasonMap);
        Map<String, String> permitReasonMap = getPermitReasonMap(reasonMap);

        storeInfoOtherStr.append(getOtherReason(ReasonParserConstants.STORE_INFO, storeReasonMap));
        storeInfoOtherStr.append(getOtherReason(ReasonParserConstants.LOGO_INFO, logoReasonMap));
        qualificationInfoOtherStr.append(getOtherReason(ReasonParserConstants.LEGAL_INFO, legalReasonMap));
        qualificationInfoOtherStr.append(getOtherReason(ReasonParserConstants.LICENSE_INFO, licenseReasonMap));
        qualificationInfoOtherStr.append(getOtherReason(ReasonParserConstants.PERMIT_INFO, permitReasonMap));

        StoreInfoReason storeInfoReason = new StoreInfoReason(storeInfoOtherStr.toString());
        storeInfoReason.setStoreReason(storeReasonMap);
        storeInfoReason.setLogoReason(logoReasonMap);
        refuseReasonDisplay.setStoreInfoReason(storeInfoReason);
        QualificationInfoReason qualificationInfoReason = new QualificationInfoReason(qualificationInfoOtherStr.toString());
        qualificationInfoReason.setLegalReason(legalReasonMap);
        qualificationInfoReason.setLicenseReason(licenseReasonMap);
        qualificationInfoReason.setPermitReason(permitReasonMap);
        refuseReasonDisplay.setQualificationInfoReason(qualificationInfoReason);
        return refuseReasonDisplay;
    }

    private static Map<String,String> getPermitReasonMap(Map<String, String> reasonMap) {
        Map<String, String> permitReasonMap = new HashMap<>();
        ReasonParser<PermitReason> permitReasonParser = new PermitReasonParser();
        String permitReasonValue = reasonMap.get(RunShopAuditType.PERMIT.getCode()+"");
        if (StringUtils.isNotBlank(permitReasonValue)) {
            permitReasonMap = permitReasonParser.parseReason(permitReasonValue);
        } else {
            permitReasonMap = permitReasonParser.parseReason(StringUtils.EMPTY);
        }
        return permitReasonMap;
    }

    private static Map<String,String> getLicenseReasonMap(Map<String, String> reasonMap) {
        Map<String, String> licenseReasonMap = new HashMap<>();
        ReasonParser<LicenseReason> licenseReasonParser = new LicenseReasonParser();
        String licenseReasonValue = reasonMap.get(RunShopAuditType.LICENSE.getCode()+"");
        if (StringUtils.isNotBlank(licenseReasonValue)) {
            licenseReasonMap = licenseReasonParser.parseReason(licenseReasonValue);
        } else {
            licenseReasonMap = licenseReasonParser.parseReason(StringUtils.EMPTY);
        }
        return licenseReasonMap;
    }

    private static Map<String,String> getLegalReasonMap(Map<String, String> reasonMap) {
        Map<String, String> legalReasonMap = new HashMap<>();
        ReasonParser<LegalReason> leagleReasonParser = new LegalReasonParser();
        String legalReasonValue = reasonMap.get(RunShopAuditType.LEGAL_PERSON.getCode()+"");
        if (StringUtils.isNotBlank(legalReasonValue)) {
            legalReasonMap = leagleReasonParser.parseReason(legalReasonValue);
        } else {
            legalReasonMap = leagleReasonParser.parseReason(StringUtils.EMPTY);
        }
        return legalReasonMap;
    }

    private static Map<String,String> getLogoReasonMap(Map<String, String> reasonMap) {
        Map<String, String> logoReasonMap = new HashMap<>();
        ReasonParser<LogoReason> logoReasonParser = new LogoReasonParser();
        String logoReasonValue = reasonMap.get(RunShopAuditType.SHOP_LOGO.getCode()+"");
        if (StringUtils.isNotBlank(logoReasonValue)) {
            logoReasonMap = logoReasonParser.parseReason(logoReasonValue);
        } else {
            logoReasonMap = logoReasonParser.parseReason(StringUtils.EMPTY);
        }
        return logoReasonMap;
    }

    private static Map<String,String> getStoreReasomMap(Map<String, String> reasonMap) {
        Map<String, String> storeReasonMap = new HashMap<>();
        ReasonParser<StoreReason> storeReasonParser = new StoreReasonParser();
        String storeReasonValue = reasonMap.get(RunShopAuditType.SHOP_BASE.getCode()+"");
        if (StringUtils.isNotBlank(storeReasonValue)) {
            storeReasonMap = storeReasonParser.parseReason(storeReasonValue);
        } else {
            storeReasonMap = storeReasonParser.parseReason(StringUtils.EMPTY);
        }
        return storeReasonMap;
    }

    private static String getOtherReason(String title, Map<String, String> storeReasonMap) {
        StringBuilder sb = new StringBuilder();
        if (StringUtils.isNotBlank(storeReasonMap.get(ReasonParserConstants.OTHER))) {
            sb.append(title+storeReasonMap.get(ReasonParserConstants.OTHER)+"； ");
        }
        if (StringUtils.isNotBlank(storeReasonMap.get(ReasonParserConstants.CUSTOM))) {
            sb.append(title+storeReasonMap.get(ReasonParserConstants.CUSTOM)+"； ");
        }
        return sb.toString();
    }
}
