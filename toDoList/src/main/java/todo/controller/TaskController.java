package todo.controller;


import todo.entity.Task;
import todo.repository.TaskRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/task")
public class TaskController extends HttpServlet {

    private TaskRepository taskRepository;

    @Override
    public void init() throws ServletException {
        super.init();
        taskRepository = new TaskRepository();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userLocale = request.getParameter("userLocale");
        if (userLocale != null) {
            Cookie localeCookie = new Cookie("userLocale", userLocale);
            localeCookie.setMaxAge(60 * 60 * 24 * 365);
            response.addCookie(localeCookie);
            request.setAttribute("futureCookie", userLocale);
        }

        String operation = request.getParameter("operation");

        if (operation != null) {
            handleOperation(operation, request);
            response.sendRedirect("task");
        } else {
            loadTaskListAndForward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String task = request.getParameter("newTask");

        Task newTask = new Task();
        newTask.setTask(task);
        taskRepository.save(newTask);

        response.sendRedirect("task");
    }

    private void handleOperation(String operation, HttpServletRequest request) {
        Long id = Long.parseLong(request.getParameter("id"));

        switch (operation) {
            case "update":
                // TODO add update functionality
                break;
            case "delete":
                taskRepository.deleteById(id);
                break;
        }
    }

    private void loadTaskListAndForward(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("tasks.jsp");
        List<Task> taskList = taskRepository.findAll();
        request.setAttribute("taskList", taskList);
        dispatcher.forward(request, response);
    }
}
