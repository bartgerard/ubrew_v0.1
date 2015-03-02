package be.gerard.core.service.validation;

import be.gerard.core.service.BaseTest;
import be.gerard.core.interface_v1.model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;

/**
 * ApplicationValidatorTest
 *
 * @author bartgerard
 * @version 0.0.1
 * @since 2015-01-11 11:32
 */
public class ApplicationValidatorTest extends BaseTest {

    @Autowired
    private ApplicationValidator applicationValidator;

//    @Test
//    public void test() {
//        Errors errors = new org.springframework.validation.AbstractErrors() {
//            @Override
//            public String getObjectName() {
//                return null;
//            }
//
//            @Override
//            public void reject(String errorCode, Object[] errorArgs, String defaultMessage) {
//
//            }
//
//            @Override
//            public void rejectValue(String field, String errorCode, Object[] errorArgs, String defaultMessage) {
//
//            }
//
//            @Override
//            public void addAllErrors(Errors errors) {
//
//            }
//
//            @Override
//            public List<ObjectError> getGlobalErrors() {
//                return null;
//            }
//
//            @Override
//            public List<FieldError> getFieldErrors() {
//                return null;
//            }
//
//            @Override
//            public Object getFieldValue(String field) {
//                return null;
//            }
//        };
//        Application application = new Application(null);
//        applicationValidator.validate(application, errors);
//
//        Assert.notNull(errors);
//    }

    @Test
    public void test2() {
        BeanPropertyBindingResult errors = new BeanPropertyBindingResult(new User(), "user");
        errors.addError(new ObjectError("foo","message1"));
        errors.addError(new ObjectError("foo", "message2"));
        ValidationUtils.rejectIfEmpty(errors, "username", "error.username.null");
        for (FieldError fieldError : errors.getFieldErrors()) {
            System.out.println(fieldError.getRejectedValue() + " " + fieldError.getField());
        }
//        ValidationException originalException=new ValidationException(errors);
//        Error error=new Error(originalException);
//        Throwable exception=error.getException();
//        assertSame(originalException.getClass(),exception.getClass());
//        assertEquals(originalException.getMessage(),exception.getMessage());
    }

}
