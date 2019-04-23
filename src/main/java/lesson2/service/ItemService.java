package lesson2.service;

import lesson2.DAO.ItemDAO;
import lesson2.entity.Item;
import lesson2.exception.BadRequestException;

public class ItemService {
    public static Item save(Item item) throws Exception {
        if (item.getId() != 0) {
            throw new BadRequestException("Error: incorrect request. itemId(id:" + item.getId() + ")");
        }

        return ItemDAO.save(item);
    }

    public static Item update(Item item) throws Exception {
        return ItemDAO.update(item);
    }

    public static void delete(Item item) throws Exception {
        ItemDAO.delete(item);
    }

    public static Item findById(long itemId) throws Exception {
        if (itemId <= 0) {
            throw new BadRequestException("Error: incorrect itemId(id:" + itemId + ")");
        }

        Item item = ItemDAO.findById(itemId);
        if (item == null) {
            throw new BadRequestException("Error: item with id:" + itemId + " has not found");
        }
        return item;
    }
}
