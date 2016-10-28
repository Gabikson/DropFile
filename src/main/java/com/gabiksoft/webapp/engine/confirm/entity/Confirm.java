package com.gabiksoft.webapp.engine.confirm.entity;

import com.gabiksoft.webapp.enums.ConfirmType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "confirm")
public class Confirm {

    @Id
    @Column(name = "id")
    @GenericGenerator(name = "uuid-generator", strategy = "uuid2")
    @GeneratedValue(generator = "uuid-generator")
    private String id;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private ConfirmType type;

    @Column(name = "idType")
    private String idType;

    @Column(name = "value")
    private String value;

    @Column(name = "expiration_time")
    private Timestamp expirationTime;

    @Column(name = "status")
    private boolean status;

    public Confirm() {
    }

    public Confirm(ConfirmType type, String idType, String value) {
        this.type = type;
        this.idType = idType;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ConfirmType getType() {
        return type;
    }

    public void setType(ConfirmType type) {
        this.type = type;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Timestamp getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Timestamp expirationTime) {
        this.expirationTime = expirationTime;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Confirm confirm = (Confirm) o;

        if (status != confirm.status) return false;
        if (expirationTime != null ? !expirationTime.equals(confirm.expirationTime) : confirm.expirationTime != null)
            return false;
        if (id != null ? !id.equals(confirm.id) : confirm.id != null) return false;
        if (idType != null ? !idType.equals(confirm.idType) : confirm.idType != null) return false;
        if (type != confirm.type) return false;
        if (value != null ? !value.equals(confirm.value) : confirm.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (idType != null ? idType.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (expirationTime != null ? expirationTime.hashCode() : 0);
        result = 31 * result + (status ? 1 : 0);
        return result;
    }
}
