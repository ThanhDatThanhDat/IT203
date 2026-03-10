package SS3.bai6;

import java.util.List;
import java.util.stream.Collectors;

record Post(String title, List<String> tags) {}

public class bai6 {
    public static void main(String[] args) {
        List<Post> posts = List.of(
                new Post("Java Programming", List.of("java", "backend")),
                new Post("Python Data Science", List.of("python", "data")),
                new Post("Frontend Basics", List.of("html", "css", "js"))
        );

        List<String> allTags = posts.stream()
                .flatMap(post -> post.tags().stream())
                .collect(Collectors.toList());

        System.out.println("--- Danh sách tags sau khi làm phẳng ---");
        System.out.println(allTags);

        List<String> uniqueTags = posts.stream()
                .flatMap(post -> post.tags().stream())
                .distinct()
                .sorted()
                .toList();

        System.out.println("\n--- Danh sách tags duy nhất (Sắp xếp) ---");
        System.out.println(uniqueTags);
    }
}