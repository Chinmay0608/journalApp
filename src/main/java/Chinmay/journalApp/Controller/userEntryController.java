package Chinmay.journalApp.Controller;

import Chinmay.journalApp.Service.UserEntryService;
import Chinmay.journalApp.Service.WeatherService;
import Chinmay.journalApp.api.response.WeatherResponse;
import Chinmay.journalApp.entity.User;
import Chinmay.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/user")
public class userEntryController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WeatherResponse weatherResponse;

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private UserEntryService userEntryService;

    @GetMapping("/get-All")
    public List<User> getAllUsers(){ // localhost:8080/journal GET
        return userEntryService.getALl();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUserEntryById(@PathVariable ObjectId myId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/create-user")
    public ResponseEntity<?> addUser(@RequestBody User user){
        try{
            User existingUser = userEntryService.findByUserName(user.getUserName());
            if (existingUser != null) {
                return new ResponseEntity<>("Username already exists", HttpStatus.CONFLICT); // 409
            }

            userEntryService.saveNewUser(user);

            return new ResponseEntity<>(user, HttpStatus.CREATED); // 201
        } catch (Exception e) {
            return new ResponseEntity<>("Error creating user: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userInDb = userEntryService.findByUserName(userName);
        if(userInDb != null){
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userEntryService.saveNewUser(userInDb);
            return new ResponseEntity<>(userInDb,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> greeting(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse apiResponse = weatherService.getWeather("Jaipur");
        String greeting = "";
        if(apiResponse != null && apiResponse.getCurrent() != null){
            greeting = ", Weather feels like "+ apiResponse.getCurrent().getFeelslike();
        }
        return new ResponseEntity<>("Hi " + authentication.getName()+ greeting, HttpStatus.OK);
    }
}
