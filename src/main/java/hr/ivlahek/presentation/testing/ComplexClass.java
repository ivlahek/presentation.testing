package hr.ivlahek.presentation.testing;

import hr.ivlahek.presentation.testing.entity.User;
import hr.ivlahek.presentation.testing.external.ExternalSystemClient;
import hr.ivlahek.presentation.testing.request.ComplexRequest;
import hr.ivlahek.presentation.testing.service.UserService;

import javax.inject.Inject;
import javax.transaction.Transactional;

/**
 * Created by ivlahek on 23.6.2016..
 */
@Transactional
public class ComplexClass {
    @Inject
    private UserService userService;

    @Inject
    private ExternalSystemClient externalSystemClient;

    public User doSomething(ComplexRequest complexRequest) {
        boolean exists = externalSystemClient.checkAddressExists(complexRequest.address, complexRequest.houseNumber, complexRequest.postalCode);

        if (!exists) {
            throw new RuntimeException();
        }

        UserService.Request request = new UserService.Request();
        request.setLanguage(complexRequest.language);
        request.setAddress(buildAddress(complexRequest.address, complexRequest.houseNumber));
        request.setCityId(complexRequest.cityId);
        request.setName(complexRequest.name);
        request.setSurname(complexRequest.surname);
        return userService.createUser(request);
    }

    String buildAddress(String address, int houseNumber) {
        return address + " " + houseNumber;
    }

    public ExternalSystemClient getExternalSystemClient() {
        return externalSystemClient;
    }

    public void setExternalSystemClient(ExternalSystemClient externalSystemClient) {
        this.externalSystemClient = externalSystemClient;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

}
