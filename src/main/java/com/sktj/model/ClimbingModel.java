package com.sktj.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClimbingModel extends DetailedAchievModel {

  String routeName;
  String difficultyGrade;
  String wall;
}
