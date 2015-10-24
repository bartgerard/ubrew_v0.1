package be.gerard.game.service.schema;

import be.gerard.game.interface_v1.model.CurrencyCode;
import be.gerard.game.interface_v1.model.PriceType;
import be.gerard.game.service.model.BundleRecord;
import be.gerard.game.service.model.CoreGameRecord;
import be.gerard.game.service.model.ExpansionRecord;
import be.gerard.game.service.model.PriceRecord;
import be.gerard.game.service.model.ProductRecord;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * InventoryTest
 *
 * @author bartgerard
 * @version v0.0.1
 */
public class InventoryTest {

    @Test
    public void buildInventory() {
        BundleRecord bundle = new BundleRecord();

        CoreGameRecord the7thContinent = new CoreGameRecord("7th-continent", 180263, "The 7th Continent", new PriceRecord(PriceType.MSRP, BigDecimal.valueOf(70), CurrencyCode.EUR));
        ExpansionRecord the7thContinentSwampOfMadness = new ExpansionRecord("7th-continent-swamp-madness", 184904, "The 7th Continent: Swamp of Madness", null);
        ExpansionRecord the7thContinentTheIcyMaze = new ExpansionRecord("7th-continent-icy-maze", 183660, "The 7th Continent: The Icy Maze", new PriceRecord(PriceType.MSRP, BigDecimal.valueOf(20), CurrencyCode.EUR));
        ExpansionRecord the7thContinentTheForbiddenSanctuary = new ExpansionRecord("7th-continent-forbidden-sanctuary", 184186, "The 7th Continent: The Forbidden Sanctuary", new PriceRecord(PriceType.MSRP, BigDecimal.valueOf(20), CurrencyCode.EUR));
        ExpansionRecord the7thContinentFearTheDevourers = new ExpansionRecord("7th-continent-fear-devourers", 186381, "The 7th Continent: Fear the Devourers", new PriceRecord(PriceType.MSRP, BigDecimal.valueOf(22), CurrencyCode.EUR));
        ExpansionRecord the7thContinentPathOfRepentance = new ExpansionRecord("7th-continent-path-repentance", 185743, "The 7th Continent: Path of Repentance", new PriceRecord(PriceType.MSRP, BigDecimal.valueOf(9), CurrencyCode.EUR));

        // 8 Bone Dice & Dice Bag MSRP: 12 EUR, ACTUAL: 9 EUR
        // Thick Card Sleeves MSRP: 9 EUR, ACTUAL: 7 EUR

        bundle.getProducts().add(new ProductRecord(the7thContinent, 1, new PriceRecord(PriceType.UNBUNDLED, BigDecimal.valueOf(59), CurrencyCode.EUR)));
        bundle.getProducts().add(new ProductRecord(the7thContinentSwampOfMadness));
        bundle.getProducts().add(new ProductRecord(the7thContinentTheIcyMaze));
        bundle.getProducts().add(new ProductRecord(the7thContinentTheForbiddenSanctuary));
        bundle.getProducts().add(new ProductRecord(the7thContinentFearTheDevourers, 1, new PriceRecord(PriceType.UNBUNDLED, BigDecimal.valueOf(17), CurrencyCode.EUR)));
        bundle.getProducts().add(new ProductRecord(the7thContinentPathOfRepentance, 1, new PriceRecord(PriceType.UNBUNDLED, BigDecimal.valueOf(7), CurrencyCode.EUR)));

        bundle.getPrices().add(new PriceRecord(PriceType.BUNDLED, BigDecimal.valueOf(122), CurrencyCode.EUR));

        BigDecimal total = BigDecimal.ZERO;

        for (ProductRecord product : bundle.getProducts()) {
            if (product.getProductDefinition().getMsrp() != null) {
                total = total.add(product.getProductDefinition().getMsrp().getValue());
            }
        }

        System.out.print(total);
    }

}
