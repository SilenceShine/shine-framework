package io.github.SilenceShine.shine.spring.orm.data.r2dbc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.*;
import org.springframework.data.relational.core.mapping.Column;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author SilenceShine
 * @github <a href="https://github.com/SilenceShine">SilenceShine</a>
 * @since 1.0
 */
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class BaseDomain implements Serializable {

    @Serial
    private static final long serialVersionUID = -1244273411669269612L;

    @Id
    @Column("id")
    private Long id;

    @Column("created_by")
    @CreatedBy
    private String createdBy;

    @Column("created_at")
    @CreatedDate
    private LocalDateTime createdAt;

    @Column("modified_by")
    @LastModifiedBy
    private String modifiedBy;

    @Column("modified_at")
    @LastModifiedDate
    private LocalDateTime modifiedAt;

    @Column("version")
    @Version
    private Integer version;

}
