package com.wujk.springbootasync;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GitHubLookupController {

    @Autowired
    private GitHubLookupService gitHubLookupService;

    @RequestMapping(value = "findAsync/{user}", method = RequestMethod.GET)
    public User findUserAsync(@PathVariable  String user) {
        try {
            return  gitHubLookupService.findUserAsync(user).get();
        } catch (Exception e) {
            e.printStackTrace();
            return  null;
        }
    }

    @RequestMapping(value = "find/{user}", method = RequestMethod.GET)
    public User findUser(@PathVariable  String user) {
        try {
            return  gitHubLookupService.findUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            return  null;
        }
    }

}
