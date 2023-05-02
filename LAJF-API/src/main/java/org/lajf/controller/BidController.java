/*
//
// Auteur : Filipe Dias Morais
// Projet : LAJF-API
// Date   : 02.05.2023
// 


package org.lajf.controller;

import org.lajf.model.Bid;
import org.lajf.service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bids")
public class BidController {

    @Autowired
    private BidService bidService;

    @GetMapping
    public List<Bid> getAllBids() {
        return bidService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bid> getBidById(@PathVariable int id) {
        return bidService.findById(id)
                .map(bid -> ResponseEntity.ok().body(bid))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public Bid createBid(@RequestBody Bid bid) {
        return bidService.save(bid);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bid> updateBid(@PathVariable int id, @RequestBody Bid bid) {
        return bidService.findById(id)
                .map(existingBid -> {
                    bid.setId(existingBid.getId());
                    return ResponseEntity.ok().body(bidService.save(bid));
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBid(@PathVariable int id) {
        bidService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
*/
