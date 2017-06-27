package net.windcrowaya.crudapp.controller;

import net.windcrowaya.crudapp.model.User;
import net.windcrowaya.crudapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    public static final int MAX_USERS_PER_PAGE = 10;
    private UserService userService;
    private int currentPage = 1;
    private int maxNumberOfPages;

    @Autowired
    @Qualifier(value = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "users", method = RequestMethod.GET)
    public String listUsers(Model model) {
        List<User> allUsers = userService.listUsers();
        List<User> usersToDisplay = new ArrayList<User>(MAX_USERS_PER_PAGE);

        if (allUsers.size() % MAX_USERS_PER_PAGE == 0)
            maxNumberOfPages = allUsers.size() / MAX_USERS_PER_PAGE;
        else
            maxNumberOfPages = allUsers.size() / MAX_USERS_PER_PAGE + 1;

        for (int i = (currentPage-1)*MAX_USERS_PER_PAGE;
             i < (currentPage-1)*MAX_USERS_PER_PAGE + MAX_USERS_PER_PAGE && i < allUsers.size();
             i++)
            usersToDisplay.add(allUsers.get(i));

        model.addAttribute("user", new User());
        model.addAttribute("pageNumber", currentPage);
        model.addAttribute("listUsers", usersToDisplay);

        return "users";
    }

    @RequestMapping(value = "users/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user) {
        if (user.getId() == 0)
            this.userService.addUser(user);
        else
            this.userService.updateUser(user);

        return "redirect:/users";
    }

    @RequestMapping("/remove/{id}")
    public String removeUser(@PathVariable("id") int id) {
        this.userService.removeUser(id);

        return "redirect:/users";
    }

    @RequestMapping("edit/{id}")
    public String editUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", this.userService.getUser(id));
        model.addAttribute("listUsers", this.userService.listUsers());

        return "users";
    }

    @RequestMapping(value = "userdata")
    public String userData(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", this.userService.getUser(id));

        return "userdata";
    }

    @RequestMapping(value = "nextPage", method = RequestMethod.GET)
    public String nextPage(Model model) {
        List<User> allUsers = userService.listUsers();
        if (++currentPage > maxNumberOfPages)
            currentPage = maxNumberOfPages;
        List<User> usersToDisplay = new ArrayList<User>(MAX_USERS_PER_PAGE);

        for (int i = (currentPage-1)*MAX_USERS_PER_PAGE;
             i < (currentPage-1)*MAX_USERS_PER_PAGE + MAX_USERS_PER_PAGE && i < allUsers.size();
             i++)
            usersToDisplay.add(allUsers.get(i));

        model.addAttribute("user", new User());
        model.addAttribute("pageNumber", currentPage);
        model.addAttribute("listUsers", usersToDisplay);

        return "users";
    }

    @RequestMapping(value = "previousPage", method = RequestMethod.GET)
    public String previousPage(Model model) {
        List<User> allUsers = userService.listUsers();
        if (--currentPage < 1)
            currentPage = 1;
        List<User> usersToDisplay = new ArrayList<User>(MAX_USERS_PER_PAGE);

        for (int i = (currentPage-1)*MAX_USERS_PER_PAGE;
             i < (currentPage-1)*MAX_USERS_PER_PAGE + MAX_USERS_PER_PAGE && i < allUsers.size();
             i++)
            usersToDisplay.add(allUsers.get(i));

        model.addAttribute("user", new User());
        model.addAttribute("pageNumber", currentPage);
        model.addAttribute("listUsers", usersToDisplay);

        return "users";
    }
}