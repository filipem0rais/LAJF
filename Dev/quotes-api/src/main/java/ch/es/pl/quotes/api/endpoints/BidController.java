//
// Auteur : Filipe Dias Morais
// Projet : Dev
// Date   : 09.05.2023
// 


package ch.es.pl.quotes.api.endpoints;

import ch.es.pl.quotes.api.entities.BidEntity;
import ch.es.pl.quotes.api.entities.ItemEntity;
import ch.es.pl.quotes.api.entities.UserEntity;
import ch.es.pl.quotes.api.exceptions.BidNotFoundException;
import ch.es.pl.quotes.api.exceptions.ItemNotFoundException;
import ch.es.pl.quotes.api.exceptions.UserNotFoundException;
import ch.es.pl.quotes.api.repositories.BidRepository;
import ch.es.pl.quotes.api.repositories.ItemRepository;
import ch.es.pl.quotes.api.repositories.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.openapitools.api.BidsApi;
import org.openapitools.model.Bid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class BidController implements BidsApi {
    @Autowired
    private BidRepository bidRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Value("${jwt.secret}")
    private String secretKey;

    @Override
    public ResponseEntity<Bid> getBid(Integer id) {
        Optional<BidEntity> opt = bidRepository.findById(id);
        if (opt.isPresent()) {
            BidEntity bidEntity = opt.get();
            Bid bid = new Bid();
            bid.setIdBid(bidEntity.getIdBid());
            bid.setBidAmount(bidEntity.getBidAmount());
            bid.setIdUser(bidEntity.getUser().getIdUser());
            bid.setIdItem(bidEntity.getItem().getIdItem());

            return new ResponseEntity<Bid>(bid, HttpStatus.OK);
        } else {
            throw new BidNotFoundException(id);
        }
    }

    @Override
    public ResponseEntity<List<Bid>> getBids() {
        List<BidEntity> bidEntities = bidRepository.findAll();
        List<Bid> bids = new ArrayList<>();

        for (BidEntity bidEntity : bidEntities) {
            Bid bid = new Bid();
            bid.setIdBid(bidEntity.getIdBid());
            bid.setBidAmount(bidEntity.getBidAmount());
            bid.setIdUser(bidEntity.getUser().getIdUser());
            bid.setIdItem(bidEntity.getItem().getIdItem());
            bids.add(bid);
        }

        return new ResponseEntity<List<Bid>>(bids, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Bid> createBid(@RequestHeader("Authorization") String authHeader, Bid bid) {

        Integer userId = getUserIdFromToken(authHeader);

        BidEntity bidEntity = new BidEntity();
        bidEntity.setBidAmount(bid.getBidAmount());

        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        bidEntity.setUser(userEntity);

        ItemEntity itemEntity = itemRepository.findById(bid.getIdItem())
                .orElseThrow(() -> new ItemNotFoundException(bid.getIdItem()));
        bidEntity.setItem(itemEntity);

        bidEntity = bidRepository.save(bidEntity);

        bid.setIdBid(bidEntity.getIdBid());
        return new ResponseEntity<Bid>(bid, HttpStatus.CREATED);
    }



    @Override
    public ResponseEntity<Bid> updateBid(@RequestHeader("Authorization") String authHeader, Bid bid) {
        Integer userId = getUserIdFromToken(authHeader);
        Integer id = bid.getIdBid();

        BidEntity existingBidEntity = bidRepository.findById(id)
                .orElseThrow(() -> new BidNotFoundException(id));

        if (!existingBidEntity.getUser().getIdUser().equals(userId)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        existingBidEntity.setBidAmount(bid.getBidAmount());

        ItemEntity itemEntity = itemRepository.findById(bid.getIdItem())
                .orElseThrow(() -> new ItemNotFoundException(bid.getIdItem()));

        existingBidEntity.setItem(itemEntity);

        bidRepository.save(existingBidEntity);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @Override
    public ResponseEntity<Void> deleteBid(Integer id, @RequestHeader("Authorization") String authHeader) {
        Integer userId = getUserIdFromToken(authHeader);

        BidEntity existingBidEntity = bidRepository.findById(id)
                .orElseThrow(() -> new BidNotFoundException(id));

        if (!existingBidEntity.getUser().getIdUser().equals(userId)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        bidRepository.delete(existingBidEntity);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    private Integer getUserIdFromToken(String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        Jws<Claims> claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token);
        return claims.getBody().get("id", Integer.class);
    }
}
