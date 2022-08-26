package org.chronopolis.remote.node.impl;

import org.chronopolis.model.*;
import org.chronopolis.remote.node.impl.jackson.ace.status.AceSummaryStatus;
import org.chronopolis.remote.node.impl.jackson.ace.status.Collection;
import org.junit.Test;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class DefaultRemoteNodeTest {

    public static final String NODE_NAME = "TEST NODE";
    public static final String ACE_URI = "http://ace.test.com/";

    private DefaultRemoteNode createDefaultRemoteNode(RestTemplate stubRestTemplate) {
        return new DefaultRemoteNode(NODE_NAME, "production", ACE_URI, stubRestTemplate);
    }

    @Test
    public void testNodeName() {

        DefaultRemoteNode remoteNode = this.createDefaultRemoteNode(null);

        assertThat(remoteNode.getNodeName(), is(NODE_NAME));
    }

    @Test
    public void testEnvironment() {

        DefaultRemoteNode remoteNode = this.createDefaultRemoteNode(null);

        assertThat(remoteNode.getEnvironment(), is("production"));
    }

    @Test
    public void testIsIngestNode() {

        DefaultRemoteNode remoteNode = this.createDefaultRemoteNode(null);

        assertThat(remoteNode.isIngestNode(), is(false));
    }

    @Test
    public void testAceIsRunningAndReturnsTrue() {

        RestTemplate stubRestTemplate = new RestTemplate() {
            public <T> T getForObject(String url, Class<T> responseType, Object... urlVariables) throws RestClientException {

                return responseType.cast("Simple String");
            }
        };

        DefaultRemoteNode remoteNode = createDefaultRemoteNode(stubRestTemplate);

        NodeOnlineStatus aceOnlineStatus = remoteNode.getNodeOnlineStatus();
        assertThat(aceOnlineStatus.getNode(), is(NODE_NAME));
        assertThat(aceOnlineStatus.getAceStatus(), is("true"));
    }

    private RestTemplate createRestClientExceptionRestTemplate(final String exceptionMessage) {

        return new RestTemplate() {
            public <T> T getForObject(String url, Class<T> responseType, Object... urlVariables) {

                throw new RestClientException(exceptionMessage);
            }
        };
    }

    @Test
    public void testExceptionReturnsFalse() {

        RestTemplate stubRestTemplate = this.createRestClientExceptionRestTemplate("Runtime Error Message");

        DefaultRemoteNode remoteNode = this.createDefaultRemoteNode(stubRestTemplate);

        NodeOnlineStatus aceOnlineStatus = remoteNode.getNodeOnlineStatus();
        assertThat(aceOnlineStatus.getAceStatus(), is("false"));
        assertThat(aceOnlineStatus.getMessage(), is("Runtime Error Message"));
    }

    @Test
    public void testAceNodeJsonConvertedToBagSummary() {

        RestTemplate stubRestTemplate = new RestTemplate() {
            public <T> T getForObject(String url, Class<T> responseType, Object... urlVariables) {

                return responseType.cast(createAceSummaryStatus());
            }
        };

        DefaultRemoteNode remoteNode = createDefaultRemoteNode(stubRestTemplate);

        AceBagSummary aceBagSummary = remoteNode.getAceSummaryStatus();

        assertThat(aceBagSummary.getNode(), is(NODE_NAME));
        assertThat(aceBagSummary.getBags(), is("3"));
        assertThat(aceBagSummary.getFiles(), is("11100"));
        assertThat(aceBagSummary.getBytes(), is("22200"));
    }

    private AceSummaryStatus createAceSummaryStatus() {

        AceSummaryStatus aceSummaryStatus = new AceSummaryStatus();
        aceSummaryStatus.setCollections(this.createCollections());

        return aceSummaryStatus;
    }

    private List<Collection> createCollections() {

        List<org.chronopolis.remote.node.impl.jackson.ace.status.Collection> collectionList = new ArrayList<>();

        collectionList.add(this.createCollection(100L, 200L, "A"));
        collectionList.add(this.createCollection(1000L, 2000L, "A"));
        collectionList.add(this.createCollection(10000L, 20000L, "A"));
        collectionList.add(this.createCollection(10L, 20L, "R"));

        return collectionList;
    }

    private org.chronopolis.remote.node.impl.jackson.ace.status.Collection createCollection(long totalFiles, long totalSize, String state) {

        org.chronopolis.remote.node.impl.jackson.ace.status.Collection collection = new org.chronopolis.remote.node.impl.jackson.ace.status.Collection();

        collection.setTotalFiles(totalFiles);
        collection.setTotalSize(totalSize);
        collection.setState(state);

        return collection;
    }

    @Test
    public void testExceptionInBagSummaryReturnsExceptionMessage() {

        RestTemplate stubRestTemplate = this.createRestClientExceptionRestTemplate("Runtime Error Message");

        DefaultRemoteNode remoteNode = createDefaultRemoteNode(stubRestTemplate);

        AceBagSummary aceBagSummary = remoteNode.getAceSummaryStatus();

        assertThat(aceBagSummary.getNode(), is(NODE_NAME));
        assertThat(aceBagSummary.getMessage(), is("Runtime Error Message"));
    }

    @Test
    public void testAceNodeJsonConvertedToAceDeposiitorSummary() {

        RestTemplate stubRestTemplate = new RestTemplate() {
            public <T> T getForObject(String url, Class<T> responseType, Object... urlVariables) {

                return responseType.cast(createDepositorAceSummaryStatus());
            }
        };

        DefaultRemoteNode remoteNode = createDefaultRemoteNode(stubRestTemplate);

        AceDepositorSummary aceDepositorSummary = remoteNode.getAceDepositorSummaryStatus();

        assertThat(aceDepositorSummary.getNode(), is(NODE_NAME));

        DepositorSummary acadisDepositorSummary = aceDepositorSummary.getDepositors().get(0);

        assertThat(acadisDepositorSummary.getName(), is("ACADIS"));
        assertThat(acadisDepositorSummary.getBags(), is(2L));
        assertThat(acadisDepositorSummary.getFiles(), is(1100L));
        assertThat(acadisDepositorSummary.getBytes(), is(2200L));

        DepositorSummary ucsdDepositorSummary = aceDepositorSummary.getDepositors().get(1);

        assertThat(ucsdDepositorSummary.getName(), is("UCSD"));
        assertThat(ucsdDepositorSummary.getBags(), is(1L));
        assertThat(ucsdDepositorSummary.getFiles(), is(10000L));
        assertThat(ucsdDepositorSummary.getBytes(), is(20000L));
    }

    private AceSummaryStatus createDepositorAceSummaryStatus() {

        AceSummaryStatus aceSummaryStatus = new AceSummaryStatus();
        aceSummaryStatus.setCollections(this.createDepositorCollections());

        return aceSummaryStatus;
    }

    private List<org.chronopolis.remote.node.impl.jackson.ace.status.Collection> createDepositorCollections() {

        List<org.chronopolis.remote.node.impl.jackson.ace.status.Collection> collectionList = new ArrayList<>();

        collectionList.add(this.createDepositorCollection("ACADIS", "BAG 1", 100L, 200L, "A"));
        collectionList.add(this.createDepositorCollection("ACADIS", "BAG 2", 1000L, 2000L, "A"));
        collectionList.add(this.createDepositorCollection("UCSD", "BAG 3", 10000L, 20000L, "A"));
        collectionList.add(this.createDepositorCollection("UCSD", "BAG 4", 10000L, 20000L, "R"));

        return collectionList;
    }

    private org.chronopolis.remote.node.impl.jackson.ace.status.Collection createDepositorCollection(String depositor, String bagName, long totalFiles, long totalSize, String state) {

        org.chronopolis.remote.node.impl.jackson.ace.status.Collection collection = new org.chronopolis.remote.node.impl.jackson.ace.status.Collection();

        collection.setGroup(depositor);
        collection.setName(bagName);
        collection.setTotalFiles(totalFiles);
        collection.setTotalSize(totalSize);
        collection.setState(state);

        return collection;
    }

    @Test
    public void testExceptionInAceDepositorSummaryStatusReturnsExceptionMessage() {

        RestTemplate stubRestTemplate = this.createRestClientExceptionRestTemplate("Runtime Error Message");

        DefaultRemoteNode remoteNode = createDefaultRemoteNode(stubRestTemplate);

        AceDepositorSummary aceDepositorSummary = remoteNode.getAceDepositorSummaryStatus();

        assertThat(aceDepositorSummary.getNode(), is(NODE_NAME));
        assertThat(aceDepositorSummary.getMessage(), is("Runtime Error Message"));
    }

    @Test
    public void testAceNodeJsonConvertedToAceDeposiitorBagSummary() {

        RestTemplate stubRestTemplate = new RestTemplate() {
            public <T> T getForObject(String url, Class<T> responseType, Object... urlVariables) {

                return responseType.cast(createDepositorAceSummaryStatus());
            }
        };

        DefaultRemoteNode remoteNode = createDefaultRemoteNode(stubRestTemplate);

        AceDepositorBagSummary aceDepositorBagSummary = remoteNode.getAceDepositorBagSummaryStatus("ACADIS");

        assertThat(aceDepositorBagSummary.getNode(), is(NODE_NAME));
        assertThat(aceDepositorBagSummary.getDepositor(), is("ACADIS"));

        DepositorBagSummary depositorBagSummary0 = aceDepositorBagSummary.getBagSummaries().get(0);

        assertThat(depositorBagSummary0.getBagName(), is("BAG 1"));
        assertThat(depositorBagSummary0.getFiles(), is(100L));
        assertThat(depositorBagSummary0.getBytes(), is(200L));

        DepositorBagSummary depositorBagSummary1 = aceDepositorBagSummary.getBagSummaries().get(1);

        assertThat(depositorBagSummary1.getBagName(), is("BAG 2"));
        assertThat(depositorBagSummary1.getFiles(), is(1000L));
        assertThat(depositorBagSummary1.getBytes(), is(2000L));
    }

    @Test
    public void testExceptionInAceDepositorBagSummaryStatusReturnsExceptionMessage() {

        RestTemplate stubRestTemplate = this.createRestClientExceptionRestTemplate("Runtime Error Message");

        DefaultRemoteNode remoteNode = createDefaultRemoteNode(stubRestTemplate);

        AceDepositorBagSummary aceDepositorBagSummaryStatus = remoteNode.getAceDepositorBagSummaryStatus("ACADIS");

        assertThat(aceDepositorBagSummaryStatus.getNode(), is(NODE_NAME));
        assertThat(aceDepositorBagSummaryStatus.getDepositor(), is("ACADIS"));
        assertThat(aceDepositorBagSummaryStatus.getMessage(), is("Runtime Error Message"));
    }
}
