package be.gerard.common.converter;

import be.gerard.common.converter.annotation.Convertible;
import java.beans.PropertyDescriptor;
import java.util.Collections;
import java.util.Set;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;
import org.springframework.stereotype.Component;

/**
 * GenericPropertyConverter
 *
 * @author bartgerard
 * @version 0.0.1
 */
@Component
public class GenericPropertyConverter implements ConditionalGenericConverter {

    @Autowired
    private ConversionService conversionService;

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new ConvertiblePair(Object.class, Object.class));
    }

    @Override
    public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
        return sourceType.getType().getAnnotation(Convertible.class) != null;
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        BeanWrapper wrappedSource = new BeanWrapperImpl(source);
        wrappedSource.setConversionService(conversionService);
        
        BeanWrapper wrappedTarget = new BeanWrapperImpl(targetType.getObjectType());
        wrappedTarget.setConversionService(conversionService);

        for (PropertyDescriptor propertyDescriptor : wrappedSource.getPropertyDescriptors()) {
            if (canConvert(propertyDescriptor, wrappedSource, wrappedTarget)) {
                wrappedTarget.setPropertyValue(propertyDescriptor.getName(), wrappedSource.getPropertyValue(propertyDescriptor.getName()));
            }
        }

        return wrappedTarget.getWrappedInstance();
    }

    private boolean canConvert(PropertyDescriptor propertyDescriptor, BeanWrapper wrappedSource, BeanWrapper wrappedTarget) {
        return wrappedSource.isReadableProperty(propertyDescriptor.getName()) && wrappedTarget.isWritableProperty(propertyDescriptor.getName()) && conversionService.canConvert(wrappedSource.getPropertyType(propertyDescriptor.getName()), wrappedTarget.getPropertyType(propertyDescriptor.getName()));
    }

}
