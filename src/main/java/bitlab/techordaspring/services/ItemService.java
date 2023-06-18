package bitlab.techordaspring.services;

import bitlab.techordaspring.models.Item;
import bitlab.techordaspring.repositories.ItemRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

  @Autowired
  private ItemRepository itemRepository;

  public List<Item> getItems() {
    return itemRepository.findAll();
  }

  public void addItem(Item item) {
    itemRepository.save(item);
  }

  public void editItem(Item item) {
    itemRepository.save(item);
  }

  public Item getItemById(Long id) {
    return itemRepository.findById(id).orElseThrow();
  }

  public List<Item> search(String text) {
    return itemRepository.findAll(text);
  }
}
