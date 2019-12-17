package com.sktj.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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

  @Column(name = "entry_timestamp")
  @NotNull
  LocalDateTime entryTimestamp;

  @Column(name = "exit_timestamp")
  @NotNull
  LocalDateTime exitTimestamp;

  @Column
  @NotNull
  String region;

  @Column(name = "climbing_route_name")
  @NotNull
  String climbingRouteName;

  @Column(name = "difficulty_grade")
  @NotNull
  String difficultyGrade;

  @Column
  @NotNull
  String wall;

  @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinColumn(name = "notification_author")
  User notificationAuthor;

  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinTable(
      name = "user_climb",
      joinColumns = @JoinColumn(name = "climb_id"),
      inverseJoinColumns = @JoinColumn(name = "user_id"))
  List<User> authors = new ArrayList<>();

  @Column(name = "authors_from_another_clubs")
  String anotherAuthors;

  @Column(length = 1000)
  String comment;

}