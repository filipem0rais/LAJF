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
import ch.es.pl.quotes.api.exceptions.InvalidBidException;
import ch.es.pl.quotes.api.exceptions.ItemNotFoundException;
import ch.es.pl.quotes.api.exceptions.UserNotFoundException;
import ch.es.pl.quotes.api.repositories.BidRepository;
import ch.es.pl.quotes.api.repositories.ItemRepository;
import ch.es.pl.quotes.api.repositories.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.openapitools.api.BidsApi;
import org.openapitools.model.Bid;
import org.openapitools.model.MakeBidRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;

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

    @Autowired
    HttpServletRequest request;

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
    public ResponseEntity<Bid> updateBid(Bid bid) {

        String token = request.getHeader("Authorization");

        token = token.replace("Bearer ", "");

        Integer userId = getUserIdFromToken(token);
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
    public ResponseEntity<Void> deleteBid(Integer id) {

        String token = request.getHeader("Authorization");

        token = token.replace("Bearer ", "");

        Integer userId = getUserIdFromToken(token);

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

    @Override
    public ResponseEntity<Bid> makeBid( @Valid @RequestBody MakeBidRequest makeBidRequest) {

        String token = request.getHeader("Authorization");

        token = token.replace("Bearer ", "");

        Integer userId = getUserIdFromToken(token);

        ItemEntity itemEntity = itemRepository.findById(makeBidRequest.getItemId())
                .orElseThrow(() -> new ItemNotFoundException(makeBidRequest.getItemId()));

        // Obtenez la plus grande enchère actuelle pour l'item
        Double highestBidAmount = bidRepository.findHighestBidAmountByItem(itemEntity);

        if (highestBidAmount == null) {
            highestBidAmount = 0.;
        }

        // Vérifiez si le bidAmount est égal ou supérieur à iteInitialValue de l'item
        if (highestBidAmount >= makeBidRequest.getBidAmount() || makeBidRequest.getBidAmount() < itemEntity.getIteInitialValue()) {
            // Le bidAmount est inférieur à iteInitialValue, renvoyez une erreur
            //throw new IllegalArgumentException("Le montant de l'enchère doit être égal ou supérieur à iteInitialValue de l'item.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        BidEntity bidEntity = new BidEntity();
        bidEntity.setBidAmount(makeBidRequest.getBidAmount());

        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found", HttpStatus.NOT_FOUND));

        /*// Vérifiez si l'enchère est supérieure au useCredit actuel de l'utilisateur
        if (makeBidRequest.getBidAmount() > userEntity.getUseCredit()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }*/

        // Obtenez tous les items qui sont encore à vendre
        List<ItemEntity> itemsOnSale = itemRepository.findByIteOnSale(true);

        Double totalWinningBids = 0.;

        // Pour chaque item, trouvez le bid le plus élevé
        for (ItemEntity item : itemsOnSale) {
            Optional<BidEntity> highestBidOpt = bidRepository.findHighestBidByItem(item);

            // Si le bid le plus élevé appartient à l'utilisateur, ajoutez-le au total
            if (highestBidOpt.isPresent()) {
                BidEntity highestBid = highestBidOpt.get();
                if (highestBid.getUser().getIdUser().equals(userId)) {
                    totalWinningBids += highestBid.getBidAmount();
                }
            }
        }

        // Vérifiez si la somme des enchères gagnantes actuelles et la nouvelle enchère dépasse le useCredit de l'utilisateur
        if (makeBidRequest.getBidAmount() + totalWinningBids > userEntity.getUseCredit()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        bidEntity.setUser(userEntity);

        bidEntity.setItem(itemEntity);

        bidEntity = bidRepository.save(bidEntity);

        Bid bid = new Bid();
        bid.setIdBid(bidEntity.getIdBid());
        bid.setBidAmount(bidEntity.getBidAmount());
        bid.setIdUser(bidEntity.getUser().getIdUser());
        bid.setIdItem(bidEntity.getItem().getIdItem());

        return new ResponseEntity<>(bid, HttpStatus.CREATED);
    }
}
