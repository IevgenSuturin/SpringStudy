package lab2.practice;


import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

/**
 * Класс должен содержать логику подмены значений филдов заданых по умолчанию в контексте.
 * Заменяет строковые значение в бинах типа
 *
 * @see Printer
 * на значения в
 * @see PropertyRepository
 * Использует изначальные значения как ключи для поиска в PropertyRepository
 */

@Service
public class PropertyPlaceholder implements BeanFactoryPostProcessor {
    //private PropertyRepository propertyRepository;

    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        for (String beanName: beanFactory.getBeanDefinitionNames()) {
            try {
                BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
                for(PropertyValue propertyValue: beanDefinition.getPropertyValues().getPropertyValueList()) {
                    String name = propertyValue.getName();
                    if(PropertyRepository.get(propertyValue.getName()) != null){
                        propertyValue.setConvertedValue(PropertyRepository.get(name));
                    }
                }
              //  beanDefinition.getPropertyValues().getPropertyValue()
            }catch(Exception e){
                throw new RuntimeException(e.getMessage());
            }
        }
    }
}
