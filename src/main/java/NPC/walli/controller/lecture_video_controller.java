package NPC.walli.controller;

import lombok.Getter;
import lombok.Setter;

import NPC.walli.domain.Lecture_video;
import NPC.walli.service.VideoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.IOException;

@Controller
@PostMapping("/videos/add")
public class LoginController {
    @PostMapping("/videos/add")
    public String addVideo(@RequestParam("title") String title,
                           @RequestParam("file") MultipartFile file, Model model) throws IOException {
        String id = VideoService.addVideo(title, file);
        return "redirect:/videos/" + id;
    }


    @GetMapping("/videos/{id}")
    public String getVideo(@PathVariable String id, Model model) throws Exception {
        Lecture_video video = VideoService.addVideo(id);
            model.addAttribute("title", video.getTitle());
            model.addAttribute("url", "/videos/stream/" + id);
            return "videos";
            }


@GetMapping("/videos/stream/{id}")
public void streamVideo(@PathVariable String id, HttpServletResponse response) throws Exception {
    Lecture_video video = VideoService.getVideo(id);
        FileCopyUtils.copy(video.getStream(), response.getOutputStream());
        }