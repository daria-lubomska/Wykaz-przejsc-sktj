package com.sktj.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
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
@Table(name = "cave")
public class CaveAchievements implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  Long id;

  @Column(name = "notification_timestamp")
  @NotNull
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
  LocalDateTime notificationTimestamp;

  @Column(name = "entry_timestamp")
  @NotNull
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
  LocalDateTime entryTimestamp;

  @Column(name = "exit_timestamp")
  @NotNull
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
  LocalDateTime exitTimestamp;

  @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinColumn(name = "cavesOfCountry")
  Country country;

  @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinColumn(name = "caves")
  Cave caveName;

  @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinColumn(name = "notification_author")
  User notificationAuthor;

  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinTable(
      name = "user_cave",
      joinColumns = @JoinColumn(name = "cave_id"),
      inverseJoinColumns = @JoinColumn(name = "user_id"))
  Set<User> authors = new HashSet<>();

  @Column(name = "reached_parts")
  @NotNull
  String reachedParts;

  @Column(name = "cave_overcome_style")
  @NotNull
  String caveOvercomeStyle;

  @Column(name = "authors_from_another_clubs")
  String authorsFromAnotherClubs;

  @Column(length = 1000)
  String comment;

}