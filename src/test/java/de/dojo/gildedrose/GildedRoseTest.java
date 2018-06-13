package de.dojo.gildedrose;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GildedRoseTest {

    @Test
    public void items_degrade_twice_as_fast_after_expire_date() {
        // given
        Item firstItem = new Item("Philipp", -1, 5);
        GildedRose gildedRose = new GildedRose(new Item[]{firstItem});
        int initialQuality= firstItem.quality;

        // when
        gildedRose.updateQuality();
        int secondQuality = firstItem.quality;

        // then
        assertThat(initialQuality - secondQuality).isEqualTo(2);
    }

    @Test
    public void items_degrade_in_quality_over_time() {
        // when
        Item firstItem = updateQualityForSingleItem("Philipp", 1, 5);

        // then
        assertThat(firstItem.quality).isEqualTo(4);
    }

    @Test
    public void items_never_have_negative_quality() {
        // when
        Item firstItem = updateQualityForSingleItem("Philipp", 1, 0);

        // then
        assertThat(firstItem.quality).isGreaterThanOrEqualTo(0);
    }

    @Test
    public void sellin_degrades_over_time() {
        // when
        Item firstItem = updateQualityForSingleItem("Philipp", 3, 5);

        // then
        assertThat(firstItem.sellIn).isEqualTo(2);
    }

    @Test
    public void aged_brie_quality_increases_over_time() {
        // when
        Item firstItem = updateQualityForSingleItem("Aged Brie", 1, 5);

        // then
        assertThat(firstItem.quality).isEqualTo(6);
    }

    @Test
    public void quality_of_an_item_is_never_above_50() {
        // when
        Item firstItem = updateQualityForSingleItem("Aged Brie", 1, 50);

        // then
        assertThat(firstItem.quality).isEqualTo(50);
    }

    @Test
    public void sulfuras_never_expires() {
        // when
        Item firstItem = updateQualityForSingleItem("Sulfuras, Hand of Ragnaros", 1, 80);

        // then
        assertThat(firstItem.sellIn).isEqualTo(1);
    }

    @Test
    public void sulfuras_has_a_stable_quality() {
        // when
        Item firstItem = updateQualityForSingleItem("Sulfuras, Hand of Ragnaros", 1, 80);

        // then
        assertThat(firstItem.quality).isEqualTo(80);
    }

    private Item updateQualityForSingleItem(String name, int sellIn, int quality) {
        Item firstItem = new Item(name, sellIn, quality);
        GildedRose gildedRose = new GildedRose(new Item[]{firstItem});
        gildedRose.updateQuality();
        return firstItem;
    }
}
