package hr.ivlahek.presentation.testing.external;

/**
 * Created by ivlahek on 23.6.2016..
 */
public interface ExternalSystemClient {

    public boolean checkAddressExists(String address, int houseNumber, int postalCode);
}
