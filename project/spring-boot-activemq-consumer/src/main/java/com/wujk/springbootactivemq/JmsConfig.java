package com.wujk.springbootactivemq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.transaction.PlatformTransactionManager;

import javax.jms.ConnectionFactory;

/**
 * Created by mavlarn on 2018/1/26.
 */
@EnableJms
@Configuration
public class JmsConfig {
    private static final Logger LOG = LoggerFactory.getLogger("asdasdasd");

    @Bean
    public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory) {
        LOG.debug("init jms template with converter.");
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(connectionFactory); // JmsTemplate使用的connectionFactory跟JmsTransactionManager使用的必须是同一个，不能在这里封装成caching之类的。
        return template;
    }

    // 这个用于设置 @JmsListener使用的containerFactory
    @Bean
    public JmsListenerContainerFactory<?> msgFactoryTopic(ConnectionFactory connectionFactory,
                                                     DefaultJmsListenerContainerFactoryConfigurer configurer,
                                                     PlatformTransactionManager transactionManager) {
        DefaultJmsListenerContainerFactory factory = getDefaultJmsListenerContainerFactory(connectionFactory, configurer, transactionManager);
        factory.setPubSubDomain(true);
        return factory;
    }

    // 这个用于设置 @JmsListener使用的containerFactory
    @Bean
    public JmsListenerContainerFactory<?> msgFactoryQueue(ConnectionFactory connectionFactory,
                                                          DefaultJmsListenerContainerFactoryConfigurer configurer,
                                                          PlatformTransactionManager transactionManager) {
        DefaultJmsListenerContainerFactory factory = getDefaultJmsListenerContainerFactory(connectionFactory, configurer, transactionManager);
        return factory;
    }

    private DefaultJmsListenerContainerFactory getDefaultJmsListenerContainerFactory(ConnectionFactory connectionFactory, DefaultJmsListenerContainerFactoryConfigurer configurer, PlatformTransactionManager transactionManager) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setTransactionManager(transactionManager);
        factory.setCacheLevelName("CACHE_CONNECTION");
        factory.setReceiveTimeout(10000L);
        configurer.configure(factory, connectionFactory);
        return factory;
    }

    @Bean
    public PlatformTransactionManager transactionManager(ConnectionFactory connectionFactory) {
        return new JmsTransactionManager(connectionFactory);
    }

}
