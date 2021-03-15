package dev.gustavoteixeira.utils;

import dev.gustavoteixeira.dto.SectorDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

public class Utils {

    public String getDifference(List<SectorDTO> oldSectors, List<SectorDTO> newSectors ) {
        // Pegar as permissões e colocar como key em um HashMap, e no value agrupar o setor
        Map<String, List<String>> oldPermissionsPerSector = new HashMap<>();
        Map<String, List<String>> newPermissionsPerSector = new HashMap<>();

        listerize(oldSectors, oldPermissionsPerSector);
        listerize(newSectors, newPermissionsPerSector);
        StringBuilder sb = new StringBuilder();
        newPermissionsPerSector.forEach((k, v) -> {
            //Se já tinha a permissão
            if (oldPermissionsPerSector.containsKey(k)) {
                List<String> difference = newPermissionsPerSector.get(k).stream()
                        .filter(permission -> !oldPermissionsPerSector.get(k).contains(permission))
                        .collect(Collectors.toList());
                if (!difference.isEmpty()) {
                    //Setor B2C alterado: permissão C incluída
                    sb.append("Setor " + difference.get(0)+" alterado: ");
                    sb.append("permissão " + k + " incluída.");
                }
            } else {
                sb.append("Setor " + v.get(0)+" alterado: ");
                sb.append("permissão " + k + " incluída.");
            }
        });



        return sb.toString();
    }

    private void listerize(List<SectorDTO> sector, Map<String, List<String>> map) {
        sector.stream().forEach(
                sectorDTO -> sectorDTO.getPermissions().stream().forEach(
                        permission -> {
                            if (!map.containsKey(permission)) {
                                map.put(permission, Arrays.asList(sectorDTO.getSectorName()));
                            } else {
                                List<String> list = new ArrayList<>();
                                List<String> strings = map.get(permission);
                                list.addAll(strings);
                                list.add(sectorDTO.getSectorName());
                                map.put(permission, list);
                            }
                        }
                ));
    }

}
