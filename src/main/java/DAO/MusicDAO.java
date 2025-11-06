package DAO;

import dataBaseConection.ConnectionFactory;
import model.Music;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MusicDAO {

    public Integer create(Music music) {
        String sql = "INSERT INTO musica (titulo, artista, album, duracao, favorito) VALUES (?, ?, ?, ?, ?)";
        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, music.getTitle());
            stmt.setString(2, music.getArtist());
            stmt.setString(3, music.getAlbum());
            stmt.setDouble(4, music.getDuration());
            stmt.setBoolean(5, music.isFavorite());
            int rows = stmt.executeUpdate();

            if (rows == 0) throw new SQLException("Insert retornou 0 linhas");
            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    int id = keys.getInt(1);
                    music.setId(id);
                    return id;
                }
            }
            return null;
        } catch (SQLException e) {
            System.err.println("[CREATE] SQLState=" + e.getSQLState() + " Code=" + e.getErrorCode() + " Msg=" + e.getMessage());
            throw new RuntimeException("Erro ao adicionar música", e);
        }
    }

    public List<Music> read() {
        String sql = "SELECT * FROM musica";
        List<Music> musics = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Music music = new Music(
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getString("artista"),
                    rs.getString("album"),
                    rs.getDouble("duracao"),
                    rs.getBoolean("favorito")
                );
                musics.add(music);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar músicas", e);
        }
        return musics;
    }

    public void update(Music music) {
        String sql = "UPDATE musica SET titulo = ?, artista = ?, album = ?, duracao = ?, favorito = ? WHERE id = ?";
        try (Connection connection = ConnectionFactory.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, music.getTitle());
            stmt.setString(2, music.getArtist());
            stmt.setString(3, music.getAlbum());
            stmt.setDouble(4, music.getDuration());
            stmt.setBoolean(5, music.isFavorite());
            stmt.setInt(6, music.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar música", e);
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM musica WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar música", e);
        }
    }

    public Music readById(int id) {
        String sql = "SELECT id, titulo, artista, album, duracao, favorito FROM musica WHERE id = ?";
        Music music = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    music = new Music(
                            rs.getInt("id"),
                            rs.getString("titulo"),
                            rs.getString("artista"),
                            rs.getString("album"),
                            rs.getDouble("duracao"),
                            rs.getBoolean("favorito")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar música por ID", e);
        }

        return music;
    }

    public void favorite(int id) {
        String sql = "UPDATE musica SET favorito = ? WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setBoolean(1, true);
            stmt.setInt(2, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao favoritar música", e);
        }
    }

    public List<Music> filterByFavorite(boolean favorite) {
        String sql = "SELECT id, titulo, artista, album, duracao, favorito FROM musica WHERE favorito = ?";
        List<Music> musics = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setBoolean(1, favorite); // true = só favoritos; false = só não-favoritos

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Music m = new Music(
                            rs.getInt("id"),
                            rs.getString("titulo"),
                            rs.getString("artista"),
                            rs.getString("album"),
                            rs.getDouble("duracao"),
                            rs.getBoolean("favorito")
                    );
                    musics.add(m);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao filtrar por favorito", e);
        }

        return musics;
    }
}
