package com.example.tennis.racquet.controller;

import com.example.tennis.racquet.model.Racket;
import com.example.tennis.racquet.model.Rent;
import com.example.tennis.racquet.service.RacketService;
import com.example.tennis.racquet.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/racket")
public class RacketController {

    @Autowired
    private RacketService racketService;
    @Autowired
    private RentService rentService;

    @GetMapping("/rent")
    public String rentForm(){
        return "rent-racket";
    }

    @PostMapping("/rent")
    public String rentRacket(RentForm rentForm){
        Rent rent = new Rent();
        rent.setDate(rentForm.getDate());
        rent.setRacquetNo(rentForm.getRacquetNo());
        rent.setName(rentForm.getName());
        if(rentForm.getRentOrReturn()) {
            rent.setRentOrReturn("Rent");
        } else {
            rent.setRentOrReturn("Return");
        }
        rentService.saveRent(rent);
        return "redirect:/racquet/viewRent";
    }

//    @GetMapping("/addRacquet")
//    public String addRacquetForm() {
//        return "add-racquet";
//    }
//
//    @PostMapping("/addRacquet")
//    public String addRacquet(RacquetForm racquetForm) {
//        Racquet racquet = new Racquet();
//        racquet.setRacquetNo(racquetForm.getRacquetNo());
//        racquet.setRacquetName(racquetForm.getRacquetName());
//        racquet.setOnRent(false);
//        racquetService.saveRacquet(racquet);
//        return "redirect:/racquet/viewRacquet";
//    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("racket", new Racket());
        return "register-racket";
    }

    @PostMapping("/register")
    public String createRacket(@ModelAttribute Racket racket) {
        racketService.createRacket(racket);
        return "redirect:/rackets/register";
    }

    @GetMapping("/viewRacket")
    public String viewRacket(Model model){
        List<Racket> rackets = racketService.getAllRackets();
        model.addAttribute("rackets", rackets);
        return "view-racket";
    }

    @GetMapping("/viewRent")
    public String viewRent(Model model){
        List<Rent> rents = rentService.getAllRents();
        model.addAttribute("rents", rents);
        return "view-rent";
    }

}
