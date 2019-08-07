package com.yulece.app.management.commons.utils;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.yulece.app.management.commons.utils.enums.AppParamEnum;
import com.yulece.app.management.commons.utils.enums.ExceptionEnum;
import com.yulece.app.management.commons.utils.exception.AppException;
import com.yulece.app.management.commons.utils.exception.ParamException;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.ObjectUtils;

public class BeanValidator {

    private static ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();


    @SuppressWarnings("unchecked")
	public static <T> Map<String,String> validate(T t,Class<?>... grounds){
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<T>> validateResult = validator.validate(t, grounds);
        if(validateResult.isEmpty()){
            return Collections.EMPTY_MAP;
        }else {
            LinkedHashMap<String,String> errors = Maps.newLinkedHashMap();
            Iterator<ConstraintViolation<T>> iterator = validateResult.iterator();
            while (iterator.hasNext()){
                 ConstraintViolation<?> violation = iterator.next();
                errors.put(violation.getPropertyPath().toString(),violation.getMessage());
            }
            return errors;
        }
    }


    public static Map<String,String> validateList(Collection<?> collection){
        Map<String,String> error = Maps.newLinkedHashMap();
        Preconditions.checkNotNull(collection);
        collection.stream().map(e -> validate(e, new Class[0]))
                .collect(Collectors.toList()).forEach((e)->error.putAll(e));
        return error;

    }

    public static Map<String,String> validateObject(Object object,Object... groups){
        if(groups!= null && groups.length > 0){
            return validateList(Lists.asList(object,groups));
        }else {
            return validate(object,new Class[0]);
        }
    }

    public static void check(Object object,Object... groups) throws ParamException {
        Map<String, String> errors = validateObject(object, groups);
        if(MapUtils.isNotEmpty(errors)){
           throw  new ParamException(errors.toString());
        }
    }

    public static <T> T checkObjectNull(T target, AppParamEnum appParamEnum){
        if(!ObjectUtils.allNotNull(target)){
            throw new AppException(appParamEnum);
        }
        return target;
    }
    public static <T> T checkObjectNull(T target, ExceptionEnum exceptionEnum){
        if(!ObjectUtils.allNotNull(target)){
            throw new AppException(exceptionEnum);
        }
        return target;
    }
}
