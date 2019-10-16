package com.maup.lesson20191008.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@MappedSuperclass
public class SystemDictionary {

    @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonView({SystemDictionaryView.MainSystemDictionaryView.class})
    private String id;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated")
    private Date updated;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created")
    private Date created;

    @Column(name = "isActive")
    private Boolean isActive=true;
}
