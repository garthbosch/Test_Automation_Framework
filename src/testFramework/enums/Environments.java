package testFramework.enums;

import testFramework.utils.ApplicationConfiguration;
import java.io.Serializable;

/**
 *
 * @author Garth Bosch
 */
public enum Environments implements Serializable {

    DEV(ApplicationConfiguration.getDevOneFMurl()),
    QA(ApplicationConfiguration.getQaOneFMurl()),
    Prod(ApplicationConfiguration.getProdOneFMurl());

    public final String OneFMurl;

    Environments(String OneFMurl) {
        this.OneFMurl = OneFMurl;
    }
}
