package DAO;

import model.Music;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MusicDaoTest {

    MusicDAO musicDao =  new MusicDAO();

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("TC01 - Listar músicas")
    void read() {
        musicDao.create(new Music(0, "Musica_TDD", "Artista_TDD", "Album_TDD", 3.3, false));
        musicDao.create(new Music(0, "Musica_TDD1", "Artista_TDD1", "Album_TDD1", 3.3, false));

        List<Music> all = musicDao.read();
        assertTrue(all.size() >= 2, "read() deve listar os elementos inseridos no banco de dados");
    }

    @Test
    @DisplayName("TC02 - Criar música")
    void createMusic() {
        Music music = new Music(0, "Musica_TDD", "Artista_TDD", "Album_TDD", 3.3, false);
        musicDao.create(music);
        List<Music> all = musicDao.read();
        assertTrue(all.stream().anyMatch(x -> "Musica_TDD".equals(x.getTitle())),
                "Após create(), read() deve conter a música inserida"
        );
    }

    @Test
    @DisplayName("TC03 - Editar uma música")
    void editMusic() {
        Music music = new Music(0, "Musica_TDD", "Artista_TDD", "Album_TDD", 3.3, false);
        Integer newId = musicDao.create(music);
        Music updateMusic = new Music(newId, "Musica_TDD_Atualizada", "Artista_TDD", "Album_TDD", 3.3, false);
        musicDao.update(updateMusic);
        List<Music> all = musicDao.read();
        assertTrue(all.stream().anyMatch(x -> "Musica_TDD_Atualizada".equals(x.getTitle())),
                "Após a atualização Musica_TDD_Atualizada deve estar no banco de dados"
        );
    }

    @Test
    @DisplayName("TC04 - Deletar música")
    void deleteMusic() {
        Music music = new Music(0, "Musica_TDD", "Artista_TDD", "Album_TDD", 3.3, false);
        Integer newId = musicDao.create(music);
        musicDao.delete(newId);

        boolean exists = musicDao.read().stream().anyMatch(m -> m.getId() == newId);
        assertFalse(exists, "A música deletada não deve mais aparecer no read()");
    }

    @Test
    @DisplayName("TC05 - Favoritar uma música")
    void favoriteMusic() {
        Music music = new Music(0, "Musica_TDD", "Artista_TDD", "Album_TDD", 3.3, false);
        Integer newId = musicDao.create(music);
        musicDao.favorite(newId);

        Music favoriteMusic = musicDao.read().stream().filter(m -> m.getId() == newId).findFirst().orElse(null);
        assertTrue(favoriteMusic.isFavorite(), "A música deveria ter sido marcada como favorita");
    }

    @Test
    @DisplayName("TC06 - Filtrar músicas favoritas")
    void filter_shouldReturnOnlyFavorites() {
        musicDao.create(new Music(0, "Musica_TDD", "Artista_TDD", "Album_TDD", 3.3, true));
        musicDao.create(new Music(0, "Musica_TDD1", "Artista_TDD1", "Album_TDD1", 3.3, false));
        musicDao.create(new Music(0, "Musica_TDD2", "Artista_TDD2", "Album_TDD2", 3.3, true));

        List<Music> favorites = musicDao.filterByFavorite(true);

        assertTrue(!favorites.isEmpty());
    }
}