package de.dojo.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            updateItem(item);
        }
    }

    private void updateItem(Item item) {
        if (isAgedBrie(item) || isBackstagePasses(item)) {
            if (item.quality < 50) {
                item.quality = item.quality + 1;

                if (isBackstagePasses(item)) {
                    if (item.sellIn < 11) {
                        if (item.quality < 50) {
                            item.quality = item.quality + 1;
                        }
                    }

                    if (item.sellIn < 6) {
                        if (item.quality < 50) {
                            item.quality = item.quality + 1;
                        }
                    }
                }
            }
        } else {
            if (item.quality > 0) {
                if (!isSulfuras(item)) {
                    item.quality = item.quality - 1;
                }
            }
        }

        if (!isSulfuras(item)) {
            item.sellIn = item.sellIn - 1;
        }

        if (item.sellIn < 0) {
            if (isAgedBrie(item)) {
                if (item.quality < 50) {
                    item.quality = item.quality + 1;
                }
            } else {
                if (isBackstagePasses(item)) {
                    item.quality = 0;
                } else {
                    if (item.quality > 0) {
                        if (!isSulfuras(item)) {
                            item.quality = item.quality - 1;
                        }
                    }
                }
            }
        }
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
