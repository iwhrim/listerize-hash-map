package dev.gustavoteixeira;


import dev.gustavoteixeira.dto.SectorDTO;
import dev.gustavoteixeira.utils.Utils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {

    @Test
    public void incluirUmaPermissãoJáExistenteEmOutroSetorEmUmNovoSetor() {
        List<SectorDTO> oldSectors = Arrays.asList(getSector("B2B", "A", "B", "C"));
        List<SectorDTO> newSectors = Arrays.asList(getSector("B2B", "A", "B", "C"),
                getSector("B2C", "C"));

        Utils utils = new Utils();
        String difference = utils.getDifference(oldSectors, newSectors);

        assertEquals("Setor B2C alterado: permissão C incluída.", difference);

    }

    @Test
    public void incluirDuasPermissõesJáExistentesEmOutroSetorEmUmNovoSetor() {
        List<SectorDTO> oldSectors = Arrays.asList(getSector("B2B", "A", "B", "C"),
                getSector("B2C", "A", "B"));
        List<SectorDTO> newSectors = Arrays.asList(getSector("B2B", "A", "B", "C"),
                getSector("B2C", "A", "B", "C"),
                getSector("CFG", "A", "F"));

        oldSectors.stream().forEach(System.out::println);
        System.out.println();
        newSectors.stream().forEach(System.out::println);

        Utils utils = new Utils();
        System.out.print("utils = " + utils.toString());
        String difference = utils.getDifference(oldSectors, newSectors);

        assertEquals("Setor B2C alterado: permissão B e C incluída", difference);

    }


    private SectorDTO getSector(String name, String... permissions) {
        return SectorDTO.builder()
                .sectorName(name)
                .permissions(Arrays.asList(permissions))
                .build();
    }

}
