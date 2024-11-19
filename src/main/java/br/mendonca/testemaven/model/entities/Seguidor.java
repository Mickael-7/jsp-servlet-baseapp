package br.mendonca.testemaven.model.entities;

import java.util.UUID;

public class Seguidor {
    private String uuid;
    private String seguidorUuid;
    private String seguidoUuid;

    public Seguidor(String seguidorUuid, String seguidoUuid) {
        this.seguidorUuid = seguidorUuid;
        this.seguidoUuid = seguidoUuid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getSeguidorUuid() {
        return seguidorUuid;
    }

    public void setSeguidorUuid(String seguidorUuid) {
        this.seguidorUuid = seguidorUuid;
    }

    public String getSeguidoUuid() {
        return seguidoUuid;
    }

    public void setSeguidoUuid(String seguidoUuid) {
        this.seguidoUuid = seguidoUuid;
    }
}
