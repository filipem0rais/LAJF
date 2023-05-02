//
// Auteur : Filipe Dias Morais
// Projet : LAJF-API
// Date   : 02.05.2023
// 


package org.lajf.service;

import org.lajf.model.Bid;
import org.lajf.repository.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BidService {

    @Autowired
    private BidRepository bidRepository;

    public List<Bid> findAll() {
        return bidRepository.findAll();
    }

    public Optional<Bid> findById(int id) {
        return bidRepository.findById(id);
    }

    public Bid save(Bid bid) {
        return bidRepository.save(bid);
    }

    public void deleteById(int id) {
        bidRepository.deleteById(id);
    }
}
