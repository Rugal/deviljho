package ga.rugal.demo.core.entity;

import static config.SystemDefaultProperty.SCHEMA;

import java.time.Instant;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

/**
 * Fridge Storage entity.
 *
 * @author sally
 */
@Data
@Entity
@Table(name = "fridge_storage", schema = SCHEMA)
public class FridgeStorage {

  private static final String SEQUENCE_NAME = "fridge_storage_fsid_seq";

  @Basic(optional = false)
  @Column(name = "fsid")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
  @Id
  @SequenceGenerator(name = SEQUENCE_NAME, allocationSize = 1,
          sequenceName = SCHEMA + "." + SEQUENCE_NAME)
  private Integer fsid;

  @Column(name = "quantity")
  private Integer quantity;

  @Column(name = "create_at")
  private Long createAt;

  @Column(name = "update_at")
  private Long updateAt;

  @JoinColumn(name = "iid", referencedColumnName = "iid")
  @ManyToOne
  private Item item;

  @PrePersist
  void onCreate() {
    this.createAt = Instant.now().getEpochSecond();
  }

  @PreUpdate
  void onUpdate() {
    this.updateAt = Instant.now().getEpochSecond();
  }
}
