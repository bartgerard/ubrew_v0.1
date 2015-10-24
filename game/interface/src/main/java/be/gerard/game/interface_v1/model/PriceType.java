package be.gerard.game.interface_v1.model;

/**
 * PriceType
 *
 * @author bartgerard
 * @version v0.0.1
 */
public enum PriceType {
    BASE,
    SHIPPING,
    GUESSTIMATION,
    MSRP, // Manufacturer's Suggested Retail Price (MSRP), list price or Recommended Retail Price (RRP)
    UNBUNDLED,
    BUNDLED

    /**
     * Both MSRP and UNBUNDLED are fictional prices that should not be taken into account when calculating totals unless you understand their meaning.
     */
}
