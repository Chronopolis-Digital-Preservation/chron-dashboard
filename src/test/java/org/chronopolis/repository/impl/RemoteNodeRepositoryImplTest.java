package org.chronopolis.repository.impl;

import org.chronopolis.remote.node.RemoteNode;
import org.chronopolis.repository.IdentifierNotFoundException;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RemoteNodeRepositoryImplTest {

    private static final String PRODUCTION_ACE_NODE_NAME = "Production ACE Node";
    private static final String TEST_ACE_NODE_NAME = "Test ACE Node";
    private static final String DPN_ACE_NODE_NAME = "DPN ACE Node";
    private static final String PRODUCTION_INGEST_NODE_NAME = "Production Ingest Node";
    private static final String TEST_INGEST_NODE_NAME = "Test Ingest Node";
    private static final String PRODUCTION_ENVIRONMENT = "production";
    private static final String TEST_ENVIRONMENT = "test";
    private static final String DPN_ENVIRONMENT = "dpn";

    private RemoteNodeRepositoryImpl remoteNodeRepository;

    private RemoteNode mockProductionAceRemoteNode;
    private RemoteNode mockProductionIngestRemoteNode;
    private RemoteNode mockTestAceRemoteNode;
    private RemoteNode mockTestIngestRemoteNode;
    private RemoteNode mockDpnRemoteNode;

    @Before
    public void setup() {

        mockProductionAceRemoteNode = createStubRemoteNode(PRODUCTION_ACE_NODE_NAME, PRODUCTION_ENVIRONMENT, false);
        mockTestAceRemoteNode = createStubRemoteNode(TEST_ACE_NODE_NAME, TEST_ENVIRONMENT, false);
        mockDpnRemoteNode = createStubRemoteNode(DPN_ACE_NODE_NAME, DPN_ENVIRONMENT, false);
        mockProductionIngestRemoteNode = createStubRemoteNode(PRODUCTION_INGEST_NODE_NAME, PRODUCTION_ENVIRONMENT, true);
        mockTestIngestRemoteNode = createStubRemoteNode(TEST_INGEST_NODE_NAME, TEST_ENVIRONMENT, true);

        HashMap<String, RemoteNode> remoteNodeHashMap = new HashMap<>();
        remoteNodeHashMap.put(PRODUCTION_ACE_NODE_NAME, mockProductionAceRemoteNode);
        remoteNodeHashMap.put(TEST_ACE_NODE_NAME, mockTestAceRemoteNode);
        remoteNodeHashMap.put(DPN_ACE_NODE_NAME, mockDpnRemoteNode);
        remoteNodeHashMap.put(PRODUCTION_INGEST_NODE_NAME, mockProductionIngestRemoteNode);
        remoteNodeHashMap.put(TEST_INGEST_NODE_NAME, mockTestIngestRemoteNode);

        remoteNodeRepository = new RemoteNodeRepositoryImpl(remoteNodeHashMap);
    }

    private RemoteNode createStubRemoteNode(String name, String environment, boolean ingest) {

        RemoteNode mockRemoteNode = mock(RemoteNode.class);
        when(mockRemoteNode.getNodeName()).thenReturn(name);
        when(mockRemoteNode.getEnvironment()).thenReturn(environment);
        when(mockRemoteNode.isIngestNode()).thenReturn(ingest);

        return mockRemoteNode;
    }

    @Test
    public void give_remote_node_name__when_getByName__then_node_returned() {

        RemoteNode returnedNode = remoteNodeRepository.getByName(TEST_ACE_NODE_NAME);
        assertThat(returnedNode.getNodeName(), is(TEST_ACE_NODE_NAME));
    }

    @Test(expected = IdentifierNotFoundException.class)
    public void give_unknown_remote_node_name__when_getByName__then_exception() throws IdentifierNotFoundException {

        String badNodeName = "BAD_NODE_NAME";
        remoteNodeRepository.getByName(badNodeName);
    }

    @Test
    public void given_production_environment__when_getNodesByEnvironment__then_production_nodes_returned() {

        List<RemoteNode> nodes = this.remoteNodeRepository.getNodesByEnvironment(PRODUCTION_ENVIRONMENT);

        assertThat(nodes.size(), is(2));
        assertThat(nodes, containsInAnyOrder(mockProductionAceRemoteNode, mockProductionIngestRemoteNode));
    }

    @Test
    public void given_test_environment__when_getIngestNodesByEnvironment__then_test_node_returned() {

        Collection<RemoteNode> nodes = this.remoteNodeRepository.getIngestNodesByEnvironment(TEST_ENVIRONMENT);

        assertThat(nodes.size(), is(1));
        assertThat(nodes, containsInAnyOrder(mockTestIngestRemoteNode));
    }
}
