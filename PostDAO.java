//data object access sınıfı

import java.net.ProxySelector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;

public class PostDAO {
    public void insertPost(Post post) {
        String sql = "INSERT INTO posts(user_id,title,content) VALUES (?,?,?)";

        try (Connection conn = sqlConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, post.getUserId());
            pstmt.setString(2, post.getTitle());
            pstmt.setString(3, post.getContent());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Post> getAllPosts() {
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT * FROM posts";
        try (Connection conn = sqlConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Post post = new Post(
                        rs.getInt("user_id"),
                        rs.getString("title"),
                        rs.getString("content"));

                post.setId(rs.getInt("id"));
                post.setCreatedAt(rs.getTimestamp("created_at"));

                posts.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return posts;
    }

    public void UpdatePosts(int postId, String newTitle, String newContent) {
        String sql = "UPDATE posts SET title =?,content=?,WHERE id =?";
        try (Connection conn = sqlConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newTitle);
            pstmt.setString(2, newContent);
            pstmt.setInt(3, postId);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("post güncellendi ");
            } else {
                System.out.println("Post bulunamadı");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePost(int postId) {
        String sql = "DELETE FROM posts WHERE id =?";

        try (Connection conn = sqlConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, postId);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("post silindi");
            } else {
                System.out.println("post bulunamadı");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Post> listPosts() {
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT * FROM posts";

        try (Connection conn = sqlConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Post post = new Post(rs.getInt("user_id"),
                        rs.getString("title"),
                        rs.getString("content"));

                post.setId(rs.getInt("id"));
                post.setCreatedAt(rs.getTimestamp("created_at"));

                posts.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }
}
