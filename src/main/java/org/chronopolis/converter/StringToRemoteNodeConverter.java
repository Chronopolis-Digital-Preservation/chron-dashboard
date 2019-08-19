package org.chronopolis.converter;

import org.chronopolis.remote.node.RemoteNode;
import org.chronopolis.repository.RemoteNodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToRemoteNodeConverter implements Converter<String, RemoteNode> {

    private final RemoteNodeRepository remoteNodeRepository;

    @Autowired
    public StringToRemoteNodeConverter(RemoteNodeRepository remoteNodeRepository) {
        this.remoteNodeRepository = remoteNodeRepository;
    }

    @Override
    public RemoteNode convert(String remoteNodeStr) {
        return remoteNodeRepository.getByName(remoteNodeStr);
    }

}
