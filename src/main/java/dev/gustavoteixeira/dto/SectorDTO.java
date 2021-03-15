package dev.gustavoteixeira.dto;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Builder
public class SectorDTO {

    String sectorName;

    List<String> permissions = new ArrayList<>();
}
