package com.customer.farm.customerfarm.Controller;

import com.customer.farm.customerfarm.Entity.Customers;
import com.customer.farm.customerfarm.Entity.Farms;
import com.customer.farm.customerfarm.Entity.Roles;
import com.customer.farm.customerfarm.Entity.Users;
import com.customer.farm.customerfarm.Repository.CustomersRepository;
import com.customer.farm.customerfarm.Repository.FarmsRepository;
import com.customer.farm.customerfarm.Repository.RolesRepository;
import com.customer.farm.customerfarm.Repository.UsersRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.security.Principal;
import java.util.*;

@RestController
public class UsersController {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    CustomersRepository customersRepository;

    @Autowired
    RolesRepository rolesRepository;

    @Autowired
    FarmsRepository farmsRepository;

    @Autowired
    CustomersRepository customersRepository;

    @GetMapping("/users")
    public ResponseEntity<List<Users>> getAllUsers(){
        List<Users> entities = usersRepository.findAll();
        return ResponseEntity.ok().body(entities);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {

        Users user = usersRepository.findById(id)
        		.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));
		return ResponseEntity.ok().body(user);
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Users createUser(@RequestParam MultiValueMap<String,String> data){

            Roles role = rolesRepository.getOne(Long.parseLong(data.getFirst("roles_id")));

        Customers customer = customersRepository.getOne(Long.parseLong(data.getFirst("customers_id")));

        Users u = new Users();
        u.setName(data.getFirst("name"));

        u.setRolesId(role);
        u.setCustomersId(customer);
        u.setUsername(data.getFirst("username"));
        u.setPassword(data.getFirst("password"));
        u.setAddress(data.getFirst("address"));
        return usersRepository.save(u);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable(value = "id") Long userId,
                                            @Valid @RequestBody Users users){
        Users user = usersRepository.getOne(userId);
        user.setName(users.getName());
        user.setAddress(users.getAddress());
        user.setUsername(users.getUsername());
        user.setPassword(users.getPassword());
        user.setCustomersId(users.getCustomersId());
        final Users finalUser = usersRepository.save(user);
    return ResponseEntity.ok(finalUser);
    }

    @DeleteMapping("users/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
    	Users user = usersRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

    	usersRepository.delete(user);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
    }

    @PostMapping("/login")
    public ResponseEntity<Users> getUserByUsernamePassword(@RequestParam String username, @RequestParam String password) {
        Users user = usersRepository.findUserByUsernameAndPassword(username, password);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/users/farms")
    public ResponseEntity<List<Farms>> getFarmsByLoggedUser(Principal principal) throws ResourceNotFoundException {
//        final String loggedInUserName = principal.getName();
        Users loggedUser = usersRepository.findUserByUsername(principal.getName());
        List<Farms> farms = new ArrayList<>(0);

        if(loggedUser.getRolesId().getName().equalsIgnoreCase("admin")){
            Customers c =  customersRepository.getOne(1l);
            Hibernate.initialize(c.getClass());
            
            farms  = farmsRepository.findFarmsByCustomersId(c.getId().longValue());
        }else if(loggedUser.getRolesId().getName().equalsIgnoreCase("user")){
            farms = loggedUser.getFarms();
        }
        return ResponseEntity.ok(farms);
    }

}
