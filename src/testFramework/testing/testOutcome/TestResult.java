package testFramework.testing.testOutcome;

import java.util.List;
import testFramework.utils.Parameter;

/**
 *
 * @author Garth Bosch
 */
public class TestResult {

    public List<Parameter> parameter;
    public boolean testPassedOrFailed;
    public String errorMessage;
    public long testDuration;

    public TestResult(List<Parameter> parameter, boolean testPassedOrFailed, String errorMessage, long testDuration) {
        this.parameter = parameter;
        this.testPassedOrFailed = testPassedOrFailed;
        this.errorMessage = errorMessage;
        this.testDuration = testDuration;
    }

    public String calculateFormattedTestTime() {
        String formattedTestDuration = "";
        long tSec, tMin = 0, tHours = 0;

        tSec = this.testDuration;

        if (tSec > 60) {
            while (tSec > 60) {
                tMin += 1;
                tSec -= 60;

            }
        }

        if (tMin > 60) {
            while (tMin > 60) {
                tHours += 1;
                tMin -= 60;
            }
        }

        if (tHours > 0) {
            formattedTestDuration += String.valueOf(tHours) + " Hour(s), ";
        }
        if (tMin > 0) {
            formattedTestDuration += String.valueOf(tMin) + " Minute(s), ";
        }
        if (tSec > 0) {
            formattedTestDuration += String.valueOf(tSec) + " Second(s)";
        }

        return formattedTestDuration;
    }
}
