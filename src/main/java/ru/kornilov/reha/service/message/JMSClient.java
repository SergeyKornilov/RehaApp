//package ru.kornilov.reha.service.message;
//
//
//import javax.jms.ConnectionFactory;
//import javax.jms.Destination;
//import javax.jms.JMSContext;
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import java.util.Properties;
//
//public class JMSClient {
//
//
//        private static final String CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
//        private static final String DESTINATION = "jms/queue/test";
//        private static final String MESSAGE_COUNT = "1";
//        private static final String USERNAME = "guestslkornilov";
//        private static final String PASSWORD = "SL-Kornilov";
//        private static final String INITIAL_CONTEXT_FACTORY = "org.jboss.naming.remote.client.InitialContextFactory";
//        private static final String PROVIDER_URL = "http-remoting://127.0.0.1:8081";
//
//        public static void confAndSendMessage(String message) {
//
//            Context namingContext = null;
//
//            try {
//                String userName = System.getProperty("username", USERNAME);
//                String password = System.getProperty("password", PASSWORD);
//
//                // Set up the namingContext for the JNDI lookup
//                final Properties env = new Properties();
//                env.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
//                env.put(Context.PROVIDER_URL, System.getProperty(Context.PROVIDER_URL, PROVIDER_URL));
//                env.put(Context.SECURITY_PRINCIPAL, userName);
//                env.put(Context.SECURITY_CREDENTIALS, password);
//                namingContext = new InitialContext(env);
//
//                // Perform the JNDI lookups
//                String connectionFactoryString = System.getProperty("connection.factory", CONNECTION_FACTORY);
////                log.info("Attempting to acquire connection factory \"" + connectionFactoryString + "\"");
//                ConnectionFactory connectionFactory = (ConnectionFactory) namingContext.lookup(connectionFactoryString);
////                log.info("Found connection factory \"" + connectionFactoryString + "\" in JNDI");
//
//                String destinationString = System.getProperty("destination", DESTINATION);
////                log.info("Attempting to acquire destination \"" + destinationString + "\"");
//                Destination destination = (Destination) namingContext.lookup(destinationString);
////                log.info("Found destination \"" + destinationString + "\" in JNDI");
//
//                int count = Integer.parseInt(System.getProperty("message.count", MESSAGE_COUNT));
//
//
//                try (JMSContext context = connectionFactory.createContext(userName, password)) {
////                    log.info("Sending " + count + " messages with content: " + content);
//                    // Send the specified number of messages
//                    for (int i = 0; i < count; i++) {
//                        context.createProducer().send(destination, message);
//                    }
//                }
//            } catch (Exception e) {
////                log.severe(e.getMessage());
//                System.out.println(e.toString());
//            } finally {
//                if (namingContext != null) {
//                    try {
//                        namingContext.close();
//                    } catch (NamingException e) {
//                        System.out.println(e.toString());
////                        log.severe(e.getMessage());
//                    }
//                }
//            }
//        }
//
//}