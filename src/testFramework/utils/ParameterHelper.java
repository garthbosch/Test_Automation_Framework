package testFramework.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * @author Garth Bosch
 */
public class ParameterHelper {

    private static String FILE = null;

    public static List<Parameter> readParameters(String dataFile) throws Exception {
        FILE = dataFile;
        List<Parameter> list = new ArrayList<Parameter>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(FILE));
            String line = br.readLine();//header row - ignore
            while ((line = br.readLine()) != null) {
                Parameter parm = createParameter(line);
                if (parm == null) {
                    break;
                }
                list.add(parm);
            }
            br.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public static List<MemberDetails> readMemberDetails(String dataFile) throws Exception {
        FILE = dataFile;
        List<MemberDetails> list = new ArrayList<MemberDetails>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(FILE));
            String line = br.readLine();//header row - ignore
            while ((line = br.readLine()) != null) {
                MemberDetails memDetail = createMemberDetails(line);
                if (memDetail == null) {
                    break;
                }
                list.add(memDetail);
            }
            br.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    private static Parameter createParameter(String line) {
        if (line == null || line.isEmpty()) {
            return null;
        }

        Parameter parm = new Parameter();
        StringTokenizer st = new StringTokenizer(line, ",");
        // must be in same order as columns in spreadsheet     
        parm.setTestCaseName(st.nextToken());
        parm.setTestCaseCode(st.nextToken());
        parm.setSchemeName(st.nextToken());
        parm.setSchemeType(st.nextToken());
        parm.setSchemeRegion(st.nextToken());
        parm.setSchemeContactPersonTitle(st.nextToken());
        parm.setSchemeContactPersonSurname(st.nextToken());
        parm.setSchemeContactPersonFirstname(st.nextToken());
        parm.setSchemeContactPersonEmail(st.nextToken());
        parm.setSchemeContactPersonPhoneNo(st.nextToken());
        parm.setSchemeContactPersonPhoneFaxNo(st.nextToken());
        parm.setSchemeContactPersonPhoneCellNo(st.nextToken());
        parm.setSchemeStreetAddress1(st.nextToken());
        parm.setSchemeStreetAddress2(st.nextToken());
        parm.setSchemeStreetAddress3(st.nextToken());
        parm.setSchemeStreetAddress4(st.nextToken());
        parm.setSchemeStreetAddressPostalCode(st.nextToken());
        parm.setSchemeStreetAddressCity(st.nextToken());
        parm.setSchemeStreetAddressCountry(st.nextToken());
        parm.setSchemeStreetAddressSameForPostalInd(extractBoolean(st.nextToken()));
        parm.setSchemePostalAddress1(st.nextToken());
        parm.setSchemePostalAddress2(st.nextToken());
        parm.setSchemePostalAddress3(st.nextToken());
        parm.setSchemePostalAddress4(st.nextToken());
        parm.setSchemePostalAddressPostalCode(st.nextToken());
        parm.setSchemePostalAddressCity(st.nextToken());
        parm.setSchemeBankingDetailsBankName(st.nextToken());
        parm.setSchemeBankingDetailsBankCode(st.nextToken());
        parm.setSchemeBankingAccNo(st.nextToken());
        parm.setSchemeBankingAccType(st.nextToken());
        parm.setSchemePaymentMethodType(st.nextToken());
        parm.setSchemePaymentMethodPaymentDay(st.nextToken());
        parm.setSchemeServicingConsultantStaffCode(st.nextToken());
        parm.setSchemeSalesConsultantStaffCode(st.nextToken());
        parm.setSchemeProductOption(st.nextToken());
        parm.setSchemeProductOptionCover(st.nextToken());
        parm.setProductType(st.nextToken());
        parm.setExternalVendor(extractBoolean(st.nextToken()));
        parm.setReIssuedBusiness(extractBoolean(st.nextToken()));
        parm.setCashBack(extractBoolean(st.nextToken()));
        parm.setWaitingPeriod(st.nextToken());
        parm.setQcInstanceName(st.nextToken());
        parm.setScreenshotsLocation(st.nextToken());
        parm.setRunTest(extractBoolean(st.nextToken()));
        return parm;
    }

    private static Boolean extractBoolean(String s) {
        if (s == null || s.isEmpty() || s.equalsIgnoreCase("N")) {
            return false;
        }
        return true;
    }

    private static MemberDetails createMemberDetails(String line) {
        if (line == null || line.isEmpty()) {
            return null;
        }

        MemberDetails memDetail = new MemberDetails();
        StringTokenizer st = new StringTokenizer(line, ",");
        // must be in same order as columns in spreadsheet
        memDetail.setSchemeName(st.nextToken());        
        memDetail.setRelationship(st.nextToken());        
        memDetail.setIdNumber(st.nextToken());        
        memDetail.setDob(st.nextToken());        
        memDetail.setTitleDescription(st.nextToken());        
        memDetail.setFirstName(st.nextToken());        
        memDetail.setSurname(st.nextToken());        
        memDetail.setGender(st.nextToken());        
        memDetail.setCover(st.nextToken());        
        return memDetail;
    }
}
