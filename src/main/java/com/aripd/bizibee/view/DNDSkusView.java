package com.aripd.bizibee.view;

import com.aripd.bizibee.entity.SkuEntity;
import com.aripd.bizibee.service.SkuService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.DragDropEvent;
 
@Named
@javax.faces.view.ViewScoped
public class DNDSkusView implements Serializable {
  
    @Inject
    private SkuService skuService;
 
    private List<SkuEntity> skus;
     
    private List<SkuEntity> droppedSkus;
     
    private SkuEntity selectedSku;
     
    @PostConstruct
    public void init() {
        skus = skuService.findAll();
        droppedSkus = new ArrayList<>();
    }
     
    public void onSkuDrop(DragDropEvent ddEvent) {
        SkuEntity sku = ((SkuEntity) ddEvent.getData());
  
        droppedSkus.add(sku);
        skus.remove(sku);
    }
     
    public void setSkuService(SkuService skuService) {
        this.skuService = skuService;
    }
 
    public List<SkuEntity> getSkus() {
        return skus;
    }
 
    public List<SkuEntity> getDroppedSkus() {
        return droppedSkus;
    }    
 
    public SkuEntity getSelectedSku() {
        return selectedSku;
    }
 
    public void setSelectedSku(SkuEntity selectedSku) {
        this.selectedSku = selectedSku;
    }
}