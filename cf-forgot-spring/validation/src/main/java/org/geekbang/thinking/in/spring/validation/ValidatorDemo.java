package org.geekbang.thinking.in.spring.validation;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.context.MessageSource;
import org.springframework.validation.*;

import java.util.List;
import java.util.Locale;

import static org.geekbang.thinking.in.spring.validation.ErrorMessageDemo.createMessageSource;

/**
 * Description: todo
 * <p>
 * company: <a href=www.jd.com>www.jd.com</a>
 *
 * @author <a href=mailto:yangzhiwei@jd.com>cf</a>
 * @date 2021/6/5
 * @since todo
 */
public class ValidatorDemo {

    public static void main(String[] args) {
        User user = new User();
//        user.setName("cf");

        UserValidator userValidator = new UserValidator();
        System.out.printf("UserValidator supports: %s\n", userValidator.supports(User.class));

        Errors errors = new BeanPropertyBindingResult(user, "user");
        MessageSource messageSource = createMessageSource();

        userValidator.validate(user, errors);
        List<ObjectError> allErr = errors.getAllErrors();
        for (ObjectError err : allErr) {
            String msg = messageSource.getMessage(err.getCode(), err.getArguments(), Locale.getDefault());
            System.out.println(msg);
        }
    }

    static class UserValidator implements Validator {

        @Override
        public boolean supports(Class<?> clazz) {
            return User.class.isAssignableFrom(clazz);
        }

        @Override
        public void validate(Object target, Errors errors) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "id.required");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required");
        }
    }
}
