
import java.net.SocketTimeoutException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.management.timer.TimerNotification;

public class main {
    public static void main(String[] args) throws SQLException {
        PostDAO postdao = new PostDAO();
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println(" 1 yeni post ekle :\n");
            System.out.println(" 2 postları listele :\n");
            System.out.println(" 3 bir postu güncelle :\n");
            System.out.println(" 4 post silmek için seç :\n");
            System.out.println(" 0 programdan çıkılıyor :\n\n ");
            System.out.println("seçiminiz");

            int secim = scan.nextInt();
            scan.nextLine();

            if (secim == 1) {

                System.out.println("kullanıcı id giriniz ");
                int userId = scan.nextInt();
                scan.nextLine();

                System.out.println("başlık giriniz :");
                String title = scan.nextLine();

                System.out.println("içerik giriniz ");
                String content = scan.nextLine();

                Post newPost = new Post(userId, title, content);
                postdao.insertPost(newPost);
                System.out.println("post eklendi");
            } else if (secim == 2) {
                List<Post> posts = postdao.listPosts(); // metottan listeyi alıyoruz

                if (posts.isEmpty()) {
                    System.out.println("Hiç post bulunamadı.");
                } else {
                    System.out.println("Tüm postlar:");
                    for (Post p : posts) {
                        System.out.println("ID: " + p.getId());
                        System.out.println("User ID: " + p.getUserId());
                        System.out.println("Title: " + p.getTitle());
                        System.out.println("Content: " + p.getContent());
                        System.out.println("Oluşturulma Tarihi: " + p.getCreatedAt());
                        System.out.println("-----------------------------");
                    }
                }
            } else if (secim == 3) {
                System.out.println("güncellemek istediğiniz postun numarasını giriniz");
                int postId = scan.nextInt();
                scan.nextLine();
                System.out.println("yeni başlığı giriniz");
                String newTitle = scan.nextLine();
                System.out.println("yeni içeriği giriniz");
                String newContent = scan.nextLine();

                postdao.UpdatePosts(postId, newTitle, newContent);
                System.out.println("içerik başarıyla güncellendi");
            }

            else if (secim == 4) {
                System.out.println("postları listelemek için 'list' yazınız");
                String list = scan.nextLine();
                if (list.equalsIgnoreCase("list")) {
                    List<Post> posts = postdao.listPosts();
                    if (posts.isEmpty()) {
                        System.out.println("hiç post yok");
                    } else {
                        for (Post post : posts) {
                            System.out.println("ID :" + post.getId() +
                                    "| Başlık :" + post.getTitle() +
                                    "| İçerik :" + post.getContent());
                        }
                        System.out.println("listeden ilgili silmek istediğiniz idyi seçiniz");
                        int deletPostId = scan.nextInt();
                        scan.nextLine();
                        postdao.deletePost(deletPostId);
                    }

                } else {
                    System.out.println("listeleme başarısız");
                }

            } else if (secim == 0) {
                System.out.println("programdan çıkılıyor..");
                break;
            } else {
                System.out.println("geçersiz işlem");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("Program sonlandırıldı 👋");
        scan.close();

    }
}