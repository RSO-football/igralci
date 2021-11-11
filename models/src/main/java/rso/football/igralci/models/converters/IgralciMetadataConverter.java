package rso.football.igralci.models.converters;

import rso.football.igralci.lib.IgralciMetadata;
import rso.football.igralci.models.entities.IgralciMetadataEntity;

public class IgralciMetadataConverter {

    public static IgralciMetadata toDto(IgralciMetadataEntity entity) {

        IgralciMetadata dto = new IgralciMetadata();
        dto.setIgralecId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(dto.getDescription());

        return dto;
    }

    public static IgralciMetadataEntity toEntity(IgralciMetadata dto) {

        IgralciMetadataEntity entity = new IgralciMetadataEntity();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());

        return entity;
    }

}
