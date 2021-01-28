package uk.co.ee.loginexercise.service;

import org.apache.log4j.Logger;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import uk.co.ee.loginexercise.exception.LoginException;
import uk.co.ee.loginexercise.model.Login;
import uk.co.ee.loginexercise.model.UserDetail;

import java.util.List;
import java.util.Optional;

import static uk.co.ee.loginexercise.util.ValidationUtil.validateCredential;

@Service
@ConfigurationProperties(prefix = "exercise")
public class AuthenticationService {

    private List<UserDetail> userDetails;

    private final static Logger logger = Logger.getLogger(AuthenticationService.class);

    public UserDetail authenticate(Login request) throws LoginException {
        validateCredential(request);
        logger.info("Input request is valid for user: " + request.getUserName());
        System.out.println("login request: " + request);
        System.out.println("userDetails: " + userDetails);

        Optional<UserDetail> userDetail = userDetails.stream().
                filter(u -> u.getEmail().equals(request.getUserName()) && u.getPassword().equals(request.getPassword()))
                .findFirst();
        if (userDetail.isPresent()) {
            return userDetail.get();
        } else {
            throw new LoginException("User not found, User credential did not match with any registered user.");
        }
    }

    public List<UserDetail> getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(List<UserDetail> userDetails) {
        this.userDetails = userDetails;
    }
}
