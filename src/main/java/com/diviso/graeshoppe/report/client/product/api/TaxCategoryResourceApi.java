/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (3.0.0-SNAPSHOT).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.diviso.graeshoppe.report.client.product.api;

import com.diviso.graeshoppe.report.client.product.model.TaxCategoryDTO;
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
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-12-31T16:45:03.566+05:30[Asia/Kolkata]")

@Api(value = "TaxCategoryResource", description = "the TaxCategoryResource API")
public interface TaxCategoryResourceApi {

    @ApiOperation(value = "createTaxCategory", nickname = "createTaxCategoryUsingPOST", notes = "", response = TaxCategoryDTO.class, tags={ "tax-category-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = TaxCategoryDTO.class),
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/tax-categories",
        produces = "*/*", 
        consumes = "application/json",
        method = RequestMethod.POST)
    ResponseEntity<TaxCategoryDTO> createTaxCategoryUsingPOST(@ApiParam(value = "taxCategoryDTO" ,required=true )  @Valid @RequestBody TaxCategoryDTO taxCategoryDTO);


    @ApiOperation(value = "deleteTaxCategory", nickname = "deleteTaxCategoryUsingDELETE", notes = "", tags={ "tax-category-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 204, message = "No Content"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden") })
    @RequestMapping(value = "/api/tax-categories/{id}",
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteTaxCategoryUsingDELETE(@ApiParam(value = "id",required=true) @PathVariable("id") Long id);


    @ApiOperation(value = "getAllTaxCategories", nickname = "getAllTaxCategoriesUsingGET", notes = "", response = TaxCategoryDTO.class, responseContainer = "List", tags={ "tax-category-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = TaxCategoryDTO.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/tax-categories",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<List<TaxCategoryDTO>> getAllTaxCategoriesUsingGET(@ApiParam(value = "Page number of the requested page") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "Size of a page") @Valid @RequestParam(value = "size", required = false) Integer size,@ApiParam(value = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.") @Valid @RequestParam(value = "sort", required = false) List<String> sort);


    @ApiOperation(value = "getTaxCategory", nickname = "getTaxCategoryUsingGET", notes = "", response = TaxCategoryDTO.class, tags={ "tax-category-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = TaxCategoryDTO.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/tax-categories/{id}",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<TaxCategoryDTO> getTaxCategoryUsingGET(@ApiParam(value = "id",required=true) @PathVariable("id") Long id);


    @ApiOperation(value = "searchTaxCategories", nickname = "searchTaxCategoriesUsingGET", notes = "", response = TaxCategoryDTO.class, responseContainer = "List", tags={ "tax-category-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = TaxCategoryDTO.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/_search/tax-categories",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<List<TaxCategoryDTO>> searchTaxCategoriesUsingGET(@NotNull @ApiParam(value = "query", required = true) @Valid @RequestParam(value = "query", required = true) String query,@ApiParam(value = "Page number of the requested page") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "Size of a page") @Valid @RequestParam(value = "size", required = false) Integer size,@ApiParam(value = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.") @Valid @RequestParam(value = "sort", required = false) List<String> sort);


    @ApiOperation(value = "updateTaxCategory", nickname = "updateTaxCategoryUsingPUT", notes = "", response = TaxCategoryDTO.class, tags={ "tax-category-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = TaxCategoryDTO.class),
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/tax-categories",
        produces = "*/*", 
        consumes = "application/json",
        method = RequestMethod.PUT)
    ResponseEntity<TaxCategoryDTO> updateTaxCategoryUsingPUT(@ApiParam(value = "taxCategoryDTO" ,required=true )  @Valid @RequestBody TaxCategoryDTO taxCategoryDTO);

}
