package bitlab.techordaspring.repositories;

import bitlab.techordaspring.models.Item;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
  @Query("select i from Item i where i.name ilike concat('%', :text, '%') "
      + "or i.description ilike concat('%', :text, '%') or "
      + "cast(i.price as string) =: text or "
      + "i.brand.name ilike concat('%', :text, '%') ")
  List<Item> findAll(String text);
}
