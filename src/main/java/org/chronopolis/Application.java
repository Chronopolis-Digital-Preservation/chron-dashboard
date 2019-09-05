package org.chronopolis;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.chronopolis.configuration.Node;
import org.chronopolis.configuration.NodeConfiguration;
import org.chronopolis.remote.node.RemoteNode;
import org.chronopolis.remote.node.impl.DefaultRemoteNode;
import org.chronopolis.remote.node.impl.RemoteIngestNode;
import org.chronopolis.repository.RemoteNodeRepository;
import org.chronopolis.repository.impl.RemoteNodeRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.PreEmptiveAuthHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Configuration
@ComponentScan
@EnableAutoConfiguration
// We could also just use @SpringBootApplication, it is the same as @Configuration @EnableAutoConfiguration @ComponentScan
public class Application extends SpringBootServletInitializer {

    /*
    ** Get values from YAML files
     */
    @Autowired
    private NodeConfiguration prodConfig;

    /*
        Used for traditional deployment into a Servlet 3.0 container.
        SpringBootServletInitializer is a WebApplicationInitializer which is detected automatically by
        SpringServletContainerInitializer which itself is bootstrapped automatically by any Servlet 3.0 container
        Servlet 3.0 ServletContainerInitializer is designed to support code-based configuration as opposed to
        the traditional web.xml based approach.
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {

        return application.sources(Application.class);
    }

    @Bean
    public RemoteNodeRepository getRemoteNodeRepository() {

        return new RemoteNodeRepositoryImpl(this.getRemoteNodeMap());
    }

    @Bean
    public Map<String, RemoteNode> getRemoteNodeMap() {

        Map<String, RemoteNode> remoteNodeMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

        List<Node> nodes = this.getNodes();

        for (Node node : nodes) {

            RemoteNode remoteNode;

            remoteNode = new DefaultRemoteNode(node.getName(), node.getEnvironment(), node.getAceendpoint(), getRestTemplate());
            remoteNodeMap.put(node.getName(), remoteNode);

            if (node.isIngestNode()) {

                // UCSD is both ACE and INGEST node so goes in for both roles
                remoteNode = new RemoteIngestNode(node.getName(), node.getEnvironment(), node.getAceendpoint(), getRestTemplate(), node.getIngestendpoint(), node.getIngesthost(), node.getIngestport());
                remoteNodeMap.put(node.getName(), remoteNode);
            }
        }

        return remoteNodeMap;
    }

    @Bean
    public List<Node> getNodes() {

        return prodConfig.getNodes();
    }

    @Bean
    public RestTemplate getRestTemplate() {

        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new PreEmptiveAuthHttpRequestFactory(this.getHttpClient(), getPreemptiveAuthCache());

        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);

        MappingJackson2HttpMessageConverter myJacksonMessageConverter = getCustomMappingJackson2HttpMessageConverter();

        List<HttpMessageConverter<?>> restTemplateMessageConverters = restTemplate.getMessageConverters();
        restTemplateMessageConverters.add(myJacksonMessageConverter);

        restTemplate.setMessageConverters(restTemplateMessageConverters);

        return restTemplate;
    }

    /* Create new type of MessageConverter to support return type of text/plain UTF-8, which is what we get from the API */
    private MappingJackson2HttpMessageConverter getCustomMappingJackson2HttpMessageConverter() {

        MappingJackson2HttpMessageConverter myJacksonMessageConverter = new MappingJackson2HttpMessageConverter();

        List<MediaType> mediaTypes = new ArrayList<>();

        mediaTypes.add(new MediaType("text", "plain", Charset.forName("UTF-8")));

        myJacksonMessageConverter.setSupportedMediaTypes(mediaTypes);

        return myJacksonMessageConverter;
    }

    @Bean
    public HttpClient getHttpClient() {

        return HttpClients.custom()
                .setConnectionManager(new PoolingHttpClientConnectionManager())
                .setDefaultCredentialsProvider(this.getCredentialsProvider())
                .setDefaultRequestConfig(this.getRequestConfig())
                .build();
    }

    public CredentialsProvider getCredentialsProvider() {

        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();

        for (Node node : this.getNodes()) {

            credentialsProvider.setCredentials(new AuthScope(node.getAcehost(), node.getAceport()), new UsernamePasswordCredentials(node.getAceusername(), node.getAcepassword()));

            // UCSD is both ACE and INGEST node
            if (node.isIngestNode()) {

                credentialsProvider.setCredentials(new AuthScope(node.getIngesthost(), node.getIngestport()), new UsernamePasswordCredentials(node.getIngestusername(), node.getIngestpassword()));
            }
        }

        return credentialsProvider;
    }

    public AuthCache getPreemptiveAuthCache() {

        AuthCache authCache = new BasicAuthCache();

        this.getNodes().forEach(node -> {

            if (node.isIngestNode()) {

                HttpHost targetHost = new HttpHost(node.getIngesthost(), node.getIngestport(), "https");

                authCache.put(targetHost, new BasicScheme());
            }
        });

        return authCache;
    }

    public RequestConfig getRequestConfig() {

        return RequestConfig.custom()
                .setConnectionRequestTimeout(50000)
                .setSocketTimeout(50000)
                .build();
    }
}
