package dev.gustavoteixeira;

import dev.gustavoteixeira.dto.SectorDTO;
import dev.gustavoteixeira.utils.Utils;

import java.util.Arrays;
import java.util.List;


public class App {


    public static void main(String[] args) {

        List<SectorDTO> oldSectors = Arrays.asList(getSector("B2B", "A", "B", "C"),
                getSector("B2C", "A", "B"));
        List<SectorDTO> newSectors = Arrays.asList(getSector("B2B", "B", "C"),
                getSector("B2C", "A", "B", "C"),
                getSector("CFG", "A", "F"));

        oldSectors.stream().forEach(System.out::println);
        System.out.println();
        newSectors.stream().forEach(System.out::println);



    }

    private static SectorDTO getSector(String name, String... permissions) {
        return SectorDTO.builder()
                .sectorName(name)
                .permissions(Arrays.asList(permissions))
                .build();
    }


}
