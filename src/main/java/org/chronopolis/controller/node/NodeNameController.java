package org.chronopolis.controller.node;

import org.chronopolis.remote.node.RemoteNode;
import org.chronopolis.repository.RemoteNodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class NodeNameController {

    private RemoteNodeRepository remoteNodeRepository;

    @Autowired
    public NodeNameController(RemoteNodeRepository remoteNodeRepository) {
        this.remoteNodeRepository = remoteNodeRepository;
    }

    @RequestMapping(value = "/api/v1/getNodeList.json")
    @ResponseBody
    public List<NodeNameResponse> getNodeNamesByEnvironment(@RequestParam("environment") String environment) {

        List<NodeNameResponse> nodeNameResponseList = new ArrayList<>();

        Collection<RemoteNode> remoteNodes = this.remoteNodeRepository.getNodesByEnvironment(environment);

        for (RemoteNode remoteNode : remoteNodes) {

            NodeNameResponse nodeNameResponse = new NodeNameResponse();

            nodeNameResponse.setNode(remoteNode.getNodeName());

            nodeNameResponseList.add(nodeNameResponse);
        }

        return nodeNameResponseList;
    }

    @RequestMapping(value = "/api/v1/getIngestNodeList.json")
    @ResponseBody
    public List<NodeNameResponse> getIngestNodeNamesByEnvironment(@RequestParam("environment") String environment) {

        List<NodeNameResponse> nodeNameResponseList = new ArrayList<>();

        Collection<RemoteNode> remoteNodes = this.remoteNodeRepository.getIngestNodesByEnvironment(environment);

        for (RemoteNode remoteNode : remoteNodes) {

            NodeNameResponse nodeNameResponse = new NodeNameResponse();

            nodeNameResponse.setNode(remoteNode.getNodeName());

            nodeNameResponseList.add(nodeNameResponse);
        }

        return nodeNameResponseList;
    }
}
