package org.chronopolis.controller.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class VersionController {

    private String dashboardVersion;

    @Autowired
    public VersionController(@Value("${dashboard.version}") String version) {

        this.dashboardVersion = version;
    }

    @RequestMapping(value = "/api/v1/getVersion.json")
    @ResponseBody
    public VersionResponse getVersion() {

        VersionResponse versionResponse = new VersionResponse();

        versionResponse.setVersion(this.dashboardVersion);

        return versionResponse;
    }
}
