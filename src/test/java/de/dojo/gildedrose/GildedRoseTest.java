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

    @Test
    public void backstage_pass_quality_increases_by_one_if_sellin_is_equal_to_eleven() {
        // when
        Item firstItem = updateQualityForSingleItem("Backstage passes to a TAFKAL80ETC concert", 11, 5);

        // then
        assertThat(firstItem.quality).isEqualTo(6);
    }

    @Test
    public void backstage_pass_quality_increases_by_two_if_sellin_is_equal_to_ten() {
        // when
        Item firstItem = updateQualityForSingleItem("Backstage passes to a TAFKAL80ETC concert", 10, 5);

        // then
        assertThat(firstItem.quality).isEqualTo(7);
    }

    @Test
    public void backstage_pass_quality_increases_by_two_if_sellin_is_equal_to_six() {
        // when
        Item firstItem = updateQualityForSingleItem("Backstage passes to a TAFKAL80ETC concert", 6, 5);

        // then
        assertThat(firstItem.quality).isEqualTo(7);
    }

    @Test
    public void backstage_pass_quality_increases_by_three_if_sellin_is_equal_to_five() {
        // when
        Item firstItem = updateQualityForSingleItem("Backstage passes to a TAFKAL80ETC concert", 5, 5);

        // then
        assertThat(firstItem.quality).isEqualTo(8);
    }

    @Test
    public void backstage_pass_quality_increases_by_three_if_sellin_is_today() {
        // when
        Item firstItem = updateQualityForSingleItem("Backstage passes to a TAFKAL80ETC concert", 1, 5);

        // then
        assertThat(firstItem.quality).isEqualTo(8);
    }

    @Test
    public void backstage_pass_quality_drops_to_zero_after_sellin() {
        // when
        Item firstItem = updateQualityForSingleItem("Backstage passes to a TAFKAL80ETC concert", 0, 5);

        // then
        assertThat(firstItem.quality).isEqualTo(0);
    }


    private Item updateQualityForSingleItem(String name, int sellIn, int quality) {
        Item firstItem = new Item(name, sellIn, quality);
        GildedRose gildedRose = new GildedRose(new Item[]{firstItem});
        gildedRose.updateQuality();
        return firstItem;
    }
}
