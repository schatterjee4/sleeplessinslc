package com.overstock.inventory.rest;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.overstock.inventory.dto.ProductInventory;
import com.overstock.inventory.service.InventoryService;

@RestController
public class InventoryResource {
  private final InventoryService inventoryService;
  
  @Inject
  public InventoryResource(InventoryService inventoryService) {
    this.inventoryService = inventoryService;
  }
  
  @RequestMapping(value = "/productInventory/{productId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ProductInventory get(@PathVariable("productId") Long productId) {
    com.overstock.inventory.model.ProductInventory inventory = inventoryService.getInventory(productId);

    return new ProductInventory(productId, inventory.getCount());
  }
}
