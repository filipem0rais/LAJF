//
// Auteur : Filipe Dias Morais
// Projet : Dev
// Date   : 16.05.2023
// 


package ch.es.pl.quotes.api.endpoints;

import ch.es.pl.quotes.api.entities.BidEntity;
import ch.es.pl.quotes.api.entities.CategoryEntity;
import ch.es.pl.quotes.api.entities.ItemEntity;
import ch.es.pl.quotes.api.entities.UserEntity;
import ch.es.pl.quotes.api.exceptions.CategoryNotFoundException;
import ch.es.pl.quotes.api.exceptions.ItemNotFoundException;
import ch.es.pl.quotes.api.exceptions.UserNotFoundException;
import ch.es.pl.quotes.api.repositories.BidRepository;
import ch.es.pl.quotes.api.repositories.CategoryRepository;
import ch.es.pl.quotes.api.repositories.ItemRepository;
import ch.es.pl.quotes.api.repositories.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.openapitools.api.ItemsApi;
import org.openapitools.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class ItemController implements ItemsApi {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BidRepository bidRepository;


    @Value("${jwt.secret}")
    private String secretKey;

    @Override
    public ResponseEntity<Item> getItem(Integer id) {
        Optional<ItemEntity> opt = itemRepository.findById(id);
        if (opt.isPresent()) {

            ItemEntity itemEntity = opt.get();

            if (!itemEntity.getIteOnSale()){
                throw new ItemNotFoundException(id);
            }

            Item item = new Item();
            item.setIdItem(itemEntity.getIdItem());
            item.setIteDescription(itemEntity.getIteDescription());
            item.setIteInitialValue(itemEntity.getIteInitialValue());
            item.setIteOnSale(itemEntity.getIteOnSale());
            item.setIteDatePublication(itemEntity.getIteDatePublication());
            item.setIdUser(itemEntity.getUser().getIdUser());
            item.setIdCategory(itemEntity.getCategory().getIdCategory());
            item.setIteName(itemEntity.getIteName());
            item.setIteState(itemEntity.getIteState());
            item.setItePicture(itemEntity.getItePicture());
            item.setItePickedUp(itemEntity.getItePickedUp());

            // Get the highest bid amount for the item
            Double highestBidAmount = bidRepository.findHighestBidAmountByItem(itemEntity);
            item.setIteHighestBidAmount(highestBidAmount);

            // Get count of bids for the item
            Integer bidsCount = bidRepository.countBidsByItem(itemEntity);
            item.setIteBidCount(bidsCount);


            return new ResponseEntity<Item>(item, HttpStatus.OK);
        } else {
            // return ResponseEntity.notFound().build();
            throw new ItemNotFoundException(id);
        }
    }

    @Override
    public ResponseEntity<Item> updateItem(@RequestHeader("Authorization") String authHeader, Item item) {
        Optional<ItemEntity> opt = itemRepository.findById(item.getIdItem());

        Integer userId = getUserIdFromToken(authHeader);
        Optional<UserEntity> optUser = userRepository.findById(userId);

        if (opt.isPresent()) {
            ItemEntity itemEntity = opt.get();
            itemEntity.setIteDescription(item.getIteDescription());
            itemEntity.setIteInitialValue(item.getIteInitialValue());
            itemEntity.setIteOnSale(item.getIteOnSale());
            itemEntity.setIteDatePublication(item.getIteDatePublication());

            UserEntity userEntity = userRepository.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException("User not found", HttpStatus.NOT_FOUND));
            itemEntity.setUser(userEntity);

            CategoryEntity categoryEntity = categoryRepository.findById(item.getIdCategory())
                    .orElseThrow(() -> new CategoryNotFoundException(item.getIdCategory()));
            itemEntity.setCategory(categoryEntity);

            itemEntity.setIteName(item.getIteName());
            itemEntity.setIteState(item.getIteState());
            itemEntity.setItePicture(item.getItePicture());
            itemEntity.setItePickedUp(item.getItePickedUp());
            itemRepository.save(itemEntity);
            return new ResponseEntity<Item>(item, HttpStatus.OK);
        } else {
            // return ResponseEntity.notFound().build();
            throw new ItemNotFoundException(item.getIdItem());
        }
    }


    @Override
    public ResponseEntity<Item> createItem(Item item) {



        String token = request.getHeader("Authorization");

        token = token.replace("Bearer ", "");
        Integer userId = getUserIdFromToken(token);
        ItemEntity itemEntity = new ItemEntity();

        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found", HttpStatus.NOT_FOUND));
        itemEntity.setUser(userEntity);

        CategoryEntity categoryEntity = categoryRepository.findById(item.getIdCategory())
                .orElseThrow(() -> new CategoryNotFoundException(item.getIdCategory()));


        itemEntity.setIteDescription(item.getIteDescription());
        itemEntity.setIteInitialValue(item.getIteInitialValue());
        itemEntity.setIteOnSale(item.getIteOnSale());
        itemEntity.setIteDatePublication(item.getIteDatePublication());
        itemEntity.setCategory(categoryEntity);
        itemEntity.setIteName(item.getIteName());
        itemEntity.setIteState(item.getIteState());
        itemEntity.setItePicture(item.getItePicture());
        itemEntity.setItePickedUp(item.getItePickedUp());
        itemRepository.save(itemEntity);
        return new ResponseEntity<Item>(item, HttpStatus.OK);

    }

    private Integer getUserIdFromToken(String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        Jws<Claims> claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token);
        return claims.getBody().get("id", Integer.class);
    }

    @Override
    public ResponseEntity<Void> deleteItem(Integer id, @RequestHeader("Authorization") String authHeader) {
        Optional<ItemEntity> opt = itemRepository.findById(id);

        Integer userId = getUserIdFromToken(authHeader);

        if (opt.isPresent()) {
            ItemEntity itemEntity = opt.get();
            if (itemEntity.getUser().getIdUser() != userId) {
                throw new ItemNotFoundException(id);
            }
            itemRepository.deleteById(id);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } else {
            // return ResponseEntity.notFound().build();
            throw new ItemNotFoundException(id);
        }
    }

    @Override
    public ResponseEntity<List<Item>> getItems() {
        List<ItemEntity> itemEntities = itemRepository.findByIteOnSale(true);
        List<Item> items = new ArrayList<>();
        for (ItemEntity itemEntity : itemEntities) {
            Item item = new Item();
            item.setIdItem(itemEntity.getIdItem());
            item.setIteDescription(itemEntity.getIteDescription());
            item.setIteInitialValue(itemEntity.getIteInitialValue());
            item.setIteOnSale(itemEntity.getIteOnSale());
            item.setIteDatePublication(itemEntity.getIteDatePublication());
            item.setIdUser(itemEntity.getUser().getIdUser());
            item.setIdCategory(itemEntity.getCategory().getIdCategory());
            item.setIteName(itemEntity.getIteName());
            item.setIteState(itemEntity.getIteState());
            item.setItePicture(itemEntity.getItePicture());
            item.setItePickedUp(itemEntity.getItePickedUp());

            // Get the highest bid amount for the item
            Double highestBidAmount = bidRepository.findHighestBidAmountByItem(itemEntity);
            item.setIteHighestBidAmount(highestBidAmount);

            // Get count of bids for the item
            Integer bidsCount = bidRepository.countBidsByItem(itemEntity);
            item.setIteBidCount(bidsCount);

            items.add(item);
        }
        return new ResponseEntity<List<Item>>(items, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Item>> getItemsByCategory(Integer idCategory) {
        Optional<CategoryEntity> opt = categoryRepository.findById(idCategory);
        if (opt.isPresent()) {
            CategoryEntity categoryEntity = opt.get();
            List<ItemEntity> items = getAllItemsForCategory(categoryEntity);
            // Convert ItemEntity to Item
            List<Item> itemsDto = new ArrayList<>();
            for (ItemEntity itemEntity : items) {
                Item item = new Item();
                item.setIdItem(itemEntity.getIdItem());
                item.setIteDescription(itemEntity.getIteDescription());
                item.setIteInitialValue(itemEntity.getIteInitialValue());
                item.setIteOnSale(itemEntity.getIteOnSale());
                item.setIteDatePublication(itemEntity.getIteDatePublication());
                item.setIdUser(itemEntity.getUser().getIdUser());
                item.setIdCategory(itemEntity.getCategory().getIdCategory());
                item.setIteName(itemEntity.getIteName());
                item.setIteState(itemEntity.getIteState());
                item.setItePicture(itemEntity.getItePicture());
                item.setItePickedUp(itemEntity.getItePickedUp());
                // Get the highest bid amount for the item
                Double highestBidAmount = bidRepository.findHighestBidAmountByItem(itemEntity);
                item.setIteHighestBidAmount(highestBidAmount);

                // Get count of bids for the item
                Integer bidsCount = bidRepository.countBidsByItem(itemEntity);
                item.setIteBidCount(bidsCount);

                itemsDto.add(item);
            }
            return new ResponseEntity<List<Item>>(itemsDto, HttpStatus.OK);
        } else {
            // Handle category not found
            return ResponseEntity.notFound().build();
        }
    }


    private List<ItemEntity> getAllItemsForCategory(CategoryEntity categoryEntity) {
        List<ItemEntity> items = itemRepository.findByCategoryAndIteOnSale(categoryEntity, true);
        List<CategoryEntity> subcategories = categoryRepository.findByCatParent(categoryEntity);
        for (CategoryEntity subcategory : subcategories) {
            items.addAll(getAllItemsForCategory(subcategory));
        }
        return items;
    }


    @Override
    public ResponseEntity<List<Item>> getUnsoldItems(@RequestHeader("Authorization") String authHeader) {
        Integer userId = getUserIdFromToken(authHeader);
        Optional<UserEntity> optUser = userRepository.findById(userId);

        if (optUser.isPresent()) {
            List<ItemEntity> itemEntities = itemRepository.findUnsoldItemsByUser(optUser.get());

            List<Item> items = new ArrayList<>();
            for (ItemEntity itemEntity : itemEntities) {
                Item item = new Item();
                item.setIdItem(itemEntity.getIdItem());
                item.setIteDescription(itemEntity.getIteDescription());
                item.setIteInitialValue(itemEntity.getIteInitialValue());
                item.setIteOnSale(itemEntity.getIteOnSale());
                item.setIteDatePublication(itemEntity.getIteDatePublication());
                item.setIdUser(itemEntity.getUser().getIdUser());
                item.setIdCategory(itemEntity.getCategory().getIdCategory());
                item.setIteName(itemEntity.getIteName());
                item.setIteState(itemEntity.getIteState());
                item.setItePicture(itemEntity.getItePicture());
                item.setItePickedUp(itemEntity.getItePickedUp());
                // Get the highest bid amount for the item
                Double highestBidAmount = bidRepository.findHighestBidAmountByItem(itemEntity);
                item.setIteHighestBidAmount(highestBidAmount);
                // Get count of bids for the item
                Integer bidsCount = bidRepository.countBidsByItem(itemEntity);
                item.setIteBidCount(bidsCount);
                items.add(item);
            }
            return new ResponseEntity<List<Item>>(items, HttpStatus.OK);
        } else {
            throw new UserNotFoundException("User not found", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Void> endAuction(@PathVariable Integer itemId, @RequestHeader("Authorization") String authHeader) {
        Integer userId = getUserIdFromToken(authHeader);
        Optional<ItemEntity> optItem = itemRepository.findById(itemId);

        if (optItem.isPresent()) {
            ItemEntity item = optItem.get();

            if (!item.getUser().getIdUser().equals(userId)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            item.setIteOnSale(false);
            itemRepository.save(item);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    public ResponseEntity<Void> validateTransaction(@PathVariable Integer itemId, @RequestHeader("Authorization") String authHeader) {
        Integer userId = getUserIdFromToken(authHeader);
        Optional<ItemEntity> optItem = itemRepository.findById(itemId);

        if (optItem.isPresent()) {
            ItemEntity item = optItem.get();

            if (!item.getUser().getIdUser().equals(userId)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            if (item.getBids().isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            BidEntity highestBid = Collections.max(item.getBids(), Comparator.comparing(BidEntity::getBidAmount));
            item.setItePickedUp(true);
            item.setUser(highestBid.getUser());
            itemRepository.save(item);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<List<Item>> getLastItemsOnSale(@PathVariable Integer count) {
        List<ItemEntity> itemEntities = itemRepository.findLastItemsOnSale(count);
        List<Item> items = new ArrayList<>();

        int i = 0;

        for (ItemEntity itemEntity : itemEntities) {
            if (i < count && itemEntity.getIteOnSale() ) {
                Item item = new Item();
                item.setIdItem(itemEntity.getIdItem());
                item.setIteDescription(itemEntity.getIteDescription());
                item.setIteInitialValue(itemEntity.getIteInitialValue());
                item.setIteOnSale(itemEntity.getIteOnSale());
                item.setIteDatePublication(itemEntity.getIteDatePublication());
                item.setIdUser(itemEntity.getUser().getIdUser());
                item.setIdCategory(itemEntity.getCategory().getIdCategory());
                item.setIteName(itemEntity.getIteName());
                item.setIteState(itemEntity.getIteState());
                item.setItePicture(itemEntity.getItePicture());
                item.setItePickedUp(itemEntity.getItePickedUp());
                items.add(item);
                i++;
            }
        }

        return new ResponseEntity<List<Item>>(items, HttpStatus.OK);
    }


    /*
    @Override
    public ResponseEntity<Item> getItemByUser(Integer idUser) {
        Optional<ItemEntity> opt = itemRepository.findById(idUser);
        if (opt.isPresent()) {
            ItemEntity itemEntity = opt.get();
            Item item = new Item();
            item.setIdItem(itemEntity.getIdItem());
            item.setIteDescription(itemEntity.getIteDescription());
            item.setIteInitialValue(itemEntity.getIteInitialValue());
            item.setIteOnSale(itemEntity.getIteOnSale());
            item.setIteDatePublication(itemEntity.getIteDatePublication());
            item.setIdUser(itemEntity.getUser().getIdUser());
            item.setIdCategory(itemEntity.getCategory().getIdCategory());
            item.setIteName(itemEntity.getIteName());
            item.setIteState(itemEntity.getIteState());
            item.setItePicture(itemEntity.getItePicture());
            item.setItePickedUp(itemEntity.getItePickedUp());

            return new ResponseEntity<Item>(item, HttpStatus.OK);
        } else {
            // return ResponseEntity.notFound().build();
            throw new ItemNotFoundException(idUser);
        }
    }

    @Override
    public ResponseEntity<Item> getItemByState(String state) {
        Optional<ItemEntity> opt = itemRepository.findById(state);
        if (opt.isPresent()) {
            ItemEntity itemEntity = opt.get();
            Item item = new Item();
            item.setIdItem(itemEntity.getIdItem());
            item.setIteDescription(itemEntity.getIteDescription());
            item.setIteInitialValue(itemEntity.getIteInitialValue());
            item.setIteOnSale(itemEntity.getIteOnSale());
            item.setIteDatePublication(itemEntity.getIteDatePublication());
            item.setIdUser(itemEntity.getUser().getIdUser());
            item.setIdCategory(itemEntity.getCategory().getIdCategory());
            item.setIteName(itemEntity.getIteName());
            item.setIteState(itemEntity.getIteState());
            item.setItePicture(itemEntity.getItePicture());
            item.setItePickedUp(itemEntity.getItePickedUp());

            return new ResponseEntity<Item>(item, HttpStatus.OK);
        } else {
            // return ResponseEntity.notFound().build();
            throw new ItemNotFoundException(state);
        }
    }

    @Override
    public ResponseEntity<Item> getItemByPickedUp(String pickedUp) {
        Optional<ItemEntity> opt = itemRepository.findById(pickedUp);
        if (opt.isPresent()) {
            ItemEntity itemEntity = opt.get();
            Item item = new Item();
            item.setIdItem(itemEntity.getIdItem());
            item.setIteDescription(itemEntity.getIteDescription());
            item.setIteInitialValue(itemEntity.getIteInitialValue());
            item.setIteOnSale(itemEntity.getIteOnSale());
            item.setIteDatePublication(itemEntity.getIteDatePublication());
            item.setIdUser(itemEntity.getUser().getIdUser());
            item.setIdCategory(itemEntity.getCategory().getIdCategory());
            item.setIteName(itemEntity.getIteName());
            item.setIteState(itemEntity.getIteState());
            item.setItePicture(itemEntity.getItePicture());
            item.setItePickedUp(itemEntity.getItePickedUp());

            return new ResponseEntity<Item>(item, HttpStatus.OK);
        } else {
            // return ResponseEntity.notFound().build();
            throw new ItemNotFoundException(pickedUp);
        }

    }

    @Override
    public ResponseEntity<Item> getItemByOnSale(String onSale) {
        Optional<ItemEntity> opt = itemRepository.findById(onSale);
        if (opt.isPresent()) {
            ItemEntity itemEntity = opt.get();
            Item item = new Item();
            item.setIdItem(itemEntity.getIdItem());
            item.setIteDescription(itemEntity.getIteDescription());
            item.setIteInitialValue(itemEntity.getIteInitialValue());
            item.setIteOnSale(itemEntity.getIteOnSale());
            item.setIteDatePublication(itemEntity.getIteDatePublication());
            item.setIdUser(itemEntity.getUser().getIdUser());
            item.setIdCategory(itemEntity.getCategory().getIdCategory());
            item.setIteName(itemEntity.getIteName());
            item.setIteState(itemEntity.getIteState());
            item.setItePicture(itemEntity.getItePicture());
            item.setItePickedUp(itemEntity.getItePickedUp());

            return new ResponseEntity<Item>(item, HttpStatus.OK);
        } else {
            // return ResponseEntity.notFound().build();
            throw new ItemNotFoundException(onSale);
        }
    }

    @Override
    public ResponseEntity<Item> getItemByDatePublication(String datePublication) {
        Optional<ItemEntity> opt = itemRepository.findById(datePublication);
        if (opt.isPresent()) {
            ItemEntity itemEntity = opt.get();
            Item item = new Item();
            item.setIdItem(itemEntity.getIdItem());
            item.setIteDescription(itemEntity.getIteDescription());
            item.setIteInitialValue(itemEntity.getIteInitialValue());
            item.setIteOnSale(itemEntity.getIteOnSale());
            item.setIteDatePublication(itemEntity.getIteDatePublication());
            item.setIdUser(itemEntity.getUser().getIdUser());
            item.setIdCategory(itemEntity.getCategory().getIdCategory());
            item.setIteName(itemEntity.getIteName());
            item.setIteState(itemEntity.getIteState());
            item.setItePicture(itemEntity.getItePicture());
            item.setItePickedUp(itemEntity.getItePickedUp());

            return new ResponseEntity<Item>(item, HttpStatus.OK);
        } else {
            // return ResponseEntity.notFound().build();
            throw new ItemNotFoundException(datePublication);
        }
    }
    */


}
