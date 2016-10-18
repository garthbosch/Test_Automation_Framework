package testFramework.testing.testSuites;

import testFramework.utils.MemberDetails;
import testFramework.utils.Parameter;
import testFramework.utils.ParameterHelper;
import java.util.List;

/**
 *
 * @author Garth Bosch
 */
public class MembersTests extends BaseClass {

    public MembersTests() {
    }

    /**
     * This will add existing members on the database to a Scheme
     *
     * @param env
     * @param qcTestLabPath
     * @param qcTestSetName
     * @return
     * @throws Exception
     */
    public Boolean addExistingMembersToSchemeTest(String env, String qcTestLabPath, String qcTestSetName) throws Exception {
        List<Parameter> parms = ParameterHelper.readParameters("Schemes");
        //getSite(parms.get(9), env);
        Boolean testResult = addingMembersToaScheme(parms.get(9), env);
        //tearDown();
//        sortFiles(parms.get(9));
        if (!zipEvidenceFolder()) {
            System.err.println("[Error] The directory \"" + evidenceFolder + "\" was not zipped");
        }
        //seleniumDriver.updateTheQualityCenterTests(parms.get(0), qcTestLabPath, qcTestSetName);
        return testResult;
    }

    public Boolean addingMembersToaScheme(Parameter parameter, String env) throws Exception {
        List<MemberDetails> memDetails = ParameterHelper.readMemberDetails("ExistingMemberDetails");
        return schemes.addSchemeMembersViaPolicyAdmin(parameter, env, memDetails);
    }
}
