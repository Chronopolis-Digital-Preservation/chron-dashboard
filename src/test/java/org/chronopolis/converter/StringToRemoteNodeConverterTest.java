package org.chronopolis.converter;

import org.chronopolis.remote.node.RemoteNode;
import org.chronopolis.repository.IdentifierNotFoundException;
import org.chronopolis.repository.RemoteNodeRepository;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class StringToRemoteNodeConverterTest {

    @Test
    public void testNodeNameCorrect() {

        DefaultRemoteNodeRepositoryStub remoteNodeRepositoryStub = new DefaultRemoteNodeRepositoryStub();

        StringToRemoteNodeConverter converter = new StringToRemoteNodeConverter(remoteNodeRepositoryStub);
        converter.convert("NCAR");

        assertThat(remoteNodeRepositoryStub.getRemoteNodeName(), is(notNullValue()));
    }

    @Test(expected = IdentifierNotFoundException.class)
    public void testExceptionNotCaught() {

        DefaultRemoteNodeRepositoryStub remoteNodeRepositoryStub = new DefaultRemoteNodeRepositoryStub() {

            public RemoteNode getByName(String remoteNodeName) {
                throw new IdentifierNotFoundException(remoteNodeName);
            }
        };

        StringToRemoteNodeConverter converter = new StringToRemoteNodeConverter(remoteNodeRepositoryStub);
        converter.convert("NCAR");
    }

    private class DefaultRemoteNodeRepositoryStub implements RemoteNodeRepository {

        private String remoteNodeName;

        @Override
        public RemoteNode getByName(String remoteNodeName) {

            this.remoteNodeName = remoteNodeName;

            return null;
        }

        @Override
        public List<RemoteNode> getIngestNodesByEnvironment(String environment) {
            return null;
        }

        public String getRemoteNodeName() {

            return this.remoteNodeName;
        }

        public List<RemoteNode> getNodesByEnvironment(String environment) {
            return null;
        }

        public Map<String, RemoteNode> getNodesMapByEnvironment(String environment) {
            return null;
        }

    }
}
