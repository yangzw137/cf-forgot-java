package org.geekbang.thinking.in.spring.validation;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.context.MessageSource;
import org.springframework.context.support.StaticMessageSource;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.Locale;

/**
 * Description: todo
 * <p>
 * company: <a href=www.jd.com>www.jd.com</a>
 *
 * @author <a href=mailto:yangzhiwei@jd.com>cf</a>
 * @date 2021/6/5
 * @since todo
 *
 * @see Errors
 */
public class ErrorMessageDemo {

    public static void main(String[] args) {
        User user = new User();

        Errors errors = new BeanPropertyBindingResult(user, "user");

        errors.reject("user.properties.not.null");
        errors.rejectValue("name", "name.required");

        errors.getGlobalErrors();
        errors.getFieldErrors();
        List<ObjectError> allErr = errors.getAllErrors();

        MessageSource messageSource = createMessageSource();
        allErr.stream().forEach(objectError -> {
            String msg = messageSource.getMessage(objectError.getCode(), objectError.getArguments(),
                    Locale.getDefault());
            System.out.println(msg);
        });

    }

    static MessageSource createMessageSource() {
        StaticMessageSource messageSource = new StaticMessageSource();
        messageSource.addMessage("user.properties.not.null", Locale.getDefault(), "User 所有属性不能为空.");
        messageSource.addMessage("id.required", Locale.getDefault(), "User id must be not null.");
        messageSource.addMessage("name.required", Locale.getDefault(), "User name must be not null.");
        return messageSource;
    }

}
