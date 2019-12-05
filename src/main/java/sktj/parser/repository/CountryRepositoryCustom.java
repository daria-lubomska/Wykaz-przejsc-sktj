package sktj.parser.repository;

import org.springframework.stereotype.Repository;
import sktj.parser.entity.Country;

@Repository()
public interface CountryRepositoryCustom {
  Country findByNameAndRegion(String name, String region);
}