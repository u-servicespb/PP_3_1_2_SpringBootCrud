package com.springboot6.springboot6.controller;

import com.springboot6.springboot6.model.User;
import com.springboot6.springboot6.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/templates/users")
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers()); //это пара ключ-значение. Ключ "users" будет во вьюшке all
        return "users/all";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("user", userService.getUserById(id));//это пара ключ-значение. Ключ "user" будет во вьюшке showUser
        return "users/showUser";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "users/new";
    }

    @PostMapping()
    public String addUser(@ModelAttribute("user") User user) {
        userService.addUser(user); // Добавляем этого юзера в БД
        return "redirect:/templates/users";
    }

    @GetMapping("/{id}/edit")
    public String edit (Model model, @PathVariable("id") Integer id ){
        model.addAttribute("user",userService.getUserById(id));
        return "users/edit";
    }

    @PatchMapping("/{id}")
    public String update (@ModelAttribute ("user") User updateUser, @PathVariable ("id") Integer id) {
        userService.updateUser(id, updateUser); //Находим по id того юзера, которого надо изменить
        return "redirect:/templates/users";
    }

// К этому методу нас перебрасывает из вьюшки showUser. Там есть указание на этот метод - th:method="DELETE" th:action="@{/users/{id}
    @DeleteMapping("/{id}")
    public String delete (@PathVariable("id") Integer id) {
        userService.delete(id);
        return "redirect:/templates/users"; // После удаления делаем редирект на /users
    }
}