package io.github.SilenceShine.shine.spring.orm.data.elasticsearch;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.*;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

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
@Document(indexName = "base_document")
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseDocument implements Serializable {

    @Serial
    private static final long serialVersionUID = -8409196324111350643L;

    @Id
    private String id;

    @Field("created_by")
    @CreatedBy
    private String createdBy;

    @Field("created_at")
    @CreatedDate
    private LocalDateTime createdAt;

    @Field("modified_by")
    @LastModifiedBy
    private String modifiedBy;

    @Field("modified_at")
    @LastModifiedDate
    private LocalDateTime modifiedAt;

    @Field("version")
    @Version
    private Long version;

}
