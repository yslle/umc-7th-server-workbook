package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.repository.store.StoreRepository;
import umc.spring.service.store.StoreQueryService;
import umc.spring.validation.annotation.ExistStores;

@Component
@RequiredArgsConstructor
public class StoresExistValidator implements ConstraintValidator<ExistStores, Long> {

    private final StoreQueryService storeQueryService;

    @Override
    public void initialize(ExistStores constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        boolean isValid = storeQueryService.checkIfStoreExist(value);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.STORE_NOT_FOUND.toString()).addConstraintViolation();
        }
        return isValid;
    }
}