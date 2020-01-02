package com.sktj.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@NamedQuery(name = "ClimbingAchievements.findUsersClimbingAchievs",
    query = "SELECT c FROM ClimbingAchievements c "
        + "left JOIN fetch c.country co "
        + "left JOIN fetch c.notificationAuthor na "
        + "left JOIN fetch c.authors ca where na.email = :email or ca.email =:email")
@Entity
@Table(name = "climbing")
public class ClimbingAchievements implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  Long id;

  @Column(name = "notification_timestamp")
  @NotNull
  LocalDateTime notificationTimestamp;

  @Column
  @NotNull
  LocalDate date;

  @Column
  Duration duration;

  @Column(name = "route_name")
  @NotNull
  String routeName;

  @Column(name = "difficulty_grade")
  @NotNull
  String difficultyGrade;

  @Column
  @NotNull
  String wall;

  @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinColumn
  Country country;

  @Column
  @NotNull
  String region;

  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinTable(
      name = "user_climb",
      joinColumns = @JoinColumn(name = "climbing_id"),
      inverseJoinColumns = @JoinColumn(name = "user_id"))
  List<User> authors = new ArrayList<>();

  @Column
  String anotherAuthors;

  @Column(length = 1000)
  String comment;

  @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinColumn
  User notificationAuthor;
}
