package sktj.parser.repository;

import org.springframework.stereotype.Repository;
import sktj.parser.entity.Cave;

@Repository
public interface CaveRepositoryCustom {

  Cave findByNameAndRegion(String name, String region);
}
