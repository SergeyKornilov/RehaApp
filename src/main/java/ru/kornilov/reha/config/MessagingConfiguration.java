package ru.kornilov.reha.config;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.connection.UserCredentialsConnectionFactoryAdapter;
import org.springframework.jms.core.JmsTemplate;
import ru.kornilov.reha.controllers.EventController;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Arrays;
import java.util.Properties;

import static org.apache.activemq.artemis.jms.client.DefaultConnectionProperties.DEFAULT_BROKER_URL;

@Configuration
public class MessagingConfiguration {
    private static final Logger logger = Logger.getLogger(MessagingConfiguration.class);


    private static final String CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
    private static final String DESTINATION = "jms/queue/test";
    private static final String MESSAGE_COUNT = "1";
    private static final String USERNAME = "guestslkornilov";
    private static final String PASSWORD = "SL-Kornilov";
    private static final String INITIAL_CONTEXT_FACTORY = "org.jboss.naming.remote.client.InitialContextFactory";
    private static final String PROVIDER_URL = "http-remoting://127.0.0.1:8081";

    @Lazy
    @Bean
    public ConnectionFactory connectionFactory() throws NamingException {

        Context namingContext = null;


                String userName = System.getProperty("username", USERNAME);
                String password = System.getProperty("password", PASSWORD);

                // Set up the namingContext for the JNDI lookup
                final Properties env = new Properties();
                env.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
                env.put(Context.PROVIDER_URL, System.getProperty(Context.PROVIDER_URL, PROVIDER_URL));
                env.put(Context.SECURITY_PRINCIPAL, userName);
                env.put(Context.SECURITY_CREDENTIALS, password);
                namingContext = new InitialContext(env);



                // Perform the JNDI lookups
                String connectionFactoryString = System.getProperty("connection.factory", CONNECTION_FACTORY);
//                log.info("Attempting to acquire connection factory \"" + connectionFactoryString + "\"");
                ConnectionFactory connectionFactory = (ConnectionFactory) namingContext.lookup(connectionFactoryString);

                return connectionFactory;


    }

    @Lazy
    @Bean
    public JmsTemplate jmsTemplate() throws NamingException {


        Context namingContext = null;

            String userName = System.getProperty("username", USERNAME);
            String password = System.getProperty("password", PASSWORD);

            // Set up the namingContext for the JNDI lookup
            final Properties env = new Properties();
            env.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
            env.put(Context.PROVIDER_URL, System.getProperty(Context.PROVIDER_URL, PROVIDER_URL));
            env.put(Context.SECURITY_PRINCIPAL, userName);
            env.put(Context.SECURITY_CREDENTIALS, password);
            namingContext = new InitialContext(env);


            String destinationString = System.getProperty("destination", DESTINATION);
    //                log.info("Attempting to acquire destination \"" + destinationString + "\"");
            Destination destination = (Destination) namingContext.lookup(destinationString);

            ConnectionFactory connectionFactory = connectionFactory();

        UserCredentialsConnectionFactoryAdapter cfCredentialsAdapter = new UserCredentialsConnectionFactoryAdapter();
        cfCredentialsAdapter.setTargetConnectionFactory(connectionFactory);
        cfCredentialsAdapter.setUsername(USERNAME);
        cfCredentialsAdapter.setPassword(PASSWORD);

//        this.cachingConnectionFactory = new CachingConnectionFactory(cfCredentialsAdapter);


        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(cfCredentialsAdapter);
        template.setDefaultDestination(destination);

        return template;


    }

}