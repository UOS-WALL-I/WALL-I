package NPC.walli.controller;

import NPC.walli.domain.PDF;
import NPC.walli.service.PDFService;
import com.mongodb.client.MongoClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Slf4j
@Controller
@AllArgsConstructor
public class PDFController {
    private final PDFService pdfService;

    @PostMapping("/pdf/add")
    public String addPDF(@RequestParam("title") String title, @RequestParam("image") MultipartFile image, Model model) throws IOException {
        System.out.println(title);
        String id = pdfService.addPDF(title, image);
        return "redirect:/file/" + id;
    }

    @GetMapping("file/{id}")
    public String getPDF(@PathVariable String id, Model model) {
        return "";
    }

    @GetMapping("/file")
    public String fileForm() {
        return "file";
    }
}
