package com.gabiksoft.webapp.entity;

import com.gabiksoft.webapp.enums.ConfirmType;

import javax.persistence.*;

@Entity
@Table(name = "confirm")
public class Confirm {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private ConfirmType type;

    @Column(name = "idType")
    private String idType;

    @Column(name = "value")
    private String value;

    public Confirm() {
    }

    public Confirm(ConfirmType type, String idType, String value) {
        this.type = type;
        this.idType = idType;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Confirm confirm = (Confirm) o;

        if (id != confirm.id) return false;
        if (idType != null ? !idType.equals(confirm.idType) : confirm.idType != null) return false;
        if (type != confirm.type) return false;
        if (value != null ? !value.equals(confirm.value) : confirm.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (idType != null ? idType.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
