package com.issue.vk.custom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

@Component
public class ArgumentExtractorBeanPostProcessor implements BeanPostProcessor {

    Logger log = LoggerFactory.getLogger(ArgumentExtractorBeanPostProcessor.class);

    @Autowired
    private ApplicationArguments applicationArguments;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        Field[] fields = beanClass.getDeclaredFields();
        for(Field field : fields) {
            field.setAccessible(true);
            InjectFirstArg injectFirstArg = field.getAnnotation(InjectFirstArg.class);
            if(injectFirstArg != null) {
                String pathToData;
                try {
                    pathToData = applicationArguments.getSourceArgs()[0];
                } catch (IndexOutOfBoundsException e) {
                    log.warn("The first argument indicating to data is not present. Default datafile will be used instead.");
                    pathToData = "src/main/resources/data.json";
                }
                ReflectionUtils.setField(field, bean, pathToData);
            }
        }
        return bean;
    }
}
