/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (3.0.0-SNAPSHOT).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.diviso.graeshoppe.report.client.store.api;

import com.diviso.graeshoppe.report.client.store.model.StoreTypeDTO;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-12-31T16:37:58.581+05:30[Asia/Kolkata]")

@Api(value = "StoreTypeResource", description = "the StoreTypeResource API")
public interface StoreTypeResourceApi {

    @ApiOperation(value = "createStoreType", nickname = "createStoreTypeUsingPOST", notes = "", response = StoreTypeDTO.class, tags={ "store-type-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = StoreTypeDTO.class),
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/store-types",
        produces = "*/*", 
        consumes = "application/json",
        method = RequestMethod.POST)
    ResponseEntity<StoreTypeDTO> createStoreTypeUsingPOST(@ApiParam(value = "storeTypeDTO" ,required=true )  @Valid @RequestBody StoreTypeDTO storeTypeDTO);


    @ApiOperation(value = "deleteStoreType", nickname = "deleteStoreTypeUsingDELETE", notes = "", tags={ "store-type-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 204, message = "No Content"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden") })
    @RequestMapping(value = "/api/store-types/{id}",
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteStoreTypeUsingDELETE(@ApiParam(value = "id",required=true) @PathVariable("id") Long id);


    @ApiOperation(value = "getAllStoreTypes", nickname = "getAllStoreTypesUsingGET", notes = "", response = StoreTypeDTO.class, responseContainer = "List", tags={ "store-type-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = StoreTypeDTO.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/store-types",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<List<StoreTypeDTO>> getAllStoreTypesUsingGET(@ApiParam(value = "Page number of the requested page") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "Size of a page") @Valid @RequestParam(value = "size", required = false) Integer size,@ApiParam(value = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.") @Valid @RequestParam(value = "sort", required = false) List<String> sort);


    @ApiOperation(value = "getStoreType", nickname = "getStoreTypeUsingGET", notes = "", response = StoreTypeDTO.class, tags={ "store-type-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = StoreTypeDTO.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/store-types/{id}",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<StoreTypeDTO> getStoreTypeUsingGET(@ApiParam(value = "id",required=true) @PathVariable("id") Long id);


    @ApiOperation(value = "searchStoreTypes", nickname = "searchStoreTypesUsingGET", notes = "", response = StoreTypeDTO.class, responseContainer = "List", tags={ "store-type-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = StoreTypeDTO.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/_search/store-types",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<List<StoreTypeDTO>> searchStoreTypesUsingGET(@NotNull @ApiParam(value = "query", required = true) @Valid @RequestParam(value = "query", required = true) String query,@ApiParam(value = "Page number of the requested page") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "Size of a page") @Valid @RequestParam(value = "size", required = false) Integer size,@ApiParam(value = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.") @Valid @RequestParam(value = "sort", required = false) List<String> sort);


    @ApiOperation(value = "updateStoreType", nickname = "updateStoreTypeUsingPUT", notes = "", response = StoreTypeDTO.class, tags={ "store-type-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = StoreTypeDTO.class),
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/store-types",
        produces = "*/*", 
        consumes = "application/json",
        method = RequestMethod.PUT)
    ResponseEntity<StoreTypeDTO> updateStoreTypeUsingPUT(@ApiParam(value = "storeTypeDTO" ,required=true )  @Valid @RequestBody StoreTypeDTO storeTypeDTO);

}
