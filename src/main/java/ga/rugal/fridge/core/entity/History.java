package ga.rugal.fridge.core.entity;

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
import lombok.NoArgsConstructor;

/**
 * History entity.
 *
 * @author Rugal Bernstein
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "history", schema = SCHEMA)
public class History {

  /**
   * Create history for fill.
   *
   * @param item   item object
   * @param number a positive number
   *
   * @return history object with positive quantity
   */
  public static History fill(final Item item, final int number) {
    return new History(item, number);
  }

  /**
   * Create history for consumption.
   *
   * @param item   item object
   * @param number a positive number
   *
   * @return history object with negative quantity
   */
  public static History consume(final Item item, final int number) {
    return new History(item, -1 * number);
  }

  private static final String SEQUENCE_NAME = "history_hid_seq";

  @Basic(optional = false)
  @Column
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
  @Id
  @SequenceGenerator(name = SEQUENCE_NAME, allocationSize = 1,
                     sequenceName = SCHEMA + "." + SEQUENCE_NAME)
  private Integer hid;

  /**
   * Number of item changed. Positive is fill, negative is consume.
   */
  @Column
  private Integer quantity;

  @JoinColumn(name = "iid", referencedColumnName = "iid")
  @ManyToOne
  private Item item;

  @Column(name = "create_at")
  private Long createAt;

  @Column(name = "update_at")
  private Long updateAt;

  public History(final Item item, final Integer quantity) {
    this.quantity = quantity;
    this.item = item;
  }

  @PrePersist
  void onCreate() {
    this.createAt = Instant.now().getEpochSecond();
  }

  @PreUpdate
  void onUpdate() {
    this.updateAt = Instant.now().getEpochSecond();
  }
}
