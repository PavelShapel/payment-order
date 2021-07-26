//package com.pavelshapel.common.module.service.jpa.decorator;
//
//import com.pavelshapel.common.module.service.jpa.JpaService;
//import org.springframework.aop.support.AopUtils;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.config.BeanPostProcessor;
//import org.springframework.context.ApplicationContext;
//import org.springframework.stereotype.Component;
//
//import java.util.Objects;
//import java.util.Optional;
//
//@Component
//public class JpaServiceBeanPostProcessor implements BeanPostProcessor {
//    @Autowired
//    private ApplicationContext context;
//
//    @Override
//    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
//        return bean;
//    }
//
//    @Override
//    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//        if (bean instanceof JpaService && !(bean instanceof AbstractDecoratorJpaService)) {
//            bean = getDecoratedBean(bean);
//        }
//
//        return bean;
//    }
//
//    private Object getDecoratedBean(Object wrapped) {
//        JpaDecorate annotation = getAnnotation(wrapped);
//
//        if (Objects.isNull(annotation)) {
//            return wrapped;
//        }
//
//        return iterateDecorationsInAnnotation(wrapped, annotation);
//    }
//
//    private JpaDecorate getAnnotation(Object wrapped) {
//        Class<?> targetClass = AopUtils.getTargetClass(wrapped);
//        return targetClass.getAnnotation(JpaDecorate.class);
//    }
//
//    private Object iterateDecorationsInAnnotation(Object wrapped, JpaDecorate annotation) {
//        for (Class<? extends JpaService<?>> decorationClass : annotation.decorations()) {
//            Optional<JpaService<?>> decoratingBean = getDecoratingBean(decorationClass);
//
//            if (decoratingBean.isPresent()) {
//                wrapped = getWrappedWithDecoration(decoratingBean.get(), wrapped);
//            }
//        }
//        return wrapped;
//    }
//
//    private Optional<JpaService<?>> getDecoratingBean(Class<?> decorationClass) {
//        return Optional.of((JpaService<?>) context.getBean(decorationClass));
//    }
//
//    private JpaService<?> getWrappedWithDecoration(JpaService<?> decoratingBean, Object wrapped) {
//        AbstractDecoratorJpaService<?> decorator = (AbstractDecoratorJpaService<?>) decoratingBean;
//        decorator.setWrapped((JpaService) wrapped);
//
//        return decorator;
//    }
//}