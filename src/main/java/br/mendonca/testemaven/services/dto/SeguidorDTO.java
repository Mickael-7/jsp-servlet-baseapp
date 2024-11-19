package br.mendonca.testemaven.services.dto;

import java.util.UUID;

public class SeguidorDTO {
    private String seguidorUuid;
    private String seguidoUuid;

    public SeguidorDTO() {}

    public SeguidorDTO(String seguidorUuid, String seguidoUuid) {
        this.seguidorUuid = seguidorUuid;
        this.seguidoUuid = seguidoUuid;
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
