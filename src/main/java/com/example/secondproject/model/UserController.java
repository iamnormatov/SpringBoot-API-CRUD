package com.example.secondproject.model;

import com.example.secondproject.user.Response;
import com.example.secondproject.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "user")

public class UserController {
    private List<User> userList;
    private Integer userIn;

    public UserController(){
        this.userList = new ArrayList<>();
        this.userIn = 0;
    }

    @PostMapping(value = "/create")
    public Response<User> createUser(@RequestBody User user){
        user.setUserId(++this.userIn);
        user.setCreatedAt(LocalDateTime.now());
        this.userList.add(user);
        return Response.<User>builder()
                .succes(true)
                .messege("OK")
                .data(user)
                .build();
    }

    @GetMapping(value = "/get")
    public Response<User> getUser(@RequestParam(value = "id") Integer userId){
        for (User user : this.userList) {
            if (user.getUserId().equals(userId)){
                return Response.<User>builder()
                        .succes(true)
                        .messege("Good")
                        .data(user)
                        .build();
            }
        }
        return Response.<User>builder()
                .messege("Bad")
                .code(-1)
                .build();
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable(value = "id") Integer userId){
        for (User user1 : this.userList) {
            if (user1.getUserId().equals(userId)){

                String updatedName = user.getName();
                String updatedSurname = user.getSurname();
                Integer updatedAge = user.getAge();
                String updatedEmail = user.getEmail();
                String updatedPassword = user.getPassword();

                if (updatedName != null) {
                    user1.setName(updatedName);
                }
                if (updatedSurname != null) {
                    user1.setSurname(updatedSurname);
                }
                if (updatedAge != null) {
                    user1.setAge(updatedAge);
                }
                if (updatedEmail != null) {
                    user1.setEmail(updatedEmail);
                }
                if (updatedPassword != null) {
                    user1.setPassword(updatedPassword);
                }

                user1.setUpdatedAt(LocalDateTime.now());
                return ResponseEntity.ok("User is successful updated");
            }
        }
        return ResponseEntity.badRequest().body("User is not found");
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<?> deleteUser(@RequestParam(value = "id") Integer userId){
        for (User user : this.userList) {
            if (user.getUserId().equals(userId)){
                this.userList.remove(user);
                user.setDeletedAt(LocalDateTime.now());
                return ResponseEntity.ok("User is successful deleted");
            }
        }
        return ResponseEntity.badRequest().body("User is not found");
    }
}
