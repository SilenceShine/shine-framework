package io.github.SilenceShine.shine.spring.orm.data.mongo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

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
@Document(collection = "base_document")
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseDocument implements Serializable {

    @Serial
    private static final long serialVersionUID = -4940540768067344590L;

    @MongoId
    @Field("_id")
    private ObjectId id;

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
    private Integer version;

}
