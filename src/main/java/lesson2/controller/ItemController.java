package lesson2.controller;

import lesson2.entity.Item;
import lesson2.service.ItemService;

public class ItemController {
    public static Item save(Item item) throws Exception {
        return ItemService.save(item);
    }

    public static Item update(Item item) throws Exception {
        return ItemService.update(item);
    }

    public static void delete(long itemId) throws Exception {
        ItemService.delete(itemId);
    }

    public static Item findById(long itemId) throws Exception {
        return ItemService.findById(itemId);
    }
}
