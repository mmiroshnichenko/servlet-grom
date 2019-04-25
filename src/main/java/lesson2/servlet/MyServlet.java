package lesson2.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import lesson2.entity.Item;
import lesson2.service.ItemService;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        ServletInputStream stream = req.getInputStream();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Item item = new Item();
            item = objectMapper.readValue(stream, item.getClass());
            resp.getWriter().println(ItemService.save(item));
        } catch (Exception e) {
            resp.getWriter().println(e.getMessage());
        } finally {
            try {
                stream.close();
            } catch (IOException e) {
                System.err.println("Closing streams failed");
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletInputStream stream = req.getInputStream();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Item item = new Item();
            item = objectMapper.readValue(req.getInputStream(), item.getClass());
            resp.getWriter().println(ItemService.update(item));
        } catch (Exception e) {
            resp.getWriter().println(e.getMessage());
        } finally {
            try {
                stream.close();
            } catch (IOException e) {
                System.err.println("Closing streams failed");
            }
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
