package com.dood.elastic.controller;

import com.dood.elastic.utils.QueueConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.swagger.web.SwaggerResource;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple controller to redirect root url requests to the swagger API
 */
@Controller //Note if this is @RestController redirect will not work
@ApiIgnore //No need to have swagger document this controller
public class RootController {

//    @RequestMapping({"/", "/swagger", "/api"})
    @RequestMapping({"/"})
    public String redirectToSwagger() {
        return QueueConstants.REDIRECT_SWAGGER_UI_HTML;
    }

    //TODO Adding this causes stuff to blow up
//    @RequestMapping(value = "/swagger-resources")
//    public ResponseEntity<List<SwaggerResource>> swaggerResources() {
//        List<SwaggerResource> resources = new ArrayList<>();
//        resources.add(createSwaggerResource("Elastic", "/elastic", "V1.0"));
//        resources.add(createSwaggerResource("Messages", "/messages", "V1.0"));
//
//        return new ResponseEntity<>(resources, HttpStatus.OK);//TODO can I just return the list?
//    }

    private SwaggerResource createSwaggerResource(String name, String location, String version) {
        SwaggerResource resource = new SwaggerResource();
        resource.setName(name);
        resource.setLocation(location);
        resource.setSwaggerVersion(version);
        return resource;
    }
}
