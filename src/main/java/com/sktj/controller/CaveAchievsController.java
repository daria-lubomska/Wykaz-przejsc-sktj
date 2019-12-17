package com.sktj.controller;

import com.sktj.controller.specification.CaveAchievFiltersSpecification;
import com.sktj.controller.specification.CaveAchievSearchSpecification;
import com.sktj.entity.CaveAchievements;
import com.sktj.repository.CaveAchievementsRepository;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/caves")
public class CaveAchievsController {

  private final CaveAchievementsRepository caveAchievementsRepository;

  @Autowired
  public CaveAchievsController(
      CaveAchievementsRepository caveAchievementsRepository) {
    this.caveAchievementsRepository = caveAchievementsRepository;
  }

  @GetMapping
  public Iterable<CaveAchievements> getAll(@PageableDefault(size = 20, sort = "entryTimestamp",
      direction = Direction.DESC) Pageable pageable) {
    return caveAchievementsRepository.findAll(pageable);
  }

  @DeleteMapping("/{caveAchievId}")
  public String deleteCaveAchiev(@PathVariable("caveAchievId") Long caveAchievId) {
    CaveAchievements caveAchiev = caveAchievementsRepository.findById(caveAchievId)
        .orElseThrow(() -> new IllegalArgumentException("This cave achievement does not exist!"));
    caveAchievementsRepository.delete(caveAchiev);
    return "redirect:/caves";
  }

//  @Transactional
  @GetMapping(value = "/filter")
  public Iterable<CaveAchievements> getCaveAchievByFilters(CaveAchievFiltersSpecification spec,
      @PageableDefault(size = 20, sort = "entryTimestamp",
          direction = Direction.DESC) Pageable pageable) {
    return caveAchievementsRepository.findAll(spec,pageable);
  }

//  @Transactional
  @GetMapping(value = "/search")
  public Iterable<CaveAchievements> getCaveAchievBySearch(CaveAchievSearchSpecification spec,
      @PageableDefault(size = 20, sort = "entryTimestamp",
          direction = Direction.DESC) Pageable pageable) {
    return caveAchievementsRepository.findAll(spec,pageable);
  }
}