package com.example.moment.controller;

import com.example.moment.config.UserPrincipal;
import com.example.moment.entity.Progress;
import com.example.moment.entity.User;
import com.example.moment.service.ProgressService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class HomeController {

    private static final List<String> SEQUENCE = List.of(
        "start", "3-2", "3-3", "homeroom", "cafeteria", "nurse-office", "principalsoffice", "1ftoilet", "audiovisualroom", "overnightroom", "counselingroom", "1felevator", "1fstairs", "library", "teachersroom", "computerroom", "clubroom1", "clubroom2", "clubroom3", "artroom", "broadcastingroom", "studentcouncilroom", "bridge", "gym", "gym2f", "clubroom4", "clubroom5", "clubroom6", "stairs2f", "musicroom", "conveniencestore", "clubroom7", "clubroom8", "englishroom", "supplyroom", "scienceroom", "powersupplyroom", "computerserverroom", "pharmacy", "convenience", "atm", "foodcourt", "cookingroom", "rehabilitationroom", "allergy", "radiation", "morgue", "administration", "intensivecareunit", "operatingroom", "emergency", "filmingroom", "xrayroom", "surgery", "medicine", "orthopedics", "ophthalmology", "injectionroom", "room201", "room202", "room203", "room204", "anticancer", "transplant", "bloodvessels", "kidney", "room301", "room302", "room303", "hosbridge", "rooftop", "watertank", "flower", "storage"
    );

    private final ProgressService progressService;

    public HomeController(ProgressService progressService) {
        this.progressService = progressService;
    }

    @GetMapping("/")
    @ResponseBody
    public String index() {
        return "✅ Railway Spring Boot 서버가 정상 작동 중입니다!";
    }


    // Progress 화면에서 'start' stage 를 쓰기 위한 entry point
    @GetMapping("/home")
    public String home(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        User user = userPrincipal.getUser();
        model.addAttribute("user", user);
        // 홈 진입도 하나의 스테이지로 저장하고 싶으면 아래 주석 해제
        // progressService.updateStage(user, "start");
        return "home";
    }

    @GetMapping("/3-2")
    public String page3_2(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("3-2", userPrincipal, model);
    }

    @GetMapping("/3-3")
    public String page3_3(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("3-3", userPrincipal, model);
    }

    @GetMapping("/homeroom")
    public String homeroom(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("homeroom", userPrincipal, model);
    }

    @GetMapping("/cafeteria")
    public String cafeteria(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("cafeteria", userPrincipal, model);
    }

    @GetMapping("/nurse-office")
    public String nurseOffice(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("nurse-office", userPrincipal, model);
    }

    @GetMapping("/principalsoffice") // ← 하이픈 없이
    public String principalsoffice(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("principalsoffice", userPrincipal, model);
    }

    @GetMapping("/1ftoilet") // ← 하이픈 없이
    public String toilet1f(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("1ftoilet", userPrincipal, model);
    }

    @GetMapping("/audiovisualroom") // ← 하이픈 없이
    public String audiovisualroom(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("audiovisualroom", userPrincipal, model);
    }

    @GetMapping("/overnightroom") // ← 하이픈 없이
    public String overnightroom(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("overnightroom", userPrincipal, model);
    }

    @GetMapping("/counselingroom") // ← 하이픈 없이
    public String counselingroom(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("counselingroom", userPrincipal, model);
    }

    @GetMapping("/1felevator") // ← 하이픈 없이
    public String elevator1f(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("1felevator", userPrincipal, model);
    }

    @GetMapping("/1fstairs") // ← 하이픈 없이
    public String stairs1f(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("1fstairs", userPrincipal, model);
    }

    @GetMapping("/library") // ← 하이픈 없이
    public String library(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("library", userPrincipal, model);
    }

    @GetMapping("/teachersroom") // ← 하이픈 없이
    public String teachersroom(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("teachersroom", userPrincipal, model);
    }

    @GetMapping("/computerroom") // ← 하이픈 없이
    public String computerroom(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("computerroom", userPrincipal, model);
    }

    @GetMapping("/clubroom1") // ← 하이픈 없이
    public String clubroom1(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("clubroom1", userPrincipal, model);
    }

    @GetMapping("/clubroom2") // ← 하이픈 없이
    public String clubroom2(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("clubroom2", userPrincipal, model);
    }


    @GetMapping("/clubroom3") // ← 하이픈 없이
    public String clubroom3(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("clubroom3", userPrincipal, model);
    }

    @GetMapping("/artroom") // ← 하이픈 없이
    public String artroom(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("artroom", userPrincipal, model);
    }

    @GetMapping("/broadcastingroom") // ← 하이픈 없이
    public String broadcastingroom(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("broadcastingroom", userPrincipal, model);
    }

    @GetMapping("/studentcouncilroom") // ← 하이픈 없이
    public String studentcouncilroom(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("studentcouncilroom", userPrincipal, model);
    }


    @GetMapping("/bridge") // ← 하이픈 없이
    public String bridge(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("bridge", userPrincipal, model);
    }

    @GetMapping("/gym") // ← 하이픈 없이
    public String gym(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("gym", userPrincipal, model);
    }

    @GetMapping("/gym2f") // ← 하이픈 없이
    public String gym2f(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("gym2f", userPrincipal, model);
    }

    @GetMapping("/clubroom4") // ← 하이픈 없이
    public String clubroom4(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("clubroom4", userPrincipal, model);
    }

    @GetMapping("/clubroom5") // ← 하이픈 없이
    public String clubroom5(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("clubroom5", userPrincipal, model);
    }

    @GetMapping("/clubroom6") // ← 하이픈 없이
    public String clubroom6(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("clubroom6", userPrincipal, model);
    }

    @GetMapping("/stairs2f") // ← 하이픈 없이
    public String stairs2f(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("stairs2f", userPrincipal, model);
    }

    @GetMapping("/musicroom") // ← 하이픈 없이
    public String musicroom(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("musicroom", userPrincipal, model);
    }

    @GetMapping("/conveniencestore") // ← 하이픈 없이
    public String conveniencestore(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("conveniencestore", userPrincipal, model);
    }

    @GetMapping("/clubroom7") // ← 하이픈 없이
    public String clubroom7(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("clubroom7", userPrincipal, model);
    }
    @GetMapping("/clubroom8") // ← 하이픈 없이
    public String clubroom8(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("clubroom8", userPrincipal, model);
    }
    @GetMapping("/englishroom") // ← 하이픈 없이
    public String englishroom(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("englishroom", userPrincipal, model);
    }
    @GetMapping("/supplyroom") // ← 하이픈 없이
    public String supplyroom(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("supplyroom", userPrincipal, model);
    }
    @GetMapping("/scienceroom") // ← 하이픈 없이
    public String scienceroom(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("scienceroom", userPrincipal, model);
    }

    @GetMapping("/powersupplyroom") // ← 하이픈 없이
    public String powersupplyroom(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("powersupplyroom", userPrincipal, model);
    }
    @GetMapping("/computerserverroom") // ← 하이픈 없이
    public String computerserverroom(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("computerserverroom", userPrincipal, model);
    }
    @GetMapping("/pharmacy") // ← 하이픈 없이
    public String pharmacy(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("pharmacy", userPrincipal, model);
    }
    @GetMapping("/convenience") // ← 하이픈 없이
    public String convenience(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("convenience", userPrincipal, model);
    }
    @GetMapping("/atm") // ← 하이픈 없이
    public String atm(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("atm", userPrincipal, model);
    }

    @GetMapping("/foodcourt") // ← 하이픈 없이
    public String foodcourt(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("foodcourt", userPrincipal, model);
    }

    @GetMapping("/cookingroom") // ← 하이픈 없이
    public String cookingroom(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("cookingroom", userPrincipal, model);
    }

    @GetMapping("/rehabilitationroom") // ← 하이픈 없이
    public String rehabilitationroom(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("rehabilitationroom", userPrincipal, model);
    }

    @GetMapping("/allergy") // ← 하이픈 없이
    public String allergy(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("allergy", userPrincipal, model);
    }
    @GetMapping("/radiation") // ← 하이픈 없이
    public String radiation(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("radiation", userPrincipal, model);
    }
    @GetMapping("/morgue") // ← 하이픈 없이
    public String morgue(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("morgue", userPrincipal, model);
    }
    @GetMapping("/administration") // ← 하이픈 없이
    public String administration(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("administration", userPrincipal, model);
    }
    @GetMapping("/intensivecareunit") // ← 하이픈 없이
    public String intensivecareunit(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("intensivecareunit", userPrincipal, model);
    }
    @GetMapping("/operatingroom") // ← 하이픈 없이
    public String operatingroom(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("operatingroom", userPrincipal, model);
    }
    @GetMapping("/emergency") // ← 하이픈 없이
    public String emergency(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("emergency", userPrincipal, model);
    }
    @GetMapping("/filmingroom") // ← 하이픈 없이
    public String filmingroom(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("filmingroom", userPrincipal, model);
    }
    @GetMapping("/xrayroom") // ← 하이픈 없이
    public String xrayroom(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("xrayroom", userPrincipal, model);
    }
    @GetMapping("/surgery") // ← 하이픈 없이
    public String surgery(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("surgery", userPrincipal, model);
    }
    @GetMapping("/medicine") // ← 하이픈 없이
    public String medicine(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("medicine", userPrincipal, model);
    }
    @GetMapping("/orthopedics") // ← 하이픈 없이
    public String orthopedics(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("orthopedics", userPrincipal, model);
    }
    @GetMapping("/ophthalmology") // ← 하이픈 없이
    public String ophthalmology(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("ophthalmology", userPrincipal, model);
    }
    @GetMapping("/injectionroom") // ← 하이픈 없이
    public String injectionroom(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("injectionroom", userPrincipal, model);
    }
    @GetMapping("/room201") // ← 하이픈 없이
    public String room201(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("room201", userPrincipal, model);
    }
    @GetMapping("/room202") // ← 하이픈 없이
    public String room202(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("room202", userPrincipal, model);
    }
    @GetMapping("/room203") // ← 하이픈 없이
    public String room203(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("room203", userPrincipal, model);
    }
    @GetMapping("/room204") // ← 하이픈 없이
    public String room204(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("room204", userPrincipal, model);
    }
    @GetMapping("/anticancer") // ← 하이픈 없이
    public String anticancer(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("anticancer", userPrincipal, model);
    }
    @GetMapping("/transplant") // ← 하이픈 없이
    public String transplant(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("transplant", userPrincipal, model);
    }
    @GetMapping("/bloodvessels") // ← 하이픈 없이
    public String bloodvessels(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("bloodvessels", userPrincipal, model);
    }
    @GetMapping("/kidney") // ← 하이픈 없이
    public String kidney(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("kidney", userPrincipal, model);
    }
    @GetMapping("/room301") // ← 하이픈 없이
    public String room301(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("room301", userPrincipal, model);
    }
    @GetMapping("/room302") // ← 하이픈 없이
    public String room302(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("room302", userPrincipal, model);
    }
    @GetMapping("/room303") // ← 하이픈 없이
    public String room303(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("room303", userPrincipal, model);
    }
    @GetMapping("/hosbridge") // ← 하이픈 없이
    public String hosbridge(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("hosbridge", userPrincipal, model);
    }
    @GetMapping("/rooftop") // ← 하이픈 없이
    public String rooftop(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("rooftop", userPrincipal, model);
    }
    @GetMapping("/watertank") // ← 하이픈 없이
    public String watertank(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("watertank", userPrincipal, model);
    }
    @GetMapping("/flower") // ← 하이픈 없이
    public String flower(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("flower", userPrincipal, model);
    }
    @GetMapping("/storage") // ← 하이픈 없이
    public String storage(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        return handleStage("storage", userPrincipal, model);
    }


    /**
     * 공통 진입 처리
     * 1) DB에 저장된 현재 stage(progStage) 가져오기
     * 2) SEQUENCE 상에서 progIdx, targetIdx 계산
     * 3) targetIdx <= progIdx+1 이면 허용 (이미 해본 단계 or 바로 다음)
     *      → progressService.updateStage(user, target) 한 뒤 해당 뷰 반환
     *    아니면 /progress 로 redirect
     */
    private String handleStage(String targetStage,
                               @AuthenticationPrincipal UserPrincipal userPrincipal,
                               Model model) {
        User user = userPrincipal.getUser();
        Progress prog = progressService.findByUser(user);

        String progStage = prog.getStage();
        int progIdx   = SEQUENCE.indexOf(progStage);
        int targetIdx = SEQUENCE.indexOf(targetStage);

        // progStage가 sequence에 없으면 0으로 간주
        if (progIdx < 0) progIdx = 0;

        // 이미 해본 단계(<=progIdx) 이거나 바로 다음 단계(progIdx+1)만 허용
        if (targetIdx >= 0 && targetIdx <= progIdx + 1) {
            // 컨트롤러마다 중복으로 쓰이지 않도록 한 곳에서 업데이트
            progressService.updateStage(user, targetStage);
            model.addAttribute("user", user);
            return targetStage;
        }

        // 아니면 Progress 화면으로
        return "redirect:/progress";
    }
}
