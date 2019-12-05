package sktj.parser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sktj.parser.entity.Country;

@Repository
public interface CountryRepository extends CountryRepositoryBasic, CountryRepositoryCustom {

}
