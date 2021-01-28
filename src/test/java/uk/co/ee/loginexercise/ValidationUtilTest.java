package uk.co.ee.loginexercise;


import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import uk.co.ee.loginexercise.exception.LoginException;
import uk.co.ee.loginexercise.model.Login;
import uk.co.ee.loginexercise.util.ValidationUtil;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.Assert.assertTrue;
import static uk.co.ee.loginexercise.util.ValidationUtil.validateCredential;

@RunWith(MockitoJUnitRunner.class)
public class ValidationUtilTest {

    @Test
    public void testEmailIdShouldBeValid() {
        //given
        final String email = "test@gmail..com";
        //when
        Throwable exception = catchThrowable(() -> ValidationUtil.isValidEmail(email));
        //then
        Assertions.assertThat(exception).isInstanceOf(LoginException.class)
                .hasMessageContaining("Input email format is not valid, please enter valid email");

        //given
        final String email1 = "test@abc@gmail.com";
        //when
        Throwable exception1 = catchThrowable(() -> ValidationUtil.isValidEmail(email1));
        //then
        Assertions.assertThat(exception1).isInstanceOf(LoginException.class)
                .hasMessageContaining("Input email format is not valid, please enter valid email");

    }

    @Test
    public void testEmailIdIdShouldNotBeNull() {
        //given
        final String email = null;
        //when
        Throwable exception = catchThrowable(() -> ValidationUtil.isValidEmail(email));
        //then
        Assertions.assertThat(exception).isInstanceOf(LoginException.class)
                .hasMessageContaining("Email id can not be empty");
    }

    @Test
    public void shouldReturnTrueWhenEmailIdIsCorrect() throws Exception {
        //given
        final String email = "johnson.k@bt.com";
        //when
        boolean isEmailValid = ValidationUtil.isValidEmail(email);
        //then
        assertTrue(isEmailValid);
    }

    @Test
    public void passwordLengthShouldBeBetween8And30Char() {
        //given
        //when password is empty or null
        final String password = "";
        //when
        Throwable exception = catchThrowable(() -> ValidationUtil.isValidPassword(password));
        //then
        Assertions.assertThat(exception).isInstanceOf(LoginException.class)
                .hasMessageContaining("Password can not be empty");

        //given
        //when password is less than 8 char
        final String password8 = "BtLon5";
        //when
        Throwable exception8 = catchThrowable(() -> ValidationUtil.isValidPassword(password8));
        //then
        Assertions.assertThat(exception8).isInstanceOf(LoginException.class)
                .hasMessageContaining("Invalid password, password must be between 8-30 char, at least 1 upper, 1 lower and 1 number");

        //given
        //when password is valid but more than 30 char
        final String password30 = "Dbcdefghijklmn12345abcdefghijklmn12345abcdefghijklmn";
        //when
        Throwable exception30 = catchThrowable(() -> ValidationUtil.isValidPassword(password30));
        //then
        Assertions.assertThat(exception30).isInstanceOf(LoginException.class)
                .hasMessageContaining("Invalid password, password must be between 8-30 char, at least 1 upper, 1 lower and 1 number");

        //given
        //when password is between but 8 - 30 char but no number
        final String passwordNotNumber = "BtLondon";
        //when
        Throwable exceptionWithoutNumber = catchThrowable(() -> ValidationUtil.isValidPassword(passwordNotNumber));
        //then
        Assertions.assertThat(exceptionWithoutNumber).isInstanceOf(LoginException.class)
                .hasMessageContaining("Invalid password, password must be between 8-30 char, at least 1 upper, 1 lower and 1 number");

        //given
        //when password is between but 8 - 30 char but no upper case letter
        final String passwordNoUpperCase = "btlondon5";
        //when
        Throwable exceptionWithoutUpperCase = catchThrowable(() -> ValidationUtil.isValidPassword(passwordNoUpperCase));
        //then
        Assertions.assertThat(exceptionWithoutUpperCase).isInstanceOf(LoginException.class)
                .hasMessageContaining("Invalid password, password must be between 8-30 char, at least 1 upper, 1 lower and 1 number");

        //when password is between but 8 - 30 char but no upper case letter
        final String passwordNoLowerCase = "BTLONDON5";
        //when
        Throwable exceptionWithoutLowerCase = catchThrowable(() -> ValidationUtil.isValidPassword(passwordNoLowerCase));
        //then
        Assertions.assertThat(exceptionWithoutLowerCase).isInstanceOf(LoginException.class)
                .hasMessageContaining("Invalid password, password must be between 8-30 char, at least 1 upper, 1 lower and 1 number");

    }

    @Test
    public void shouldReturnTrueWhenEnteredValidPassword() throws Exception {
        //given
        final String password = "BtLondon5";
        //when
        boolean isEmailPassword = ValidationUtil.isValidPassword(password);
        //then
        assertTrue(isEmailPassword);
    }

    @Test
    public void userNameAndPasswordShouldNotBeSame() throws Exception {
        //given
        Login login = new Login();
        login.setUserName("johonson.k@bt.com");
        login.setPassword("johonson.k@bt.com");
        //when

        Throwable exceptionWithoutLowerCase = catchThrowable(() -> validateCredential(login));
        //then
        Assertions.assertThat(exceptionWithoutLowerCase).isInstanceOf(LoginException.class)
                .hasMessageContaining("User name and password should not be same.");

    }

    @Test
    public void shouldValidDateUserCredential() throws Exception {
        //given
        Login login = new Login();
        login.setUserName("johonson.k@bt.com");
        login.setPassword("BtLondon5");
        //when
        validateCredential(login);
        //then
    }
}