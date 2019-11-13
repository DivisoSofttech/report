/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (3.0.0-SNAPSHOT).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.diviso.graeshoppe.report.client.order.api;

import com.diviso.graeshoppe.report.client.order.model.OpenTask;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import javax.validation.Valid;
import java.util.List;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-08-27T15:03:55.787+05:30[Asia/Kolkata]")

@Api(value = "OrderQueryResource", description = "the OrderQueryResource API")
public interface OrderQueryResourceApi {

    @ApiOperation(value = "getTasks", nickname = "getTasksUsingGET", notes = "", response = OpenTask.class, responseContainer = "List", tags={ "order-query-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = OpenTask.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/tasks",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<List<OpenTask>> getTasksUsingGET(@ApiParam(value = "assignee") @Valid @RequestParam(value = "assignee", required = false) String assignee,@ApiParam(value = "assigneeLike") @Valid @RequestParam(value = "assigneeLike", required = false) String assigneeLike,@ApiParam(value = "candidateGroup") @Valid @RequestParam(value = "candidateGroup", required = false) String candidateGroup,@ApiParam(value = "candidateGroups") @Valid @RequestParam(value = "candidateGroups", required = false) String candidateGroups,@ApiParam(value = "candidateUser") @Valid @RequestParam(value = "candidateUser", required = false) String candidateUser,@ApiParam(value = "createdAfter") @Valid @RequestParam(value = "createdAfter", required = false) String createdAfter,@ApiParam(value = "createdBefore") @Valid @RequestParam(value = "createdBefore", required = false) String createdBefore,@ApiParam(value = "createdOn") @Valid @RequestParam(value = "createdOn", required = false) String createdOn,@ApiParam(value = "name") @Valid @RequestParam(value = "name", required = false) String name,@ApiParam(value = "nameLike") @Valid @RequestParam(value = "nameLike", required = false) String nameLike);

}
