package ga.rugal.fridge.core.entity;

import static config.SystemDefaultProperty.SCHEMA;

import java.time.Instant;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

/**
 * Item entity.
 *
 * @author sally
 */
@Data
@Entity
@Table(name = "item", schema = SCHEMA)
public class Item {

  private static final String SEQUENCE_NAME = "item_iid_seq";

  @Basic(optional = false)
  @Column(name = "iid")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
  @Id
  @SequenceGenerator(name = SEQUENCE_NAME, allocationSize = 1,
          sequenceName = SCHEMA + "." + SEQUENCE_NAME)
  private Integer iid;

  @Column(name = "name")
  private String name;

  @Column(name = "create_at")
  private Long createAt;

  @Column(name = "update_at")
  private Long updateAt;

  @OneToMany(mappedBy = "item")
  private List<Storage> fridgeStorageList;

  @OneToMany(mappedBy = "item")
  private List<ItemTag> itemTagList;

  @PrePersist
  void onCreate() {
    this.createAt = Instant.now().getEpochSecond();
  }

  @PreUpdate
  void onUpdate() {
    this.updateAt = Instant.now().getEpochSecond();
  }

}
