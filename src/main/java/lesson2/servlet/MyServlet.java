package lesson2.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import lesson2.entity.Item;
import lesson2.service.ItemService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet(urlPatterns = "/test")
public class MyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            resp.getWriter().println(ItemService.findById(Long.parseLong(req.getParameter("id"))));
        } catch (Exception e) {
            resp.getWriter().println(e.getMessage());
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Item item = new Item();
            item = objectMapper.readValue(req.getInputStream(), item.getClass());
            item.setDateCreated(new Date());
            item.setLastUpdatedDate(new Date());
            resp.getWriter().println(ItemService.save(item));
        } catch (Exception e) {
            resp.getWriter().println(e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Item item = new Item();
            item = objectMapper.readValue(req.getInputStream(), item.getClass());
            Item itemForUpdate = ItemService.findById(item.getId());
            item.setDateCreated(itemForUpdate.getDateCreated());
            item.setLastUpdatedDate(new Date());
            resp.getWriter().println(ItemService.update(item));
        } catch (Exception e) {
            resp.getWriter().println(e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Item item = ItemService.findById(Long.parseLong(req.getParameter("id")));
            ItemService.delete(item);
        } catch (Exception e) {
            resp.getWriter().println(e.getMessage());
        }
    }
}
