/*
//
// Auteur : Filipe Dias Morais
// Projet : LAJF-API
// Date   : 02.05.2023
// 


package org.lajf.controller;

import org.lajf.model.Sale;
import org.lajf.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @GetMapping
    public List<Sale> getAllSales() {
        return saleService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sale> getSaleById(@PathVariable int id) {
        return saleService.findById(id)
                .map(sale -> ResponseEntity.ok().body(sale))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public Sale createSale(@RequestBody Sale sale) {
        return saleService.save(sale);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sale> updateSale(@PathVariable int id, @RequestBody Sale sale) {
        return saleService.findById(id)
                .map(existingSale -> {
                    sale.setId(existingSale.getId());
                    return ResponseEntity.ok().body(saleService.save(sale));
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable int id) {
        saleService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
*/
