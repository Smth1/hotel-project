package com.roma.distr.entities;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
public class HotelClient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "client_id")
    private UUID clientId;
    private String name;

    public HotelClient() {
    }

    public HotelClient(String name) {
        this.name = name;
        clientId = UUID.randomUUID();
    }

    public UUID getClientId() {
        return clientId;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HotelClient)) return false;
        HotelClient that = (HotelClient) o;
        return Objects.equals(clientId, that.clientId) &&
                Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, getName());
    }

    @Override
    public String toString() {
        return "HotelClient{" +
                "clientId=" + clientId +
                ", name='" + name + '\'' +
                '}';
    }
}
