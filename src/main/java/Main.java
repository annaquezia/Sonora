import DAO.MusicDAO;
import model.Music;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        MusicDAO musicDAO = new MusicDAO();

        Music newMusic = new Music(0, "Love Me Not", "Ravyn Lenae", "Bird's Eye", 3.33, false);
        musicDAO.create(newMusic);

        List<Music> musics = musicDAO.read();
        for (Music music : musics) {
            System.out.println(music.getId() + " - " + music.getAlbum());
        }

        Music updateMusic = new Music(2, "Love Me Not", "Ravyn Lenae", "mudei isso", 3.33,false );
        musicDAO.update(updateMusic);

        List<Music> musicas = musicDAO.read();
        for (Music music : musicas) {
            System.out.println(music.getId() + " - " + music.getAlbum());
        }

        musicDAO.delete(2);

        List<Music> delmusicas = musicDAO.read();
        for (Music music : delmusicas) {
            System.out.println(music.getId() + " - " + music.getAlbum());
        }
    }
}
