package uk.co.ee.loginexercise.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uk.co.ee.loginexercise.exception.LoginException;
import uk.co.ee.loginexercise.model.Login;
import uk.co.ee.loginexercise.model.UserDetail;
import uk.co.ee.loginexercise.service.AuthenticationService;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static uk.co.ee.loginexercise.service.MessageService.getGreetingMessage;
import static uk.co.ee.loginexercise.util.ValidationUtil.validateCredential;

@Controller
public class LoginController {

    private final static Logger logger = Logger.getLogger(LoginController.class);

    private Map<String, Date> lastLoginTime = new HashMap<>();

    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(ModelMap model) {
        logger.info("Request received for login");
        model.addAttribute(new Login());
        return "login";
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute("login") Login login, BindingResult bindingResult, ModelMap model) {
        boolean isValidUser = false;
        String message;
        try {
            validateCredential(login);
            UserDetail user = authenticationService.authenticate(login);
            final String greetingMessage = getGreetingMessage(LocalDateTime.now());
            Date date = lastLoginTime.containsKey(user.getEmail()) ? lastLoginTime.get(user.getEmail()) : new Date();
            message = greetingMessage + user.getName() + ". You last logged in at " + date;

            lastLoginTime.put(user.getEmail(), new Date());
            isValidUser = true;
        } catch (LoginException e) {
            logger.error("Input validation failed with error: ", e);
            message = "Please try again";
        }
        model.addAttribute("message", message);
        return isValidUser ? "welcome" : "login";
    }
}


