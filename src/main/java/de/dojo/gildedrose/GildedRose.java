package de.dojo.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {

            if (isAgedBrie(item)) {
                updateAgedBrie(item);
            } else if (isBackstagePasses(item)) {
                updateBackstagePasses(item);
            } else if (isSulfuras(item)) {
                // sulfuras doesnt change
            } else {
                updateOrdinaryItem(item);
            }
        }
    }

    private void updateBackstagePasses(Item item) {

        incrementQuality(item);

        if (item.sellIn < 11) {
            incrementQuality(item);
        }

        if (item.sellIn < 6) {
            incrementQuality(item);
        }

        decrementSellIn(item);

        if (item.sellIn < 0) {
            item.quality = 0;
        }
    }


    private void updateOrdinaryItem(Item item) {
        decrementQuality(item);

        decrementSellIn(item);

        if (item.sellIn < 0) {
            decrementQuality(item);
        }
    }

    private void decrementQuality(Item item) {
        if (isAboveMinQuality(item)) {
            item.quality = item.quality - 1;
        }
    }


    private void updateAgedBrie(Item item) {
        incrementQuality(item);

        decrementSellIn(item);

        if (item.sellIn < 0) {
            incrementQuality(item);
        }
    }

    private void incrementQuality(Item item) {
        if (isBelowMaxQuality(item)) {
            item.quality = item.quality + 1;
        }
    }

    private boolean isAboveMinQuality(Item item) {
        return item.quality > 0;
    }

    private boolean isBelowMaxQuality(Item item) {
        return item.quality < 50;
    }

    private void decrementSellIn(Item item) {
        item.sellIn = item.sellIn - 1;
    }

    private boolean isSulfuras(Item item) {
        return item.name.equals("Sulfuras, Hand of Ragnaros");
    }

    private boolean isBackstagePasses(Item item) {
        return item.name.equals("Backstage passes to a TAFKAL80ETC concert");
    }

    private boolean isAgedBrie(Item item) {
        return item.name.equals("Aged Brie");
    }
}
