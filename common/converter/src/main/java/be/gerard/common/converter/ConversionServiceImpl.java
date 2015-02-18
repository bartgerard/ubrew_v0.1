package be.gerard.common.converter;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.core.convert.support.GenericConversionService;

/**
 * ConversionServiceFactoryBean
 *
 * @author bartgerard
 * @version 0.0.1
 */
public class ConversionServiceImpl extends ConversionServiceFactoryBean implements BeanPostProcessor {

    public GenericConversionService getConversionService() {
        return (GenericConversionService) getObject();
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object converter, String beanName) throws BeansException {
        if (converter instanceof GenericConverter) {
            getConversionService().addConverter((GenericConverter) converter);
        } else if (converter instanceof Converter<?, ?>) {
            getConversionService().addConverter((Converter<?, ?>) converter);
        } else if (converter instanceof ConverterFactory<?, ?>) {
            getConversionService().addConverterFactory((ConverterFactory<?, ?>) converter);
        }

        return converter;
    }

    @Override
    public void afterPropertiesSet() {
        super.afterPropertiesSet();

        // TODO
    }

//    public <T> T convert(Object object) {
//        if (object == null) {
//            return null;
//        }
//        
//        return (T) getObject().convert(object, getTargetType(object));
//    }
//    
//    private Class<?> getTargetType(Object object) {
//        Class<?> sourceClass = object.getClass();
//        Class<?> targetClass = null;
//        
//        Convertible encodable = sourceClass.getAnnotation(Convertible.class);
//        
//        if (encodable != null) {
//            targetClass = !Objects.equals(encodable.defaultTargetType(), Void.class) ? encodable.defaultTargetType() : null;
//        }
//        
//        Assert.notNull(targetClass, String.format("targetClass is not found for sourceClass [%s]", sourceClass.getName()));
//        
//        return targetClass;
//    }
    
}
