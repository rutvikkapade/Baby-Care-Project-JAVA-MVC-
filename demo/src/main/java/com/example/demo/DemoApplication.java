
              package com.example.demo;

              import org.springframework.beans.factory.annotation.Autowired;
              import org.springframework.boot.SpringApplication;
              import org.springframework.boot.autoconfigure.SpringBootApplication;
              import org.springframework.stereotype.Controller;
              import org.springframework.ui.Model;
              import org.springframework.web.bind.annotation.*;

              import java.sql.ResultSet;
              import java.sql.SQLException;

              @SpringBootApplication
              @Controller
              public class DemoApplication {
                  @Autowired
                  private Dbservice DB;
                  public static String email;
                  
                  public static void main(String[] args) {
                  SpringApplication.run(DemoApplication.class, args);
                  }
                  
                  @GetMapping("/hello")
                  public String hello(String name) {
                  name="Rutvik";
                  return String.format("Hello %s!", name);
                  }
                  @GetMapping("/hello1")
                  public String hello1(@RequestParam(value="name",defaultValue="Rutvik")String name){
                      return String.format("hey %s", name);
                  }
                  @ResponseBody
                  @RequestMapping("/arr")
                  public int run(){
                      int a=1;
                      return a;
                  }
                  @GetMapping("/")
                  public String greetingForm() {
                      return "index.html";
                  }
                  @GetMapping("/register")
                  public String form(Model model){
                      model.addAttribute("register",new Register());
                      return "register";
                  }
                  @PostMapping("/register")
                  public  String SubmitForm(@ModelAttribute Register register){
                      DB.addValues(register.getFname(),register.getLname(),register.getEmail(),register.getPass());
                      return "result";
                  }
                  @GetMapping("/choose")
                  public String Choose(){
                      return "choose";
                  }
                  @GetMapping("/login")
                  public String login(Model model){
                      model.addAttribute("login",new Login());
                      model.addAttribute("login",new User());

                      return "login";
                  }
                  @PostMapping("/login")
                  public String loginSubmit(@ModelAttribute Login login,@ModelAttribute User user){
                      int a=DB.auth(login.getEmail(),login.getPassword());
                      if(a==1){
                          email=login.getEmail();
                          return "user";
                      }else{
                          return "loginerror";
                      }

                  }
                  @GetMapping("/sample")
                  public String run1(){
                      return "sample";
                  }
                  @GetMapping("/name")
                  public void try1(Model model){
                      model.addAttribute("name",new Baby());
                  }
                  @PostMapping("/name")
                  public String addName(@ModelAttribute Baby baby){
                      int a=DB.addBname(baby.getName(), baby.getReligion(), baby.getLocation(),DemoApplication.email);
                      if(a==1){
                          return "success";
                      }else {
                          return "faliure";
                      }
                  }
                  @GetMapping("/print")
                  public String print(Model model) throws SQLException {
                      ResultSet rs=DB.resultset(email);
                      model.addAttribute("items", DB.createList(rs));
                      return "contribution";
                  }
                  @GetMapping("/chere")
                  public String hey(){
                      return "load";
                  }
                  @GetMapping("/allnames")
                  public String allnames(Model model) throws SQLException {
                      ResultSet rs=DB.resultset1();
                      model.addAttribute("items",DB.createList1(rs));
                      return "allnames";
                  }
                  @GetMapping("/user1")
                  public String yo(){
                      return "user1";
                  }
                  @GetMapping("/allnames1")
                  public String allnames1(Model model) throws SQLException {
                      ResultSet rs=DB.resultset1();
                      model.addAttribute("items",DB.createList1(rs));
                      return "allnames1";
                  }
                  @GetMapping("/products1")
                  public String products1(){
                      return "products1";
                  }
                  @GetMapping("/error")
                  public String  error(){
                      return "error";
                  }

              }
            