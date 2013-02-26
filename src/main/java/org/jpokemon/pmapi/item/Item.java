package org.jpokemon.pmapi.item;

import java.util.HashMap;

/**
 * Defines a basic item. This class provides the most basic attributes that all
 * items in a Pokémon game are likely to share. To create your own items, you 
 * have two options: (1) extend this class, or (2) make use of the `attribute` 
 * system outlined below.
 *
 * In addition to its basic functionality, items can have any number of other 
 * `attribute` properties, ideally which do not have executable patterns. So a 
 * `Berry` item might have an `id` attribute, a `flavour` attribute, and so on. 
 * This would also be an easy way to indicate what pocket an item belongs in. 
 * Suppose you wanted to initialize a [Cherry Berry][link1] item. This could be
 * achieved using something like
 *
 * >     Item cherriBerry = new Item();
 * >     cherryBerry.addAttribute(new IdentityAttribute(1));
 * >     cherryBerry.addAttribute(new FlavorAttribute(10, 0, 0, 0, 0));
 * >     PocketAttribute pocket = cherryBerry.addAttribute(new PocketAttribute());
 * >     pocket.setPocket(PocketAttribute.BERRIES);
 * >     // Add other relevant attributes
 *
 * These attributes and other can be found in the `attribute` sub-package. Of 
 * course, this can get a little repetitive, so it's probably a good idea to 
 * set up a `BerryFactory` if you want to make more than a few of them. Now 
 * suppose you wanted to check what pocket an item should be sorted into. If 
 * the Item was constructed as above, you could do something like
 *
 * >     boolean hasPocket = cherriBerry.hasAttribute(ItemAttributeType.POCKET);
 * >     if (hasPocket) {
 * >         PocketAttribute attribute = cherriBerry.getAttribute(ItemAttributeType.POCKET);
 * >         String pocketName = attribute.getPocket();
 * >         // Sort cherriBerry into the pocket with name "pocketName".
 * >     }
 *
 * The use of {@link ItemAttributeType} (as opposed to just indexing the 
 * attributes by class) is intended to make serialization less difficult.
 * 
 * For details on creating your own attributes, see the {@link ItemAttribute}
 * class. Note that this technique is probably not powerful enough to allow for
 * the kinds of in-game effects some items will require.
 * 
 * Note that the `attributes` {@link HashMap} will not initialize until an 
 * attribute is added. Thus, if a traditional inheritance scheme is preferable 
 * for your project, this class can be extended and this functionality ignored
 * without memory inefficiency.
 *
 * [link1]: http://bulbapedia.bulbagarden.net/wiki/Cheri_Berry
 *
 * @author Atheriel
 *
 * @since  alpha
 */
public class Item {
	/** Indicates the non-basic attributes of the item. */
	protected HashMap<ItemAttributeType, ItemAttribute> attributes;
	
	/** Indicates the name of the item (as it would appear in the bag or a shop). */
	protected String name = "????";

	/** Indicates the description of this item (as it would appear in the bag or a shop). */
	protected String description = "";

	/** Indicates whether this item is sellable to a vendor. */
	protected boolean sellable = false;
	
	/** Indicates the sale price for this item. */
	protected int salePrice = 0;
	
	/** Indicates whether this item is usable in any sense. */
	protected boolean usable = false;

	/** Indicates whether this item is consumed upon use, including use while holding. */
	protected boolean consumable = false;

	/** Indicates whether this item is holdable by a Pokémon. */
	protected boolean holdable = false;

	/** Indicates whether this item has an effect when held by a Pokémon. */
	protected boolean holdEffect = false;

	/** Gets the name of this item. */
	public String getName() {
		return name;
	}

	/** Sets the name of this item. */
	public void setName(String name) {
		this.name = name;
	}

	/** Gets the description of this item. */
	public String getDescription() {
		return description;
	}

	/** Sets the description of this item. */
	public void setDescription(String description) {
		this.description = description;
	}

	/** Checks whether this item is sellable to a vendor. */
	public boolean isSellable() {
		return sellable;
	}

	/** Sets whether this item is sellable to a vendor. */
	public void setSellable(boolean sellable) {
		this.sellable = sellable;
	}

	/** Gets the sale price for this item. */
	public int getSalePrice() {
		return salePrice;
	}

	/** Sets the sale price for this item. */
	public void setSalePrice(int salePrice) {
		this.salePrice = salePrice;
	}

	/** Checks whether this item is usable in any sense. */
	public boolean isUsable() {
		return usable;
	}

	/** Sets whether this item is usable in any sense. */
	public void setUsable(boolean usable) {
		this.usable = usable;
	}

	/** Checks whether this item is consumed upon use. */
	public boolean isConsumable() {
		return consumable;
	}

	/** Sets whether this item is consumed upon use. */
	public void setConsumable(boolean consumable) {
		this.consumable = consumable;
	}

	/** Checks whether this item can be held by a Pokémon. */
	public boolean isHoldable() {
		return holdable;
	}

	/** Sets whether this item can be held by a Pokémon. */
	public void setHoldable(boolean holdable) {
		this.holdable = holdable;
	}

	/** Checks whether this item has an effect when held by a Pokémon. */
	public boolean hasHoldEffect() {
		return holdEffect;
	}

	/** Sets whether this item has an effect when held by a Pokémon. */
	public void setHoldEffect(boolean holdEffect) {
		this.holdEffect = holdEffect;
	}

	/** Adds an attribute to the item. */
	public void addAttribute(ItemAttributeType type, ItemAttribute attribute) {
		if (attributes == null) {
			attributes = new HashMap<ItemAttributeType, ItemAttribute>();
		}
		attributes.put(type, attribute);
		// Maybe add a check to make sure it has not been added already?
	}

	/**
	 * Gets the {@link ItemAttribute} instance for this item of a given type.
	 * 
	 * @param  type The type of attribute requested.
	 * 
	 * @return      The item's attribute of this type, or `null` if it does not
	 *              possess one.
	 */
	public ItemAttribute getAttribute(ItemAttributeType type) {
		if (attributes == null) {
			return null;
		}

		if (attributes.containsKey(type)) {
			return attributes.get(type);
		}

		return null;
	}
}