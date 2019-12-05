package sktj.parser.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@Entity
@Table(name = "cave_name", indexes = {@Index(name = "name_cave", columnList = "name")})
public class Cave {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private Long id;

  @Column
  @NotNull
  private String name;

  @Column
  @NotNull
  private String region;

  public Cave() {
  }

  public Cave(@NotNull String name, @NotNull String region) {
    this.name = name;
    this.region = region;
  }
}