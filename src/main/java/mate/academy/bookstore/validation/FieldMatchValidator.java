package mate.academy.bookstore.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {
    private String firstFieldName;
    private String secondFieldName;

    @Override
    public void initialize(FieldMatch constraintAnnotation) {
        firstFieldName = constraintAnnotation.firstField();
        secondFieldName = constraintAnnotation.secondField();
    }

    @Override
    public boolean isValid(Object target, ConstraintValidatorContext context) {
        try {
            Field firstField = target.getClass().getDeclaredField(firstFieldName);
            Field secondField = target.getClass().getDeclaredField(secondFieldName);
            firstField.setAccessible(true);
            secondField.setAccessible(true);
            Object firstValue = firstField.get(target);
            Object secondValue = secondField.get(target);
            if (firstValue == null || secondValue == null) {
                return false;
            }
            return firstValue.equals(secondValue);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Fields: \""
                    + firstFieldName
                    + "\" \""
                    + secondFieldName
                    + "\" cannot be compared", e);
        }
    }
}
