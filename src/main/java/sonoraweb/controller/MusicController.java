package sonoraweb.controller;

import DAO.MusicDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import model.Music;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MusicController {

    private MusicDAO musicDAO = new MusicDAO();

    @GetMapping("/")
    public String home(Model model) {
        List<Music> musics = musicDAO.read();
        model.addAttribute("musics", musics);
        model.addAttribute("musicForm",new Music(0, "", "", "", 0.0, false) );
        return "index";
    }

    @GetMapping("/favorites")
    public String showFavorites(Model model) {
        List<Music> favorites = musicDAO.filterByFavorite(true);
        model.addAttribute("musics", favorites);
        model.addAttribute("musicForm", new Music(0, "", "", "", 0.0, false));
        return "index";
    }

    @PostMapping("/musics")
    public String create(@ModelAttribute("musicForm") Music music) {
        musicDAO.create(music);
        return "redirect:/";
    }

    @PostMapping("/musics/{id}/delete")
    public String delete(@PathVariable int id) {
        musicDAO.delete(id);
        return "redirect:/";
    }

    @PostMapping("/musics/{id}/toggle-favorite")
    public String toggleFavorite(@PathVariable int id) {
        Music m = musicDAO.readById(id);
        if (m != null) {
            m.setFavorite(!m.isFavorite());
            musicDAO.update(m);
        }
        return "redirect:/";
    }

    @GetMapping("/musics/{id}/edit")
    public String editForm(@PathVariable int id, Model model) {
        Music music = musicDAO.readById(id);
        model.addAttribute("musicForm", music);
        return "edit";
    }

    @PostMapping("/musics/update")
    public String update(@ModelAttribute("musicForm") Music music) {
        musicDAO.update(music);
        return "redirect:/";
    }
}
