package org.chronopolis.controller.ace.onlinestatus;

import org.chronopolis.model.NodeOnlineStatus;
import org.chronopolis.remote.node.RemoteNode;
import org.chronopolis.repository.RemoteNodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AceOnlineStatusController {

    private RemoteNodeRepository remoteNodeRepository;

    @Autowired
    public AceOnlineStatusController(RemoteNodeRepository remoteNodeRepository) {
        this.remoteNodeRepository = remoteNodeRepository;
    }

    @RequestMapping(value = "/api/v1/getNodeOnlineStatus.json")
    @ResponseBody
    public List<NodeOnlineStatus> getOnlineStatuses(@RequestBody List<AceOnlineStatusRequest> aceOnlineStatusRequests) {

        List<NodeOnlineStatus> nodeOnlineStatuses = new ArrayList<>();

        for (AceOnlineStatusRequest aceOnlineStatusRequest : aceOnlineStatusRequests) {

            RemoteNode remoteNode = this.remoteNodeRepository.getByName(aceOnlineStatusRequest.getNode());

            nodeOnlineStatuses.add(remoteNode.getNodeOnlineStatus());
        }

        return nodeOnlineStatuses;
    }

    @RequestMapping(value = "/api/v1/node/{node}/ace.json", method = RequestMethod.GET)
    @ResponseBody
    public NodeOnlineStatus getOnlineStatus(@PathVariable(value = "node") RemoteNode remoteNode) {

        return remoteNode.getNodeOnlineStatus();
    }

}
