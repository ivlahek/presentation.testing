package hr.ivlahek.presentation.testing.external;

import javax.enterprise.inject.Alternative;

/**
 * Created by ivlahek on 29.6.2016..
 */
@Alternative
public class MockExternalSystemClient implements ExternalSystemClient {
    private static final String VALID_ADDRESS = "Ulica grada Vukovara";
    private static final int VALID_POSTAL_CODE = 10000;
    private int VALID_HOUSE_NUMBER = 269;

    public boolean checkAddressExists(String address, int houseNumber, int postalCode) {
        if (VALID_ADDRESS.equals(address) && houseNumber == VALID_HOUSE_NUMBER && postalCode == VALID_POSTAL_CODE) {
            return true;
        }
        return false;
    }
}
